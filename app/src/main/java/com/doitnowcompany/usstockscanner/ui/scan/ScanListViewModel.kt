package com.doitnowcompany.usstockscanner.ui.scan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.doitnowcompany.usstockscanner.TickerRepository
import com.doitnowcompany.usstockscanner.db.AppDatabase
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity
import com.doitnowcompany.usstockscanner.network.IEXApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScanListViewModel (application: Application) : AndroidViewModel(application) {

    // Reference to the repository
    private val repository: TickerRepository
    // Updated list of tickers when they change
    val allTickers: LiveData<List<TickerEntity>>

    private val _selectedTicker = MutableLiveData<TickerEntity>()

    val selectedTicker: LiveData<TickerEntity>
        get() = _selectedTicker

    init{
        // Getting reference to the TickerDao from AppDatabase to construct
        // the correct TickerRepository
        val tickerDao = AppDatabase.getDatabase(application, viewModelScope).tickerDao()
        repository = TickerRepository(IEXApi.retrofitService, tickerDao)
        allTickers = repository.allTickers
    }

    //using viewModelScope to run operations using coroutines
    fun insert(ticker: TickerEntity) = viewModelScope.launch {
        repository.insert(ticker)
    }

    //refreshing tickers from network
    fun refreshTickers() = viewModelScope.launch {
        repository.refreshTickers()
    }

    //when list item is clicked we are setting the _selectedTicker
    fun displayTickerChart(tickerEntity: TickerEntity){
        _selectedTicker.value = tickerEntity
    }

    //after the navigation has taken place, we are setting _selectedTicker to null
    fun displayTickerChartComplete(){
        _selectedTicker.value = null
    }

}