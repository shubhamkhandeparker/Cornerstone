package com.shubham.cornerstone

enum class ChallengeCategory(
    val displayName: String
) {
    STREAK("Streak"),
    SHADOWBOXING("Shadowboxing"),
    TECHNIQUE("Technique"),
    CONDITIONING("Conditioning"),
    KICKS("Kicks"),
    CORE("Core"),
    ELITE("Elite")
}

enum class ChallengeDifficulty(
    val displayName: String
) {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced"),
    ELITE("Elite")
}

enum class ChallengeStatus {
    NOT_STARTED,
    ACTIVE,
    COMPLETED,
    FAILED,
    ABANDONED
}

enum class ChallengeIntegrityStatus {
    VALID,
    SUSPICIOUS,
    INVALID
}

enum class ChallengeRewardType {
    NONE,
    POINTS,
    PRO_PASS
}

data class ChallengeReward(
    val type: ChallengeRewardType,
    val points: Int = 0,
    val proPassDays: Int = 0
) {
    init {
        require(points >= 0) {
            "Reward points cannot be negative."
        }

        require(proPassDays >= 0) {
            "Pro Pass duration cannot be negative."
        }
    }
}

data class ChallengeDefinition(
    val id: String,
    val title: String,
    val shortDescription: String,
    val fullDescription: String,
    val category: ChallengeCategory,
    val difficulty: ChallengeDifficulty,
    val durationDays: Int,
    val requiredSessionsPerDay: Int = 1,
    val requiredActiveMinutesPerDay: Int = 0,
    val requiredRepetitionsPerDay: Int = 0,
    val requiresConsecutiveDays: Boolean = false,
    val reward: ChallengeReward,
    val isProOnly: Boolean = false,
    val isActive: Boolean = true
) {
    init {
        require(id.isNotBlank()) {
            "Challenge ID cannot be blank."
        }

        require(title.isNotBlank()) {
            "Challenge title cannot be blank."
        }

        require(durationDays > 0) {
            "Challenge duration must be greater than zero."
        }

        require(requiredSessionsPerDay >= 0) {
            "Required sessions cannot be negative."
        }

        require(requiredActiveMinutesPerDay >= 0) {
            "Required active minutes cannot be negative."
        }

        require(requiredRepetitionsPerDay >= 0) {
            "Required repetitions cannot be negative."
        }
    }
}

data class ChallengeProgress(
    val challengeId: String,
    val status: ChallengeStatus,
    val integrityStatus: ChallengeIntegrityStatus,
    val startedAtEpochDay: Long?,
    val completedAtEpochDay: Long?,
    val currentDay: Int,
    val completedDays: Int,
    val currentStreakDays: Int,
    val totalValidatedSessions: Int,
    val totalValidatedMinutes: Int,
    val totalValidatedRepetitions: Int
) {
    init {
        require(challengeId.isNotBlank()) {
            "Challenge ID cannot be blank."
        }

        require(currentDay >= 0) {
            "Current day cannot be negative."
        }

        require(completedDays >= 0) {
            "Completed days cannot be negative."
        }

        require(currentStreakDays >= 0) {
            "Current streak cannot be negative."
        }

        require(totalValidatedSessions >= 0) {
            "Validated sessions cannot be negative."
        }

        require(totalValidatedMinutes >= 0) {
            "Validated minutes cannot be negative."
        }

        require(totalValidatedRepetitions >= 0) {
            "Validated repetitions cannot be negative."
        }
    }
}