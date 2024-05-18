package com.example.a2020102527_main_project_2.core.tracking.service
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.content.Context
import android.content.Intent
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/*
    DefaultTrackingServiceManager starts and stops a tracking service in a running application.
    It utilizes foreground service functionality for compatibility with newer Android versions,
    ensuring uninterrupted tracking. This class abstracts the management of tracking service
    operations, promoting modularity and encapsulation in the application architecture.
 */
class DefaultTrackingServiceManager @Inject constructor(
    @ApplicationContext private val context: Context
) : TrackingServiceManager {

    override fun startService() {
        Intent(context, TrackingService::class.java).apply {
            action = TrackingService.ACTION_START_SERVICE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(this)
            } else context.startService(this)
        }
    }

    override fun stopService() {
        Intent(context, TrackingService::class.java).apply {
            context.stopService(this)
        }
    }
}