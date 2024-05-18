package com.example.a2020102527_main_project_2.ui.screen.main
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2020102527_main_project_2.core.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
/*
    This ViewModel class checks if a user exists in the app by observing a state flow from the user repository.
    It initializes a boolean state representing whether a user exists, utilizing stateIn to collect
    values from the flow within the ViewModel's lifecycle.
 */
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
    val doesUserExist = userRepository.doesUserExist
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            null
        )
}