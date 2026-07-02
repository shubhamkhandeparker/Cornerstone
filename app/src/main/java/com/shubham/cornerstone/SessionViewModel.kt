package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Loads a training session via the combo generator.
 *
 * Supports:
 * - Custom number of combos.
 * - In-memory session offset so repeated sessions do not always start from combo 1.
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

    fun load(
        sport: String,
        level: String,
        dominance: String,
        stance: String,
        count: Int = 6,
        offset: Int = 0
    ) {
        _state.value = State.Loading

        viewModelScope.launch {
            val combos = generator.generate(
                sport = sport,
                level = level,
                dominance = dominance,
                stance = stance,
                count = count,
                offset = offset
            )

            _state.value = State.Ready(combos)
        }
    }
}