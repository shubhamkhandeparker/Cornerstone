package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_combo")
data class SavedComboEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val sport: String,
    val phase: String,
    val moves: String,
    val cue: String,

    // "generated" or "custom"
    val source: String,

    val createdAtEpochMs: Long = System.currentTimeMillis()
)