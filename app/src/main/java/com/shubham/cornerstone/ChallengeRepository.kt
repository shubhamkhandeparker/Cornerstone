package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

data class ChallengeWithProgress(
    val definition: ChallengeDefinition,
    val progress: ChallengeProgress?,
    val rewardClaimed: Boolean
) {
    val status: ChallengeStatus
        get() = progress?.status
            ?: ChallengeStatus.NOT_STARTED

    val isActive: Boolean
        get() = status ==
                ChallengeStatus.ACTIVE

    val isCompleted: Boolean
        get() = status ==
                ChallengeStatus.COMPLETED
}

sealed interface ChallengeRewardClaimResult {

    data class PointsAwarded(
        val points: Int
    ) : ChallengeRewardClaimResult

    data class PointsAlreadyAwarded(
        val points: Int
    ) : ChallengeRewardClaimResult

    /*
     * isGranted = true means the earned Pro Pass
     * was safely saved.
     *
     * isGranted = false means the Pro Pass
     * repository has not been connected yet.
     *
     * This existing result name is temporarily kept
     * so the current ViewModel continues compiling.
     */
    data class ProPassPending(
        val proPassDays: Int,
        val isGranted: Boolean = false
    ) : ChallengeRewardClaimResult

    data object NoReward :
        ChallengeRewardClaimResult

    data object AlreadyClaimed :
        ChallengeRewardClaimResult

    data object PointsSystemUnavailable :
        ChallengeRewardClaimResult

    data object InvalidReward :
        ChallengeRewardClaimResult
}

