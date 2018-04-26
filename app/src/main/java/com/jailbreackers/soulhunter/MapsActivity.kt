package com.jailbreackers.soulhunter

import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        checkPermision()
        locationOfCoin()
    }
    val accessLocation=123
    fun checkPermision(){


        if(Build.VERSION.SDK_INT>=23){
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),accessLocation)
            }
        }

        getUserLoaction()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       when(requestCode){
           accessLocation->{
               if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   getUserLoaction()
               }
               else {
                   Toast.makeText(this,"Location permision is denied",Toast.LENGTH_LONG).show()

               }
           }


       }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun getUserLoaction(){
        Toast.makeText(this,"Location is accessed",Toast.LENGTH_LONG).show()

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
                MarkerOptions()
                        .position(sydney)
                        .title("Mohamad")
                        .snippet("Get the souls")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.vampire_icon))

        )
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f ))
    }


    // calculate coordinates for coin
    fun locationOfCoin(): Location{

        // fake data
        val myLocation:Location = Location("me")
        myLocation.latitude = 0.0
        myLocation.longitude = 0.0



        //core of the function
        var latitude: Double = myLocation.latitude
        var longitude: Double = myLocation.longitude

        val latitudeRange2km: Double = 0.0076
        val longituteRange2km: Double = 0.0182


        val random = Random()
        val randomLatitude: Double = random.nextDouble() * latitudeRange2km
        val randomLongitude: Double = random.nextDouble() * longituteRange2km

        if(random.nextInt(1) == 0){
            latitude += randomLatitude
            Log.v("latitute", "0")
        } else{
            latitude -= randomLatitude
            Log.v("latitute", "1")
        }

        if(random.nextInt(1) == 0){
            longitude += randomLongitude
            Log.v("longitute", "0")
        }else{
            longitude -= randomLongitude
            Log.v("longitute", "1")

        }





        val locationOfCoin: Location  = Location("coin")
        locationOfCoin.latitude = latitude
        locationOfCoin.longitude = longitude



        return locationOfCoin
    }


}

