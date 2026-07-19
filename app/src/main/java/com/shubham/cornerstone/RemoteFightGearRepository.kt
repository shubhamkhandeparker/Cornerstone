package com.shubham.cornerstone

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class RemoteFightGearRepository(
    private val api: FightGearCatalogApi,
    private val catalogUrl: String,
    context: Context? = null
) : FightGearRepository {

    private companion object {
        const val PREFERENCES_NAME =
            "fight_gear_catalog_preferences"

        const val CACHED_CATALOG_KEY =
            "cached_fight_gear_catalog"
    }

    private val appContext: Context? =
        context?.applicationContext

    private val preferences =
        appContext?.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    private val _uiState = MutableStateFlow(
        loadCachedUiState()
    )

    override val uiState: StateFlow<FightGearUiState> =
        _uiState.asStateFlow()

    override suspend fun refresh() {
        val safeCatalogUrl = catalogUrl.trim()

        if (!safeCatalogUrl.startsWith("https://")) {
            if (_uiState.value !is FightGearUiState.Content) {
                _uiState.value = FightGearUiState.Error(
                    message = "The gear catalog URL is not configured correctly."
                )
            }

            return
        }

        val existingProducts =
            (_uiState.value as? FightGearUiState.Content)
                ?.products
                .orEmpty()

        if (existingProducts.isEmpty()) {
            _uiState.value = FightGearUiState.Loading
        }

        val result = runCatching {
            api.getCatalog(
                catalogUrl = safeCatalogUrl
            )
        }

        result.onSuccess { response ->
            saveCatalogToCache(response)

            _uiState.value =
                response.toUiState()
        }.onFailure {
            val cachedState = loadCachedUiState()

            _uiState.value = when {
                cachedState is FightGearUiState.Content -> {
                    cachedState
                }

                cachedState == FightGearUiState.Empty -> {
                    FightGearUiState.Empty
                }

                existingProducts.isNotEmpty() -> {
                    FightGearUiState.Content(
                        products = existingProducts
                    )
                }

                else -> {
                    FightGearUiState.Error(
                        message = "Check your internet connection and try again."
                    )
                }
            }
        }
    }

    private fun loadCachedUiState(): FightGearUiState {
        val cachedJson = preferences
            ?.getString(
                CACHED_CATALOG_KEY,
                null
            )
            ?.takeIf { it.isNotBlank() }
            ?: return FightGearUiState.Loading

        val cachedResponse = runCatching {
            json.decodeFromString<FightGearCatalogResponse>(
                cachedJson
            )
        }.getOrNull()
            ?: return FightGearUiState.Loading

        return cachedResponse.toUiState()
    }

    private fun saveCatalogToCache(
        response: FightGearCatalogResponse
    ) {
        val encodedCatalog = runCatching {
            json.encodeToString(response)
        }.getOrNull() ?: return

        preferences
            ?.edit()
            ?.putString(
                CACHED_CATALOG_KEY,
                encodedCatalog
            )
            ?.apply()
    }

    private fun FightGearCatalogResponse.toUiState():
            FightGearUiState {

        val products = products
            .asSequence()
            .filter { product ->
                product.isActive
            }
            .sortedWith(
                compareBy<FightGearProductDto> { product ->
                    product.sortOrder
                }.thenBy { product ->
                    product.name.lowercase()
                }
            )
            .mapNotNull { product ->
                product.toFightGearProduct()
            }
            .distinctBy { product ->
                product.id
            }
            .toList()

        return if (products.isEmpty()) {
            FightGearUiState.Empty
        } else {
            FightGearUiState.Content(
                products = products
            )
        }
    }
}