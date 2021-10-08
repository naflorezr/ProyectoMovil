package com.example.colombiadiversa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PoiViewModel : ViewModel() {

    private val selected = MutableLiveData<PoiItem>()

    fun getSelected() : LiveData<PoiItem> = selected

    fun select(poi: PoiItem) {
        selected.value = poi
    }
}