package com.shubham.cornerstone

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun FightGearAnalyticsScreen(
    repository: FightGearAnalyticsRepository,
    onExit: () -> Unit,
    onAnalyticsReset: () -> Unit = {}
) {
    BackHandler(onBack = onExit)

    val scope = rememberCoroutineScope()

    var showResetConfirmation by remember {
        mutableStateOf(false)
    }

    var resetInProgress by remember {
        mutableStateOf(false)
    }

    val totalImpressions by repository
        .observeImpressionCount()
        .collectAsStateWithLifecycle(
            initialValue = 0
        )

    val totalClicks by repository
        .observeViewDealClickCount()
        .collectAsStateWithLifecycle(
            initialValue = 0
        )

    val productAnalytics by repository
        .observeProductAnalytics()
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    val overallClickThroughRate =
        if (totalImpressions > 0) {
            totalClicks.toDouble() /
                    totalImpressions.toDouble() *
                    100.0
        } else {
            0.0
        }

    if (showResetConfirmation) {
        AlertDialog(
            onDismissRequest = {
                if (!resetInProgress) {
                    showResetConfirmation = false
                }
            },
            title = {
                Text(
                    text = "Reset analytics?"
                )
            },
            text = {
                Text(
                    text = "This deletes only the locally stored Fight Gear test statistics. Your products and other app data will not be affected."
                )
            },
            confirmButton = {
                TextButton(
                    enabled = !resetInProgress,
                    onClick = {
                        resetInProgress = true

                        scope.launch {
                            val resetSuccessful =
                                runCatching {
                                    repository.deleteAllEvents()
                                }.isSuccess

                            if (resetSuccessful) {
                                onAnalyticsReset()
                            }

                            resetInProgress = false
                            showResetConfirmation = false
                        }
                    }
                ) {
                    Text(
                        text = if (resetInProgress) {
                            "RESETTING..."
                        } else {
                            "RESET"
                        },
                        color = FightRed,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    enabled = !resetInProgress,
                    onClick = {
                        showResetConfirmation = false
                    }
                ) {
                    Text(
                        text = "CANCEL"
                    )
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF161518),
                        InkBlack
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            AnalyticsHeader(
                onExit = onExit
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 8.dp,
                    bottom = 28.dp
                ),
                verticalArrangement =
                    Arrangement.spacedBy(14.dp)
            ) {
                item {
                    Text(
                        text = "Local performance summary",
                        fontSize = 14.sp,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }

                item {
                    OverallAnalyticsCard(
                        impressions =
                            totalImpressions,
                        clicks =
                            totalClicks,
                        clickThroughRate =
                            overallClickThroughRate
                    )
                }

                if (BuildConfig.DEBUG) {
                    item {
                        ResetAnalyticsButton(
                            enabled =
                                !resetInProgress,
                            onClick = {
                                showResetConfirmation =
                                    true
                            }
                        )
                    }
                }

                item {
                    Text(
                        text = "Product performance",
                        fontSize = 20.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            MaterialTheme.colorScheme
                                .onBackground,
                        modifier =
                            Modifier.padding(
                                top = 8.dp
                            )
                    )
                }

                if (productAnalytics.isEmpty()) {
                    item {
                        EmptyAnalyticsCard()
                    }
                } else {
                    items(
                        items =
                            productAnalytics,
                        key = { summary ->
                            summary.productId
                        }
                    ) { summary ->
                        ProductAnalyticsCard(
                            summary = summary
                        )
                    }
                }

                item {
                    PrivacyNoticeCard()
                }
            }
        }
    }
}

@Composable
private fun AnalyticsHeader(
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            ),
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.clickable {
                onExit()
            },
            shape = RoundedCornerShape(12.dp),
            color = Charcoal
        ) {
            Text(
                text = "<-",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = FightRed,
                modifier = Modifier.padding(
                    horizontal = 14.dp,
                    vertical = 10.dp
                )
            )
        }

        Spacer(
            modifier = Modifier.size(14.dp)
        )

        Column {
            Text(
                text = "Gear Analytics",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Text(
                text = "Internal dashboard",
                fontSize = 13.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun OverallAnalyticsCard(
    impressions: Int,
    clicks: Int,
    clickThroughRate: Double
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {
            AnalyticsMetric(
                value = impressions.toString(),
                label = "Impressions"
            )

            AnalyticsMetric(
                value = clicks.toString(),
                label = "Deal clicks"
            )

            AnalyticsMetric(
                value = formatPercentage(
                    clickThroughRate
                ),
                label = "CTR"
            )
        }
    }
}

@Composable
private fun ResetAnalyticsButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled
            ) {
                onClick()
            },
        shape = RoundedCornerShape(14.dp),
        color = FightRed.copy(
            alpha = if (enabled) {
                0.12f
            } else {
                0.06f
            }
        )
    ) {
        Text(
            text = "RESET TEST DATA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.sp,
            color = FightRed.copy(
                alpha = if (enabled) {
                    1f
                } else {
                    0.5f
                }
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 13.dp
            )
        )
    }
}

@Composable
private fun AnalyticsMetric(
    value: String,
    label: String
) {
    Column(
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 22.sp,
            fontWeight = FontWeight.Black,
            color = FightRed
        )

        Text(
            text = label,
            fontSize = 11.sp,
            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProductAnalyticsCard(
    summary: FightGearProductAnalyticsSummary
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = summary.productName,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.size(4.dp)
            )

            Text(
                text = summary.retailerName,
                fontSize = 13.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.size(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {
                AnalyticsMetric(
                    value =
                        summary.impressions
                            .toString(),
                    label = "Impressions"
                )

                AnalyticsMetric(
                    value =
                        summary.viewDealClicks
                            .toString(),
                    label = "Clicks"
                )

                AnalyticsMetric(
                    value = formatPercentage(
                        summary
                            .clickThroughRatePercent
                    ),
                    label = "CTR"
                )
            }
        }
    }
}

@Composable
private fun EmptyAnalyticsCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Text(
            text =
                "No gear activity has been recorded yet.",
            fontSize = 14.sp,
            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
private fun PrivacyNoticeCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color =
            FightRed.copy(alpha = 0.10f)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Privacy",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.size(6.dp)
            )

            Text(
                text = "These statistics are currently stored only on this device. No name, email address, precise location or payment information is recorded.",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

private fun formatPercentage(
    value: Double
): String {
    return String.format(
        Locale.US,
        "%.1f%%",
        value
    )
}