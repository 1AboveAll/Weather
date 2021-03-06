package com.himanshurawat.weather.pojo

import java.io.Serializable

data class DailyData (val time:Long,val summary:String,val icon:String,val sunriseTime:Long,val sunsetTime:Long,
                      val precipIntensity:Float,val precipIntensityMax:Float,val precipIntensityMaxTime:Long,
                      val precipProbability:Float,val precipType:String,val temperatureHigh:Float,
                      val temperatureHighTime:Long,val temperatureLow:Float,val temperatureLowTime:Long,
                      val apparentTemperatureHigh:Float,val apparentTemperatureHighTime:Long,val apparentTemperatureLow:Float,
                      val apparentTemperatureLowTime:Long,val dewPoint:Float,val humidity:Float,val pressure:Float,val windSpeed:Float,
                      val windGust:Float,val windGustTime:Long,val windBearing:Int,val cloudCover:Float,val uvIndex:Int,val uvIndexTime:Long,
                      val ozone:Float,val temperatureMin:Float,val temperatureMinTime:Long,val temperatureMax:Float,
                      val temperatureMaxTime:Long,val apparentTemperatureMin:Float,val apparentTemperatureMinTime:Long,
                      val apparentTemperatureMax:Float,val apparentTemperatureMaxTime:Long): Serializable