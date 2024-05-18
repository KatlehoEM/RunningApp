package com.example.a2020102527_main_project_2.ui.screen.home
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.core.data.model.Run
import com.example.a2020102527_main_project_2.core.data.model.User
import com.example.a2020102527_main_project_2.domain.model.CurrentRunStateWithCalories
/*
    The HomeScreenState data class encapsulates the state of the home screen.
    It includes properties such as recent run list, current run state with calories,
     details of the current run being viewed, user information, and the distance covered by
     the user in the current week.
     This state is utilized by the HomeViewModel to manage and update the UI of the home screen efficiently.
 */
data class HomeScreenState(
    val runList: List<Run> = emptyList(),
    val currentRunStateWithCalories: CurrentRunStateWithCalories = CurrentRunStateWithCalories(),
    val currentRunInfo: Run? = null,
    val user: User = User(),
    val distanceCoveredInKmInThisWeek: Float = 0.0f
)