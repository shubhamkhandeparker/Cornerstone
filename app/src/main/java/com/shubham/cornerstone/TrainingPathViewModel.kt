package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import java.time.LocalDate
import kotlinx.coroutines.Job
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
        kotlinx.coroutines.flow.MutableStateFlow(
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

                        val chapter =
                            progress
                                ?.currentChapterId
                                ?.let {
                                        chapterId ->

                                    TrainingCurriculum
                                        .getChapter(
                                            chapterId =
                                                chapterId
                                        )
                                }

                        val lesson =
                            progress
                                ?.currentLessonId
                                ?.let {
                                        lessonId ->

                                    TrainingCurriculum
                                        .getLesson(
                                            lessonId =
                                                lessonId
                                        )
                                }

                        val completedIds =
                            completions
                                .map {
                                        completion ->

                                    completion.lessonId
                                }
                                .toSet()

                        val completedInChapter =
                            chapter
                                ?.lessons
                                ?.count {
                                        chapterLesson ->

                                    chapterLesson.id in
                                            completedIds
                                }
                                ?: 0

                        UiState(
                            isLoading = false,
                            sport = safeSport,
                            progress = progress,
                            currentChapter = chapter,
                            currentLesson = lesson,
                            completedLessonIds =
                                completedIds,
                            chapterCompletedLessons =
                                completedInChapter,
                            chapterTotalLessons =
                                chapter
                                    ?.lessons
                                    ?.size
                                    ?: 0,
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