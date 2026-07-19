package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingSessionDao {

    @Insert
    suspend fun insertSession(
        session: TrainingSessionEntity
    ): Long

    @Query(
        """
        SELECT COUNT(*)
        FROM training_session
        WHERE localDate = :localDate
        """
    )
    fun observeSessionCountForDate(
        localDate: String
    ): Flow<Int>

    @Query(
        """
        SELECT COALESCE(SUM(durationSeconds), 0)
        FROM training_session
        WHERE localDate = :localDate
        """
    )
    fun observeTotalDurationSecondsForDate(
        localDate: String
    ): Flow<Int>

    @Query(
        """
        SELECT *
        FROM training_session
        ORDER BY finishedAtEpochMs DESC
        LIMIT :limit
        """
    )
    fun observeRecentSessions(
        limit: Int = 20
    ): Flow<List<TrainingSessionEntity>>

    @Query(
        """
        SELECT *
        FROM training_session
        WHERE localDate = :localDate
        ORDER BY finishedAtEpochMs DESC
        """
    )
    fun observeSessionsForDate(
        localDate: String
    ): Flow<List<TrainingSessionEntity>>

    /**
     * Used by challenge validation immediately after
     * a training session is completed.
     */
    @Query(
        """
        SELECT *
        FROM training_session
        WHERE localDate = :localDate
        ORDER BY finishedAtEpochMs ASC
        """
    )
    suspend fun getSessionsForDate(
        localDate: String
    ): List<TrainingSessionEntity>

    /**
     * Used to rebuild challenge progress safely from
     * the user's recorded training history.
     *
     * Dates use ISO format: YYYY-MM-DD.
     */
    @Query(
        """
        SELECT *
        FROM training_session
        WHERE localDate BETWEEN :startDate AND :endDate
        ORDER BY localDate ASC, finishedAtEpochMs ASC
        """
    )
    suspend fun getSessionsBetweenDates(
        startDate: String,
        endDate: String
    ): List<TrainingSessionEntity>
}