package com.example.app_cityweather

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Define a service interface
interface APPService {
    @GET("data/2.5/weather")
    suspend fun getAppData(@Query("q") location:String, @Query("units") unit: String, @Query("lang") lang: String,
                           @Query("appid") api_key: String): WeatherData
}

// Create a Moshi JSON converter object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Create a Retrofit object
private val retrofit = Retrofit.Builder()
    .baseUrl(WeatherViewModel.API_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

// Create a singleton object to call the AppService
object GetService {
    val retrofitService: APPService by lazy {
        retrofit.create(APPService::class.java)
    }
}