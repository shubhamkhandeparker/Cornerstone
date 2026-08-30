package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import java.time.LocalDate
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TrainingPathViewModel(
    private val repository: TrainingPathRepository
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = true,
        val sport: String = "",
        val progress: TrainingPathProgressEntity? = null,
        val currentChapter: TrainingChapterDefinition? = null,
        val currentLesson: TrainingLessonDefinition? = null,
        val completedLessonIds: Set<String> = emptySet(),
        val chapterCompletedLessons: Int = 0,
        val chapterTotalLessons: Int = 0,

        /**
         * True when every lesson in the fighter's current
         * progression level has been completed.
         */
        val isCurrentLevelComplete: Boolean = false,

        /**
         * True when there is currently no further curriculum
         * available after the fighter's completed level.
         *
         * For curriculum version 1 this currently means the
         * available Beginner path has been completed.
         */
        val isAvailablePathComplete: Boolean = false,

        /**
         * The final completed lesson when the available path
         * has ended.
         */
        val lastCompletedLesson:
        TrainingLessonDefinition? = null,

        val errorMessage: String? = null
    ) {

        val chapterProgressFraction: Float
            get() {
                if (chapterTotalLessons <= 0) {
                    return 0f
                }

                return (
                        chapterCompletedLessons.toFloat() /
                                chapterTotalLessons.toFloat()
                        ).coerceIn(
                        minimumValue = 0f,
                        maximumValue = 1f
                    )
            }
    }

    private val _uiState =
        MutableStateFlow(
            UiState()
        )

    val uiState =
        _uiState.asStateFlow()

    private var observeJob: Job? = null

    private var loadedSport: String? = null

    fun loadSport(
        sport: String
    ) {
        val safeSport =
            sport.trim()

        if (safeSport.isBlank()) {
            return
        }

        if (
            loadedSport.equals(
                safeSport,
                ignoreCase = true
            )
        ) {
            return
        }

        loadedSport =
            safeSport

        observeJob?.cancel()

        _uiState.value =
            UiState(
                isLoading = true,
                sport = safeSport
            )

        observeJob =
            viewModelScope.launch {

                try {
                    repository
                        .ensureProgressExists(
                            sport = safeSport
                        )

                    combine(
                        repository.observeProgress(
                            sport = safeSport
                        ),
                        repository.observeLessonCompletions(
                            sport = safeSport
                        )
                    ) {
                            progress,
                            completions ->

                        if (progress == null) {
                            return@combine UiState(
                                isLoading = false,
                                sport = safeSport,
                                errorMessage =
                                    "Fight Path progress could not be loaded."
                            )
                        }

                        val completedIds =
                            completions
                                .map {
                                        completion ->

                                    completion.lessonId
                                }
                                .toSet()

                        val currentLevel =
                            runCatching {
                                TrainingPathLevel
                                    .valueOf(
                                        progress.level
                                    )
                            }.getOrDefault(
                                TrainingPathLevel
                                    .BEGINNER
                            )

                        val levelLessons =
                            TrainingCurriculum
                                .lessonsForSport(
                                    sport = safeSport,
                                    level =
                                        currentLevel
                                )

                        val completedLevelLessons =
                            levelLessons.count {
                                    lesson ->

                                lesson.id in
                                        completedIds
                            }

                        val levelComplete =
                            levelLessons.isNotEmpty() &&
                                    completedLevelLessons >=
                                    levelLessons.size

                        val storedLesson =
                            TrainingCurriculum
                                .getLesson(
                                    lessonId =
                                        progress
                                            .currentLessonId
                                )

                        val storedChapter =
                            TrainingCurriculum
                                .getChapter(
                                    chapterId =
                                        progress
                                            .currentChapterId
                                )

                        val storedLessonCompleted =
                            storedLesson != null &&
                                    storedLesson.id in
                                    completedIds

                        /*
                         * When the final available lesson has
                         * already been completed, it must no
                         * longer appear as today's active lesson.
                         */
                        val activeLesson =
                            if (
                                levelComplete &&
                                storedLessonCompleted
                            ) {
                                null
                            } else {
                                storedLesson
                            }

                        val activeChapter =
                            if (
                                activeLesson != null
                            ) {
                                storedChapter
                            } else {
                                null
                            }

                        val displayChapter =
                            activeChapter
                                ?: storedChapter

                        val completedInChapter =
                            displayChapter
                                ?.lessons
                                ?.count {
                                        chapterLesson ->

                                    chapterLesson.id in
                                            completedIds
                                }
                                ?: 0

                        val totalInChapter =
                            displayChapter
                                ?.lessons
                                ?.size
                                ?: 0

                        val lastCompletion =
                            completions
                                .maxByOrNull {
                                        completion ->

                                    completion
                                        .completedAtEpochMs
                                }

                        val lastCompletedLesson =
                            lastCompletion
                                ?.lessonId
                                ?.let {
                                        lessonId ->

                                    TrainingCurriculum
                                        .getLesson(
                                            lessonId =
                                                lessonId
                                        )
                                }

                        /*
                         * Currently the curriculum only contains
                         * Beginner lessons.
                         *
                         * As soon as Fundamentals is added, this
                         * calculation will automatically stop
                         * treating Beginner completion as the end
                         * if another level contains lessons.
                         */
                        val levelsAfterCurrent =
                            TrainingPathLevel
                                .entries
                                .dropWhile {
                                        level ->

                                    level !=
                                            currentLevel
                                }
                                .drop(1)

                        val hasFutureCurriculum =
                            levelsAfterCurrent
                                .any {
                                        futureLevel ->

                                    TrainingCurriculum
                                        .lessonsForSport(
                                            sport =
                                                safeSport,
                                            level =
                                                futureLevel
                                        )
                                        .isNotEmpty()
                                }

                        val availablePathComplete =
                            levelComplete &&
                                    !hasFutureCurriculum

                        UiState(
                            isLoading = false,
                            sport = safeSport,
                            progress = progress,
                            currentChapter =
                                activeChapter,
                            currentLesson =
                                activeLesson,
                            completedLessonIds =
                                completedIds,
                            chapterCompletedLessons =
                                completedInChapter,
                            chapterTotalLessons =
                                totalInChapter,
                            isCurrentLevelComplete =
                                levelComplete,
                            isAvailablePathComplete =
                                availablePathComplete,
                            lastCompletedLesson =
                                lastCompletedLesson,
                            errorMessage = null
                        )
                    }.collect {
                            state ->

                        _uiState.value =
                            state
                    }
                } catch (
                    throwable: Throwable
                ) {
                    _uiState.value =
                        UiState(
                            isLoading = false,
                            sport = safeSport,
                            errorMessage =
                                throwable.message
                                    ?: "Unable to load Fight Path."
                        )
                }
            }
    }

    suspend fun completeCurrentLesson(
        trainingSessionId: Long,
        activeTrainingSeconds: Int,
        completedCombos: Int,
        skippedCombos: Int
    ): StructuredLessonCompletionResult {

        val currentState =
            _uiState.value

        val lesson =
            currentState.currentLesson
                ?: return StructuredLessonCompletionResult
                    .LessonNotFound

        return repository.completeLesson(
            sport =
                currentState.sport,
            lessonId =
                lesson.id,
            trainingSessionId =
                trainingSessionId,
            activeTrainingSeconds =
                activeTrainingSeconds,
            completedCombos =
                completedCombos,
            skippedCombos =
                skippedCombos,
            completedDate =
                LocalDate.now()
        )
    }

    class Factory(
        private val repository:
        TrainingPathRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {

            if (
                modelClass.isAssignableFrom(
                    TrainingPathViewModel::class.java
                )
            ) {
                return TrainingPathViewModel(
                    repository = repository
                ) as T
            }

            throw IllegalArgumentException(
                "Unknown ViewModel class: ${modelClass.name}"
            )
        }
    }
}