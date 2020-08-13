package com.doitnowcompany.usstockscanner.network


import com.doitnowcompany.usstockscanner.App
import com.doitnowcompany.usstockscanner.R
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(App.context.resources.getString(R.string.base_url_production))
    .build()

interface IEXApiService {

    //Getting gainers to the list
    @GET("stable/stock/market/list/gainers")
    fun getTickersGainersAsync(@Query("token") token:String):
            Deferred<List<TickerData>>

    //Getting losers to the list
    @GET("/stable/stock/market/list/losers")
    fun getTickersLosersAsync(@Query("token") token:String):
            Deferred<List<TickerData>>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object IEXApi {
    val retrofitService : IEXApiService by lazy {
        retrofit.create(IEXApiService::class.java)
    }
}