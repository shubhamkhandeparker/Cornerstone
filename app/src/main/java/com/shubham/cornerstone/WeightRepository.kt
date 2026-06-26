package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class WeightRepository(
    private val weightDao: WeightDao,
    private val profileDao: UserProfileDao
) {

    val entries: Flow<List<WeightEntry>> = weightDao.observeAll()

    suspend fun logWeight(weightKg: Float, date: LocalDate = LocalDate.now()) {
        weightDao.upsert(
            WeightEntry(epochDay = date.toEpochDay(), weightKg = weightKg)
        )

        val profile = profileDao.getProfile() ?: UserProfile()
        profileDao.saveProfile(
            profile.copy(currentWeightKg = weightKg)
        )
    }

    suspend fun latestEntry(): WeightEntry? = weightDao.latest()

    suspend fun todayEntry(): WeightEntry? =
        weightDao.forDay(LocalDate.now().toEpochDay())

    suspend fun setPlan(
        targetKg: Float,
        fightDate: LocalDate,
        useKg: Boolean
    ) {
        val p = profileDao.getProfile() ?: UserProfile()
        profileDao.saveProfile(
            p.copy(
                targetWeightKg = targetKg,
                fightDateEpochDay = fightDate.toEpochDay(),
                weightUnit = if (useKg) "kg" else "lbs"
            )
        )
    }

    suspend fun computeStatus(): CutStatus? {
        val profile = profileDao.getProfile() ?: return null
        val target = profile.targetWeightKg ?: return null
        val fightDay = profile.fightDateEpochDay ?: return null
        val latest = weightDao.latest() ?: return null

        val today = LocalDate.now().toEpochDay()
        val daysLeft = (fightDay - today).coerceAtLeast(0)
        val weeksLeft = (daysLeft / 7f).coerceAtLeast(0.1f)

        val current = latest.weightKg
        val toLose = (current - target).coerceAtLeast(0f)

        val perDayNeeded = if (daysLeft > 0) toLose / daysLeft else toLose
        val perWeekNeeded = toLose / weeksLeft
        val weeklyPercent = if (current > 0f) perWeekNeeded / current else 0f

        val status: Pace = when {
            toLose <= 0f -> Pace.ON_WEIGHT
            daysLeft == 0L -> Pace.BEHIND
            weeklyPercent <= 0.010f -> Pace.ON_TRACK
            weeklyPercent <= 0.0125f -> Pace.AGGRESSIVE
            else -> Pace.BEHIND
        }

        return CutStatus(
            currentKg = current,
            targetKg = target,
            toLoseKg = toLose,
            daysLeft = daysLeft,
            perDayNeededKg = perDayNeeded,
            perWeekNeededKg = perWeekNeeded,
            weeklyPercent = weeklyPercent,
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
    val perWeekNeededKg: Float,
    val weeklyPercent: Float,
    val pace: Pace
)