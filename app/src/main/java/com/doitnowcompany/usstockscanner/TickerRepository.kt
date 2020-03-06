package com.doitnowcompany.usstockscanner

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.doitnowcompany.usstockscanner.db.dao.TickerDao
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity


class TickerRepository(private val tickerDao: TickerDao){

    val allTickers: LiveData<List<TickerEntity>> = tickerDao.getAlphabetizedTickers()

    suspend fun insert(ticker: TickerEntity){
        tickerDao.insert(ticker)
    }
}