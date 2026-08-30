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
import androidx.compose.foundation.layout.navigationBarsPadding
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
fun MoreHubScreen(
    sport: String,
    hasWeightPlan: Boolean,
    onOpenChallenges: () -> Unit,
    onOpenTechniques: () -> Unit,
    onOpenFightGear: () -> Unit,
    onOpenWeightCut: () -> Unit,
    onOpenProgressCamera: () -> Unit,
    onOpenGlossary: () -> Unit
) {
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
                    .navigationBarsPadding()
                    .padding(
                        horizontal = 20.dp
                    )
        ) {
            Spacer(
                modifier =
                    Modifier.height(22.dp)
            )

            Text(
                text = "More",
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
                    "Tools, learning and everything else.",
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

            MoreSectionTitle(
                title = "Keep improving"
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {
                MoreFeatureCard(
                    title =
                        "Challenges",
                    subtitle =
                        "Goals, streaks\n& rewards",
                    symbol =
                        "◆",
                    badge =
                        "CHALLENGE",
                    highlighted =
                        true,
                    modifier =
                        Modifier.weight(1f),
                    onClick =
                        onOpenChallenges
                )

                MoreFeatureCard(
                    title =
                        "Techniques",
                    subtitle =
                        "Learn movements\nclearly",
                    symbol =
                        "◎",
                    badge =
                        "LEARN",
                    modifier =
                        Modifier.weight(1f),
                    onClick =
                        onOpenTechniques
                )
            }

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            MoreWideRow(
                title =
                    "Fight Gear",
                subtitle =
                    "Browse fighter gear and current deals.",
                badge =
                    "GEAR",
                symbol =
                    "◈",
                onClick =
                    onOpenFightGear
            )

            Spacer(
                modifier =
                    Modifier.height(26.dp)
            )

            MoreSectionTitle(
                title = "Body & progress"
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            MoreWideRow(
                title =
                    "Weight Cut",
                subtitle =
                    if (
                        hasWeightPlan
                    ) {
                        "Log your weight and check your pace."
                    } else {
                        "Set a target weight and fight timeline."
                    },
                badge =
                    if (
                        hasWeightPlan
                    ) {
                        "TRACK"
                    } else {
                        "SET UP"
                    },
                symbol =
                    "↘",
                onClick =
                    onOpenWeightCut
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            MoreWideRow(
                title =
                    "Progress Camera",
                subtitle =
                    "Track visual progress with photos and clips.",
                badge =
                    "CAMERA",
                symbol =
                    "◉",
                onClick =
                    onOpenProgressCamera
            )

            Spacer(
                modifier =
                    Modifier.height(26.dp)
            )

            MoreSectionTitle(
                title = "Learn"
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            MoreWideRow(
                title =
                    glossaryTitle(
                        sport = sport
                    ),
                subtitle =
                    glossarySubtitle(
                        sport = sport
                    ),
                badge =
                    "GLOSSARY",
                symbol =
                    "?",
                onClick =
                    onOpenGlossary
            )

            Spacer(
                modifier =
                    Modifier.height(22.dp)
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
                                text = "C",
                                fontSize = 15.sp,
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
                                "Cornerstone",
                            fontSize = 12.sp,
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
                                    2.dp
                                )
                        )

                        Text(
                            text =
                                "Train. Progress. Keep showing up.",
                            fontSize = 10.sp,
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
                    Modifier.height(90.dp)
            )
        }
    }
}

@Composable
private fun MoreSectionTitle(
    title: String
) {
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
}

@Composable
private fun MoreFeatureCard(
    title: String,
    subtitle: String,
    symbol: String,
    badge: String,
    highlighted: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier =
            modifier
                .height(
                    180.dp
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
                        16.dp
                    ),
            verticalArrangement =
                Arrangement.SpaceBetween
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
                            fontSize = 18.sp,
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

                Text(
                    text = "→",
                    fontSize = 18.sp,
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

            Column {
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
                            5.dp
                        )
                )

                Text(
                    text =
                        title,
                    fontSize = 18.sp,
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
private fun MoreWideRow(
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
                    16.dp
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
                        fontSize = 18.sp,
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
                    fontSize = 16.sp,
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
                            2.dp
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

            Text(
                text = "→",
                fontSize = 21.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    FightRed
            )
        }
    }
}

private fun glossaryTitle(
    sport: String
): String {

    return when (sport) {
        "Muay Thai" -> {
            "Muay Thai Glossary"
        }

        "Kickboxing" -> {
            "Kickboxing Glossary"
        }

        "MMA" -> {
            "MMA Glossary"
        }

        else -> {
            "Boxing Glossary"
        }
    }
}

private fun glossarySubtitle(
    sport: String
): String {

    return when (sport) {
        "Muay Thai" -> {
            "Understand strikes, knees, elbows and common terms."
        }

        "Kickboxing" -> {
            "Understand punches, kicks and movement terminology."
        }

        "MMA" -> {
            "Understand striking, wrestling and fight terminology."
        }

        else -> {
            "Understand punches, numbers and boxing terminology."
        }
    }
}