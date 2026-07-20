package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CornerstonePointsDao {

    /*
     * Returns the new row ID.
     *
     * Returns -1 when the unique deduplication key
     * already exists, preventing duplicate rewards
     * or duplicate redemptions.
     */
    @Insert(
        onConflict = OnConflictStrategy.IGNORE
    )
    suspend fun insertTransaction(
        transaction:
        CornerstonePointsTransactionEntity
    ): Long

    @Query(
        """
        SELECT COALESCE(
            SUM(amount),
            0
        )
        FROM cornerstone_points_transactions
        """
    )
    fun observePointsBalance(): Flow<Int>

    @Query(
        """
        SELECT COALESCE(
            SUM(amount),
            0
        )
        FROM cornerstone_points_transactions
        """
    )
    suspend fun getPointsBalance(): Int

    @Query(
        """
        SELECT *
        FROM cornerstone_points_transactions
        ORDER BY createdAtEpochMs DESC,
            id DESC
        LIMIT :limit
        """
    )
    fun observeRecentTransactions(
        limit: Int = 50
    ): Flow<List<CornerstonePointsTransactionEntity>>

    @Query(
        """
        SELECT *
        FROM cornerstone_points_transactions
        ORDER BY createdAtEpochMs DESC,
            id DESC
        LIMIT :limit
        """
    )
    suspend fun getRecentTransactions(
        limit: Int = 50
    ): List<CornerstonePointsTransactionEntity>

    @Query(
        """
        SELECT *
        FROM cornerstone_points_transactions
        WHERE deduplicationKey =
            :deduplicationKey
        LIMIT 1
        """
    )
    suspend fun getTransactionByDeduplicationKey(
        deduplicationKey: String
    ): CornerstonePointsTransactionEntity?

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM cornerstone_points_transactions
            WHERE deduplicationKey =
                :deduplicationKey
        )
        """
    )
    suspend fun hasDeduplicationKey(
        deduplicationKey: String
    ): Boolean

    @Query(
        """
        SELECT *
        FROM cornerstone_points_transactions
        WHERE sourceType = :sourceType
        AND sourceId = :sourceId
        ORDER BY createdAtEpochMs DESC,
            id DESC
        """
    )
    suspend fun getTransactionsForSource(
        sourceType: String,
        sourceId: String
    ): List<CornerstonePointsTransactionEntity>

    /*
     * Atomically verifies that enough points are
     * available before inserting a negative points
     * transaction.
     */
    @Transaction
    suspend fun insertRedemptionIfAffordable(
        transaction:
        CornerstonePointsTransactionEntity
    ): Boolean {
        if (transaction.amount >= 0) {
            return false
        }

        val cost =
            -transaction.amount

        val currentBalance =
            getPointsBalance()
                .coerceAtLeast(0)

        if (currentBalance < cost) {
            return false
        }

        val insertedId =
            insertTransaction(
                transaction = transaction
            )

        return insertedId > 0L
    }

    @Query(
        """
        DELETE FROM cornerstone_points_transactions
        WHERE id = :transactionId
        """
    )
    suspend fun deleteTransaction(
        transactionId: Long
    )
}