package com.example.koganaveragecubicweight.service.repository

import android.app.Activity
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.koganaveragecubicweight.service.model.ObjectContentModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.net.URL

class GetApiRepository {
    private var mutableLiveDataList: MutableLiveData<List<ObjectContentModel>> =
        MutableLiveData<List<ObjectContentModel>>()
    private var str = ""

    companion object {
        private lateinit var instance: GetApiRepository
        fun getInstance(): GetApiRepository {
            instance = GetApiRepository()
            return instance
        }
    }

    fun getVolleyData(
        activity: Activity,
        product: String
    ): MutableLiveData<List<ObjectContentModel>> {

        val url = URL("http://wp8m3he1wt.s3-website-ap-southeast-2.amazonaws.com/")
        val urrl = URL(url.toExternalForm() + product)
        Log.e("Url", urrl.toString())
        val request = StringRequest(
            Request.Method.GET,
            urrl.toString(),
            Response.Listener { response ->
                val gson = Gson()
                val listType = object : TypeToken<MutableList<ObjectContentModel>>() {}.type
                if (response.isNotEmpty()) {
                    val je = gson.fromJson(response, JsonObject::class.java)
                    val contentList = gson.fromJson<MutableList<ObjectContentModel>>(
                        je.getAsJsonArray("objects"),
                        listType
                    )
                    mutableLiveDataList.value = contentList
                    Log.e("ddddddddddddddddddddddd", je.get("next").toString())
                    if (je.get("next").toString() != "null" && str != je.get("next").toString()
                    ) {
                        str = je.get("next").toString()
                        getVolleyData(activity, str.substring(2, str.length - 1))
                    }
                } else {
                    Log.e("Error", "Did not get data from server")
                }
            },
            Response.ErrorListener {
                Log.e("Error", "Volley did not work ${it.localizedMessage}")
            }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(request)
        return mutableLiveDataList
    }
}