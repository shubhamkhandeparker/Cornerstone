package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrainingSessionRepository(
    private val dao: TrainingSessionDao
) {

    fun observeTodaySessionCount(): Flow<Int> {
        return dao.observeSessionCountForDate(
            localDate = dateString(
                LocalDate.now()
            )
        )
    }

    fun observeTodayDurationSeconds(): Flow<Int> {
        return dao.observeTotalDurationSecondsForDate(
            localDate = dateString(
                LocalDate.now()
            )
        )
    }

    fun observeRecentSessions():
            Flow<List<TrainingSessionEntity>> {

        return dao.observeRecentSessions()
    }

    fun observeSessionsForDate(
        date: LocalDate
    ): Flow<List<TrainingSessionEntity>> {
        return dao.observeSessionsForDate(
            localDate = dateString(date)
        )
    }

    suspend fun getSessionsForDate(
        date: LocalDate
    ): List<TrainingSessionEntity> {
        return dao.getSessionsForDate(
            localDate = dateString(date)
        )
    }

    suspend fun getSessionsBetweenDates(
        startDate: LocalDate,
        endDate: LocalDate
    ): List<TrainingSessionEntity> {
        require(!endDate.isBefore(startDate)) {
            "End date cannot be before start date."
        }

        return dao.getSessionsBetweenDates(
            startDate = dateString(startDate),
            endDate = dateString(endDate)
        )
    }

    suspend fun getTodaySessions():
            List<TrainingSessionEntity> {

        return getSessionsForDate(
            date = LocalDate.now()
        )
    }

    suspend fun logFinishedSession(
        sessionType: String,
        sport: String,
        comboCount: Int,
        secondsPerCombo: Int,
        activeTrainingSeconds: Int? = null
    ): Long {
        val safeComboCount =
            comboCount.coerceAtLeast(0)

        val safeSecondsPerCombo =
            secondsPerCombo.coerceAtLeast(0)

        val durationSeconds =
            activeTrainingSeconds
                ?.coerceAtLeast(0)
                ?: calculateEstimatedDuration(
                    comboCount =
                        safeComboCount,
                    secondsPerCombo =
                        safeSecondsPerCombo
                )

        val session =
            TrainingSessionEntity(
                sessionType =
                    sessionType.trim()
                        .ifBlank { "training" },
                sport =
                    sport.trim()
                        .ifBlank { "Boxing" },
                comboCount =
                    safeComboCount,
                secondsPerCombo =
                    safeSecondsPerCombo,
                durationSeconds =
                    durationSeconds,
                localDate =
                    dateString(LocalDate.now())
            )

        return dao.insertSession(session)
    }

    private fun calculateEstimatedDuration(
        comboCount: Int,
        secondsPerCombo: Int
    ): Int {
        if (comboCount <= 0) {
            return 0
        }

        return if (secondsPerCombo == 0) {
            /*
             * Legacy fallback for callers that do not yet
             * provide accurate active training time.
             */
            comboCount * 15
        } else {
            comboCount * secondsPerCombo
        }
    }

    private fun dateString(
        date: LocalDate
    ): String {
        return date.toString()
    }
}