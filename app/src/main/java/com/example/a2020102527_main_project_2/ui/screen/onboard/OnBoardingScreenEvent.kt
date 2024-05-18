package com.example.a2020102527_main_project_2.ui.screen.onboard
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.core.data.model.Gender

/*
    The OnBoardingScreenEvent interface defines methods for updating user details during
    the onboarding process. It includes functions to update the user's name, gender,
     weight, and weekly goal distance.
     This interface serves as a contract for handling user input and updating relevant
     data within the onboarding screen or view model.
 */
interface OnBoardingScreenEvent {
    fun updateName(name: String)
    fun updateGender(gender: Gender)
    fun updateWeight(weightInKg: Float)
    fun updateWeeklyGoal(weeklyGoalInKm: Float)
}