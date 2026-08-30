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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun ConditioningModePickerScreen(
    lessonTitle: String,
    levelName: String,
    completedFightPathSessions: Int,
    onSelectMode: (ConditioningMode) -> Unit,
    onBack: () -> Unit
) {
    val mixedPlan =
        ConditioningSessionPlanner.create(
            mode =
                ConditioningMode.TECHNIQUE_PLUS_CONDITIONING,
            completedFightPathSessions =
                completedFightPathSessions
        )

    val conditioningOnlyPlan =
        ConditioningSessionPlanner.create(
            mode =
                ConditioningMode.CONDITIONING_ONLY,
            completedFightPathSessions =
                completedFightPathSessions
        )

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
                    Modifier.height(14.dp)
            )

            Text(
                text =
                    "← Back",
                fontSize =
                    14.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,
                modifier =
                    Modifier
                        .clickable {
                            onBack()
                        }
                        .padding(
                            vertical =
                                8.dp
                        )
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(
                text =
                    "Choose your training",
                fontSize =
                    30.sp,
                lineHeight =
                    34.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )

            Text(
                text =
                    "$levelName · $lessonTitle",
                fontSize =
                    13.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )

            Text(
                text =
                    "Choose how hard you want today's session to be.",
                fontSize =
                    13.sp,
                lineHeight =
                    19.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            ModeCard(
                title =
                    "Technique Only",
                subtitle =
                    "Focus only on today's Fight Path lesson.",
                badge =
                    "TECHNIQUE",
                detail =
                    "No conditioning blocks",
                highlighted =
                    false,
                onClick = {
                    onSelectMode(
                        ConditioningMode
                            .TECHNIQUE_ONLY
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            ModeCard(
                title =
                    "Technique + Conditioning",
                subtitle =
                    "Train the lesson, then mix in fighter conditioning.",
                badge =
                    "RECOMMENDED",
                detail =
                    conditioningSummary(
                        plan =
                            mixedPlan
                    ),
                highlighted =
                    true,
                onClick = {
                    onSelectMode(
                        ConditioningMode
                            .TECHNIQUE_PLUS_CONDITIONING
                    )
                }
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            ModeCard(
                title =
                    "Conditioning Only",
                subtitle =
                    "Skip technique today and run a no-equipment conditioning circuit.",
                badge =
                    "CONDITIONING",
                detail =
                    conditioningSummary(
                        plan =
                            conditioningOnlyPlan
                    ),
                highlighted =
                    false,
                onClick = {
                    onSelectMode(
                        ConditioningMode
                            .CONDITIONING_ONLY
                    )
                }
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
                        16.dp
                    ),
                color =
                    Color.White.copy(
                        alpha =
                            0.04f
                    )
            ) {
                Row(
                    modifier =
                        Modifier.padding(
                            14.dp
                        ),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    Surface(
                        modifier =
                            Modifier.height(34.dp),
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
                                        11.dp
                                ),
                            contentAlignment =
                                Alignment.Center
                        ) {
                            Text(
                                text =
                                    "⚡",
                                fontSize =
                                    15.sp
                            )
                        }
                    }

                    Spacer(
                        modifier =
                            Modifier.padding(
                                horizontal =
                                    6.dp
                            )
                    )

                    Text(
                        text =
                            "Conditioning gets harder as your Fight Path progress increases.",
                        modifier =
                            Modifier.weight(1f),
                        fontSize =
                            11.sp,
                        lineHeight =
                            16.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            Text(
                text =
                    "You can choose a different mode every training session.",
                modifier =
                    Modifier.fillMaxWidth(),
                fontSize =
                    11.sp,
                lineHeight =
                    16.sp,
                textAlign =
                    TextAlign.Center,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                        .copy(
                            alpha =
                                0.75f
                        )
            )

            // Keeps final text fully visible above
            // smaller-screen navigation areas.
            Spacer(
                modifier =
                    Modifier.height(48.dp)
            )
        }
    }
}

@Composable
private fun ModeCard(
    title: String,
    subtitle: String,
    badge: String,
    detail: String,
    highlighted: Boolean,
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
                Modifier.padding(
                    18.dp
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
                    Text(
                        text =
                            badge,
                        fontSize =
                            9.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing =
                            1.sp,
                        color =
                            if (
                                highlighted
                            ) {
                                FightRed
                            } else {
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                            },
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
                        "→",
                    fontSize =
                        22.sp,
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
                                .onSurfaceVariant
                        }
                )
            }

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(
                text =
                    title,
                fontSize =
                    19.sp,
                lineHeight =
                    23.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text =
                    subtitle,
                fontSize =
                    12.sp,
                lineHeight =
                    18.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Text(
                text =
                    detail,
                fontSize =
                    11.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    if (
                        highlighted
                    ) {
                        FightRed
                    } else {
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                    }
            )
        }
    }
}

private fun conditioningSummary(
    plan:
    ConditioningSessionPlan
): String {

    if (
        !plan.hasConditioning
    ) {
        return "No conditioning"
    }

    val blocks =
        plan.allBlocks.size

    val minutes =
        (
                plan.estimatedConditioningSeconds +
                        59
                ) / 60

    return buildString {
        append(
            blocks
        )

        append(
            if (
                blocks == 1
            ) {
                " conditioning block"
            } else {
                " conditioning blocks"
            }
        )

        append(
            " · about "
        )

        append(
            minutes.coerceAtLeast(1)
        )

        append(
            " min"
        )
    }
}