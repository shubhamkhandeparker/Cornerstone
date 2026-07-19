package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "challenge_progress",
    indices = [
        Index(value = ["status"]),
        Index(value = ["startedAtEpochDay"]),
        Index(value = ["updatedAtEpochMs"])
    ]
)
data class ChallengeProgressEntity(
    @PrimaryKey
    val challengeId: String,

    val status: String,

    val integrityStatus: String,

    val startedAtEpochDay: Long?,

    val completedAtEpochDay: Long?,

    val currentDay: Int,

    val completedDays: Int,

    val currentStreakDays: Int,

    val totalValidatedSessions: Int,

    val totalValidatedMinutes: Int,

    val totalValidatedRepetitions: Int,

    val rewardClaimed: Boolean = false,

    val createdAtEpochMs: Long =
        System.currentTimeMillis(),

    val updatedAtEpochMs: Long =
        System.currentTimeMillis()
) {

    fun toChallengeProgress(): ChallengeProgress {
        return ChallengeProgress(
            challengeId = challengeId,
            status = status.toChallengeStatus(),
            integrityStatus =
                integrityStatus.toChallengeIntegrityStatus(),
            startedAtEpochDay = startedAtEpochDay,
            completedAtEpochDay = completedAtEpochDay,
            currentDay = currentDay.coerceAtLeast(0),
            completedDays = completedDays.coerceAtLeast(0),
            currentStreakDays =
                currentStreakDays.coerceAtLeast(0),
            totalValidatedSessions =
                totalValidatedSessions.coerceAtLeast(0),
            totalValidatedMinutes =
                totalValidatedMinutes.coerceAtLeast(0),
            totalValidatedRepetitions =
                totalValidatedRepetitions.coerceAtLeast(0)
        )
    }

    companion object {

        fun fromChallengeProgress(
            progress: ChallengeProgress,
            rewardClaimed: Boolean = false,
            createdAtEpochMs: Long =
                System.currentTimeMillis()
        ): ChallengeProgressEntity {
            return ChallengeProgressEntity(
                challengeId = progress.challengeId,
                status = progress.status.name,
                integrityStatus =
                    progress.integrityStatus.name,
                startedAtEpochDay =
                    progress.startedAtEpochDay,
                completedAtEpochDay =
                    progress.completedAtEpochDay,
                currentDay =
                    progress.currentDay,
                completedDays =
                    progress.completedDays,
                currentStreakDays =
                    progress.currentStreakDays,
                totalValidatedSessions =
                    progress.totalValidatedSessions,
                totalValidatedMinutes =
                    progress.totalValidatedMinutes,
                totalValidatedRepetitions =
                    progress.totalValidatedRepetitions,
                rewardClaimed = rewardClaimed,
                createdAtEpochMs =
                    createdAtEpochMs,
                updatedAtEpochMs =
                    System.currentTimeMillis()
            )
        }
    }
}

private fun String.toChallengeStatus(): ChallengeStatus {
    return runCatching {
        ChallengeStatus.valueOf(this)
    }.getOrDefault(
        ChallengeStatus.NOT_STARTED
    )
}

private fun String.toChallengeIntegrityStatus():
        ChallengeIntegrityStatus {

    return runCatching {
        ChallengeIntegrityStatus.valueOf(this)
    }.getOrDefault(
        ChallengeIntegrityStatus.VALID
    )
}