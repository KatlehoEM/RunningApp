package com.example.a2020102527_main_project_2.domain.usecase
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.core.data.repo.UserRepository
import com.example.a2020102527_main_project_2.core.tracking.TrackingManager
import com.example.a2020102527_main_project_2.domain.model.CurrentRunStateWithCalories
import com.example.a2020102527_main_project_2.utils.RunUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

/*
    The GetCurrentRunStateWithCaloriesUseCase class calculates the current run state with burnt calories.
     It combines user data and tracking manager's current run state to determine calories burnt
     based on distance and user weight. This use case encapsulates the logic for real-time
     calculation of calories burnt during a run, facilitating seamless integration into fitness
     tracking applications.
 */
@Singleton
class GetCurrentRunStateWithCaloriesUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val trackingManager: TrackingManager
) {
    operator fun invoke(): Flow<CurrentRunStateWithCalories> {
        return combine(userRepository.user, trackingManager.currentRunState) { user, runState ->
            CurrentRunStateWithCalories(
                currentRunState = runState,
                caloriesBurnt = RunUtils.calculateCaloriesBurnt(
                    distanceInMeters = runState.distanceInMeters,
                    weightInKg = user.weightInKg
                ).roundToInt()
            )
        }
    }
}