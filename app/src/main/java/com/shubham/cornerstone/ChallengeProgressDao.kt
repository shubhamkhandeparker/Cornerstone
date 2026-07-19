package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeProgressDao {

    @Query(
        """
        SELECT *
        FROM challenge_progress
        ORDER BY updatedAtEpochMs DESC
        """
    )
    fun observeAllProgress():
            Flow<List<ChallengeProgressEntity>>

    @Query(
        """
        SELECT *
        FROM challenge_progress
        WHERE challengeId = :challengeId
        LIMIT 1
        """
    )
    fun observeProgress(
        challengeId: String
    ): Flow<ChallengeProgressEntity?>

    @Query(
        """
        SELECT *
        FROM challenge_progress
        WHERE challengeId = :challengeId
        LIMIT 1
        """
    )
    suspend fun getProgress(
        challengeId: String
    ): ChallengeProgressEntity?

    @Query(
        """
        SELECT *
        FROM challenge_progress
        WHERE status = 'ACTIVE'
        ORDER BY startedAtEpochDay ASC
        """
    )
    fun observeActiveChallenges():
            Flow<List<ChallengeProgressEntity>>

    /**
     * Used by the challenge progress engine immediately
     * after a completed training session is saved.
     */
    @Query(
        """
        SELECT *
        FROM challenge_progress
        WHERE status = 'ACTIVE'
        ORDER BY startedAtEpochDay ASC
        """
    )
    suspend fun getActiveChallenges():
            List<ChallengeProgressEntity>

    @Query(
        """
        SELECT *
        FROM challenge_progress
        WHERE status = 'COMPLETED'
        ORDER BY completedAtEpochDay DESC
        """
    )
    fun observeCompletedChallenges():
            Flow<List<ChallengeProgressEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun saveProgress(
        progress: ChallengeProgressEntity
    )

    @Query(
        """
        UPDATE challenge_progress
        SET
            status = :status,
            updatedAtEpochMs = :updatedAtEpochMs
        WHERE challengeId = :challengeId
        """
    )
    suspend fun updateStatus(
        challengeId: String,
        status: String,
        updatedAtEpochMs: Long =
            System.currentTimeMillis()
    )

    @Query(
        """
        UPDATE challenge_progress
        SET
            rewardClaimed = 1,
            updatedAtEpochMs = :updatedAtEpochMs
        WHERE challengeId = :challengeId
        """
    )
    suspend fun markRewardClaimed(
        challengeId: String,
        updatedAtEpochMs: Long =
            System.currentTimeMillis()
    )

    @Query(
        """
        DELETE FROM challenge_progress
        WHERE challengeId = :challengeId
        """
    )
    suspend fun deleteProgress(
        challengeId: String
    )
}