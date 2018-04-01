package com.himanshurawat.weather.pojo

import java.io.Serializable

data class Daily(val summary:String,val icon:String,val data:List<DailyData>): Serializable {
}