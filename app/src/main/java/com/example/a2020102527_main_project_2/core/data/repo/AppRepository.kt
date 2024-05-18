package com.example.a2020102527_main_project_2.core.data.repo
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.a2020102527_main_project_2.core.data.db.dao.RunDao
import com.example.a2020102527_main_project_2.core.data.model.Run
import com.example.a2020102527_main_project_2.core.data.utils.RunSortOrder
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton
/*
    The AppRepository class acts as a bridge between the data source (Room database)
    and the rest of the application. It provides methods to interact with run data, including
     insertion, deletion, and retrieval operations. The getSortedAllRun function enables pagination
      and sorting of run data based on different criteria. Other functions calculate statistics such as
       total running duration, calories burned, distance, and average speed within specified date ranges,
        all returned as reactive flows for real-time updates in the UI. This repository pattern helps
        centralize data operations, promoting maintainability and testability in the application architecture.
 */
@Singleton
class AppRepository @Inject constructor(
    private val runDao: RunDao
) {
    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getSortedAllRun(sortingOrder: RunSortOrder) = Pager(
        config = PagingConfig(pageSize = 20),
    ) {
        when (sortingOrder) {
            RunSortOrder.DATE -> runDao.getAllRunSortByDate()
            RunSortOrder.DURATION -> runDao.getAllRunSortByDuration()
            RunSortOrder.CALORIES_BURNED -> runDao.getAllRunSortByCaloriesBurned()
            RunSortOrder.AVG_SPEED -> runDao.getAllRunSortByAvgSpeed()
            RunSortOrder.DISTANCE -> runDao.getAllRunSortByDistance()
        }
    }

    suspend fun getRunStatsInDateRange(fromDate: Date?, toDate: Date?) =
        runDao.getRunStatsInDateRange(fromDate, toDate)

    fun getRunByDescDateWithLimit(limit: Int) = runDao.getRunByDescDateWithLimit(limit)

    fun getTotalRunningDuration(fromDate: Date? = null, toDate: Date? = null): Flow<Long> =
        runDao.getTotalRunningDuration(fromDate, toDate)

    fun getTotalCaloriesBurned(fromDate: Date? = null, toDate: Date? = null): Flow<Long> =
        runDao.getTotalCaloriesBurned(fromDate, toDate)

    fun getTotalDistance(fromDate: Date? = null, toDate: Date? = null): Flow<Long> =
        runDao.getTotalDistance(fromDate, toDate)

    fun getTotalAvgSpeed(fromDate: Date? = null, toDate: Date? = null): Flow<Float> =
        runDao.getTotalAvgSpeed(fromDate, toDate)

}