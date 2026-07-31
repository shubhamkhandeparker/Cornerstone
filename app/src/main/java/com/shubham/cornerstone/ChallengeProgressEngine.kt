package com.shubham.cornerstone

import java.time.LocalDate
import java.time.temporal.ChronoUnit

private const val KICK_MOVEMENT_TYPE = "KICK"
private const val ELITE_MIN_SESSION_SECONDS = 60 * 60

private val FOUNDATION_SESSION_TYPES =
    setOf(
        "ai",
        "playlist"
    )

class ChallengeProgressEngine(
    private val challengeProgressDao:
    ChallengeProgressDao,
    private val trainingSessionRepository:
    TrainingSessionRepository,
    private val trainingRepetitionRepository:
    TrainingRepetitionRepository? = null
) {

    suspend fun refreshActiveChallenges(
        today: LocalDate = LocalDate.now()
    ) {
        val activeChallenges =
            challengeProgressDao
                .getActiveChallenges()

        activeChallenges.forEach { progressEntity ->
            refreshChallenge(
                progressEntity = progressEntity,
                today = today
            )
        }
    }

    private suspend fun refreshChallenge(
        progressEntity: ChallengeProgressEntity,
        today: LocalDate
    ) {
        val definition =
            ChallengeCatalog.getChallenge(
                challengeId =
                    progressEntity.challengeId
            ) ?: return

        val startedAtEpochDay =
            progressEntity.startedAtEpochDay
                ?: return

        val startDate =
            LocalDate.ofEpochDay(
                startedAtEpochDay
            )

        val finalChallengeDate =
            startDate.plusDays(
                definition.durationDays
                    .toLong() - 1L
            )

        val evaluationEndDate =
            if (
                today.isAfter(
                    finalChallengeDate
                )
            ) {
                finalChallengeDate
            } else {
                today
            }

        if (
            evaluationEndDate.isBefore(
                startDate
            )
        ) {
            return
        }

        val sessions =
            trainingSessionRepository
                .getSessionsBetweenDates(
                    startDate = startDate,
                    endDate = evaluationEndDate
                )

        val sessionsByDate =
            sessions.groupBy { session ->
                session.localDate
            }

        val validKickRepetitions =
            if (
                definition.requiredRepetitionsPerDay >
                0
            ) {
                trainingRepetitionRepository
                    ?.getRepetitionsBetweenDates(
                        startDate = startDate,
                        endDate = evaluationEndDate
                    )
                    .orEmpty()
                    .filter { repetition ->
                        repetition.movementType ==
                                KICK_MOVEMENT_TYPE &&
                                repetition.integrityStatus ==
                                ChallengeIntegrityStatus
                                    .VALID
                                    .name &&
                                repetition.repetitionCount >
                                0
                    }
            } else {
                emptyList()
            }

        val repetitionsByDate =
            validKickRepetitions.groupBy { repetition ->
                repetition.localDate
            }

        val evaluatedDayCount =
            ChronoUnit.DAYS.between(
                startDate,
                evaluationEndDate
            ).toInt() + 1

        val requiresSingleKickSession =
            definition.id ==
                    ChallengeCatalog
                        .ONE_HUNDRED_KICKS_ID

        val requiresFoundationSession =
            definition.id ==
                    ChallengeCatalog
                        .FOUNDATION_14_ID

        val requiresEliteSessions =
            definition.id ==
                    ChallengeCatalog
                        .ELITE_75_ID

        var completedDays = 0
        var consecutiveCompletedDays = 0
        var missedRequiredPastDay = false

        var bestSingleKickSessionRepetitions = 0
        var validatedKickSessionCount = 0
        var validatedKickActiveSeconds = 0

        var validatedFoundationSessionCount = 0
        var validatedFoundationActiveSeconds = 0

        var validatedEliteSessionCount = 0
        var validatedEliteActiveSeconds = 0

        repeat(evaluatedDayCount) { dayIndex ->
            val date =
                startDate.plusDays(
                    dayIndex.toLong()
                )

            val dateString =
                date.toString()

            val sessionsForDay =
                sessionsByDate[
                    dateString
                ].orEmpty()

            val repetitionsForDay =
                repetitionsByDate[
                    dateString
                ].orEmpty()

            val foundationMinimumSeconds =
                definition
                    .requiredActiveMinutesPerDay
                    .coerceAtLeast(0) *
                        60

            val foundationSessionsForDay =
                if (requiresFoundationSession) {
                    sessionsForDay.filter { session ->
                        session.sessionType
                            .trim()
                            .lowercase() in
                                FOUNDATION_SESSION_TYPES &&
                                session.durationSeconds >=
                                foundationMinimumSeconds
                    }
                } else {
                    emptyList()
                }

            if (requiresFoundationSession) {
                validatedFoundationSessionCount +=
                    foundationSessionsForDay.size

                validatedFoundationActiveSeconds +=
                    foundationSessionsForDay.sumOf { session ->
                        session.durationSeconds
                            .coerceAtLeast(0)
                    }
            }

            val eliteSessionsForDay =
                if (requiresEliteSessions) {
                    sessionsForDay.filter { session ->
                        session.durationSeconds >=
                                ELITE_MIN_SESSION_SECONDS
                    }
                } else {
                    emptyList()
                }

            if (requiresEliteSessions) {
                validatedEliteSessionCount +=
                    eliteSessionsForDay.size

                validatedEliteActiveSeconds +=
                    eliteSessionsForDay.sumOf { session ->
                        session.durationSeconds
                            .coerceAtLeast(0)
                    }
            }

            val sessionsUsedForValidation =
                when {
                    requiresFoundationSession -> {
                        foundationSessionsForDay
                    }

                    requiresEliteSessions -> {
                        eliteSessionsForDay
                    }

                    else -> {
                        sessionsForDay
                    }
                }

            val dailySessionCount =
                sessionsUsedForValidation.size

            val dailyDurationSeconds =
                sessionsUsedForValidation.sumOf { session ->
                    session.durationSeconds
                        .coerceAtLeast(0)
                }

            val dailyRepetitionCount =
                repetitionsForDay.sumOf { repetition ->
                    repetition.repetitionCount
                        .coerceAtLeast(0)
                }

            val validTrainingSessionIds =
                sessionsForDay
                    .map { session ->
                        session.id
                    }
                    .filter { sessionId ->
                        sessionId > 0L
                    }
                    .toSet()

            val kickSessionAggregates =
                buildKickSessionAggregates(
                    repetitions =
                        repetitionsForDay,
                    validTrainingSessionIds =
                        validTrainingSessionIds
                )

            val highestKickCountInOneSession =
                kickSessionAggregates
                    .maxOfOrNull { aggregate ->
                        aggregate.repetitionCount
                    }
                    ?: 0

            if (requiresSingleKickSession) {
                bestSingleKickSessionRepetitions =
                    maxOf(
                        bestSingleKickSessionRepetitions,
                        highestKickCountInOneSession
                    )

                validatedKickSessionCount +=
                    kickSessionAggregates.size

                validatedKickActiveSeconds +=
                    kickSessionAggregates.sumOf { aggregate ->
                        aggregate.activeSeconds
                    }
            }

            val sessionsRequirementMet =
                if (requiresSingleKickSession) {
                    kickSessionAggregates.size >=
                            definition
                                .requiredSessionsPerDay
                } else {
                    dailySessionCount >=
                            definition
                                .requiredSessionsPerDay
                }

            val minutesRequirementMet =
                dailyDurationSeconds >=
                        definition
                            .requiredActiveMinutesPerDay *
                        60

            val repetitionsRequirementMet =
                if (requiresSingleKickSession) {
                    highestKickCountInOneSession >=
                            definition
                                .requiredRepetitionsPerDay
                } else {
                    dailyRepetitionCount >=
                            definition
                                .requiredRepetitionsPerDay
                }

            val dayCompleted =
                sessionsRequirementMet &&
                        minutesRequirementMet &&
                        repetitionsRequirementMet

            if (dayCompleted) {
                completedDays += 1

                if (!missedRequiredPastDay) {
                    consecutiveCompletedDays += 1
                }
            } else {
                val isPastDay =
                    date.isBefore(today)

                if (
                    definition.requiresConsecutiveDays &&
                    isPastDay
                ) {
                    missedRequiredPastDay = true
                }
            }
        }

        val totalValidatedSessions =
            when {
                requiresSingleKickSession -> {
                    validatedKickSessionCount
                }

                requiresFoundationSession -> {
                    validatedFoundationSessionCount
                }

                requiresEliteSessions -> {
                    validatedEliteSessionCount
                }

                else -> {
                    sessions.size
                }
            }

        val totalValidatedMinutes =
            when {
                requiresSingleKickSession -> {
                    validatedKickActiveSeconds / 60
                }

                requiresFoundationSession -> {
                    validatedFoundationActiveSeconds / 60
                }

                requiresEliteSessions -> {
                    validatedEliteActiveSeconds / 60
                }

                else -> {
                    sessions.sumOf { session ->
                        session.durationSeconds
                            .coerceAtLeast(0)
                    } / 60
                }
            }

        val totalValidatedRepetitions =
            if (requiresSingleKickSession) {
                bestSingleKickSessionRepetitions
            } else {
                validKickRepetitions.sumOf { repetition ->
                    repetition.repetitionCount
                        .coerceAtLeast(0)
                }
            }

        val integrityStatus =
            progressEntity
                .integrityStatus
                .toIntegrityStatus()

        val challengeCompleted =
            completedDays >=
                    definition.durationDays &&
                    integrityStatus ==
                    ChallengeIntegrityStatus.VALID

        val challengeExpired =
            today.isAfter(
                finalChallengeDate
            )

        val newStatus =
            when {
                integrityStatus ==
                        ChallengeIntegrityStatus.INVALID -> {

                    ChallengeStatus.FAILED
                }

                challengeCompleted -> {
                    ChallengeStatus.COMPLETED
                }

                missedRequiredPastDay -> {
                    ChallengeStatus.FAILED
                }

                challengeExpired -> {
                    ChallengeStatus.FAILED
                }

                else -> {
                    ChallengeStatus.ACTIVE
                }
            }

        val currentDay =
            (
                    ChronoUnit.DAYS.between(
                        startDate,
                        today
                    ).toInt() + 1
                    ).coerceIn(
                    minimumValue = 1,
                    maximumValue =
                        definition.durationDays
                )

        val updatedProgress =
            ChallengeProgress(
                challengeId =
                    progressEntity.challengeId,
                status =
                    newStatus,
                integrityStatus =
                    integrityStatus,
                startedAtEpochDay =
                    startedAtEpochDay,
                completedAtEpochDay =
                    if (
                        newStatus ==
                        ChallengeStatus.COMPLETED
                    ) {
                        today.toEpochDay()
                    } else {
                        null
                    },
                currentDay =
                    currentDay,
                completedDays =
                    completedDays.coerceAtMost(
                        definition.durationDays
                    ),
                currentStreakDays =
                    consecutiveCompletedDays,
                totalValidatedSessions =
                    totalValidatedSessions,
                totalValidatedMinutes =
                    totalValidatedMinutes,
                totalValidatedRepetitions =
                    if (
                        definition
                            .requiredRepetitionsPerDay >
                        0
                    ) {
                        totalValidatedRepetitions
                    } else {
                        progressEntity
                            .totalValidatedRepetitions
                            .coerceAtLeast(0)
                    }
            )

        challengeProgressDao.saveProgress(
            ChallengeProgressEntity
                .fromChallengeProgress(
                    progress =
                        updatedProgress,
                    rewardClaimed =
                        progressEntity.rewardClaimed,
                    createdAtEpochMs =
                        progressEntity.createdAtEpochMs
                )
        )
    }
}

