package com.example.a2020102527_main_project_2.ui.screen.runninghistory
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.a2020102527_main_project_2.core.data.model.Run
import com.example.a2020102527_main_project_2.core.data.repo.AppRepository
import com.example.a2020102527_main_project_2.core.data.utils.RunSortOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
    The RunningHistoryVM ViewModel manages the state and interactions for the running history screen.
    It provides methods to set the sorting order of the runs, handle dialog display for individual runs,
     and delete selected runs. The runList flow emits a sorted list of runs based on the selected
     sorting order.
 */
@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class RunningHistoryVM @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _runSortOrder = MutableStateFlow(RunSortOrder.DATE)

    val runList = _runSortOrder.flatMapLatest {
        repository.getSortedAllRun(it)
            .flow
            .cachedIn(viewModelScope)
    }

    private val _dialogRun = MutableStateFlow<Run?>(null)
    val dialogRun = _dialogRun.asStateFlow()

    fun setSortOrder(sortOrder: RunSortOrder) {
        _runSortOrder.value = sortOrder
    }

    fun setDialogRun(run: Run?) {
        _dialogRun.value = run
    }

    fun deleteRun() = dialogRun.value?.let {
        viewModelScope.launch {
            _dialogRun.value = null
            repository.deleteRun(it)
        }
    }
}