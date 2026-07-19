package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fight_gear_analytics_events",
    indices = [
        Index(value = ["eventType"]),
        Index(value = ["productId"]),
        Index(value = ["createdAtEpochMs"])
    ]
)
data class FightGearAnalyticsEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val eventType: String,

    val productId: String,

    val productName: String,

    val category: String,

    val retailerName: String,

    val isAffiliateLink: Boolean,

    val countryCode: String,

    val createdAtEpochMs: Long =
        System.currentTimeMillis()
)

object FightGearAnalyticsEventType {

    const val PRODUCT_IMPRESSION =
        "PRODUCT_IMPRESSION"

    const val VIEW_DEAL_CLICK =
        "VIEW_DEAL_CLICK"
}