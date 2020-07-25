package com.doitnowcompany.usstockscanner.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity

class ChartViewModel(tickerEntity: TickerEntity, application: Application) : AndroidViewModel(application) {

    private val _chartURL = MutableLiveData<String>()

    val chartURL: LiveData<String>
        get() = _chartURL

    // Initialize the _selectedProperty MutableLiveData
    init {
        _chartURL.value = "http://finviz.com/chart.ashx?t="+tickerEntity.ticker+"&ty=c&ta=1&p=d&s=l.png"
    }
    //http://finviz.com/chart.ashx?t=RNET&ty=c&ta=1&p=d&s=l.png

}
