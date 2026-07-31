package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EarnedProPassDao {

    @Insert(
        onConflict =
            OnConflictStrategy.IGNORE
    )
    suspend fun insertPass(
        pass: EarnedProPassEntity
    ): Long

    @Query(
        """
        SELECT *
        FROM earned_pro_passes
        WHERE revokedAtEpochMs IS NULL
        AND startsAtEpochMs <= :nowEpochMs
        AND expiresAtEpochMs > :nowEpochMs
        ORDER BY expiresAtEpochMs DESC, id DESC
        LIMIT 1
        """
    )
    fun observeActivePass(
        nowEpochMs: Long
    ): Flow<EarnedProPassEntity?>

    @Query(
        """
        SELECT *
        FROM earned_pro_passes
        WHERE revokedAtEpochMs IS NULL
        AND startsAtEpochMs <= :nowEpochMs
        AND expiresAtEpochMs > :nowEpochMs
        ORDER BY expiresAtEpochMs DESC, id DESC
        LIMIT 1
        """
    )
    suspend fun getActivePass(
        nowEpochMs: Long
    ): EarnedProPassEntity?

    @Query(
        """
        SELECT COALESCE(
            MAX(expiresAtEpochMs),
            :nowEpochMs
        )
        FROM earned_pro_passes
        WHERE revokedAtEpochMs IS NULL
        AND expiresAtEpochMs > :nowEpochMs
        """
    )
    suspend fun getLatestAvailableExpiry(
        nowEpochMs: Long
    ): Long

    @Query(
        """
        SELECT *
        FROM earned_pro_passes
        WHERE deduplicationKey = :deduplicationKey
        LIMIT 1
        """
    )
    suspend fun getPassByDeduplicationKey(
        deduplicationKey: String
    ): EarnedProPassEntity?

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM earned_pro_passes
            WHERE deduplicationKey = :deduplicationKey
        )
        """
    )
    suspend fun hasDeduplicationKey(
        deduplicationKey: String
    ): Boolean

    @Query(
        """
        SELECT *
        FROM earned_pro_passes
        ORDER BY createdAtEpochMs DESC, id DESC
        LIMIT :limit
        """
    )
    fun observeRecentPasses(
        limit: Int = 20
    ): Flow<List<EarnedProPassEntity>>

    @Query(
        """
        SELECT *
        FROM earned_pro_passes
        ORDER BY createdAtEpochMs DESC, id DESC
        LIMIT :limit
        """
    )
    suspend fun getRecentPasses(
        limit: Int = 20
    ): List<EarnedProPassEntity>

    @Query(
        """
        UPDATE earned_pro_passes
        SET revokedAtEpochMs = :revokedAtEpochMs
        WHERE id = :passId
        AND revokedAtEpochMs IS NULL
        """
    )
    suspend fun revokePass(
        passId: Long,
        revokedAtEpochMs: Long =
            System.currentTimeMillis()
    ): Int

    @Query(
        """
        DELETE FROM earned_pro_passes
        WHERE id = :passId
        """
    )
    suspend fun deletePass(
        passId: Long
    )
}