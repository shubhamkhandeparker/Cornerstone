package com.shubham.cornerstone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun ProgressHubScreen(
    sport: String,
    todaySessionCount: Int,
    todayDurationSeconds: Int,
    trainingPathState:
    TrainingPathViewModel.UiState?
) {
    val progress =
        trainingPathState?.progress

    val currentLesson =
        trainingPathState?.currentLesson

    val currentLevel =
        progress
            ?.level
            ?.let {
                    levelName ->

                runCatching {
                    TrainingPathLevel
                        .valueOf(
                            levelName
                        )
                }.getOrNull()
            }
            ?: TrainingPathLevel.BEGINNER

    val todayMinutes =
        todayDurationSeconds
            .coerceAtLeast(0) / 60

    val scrollState =
        rememberScrollState()

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors =
                            listOf(
                                Color(0xFF161518),
                                InkBlack
                            )
                    )
                )
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        scrollState
                    )
                    .statusBarsPadding()
                    .padding(
                        horizontal =
                            20.dp
                    )
        ) {
            Spacer(
                modifier =
                    Modifier.height(
                        22.dp
                    )
            )

            Text(
                text =
                    "Progress",
                fontSize =
                    32.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(
                        4.dp
                    )
            )

            Text(
                text =
                    "$sport · Your work, clearly.",
                fontSize =
                    13.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(
                        24.dp
                    )
            )

            Surface(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(
                        22.dp
                    ),
                color =
                    FightRed.copy(
                        alpha =
                            0.12f
                    )
            ) {
                Column(
                    modifier =
                        Modifier.padding(
                            20.dp
                        )
                ) {
                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.SpaceBetween,
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text =
                                    "FIGHT PATH",
                                fontSize =
                                    9.sp,
                                fontWeight =
                                    FontWeight.Black,
                                letterSpacing =
                                    1.3.sp,
                                color =
                                    FightRed
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        4.dp
                                    )
                            )

                            Text(
                                text =
                                    currentLevel
                                        .displayName,
                                fontSize =
                                    26.sp,
                                fontWeight =
                                    FontWeight.Black,
                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .onBackground
                            )
                        }

                        Column(
                            horizontalAlignment =
                                Alignment.End
                        ) {
                            Text(
                                text =
                                    "${progress?.xp ?: 0}",
                                fontSize =
                                    28.sp,
                                fontWeight =
                                    FontWeight.Black,
                                color =
                                    FightRed
                            )

                            Text(
                                text =
                                    "XP",
                                fontSize =
                                    10.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.height(
                                20.dp
                            )
                    )

                    Row(
                        modifier =
                            Modifier.fillMaxWidth(),
                        horizontalArrangement =
                            Arrangement.spacedBy(
                                10.dp
                            )
                    ) {
                        ProgressStatCard(
                            value =
                                (
                                        progress
                                            ?.totalLessonsCompleted
                                            ?: 0
                                        )
                                    .toString(),
                            label =
                                "SESSIONS",
                            modifier =
                                Modifier.weight(1f)
                        )

                        ProgressStatCard(
                            value =
                                (
                                        progress
                                            ?.totalChaptersCompleted
                                            ?: 0
                                        )
                                    .toString(),
                            label =
                                "CHAPTERS",
                            modifier =
                                Modifier.weight(1f)
                        )

                        ProgressStatCard(
                            value =
                                (
                                        progress
                                            ?.currentStreakDays
                                            ?: 0
                                        )
                                    .toString(),
                            label =
                                "STREAK",
                            modifier =
                                Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(
                        14.dp
                    )
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {
                TodayProgressCard(
                    value =
                        todaySessionCount
                            .coerceAtLeast(0)
                            .toString(),
                    label =
                        if (
                            todaySessionCount == 1
                        ) {
                            "session today"
                        } else {
                            "sessions today"
                        },
                    modifier =
                        Modifier.weight(1f)
                )

                TodayProgressCard(
                    value =
                        "$todayMinutes",
                    label =
                        "minutes today",
                    modifier =
                        Modifier.weight(1f)
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        14.dp
                    )
            )

            Surface(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(
                        20.dp
                    ),
                color =
                    Charcoal
            ) {
                Column(
                    modifier =
                        Modifier.padding(
                            18.dp
                        )
                ) {
                    Text(
                        text =
                            "NEXT UP",
                        fontSize =
                            9.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing =
                            1.2.sp,
                        color =
                            FightRed
                    )

                    Spacer(
                        modifier =
                            Modifier.height(
                                8.dp
                            )
                    )

                    if (
                        currentLesson != null
                    ) {
                        Text(
                            text =
                                currentLesson.title,
                            fontSize =
                                20.sp,
                            fontWeight =
                                FontWeight.Black,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    4.dp
                                )
                        )

                        Text(
                            text =
                                currentLesson.subtitle,
                            fontSize =
                                11.sp,
                            lineHeight =
                                17.sp,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    12.dp
                                )
                        )

                        Row(
                            modifier =
                                Modifier.fillMaxWidth(),
                            horizontalArrangement =
                                Arrangement.SpaceBetween,
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {
                            Text(
                                text =
                                    "+${currentLesson.xpReward} XP",
                                fontSize =
                                    11.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                color =
                                    FightRed
                            )

                            Text(
                                text =
                                    "${currentLesson.requiredActiveSeconds / 60} min minimum",
                                fontSize =
                                    10.sp,
                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                            )
                        }
                    } else {
                        Text(
                            text =
                                "Fight Path complete",
                            fontSize =
                                20.sp,
                            fontWeight =
                                FontWeight.Black,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    4.dp
                                )
                        )

                        Text(
                            text =
                                "You cleared every currently available session.",
                            fontSize =
                                11.sp,
                            lineHeight =
                                17.sp,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(
                        14.dp
                    )
            )

            Surface(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(
                        18.dp
                    ),
                color =
                    Color.White.copy(
                        alpha =
                            0.035f
                    )
            ) {
                Row(
                    modifier =
                        Modifier.padding(
                            16.dp
                        ),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    Text(
                        text =
                            "🔥",
                        fontSize =
                            24.sp
                    )

                    Spacer(
                        modifier =
                            Modifier.padding(
                                horizontal =
                                    7.dp
                            )
                    )

                    Column(
                        modifier =
                            Modifier.weight(1f)
                    ) {
                        Text(
                            text =
                                streakHeadline(
                                    streakDays =
                                        progress
                                            ?.currentStreakDays
                                            ?: 0
                                ),
                            fontSize =
                                12.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    2.dp
                                )
                        )

                        Text(
                            text =
                                "Progress comes from completed training, not simply opening the app.",
                            fontSize =
                                10.sp,
                            lineHeight =
                                15.sp,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )
                    }
                }
            }

            /*
             * Keeps the final card clear of
             * Cornerstone's bottom navigation.
             */
            Spacer(
                modifier =
                    Modifier.height(
                        48.dp
                    )
            )
        }
    }
}

