package com.example.a2020102527_main_project_2.domain.model
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.core.tracking.model.CurrentRunState
/*
    CurrentRunStateWithCalories is a data class representing the current state of a
    run along with the calculated calories burnt. It encapsulates information about the ongoing run,
     such as distance and duration, along with the corresponding calories burnt based on user weight.
      This structure simplifies the management and presentation of run-related data, aiding in the
       development of fitness tracking features in applications.

 */
data class CurrentRunStateWithCalories(
    val currentRunState: CurrentRunState = CurrentRunState(),
    val caloriesBurnt: Int = 0
)
