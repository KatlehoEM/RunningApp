package com.example.a2020102527_main_project_2.ui.screen.runstats.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import com.example.a2020102527_main_project_2.core.data.model.Run
import com.example.a2020102527_main_project_2.ui.screen.runstats.RunStatsUiState
import java.util.Date

/*
    The RunStatsAccumulator object provides a method accumulateRunByDate to accumulate run statistics
    by date from a list of run data.
    It iterates through the list of runs, accumulating statistics like distance, duration, and
    calories burned into a map keyed by date.
 */
object RunStatsAccumulator {
    fun accumulateRunByDate(
        list: List<Run>
    ): Map<Date, RunStatsUiState.AccumulatedRunStatisticsOnDate> {
        return buildMap {
            list.forEach { run ->
                val newStats = RunStatsUiState.AccumulatedRunStatisticsOnDate.fromRun(run)
                this[newStats.date] = newStats + this[newStats.date]
            }
        }
    }

}