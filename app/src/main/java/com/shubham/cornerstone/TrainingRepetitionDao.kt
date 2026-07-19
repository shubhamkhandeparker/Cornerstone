package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingRepetitionDao {

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertRepetition(
        repetition:
        TrainingRepetitionEntity
    ): Long

    @Query(
        """
        SELECT *
        FROM training_repetitions
        WHERE localDate = :localDate
        ORDER BY recordedAtEpochMs DESC
        """
    )
    fun observeRepetitionsForDate(
        localDate: String
    ): Flow<List<TrainingRepetitionEntity>>

    @Query(
        """
        SELECT *
        FROM training_repetitions
        WHERE localDate = :localDate
        ORDER BY recordedAtEpochMs DESC
        """
    )
    suspend fun getRepetitionsForDate(
        localDate: String
    ): List<TrainingRepetitionEntity>

    @Query(
        """
        SELECT *
        FROM training_repetitions
        WHERE localDate BETWEEN
            :startDate AND :endDate
        ORDER BY localDate ASC,
            recordedAtEpochMs ASC
        """
    )
    suspend fun getRepetitionsBetweenDates(
        startDate: String,
        endDate: String
    ): List<TrainingRepetitionEntity>

    @Query(
        """
        SELECT COALESCE(
            SUM(repetitionCount),
            0
        )
        FROM training_repetitions
        WHERE localDate = :localDate
        AND movementType = :movementType
        AND integrityStatus = :integrityStatus
        """
    )
    fun observeTotalValidRepetitionsForDate(
        localDate: String,
        movementType: String,
        integrityStatus: String =
            ChallengeIntegrityStatus
                .VALID
                .name
    ): Flow<Int>

    @Query(
        """
        SELECT COALESCE(
            SUM(repetitionCount),
            0
        )
        FROM training_repetitions
        WHERE localDate = :localDate
        AND movementType = :movementType
        AND integrityStatus = :integrityStatus
        """
    )
    suspend fun getTotalValidRepetitionsForDate(
        localDate: String,
        movementType: String,
        integrityStatus: String =
            ChallengeIntegrityStatus
                .VALID
                .name
    ): Int

    @Query(
        """
        SELECT COALESCE(
            SUM(repetitionCount),
            0
        )
        FROM training_repetitions
        WHERE localDate BETWEEN
            :startDate AND :endDate
        AND movementType = :movementType
        AND integrityStatus = :integrityStatus
        """
    )
    suspend fun getTotalValidRepetitionsBetweenDates(
        startDate: String,
        endDate: String,
        movementType: String,
        integrityStatus: String =
            ChallengeIntegrityStatus
                .VALID
                .name
    ): Int

    @Query(
        """
        DELETE FROM training_repetitions
        WHERE trainingSessionId =
            :trainingSessionId
        """
    )
    suspend fun deleteForTrainingSession(
        trainingSessionId: Long
    )

    @Query(
        """
        DELETE FROM training_repetitions
        WHERE id = :repetitionId
        """
    )
    suspend fun deleteRepetition(
        repetitionId: Long
    )
}