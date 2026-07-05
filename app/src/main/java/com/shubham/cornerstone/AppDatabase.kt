package com.shubham.cornerstone

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * The app's single local database.
 * Holds all tables and hands out DAOs to talk to them.
 */
@Database(
    entities = [
        UserProfile::class,
        WeightEntry::class,
        SavedComboEntity::class,
        ComboPlaylistEntity::class,
        PlaylistComboEntity::class,
        TrainingSessionEntity::class,
        ProgressPhotoEntity::class
    ],
    version = 10,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
    abstract fun weightDao(): WeightDao
    abstract fun comboLibraryDao(): ComboLibraryDao
    abstract fun trainingSessionDao(): TrainingSessionDao
    abstract fun progressPhotoDao(): ProgressPhotoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE user_profile ADD COLUMN introSeen INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        private val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `saved_combo` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `sport` TEXT NOT NULL,
                        `phase` TEXT NOT NULL,
                        `moves` TEXT NOT NULL,
                        `cue` TEXT NOT NULL,
                        `source` TEXT NOT NULL,
                        `createdAtEpochMs` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `combo_playlist` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `name` TEXT NOT NULL,
                        `createdAtEpochMs` INTEGER NOT NULL,
                        `updatedAtEpochMs` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )

                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `playlist_combo` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `playlistId` INTEGER NOT NULL,
                        `position` INTEGER NOT NULL,
                        `phase` TEXT NOT NULL,
                        `moves` TEXT NOT NULL,
                        `cue` TEXT NOT NULL,
                        `createdAtEpochMs` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        private val MIGRATION_8_9 = object : Migration(8, 9) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `training_session` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `sessionType` TEXT NOT NULL,
                        `sport` TEXT NOT NULL,
                        `comboCount` INTEGER NOT NULL,
                        `secondsPerCombo` INTEGER NOT NULL,
                        `durationSeconds` INTEGER NOT NULL,
                        `localDate` TEXT NOT NULL,
                        `finishedAtEpochMs` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        private val MIGRATION_9_10 = object : Migration(9, 10) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `progress_photo` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `filePath` TEXT NOT NULL,
                        `fileName` TEXT NOT NULL,
                        `localDate` TEXT NOT NULL,
                        `weightKg` REAL,
                        `capturedAtEpochMs` INTEGER NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cornerstone.db"
                )
                    .addMigrations(
                        MIGRATION_6_7,
                        MIGRATION_7_8,
                        MIGRATION_8_9,
                        MIGRATION_9_10
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}