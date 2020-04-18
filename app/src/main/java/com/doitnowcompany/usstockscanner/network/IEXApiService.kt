package com.doitnowcompany.usstockscanner.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://sandbox.iexapis.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface IEXApiService {
    @GET("stable/stock/market/list/iexpercent?listLimit=10&token=Tsk_a1853bb7918c46738cde0ca271e6c739")
    fun getTickers():
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