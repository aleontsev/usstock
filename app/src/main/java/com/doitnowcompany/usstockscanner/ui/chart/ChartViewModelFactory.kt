package com.doitnowcompany.usstockscanner.ui.chart

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity

class ChartViewModelFactory(
    private val tickerEntity: TickerEntity,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChartViewModel::class.java)) {
            return ChartViewModel(
                tickerEntity,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
