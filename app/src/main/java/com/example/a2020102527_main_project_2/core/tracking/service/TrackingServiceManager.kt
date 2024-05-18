package com.example.a2020102527_main_project_2.core.tracking.service
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/

/*
    TrackingServiceManager abstracts operations to start and stop a tracking service in Android applications.
     It provides methods startService() to initiate tracking service and stopService() to terminate it.
     This interface simplifies the management of tracking service lifecycle,
      promoting modular design and encapsulation.
 */
interface TrackingServiceManager {
    fun startService()
    fun stopService()
}