package com.himanshurawat.weather.pojo

import java.io.Serializable

data class Hourly(val summary:String,val icon:String, val data:List<HourlyData>):Serializable {
}