package com.shubham.cornerstone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The app's single local database.
 * Holds all tables and hands out DAOs to talk to them.
 */
@Database(
    entities = [UserProfile::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Returns the one shared database instance (creates it once).
        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cornerstone.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}