package com.example.a2020102527_main_project_2.core.tracking.location
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.app.Activity
import android.content.IntentSender
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient

/*
    LocationUtils provides Android location utilities:
    - Request Builder: Generates accurate location requests with set intervals.
    - Enablement Check: Prompts users to enable location settings for accurate tracking, enhancing location-related functionality in apps.
 */
object LocationUtils {
    private const val LOCATION_UPDATE_INTERVAL = 5000L

    val locationRequestBuilder
        get() = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            LOCATION_UPDATE_INTERVAL
        )

    const val LOCATION_ENABLE_REQUEST_CODE = 5234

    fun checkAndRequestLocationSetting(activity: Activity){
        val locationRequest = locationRequestBuilder.build()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        val client: SettingsClient = LocationServices.getSettingsClient(activity)

        client.checkLocationSettings(builder.build())
            .addOnFailureListener{ exception ->
                if(exception is ResolvableApiException){
                    try{
                        exception.startResolutionForResult(
                            activity,
                            LOCATION_ENABLE_REQUEST_CODE
                        )
                    } catch(_: IntentSender.SendIntentException){

                    }
                }
            }
    }
}