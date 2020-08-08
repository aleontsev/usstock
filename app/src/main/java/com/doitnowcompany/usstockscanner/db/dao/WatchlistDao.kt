package com.doitnowcompany.usstockscanner.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doitnowcompany.usstockscanner.db.entity.WatchlistEntity

interface WatchlistDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from watchlist_table ORDER BY change DESC")
    fun getAlphabetizedWatchlistTickers(): LiveData<List<WatchlistEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(watchlistTicker: WatchlistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTickers(watchlistTickerRoomList : List<WatchlistEntity>);

    @Query("DELETE FROM watchlist_table")
    suspend fun deleteAll()
}