@Composable
private fun ProgressStatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier =
            modifier
                .height(
                    82.dp
                ),
        shape =
            RoundedCornerShape(
                16.dp
            ),
        color =
            Color.Black.copy(
                alpha =
                    0.18f
            )
    ) {
        Column(
            modifier =
                Modifier.fillMaxSize(),
            horizontalAlignment =
                Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.Center
        ) {
            Text(
                text =
                    value,
                fontSize =
                    19.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(
                        3.dp
                    )
            )

            Text(
                text =
                    label,
                fontSize =
                    8.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing =
                    0.8.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun TodayProgressCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier =
            modifier
                .height(
                    106.dp
                ),
        shape =
            RoundedCornerShape(
                18.dp
            ),
        color =
            Charcoal
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        16.dp
                    ),
            verticalArrangement =
                Arrangement.Center
        ) {
            Text(
                text =
                    value,
                fontSize =
                    28.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(
                        2.dp
                    )
            )

            Text(
                text =
                    label,
                fontSize =
                    11.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

private fun streakHeadline(
    streakDays: Int
): String {

    return when {
        streakDays <= 0 -> {
            "Start your training streak"
        }

        streakDays == 1 -> {
            "1 day of work"
        }

        else -> {
            "$streakDays days of work"
        }
    }
}