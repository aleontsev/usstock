package com.doitnowcompany.usstockscanner     



import androidx.lifecycle.LiveData
import com.doitnowcompany.usstockscanner.App.Companion.context
import com.doitnowcompany.usstockscanner.db.dao.TickerDao
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity
import com.doitnowcompany.usstockscanner.network.IEXApiService


class TickerRepository(private val network: IEXApiService, private val tickerDao: TickerDao) {

    val allTickers: LiveData<List<TickerEntity>> = tickerDao.getAlphabetizedTickers()

    //Refresh tickers and save results to the offline cache(database)
    //Gainers
    suspend fun refreshTickers(direction: Boolean) {
        try {
            //getting token
            val token = context.resources.getString(R.string.token_production)
            //getting deferred object
            val getTickersDeferred = if (direction) {
                network.getTickersGainersAsync(token)
            } else {
                network.getTickersLosersAsync(token)
            }
            val resultResponse = getTickersDeferred.await()
            if (resultResponse.isNotEmpty()) {
                //deleting all tickers before updating
                tickerDao.deleteAll()
                for (s in resultResponse) {
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
