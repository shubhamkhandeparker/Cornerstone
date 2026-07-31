package com.shubham.cornerstone

import androidx.room.Entity

/**
 * Stores the fighter's overall progression for one combat-sport path.
 *
 * One row exists per sport:
 * - Boxing
 * - Kickboxing
 * - Muay Thai
 * - MMA
 *
 * Individual lesson completion will be stored separately so XP and
 * lesson rewards cannot accidentally be awarded twice.
 */
@Entity(
    tableName = "training_path_progress",
    primaryKeys = ["sport"]
)
data class TrainingPathProgressEntity(

    /**
     * Boxing
     * Kickboxing
     * Muay Thai
     * MMA
     */
    val sport: String,

    /**
     * Progression stage.
     *
     * BEGINNER
     * FUNDAMENTALS
     * DEVELOPING
     * INTERMEDIATE
     * ADVANCED
     */
    val level: String = TrainingPathLevel.BEGINNER.name,

    /**
     * Current curriculum chapter.
     *
     * Example:
     * boxing_beginner_chapter_1
     */
    val currentChapterId: String,

    /**
     * Current lesson inside the chapter.
     *
     * Example:
     * boxing_beginner_chapter_1_lesson_1
     */
    val currentLessonId: String,

    /**
     * Total progression XP earned in this sport.
     */
    val xp: Int = 0,

    /**
     * Number of consecutive training days.
     */
    val currentStreakDays: Int = 0,

    /**
     * Best streak ever achieved in this sport.
     */
    val longestStreakDays: Int = 0,

    /**
     * Last day on which a valid structured lesson was completed.
     *
     * Uses LocalDate.toEpochDay().
     */
    val lastCompletedEpochDay: Long? = null,

    /**
     * Total structured lessons completed.
     */
    val totalLessonsCompleted: Int = 0,

    /**
     * Total chapters completed.
     */
    val totalChaptersCompleted: Int = 0,

    /**
     * Used later if we change the curriculum structure.
     */
    val curriculumVersion: Int = 1,

    val createdAtEpochMs: Long =
        System.currentTimeMillis(),

    val updatedAtEpochMs: Long =
        System.currentTimeMillis()
) {

    init {
        require(sport.isNotBlank()) {
            "Sport cannot be blank."
        }

        require(currentChapterId.isNotBlank()) {
            "Current chapter ID cannot be blank."
        }

        require(currentLessonId.isNotBlank()) {
            "Current lesson ID cannot be blank."
        }

        require(xp >= 0) {
            "XP cannot be negative."
        }

        require(currentStreakDays >= 0) {
            "Current streak cannot be negative."
        }

        require(longestStreakDays >= 0) {
            "Longest streak cannot be negative."
        }

        require(totalLessonsCompleted >= 0) {
            "Completed lessons cannot be negative."
        }

        require(totalChaptersCompleted >= 0) {
            "Completed chapters cannot be negative."
        }

        require(curriculumVersion > 0) {
            "Curriculum version must be positive."
        }
    }
}

enum class TrainingPathLevel(
    val displayName: String
) {
    BEGINNER("Beginner"),
    FUNDAMENTALS("Fundamentals"),
    DEVELOPING("Developing"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced")
}