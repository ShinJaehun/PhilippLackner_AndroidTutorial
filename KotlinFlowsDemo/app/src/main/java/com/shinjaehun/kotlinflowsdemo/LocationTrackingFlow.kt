package com.shinjaehun.kotlinflowsdemo

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import android.Manifest
import androidx.core.app.ActivityCompat

fun observeLocation(context: Context): Flow<Location> {
//    if (!context.hasLocationPermission()) {
//        throw RuntimeException("No permission.")
//    }

    return callbackFlow {
        val client = LocationServices.getFusedLocationProviderClient(context)
        val request = LocationRequest.Builder(5000L)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()
        val callback = object: LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                println("Location callback triggered")
                result.locations.lastOrNull()?.let { location ->
                    trySend(location)
                }
            }
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            throw RuntimeException("No permission")
        }

        client.requestLocationUpdates(request, callback, context.mainLooper)

        awaitClose {
            client.removeLocationUpdates(callback)
        }
    }
}

//fun hasLocationPermission(context: Context): Boolean{
//    return ContextCompat.checkSelfPermission(
//        context,
//        ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//            &&
//            ContextCompat.checkSelfPermission(
//                context,
//                ACCESS_COARSE_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED
//}