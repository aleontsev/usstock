package com.doitnowcompany.usstockscanner.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doitnowcompany.usstockscanner.network.TickerData
import kotlinx.android.parcel.Parcelize
import kotlin.math.floor

@Parcelize
@Entity(tableName = "ticker_table")
data class TickerEntity(
    @PrimaryKey (autoGenerate = true)
    val uid: Int,
    val ticker: String,
    val time: String,
    val last_price: Double,
    val volume: Int,
    val net_change: Double,
    val change: Double
) : Parcelable {
    constructor(tickerData:TickerData): this(0, tickerData.ticker,tickerData.time,
        tickerData.last_price?:0.00, tickerData.volume?:0, tickerData.net_change?:0.00,
        tickerData.change?.let{(floor(it*10000) /100.0)}?:0.00)
}


