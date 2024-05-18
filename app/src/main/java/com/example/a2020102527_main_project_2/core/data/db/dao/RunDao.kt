package com.example.a2020102527_main_project_2.core.data.db.dao
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a2020102527_main_project_2.core.data.model.Run
import kotlinx.coroutines.flow.Flow
import java.util.Date

/*
    This interface defines database operations for managing running activities
    using the Room persistence library. It includes methods to:

    - Insert and delete run records.
    - Retrieve runs sorted by date, duration, calories burned, average speed,
      or distance, with support for pagination.
    - Get a limited number of recent runs.
    - Retrieve runs within a specific date range.
    - Calculate aggregate statistics such as total duration, total calories burned,
      total distance, and average speed within a date range, all returned as reactive Flow streams.
 */

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunSortByDate(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY durationInMilliSeconds DESC")
    fun getAllRunSortByDuration(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunSortByCaloriesBurned(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunSortByAvgSpeed(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunSortByDistance(): PagingSource<Int, Run>

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC LIMIT :limit")
    fun getRunByDescDateWithLimit(limit: Int): Flow<List<Run>>

    @Query(
        "SELECT * FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    suspend fun getRunStatsInDateRange(fromDate: Date?, toDate: Date?): List<Run>


    //for statistics
    @Query(
        "SELECT TOTAL(durationInMilliSeconds) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalRunningDuration(fromDate: Date?, toDate: Date?): Flow<Long>

    @Query(
        "SELECT TOTAL(caloriesBurned) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalCaloriesBurned(fromDate: Date?, toDate: Date?): Flow<Long>

    @Query(
        "SELECT TOTAL(distanceInMeters) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalDistance(fromDate: Date?, toDate: Date?): Flow<Long>

    @Query(
        "SELECT AVG(avgSpeedInKMH) FROM running_table WHERE " +
                "(:fromDate IS NULL OR timestamp >= :fromDate) AND " +
                "(:toDate IS NULL OR timestamp <= :toDate) " +
                "ORDER BY timestamp DESC"
    )
    fun getTotalAvgSpeed(fromDate: Date?, toDate: Date?): Flow<Float>

}