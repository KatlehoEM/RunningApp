package com.example.a2020102527_main_project_2.ui.screen.home
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2020102527_main_project_2.core.data.model.Run
import com.example.a2020102527_main_project_2.core.data.model.User
import com.example.a2020102527_main_project_2.core.data.repo.AppRepository
import com.example.a2020102527_main_project_2.core.data.repo.UserRepository
import com.example.a2020102527_main_project_2.core.tracking.TrackingManager
import com.example.a2020102527_main_project_2.di.ApplicationScope
import com.example.a2020102527_main_project_2.di.IoDispatcher
import com.example.a2020102527_main_project_2.domain.model.CurrentRunStateWithCalories
import com.example.a2020102527_main_project_2.domain.usecase.GetCurrentRunStateWithCaloriesUseCase
import com.example.a2020102527_main_project_2.utils.setDateToWeekFirstDay
import com.example.a2020102527_main_project_2.utils.setDateToWeekLastDay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
/*
    The HomeViewModel manages the home screen's data and logic.
    It retrieves data like the user's existence, recent runs, current run state, and user information.
     Additionally, it calculates the distance covered by the user in the current week.
     This ViewModel also handles operations like deleting runs and managing the state for
      displaying or dismissing the details of a specific run on the home screen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository,
    trackingManager: TrackingManager,
    @ApplicationScope
    private val externalScope: CoroutineScope,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    userRepository: UserRepository,
    getCurrentRunStateWithCaloriesUseCase: GetCurrentRunStateWithCaloriesUseCase
) : ViewModel() {

    val durationInMillis = trackingManager.trackingDurationInMs

    val doesUserExist = userRepository.doesUserExist
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            null
        )

    private val calendar = Calendar.getInstance()

    private val distanceCoveredInThisWeekInMeter = repository.getTotalDistance(
        calendar.setDateToWeekFirstDay().time,
        calendar.setDateToWeekLastDay().time
    )

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = combine(
        repository.getRunByDescDateWithLimit(3),
        getCurrentRunStateWithCaloriesUseCase(),
        userRepository.user,
        distanceCoveredInThisWeekInMeter,
        _homeScreenState,
    ) { runList: List<Run>, runState:CurrentRunStateWithCalories, user: User, distanceInMeter:Long, state: HomeScreenState ->
        state.copy(
            runList = runList,
            currentRunStateWithCalories = runState,
            user = user,
            distanceCoveredInKmInThisWeek = distanceInMeter / 1000f
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        HomeScreenState()
    )

    fun deleteRun(run: Run) = externalScope.launch(ioDispatcher) {
        dismissRunDialog()
        repository.deleteRun(run)
    }

    fun showRun(run: Run) {
        _homeScreenState.update { it.copy(currentRunInfo = run) }
    }

    fun dismissRunDialog() {
        _homeScreenState.update { it.copy(currentRunInfo = null) }
    }

}