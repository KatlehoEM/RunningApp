package com.example.a2020102527_main_project_2.core.tracking.service
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.a2020102527_main_project_2.core.tracking.TrackingManager
import com.example.a2020102527_main_project_2.core.tracking.notification.NotificationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

/*
    TrackingService is a foreground service managing running tracking operations in Android.
    It responds to actions like pausing, resuming tracking, and starting the service.
    It integrates with TrackingManager for tracking logic and NotificationHelper for notification updates.
    The service runs in the foreground to ensure uninterrupted tracking and updates notifications based
    on tracking state.
 */
@AndroidEntryPoint
class TrackingService : LifecycleService() {

    companion object {
        const val ACTION_PAUSE_TRACKING = "action_pause_tracking"
        const val ACTION_RESUME_TRACKING = "action_resume_tracking"
        const val ACTION_START_SERVICE = "action_start_service"
    }

    @Inject
    lateinit var trackingManager: TrackingManager

    @Inject
    lateinit var notificationHelper: NotificationHelper
    var job: Job? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        when (intent?.action) {
            ACTION_PAUSE_TRACKING -> trackingManager.pauseTracking()
            ACTION_RESUME_TRACKING -> trackingManager.startResumeTracking()
            ACTION_START_SERVICE -> {
                startForeground(
                    NotificationHelper.TRACKING_NOTIFICATION_ID,
                    notificationHelper.baseNotificationBuilder.build()
                )

                if (job == null)
                    job = combine(
                        trackingManager.trackingDurationInMs,
                        trackingManager.currentRunState
                    ) { duration, currentRunState ->
                        notificationHelper.updateTrackingNotification(
                            durationInMillis = duration,
                            isTracking = currentRunState.isTracking
                        )
                    }.launchIn(lifecycleScope)
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationHelper.removeTrackingNotification()

        job?.cancel()
        job = null
    }
}