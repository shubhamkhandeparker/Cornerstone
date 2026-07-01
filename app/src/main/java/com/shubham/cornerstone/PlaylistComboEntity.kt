package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "playlist_combo")
data class PlaylistComboEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val playlistId: Long,
    val position: Int,

    val phase: String,
    val moves: String,
    val cue: String,

    val createdAtEpochMs: Long = System.currentTimeMillis()
)