package com.example.colombiadiversa


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharingViewModel : ViewModel() {

    private var poiClickedData: MutableLiveData<Any>? = null

    fun setPoiClickedData(poiData: Any) {
        poiClickedData?.value = poiData

    }

    fun getPoiClickedData(): MutableLiveData<Any>? {
        if (poiClickedData == null) {
            poiClickedData = MutableLiveData()
        }
        return poiClickedData
    }
}