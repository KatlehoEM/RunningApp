package com.example.a2020102527_main_project_2.ui.screen.runstats
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.compose.runtime.Immutable
import com.example.a2020102527_main_project_2.core.data.model.Run
import com.example.a2020102527_main_project_2.utils.setDateToWeekFirstDay
import com.example.a2020102527_main_project_2.utils.setDateToWeekLastDay
import com.example.a2020102527_main_project_2.utils.setMinimumTime
import com.example.a2020102527_main_project_2.utils.toCalendar
import java.util.Calendar
import java.util.Date

/*
    The RunStatsUiState represents the UI state for the run statistics screen,
     including the date range, accumulated run statistics, and the selected statistic to display.
     It offers methods for creating an initial empty state and accumulating statistics from run data.
 */
@Immutable
data class RunStatsUiState(
    val dateRange: ClosedRange<Date>,
    val runStats: List<Run>,
    val statisticToShow: Statistic,
    val runStatisticsOnDate: Map<Date, AccumulatedRunStatisticsOnDate>,
) {

    data class AccumulatedRunStatisticsOnDate(
        val date: Date = Date(),
        val distanceInMeters: Int = 0,
        val durationInMillis: Long = 0L,
        val caloriesBurned: Int = 0
    ) {
        operator fun plus(other: AccumulatedRunStatisticsOnDate?) = other?.let {
            AccumulatedRunStatisticsOnDate(
                date = this.date,
                distanceInMeters = this.distanceInMeters + other.distanceInMeters,
                durationInMillis = this.durationInMillis + other.durationInMillis,
                caloriesBurned = this.caloriesBurned + other.caloriesBurned
            )
        } ?: this

        companion object {
            fun fromRun(run: Run) = AccumulatedRunStatisticsOnDate(
                date = run.timestamp.toCalendar().setMinimumTime().time,
                distanceInMeters = run.distanceInMeters,
                durationInMillis = run.durationInMilliSeconds,
                caloriesBurned = run.caloriesBurned
            )
        }
    }

    enum class Statistic {
        CALORIES,
        DURATION,
        DISTANCE
    }

    companion object {
        val EMPTY_STATE
            get() = RunStatsUiState(
                dateRange = Calendar.getInstance().setDateToWeekFirstDay().time..
                        Calendar.getInstance().setDateToWeekLastDay().time,
                runStats = emptyList(),
                statisticToShow = Statistic.DISTANCE,
                runStatisticsOnDate = emptyMap()
            )
    }
}

