package com.example.a2020102527_main_project_2.core.tracking.location
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.example.a2020102527_main_project_2.utils.RunUtils.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import dagger.hilt.android.qualifiers.ApplicationContext

/*
    The DefaultLocationTrackingManager utilizes the Fused Location Provider in Android to
    manage location tracking. It registers a callback to receive location updates if the app
    has permissions, and removes the callback when necessary.
    This class encapsulates location tracking logic, ensuring modular and maintainable code
     for location-related functionalities in the application.
 */
@SuppressLint("MissingPermission")
class DefaultLocationTrackingManager constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    @ApplicationContext private val context: Context,
    private val locationRequest: LocationRequest
) : LocationTrackingManager {

    override fun registerCallback(locationCallback: LocationCallback) {
        if (context.hasLocationPermission()) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    override fun unRegisterCallback(locationCallback: LocationCallback) {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

}