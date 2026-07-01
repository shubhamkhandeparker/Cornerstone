package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "combo_playlist")
data class ComboPlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    val createdAtEpochMs: Long = System.currentTimeMillis(),
    val updatedAtEpochMs: Long = System.currentTimeMillis()
)