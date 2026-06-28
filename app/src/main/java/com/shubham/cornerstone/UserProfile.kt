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
    val sport: String = "Boxing",            // Boxing / Muay Thai / MMA
    val level: String = "Beginner",          // Beginner / Intermediate / Advanced
    val dominance: String = "Striker",       // Striker / Grappler / All-rounder
    val stance: String = "Orthodox",         // Orthodox / Southpaw

    // --- Learned passively over time ---
    val currentWeightKg: Float? = null,      // null until first logged
    val targetWeightKg: Float? = null,       // fight-day target
    val fightDateEpochDay: Long? = null,     // null = no fight booked
    val weightUnit: String = "kg",           // "kg" or "lbs"

    // --- Progress ---
    val sessionsCompleted: Int = 0,

    // --- Monetization ---
    val isPro: Boolean = false,              // unlocked the paid weight-cut feature

    // --- Housekeeping ---
    val onboardingComplete: Boolean = false,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)