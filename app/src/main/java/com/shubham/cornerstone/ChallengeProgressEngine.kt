package com.shubham.cornerstone

import java.time.LocalDate
import java.time.temporal.ChronoUnit

private const val KICK_MOVEMENT_TYPE = "KICK"

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

        activeChallenges.forEach {
                progressEntity ->

            refreshChallenge(
                progressEntity =
                    progressEntity,
                today = today
            )
        }
    }

    private suspend fun refreshChallenge(
        progressEntity:
        ChallengeProgressEntity,
        today: LocalDate
    ) {
        val definition =
            ChallengeCatalog.getChallenge(
                challengeId =
                    progressEntity
                        .challengeId
            ) ?: return

        val startedAtEpochDay =
            progressEntity
                .startedAtEpochDay
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
                    startDate =
                        startDate,
                    endDate =
                        evaluationEndDate
                )

        val sessionsByDate =
            sessions.groupBy {
                    session ->

                session.localDate
            }

        /*
         * Repetition records are only loaded for
         * challenges that actually require them.
         *
         * Only VALID kick records count toward
         * challenge completion. Suspicious and
         * invalid records remain stored but do
         * not increase progress.
         */
        val validKickRepetitions =
            if (
                definition
                    .requiredRepetitionsPerDay >
                0
            ) {
                trainingRepetitionRepository
                    ?.getRepetitionsBetweenDates(
                        startDate =
                            startDate,
                        endDate =
                            evaluationEndDate
                    )
                    .orEmpty()
                    .filter {
                            repetition ->

                        repetition.movementType ==
                                KICK_MOVEMENT_TYPE &&
                                repetition
                                    .integrityStatus ==
                                ChallengeIntegrityStatus
                                    .VALID
                                    .name &&
                                repetition
                                    .repetitionCount >
                                0
                    }
            } else {
                emptyList()
            }

        val repetitionsByDate =
            validKickRepetitions.groupBy {
                    repetition ->

                repetition.localDate
            }

        val evaluatedDayCount =
            ChronoUnit.DAYS.between(
                startDate,
                evaluationEndDate
            ).toInt() + 1

        var completedDays = 0
        var consecutiveCompletedDays = 0
        var missedRequiredPastDay = false

        repeat(evaluatedDayCount) {
                dayIndex ->

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

            val dailySessionCount =
                sessionsForDay.size

            val dailyDurationSeconds =
                sessionsForDay.sumOf {
                        session ->

                    session.durationSeconds
                        .coerceAtLeast(0)
                }

            val dailyRepetitionCount =
                repetitionsForDay.sumOf {
                        repetition ->

                    repetition.repetitionCount
                        .coerceAtLeast(0)
                }

            val sessionsRequirementMet =
                dailySessionCount >=
                        definition
                            .requiredSessionsPerDay

            val minutesRequirementMet =
                dailyDurationSeconds >=
                        definition
                            .requiredActiveMinutesPerDay *
                        60

            val repetitionsRequirementMet =
                dailyRepetitionCount >=
                        definition
                            .requiredRepetitionsPerDay

            val dayCompleted =
                sessionsRequirementMet &&
                        minutesRequirementMet &&
                        repetitionsRequirementMet

            if (dayCompleted) {
                completedDays += 1

                if (
                    !missedRequiredPastDay
                ) {
                    consecutiveCompletedDays +=
                        1
                }
            } else {
                val isPastDay =
                    date.isBefore(today)

                if (
                    definition
                        .requiresConsecutiveDays &&
                    isPastDay
                ) {
                    missedRequiredPastDay =
                        true
                }
            }
        }

        val totalValidatedSessions =
            sessions.size

        val totalValidatedMinutes =
            sessions.sumOf {
                    session ->

                session.durationSeconds
                    .coerceAtLeast(0)
            } / 60

        val totalValidatedRepetitions =
            validKickRepetitions.sumOf {
                    repetition ->

                repetition.repetitionCount
                    .coerceAtLeast(0)
            }

        val challengeCompleted =
            completedDays >=
                    definition.durationDays

        val challengeExpired =
            today.isAfter(
                finalChallengeDate
            )

        val newStatus =
            when {
                challengeCompleted -> {
                    ChallengeStatus
                        .COMPLETED
                }

                missedRequiredPastDay -> {
                    ChallengeStatus
                        .FAILED
                }

                challengeExpired -> {
                    ChallengeStatus
                        .FAILED
                }

                else -> {
                    ChallengeStatus
                        .ACTIVE
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
                    progressEntity
                        .challengeId,
                status =
                    newStatus,
                integrityStatus =
                    progressEntity
                        .integrityStatus
                        .toIntegrityStatus(),
                startedAtEpochDay =
                    startedAtEpochDay,
                completedAtEpochDay =
                    if (
                        newStatus ==
                        ChallengeStatus
                            .COMPLETED
                    ) {
                        today.toEpochDay()
                    } else {
                        null
                    },
                currentDay =
                    currentDay,
                completedDays =
                    completedDays
                        .coerceAtMost(
                            definition
                                .durationDays
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

        challengeProgressDao
            .saveProgress(
                ChallengeProgressEntity
                    .fromChallengeProgress(
                        progress =
                            updatedProgress,
                        rewardClaimed =
                            progressEntity
                                .rewardClaimed,
                        createdAtEpochMs =
                            progressEntity
                                .createdAtEpochMs
                    )
            )
    }
}

private fun String.toIntegrityStatus():
        ChallengeIntegrityStatus {

    return runCatching {
        ChallengeIntegrityStatus
            .valueOf(this)
    }.getOrDefault(
        ChallengeIntegrityStatus
            .VALID
    )
}