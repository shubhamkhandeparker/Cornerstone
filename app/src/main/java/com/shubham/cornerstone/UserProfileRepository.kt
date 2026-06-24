package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow

/**
 * The single source of truth for profile data.
 * Screens and ViewModels talk to THIS, never to the DAO directly.
 */
class UserProfileRepository(private val dao: UserProfileDao) {

    // Live stream of the profile — UI reacts automatically to changes.
    val profile: Flow<UserProfile?> = dao.observeProfile()

    suspend fun getProfile(): UserProfile? = dao.getProfile()

    suspend fun saveProfile(profile: UserProfile) = dao.saveProfile(profile)

    // Convenience: save the onboarding answers and mark it done.
    suspend fun completeOnboarding(
        sport: String,
        level: String,
        dominance: String,
        stance: String
    ) {
        val existing = dao.getProfile() ?: UserProfile()
        dao.saveProfile(
            existing.copy(
                sport = sport,
                level = level,
                dominance = dominance,
                stance = stance,
                onboardingComplete = true
            )
        )
    }

    // Called when a training session finishes — increments the counter.
    suspend fun incrementSessionsCompleted() {
        val existing = dao.getProfile() ?: return
        dao.saveProfile(
            existing.copy(sessionsCompleted = existing.sessionsCompleted + 1)
        )
    }
}