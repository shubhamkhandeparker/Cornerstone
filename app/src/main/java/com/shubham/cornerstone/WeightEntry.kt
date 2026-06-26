package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * One day's weigh-in. The primary key is the epoch-day, so logging
 * twice on the same day overwrites (one clean number per day).
 */
@Entity(tableName = "weight_entries")
data class WeightEntry(
    @PrimaryKey
    val epochDay: Long,          // days since 1970 — unique per calendar day
    val weightKg: Float,         // always stored in kg internally
    val loggedAtMs: Long = System.currentTimeMillis()
)

@Dao
interface WeightDao {

    // All entries, oldest first — drives the graph and the math.
    @Query("SELECT * FROM weight_entries ORDER BY epochDay ASC")
    fun observeAll(): Flow<List<WeightEntry>>

    // The most recent weigh-in (or null if none yet).
    @Query("SELECT * FROM weight_entries ORDER BY epochDay DESC LIMIT 1")
    suspend fun latest(): WeightEntry?

    // Save or overwrite today's weigh-in.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entry: WeightEntry)

    @Query("SELECT * FROM weight_entries WHERE epochDay = :day LIMIT 1")
    suspend fun forDay(day: Long): WeightEntry?
}