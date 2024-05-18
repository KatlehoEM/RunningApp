package com.example.a2020102527_main_project_2.core.data.db.mapper
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.Date
/*
    This object defines type converters for the Room persistence library
    to handle complex data types:

    - Bitmap Converters are used to convert ByteArray to Bitmap and vice versa
     for storing and retrieving images in the database.

    - Date Converters are used to convert Long values (time in milliseconds)
     to Date objects and vice versa for storing and retrieving dates in the database.
 */
object DBConverters {
    @TypeConverter
    fun fromByteArrayToBitmap(bytes: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)
    }

    @TypeConverter
    fun fromBitmapToByteArray(bitmap: Bitmap): ByteArray{
        return ByteArrayOutputStream().use {
            bitmap.compress(Bitmap.CompressFormat.PNG,100,it)
            return@use it.toByteArray()
        }
    }

    @TypeConverter
    fun fromTimeInMilliSecondsToDate(timeInMilliSeconds: Long): Date{
        return Date(timeInMilliSeconds)
    }

    @TypeConverter
    fun fromDateToTimeInMilliSeconds(date: Date?): Long? {
        return date?.time
    }
}