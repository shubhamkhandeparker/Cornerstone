package com.shubham.cornerstone

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface FightGearRepository {

    val uiState: StateFlow<FightGearUiState>

    suspend fun refresh()
}

class DemoFightGearRepository : FightGearRepository {

    private val _uiState = MutableStateFlow<FightGearUiState>(
        FightGearUiState.Content(
            products = FightGearDemoCatalog.products
        )
    )

    override val uiState: StateFlow<FightGearUiState> =
        _uiState.asStateFlow()

    override suspend fun refresh() {
        _uiState.value = FightGearUiState.Loading

        _uiState.value = FightGearUiState.Content(
            products = FightGearDemoCatalog.products
        )
    }
}