package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Holds the onboarding screen's state and logic.
 * Tracks the user's choices and saves them when they finish.
 */
class OnboardingViewModel(
    private val repository: UserProfileRepository
) : ViewModel() {

    private val _sport = MutableStateFlow("Boxing")
    val sport: StateFlow<String> = _sport.asStateFlow()

    private val _level = MutableStateFlow("Beginner")
    val level: StateFlow<String> = _level.asStateFlow()

    private val _dominance = MutableStateFlow("Striker")
    val dominance: StateFlow<String> = _dominance.asStateFlow()

    private val _stance = MutableStateFlow("Orthodox")
    val stance: StateFlow<String> = _stance.asStateFlow()

    fun selectSport(value: String) { _sport.value = value }
    fun selectLevel(value: String) { _level.value = value }
    fun selectDominance(value: String) { _dominance.value = value }
    fun selectStance(value: String) { _stance.value = value }

    // Save choices to the database, then run the callback (e.g. navigate away).
    fun finish(onDone: () -> Unit) {
        viewModelScope.launch {
            repository.completeOnboarding(
                sport = _sport.value,
                level = _level.value,
                dominance = _dominance.value,
                stance = _stance.value
            )
            onDone()
        }
    }

    class Factory(private val repository: UserProfileRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OnboardingViewModel(repository) as T
        }
    }
}