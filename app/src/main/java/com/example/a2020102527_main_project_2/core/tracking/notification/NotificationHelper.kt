package com.example.a2020102527_main_project_2.core.tracking.notification
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.core.app.NotificationCompat

/*
    NotificationHelper interface abstracts notification management for a running application.
    It defines methods to create a notification channel, update a tracking notification
     with duration and tracking status, and remove a tracking notification.
     The baseNotificationBuilder property provides a base builder for notifications.
     This interface facilitates consistent and modular handling of notifications,
     enhancing user experience during running sessions.
 */
interface NotificationHelper {
    val baseNotificationBuilder: NotificationCompat.Builder

    fun createNotificationChannel()
    fun updateTrackingNotification(durationInMillis: Long, isTracking: Boolean)
    fun removeTrackingNotification()

    companion object {
        const val TRACKING_NOTIFICATION_ID = 3
    }
}