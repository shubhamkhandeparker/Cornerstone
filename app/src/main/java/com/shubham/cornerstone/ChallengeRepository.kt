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
        get() = status == ChallengeStatus.ACTIVE

    val isCompleted: Boolean
        get() = status == ChallengeStatus.COMPLETED
}

class ChallengeRepository(
    private val dao: ChallengeProgressDao
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
                            progress = entity
                                ?.toChallengeProgress(),
                            rewardClaimed =
                                entity?.rewardClaimed
                                    ?: false
                        )
                    }
            }
    }

    fun observeChallenge(
        challengeId: String
    ): Flow<ChallengeWithProgress?> {

        return dao.observeProgress(challengeId)
            .map { entity ->
                val definition =
                    ChallengeCatalog.getChallenge(
                        challengeId
                    ) ?: return@map null

                ChallengeWithProgress(
                    definition = definition,
                    progress = entity
                        ?.toChallengeProgress(),
                    rewardClaimed =
                        entity?.rewardClaimed
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
                        ChallengeCatalog.getChallenge(
                            entity.challengeId
                        ) ?: return@mapNotNull null

                    ChallengeWithProgress(
                        definition = definition,
                        progress =
                            entity.toChallengeProgress(),
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
                        ChallengeCatalog.getChallenge(
                            entity.challengeId
                        ) ?: return@mapNotNull null

                    ChallengeWithProgress(
                        definition = definition,
                        progress =
                            entity.toChallengeProgress(),
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
                dao.getProgress(challengeId)

            require(
                existing?.status !=
                        ChallengeStatus.ACTIVE.name
            ) {
                "This challenge is already active."
            }

            val progress =
                ChallengeProgress(
                    challengeId = challengeId,
                    status =
                        ChallengeStatus.ACTIVE,
                    integrityStatus =
                        ChallengeIntegrityStatus.VALID,
                    startedAtEpochDay =
                        today.toEpochDay(),
                    completedAtEpochDay = null,
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
                            existing?.createdAtEpochMs
                                ?: System.currentTimeMillis()
                    )
            )
        }
    }

    suspend fun abandonChallenge(
        challengeId: String
    ): Result<Unit> {
        return runCatching {
            val existing =
                dao.getProgress(challengeId)
                    ?: error(
                        "Challenge progress not found."
                    )

            require(
                existing.status ==
                        ChallengeStatus.ACTIVE.name
            ) {
                "Only active challenges can be abandoned."
            }

            dao.updateStatus(
                challengeId = challengeId,
                status =
                    ChallengeStatus.ABANDONED.name
            )
        }
    }

    suspend fun restartChallenge(
        challengeId: String,
        today: LocalDate = LocalDate.now()
    ): Result<Unit> {
        return runCatching {
            dao.deleteProgress(challengeId)

            startChallenge(
                challengeId = challengeId,
                today = today
            ).getOrThrow()
        }
    }

    suspend fun markRewardClaimed(
        challengeId: String
    ): Result<Unit> {
        return runCatching {
            val existing =
                dao.getProgress(challengeId)
                    ?: error(
                        "Challenge progress not found."
                    )

            require(
                existing.status ==
                        ChallengeStatus.COMPLETED.name
            ) {
                "The challenge must be completed before claiming its reward."
            }

            require(!existing.rewardClaimed) {
                "This reward has already been claimed."
            }

            dao.markRewardClaimed(
                challengeId = challengeId
            )
        }
    }
}