private data class KickSessionAggregate(
    val repetitionCount: Int,
    val activeSeconds: Int
)

private fun buildKickSessionAggregates(
    repetitions: List<TrainingRepetitionEntity>,
    validTrainingSessionIds: Set<Long>
): List<KickSessionAggregate> {
    val linkedSessionAggregates =
        repetitions
            .filter { repetition ->
                val sessionId =
                    repetition.trainingSessionId

                sessionId != null &&
                        sessionId in
                        validTrainingSessionIds
            }
            .groupBy { repetition ->
                requireNotNull(
                    repetition.trainingSessionId
                )
            }
            .values
            .map { sessionRepetitions ->
                KickSessionAggregate(
                    repetitionCount =
                        sessionRepetitions.sumOf { repetition ->
                            repetition.repetitionCount
                                .coerceAtLeast(0)
                        },
                    activeSeconds =
                        sessionRepetitions.sumOf { repetition ->
                            repetition.activeSeconds
                                .coerceAtLeast(0)
                        }
                )
            }

    val standaloneSessionAggregates =
        repetitions
            .filter { repetition ->
                repetition.trainingSessionId ==
                        null
            }
            .map { repetition ->
                KickSessionAggregate(
                    repetitionCount =
                        repetition.repetitionCount
                            .coerceAtLeast(0),
                    activeSeconds =
                        repetition.activeSeconds
                            .coerceAtLeast(0)
                )
            }

    return linkedSessionAggregates +
            standaloneSessionAggregates
}

private fun String.toIntegrityStatus():
        ChallengeIntegrityStatus {

    return runCatching {
        ChallengeIntegrityStatus.valueOf(
            this
        )
    }.getOrDefault(
        ChallengeIntegrityStatus.VALID
    )
}