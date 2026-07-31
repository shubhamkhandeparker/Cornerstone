package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged

enum class ProEntitlementSource {
    NONE,
    GOOGLE_PLAY,
    EARNED_PRO_PASS,
    GOOGLE_PLAY_AND_EARNED_PRO_PASS
}

data class ProEntitlementState(
    val isPro: Boolean = false,
    val hasGooglePlayPro: Boolean = false,
    val activeEarnedPass:
    EarnedProPassEntity? = null,
    val source: ProEntitlementSource =
        ProEntitlementSource.NONE
) {
    val earnedPassExpiresAtEpochMs: Long?
        get() =
            activeEarnedPass
                ?.expiresAtEpochMs

    val earnedPassDays: Int?
        get() =
            activeEarnedPass
                ?.durationDays
}

class ProEntitlementRepository(
    private val userRepository:
    UserProfileRepository,
    private val earnedProPassRepository:
    EarnedProPassRepository
) {

    fun observeEntitlement(
        refreshIntervalMillis: Long =
            60_000L
    ): Flow<ProEntitlementState> {
        return combine(
            userRepository.profile,
            earnedProPassRepository
                .observeActivePass(
                    refreshIntervalMillis =
                        refreshIntervalMillis
                )
        ) {
                profile,
                observedPass ->

            val now =
                System.currentTimeMillis()

            val hasGooglePlayPro =
                profile?.isPro == true

            val activeEarnedPass =
                observedPass
                    ?.takeIf { pass ->
                        pass.isActive(
                            nowEpochMs = now
                        )
                    }

            val hasEarnedPro =
                activeEarnedPass != null

            val source =
                when {
                    hasGooglePlayPro &&
                            hasEarnedPro -> {

                        ProEntitlementSource
                            .GOOGLE_PLAY_AND_EARNED_PRO_PASS
                    }

                    hasGooglePlayPro -> {
                        ProEntitlementSource
                            .GOOGLE_PLAY
                    }

                    hasEarnedPro -> {
                        ProEntitlementSource
                            .EARNED_PRO_PASS
                    }

                    else -> {
                        ProEntitlementSource.NONE
                    }
                }

            ProEntitlementState(
                isPro =
                    hasGooglePlayPro ||
                            hasEarnedPro,
                hasGooglePlayPro =
                    hasGooglePlayPro,
                activeEarnedPass =
                    activeEarnedPass,
                source =
                    source
            )
        }.distinctUntilChanged()
    }

    suspend fun getCurrentEntitlement():
            ProEntitlementState {

        val now =
            System.currentTimeMillis()

        val profile =
            userRepository.getProfile()

        val activeEarnedPass =
            earnedProPassRepository
                .getActivePass(
                    nowEpochMs = now
                )
                ?.takeIf { pass ->
                    pass.isActive(
                        nowEpochMs = now
                    )
                }

        val hasGooglePlayPro =
            profile?.isPro == true

        val hasEarnedPro =
            activeEarnedPass != null

        val source =
            when {
                hasGooglePlayPro &&
                        hasEarnedPro -> {

                    ProEntitlementSource
                        .GOOGLE_PLAY_AND_EARNED_PRO_PASS
                }

                hasGooglePlayPro -> {
                    ProEntitlementSource
                        .GOOGLE_PLAY
                }

                hasEarnedPro -> {
                    ProEntitlementSource
                        .EARNED_PRO_PASS
                }

                else -> {
                    ProEntitlementSource.NONE
                }
            }

        return ProEntitlementState(
            isPro =
                hasGooglePlayPro ||
                        hasEarnedPro,
            hasGooglePlayPro =
                hasGooglePlayPro,
            activeEarnedPass =
                activeEarnedPass,
            source =
                source
        )
    }
}