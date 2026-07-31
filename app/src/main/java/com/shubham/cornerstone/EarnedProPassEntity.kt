package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

enum class EarnedProPassSourceType {
    CHALLENGE_REWARD,
    POINTS_REDEMPTION,
    MANUAL_ADJUSTMENT
}

@Entity(
    tableName = "earned_pro_passes",
    indices = [
        Index(
            value = ["sourceType"]
        ),
        Index(
            value = ["sourceId"]
        ),
        Index(
            value = ["startsAtEpochMs"]
        ),
        Index(
            value = ["expiresAtEpochMs"]
        ),
        Index(
            value = ["deduplicationKey"],
            unique = true
        )
    ]
)
data class EarnedProPassEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long = 0L,

    val sourceType: String,

    val sourceId: String?,

    val deduplicationKey: String?,

    val durationDays: Int,

    val startsAtEpochMs: Long,

    val expiresAtEpochMs: Long,

    val revokedAtEpochMs: Long? = null,

    val description: String,

    val createdAtEpochMs: Long =
        System.currentTimeMillis()
) {

    val isRevoked: Boolean
        get() = revokedAtEpochMs != null

    fun isActive(
        nowEpochMs: Long =
            System.currentTimeMillis()
    ): Boolean {
        return !isRevoked &&
                nowEpochMs >= startsAtEpochMs &&
                nowEpochMs < expiresAtEpochMs
    }

    companion object {

        private const val MILLIS_PER_DAY =
            24L * 60L * 60L * 1_000L

        fun challengeReward(
            challengeId: String,
            challengeTitle: String,
            proPassDays: Int,
            startsAtEpochMs: Long =
                System.currentTimeMillis()
        ): EarnedProPassEntity {
            val safeChallengeId =
                challengeId.trim()

            val safeTitle =
                challengeTitle.trim()
                    .ifBlank {
                        "Completed challenge"
                    }

            val safeDays =
                proPassDays.coerceAtLeast(1)

            val safeStart =
                startsAtEpochMs
                    .coerceAtLeast(0L)

            return EarnedProPassEntity(
                sourceType =
                    EarnedProPassSourceType
                        .CHALLENGE_REWARD
                        .name,
                sourceId =
                    safeChallengeId,
                deduplicationKey =
                    "challenge_pro_pass:$safeChallengeId",
                durationDays =
                    safeDays,
                startsAtEpochMs =
                    safeStart,
                expiresAtEpochMs =
                    calculateExpiry(
                        startsAtEpochMs =
                            safeStart,
                        durationDays =
                            safeDays
                    ),
                description =
                    "$safeDays-day Pro Pass earned from $safeTitle"
            )
        }

        fun pointsRedemption(
            redemptionId: String,
            pointsCost: Int,
            proPassDays: Int,
            startsAtEpochMs: Long =
                System.currentTimeMillis()
        ): EarnedProPassEntity {
            val safeRedemptionId =
                redemptionId.trim()

            val safePointsCost =
                pointsCost.coerceAtLeast(1)

            val safeDays =
                proPassDays.coerceAtLeast(1)

            val safeStart =
                startsAtEpochMs
                    .coerceAtLeast(0L)

            return EarnedProPassEntity(
                sourceType =
                    EarnedProPassSourceType
                        .POINTS_REDEMPTION
                        .name,
                sourceId =
                    safeRedemptionId,
                deduplicationKey =
                    "points_pro_pass:$safeRedemptionId",
                durationDays =
                    safeDays,
                startsAtEpochMs =
                    safeStart,
                expiresAtEpochMs =
                    calculateExpiry(
                        startsAtEpochMs =
                            safeStart,
                        durationDays =
                            safeDays
                    ),
                description =
                    "$safeDays-day Pro Pass redeemed for $safePointsCost points"
            )
        }

        private fun calculateExpiry(
            startsAtEpochMs: Long,
            durationDays: Int
        ): Long {
            val safeDays =
                durationDays.coerceAtLeast(1)

            val durationMillis =
                safeDays.toLong()
                    .times(MILLIS_PER_DAY)

            return startsAtEpochMs
                .plus(durationMillis)
        }
    }
}