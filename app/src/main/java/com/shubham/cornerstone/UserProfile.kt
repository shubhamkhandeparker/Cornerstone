package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The fighter's profile - the core of personalization.
 * There's only ever ONE row (id = 1), updated as we learn more.
 */
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey
    val id: Int = 1,

    // --- Set during onboarding (tiny) ---
    val sport: String = "Boxing",
    val level: String = "Beginner",
    val dominance: String = "Striker",
    val stance: String = "Orthodox",

    // --- Learned passively over time ---
    val currentWeightKg: Float? = null,
    val targetWeightKg: Float? = null,
    val fightDateEpochDay: Long? = null,
    val weightUnit: String = "kg",

    // --- Progress ---
    val sessionsCompleted: Int = 0,

    // --- Monetization ---
    val isPro: Boolean = false,

    // --- First-launch flow ---
    val introSeen: Boolean = false,

    // --- Housekeeping ---
    val onboardingComplete: Boolean = false,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)