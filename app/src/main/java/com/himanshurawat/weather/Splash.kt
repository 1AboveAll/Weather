package com.himanshurawat.weather

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.PermissionChecker
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.himanshurawat.weather.activity.MainActivity
import com.himanshurawat.weather.util.Constant

class Splash : AppCompatActivity() {

    private lateinit var userPref: SharedPreferences
    private lateinit var mFusedLocationProvider: FusedLocationProviderClient
    private lateinit var mLocationCallback: LocationCallback
    private lateinit var mLocationRequest: LocationRequest
    private var mLocationPermissionGranted:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userPref = applicationContext.getSharedPreferences(Constant.USER_PREF, Context.MODE_PRIVATE)
        mFusedLocationProvider = LocationServices.getFusedLocationProviderClient(this)

        //Location Callback
        mLocationCallback = object: LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                if(p0==null){
                    return
                }else{
                    getDeviceLocation()
                }
            }
        }

        //Location Request
        mLocationRequest = LocationRequest.create()
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        mLocationRequest.interval = Constant.SET_INTERVAL
        mLocationRequest.numUpdates = Constant.NUM_UPDATES

        getLocationPermission()
        getDeviceLocation()

    }

    private fun getLocationPermission() {

        var permission:Array<String> = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,android.Manifest.permission.ACCESS_COARSE_LOCATION)
        if(ContextCompat.checkSelfPermission(applicationContext,Constant.FINE_LOCATION)== PermissionChecker.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(applicationContext,Constant.COARSE_LOCATION)== PermissionChecker.PERMISSION_GRANTED){
                mLocationPermissionGranted = true
            }
        }else{
            ActivityCompat.requestPermissions(this,permission,Constant.LOCATION_REQUEST_CODE)
        }

    }

    private fun getDeviceLocation() {
        try{
            if(mLocationPermissionGranted){

                mFusedLocationProvider.lastLocation.addOnCompleteListener(this,{task: Task<Location> ->
                    if(task.isSuccessful){
                        var currentLocation: Location? = task.result
                        if(currentLocation!=null){
                            val userPrefEditor:SharedPreferences.Editor = userPref.edit()
                            userPrefEditor.putFloat(Constant.LATITUDE,currentLocation.latitude.toFloat())
                            userPrefEditor.putFloat(Constant.LONGITUDE,currentLocation.longitude.toFloat())
                            Log.i("Location","Latitude is ${currentLocation.latitude} and Longitude" +
                                    "${currentLocation.longitude}")
                            userPrefEditor.apply()

                            Handler().postDelayed({
                                val intent = Intent(this, MainActivity::class.java)
                                finish()
                                startActivity(intent)
                            },2000)

                        }
                        //Check Whether We Have Location in Preferences of Not
                        else if(userPref.getFloat(Constant.LATITUDE,0f)==0f||userPref.getFloat(Constant.LONGITUDE,0f)==0f){
                            //Request Location
                            mFusedLocationProvider.requestLocationUpdates(mLocationRequest,mLocationCallback,null)
                        }else{
                            Handler().postDelayed({
                                val intent = Intent(this, MainActivity::class.java)
                                finish()
                                startActivity(intent)
                                Toast.makeText(this,"Using Last Known Location", Toast.LENGTH_LONG).show()
                            },2000)
                        }
                    }
                })
            }
        }catch (e:SecurityException){
            Log.e("Error", "Security Exception ${e.message}")
        }


    }

}
