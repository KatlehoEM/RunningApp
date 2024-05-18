package com.example.a2020102527_main_project_2.core.tracking.location
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.google.android.gms.location.LocationCallback

/*
    The LocationTrackingManager interface defines methods for registering and
    unregistering location callbacks. Implementations of this interface handle location
    tracking functionality, facilitating modular and maintainable code for location-related
    operations in the application.
 */
interface LocationTrackingManager {

    fun registerCallback(locationCallback: LocationCallback)
    fun unRegisterCallback(locationCallback: LocationCallback)

}