package com.himanshurawat.weather.retrofit

import com.himanshurawat.weather.pojo.Currently
import com.himanshurawat.weather.pojo.Daily
import com.himanshurawat.weather.pojo.Hourly
import java.io.Serializable

data class WeatherResponse(val latitude:Float,val longitude:Float,val timezone:Long,
                           val currently:Currently,val hourly:Hourly,val daily: Daily) : Serializable
