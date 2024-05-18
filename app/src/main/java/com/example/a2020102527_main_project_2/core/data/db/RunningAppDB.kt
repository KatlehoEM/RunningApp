package com.example.a2020102527_main_project_2.core.data.db
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.a2020102527_main_project_2.core.data.db.dao.RunDao
import com.example.a2020102527_main_project_2.core.data.db.mapper.DBConverters
import com.example.a2020102527_main_project_2.core.data.model.Run

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(DBConverters::class)
abstract class RunningAppDB : RoomDatabase() {
    companion object {
        const val RUNNING_APP_DB_NAME = "runningapp_db"
    }

    abstract fun getRunDao(): RunDao
}