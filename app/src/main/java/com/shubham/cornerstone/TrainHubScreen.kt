package com.shubham.cornerstone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
fun TrainHubScreen(
    sport: String,
    trainingPathState: TrainingPathViewModel.UiState?,
    onContinueFightPath: () -> Unit,
    onFreeTraining: () -> Unit,
    onConditioningOnly: () -> Unit,
    onOpenPlaylists: () -> Unit
) {
    val currentLesson =
        trainingPathState?.currentLesson

    val progress =
        trainingPathState?.progress

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
                        horizontal = 20.dp
                    )
        ) {
            Spacer(
                modifier =
                    Modifier.height(22.dp)
            )

            Text(
                text = "Train",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    "$sport · Choose how you want to work today.",
                fontSize = 13.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            if (
                currentLesson != null
            ) {
                CurrentFightPathTrainCard(
                    lessonTitle =
                        currentLesson.title,
                    levelName =
                        currentLesson
                            .level
                            .displayName,
                    xp =
                        progress?.xp ?: 0,
                    onClick =
                        onContinueFightPath
                )

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )
            }

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {
                TrainShortcutCard(
                    title =
                        "Free\nTraining",
                    subtitle =
                        "Custom shadowboxing",
                    symbol =
                        "◉",
                    highlighted =
                        true,
                    modifier =
                        Modifier.weight(1f),
                    onClick =
                        onFreeTraining
                )

                TrainShortcutCard(
                    title =
                        "Conditioning\nOnly",
                    subtitle =
                        "No-equipment circuit",
                    symbol =
                        "⚡",
                    modifier =
                        Modifier.weight(1f),
                    onClick =
                        onConditioningOnly
                )
            }

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            TrainWideCard(
                title =
                    "Playlists",
                subtitle =
                    "Train combinations you saved yourself.",
                badge =
                    "SAVED COMBOS",
                symbol =
                    "▤",
                onClick =
                    onOpenPlaylists
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
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
                    Surface(
                        modifier =
                            Modifier.height(
                                38.dp
                            ),
                        shape =
                            CircleShape,
                        color =
                            FightRed.copy(
                                alpha =
                                    0.14f
                            )
                    ) {
                        Box(
                            modifier =
                                Modifier.padding(
                                    horizontal =
                                        12.dp
                                ),
                            contentAlignment =
                                Alignment.Center
                        ) {
                            Text(
                                text = "↗",
                                fontSize = 17.sp,
                                fontWeight =
                                    FontWeight.Black,
                                color =
                                    FightRed
                            )
                        }
                    }

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
                                "Your Fight Path stays separate",
                            fontSize = 12.sp,
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
                                "Free Training and Conditioning Only do not skip or falsely complete your next lesson.",
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )
                    }
                }
            }

            // Allows the last card to scroll clearly
            // above Cornerstone's bottom navigation.
            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )
        }
    }
}

@Composable
private fun CurrentFightPathTrainCard(
    lessonTitle: String,
    levelName: String,
    xp: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
        shape =
            RoundedCornerShape(
                22.dp
            ),
        color =
            FightRed.copy(
                alpha =
                    0.13f
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
                Surface(
                    shape =
                        RoundedCornerShape(
                            999.dp
                        ),
                    color =
                        FightRed.copy(
                            alpha =
                                0.17f
                        )
                ) {
                    Text(
                        text =
                            "CONTINUE PATH",
                        fontSize = 9.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing = 1.sp,
                        color =
                            FightRed,
                        modifier =
                            Modifier.padding(
                                horizontal =
                                    10.dp,
                                vertical =
                                    6.dp
                            )
                    )
                }

                Text(
                    text =
                        "$xp XP",
                    fontSize = 12.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        18.dp
                    )
            )

            Text(
                text =
                    lessonTitle,
                fontSize = 24.sp,
                lineHeight = 28.sp,
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
                        5.dp
                    )
            )

            Text(
                text =
                    "$levelName · Technique / Conditioning options available",
                fontSize = 11.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(
                        18.dp
                    )
            )

            Row(
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                Text(
                    text =
                        "Start today's session",
                    fontSize = 13.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        FightRed
                )

                Spacer(
                    modifier =
                        Modifier.weight(1f)
                )

                Text(
                    text = "→",
                    fontSize = 22.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        FightRed
                )
            }
        }
    }
}

@Composable
private fun TrainShortcutCard(
    title: String,
    subtitle: String,
    symbol: String,
    highlighted: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier =
            modifier
                .height(
                    178.dp
                )
                .clickable {
                    onClick()
                },
        shape =
            RoundedCornerShape(
                20.dp
            ),
        color =
            if (
                highlighted
            ) {
                FightRed.copy(
                    alpha =
                        0.11f
                )
            } else {
                Charcoal
            }
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(
                        17.dp
                    ),
            verticalArrangement =
                Arrangement.SpaceBetween
        ) {
            Surface(
                modifier =
                    Modifier.height(
                        42.dp
                    ),
                shape =
                    CircleShape,
                color =
                    if (
                        highlighted
                    ) {
                        FightRed.copy(
                            alpha =
                                0.18f
                        )
                    } else {
                        Color.White.copy(
                            alpha =
                                0.05f
                        )
                    }
            ) {
                Box(
                    modifier =
                        Modifier.padding(
                            horizontal =
                                13.dp
                        ),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            symbol,
                        fontSize = 19.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            if (
                                highlighted
                            ) {
                                FightRed
                            } else {
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                            }
                    )
                }
            }

            Column {
                Text(
                    text =
                        title,
                    fontSize = 19.sp,
                    lineHeight = 22.sp,
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
                            6.dp
                        )
                )

                Text(
                    text =
                        subtitle,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun TrainWideCard(
    title: String,
    subtitle: String,
    badge: String,
    symbol: String,
    onClick: () -> Unit
) {
    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },
        shape =
            RoundedCornerShape(
                18.dp
            ),
        color =
            Charcoal
    ) {
        Row(
            modifier =
                Modifier.padding(
                    17.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Surface(
                modifier =
                    Modifier.height(
                        46.dp
                    ),
                shape =
                    CircleShape,
                color =
                    Color.White.copy(
                        alpha =
                            0.05f
                    )
            ) {
                Box(
                    modifier =
                        Modifier.padding(
                            horizontal =
                                14.dp
                        ),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            symbol,
                        fontSize = 19.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            FightRed
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.padding(
                        horizontal =
                            8.dp
                    )
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {
                Text(
                    text =
                        badge,
                    fontSize = 8.sp,
                    fontWeight =
                        FontWeight.Black,
                    letterSpacing = 1.sp,
                    color =
                        FightRed
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            3.dp
                        )
                )

                Text(
                    text =
                        title,
                    fontSize = 17.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text =
                        subtitle,
                    fontSize = 10.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            Text(
                text = "→",
                fontSize = 22.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    FightRed
            )
        }
    }
}