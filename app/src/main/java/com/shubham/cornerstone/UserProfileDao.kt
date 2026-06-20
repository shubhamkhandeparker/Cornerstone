package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * The commands for reading and writing the user's profile.
 * Room generates the actual code behind these at build time.
 */
@Dao
interface UserProfileDao {

    // Watch the profile live — UI updates automatically when it changes.
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun observeProfile(): Flow<UserProfile?>

    // Read the profile once (e.g. on app start).
    @Query("SELECT * FROM user_profile WHERE id = 1")
    suspend fun getProfile(): UserProfile?

    // Save or update the profile. Replaces the single row if it exists.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(profile: UserProfile)
}