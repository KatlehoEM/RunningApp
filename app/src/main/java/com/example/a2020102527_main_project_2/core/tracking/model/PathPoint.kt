package com.example.a2020102527_main_project_2.core.tracking.model
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.google.android.gms.maps.model.LatLng
/*
    The PathPoint sealed interface defines two types of points:
    LocationPoint, representing a geographical location with latitude and longitude coordinates,
    and EmptyLocationPoint, indicating a placeholder or absence of location data.
    This structure is useful for modeling paths or routes in applications, allowing
    differentiation between valid location points and placeholders
 */
sealed interface PathPoint {
    class LocationPoint(val latLng: LatLng): PathPoint
    object EmptyLocationPoint: PathPoint
}