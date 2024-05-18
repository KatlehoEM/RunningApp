package com.example.a2020102527_main_project_2.core.tracking.notification
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import com.example.a2020102527_main_project_2.ui.MainActivity
import com.example.a2020102527_main_project_2.R
import com.example.a2020102527_main_project_2.core.tracking.notification.NotificationHelper.Companion.TRACKING_NOTIFICATION_ID
import com.example.a2020102527_main_project_2.core.tracking.service.TrackingService
import com.example.a2020102527_main_project_2.ui.nav.Destination
import com.example.a2020102527_main_project_2.utils.RunUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/*
    DefaultNotificationHelper manages notifications for a running application.
    It creates and updates notifications displaying running duration, allowing pausing
    and resuming tracking directly from the notification.
    It also creates a notification channel for tracking status updates.
    This class enhances user experience by providing real-time feedback
    and control over running sessions via notifications.
 */
class DefaultNotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationHelper {

    companion object {
        private const val TRACKING_NOTIFICATION_CHANNEL_ID = "tracking_notification"
        private const val TRACKING_NOTIFICATION_CHANNEL_NAME = "Run Tracking Status"
    }

    private val intentToRunScreen = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(
            Intent(
                Intent.ACTION_VIEW,
                Destination.CurrentRun.currentRunUriPattern.toUri(),
                context,
                MainActivity::class.java
            )
        )
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)!!
    }

    override val baseNotificationBuilder
        get() = NotificationCompat.Builder(
            context,
            TRACKING_NOTIFICATION_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.running_boy)
            .setAutoCancel(false)
            .setOngoing(true)
            .setContentTitle("Running Time")
            .setContentText("00:00:00")
            .setContentIntent(intentToRunScreen)

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun updateTrackingNotification(durationInMillis: Long, isTracking: Boolean) {
        val notification = baseNotificationBuilder
            .setContentText(RunUtils.getFormattedStopwatchTime(durationInMillis))
            .clearActions()
            .addAction(getTrackingNotificationAction(isTracking))
            .build()

        notificationManager.notify(TRACKING_NOTIFICATION_ID, notification)
    }

    private fun getTrackingNotificationAction(isTracking: Boolean): NotificationCompat.Action {
        return NotificationCompat.Action(
            if (isTracking) R.drawable.ic_pause else R.drawable.ic_play,
            if (isTracking) "Pause" else "Resume",
            PendingIntent.getService(
                context,
                2234,
                Intent(
                    context,
                    TrackingService::class.java
                ).apply {
                    action =
                        if (isTracking) TrackingService.ACTION_PAUSE_TRACKING else TrackingService.ACTION_RESUME_TRACKING
                },
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
    }

    override fun removeTrackingNotification() {
        notificationManager.cancel(TRACKING_NOTIFICATION_ID)
    }

    override fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
            return

        val notificationChannel = NotificationChannel(
            TRACKING_NOTIFICATION_CHANNEL_ID,
            TRACKING_NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(notificationChannel)
    }
}