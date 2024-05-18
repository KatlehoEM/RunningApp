package com.example.a2020102527_main_project_2.core.tracking.model
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/

/*
    CurrentRunState is a data class representing the current state of a running activity.
    It includes properties such as distance covered in meters, speed in kilometers per hour,
     tracking status, and a list of path points indicating the route taken.
     This structure encapsulates essential data for real-time monitoring and visualization
     of a user's running session in applications.
 */
data class CurrentRunState(
    val distanceInMeters: Int = 0,
    val speedInKMH: Float = 0f,
    val isTracking: Boolean = false,
    val pathPoints: List<PathPoint> = emptyList()
)
