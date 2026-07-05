package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress_photo")
data class ProgressPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val filePath: String,
    val fileName: String,

    // Example: 2026-07-05
    val localDate: String,

    // Optional later: attach weight to photo
    val weightKg: Double? = null,

    val capturedAtEpochMs: Long = System.currentTimeMillis()
)