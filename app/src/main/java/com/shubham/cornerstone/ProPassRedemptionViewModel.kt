package com.shubham.cornerstone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ProPassRedemptionUiState(
    val pointsBalance: Int = 0,
    val options: List<ProPassRedemptionOption> =
        emptyList(),
    val activePass: EarnedProPassEntity? = null,
    val isProcessing: Boolean = false,
    val processingOptionId: String? = null,
    val message: String? = null
) {
    val hasActiveEarnedPass: Boolean
        get() =
            activePass?.isActive() == true

    fun canAfford(
        option: ProPassRedemptionOption
    ): Boolean {
        return pointsBalance >=
                option.pointsCost
    }
}

class ProPassRedemptionViewModel(
    private val pointsRepository:
    CornerstonePointsRepository,
    private val earnedProPassRepository:
    EarnedProPassRepository
) : ViewModel() {

    private val isProcessing =
        MutableStateFlow(false)

    private val processingOptionId =
        MutableStateFlow<String?>(null)

    private val message =
        MutableStateFlow<String?>(null)

    private val options =
        MutableStateFlow(
            ProPassRedemptionCatalog
                .activeOptions()
        )

    val uiState:
            StateFlow<ProPassRedemptionUiState> =
        combine(
            pointsRepository
                .observeBalance(),
            earnedProPassRepository
                .observeActivePass(
                    refreshIntervalMillis =
                        5_000L
                ),
            options,
            isProcessing,
            processingOptionId
        ) {
                balance,
                activePass,
                currentOptions,
                processing,
                currentProcessingOptionId ->

            ProPassRedemptionUiState(
                pointsBalance =
                    balance.coerceAtLeast(0),
                options =
                    currentOptions,
                activePass =
                    activePass,
                isProcessing =
                    processing,
                processingOptionId =
                    currentProcessingOptionId,
                message =
                    message.value
            )
        }.combine(
            message
        ) {
                state,
                currentMessage ->

            state.copy(
                message =
                    currentMessage
            )
        }.stateIn(
            scope =
                viewModelScope,
            started =
                SharingStarted
                    .WhileSubscribed(
                        stopTimeoutMillis =
                            5_000L
                    ),
            initialValue =
                ProPassRedemptionUiState(
                    options =
                        ProPassRedemptionCatalog
                            .activeOptions()
                )
        )

    fun redeem(
        optionId: String
    ) {
        if (isProcessing.value) {
            return
        }

        val option =
            ProPassRedemptionCatalog
                .getOption(
                    optionId =
                        optionId
                )

        if (option == null) {
            message.value =
                "This Pro Pass option is unavailable."

            return
        }

        viewModelScope.launch {
            isProcessing.value =
                true

            processingOptionId.value =
                option.id

            message.value =
                null

            try {
                val currentBalance =
                    pointsRepository
                        .getBalance()

                if (
                    currentBalance <
                    option.pointsCost
                ) {
                    message.value =
                        "You need ${option.pointsCost - currentBalance} more points."

                    return@launch
                }

                val redemptionId =
                    buildRedemptionId(
                        optionId =
                            option.id
                    )

                val redemptionResult =
                    earnedProPassRepository
                        .redeemPointsForProPass(
                            redemptionId =
                                redemptionId,
                            pointsCost =
                                option.pointsCost,
                            proPassDays =
                                option.proPassDays
                        )

                message.value =
                    redemptionResult
                        .toUserMessage(
                            option =
                                option
                        )
            } catch (error: Throwable) {
                message.value =
                    error.message
                        ?: "Unable to redeem the Pro Pass."
            } finally {
                isProcessing.value =
                    false

                processingOptionId.value =
                    null
            }
        }
    }

    fun clearMessage() {
        message.value =
            null
    }

    private fun buildRedemptionId(
        optionId: String
    ): String {
        return "$optionId:${UUID.randomUUID()}"
    }

    class Factory(
        private val pointsRepository:
        CornerstonePointsRepository,
        private val earnedProPassRepository:
        EarnedProPassRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {
            require(
                modelClass.isAssignableFrom(
                    ProPassRedemptionViewModel::class.java
                )
            ) {
                "Unknown ViewModel class: ${modelClass.name}"
            }

            return ProPassRedemptionViewModel(
                pointsRepository =
                    pointsRepository,
                earnedProPassRepository =
                    earnedProPassRepository
            ) as T
        }
    }
}

private fun EarnedProPassRedemptionResult
        .toUserMessage(
    option: ProPassRedemptionOption
): String {
    return when (this) {
        is EarnedProPassRedemptionResult
        .Redeemed -> {

            "${option.pointsCost} points redeemed. ${option.proPassDays}-day Pro Pass activated."
        }

        is EarnedProPassRedemptionResult
        .Repaired -> {

            "Your ${option.proPassDays}-day Pro Pass has been restored."
        }

        is EarnedProPassRedemptionResult
        .AlreadyRedeemed -> {

            "This Pro Pass redemption was already completed."
        }

        EarnedProPassRedemptionResult
            .InsufficientPoints -> {

            "You do not have enough Cornerstone Points."
        }

        EarnedProPassRedemptionResult
            .InvalidRedemption -> {

            "This Pro Pass redemption is invalid."
        }
    }
}