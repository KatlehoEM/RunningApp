package com.example.a2020102527_main_project_2.ui.screen.profile
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.core.data.model.User

/*
    ProfileScreenState encapsulates the state for the user profile screen,
     including total distance, duration, calories burnt, user data, edit mode status, and error message.
     It serves as a snapshot of the profile screen's current state, facilitating seamless management
      and rendering of profile-related information and interactions.
 */
data class ProfileScreenState(
    val totalDistanceInKm: Float = 0f,
    val totalDurationInHr: Float = 0f,
    val totalCaloriesBurnt: Long = 0,
    val user: User = User(),
    val isEditMode: Boolean = false,
    val errorMsg: String? = null
)
