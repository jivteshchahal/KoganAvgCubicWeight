package com.example.koganaveragecubicweight.viewModel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.koganaveragecubicweight.service.model.ObjectContentModel
import com.example.koganaveragecubicweight.service.repository.GetApiRepository

class AvgCubicWeightViewModel : ViewModel() {
    private lateinit var getApiRepository: GetApiRepository

    fun init() {
        getApiRepository = GetApiRepository.getInstance()
    }

    // live data repository
    fun getContacts(activity: Activity, productListNumber:String): LiveData<List<ObjectContentModel>> {
        return getApiRepository.getVolleyData(activity, productListNumber)
    }
}
