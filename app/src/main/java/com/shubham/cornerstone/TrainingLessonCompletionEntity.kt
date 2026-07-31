package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.Index

/**
 * Permanent record that a structured Fight Path lesson
 * has been successfully completed.
 *
 * A fighter may repeat a lesson for practice, but the same
 * lesson cannot be completed/rewarded twice for progression.
 */
@Entity(
    tableName = "training_lesson_completions",
    primaryKeys = [
        "sport",
        "lessonId"
    ],
    indices = [
        Index(
            value = ["sport"]
        ),
        Index(
            value = ["chapterId"]
        ),
        Index(
            value = ["completedAtEpochDay"]
        ),
        Index(
            value = ["trainingSessionId"]
        )
    ]
)
data class TrainingLessonCompletionEntity(

    /**
     * Boxing
     * Kickboxing
     * Muay Thai
     * MMA
     */
    val sport: String,

    /**
     * Unique curriculum lesson ID.
     *
     * Example:
     * boxing_beginner_chapter_1_lesson_1
     */
    val lessonId: String,

    /**
     * Chapter containing this lesson.
     */
    val chapterId: String,

    /**
     * Progression level when this lesson was completed.
     */
    val level: String,

    /**
     * Links this progression completion to the genuine
     * TrainingSessionEntity that produced it.
     */
    val trainingSessionId: Long,

    /**
     * XP awarded for completing this lesson the first time.
     */
    val xpAwarded: Int,

    /**
     * Actual active training time.
     */
    val activeTrainingSeconds: Int,

    /**
     * Number of lesson combos successfully completed.
     */
    val completedCombos: Int,

    /**
     * Number of combos skipped.
     */
    val skippedCombos: Int,

    /**
     * LocalDate.toEpochDay().
     */
    val completedAtEpochDay: Long,

    /**
     * Allows future curriculum changes without corrupting
     * old progression history.
     */
    val curriculumVersion: Int = 1,

    val completedAtEpochMs: Long =
        System.currentTimeMillis()
) {

    init {
        require(sport.isNotBlank()) {
            "Sport cannot be blank."
        }

        require(lessonId.isNotBlank()) {
            "Lesson ID cannot be blank."
        }

        require(chapterId.isNotBlank()) {
            "Chapter ID cannot be blank."
        }

        require(level.isNotBlank()) {
            "Level cannot be blank."
        }

        require(trainingSessionId > 0L) {
            "Training session ID must be valid."
        }

        require(xpAwarded >= 0) {
            "XP awarded cannot be negative."
        }

        require(activeTrainingSeconds >= 0) {
            "Active training time cannot be negative."
        }

        require(completedCombos >= 0) {
            "Completed combos cannot be negative."
        }

        require(skippedCombos >= 0) {
            "Skipped combos cannot be negative."
        }

        require(curriculumVersion > 0) {
            "Curriculum version must be positive."
        }
    }
}