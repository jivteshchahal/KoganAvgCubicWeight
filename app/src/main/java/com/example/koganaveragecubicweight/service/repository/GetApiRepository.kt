package com.example.koganaveragecubicweight.service.repository

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.koganaveragecubicweight.R
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

    // return live data
    fun getVolleyData(
        activity: Activity,
        product: String
    ): MutableLiveData<List<ObjectContentModel>> {

        val url = URL(activity.getString(R.string.api_url))
        val urrl = URL(url.toExternalForm() + product)
        val request = StringRequest(
            Request.Method.GET,
            urrl.toString(),
            Response.Listener { response ->
                val gson = Gson()
                val listType = object : TypeToken<MutableList<ObjectContentModel>>() {}.type
                if (response.isNotEmpty()) {
                    val je = gson.fromJson(response, JsonObject::class.java)
                    val contentList = gson.fromJson<MutableList<ObjectContentModel>>(
                        je.getAsJsonArray(activity.getString(R.string.api_object)),
                        listType
                    )
                    mutableLiveDataList.value = contentList
                    if (je.get(activity.getString(R.string.api_next))
                            .toString() != activity.getString(
                            R.string.api_str_null
                        ) && str != je.get(activity.getString(R.string.api_next)).toString()
                    ) {
                        str = je.get(activity.getString(R.string.api_next)).toString()
                        getVolleyData(activity, str.substring(2, str.length - 1))
                    }
                } else {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.api_error_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            Response.ErrorListener {
                Log.e("Error", "Volley did not work ${it.localizedMessage}")
                Toast.makeText(
                    activity,
                    activity.getString(R.string.api_error_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        val requestQueue = Volley.newRequestQueue(activity)
        requestQueue.add(request)
        return mutableLiveDataList
    }
}