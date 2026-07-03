package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_session")
data class TrainingSessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // "ai" or "playlist"
    val sessionType: String,

    val sport: String,

    val comboCount: Int,

    // 0 means tap-to-advance mode
    val secondsPerCombo: Int,

    // estimated training duration
    val durationSeconds: Int,

    // Example: "2026-07-04"
    val localDate: String,

    val finishedAtEpochMs: Long = System.currentTimeMillis()
)