package com.doitnowcompany.usstockscanner     

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doitnowcompany.usstockscanner.db.dao.TickerDao
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity
import com.doitnowcompany.usstockscanner.network.IEXApiService
import kotlinx.coroutines.withTimeout


class TickerRepository(private val network: IEXApiService, private val tickerDao: TickerDao) {

    val allTickers: LiveData<List<TickerEntity>> = tickerDao.getAlphabetizedTickers()

    /*
   * Refresh tickers and save results to the offline cache(database)
   * */
    suspend fun refreshTickers() {
        try {
            //getting deferred object
            val getTickersDeferred = network.getTickers()
            val resultResponse = getTickersDeferred.await()
            if (resultResponse.isNotEmpty()) {
                //deleting all tickers before updating
                tickerDao.deleteAll()
                for(s in resultResponse) {
                    val newTickerEntity = TickerEntity(s)
                    tickerDao.insert(newTickerEntity)
                }
            }

        } catch (error: Throwable) {
            throw TitleRefreshError("Unable to refresh tickers", error)
        }
    }


        suspend fun insert(ticker: TickerEntity) {
            tickerDao.insert(ticker)
        }


    }

    class TitleRefreshError(message: String, cause: Throwable) : Throwable(message, cause)
