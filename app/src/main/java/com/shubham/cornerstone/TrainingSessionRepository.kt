package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrainingSessionRepository(
    private val dao: TrainingSessionDao
) {

    fun observeTodaySessionCount(): Flow<Int> {
        return dao.observeSessionCountForDate(
            localDate = todayString()
        )
    }

    fun observeTodayDurationSeconds(): Flow<Int> {
        return dao.observeTotalDurationSecondsForDate(
            localDate = todayString()
        )
    }

    fun observeRecentSessions(): Flow<List<TrainingSessionEntity>> {
        return dao.observeRecentSessions()
    }

    suspend fun logFinishedSession(
        sessionType: String,
        sport: String,
        comboCount: Int,
        secondsPerCombo: Int
    ) {
        val safeComboCount = comboCount.coerceAtLeast(1)
        val safeSeconds = secondsPerCombo.coerceAtLeast(0)

        val durationSeconds = if (safeSeconds == 0) {
            // Tap-to-advance mode does not have exact time.
            // We estimate 15 sec per combo so daily stats still feel useful.
            safeComboCount * 15
        } else {
            safeComboCount * safeSeconds
        }

        dao.insertSession(
            TrainingSessionEntity(
                sessionType = sessionType,
                sport = sport,
                comboCount = safeComboCount,
                secondsPerCombo = safeSeconds,
                durationSeconds = durationSeconds,
                localDate = todayString()
            )
        )
    }

    private fun todayString(): String {
        return LocalDate.now().toString()
    }
}