package com.himanshurawat.weather.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.himanshurawat.weather.R
import com.himanshurawat.weather.util.Constant

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userPref: SharedPreferences
    private lateinit var weatherImageView: ImageView
    private lateinit var weatherInfoTextView: TextView
    private lateinit var lastUpdatedTextView: TextView
    private lateinit var cityTextView: TextView
    private lateinit var countryTextView: TextView
    private lateinit var tempTextView: TextView
    private lateinit var feelsLikeTextView: TextView
    private lateinit var visibilityTextView: TextView
    private lateinit var humidityTextView: TextView
    private lateinit var uvIndexTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        userPref = applicationContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)


        var weatherDetails:String? = userPref.getString(Constant.WEATHER_DETAILS,null)
        if(weatherDetails!=null){

        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
