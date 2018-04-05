package com.himanshurawat.weather.util

class Constant{
    companion object {
        //User Preferences
        const val USER_PREF :String = "userPref"
        const val LATITUDE:String = "latitude"
        const val LONGITUDE:String = "longitude"

        //Weather
        const val WEATHER_DETAILS:String = "weatherDetails"

        //Location Request Settings
        const val SET_INTERVAL:Long = 2000
        const val NUM_UPDATES:Int = 1

        //Location Permission
        const val FINE_LOCATION:String = android.Manifest.permission.ACCESS_FINE_LOCATION
        const val COARSE_LOCATION:String = android.Manifest.permission.ACCESS_COARSE_LOCATION

        //Location Request Code
        const val LOCATION_REQUEST_CODE:Int = 7


        private const val API_KEY = "fc515061464572dc8effc3e7d9bb6b6c"
        const val BASE_URL = "https://api.darksky.net/forecast/$API_KEY/"
    }
}