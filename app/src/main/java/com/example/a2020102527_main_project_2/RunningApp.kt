package com.example.a2020102527_main_project_2
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.app.Application
import com.example.a2020102527_main_project_2.core.tracking.notification.NotificationHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

/*
    The RunTrackApp class extends the Application class and is annotated with
    @HiltAndroidApp for dependency injection with Hilt. Upon creation,
    it initializes Timber for logging and injects a NotificationHelper instance to manage notifications.
     This setup ensures proper initialization of app-wide dependencies and facilitates
      modular development in the Run Tracking application.
 */
@HiltAndroidApp
class RunTrackApp : Application() {
    @Inject
    lateinit var notificationHelper: NotificationHelper
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        notificationHelper.createNotificationChannel()
    }
}
