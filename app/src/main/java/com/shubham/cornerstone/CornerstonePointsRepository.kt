package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow

sealed interface PointsRewardResult {

    data class Awarded(
        val points: Int
    ) : PointsRewardResult

    data object AlreadyAwarded :
        PointsRewardResult

    data object InvalidReward :
        PointsRewardResult
}

sealed interface PointsRedemptionResult {

    data class Redeemed(
        val pointsSpent: Int,
        val proPassDays: Int
    ) : PointsRedemptionResult

    data object InsufficientBalance :
        PointsRedemptionResult

    data object AlreadyRedeemed :
        PointsRedemptionResult

    data object InvalidRedemption :
        PointsRedemptionResult
}

class CornerstonePointsRepository(
    private val dao: CornerstonePointsDao
) {

    fun observeBalance(): Flow<Int> {
        return dao.observePointsBalance()
    }

    fun observeRecentTransactions(
        limit: Int = 50
    ): Flow<List<CornerstonePointsTransactionEntity>> {
        return dao.observeRecentTransactions(
            limit = limit.coerceIn(
                minimumValue = 1,
                maximumValue = 200
            )
        )
    }

    suspend fun getBalance(): Int {
        return dao.getPointsBalance()
            .coerceAtLeast(0)
    }

    suspend fun getRecentTransactions(
        limit: Int = 50
    ): List<CornerstonePointsTransactionEntity> {
        return dao.getRecentTransactions(
            limit = limit.coerceIn(
                minimumValue = 1,
                maximumValue = 200
            )
        )
    }

    suspend fun awardChallengePoints(
        challengeId: String,
        challengeTitle: String,
        points: Int
    ): PointsRewardResult {
        val safeChallengeId =
            challengeId.trim()

        val safePoints =
            points.coerceAtLeast(0)

        if (
            safeChallengeId.isBlank() ||
            safePoints <= 0
        ) {
            return PointsRewardResult
                .InvalidReward
        }

        val transaction =
            CornerstonePointsTransactionEntity
                .challengeReward(
                    challengeId =
                        safeChallengeId,
                    challengeTitle =
                        challengeTitle,
                    points =
                        safePoints
                )

        val insertedId =
            dao.insertTransaction(
                transaction = transaction
            )

        return if (insertedId > 0L) {
            PointsRewardResult.Awarded(
                points = safePoints
            )
        } else {
            PointsRewardResult
                .AlreadyAwarded
        }
    }

    suspend fun hasChallengeReward(
        challengeId: String
    ): Boolean {
        val safeChallengeId =
            challengeId.trim()

        if (safeChallengeId.isBlank()) {
            return false
        }

        return dao.hasDeduplicationKey(
            deduplicationKey =
                challengeRewardKey(
                    challengeId =
                        safeChallengeId
                )
        )
    }

    suspend fun redeemPointsForProPass(
        redemptionId: String,
        pointsCost: Int,
        proPassDays: Int
    ): PointsRedemptionResult {
        val safeRedemptionId =
            redemptionId.trim()

        val safePointsCost =
            pointsCost.coerceAtLeast(0)

        val safeProPassDays =
            proPassDays.coerceAtLeast(0)

        if (
            safeRedemptionId.isBlank() ||
            safePointsCost <= 0 ||
            safeProPassDays <= 0
        ) {
            return PointsRedemptionResult
                .InvalidRedemption
        }

        val deduplicationKey =
            proPassRedemptionKey(
                redemptionId =
                    safeRedemptionId
            )

        val alreadyRedeemed =
            dao.hasDeduplicationKey(
                deduplicationKey =
                    deduplicationKey
            )

        if (alreadyRedeemed) {
            return PointsRedemptionResult
                .AlreadyRedeemed
        }

        val transaction =
            CornerstonePointsTransactionEntity
                .proPassRedemption(
                    redemptionId =
                        safeRedemptionId,
                    pointsCost =
                        safePointsCost,
                    proPassDays =
                        safeProPassDays
                )

        val redeemed =
            dao.insertRedemptionIfAffordable(
                transaction = transaction
            )

        if (redeemed) {
            return PointsRedemptionResult
                .Redeemed(
                    pointsSpent =
                        safePointsCost,
                    proPassDays =
                        safeProPassDays
                )
        }

        val nowAlreadyRedeemed =
            dao.hasDeduplicationKey(
                deduplicationKey =
                    deduplicationKey
            )

        return if (nowAlreadyRedeemed) {
            PointsRedemptionResult
                .AlreadyRedeemed
        } else {
            PointsRedemptionResult
                .InsufficientBalance
        }
    }

    private fun challengeRewardKey(
        challengeId: String
    ): String {
        return "challenge_reward:$challengeId"
    }

    private fun proPassRedemptionKey(
        redemptionId: String
    ): String {
        return "pro_pass_redemption:$redemptionId"
    }
}