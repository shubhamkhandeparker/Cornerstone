package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

data class FightGearProductAnalyticsSummary(
    val productId: String,
    val productName: String,
    val retailerName: String,
    val impressions: Long,
    val viewDealClicks: Long
) {
    val clickThroughRatePercent: Double
        get() {
            if (impressions <= 0L) {
                return 0.0
            }

            return viewDealClicks.toDouble() /
                    impressions.toDouble() * 100.0
        }
}

@Dao
interface FightGearAnalyticsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEvent(
        event: FightGearAnalyticsEventEntity
    )

    @Query(
        """
        SELECT *
        FROM fight_gear_analytics_events
        ORDER BY createdAtEpochMs DESC
        """
    )
    fun observeAllEvents():
            Flow<List<FightGearAnalyticsEventEntity>>

    @Query(
        """
        SELECT COUNT(*)
        FROM fight_gear_analytics_events
        WHERE eventType = :eventType
        """
    )
    fun observeEventCount(
        eventType: String
    ): Flow<Int>

    @Query(
        """
        SELECT COUNT(*)
        FROM fight_gear_analytics_events
        WHERE productId = :productId
        AND eventType = :eventType
        """
    )
    fun observeProductEventCount(
        productId: String,
        eventType: String
    ): Flow<Int>

    @Query(
        """
        SELECT
            productId AS productId,
            MAX(productName) AS productName,
            MAX(retailerName) AS retailerName,
            SUM(
                CASE
                    WHEN eventType = 'PRODUCT_IMPRESSION'
                    THEN 1
                    ELSE 0
                END
            ) AS impressions,
            SUM(
                CASE
                    WHEN eventType = 'VIEW_DEAL_CLICK'
                    THEN 1
                    ELSE 0
                END
            ) AS viewDealClicks
        FROM fight_gear_analytics_events
        GROUP BY productId
        ORDER BY
            viewDealClicks DESC,
            impressions DESC,
            productName COLLATE NOCASE ASC
        """
    )
    fun observeProductAnalytics():
            Flow<List<FightGearProductAnalyticsSummary>>

    @Query(
        """
        DELETE FROM fight_gear_analytics_events
        WHERE createdAtEpochMs < :cutoffEpochMs
        """
    )
    suspend fun deleteEventsOlderThan(
        cutoffEpochMs: Long
    )

    @Query(
        """
        DELETE FROM fight_gear_analytics_events
        """
    )
    suspend fun deleteAllEvents()
}