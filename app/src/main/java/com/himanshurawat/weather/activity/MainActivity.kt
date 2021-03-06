package com.himanshurawat.weather.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.himanshurawat.weather.R
import com.himanshurawat.weather.retrofit.WeatherInterface
import com.himanshurawat.weather.retrofit.WeatherResponse
import com.himanshurawat.weather.util.Constant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var userPref:SharedPreferences
    lateinit var gson:Gson

    constructor() : super()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        userPref = applicationContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        gson = Gson()

        if(userPref.getString(Constant.WEATHER_DETAILS,null)!=null){

            var weatherDetails = gson.fromJson(userPref.getString(Constant.WEATHER_DETAILS,null),WeatherResponse::class.java)
            loadContent(weatherDetails)
        }

        var retrofit:Retrofit = Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl(Constant.BASE_URL).build()

        var weatherInterface:WeatherInterface = retrofit.create(WeatherInterface::class.java)

        var weatherResponseCall: Call<WeatherResponse> = weatherInterface.getWeatherResponse(
                userPref.getFloat(Constant.LATITUDE,0f),userPref.getFloat(Constant.LONGITUDE,0f),
                "si")
        weatherResponseCall.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                if(response!=null){
                    var weatherDetails:String = gson.toJson(response.body())
                    var userPrefEditor:SharedPreferences.Editor = userPref.edit()
                    userPrefEditor.putString(Constant.WEATHER_DETAILS,weatherDetails)
                    userPrefEditor.apply()
                    loadContent(response.body())
                    Log.i("Location", weatherDetails)
                }

            }

            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                if(t!=null){
                    Log.i("Location",t.message)
                }

            }
        })


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }



    private fun loadContent(weatherDetails: WeatherResponse?) {


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
