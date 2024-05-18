package com.example.a2020102527_main_project_2.core.tracking
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.di.ApplicationScope
import com.example.a2020102527_main_project_2.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
    TimeTracker manages a timer in Android apps, tracking elapsed time in milliseconds.
    It provides functionality to start, resume, pause, and stop the timer, utilizing coroutines
    for asynchronous operations. This class abstracts timer logic, facilitating easy integration
     and management of time-tracking features in applications.
 */
class TimeTracker @Inject constructor(
    @ApplicationScope private val applicationScope: CoroutineScope,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
){
    private var timeElapsedInMilliSeconds = 0L
    private var isRunning = false
    private var callback: ((timeInMilliSeconds: Long) -> Unit)? = null
    private var job: Job? = null

    private fun start() {
        if(job != null)
            return
        System.currentTimeMillis()
        this.job = applicationScope.launch(defaultDispatcher){
            while(isRunning && isActive){
                callback?.invoke(timeElapsedInMilliSeconds)
                delay(1000)
                timeElapsedInMilliSeconds += 1000

            }
        }
    }

    fun startResumeTimer(callback: (timeInMilliSeconds: Long) -> Unit){
        if(isRunning)
            return
        this.callback = callback
        isRunning = true
        start()
    }

    fun stopTimer(){
        pauseTimer()
        timeElapsedInMilliSeconds = 0
    }

    fun pauseTimer(){
        isRunning = false
        job?.cancel()
        job = null
        callback = null
    }
}