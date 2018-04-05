package com.himanshurawat.weather.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherInterface {

    @GET("{latitude},{longitude}")
    fun getWeatherResponse(@Path("latitude") latitude: Float, @Path("longitude") longitude: Float, @Query("units") units: String): Call<WeatherResponse>

}