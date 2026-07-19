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
        ProgressPhotoEntity::class,
        FightGearAnalyticsEventEntity::class,
        ChallengeProgressEntity::class,
        TrainingRepetitionEntity::class
    ],
    version = 13,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao

    abstract fun weightDao(): WeightDao

    abstract fun comboLibraryDao(): ComboLibraryDao

    abstract fun trainingSessionDao(): TrainingSessionDao

    abstract fun progressPhotoDao(): ProgressPhotoDao

    abstract fun fightGearAnalyticsDao(): FightGearAnalyticsDao

    abstract fun challengeProgressDao(): ChallengeProgressDao

    abstract fun trainingRepetitionDao(): TrainingRepetitionDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_6_7 =
            object : Migration(6, 7) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
                    db.execSQL(
                        """
                        ALTER TABLE user_profile
                        ADD COLUMN introSeen INTEGER NOT NULL DEFAULT 0
                        """.trimIndent()
                    )
                }
            }

        private val MIGRATION_7_8 =
            object : Migration(7, 8) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
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

        private val MIGRATION_8_9 =
            object : Migration(8, 9) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
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

        private val MIGRATION_9_10 =
            object : Migration(9, 10) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
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

        private val MIGRATION_10_11 =
            object : Migration(10, 11) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
                    db.execSQL(
                        """
                        CREATE TABLE IF NOT EXISTS `fight_gear_analytics_events` (
                            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            `eventType` TEXT NOT NULL,
                            `productId` TEXT NOT NULL,
                            `productName` TEXT NOT NULL,
                            `category` TEXT NOT NULL,
                            `retailerName` TEXT NOT NULL,
                            `isAffiliateLink` INTEGER NOT NULL,
                            `countryCode` TEXT NOT NULL,
                            `createdAtEpochMs` INTEGER NOT NULL
                        )
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_fight_gear_analytics_events_eventType`
                        ON `fight_gear_analytics_events` (`eventType`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_fight_gear_analytics_events_productId`
                        ON `fight_gear_analytics_events` (`productId`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_fight_gear_analytics_events_createdAtEpochMs`
                        ON `fight_gear_analytics_events` (`createdAtEpochMs`)
                        """.trimIndent()
                    )
                }
            }

        private val MIGRATION_11_12 =
            object : Migration(11, 12) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
                    db.execSQL(
                        """
                        CREATE TABLE IF NOT EXISTS `challenge_progress` (
                            `challengeId` TEXT NOT NULL,
                            `status` TEXT NOT NULL,
                            `integrityStatus` TEXT NOT NULL,
                            `startedAtEpochDay` INTEGER,
                            `completedAtEpochDay` INTEGER,
                            `currentDay` INTEGER NOT NULL,
                            `completedDays` INTEGER NOT NULL,
                            `currentStreakDays` INTEGER NOT NULL,
                            `totalValidatedSessions` INTEGER NOT NULL,
                            `totalValidatedMinutes` INTEGER NOT NULL,
                            `totalValidatedRepetitions` INTEGER NOT NULL,
                            `rewardClaimed` INTEGER NOT NULL,
                            `createdAtEpochMs` INTEGER NOT NULL,
                            `updatedAtEpochMs` INTEGER NOT NULL,
                            PRIMARY KEY(`challengeId`)
                        )
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_challenge_progress_status`
                        ON `challenge_progress` (`status`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_challenge_progress_startedAtEpochDay`
                        ON `challenge_progress` (`startedAtEpochDay`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_challenge_progress_updatedAtEpochMs`
                        ON `challenge_progress` (`updatedAtEpochMs`)
                        """.trimIndent()
                    )
                }
            }

        private val MIGRATION_12_13 =
            object : Migration(12, 13) {
                override fun migrate(
                    db: SupportSQLiteDatabase
                ) {
                    db.execSQL(
                        """
                        CREATE TABLE IF NOT EXISTS `training_repetitions` (
                            `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            `trainingSessionId` INTEGER,
                            `localDate` TEXT NOT NULL,
                            `recordedAtEpochMs` INTEGER NOT NULL,
                            `sessionType` TEXT NOT NULL,
                            `sport` TEXT NOT NULL,
                            `movementType` TEXT NOT NULL,
                            `repetitionCount` INTEGER NOT NULL,
                            `activeSeconds` INTEGER NOT NULL,
                            `integrityStatus` TEXT NOT NULL
                        )
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_training_repetitions_localDate`
                        ON `training_repetitions` (`localDate`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_training_repetitions_movementType`
                        ON `training_repetitions` (`movementType`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_training_repetitions_trainingSessionId`
                        ON `training_repetitions` (`trainingSessionId`)
                        """.trimIndent()
                    )

                    db.execSQL(
                        """
                        CREATE INDEX IF NOT EXISTS
                        `index_training_repetitions_recordedAtEpochMs`
                        ON `training_repetitions` (`recordedAtEpochMs`)
                        """.trimIndent()
                    )
                }
            }

        fun get(
            context: Context
        ): AppDatabase {
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
                        MIGRATION_9_10,
                        MIGRATION_10_11,
                        MIGRATION_11_12,
                        MIGRATION_12_13
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { database ->
                        INSTANCE = database
                    }
            }
        }
    }
}