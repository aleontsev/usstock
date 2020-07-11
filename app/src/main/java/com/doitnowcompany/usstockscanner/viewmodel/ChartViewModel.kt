package com.doitnowcompany.usstockscanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ChartViewModel (application: Application) : AndroidViewModel(application) {

    private val _chartURL = MutableLiveData<String>()

    val chartURL : LiveData<String>
    get() = _chartURL
}
