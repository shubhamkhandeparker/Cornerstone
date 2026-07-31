package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingPathDao {

    /*
     * ----------------------------------------------------
     * TRAINING PATH PROGRESS
     * ----------------------------------------------------
     */

    @Query(
        """
        SELECT *
        FROM training_path_progress
        WHERE sport = :sport
        LIMIT 1
        """
    )
    fun observeProgress(
        sport: String
    ): Flow<TrainingPathProgressEntity?>

    @Query(
        """
        SELECT *
        FROM training_path_progress
        WHERE sport = :sport
        LIMIT 1
        """
    )
    suspend fun getProgress(
        sport: String
    ): TrainingPathProgressEntity?

    @Query(
        """
        SELECT *
        FROM training_path_progress
        ORDER BY updatedAtEpochMs DESC
        """
    )
    fun observeAllProgress():
            Flow<List<TrainingPathProgressEntity>>

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun saveProgress(
        progress: TrainingPathProgressEntity
    )

    @Query(
        """
        DELETE FROM training_path_progress
        WHERE sport = :sport
        """
    )
    suspend fun deleteProgress(
        sport: String
    )

    /*
     * ----------------------------------------------------
     * LESSON COMPLETION
     * ----------------------------------------------------
     */

    @Insert(
        onConflict =
            OnConflictStrategy.IGNORE
    )
    suspend fun insertLessonCompletion(
        completion:
        TrainingLessonCompletionEntity
    ): Long

    @Query(
        """
        SELECT EXISTS(
            SELECT 1
            FROM training_lesson_completions
            WHERE sport = :sport
            AND lessonId = :lessonId
        )
        """
    )
    suspend fun isLessonCompleted(
        sport: String,
        lessonId: String
    ): Boolean

    @Query(
        """
        SELECT *
        FROM training_lesson_completions
        WHERE sport = :sport
        AND lessonId = :lessonId
        LIMIT 1
        """
    )
    suspend fun getLessonCompletion(
        sport: String,
        lessonId: String
    ): TrainingLessonCompletionEntity?

    @Query(
        """
        SELECT *
        FROM training_lesson_completions
        WHERE sport = :sport
        ORDER BY completedAtEpochMs DESC
        """
    )
    fun observeLessonCompletions(
        sport: String
    ): Flow<List<TrainingLessonCompletionEntity>>

    @Query(
        """
        SELECT *
        FROM training_lesson_completions
        WHERE sport = :sport
        AND chapterId = :chapterId
        ORDER BY completedAtEpochMs ASC
        """
    )
    fun observeChapterCompletions(
        sport: String,
        chapterId: String
    ): Flow<List<TrainingLessonCompletionEntity>>

    @Query(
        """
        SELECT COUNT(*)
        FROM training_lesson_completions
        WHERE sport = :sport
        AND chapterId = :chapterId
        """
    )
    suspend fun getCompletedLessonCountForChapter(
        sport: String,
        chapterId: String
    ): Int

    @Query(
        """
        SELECT COUNT(*)
        FROM training_lesson_completions
        WHERE sport = :sport
        """
    )
    suspend fun getTotalCompletedLessonCount(
        sport: String
    ): Int

    @Query(
        """
        SELECT COALESCE(
            SUM(xpAwarded),
            0
        )
        FROM training_lesson_completions
        WHERE sport = :sport
        """
    )
    suspend fun getTotalEarnedXp(
        sport: String
    ): Int

    @Query(
        """
        SELECT *
        FROM training_lesson_completions
        WHERE sport = :sport
        ORDER BY completedAtEpochMs DESC
        LIMIT :limit
        """
    )
    suspend fun getRecentLessonCompletions(
        sport: String,
        limit: Int = 20
    ): List<TrainingLessonCompletionEntity>

    @Query(
        """
        DELETE FROM training_lesson_completions
        WHERE sport = :sport
        """
    )
    suspend fun deleteLessonCompletions(
        sport: String
    )
}