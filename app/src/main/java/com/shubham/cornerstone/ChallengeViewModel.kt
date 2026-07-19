package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ChallengeUiState(
    val challenges: List<ChallengeWithProgress> =
        emptyList(),
    val selectedCategory: ChallengeCategory? =
        null,
    val isProcessing: Boolean = false,
    val message: String? = null
) {
    val visibleChallenges: List<ChallengeWithProgress>
        get() {
            val category = selectedCategory
                ?: return challenges

            return challenges.filter { challenge ->
                challenge.definition.category ==
                        category
            }
        }

    val activeChallengeCount: Int
        get() = challenges.count { challenge ->
            challenge.isActive
        }

    val completedChallengeCount: Int
        get() = challenges.count { challenge ->
            challenge.isCompleted
        }
}

class ChallengeViewModel(
    private val repository: ChallengeRepository
) : ViewModel() {

    private val selectedCategory =
        MutableStateFlow<ChallengeCategory?>(null)

    private val isProcessing =
        MutableStateFlow(false)

    private val message =
        MutableStateFlow<String?>(null)

    val uiState: StateFlow<ChallengeUiState> =
        combine(
            repository.observeChallenges(),
            selectedCategory,
            isProcessing,
            message
        ) {
                challenges,
                category,
                processing,
                currentMessage ->

            ChallengeUiState(
                challenges = challenges,
                selectedCategory = category,
                isProcessing = processing,
                message = currentMessage
            )
        }.stateIn(
            scope = viewModelScope,
            started =
                SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 5_000
                ),
            initialValue =
                ChallengeUiState()
        )

    fun selectCategory(
        category: ChallengeCategory?
    ) {
        selectedCategory.value = category
    }

    fun startChallenge(
        challengeId: String
    ) {
        performAction(
            successMessage =
                "Challenge started."
        ) {
            repository.startChallenge(
                challengeId = challengeId
            )
        }
    }

    fun abandonChallenge(
        challengeId: String
    ) {
        performAction(
            successMessage =
                "Challenge abandoned."
        ) {
            repository.abandonChallenge(
                challengeId = challengeId
            )
        }
    }

    fun restartChallenge(
        challengeId: String
    ) {
        performAction(
            successMessage =
                "Challenge restarted."
        ) {
            repository.restartChallenge(
                challengeId = challengeId
            )
        }
    }

    fun clearMessage() {
        message.value = null
    }

    private fun performAction(
        successMessage: String,
        action: suspend () -> Result<Unit>
    ) {
        if (isProcessing.value) {
            return
        }

        viewModelScope.launch {
            isProcessing.value = true
            message.value = null

            val result = action()

            message.value =
                result.fold(
                    onSuccess = {
                        successMessage
                    },
                    onFailure = { error ->
                        error.message
                            ?: "Something went wrong."
                    }
                )

            isProcessing.value = false
        }
    }

    class Factory(
        private val repository:
        ChallengeRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {
            require(
                modelClass.isAssignableFrom(
                    ChallengeViewModel::class.java
                )
            ) {
                "Unknown ViewModel class: ${modelClass.name}"
            }

            return ChallengeViewModel(
                repository = repository
            ) as T
        }
    }
}