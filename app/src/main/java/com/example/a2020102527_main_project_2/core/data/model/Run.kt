package com.example.a2020102527_main_project_2.core.data.model

/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
/*
    This is an entity class that will be used to store
    the information for the users with each run that
    they make on the app
 */
@Entity(tableName = "running_table")
data class Run(
    var img: Bitmap,
    var timestamp: Date = Date(),
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var durationInMilliSeconds: Long = 0L,
    var caloriesBurned: Int = 0,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

    )
