package com.shubham.cornerstone

import androidx.room.withTransaction
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

sealed interface EarnedProPassGrantResult {

    data class Granted(
        val pass: EarnedProPassEntity
    ) : EarnedProPassGrantResult

    data class AlreadyGranted(
        val pass: EarnedProPassEntity
    ) : EarnedProPassGrantResult

    data object InvalidReward :
        EarnedProPassGrantResult
}

sealed interface EarnedProPassRedemptionResult {

    data class Redeemed(
        val pass: EarnedProPassEntity,
        val pointsSpent: Int
    ) : EarnedProPassRedemptionResult

    data class Repaired(
        val pass: EarnedProPassEntity
    ) : EarnedProPassRedemptionResult

    data class AlreadyRedeemed(
        val pass: EarnedProPassEntity
    ) : EarnedProPassRedemptionResult

    data object InsufficientPoints :
        EarnedProPassRedemptionResult

    data object InvalidRedemption :
        EarnedProPassRedemptionResult
}

class EarnedProPassRepository(
    private val database: AppDatabase
) {

    private val dao =
        database.earnedProPassDao()

    private val pointsRepository =
        CornerstonePointsRepository(
            dao =
                database.cornerstonePointsDao()
        )

    /*
     * This observes the Room table directly.
     *
     * Any Pro Pass inserted by another repository
     * instance immediately updates every observer
     * across the app.
     *
     * The time ticker is still required so an active
     * pass automatically expires while the app is open.
     */
    fun observeActivePass(
        refreshIntervalMillis: Long =
            60_000L
    ): Flow<EarnedProPassEntity?> {
        val safeInterval =
            refreshIntervalMillis.coerceAtLeast(
                5_000L
            )

        return combine(
            dao.observeRecentPasses(
                limit = 100
            ),
            observeCurrentTime(
                refreshIntervalMillis =
                    safeInterval
            )
        ) {
                passes,
                nowEpochMs ->

            passes
                .asSequence()
                .filter { pass ->
                    pass.isActive(
                        nowEpochMs =
                            nowEpochMs
                    )
                }
                .maxByOrNull { pass ->
                    pass.expiresAtEpochMs
                }
        }.distinctUntilChanged()
    }

    fun observeRecentPasses(
        limit: Int = 20
    ): Flow<List<EarnedProPassEntity>> {
        return dao.observeRecentPasses(
            limit =
                limit.coerceIn(
                    minimumValue = 1,
                    maximumValue = 100
                )
        )
    }

    suspend fun getActivePass(
        nowEpochMs: Long =
            System.currentTimeMillis()
    ): EarnedProPassEntity? {
        return dao.getActivePass(
            nowEpochMs =
                nowEpochMs.coerceAtLeast(
                    0L
                )
        )
    }

    suspend fun hasActivePass(
        nowEpochMs: Long =
            System.currentTimeMillis()
    ): Boolean {
        return getActivePass(
            nowEpochMs =
                nowEpochMs
        ) != null
    }

    suspend fun getRecentPasses(
        limit: Int = 20
    ): List<EarnedProPassEntity> {
        return dao.getRecentPasses(
            limit =
                limit.coerceIn(
                    minimumValue = 1,
                    maximumValue = 100
                )
        )
    }

    suspend fun grantChallengeProPass(
        challengeId: String,
        challengeTitle: String,
        proPassDays: Int
    ): EarnedProPassGrantResult {
        val safeChallengeId =
            challengeId.trim()

        val safeDays =
            proPassDays.coerceAtLeast(
                0
            )

        if (
            safeChallengeId.isBlank() ||
            safeDays <= 0
        ) {
            return EarnedProPassGrantResult
                .InvalidReward
        }

        val deduplicationKey =
            challengePassKey(
                challengeId =
                    safeChallengeId
            )

        return database.withTransaction {
            val existing =
                dao.getPassByDeduplicationKey(
                    deduplicationKey =
                        deduplicationKey
                )

            if (existing != null) {
                EarnedProPassGrantResult
                    .AlreadyGranted(
                        pass =
                            existing
                    )
            } else {
                val now =
                    System.currentTimeMillis()

                val startsAt =
                    dao.getLatestAvailableExpiry(
                        nowEpochMs =
                            now
                    ).coerceAtLeast(
                        now
                    )

                val pass =
                    EarnedProPassEntity
                        .challengeReward(
                            challengeId =
                                safeChallengeId,
                            challengeTitle =
                                challengeTitle,
                            proPassDays =
                                safeDays,
                            startsAtEpochMs =
                                startsAt
                        )

                val insertedId =
                    dao.insertPass(
                        pass =
                            pass
                    )

                if (insertedId > 0L) {
                    EarnedProPassGrantResult
                        .Granted(
                            pass =
                                pass.copy(
                                    id =
                                        insertedId
                                )
                        )
                } else {
                    val nowExisting =
                        dao.getPassByDeduplicationKey(
                            deduplicationKey =
                                deduplicationKey
                        )

                    if (nowExisting != null) {
                        EarnedProPassGrantResult
                            .AlreadyGranted(
                                pass =
                                    nowExisting
                            )
                    } else {
                        error(
                            "Unable to save earned Pro Pass."
                        )
                    }
                }
            }
        }
    }

    suspend fun redeemPointsForProPass(
        redemptionId: String,
        pointsCost: Int,
        proPassDays: Int
    ): EarnedProPassRedemptionResult {
        val safeRedemptionId =
            redemptionId.trim()

        val safePointsCost =
            pointsCost.coerceAtLeast(
                0
            )

        val safeDays =
            proPassDays.coerceAtLeast(
                0
            )

        if (
            safeRedemptionId.isBlank() ||
            safePointsCost <= 0 ||
            safeDays <= 0
        ) {
            return EarnedProPassRedemptionResult
                .InvalidRedemption
        }

        val passDeduplicationKey =
            pointsPassKey(
                redemptionId =
                    safeRedemptionId
            )

        return database.withTransaction {
            val existingPass =
                dao.getPassByDeduplicationKey(
                    deduplicationKey =
                        passDeduplicationKey
                )

            if (existingPass != null) {
                EarnedProPassRedemptionResult
                    .AlreadyRedeemed(
                        pass =
                            existingPass
                    )
            } else {
                when (
                    pointsRepository
                        .redeemPointsForProPass(
                            redemptionId =
                                safeRedemptionId,
                            pointsCost =
                                safePointsCost,
                            proPassDays =
                                safeDays
                        )
                ) {
                    is PointsRedemptionResult
                    .Redeemed -> {

                        val pass =
                            createPointsPass(
                                redemptionId =
                                    safeRedemptionId,
                                pointsCost =
                                    safePointsCost,
                                proPassDays =
                                    safeDays
                            )

                        val insertedId =
                            dao.insertPass(
                                pass =
                                    pass
                            )

                        check(
                            insertedId > 0L
                        ) {
                            "Unable to save redeemed Pro Pass."
                        }

                        EarnedProPassRedemptionResult
                            .Redeemed(
                                pass =
                                    pass.copy(
                                        id =
                                            insertedId
                                    ),
                                pointsSpent =
                                    safePointsCost
                            )
                    }

                    PointsRedemptionResult
                        .AlreadyRedeemed -> {

                        /*
                         * Repair an interrupted redemption
                         * where the points transaction exists
                         * but the corresponding pass is absent.
                         */
                        val repairedPass =
                            createPointsPass(
                                redemptionId =
                                    safeRedemptionId,
                                pointsCost =
                                    safePointsCost,
                                proPassDays =
                                    safeDays
                            )

                        val insertedId =
                            dao.insertPass(
                                pass =
                                    repairedPass
                            )

                        if (insertedId > 0L) {
                            EarnedProPassRedemptionResult
                                .Repaired(
                                    pass =
                                        repairedPass.copy(
                                            id =
                                                insertedId
                                        )
                                )
                        } else {
                            val nowExisting =
                                dao.getPassByDeduplicationKey(
                                    deduplicationKey =
                                        passDeduplicationKey
                                ) ?: error(
                                    "Unable to restore redeemed Pro Pass."
                                )

                            EarnedProPassRedemptionResult
                                .AlreadyRedeemed(
                                    pass =
                                        nowExisting
                                )
                        }
                    }

                    PointsRedemptionResult
                        .InsufficientBalance -> {

                        EarnedProPassRedemptionResult
                            .InsufficientPoints
                    }

                    PointsRedemptionResult
                        .InvalidRedemption -> {

                        EarnedProPassRedemptionResult
                            .InvalidRedemption
                    }
                }
            }
        }
    }

    suspend fun revokePass(
        passId: Long
    ): Boolean {
        if (passId <= 0L) {
            return false
        }

        return dao.revokePass(
            passId =
                passId
        ) > 0
    }

    private fun observeCurrentTime(
        refreshIntervalMillis: Long
    ): Flow<Long> {
        return flow {
            while (
                currentCoroutineContext()
                    .isActive
            ) {
                emit(
                    System.currentTimeMillis()
                )

                delay(
                    refreshIntervalMillis
                )
            }
        }
    }

    private suspend fun createPointsPass(
        redemptionId: String,
        pointsCost: Int,
        proPassDays: Int
    ): EarnedProPassEntity {
        val now =
            System.currentTimeMillis()

        val startsAt =
            dao.getLatestAvailableExpiry(
                nowEpochMs =
                    now
            ).coerceAtLeast(
                now
            )

        return EarnedProPassEntity
            .pointsRedemption(
                redemptionId =
                    redemptionId,
                pointsCost =
                    pointsCost,
                proPassDays =
                    proPassDays,
                startsAtEpochMs =
                    startsAt
            )
    }

    private fun challengePassKey(
        challengeId: String
    ): String {
        return "challenge_pro_pass:$challengeId"
    }

    private fun pointsPassKey(
        redemptionId: String
    ): String {
        return "points_pro_pass:$redemptionId"
    }
}