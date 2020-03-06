package com.doitnowcompany.usstockscanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity

@Dao
interface TickerDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from ticker_table ORDER BY change ASC")
    fun getAlphabetizedTickers(): LiveData<List<TickerEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(ticker: TickerEntity)

    @Query("DELETE FROM ticker_table")
    suspend fun deleteAll()
}