class ChallengeRepository(
    private val dao: ChallengeProgressDao,
    private val pointsRepository:
    CornerstonePointsRepository? = null,
    private val earnedProPassRepository:
    EarnedProPassRepository? = null
) {

    fun observeChallenges():
            Flow<List<ChallengeWithProgress>> {

        return dao.observeAllProgress()
            .map { savedProgress ->
                val progressByChallengeId =
                    savedProgress.associateBy { entity ->
                        entity.challengeId
                    }

                ChallengeCatalog
                    .activeChallenges()
                    .map { definition ->
                        val entity =
                            progressByChallengeId[
                                definition.id
                            ]

                        ChallengeWithProgress(
                            definition = definition,
                            progress =
                                entity
                                    ?.toChallengeProgress(),
                            rewardClaimed =
                                entity
                                    ?.rewardClaimed
                                    ?: false
                        )
                    }
            }
    }

    fun observeChallenge(
        challengeId: String
    ): Flow<ChallengeWithProgress?> {

        return dao.observeProgress(
            challengeId
        ).map { entity ->
            val definition =
                ChallengeCatalog.getChallenge(
                    challengeId
                ) ?: return@map null

            if (!definition.isActive) {
                return@map null
            }

            ChallengeWithProgress(
                definition = definition,
                progress =
                    entity
                        ?.toChallengeProgress(),
                rewardClaimed =
                    entity
                        ?.rewardClaimed
                        ?: false
            )
        }
    }

    fun observeActiveChallenges():
            Flow<List<ChallengeWithProgress>> {

        return dao.observeActiveChallenges()
            .map { entities ->
                entities.mapNotNull { entity ->
                    val definition =
                        ChallengeCatalog
                            .getChallenge(
                                entity.challengeId
                            )
                            ?: return@mapNotNull null

                    if (!definition.isActive) {
                        return@mapNotNull null
                    }

                    ChallengeWithProgress(
                        definition = definition,
                        progress =
                            entity
                                .toChallengeProgress(),
                        rewardClaimed =
                            entity.rewardClaimed
                    )
                }
            }
    }

    fun observeCompletedChallenges():
            Flow<List<ChallengeWithProgress>> {

        return dao.observeCompletedChallenges()
            .map { entities ->
                entities.mapNotNull { entity ->
                    val definition =
                        ChallengeCatalog
                            .getChallenge(
                                entity.challengeId
                            )
                            ?: return@mapNotNull null

                    if (!definition.isActive) {
                        return@mapNotNull null
                    }

                    ChallengeWithProgress(
                        definition = definition,
                        progress =
                            entity
                                .toChallengeProgress(),
                        rewardClaimed =
                            entity.rewardClaimed
                    )
                }
            }
    }

    suspend fun startChallenge(
        challengeId: String,
        today: LocalDate = LocalDate.now()
    ): Result<Unit> {
        return runCatching {
            val definition =
                ChallengeCatalog.getChallenge(
                    challengeId
                ) ?: error(
                    "Challenge definition not found."
                )

            require(definition.isActive) {
                "This challenge is not currently active."
            }

            val existing =
                dao.getProgress(
                    challengeId
                )

            require(
                existing?.status !=
                        ChallengeStatus
                            .ACTIVE
                            .name
            ) {
                "This challenge is already active."
            }

            val progress =
                ChallengeProgress(
                    challengeId =
                        challengeId,
                    status =
                        ChallengeStatus.ACTIVE,
                    integrityStatus =
                        ChallengeIntegrityStatus
                            .VALID,
                    startedAtEpochDay =
                        today.toEpochDay(),
                    completedAtEpochDay =
                        null,
                    currentDay = 1,
                    completedDays = 0,
                    currentStreakDays = 0,
                    totalValidatedSessions = 0,
                    totalValidatedMinutes = 0,
                    totalValidatedRepetitions = 0
                )

            dao.saveProgress(
                ChallengeProgressEntity
                    .fromChallengeProgress(
                        progress = progress,
                        rewardClaimed = false,
                        createdAtEpochMs =
                            existing
                                ?.createdAtEpochMs
                                ?: System
                                    .currentTimeMillis()
                    )
            )
        }
    }

    suspend fun abandonChallenge(
        challengeId: String
    ): Result<Unit> {
        return runCatching {
            val existing =
                dao.getProgress(
                    challengeId
                ) ?: error(
                    "Challenge progress not found."
                )

            require(
                existing.status ==
                        ChallengeStatus
                            .ACTIVE
                            .name
            ) {
                "Only active challenges can be abandoned."
            }

            dao.updateStatus(
                challengeId =
                    challengeId,
                status =
                    ChallengeStatus
                        .ABANDONED
                        .name
            )
        }
    }

    suspend fun restartChallenge(
        challengeId: String,
        today: LocalDate = LocalDate.now()
    ): Result<Unit> {
        return runCatching {
            dao.deleteProgress(
                challengeId
            )

            startChallenge(
                challengeId =
                    challengeId,
                today =
                    today
            ).getOrThrow()
        }
    }

    suspend fun claimReward(
        challengeId: String
    ): Result<ChallengeRewardClaimResult> {
        return runCatching {
            val safeChallengeId =
                challengeId.trim()

            require(
                safeChallengeId.isNotBlank()
            ) {
                "Challenge ID cannot be blank."
            }

            val definition =
                ChallengeCatalog.getChallenge(
                    safeChallengeId
                ) ?: error(
                    "Challenge definition not found."
                )

            val existing =
                dao.getProgress(
                    safeChallengeId
                ) ?: error(
                    "Challenge progress not found."
                )

            require(
                existing.status ==
                        ChallengeStatus
                            .COMPLETED
                            .name
            ) {
                "The challenge must be completed before claiming its reward."
            }

            if (existing.rewardClaimed) {
                ChallengeRewardClaimResult
                    .AlreadyClaimed
            } else {
                when (
                    definition.reward.type
                ) {
                    ChallengeRewardType.NONE -> {
                        dao.markRewardClaimed(
                            challengeId =
                                safeChallengeId
                        )

                        ChallengeRewardClaimResult
                            .NoReward
                    }

                    ChallengeRewardType.POINTS -> {
                        claimPointsReward(
                            definition =
                                definition
                        )
                    }

                    ChallengeRewardType.PRO_PASS -> {
                        claimProPassReward(
                            definition =
                                definition
                        )
                    }
                }
            }
        }
    }

    /*
     * Kept for compatibility with existing callers.
     */
    suspend fun markRewardClaimed(
        challengeId: String
    ): Result<Unit> {
        return runCatching {
            when (
                val claimResult =
                    claimReward(
                        challengeId =
                            challengeId
                    ).getOrThrow()
            ) {
                is ChallengeRewardClaimResult
                .PointsAwarded -> Unit

                is ChallengeRewardClaimResult
                .PointsAlreadyAwarded -> Unit

                ChallengeRewardClaimResult
                    .NoReward -> Unit

                ChallengeRewardClaimResult
                    .AlreadyClaimed -> {

                    error(
                        "This reward has already been claimed."
                    )
                }

                is ChallengeRewardClaimResult
                .ProPassPending -> {

                    if (claimResult.isGranted) {
                        Unit
                    } else {
                        error(
                            "Earned Pro Pass storage is not connected yet."
                        )
                    }
                }

                ChallengeRewardClaimResult
                    .PointsSystemUnavailable -> {

                    error(
                        "Cornerstone Points is not connected yet."
                    )
                }

                ChallengeRewardClaimResult
                    .InvalidReward -> {

                    error(
                        "The challenge reward is invalid."
                    )
                }
            }
        }
    }

    private suspend fun claimPointsReward(
        definition: ChallengeDefinition
    ): ChallengeRewardClaimResult {
        val points =
            definition.reward.points
                .coerceAtLeast(0)

        if (points <= 0) {
            return ChallengeRewardClaimResult
                .InvalidReward
        }

        val repository =
            pointsRepository
                ?: return ChallengeRewardClaimResult
                    .PointsSystemUnavailable

        return when (
            repository.awardChallengePoints(
                challengeId =
                    definition.id,
                challengeTitle =
                    definition.title,
                points =
                    points
            )
        ) {
            is PointsRewardResult.Awarded -> {
                dao.markRewardClaimed(
                    challengeId =
                        definition.id
                )

                ChallengeRewardClaimResult
                    .PointsAwarded(
                        points = points
                    )
            }

            PointsRewardResult.AlreadyAwarded -> {
                /*
                 * The unique ledger entry proves the
                 * points were previously awarded.
                 *
                 * Repair the challenge claim flag
                 * without adding points again.
                 */
                dao.markRewardClaimed(
                    challengeId =
                        definition.id
                )

                ChallengeRewardClaimResult
                    .PointsAlreadyAwarded(
                        points = points
                    )
            }

            PointsRewardResult.InvalidReward -> {
                ChallengeRewardClaimResult
                    .InvalidReward
            }
        }
    }

    private suspend fun claimProPassReward(
        definition: ChallengeDefinition
    ): ChallengeRewardClaimResult {
        val proPassDays =
            definition.reward.proPassDays
                .coerceAtLeast(0)

        if (proPassDays <= 0) {
            return ChallengeRewardClaimResult
                .InvalidReward
        }

        val repository =
            earnedProPassRepository
                ?: return ChallengeRewardClaimResult
                    .ProPassPending(
                        proPassDays =
                            proPassDays,
                        isGranted =
                            false
                    )

        return when (
            repository.grantChallengeProPass(
                challengeId =
                    definition.id,
                challengeTitle =
                    definition.title,
                proPassDays =
                    proPassDays
            )
        ) {
            is EarnedProPassGrantResult
            .Granted -> {

                dao.markRewardClaimed(
                    challengeId =
                        definition.id
                )

                ChallengeRewardClaimResult
                    .ProPassPending(
                        proPassDays =
                            proPassDays,
                        isGranted =
                            true
                    )
            }

            is EarnedProPassGrantResult
            .AlreadyGranted -> {

                /*
                 * The unique pass key proves the reward
                 * was previously stored. Repair the
                 * challenge claim flag safely.
                 */
                dao.markRewardClaimed(
                    challengeId =
                        definition.id
                )

                ChallengeRewardClaimResult
                    .ProPassPending(
                        proPassDays =
                            proPassDays,
                        isGranted =
                            true
                    )
            }

            EarnedProPassGrantResult
                .InvalidReward -> {

                ChallengeRewardClaimResult
                    .InvalidReward
            }
        }
    }
}