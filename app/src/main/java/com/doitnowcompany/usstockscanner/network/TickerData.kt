package com.doitnowcompany.usstockscanner.network

import com.squareup.moshi.Json

data class TickerData(
    @Json(name = "symbol")
    val ticker: String = "TSLA",
    @Json(name = "latestPrice")
    val time: String = "01",
    @Json(name = "latestUpdate")
    val last_price: Double = 1.00,
    @Json(name = "volume")
    val volume: Int? = 100,
    @Json(name = "change")
    val net_change: Double = 1.00,
    @Json(name = "changePercent")
    val change: Double  = 0.1
)