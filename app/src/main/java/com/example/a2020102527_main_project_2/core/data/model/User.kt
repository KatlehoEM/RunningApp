package com.example.a2020102527_main_project_2.core.data.model
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.net.Uri

/*
    This is the class for the information for the User
 */
data class User(
    val name: String = "",
    val gender: Gender = Gender.FEMALE,
    val weightInKg: Float = 0.0f,
    val weeklyGoalInKM: Float = 0.0f,
    val imgUri: Uri? = null
)
