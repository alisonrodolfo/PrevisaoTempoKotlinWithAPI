package com.example.android.architecture.blueprints.todoapp.data.source

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("q") city: String,  @Query("lang") lang: String, @Query("units") units: String, @Query("APPID") app_id: String): Call<WeatherResponse>
}

