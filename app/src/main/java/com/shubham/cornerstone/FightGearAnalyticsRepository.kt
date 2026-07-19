package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit

class FightGearAnalyticsRepository(
    private val dao: FightGearAnalyticsDao
) {

    fun observeAllEvents():
            Flow<List<FightGearAnalyticsEventEntity>> {

        return dao.observeAllEvents()
    }

    fun observeProductAnalytics():
            Flow<List<FightGearProductAnalyticsSummary>> {

        return dao.observeProductAnalytics()
    }

    fun observeImpressionCount(): Flow<Int> {
        return dao.observeEventCount(
            eventType =
                FightGearAnalyticsEventType.PRODUCT_IMPRESSION
        )
    }

    fun observeViewDealClickCount(): Flow<Int> {
        return dao.observeEventCount(
            eventType =
                FightGearAnalyticsEventType.VIEW_DEAL_CLICK
        )
    }

    fun observeProductImpressionCount(
        productId: String
    ): Flow<Int> {
        return dao.observeProductEventCount(
            productId = productId,
            eventType =
                FightGearAnalyticsEventType.PRODUCT_IMPRESSION
        )
    }

    fun observeProductViewDealClickCount(
        productId: String
    ): Flow<Int> {
        return dao.observeProductEventCount(
            productId = productId,
            eventType =
                FightGearAnalyticsEventType.VIEW_DEAL_CLICK
        )
    }

    suspend fun logProductImpression(
        product: FightGearProduct
    ) {
        insertEvent(
            eventType =
                FightGearAnalyticsEventType.PRODUCT_IMPRESSION,
            product = product
        )
    }

    suspend fun logViewDealClick(
        product: FightGearProduct
    ) {
        insertEvent(
            eventType =
                FightGearAnalyticsEventType.VIEW_DEAL_CLICK,
            product = product
        )
    }

    suspend fun deleteEventsOlderThan90Days() {
        val retentionDurationMs =
            TimeUnit.DAYS.toMillis(90)

        val cutoffEpochMs =
            System.currentTimeMillis() - retentionDurationMs

        dao.deleteEventsOlderThan(
            cutoffEpochMs = cutoffEpochMs
        )
    }

    suspend fun deleteAllEvents() {
        dao.deleteAllEvents()
    }

    private suspend fun insertEvent(
        eventType: String,
        product: FightGearProduct
    ) {
        dao.insertEvent(
            FightGearAnalyticsEventEntity(
                eventType = eventType,
                productId = product.id,
                productName = product.name,
                category = product.category.name,
                retailerName = product.retailerName,
                isAffiliateLink =
                    product.isAffiliateLink,
                countryCode = product.countryCode
            )
        )
    }
}