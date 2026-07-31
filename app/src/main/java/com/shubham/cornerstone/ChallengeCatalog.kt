package com.shubham.cornerstone

object ChallengeCatalog {

    const val BEGINNER_STREAK_30_ID =
        "beginner_streak_30"

    const val ONE_HUNDRED_KICKS_ID =
        "one_hundred_kicks"

    const val FOUNDATION_14_ID =
        "foundation_14"

    const val BODY_CONDITIONING_21_ID =
        "body_conditioning_21"

    const val CORE_14_ID =
        "core_14"

    const val ELITE_75_ID =
        "elite_75"

    val challenges: List<ChallengeDefinition> =
        listOf(
            beginnerStreak30(),
            oneHundredKicks(),
            foundation14(),
            bodyConditioning21(),
            core14(),
            elite75()
        )

    fun getChallenge(
        challengeId: String
    ): ChallengeDefinition? {
        return challenges.firstOrNull { challenge ->
            challenge.id == challengeId
        }
    }

    fun activeChallenges():
            List<ChallengeDefinition> {

        return challenges.filter { challenge ->
            challenge.isActive
        }
    }

    fun challengesForCategory(
        category: ChallengeCategory
    ): List<ChallengeDefinition> {
        return activeChallenges().filter { challenge ->
            challenge.category == category
        }
    }

    private fun beginnerStreak30():
            ChallengeDefinition {

        return ChallengeDefinition(
            id = BEGINNER_STREAK_30_ID,
            title = "30-Day Beginner Streak",
            shortDescription =
                "Train for at least 20 active minutes every day.",
            fullDescription =
                "Complete at least 20 validated active training minutes every day for 30 consecutive days. Missing a required day breaks the streak.",
            category =
                ChallengeCategory.STREAK,
            difficulty =
                ChallengeDifficulty.BEGINNER,
            durationDays = 30,
            requiredSessionsPerDay = 1,
            requiredActiveMinutesPerDay = 20,
            requiredRepetitionsPerDay = 0,
            requiresConsecutiveDays = true,
            reward = ChallengeReward(
                type =
                    ChallengeRewardType.PRO_PASS,
                proPassDays = 30
            ),
            isProOnly = false,
            isActive = true
        )
    }

    private fun oneHundredKicks():
            ChallengeDefinition {

        return ChallengeDefinition(
            id = ONE_HUNDRED_KICKS_ID,
            title = "100 Kicks",
            shortDescription =
                "Complete 100 controlled kicks in one session.",
            fullDescription =
                "Complete 100 technically controlled kicks in one validated session. Split attempts do not complete the challenge. Use balanced sets, maintain proper form and stop if pain or loss of control develops.",
            category =
                ChallengeCategory.KICKS,
            difficulty =
                ChallengeDifficulty.BEGINNER,
            durationDays = 1,
            requiredSessionsPerDay = 1,
            requiredActiveMinutesPerDay = 0,
            requiredRepetitionsPerDay = 100,
            requiresConsecutiveDays = false,
            reward = ChallengeReward(
                type =
                    ChallengeRewardType.POINTS,
                points = 100
            ),
            isProOnly = false,
            isActive = true
        )
    }

    private fun foundation14():
            ChallengeDefinition {

        return ChallengeDefinition(
            id = FOUNDATION_14_ID,
            title = "14-Day Foundation",
            shortDescription =
                "Build consistency with basic striking sessions.",
            fullDescription =
                "Complete one validated striking session lasting at least 15 active minutes each day for 14 consecutive days. Prioritize stance, guard, footwork, straight punches and defensive movement.",
            category =
                ChallengeCategory.TECHNIQUE,
            difficulty =
                ChallengeDifficulty.BEGINNER,
            durationDays = 14,
            requiredSessionsPerDay = 1,
            requiredActiveMinutesPerDay = 15,
            requiredRepetitionsPerDay = 0,
            requiresConsecutiveDays = true,
            reward = ChallengeReward(
                type =
                    ChallengeRewardType.POINTS,
                points = 250
            ),
            isProOnly = false,
            isActive = true
        )
    }

    private fun bodyConditioning21():
            ChallengeDefinition {

        return ChallengeDefinition(
            id = BODY_CONDITIONING_21_ID,
            title = "21-Day Conditioning",
            shortDescription =
                "Improve work capacity through consistent training.",
            fullDescription =
                "Complete at least one validated conditioning workout for 21 consecutive days. Recovery, hydration and safe intensity remain part of the challenge.",
            category =
                ChallengeCategory.CONDITIONING,
            difficulty =
                ChallengeDifficulty.INTERMEDIATE,
            durationDays = 21,
            requiredSessionsPerDay = 1,
            requiredActiveMinutesPerDay = 25,
            requiredRepetitionsPerDay = 0,
            requiresConsecutiveDays = true,
            reward = ChallengeReward(
                type =
                    ChallengeRewardType.POINTS,
                points = 500
            ),
            isProOnly = false,
            isActive = false
        )
    }

    private fun core14():
            ChallengeDefinition {

        return ChallengeDefinition(
            id = CORE_14_ID,
            title = "14-Day Fighter Core",
            shortDescription =
                "Develop consistent core-conditioning habits.",
            fullDescription =
                "Complete one validated core-focused workout lasting at least 10 active minutes each day for 14 consecutive days. Use controlled repetitions and avoid continuing through sharp pain.",
            category =
                ChallengeCategory.CORE,
            difficulty =
                ChallengeDifficulty.INTERMEDIATE,
            durationDays = 14,
            requiredSessionsPerDay = 1,
            requiredActiveMinutesPerDay = 10,
            requiredRepetitionsPerDay = 0,
            requiresConsecutiveDays = true,
            reward = ChallengeReward(
                type =
                    ChallengeRewardType.POINTS,
                points = 300
            ),
            isProOnly = false,
            isActive = false
        )
    }

    private fun elite75():
            ChallengeDefinition {

        return ChallengeDefinition(
            id = ELITE_75_ID,
            title = "75-Day Elite",
            shortDescription =
                "Complete three separate one-hour sessions every day.",
            fullDescription =
                "Complete three separate validated training sessions every day for 75 consecutive days. Each session must include at least 60 active training minutes. Shorter sessions cannot be combined to satisfy the requirement. This challenge is intended for experienced athletes and requires planned recovery and responsible intensity.",
            category =
                ChallengeCategory.ELITE,
            difficulty =
                ChallengeDifficulty.ELITE,
            durationDays = 75,
            requiredSessionsPerDay = 3,
            requiredActiveMinutesPerDay = 180,
            requiredRepetitionsPerDay = 0,
            requiresConsecutiveDays = true,
            reward = ChallengeReward(
                type =
                    ChallengeRewardType.PRO_PASS,
                proPassDays = 30
            ),
            isProOnly = false,
            isActive = true
        )
    }
}