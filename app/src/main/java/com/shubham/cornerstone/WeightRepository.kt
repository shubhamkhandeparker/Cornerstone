package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * All weight-cut logic lives here: storing weigh-ins, the plan
 * (target + fight date), and the pace math that makes this feature
 * worth paying for.
 */
class WeightRepository(
    private val weightDao: WeightDao,
    private val profileDao: UserProfileDao
) {

    val entries: Flow<List<WeightEntry>> = weightDao.observeAll()

    // --- Logging ---

    suspend fun logWeight(weightKg: Float, date: LocalDate = LocalDate.now()) {
        weightDao.upsert(
            WeightEntry(epochDay = date.toEpochDay(), weightKg = weightKg)
        )
    }

    suspend fun latestEntry(): WeightEntry? = weightDao.latest()

    suspend fun todayEntry(): WeightEntry? =
        weightDao.forDay(LocalDate.now().toEpochDay())

    // --- The plan (stored on the single user profile) ---

    suspend fun setPlan(targetKg: Float, fightDate: LocalDate) {
        val p = profileDao.getProfile() ?: UserProfile()
        profileDao.saveProfile(
            p.copy(
                targetWeightKg = targetKg,
                fightDateEpochDay = fightDate.toEpochDay()
            )
        )
    }

    // --- The math brain ---

    /**
     * Computes the current weight-cut status from the latest weigh-in,
     * the target, and the fight date. Returns null if the plan isn't set.
     */
    suspend fun computeStatus(): CutStatus? {
        val profile = profileDao.getProfile() ?: return null
        val target = profile.targetWeightKg ?: return null
        val fightDay = profile.fightDateEpochDay ?: return null
        val latest = weightDao.latest() ?: return null

        val today = LocalDate.now().toEpochDay()
        val daysLeft = (fightDay - today).coerceAtLeast(0)
        val current = latest.weightKg
        val toLose = (current - target).coerceAtLeast(0f)

        // Safe pace guideline: ~1% of bodyweight per day is an aggressive
        // upper bound; we use it only to flag "too fast".
        val perDayNeeded = if (daysLeft > 0) toLose / daysLeft else toLose

        val status: Pace = when {
            toLose <= 0f -> Pace.ON_WEIGHT
            daysLeft == 0L -> Pace.BEHIND          // fight day, still over
            perDayNeeded <= current * 0.01f -> Pace.ON_TRACK
            else -> Pace.AGGRESSIVE
        }

        return CutStatus(
            currentKg = current,
            targetKg = target,
            toLoseKg = toLose,
            daysLeft = daysLeft,
            perDayNeededKg = perDayNeeded,
            pace = status
        )
    }
}

enum class Pace { ON_WEIGHT, ON_TRACK, AGGRESSIVE, BEHIND }

data class CutStatus(
    val currentKg: Float,
    val targetKg: Float,
    val toLoseKg: Float,
    val daysLeft: Long,
    val perDayNeededKg: Float,
    val pace: Pace
)