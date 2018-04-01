package com.himanshurawat.weather.pojo

import java.io.Serializable

data class HourlyData(val time:Long,val summary:String,val icon:String,val precipIntensity:Float,val precipProbability:Float,
                      val temperature:Float,val apparentTemperature:Float,val dewPoint:Float,val humidity:Float,val pressure:Float,val windSpeed:Float,
                      val windGust:Float,val windBearing:Float,val cloudCover:Float,val uvIndex:Int,val visibility:Float,val ozone:Float): Serializable