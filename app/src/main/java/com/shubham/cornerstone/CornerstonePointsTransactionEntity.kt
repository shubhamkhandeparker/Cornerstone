package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

enum class CornerstonePointsTransactionType {
    CHALLENGE_REWARD,
    PRO_PASS_REDEMPTION,
    MANUAL_ADJUSTMENT
}

enum class CornerstonePointsSourceType {
    CHALLENGE,
    REDEMPTION,
    SYSTEM
}

@Entity(
    tableName = "cornerstone_points_transactions",
    indices = [
        Index(
            value = ["transactionType"]
        ),
        Index(
            value = ["sourceType"]
        ),
        Index(
            value = ["sourceId"]
        ),
        Index(
            value = ["createdAtEpochMs"]
        ),
        Index(
            value = ["deduplicationKey"],
            unique = true
        )
    ]
)
data class CornerstonePointsTransactionEntity(

    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long = 0L,

    /*
     * Positive values add points.
     * Negative values spend points.
     */
    val amount: Int,

    val transactionType: String,

    val sourceType: String,

    /*
     * Examples:
     * one_hundred_kicks
     * foundation_14
     * pro_pass_7_days
     */
    val sourceId: String? = null,

    /*
     * Prevents the same reward or redemption from
     * being recorded more than once.
     *
     * Example:
     * challenge_reward:one_hundred_kicks
     */
    val deduplicationKey: String? = null,

    val description: String,

    val createdAtEpochMs: Long =
        System.currentTimeMillis()
) {

    fun resolvedTransactionType():
            CornerstonePointsTransactionType {

        return runCatching {
            CornerstonePointsTransactionType
                .valueOf(transactionType)
        }.getOrDefault(
            CornerstonePointsTransactionType
                .MANUAL_ADJUSTMENT
        )
    }

    fun resolvedSourceType():
            CornerstonePointsSourceType {

        return runCatching {
            CornerstonePointsSourceType
                .valueOf(sourceType)
        }.getOrDefault(
            CornerstonePointsSourceType
                .SYSTEM
        )
    }

    companion object {

        fun challengeReward(
            challengeId: String,
            challengeTitle: String,
            points: Int
        ): CornerstonePointsTransactionEntity {
            val safeChallengeId =
                challengeId
                    .trim()
                    .ifBlank {
                        "unknown_challenge"
                    }

            val safeChallengeTitle =
                challengeTitle
                    .trim()
                    .ifBlank {
                        "Challenge"
                    }

            return CornerstonePointsTransactionEntity(
                amount =
                    points.coerceAtLeast(0),
                transactionType =
                    CornerstonePointsTransactionType
                        .CHALLENGE_REWARD
                        .name,
                sourceType =
                    CornerstonePointsSourceType
                        .CHALLENGE
                        .name,
                sourceId =
                    safeChallengeId,
                deduplicationKey =
                    "challenge_reward:$safeChallengeId",
                description =
                    "$safeChallengeTitle reward"
            )
        }

        fun proPassRedemption(
            redemptionId: String,
            pointsCost: Int,
            proPassDays: Int
        ): CornerstonePointsTransactionEntity {
            val safeRedemptionId =
                redemptionId
                    .trim()
                    .ifBlank {
                        "unknown_redemption"
                    }

            val safeProPassDays =
                proPassDays.coerceAtLeast(1)

            return CornerstonePointsTransactionEntity(
                amount =
                    -pointsCost
                        .coerceAtLeast(0),
                transactionType =
                    CornerstonePointsTransactionType
                        .PRO_PASS_REDEMPTION
                        .name,
                sourceType =
                    CornerstonePointsSourceType
                        .REDEMPTION
                        .name,
                sourceId =
                    safeRedemptionId,
                deduplicationKey =
                    "pro_pass_redemption:$safeRedemptionId",
                description =
                    "$safeProPassDays-day Cornerstone Pro Pass"
            )
        }
    }
}