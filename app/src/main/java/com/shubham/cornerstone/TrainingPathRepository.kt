package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.temporal.ChronoUnit

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
                    TrainingPathLevel
                        .BEGINNER
                        .name,
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

        return TrainingCurriculum.getLesson(
            lessonId =
                progress.currentLessonId
        )
    }

    suspend fun getCurrentChapter(
        sport: String
    ): TrainingChapterDefinition? {

        val progress =
            ensureProgressExists(
                sport = sport
            )

        return TrainingCurriculum.getChapter(
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
         * A fighter can repeat older lessons for practice,
         * but progression can only be earned from the lesson
         * that is currently unlocked.
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
         * Room returns -1 when IGNORE prevented the insert.
         * This guarantees XP cannot be awarded twice.
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

        val nextLesson =
            findNextLesson(
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
                    completedDate
                        .toEpochDay(),
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
            progress =
                updatedProgress
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

    private fun findNextLesson(
        lesson: TrainingLessonDefinition
    ): TrainingLessonDefinition? {

        val lessons =
            TrainingCurriculum
                .lessonsForSport(
                    sport =
                        lesson.sport,
                    level =
                        lesson.level
                )

        val currentIndex =
            lessons.indexOfFirst { item ->
                item.id == lesson.id
            }

        if (
            currentIndex < 0 ||
            currentIndex >=
            lessons.lastIndex
        ) {
            return null
        }

        return lessons[
            currentIndex + 1
        ]
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
             * Multiple lessons completed on the same day
             * do not artificially increase the streak.
             */
            daysBetween <= 0L -> {
                safePreviousStreak
                    .coerceAtLeast(1)
            }

            /*
             * Trained the very next day.
             */
            daysBetween == 1L -> {
                safePreviousStreak + 1
            }

            /*
             * Allow up to two missed days without
             * destroying the fighter's streak.
             *
             * Example:
             * Monday training
             * Tuesday + Wednesday missed
             * Thursday training
             * streak survives.
             */
            daysBetween <=
                    MAX_STREAK_GRACE_DAYS + 1L -> {

                safePreviousStreak + 1
            }

            /*
             * More than two full missed days:
             * start a new streak.
             */
            else -> {
                1
            }
        }
    }
}