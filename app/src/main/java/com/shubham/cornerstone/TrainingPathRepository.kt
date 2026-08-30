package com.shubham.cornerstone

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlinx.coroutines.flow.Flow

private const val MAX_STREAK_GRACE_DAYS = 2

sealed interface StructuredLessonCompletionResult {

    data class Completed(
        val lesson: TrainingLessonDefinition,
        val xpAwarded: Int,
        val totalXp: Int,
        val currentStreakDays: Int,
        val chapterCompleted: Boolean,
        val nextLesson: TrainingLessonDefinition?
    ) : StructuredLessonCompletionResult

    data class AlreadyCompleted(
        val lesson: TrainingLessonDefinition
    ) : StructuredLessonCompletionResult

    data class InvalidSession(
        val reason: String
    ) : StructuredLessonCompletionResult

    data object LessonNotFound :
        StructuredLessonCompletionResult
}

class TrainingPathRepository(
    private val dao: TrainingPathDao
) {

    fun observeProgress(
        sport: String
    ): Flow<TrainingPathProgressEntity?> {

        return dao.observeProgress(
            sport = sport.trim()
        )
    }

    fun observeLessonCompletions(
        sport: String
    ): Flow<List<TrainingLessonCompletionEntity>> {

        return dao.observeLessonCompletions(
            sport = sport.trim()
        )
    }

    suspend fun getProgress(
        sport: String
    ): TrainingPathProgressEntity? {

        return dao.getProgress(
            sport = sport.trim()
        )
    }

    suspend fun ensureProgressExists(
        sport: String
    ): TrainingPathProgressEntity {

        val safeSport =
            sport.trim()

        val existing =
            dao.getProgress(
                sport = safeSport
            )

        if (existing != null) {
            return existing
        }

        val firstChapter =
            TrainingCurriculum.firstChapter(
                sport = safeSport
            ) ?: error(
                "No curriculum exists for $safeSport."
            )

        val firstLesson =
            TrainingCurriculum.firstLesson(
                sport = safeSport
            ) ?: error(
                "No lessons exist for $safeSport."
            )

        val progress =
            TrainingPathProgressEntity(
                sport = safeSport,
                level =
                    firstLesson.level.name,
                currentChapterId =
                    firstChapter.id,
                currentLessonId =
                    firstLesson.id,
                xp = 0,
                currentStreakDays = 0,
                longestStreakDays = 0,
                lastCompletedEpochDay = null,
                totalLessonsCompleted = 0,
                totalChaptersCompleted = 0,
                curriculumVersion =
                    TrainingCurriculum.VERSION
            )

        dao.saveProgress(
            progress = progress
        )

        return progress
    }

    suspend fun getCurrentLesson(
        sport: String
    ): TrainingLessonDefinition? {

        val progress =
            ensureProgressExists(
                sport = sport
            )

        val lesson =
            TrainingCurriculum
                .getLesson(
                    lessonId =
                        progress.currentLessonId
                )

        if (lesson == null) {
            return null
        }

        val alreadyCompleted =
            dao.isLessonCompleted(
                sport =
                    progress.sport,
                lessonId =
                    lesson.id
            )

        /*
         * When the final available lesson has already
         * been completed, there is no active lesson.
         */
        return if (alreadyCompleted) {
            null
        } else {
            lesson
        }
    }

    suspend fun getCurrentChapter(
        sport: String
    ): TrainingChapterDefinition? {

        val progress =
            ensureProgressExists(
                sport = sport
            )

        return TrainingCurriculum
            .getChapter(
                chapterId =
                    progress.currentChapterId
            )
    }

    suspend fun completeLesson(
        sport: String,
        lessonId: String,
        trainingSessionId: Long,
        activeTrainingSeconds: Int,
        completedCombos: Int,
        skippedCombos: Int,
        completedDate: LocalDate =
            LocalDate.now()
    ): StructuredLessonCompletionResult {

        val safeSport =
            sport.trim()

        val lesson =
            TrainingCurriculum.getLesson(
                lessonId = lessonId
            ) ?: return StructuredLessonCompletionResult
                .LessonNotFound

        if (
            !lesson.sport.equals(
                safeSport,
                ignoreCase = true
            )
        ) {
            return StructuredLessonCompletionResult
                .InvalidSession(
                    reason =
                        "This lesson does not belong to the selected sport."
                )
        }

        if (trainingSessionId <= 0L) {
            return StructuredLessonCompletionResult
                .InvalidSession(
                    reason =
                        "Training session was not saved correctly."
                )
        }

        val safeActiveSeconds =
            activeTrainingSeconds
                .coerceAtLeast(0)

        val safeCompletedCombos =
            completedCombos
                .coerceAtLeast(0)

        val safeSkippedCombos =
            skippedCombos
                .coerceAtLeast(0)

        if (
            safeActiveSeconds <
            lesson.requiredActiveSeconds
        ) {
            return StructuredLessonCompletionResult
                .InvalidSession(
                    reason =
                        "Complete at least ${lesson.requiredActiveSeconds / 60} active training minutes."
                )
        }

        if (
            safeCompletedCombos <
            lesson.combos.size
        ) {
            return StructuredLessonCompletionResult
                .InvalidSession(
                    reason =
                        "Complete every lesson combo before finishing."
                )
        }

        if (safeSkippedCombos > 0) {
            return StructuredLessonCompletionResult
                .InvalidSession(
                    reason =
                        "Skipped combinations do not count toward lesson completion."
                )
        }

        val alreadyCompleted =
            dao.isLessonCompleted(
                sport = safeSport,
                lessonId = lesson.id
            )

        if (alreadyCompleted) {
            return StructuredLessonCompletionResult
                .AlreadyCompleted(
                    lesson = lesson
                )
        }

        val currentProgress =
            ensureProgressExists(
                sport = safeSport
            )

        /*
         * Progression can only be earned from the
         * fighter's currently unlocked lesson.
         */
        if (
            currentProgress.currentLessonId !=
            lesson.id
        ) {
            return StructuredLessonCompletionResult
                .InvalidSession(
                    reason =
                        "Complete your currently unlocked lesson first."
                )
        }

        val completion =
            TrainingLessonCompletionEntity(
                sport = safeSport,
                lessonId = lesson.id,
                chapterId =
                    lesson.chapterId,
                level =
                    lesson.level.name,
                trainingSessionId =
                    trainingSessionId,
                xpAwarded =
                    lesson.xpReward,
                activeTrainingSeconds =
                    safeActiveSeconds,
                completedCombos =
                    safeCompletedCombos,
                skippedCombos =
                    safeSkippedCombos,
                completedAtEpochDay =
                    completedDate.toEpochDay(),
                curriculumVersion =
                    TrainingCurriculum.VERSION
            )

        val insertedId =
            dao.insertLessonCompletion(
                completion = completion
            )

        /*
         * IGNORE returns -1 if another completion
         * already exists for this sport + lesson.
         */
        if (insertedId == -1L) {
            return StructuredLessonCompletionResult
                .AlreadyCompleted(
                    lesson = lesson
                )
        }

        val chapter =
            TrainingCurriculum.getChapter(
                chapterId =
                    lesson.chapterId
            )

        val completedChapterLessonCount =
            dao.getCompletedLessonCountForChapter(
                sport = safeSport,
                chapterId =
                    lesson.chapterId
            )

        val chapterCompleted =
            chapter != null &&
                    completedChapterLessonCount >=
                    chapter.lessons.size

        /*
         * Important:
         *
         * This searches:
         *
         * current lesson
         *      ↓
         * next lesson in chapter
         *      ↓
         * next chapter
         *      ↓
         * next progression level
         *
         * Example:
         *
         * Beginner Chapter 1
         * Beginner Chapter 2
         * Fundamentals Chapter 1
         */
        val nextLesson =
            findNextLessonInPath(
                lesson = lesson
            )

        val newStreak =
            calculateNewStreak(
                previousStreak =
                    currentProgress
                        .currentStreakDays,
                previousCompletedEpochDay =
                    currentProgress
                        .lastCompletedEpochDay,
                completedDate =
                    completedDate
            )

        val newLongestStreak =
            maxOf(
                currentProgress
                    .longestStreakDays,
                newStreak
            )

        val newXp =
            currentProgress.xp +
                    lesson.xpReward

        val newCompletedLessonCount =
            currentProgress
                .totalLessonsCompleted + 1

        val newCompletedChapterCount =
            currentProgress
                .totalChaptersCompleted +
                    if (chapterCompleted) {
                        1
                    } else {
                        0
                    }

        /*
         * If another lesson exists, progression moves
         * completely to that lesson — including its
         * level and chapter.
         *
         * If no lesson exists yet, keep the final stored
         * IDs. TrainingPathViewModel will recognise that
         * those IDs point to an already-completed final
         * lesson and display PATH COMPLETE.
         */
        val resolvedLevel =
            nextLesson
                ?.level
                ?.name
                ?: currentProgress.level

        val resolvedNextChapterId =
            nextLesson
                ?.chapterId
                ?: currentProgress
                    .currentChapterId

        val resolvedNextLessonId =
            nextLesson
                ?.id
                ?: currentProgress
                    .currentLessonId

        val updatedProgress =
            currentProgress.copy(
                level =
                    resolvedLevel,
                currentChapterId =
                    resolvedNextChapterId,
                currentLessonId =
                    resolvedNextLessonId,
                xp =
                    newXp,
                currentStreakDays =
                    newStreak,
                longestStreakDays =
                    newLongestStreak,
                lastCompletedEpochDay =
                    completedDate.toEpochDay(),
                totalLessonsCompleted =
                    newCompletedLessonCount,
                totalChaptersCompleted =
                    newCompletedChapterCount,
                curriculumVersion =
                    TrainingCurriculum.VERSION,
                updatedAtEpochMs =
                    System.currentTimeMillis()
            )

        dao.saveProgress(
            progress = updatedProgress
        )

        return StructuredLessonCompletionResult
            .Completed(
                lesson = lesson,
                xpAwarded =
                    lesson.xpReward,
                totalXp =
                    newXp,
                currentStreakDays =
                    newStreak,
                chapterCompleted =
                    chapterCompleted,
                nextLesson =
                    nextLesson
            )
    }

    suspend fun isLessonCompleted(
        sport: String,
        lessonId: String
    ): Boolean {

        return dao.isLessonCompleted(
            sport = sport.trim(),
            lessonId = lessonId
        )
    }

    /**
     * Finds the next lesson across the entire sport path.
     *
     * Order:
     *
     * BEGINNER
     * FUNDAMENTALS
     * DEVELOPING
     * INTERMEDIATE
     * ADVANCED
     */
    private fun findNextLessonInPath(
        lesson: TrainingLessonDefinition
    ): TrainingLessonDefinition? {

        /*
         * First look for another lesson inside the
         * current progression level.
         */
        val currentLevelLessons =
            TrainingCurriculum
                .lessonsForSport(
                    sport =
                        lesson.sport,
                    level =
                        lesson.level
                )

        val currentIndex =
            currentLevelLessons
                .indexOfFirst {
                        item ->

                    item.id == lesson.id
                }

        if (
            currentIndex >= 0 &&
            currentIndex <
            currentLevelLessons.lastIndex
        ) {
            return currentLevelLessons[
                currentIndex + 1
            ]
        }

        /*
         * Current level ended.
         *
         * Search every higher level until we find
         * the first available lesson.
         */
        val currentLevelIndex =
            TrainingPathLevel
                .entries
                .indexOf(
                    lesson.level
                )

        if (currentLevelIndex < 0) {
            return null
        }

        val futureLevels =
            TrainingPathLevel
                .entries
                .drop(
                    currentLevelIndex + 1
                )

        futureLevels.forEach {
                futureLevel ->

            val firstFutureLesson =
                TrainingCurriculum
                    .lessonsForSport(
                        sport =
                            lesson.sport,
                        level =
                            futureLevel
                    )
                    .firstOrNull()

            if (
                firstFutureLesson != null
            ) {
                return firstFutureLesson
            }
        }

        return null
    }

    private fun calculateNewStreak(
        previousStreak: Int,
        previousCompletedEpochDay: Long?,
        completedDate: LocalDate
    ): Int {

        val safePreviousStreak =
            previousStreak
                .coerceAtLeast(0)

        if (
            previousCompletedEpochDay ==
            null
        ) {
            return 1
        }

        val previousDate =
            LocalDate.ofEpochDay(
                previousCompletedEpochDay
            )

        val daysBetween =
            ChronoUnit.DAYS.between(
                previousDate,
                completedDate
            )

        return when {

            /*
             * Completing multiple lessons today does
             * not artificially increase the streak.
             */
            daysBetween <= 0L -> {
                safePreviousStreak
                    .coerceAtLeast(1)
            }

            /*
             * Trained on the next day.
             */
            daysBetween == 1L -> {
                safePreviousStreak + 1
            }

            /*
             * Up to two complete missed days are
             * currently allowed as streak grace.
             */
            daysBetween <=
                    MAX_STREAK_GRACE_DAYS + 1L -> {

                safePreviousStreak + 1
            }

            /*
             * Longer absence starts a fresh streak.
             */
            else -> {
                1
            }
        }
    }
}