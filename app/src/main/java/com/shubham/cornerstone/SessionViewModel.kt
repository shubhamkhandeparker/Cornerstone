package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Loads a training session via the AI generator.
 * The screen watches `state` and shows loading / combos accordingly.
 */
class SessionViewModel(
    private val generator: ComboGenerator = ComboGenerator()
) : ViewModel() {

    sealed interface State {
        data object Loading : State
        data class Ready(val combos: List<Combo>) : State
    }

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state.asStateFlow()

    fun load(sport: String, level: String) {
        _state.value = State.Loading
        viewModelScope.launch {
            val combos = generator.generate(sport, level)
            _state.value = State.Ready(combos)
        }
    }
}