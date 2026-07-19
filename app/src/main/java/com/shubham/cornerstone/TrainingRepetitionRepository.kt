package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import kotlin.math.ceil

private const val MOVEMENT_TYPE_KICK = "KICK"

/*
 * A generous upper limit used only to prevent obviously
 * impossible repetition records from counting.
 *
 * Genuine fast kicking drills should remain valid.
 */
private const val MAX_VALID_KICKS_PER_SECOND = 4

class TrainingRepetitionRepository(
    private val dao: TrainingRepetitionDao
) {

    fun observeTodayValidKickCount(): Flow<Int> {
        return dao.observeTotalValidRepetitionsForDate(
            localDate = dateString(
                LocalDate.now()
            ),
            movementType =
                MOVEMENT_TYPE_KICK
        )
    }

    fun observeValidKickCountForDate(
        date: LocalDate
    ): Flow<Int> {
        return dao.observeTotalValidRepetitionsForDate(
            localDate = dateString(date),
            movementType =
                MOVEMENT_TYPE_KICK
        )
    }

    suspend fun getTodayValidKickCount(): Int {
        return getValidKickCountForDate(
            date = LocalDate.now()
        )
    }

    suspend fun getValidKickCountForDate(
        date: LocalDate
    ): Int {
        return dao.getTotalValidRepetitionsForDate(
            localDate = dateString(date),
            movementType =
                MOVEMENT_TYPE_KICK
        )
    }

    suspend fun getValidKickCountBetweenDates(
        startDate: LocalDate,
        endDate: LocalDate
    ): Int {
        require(!endDate.isBefore(startDate)) {
            "End date cannot be before start date."
        }

        return dao
            .getTotalValidRepetitionsBetweenDates(
                startDate =
                    dateString(startDate),
                endDate =
                    dateString(endDate),
                movementType =
                    MOVEMENT_TYPE_KICK
            )
    }

    suspend fun getRepetitionsForDate(
        date: LocalDate
    ): List<TrainingRepetitionEntity> {
        return dao.getRepetitionsForDate(
            localDate = dateString(date)
        )
    }

    suspend fun getRepetitionsBetweenDates(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<TrainingRepetitionEntity> {
        require(!endDate.isBefore(startDate)) {
            "End date cannot be before start date."
        }

        return dao.getRepetitionsBetweenDates(
            startDate =
                dateString(startDate),
            endDate =
                dateString(endDate)
        )
    }

    suspend fun logKickRepetitions(
        sessionType: String,
        sport: String,
        repetitionCount: Int,
        activeSeconds: Int,
        trainingSessionId: Long? = null
    ): Long {
        val safeRepetitionCount =
            repetitionCount.coerceAtLeast(0)

        val safeActiveSeconds =
            activeSeconds.coerceAtLeast(0)

        val integrityStatus =
            determineIntegrityStatus(
                repetitionCount =
                    safeRepetitionCount,
                activeSeconds =
                    safeActiveSeconds
            )

        val repetition =
            TrainingRepetitionEntity(
                trainingSessionId =
                    trainingSessionId,
                localDate =
                    dateString(LocalDate.now()),
                sessionType =
                    sessionType
                        .trim()
                        .ifBlank {
                            "challenge"
                        },
                sport =
                    sport
                        .trim()
                        .ifBlank {
                            "Boxing"
                        },
                movementType =
                    MOVEMENT_TYPE_KICK,
                repetitionCount =
                    safeRepetitionCount,
                activeSeconds =
                    safeActiveSeconds,
                integrityStatus =
                    integrityStatus.name
            )

        return dao.insertRepetition(
            repetition = repetition
        )
    }

    suspend fun deleteForTrainingSession(
        trainingSessionId: Long
    ) {
        if (trainingSessionId <= 0L) {
            return
        }

        dao.deleteForTrainingSession(
            trainingSessionId =
                trainingSessionId
        )
    }

    private fun determineIntegrityStatus(
        repetitionCount: Int,
        activeSeconds: Int
    ): ChallengeIntegrityStatus {
        if (
            repetitionCount <= 0 ||
            activeSeconds <= 0
        ) {
            return ChallengeIntegrityStatus.INVALID
        }

        val minimumPlausibleSeconds =
            ceil(
                repetitionCount.toDouble() /
                        MAX_VALID_KICKS_PER_SECOND
            ).toInt()

        return if (
            activeSeconds <
            minimumPlausibleSeconds
        ) {
            ChallengeIntegrityStatus.SUSPICIOUS
        } else {
            ChallengeIntegrityStatus.VALID
        }
    }

    private fun dateString(
        date: LocalDate
    ): String {
        return date.toString()
    }
}