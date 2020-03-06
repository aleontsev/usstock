package com.doitnowcompany.usstockscanner.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "ticker_table")
data class TickerEntity(
    @PrimaryKey val uid: Int,
    val ticker: String,
    val time: String,
    val last_price: Double,
    val volume: Int,
    val net_change: Double,
    val change: Double
)

