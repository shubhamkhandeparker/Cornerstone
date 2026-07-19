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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.FightRedDark
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun HomeScreen(
    profile: UserProfile,
    todaySessionCount: Int,
    todayDurationSeconds: Int,
    onStartSession: () -> Unit,
    onOpenPlaylists: () -> Unit,
    onOpenGlossary: () -> Unit,
    onOpenTechniques: () -> Unit,
    onOpenWeightCut: () -> Unit,
    onOpenProgressCamera: () -> Unit,
    onOpenFightGearDeals: () -> Unit = {},
    onOpenChallenges: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

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
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(
                modifier = Modifier.height(20.dp)
            )

            HomeHeader(
                sport = profile.sport
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            TodayTrainingCard(
                todaySessionCount = todaySessionCount,
                todayDurationSeconds =
                    todayDurationSeconds
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            SessionCard(
                sport = profile.sport,
                level = profile.level,
                sessionNumber =
                    profile.sessionsCompleted + 1,
                onClick = onStartSession
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            SectionTitle(
                title = "Quick access",
                subtitle =
                    "Everything you need to keep improving"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                QuickActionCard(
                    title = "Challenges",
                    subtitle = "Build streaks",
                    badge = "NEW",
                    highlighted = true,
                    modifier = Modifier.weight(1f),
                    onClick = onOpenChallenges
                )

                QuickActionCard(
                    title = "Playlists",
                    subtitle = "Saved combos",
                    badge = "TRAIN",
                    modifier = Modifier.weight(1f),
                    onClick = onOpenPlaylists
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                QuickActionCard(
                    title = "Techniques",
                    subtitle = "Learn clearly",
                    badge = "LEARN",
                    modifier = Modifier.weight(1f),
                    onClick = onOpenTechniques
                )

                QuickActionCard(
                    title = "Fight gear",
                    subtitle = "Deals and gear",
                    badge = "GEAR",
                    modifier = Modifier.weight(1f),
                    onClick = onOpenFightGearDeals
                )
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            SectionTitle(
                title = "Tools",
                subtitle = "Progress and beginner support"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            ToolsCard(
                sport = profile.sport,
                hasWeightPlan =
                    profile.targetWeightKg != null &&
                            profile.fightDateEpochDay != null,
                onOpenWeightCut = onOpenWeightCut,
                onOpenProgressCamera =
                    onOpenProgressCamera,
                onOpenGlossary = onOpenGlossary
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )
        }
    }
}

@Composable
private fun HomeHeader(
    sport: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
            Arrangement.SpaceBetween,
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Evening, fighter.",
                fontSize = 13.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = "Time to work.",
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )
        }

        Surface(
            modifier = Modifier.size(44.dp),
            shape = CircleShape,
            color = Charcoal
        ) {
            Box(
                contentAlignment =
                    Alignment.Center
            ) {
                Text(
                    text = sport
                        .take(1)
                        .uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = FightRed
                )
            }
        }
    }
}

@Composable
private fun TodayTrainingCard(
    todaySessionCount: Int,
    todayDurationSeconds: Int
) {
    val minutes = todayDurationSeconds / 60
    val seconds = todayDurationSeconds % 60

    val durationText =
        when {
            todayDurationSeconds <= 0 -> {
                "0 min"
            }

            minutes <= 0 -> {
                "${seconds}s"
            }

            seconds == 0 -> {
                "$minutes min"
            }

            else -> {
                "$minutes min ${seconds}s"
            }
        }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = FightRed.copy(alpha = 0.12f)
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 14.dp
            ),
            verticalAlignment =
                Alignment.CenterVertically,
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Today’s work",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme.colorScheme
                            .onBackground
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text =
                        if (todaySessionCount == 0) {
                            "Your first round is waiting"
                        } else {
                            "Keep the momentum going"
                        },
                    fontSize = 12.sp,
                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )
            }

            TodayStatBlock(
                value =
                    todaySessionCount.toString(),
                label =
                    if (todaySessionCount == 1) {
                        "session"
                    } else {
                        "sessions"
                    }
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            TodayStatBlock(
                value = durationText,
                label = "trained"
            )
        }
    }
}

@Composable
private fun TodayStatBlock(
    value: String,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = value,
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            color = FightRed
        )

        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant
        )
    }
}

@Composable
private fun SectionTitle(
    title: String,
    subtitle: String
) {
    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color =
                MaterialTheme.colorScheme
                    .onBackground
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = subtitle,
            fontSize = 12.sp,
            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant
        )
    }
}

@Composable
private fun QuickActionCard(
    title: String,
    subtitle: String,
    badge: String,
    modifier: Modifier = Modifier,
    highlighted: Boolean = false,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(116.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(18.dp),
        color =
            if (highlighted) {
                FightRed.copy(alpha = 0.13f)
            } else {
                Charcoal
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            verticalArrangement =
                Arrangement.SpaceBetween
        ) {
            Surface(
                shape = RoundedCornerShape(999.dp),
                color =
                    if (highlighted) {
                        FightRed.copy(alpha = 0.18f)
                    } else {
                        Color(0xFF29282C)
                    }
            ) {
                Text(
                    text = badge,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.8.sp,
                    color =
                        if (highlighted) {
                            FightRed
                        } else {
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                        },
                    modifier = Modifier.padding(
                        horizontal = 9.dp,
                        vertical = 5.dp
                    )
                )
            }

            Column {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    color =
                        MaterialTheme.colorScheme
                            .onBackground
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color =
                        MaterialTheme.colorScheme
                            .onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ToolsCard(
    sport: String,
    hasWeightPlan: Boolean,
    onOpenWeightCut: () -> Unit,
    onOpenProgressCamera: () -> Unit,
    onOpenGlossary: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Charcoal
    ) {
        Column {
            WeightToolRow(
                hasPlan = hasWeightPlan,
                onOpenWeightCut =
                    onOpenWeightCut,
                onOpenProgressCamera =
                    onOpenProgressCamera
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(
                        Color.White.copy(
                            alpha = 0.06f
                        )
                    )
            )

            GlossaryToolRow(
                sport = sport,
                onClick = onOpenGlossary
            )
        }
    }
}

@Composable
private fun WeightToolRow(
    hasPlan: Boolean,
    onOpenWeightCut: () -> Unit,
    onOpenProgressCamera: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment =
            Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onOpenWeightCut()
                }
        ) {
            Text(
                text = "Weight cut",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text =
                    if (hasPlan) {
                        "Log weight and check your pace"
                    } else {
                        "Set your target and timeline"
                    },
                fontSize = 12.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )
        }

        CompactToolButton(
            text = "CAM",
            onClick = onOpenProgressCamera
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        CompactToolButton(
            text =
                if (hasPlan) {
                    "TRACK"
                } else {
                    "SET UP"
                },
            onClick = onOpenWeightCut
        )
    }
}

@Composable
private fun GlossaryToolRow(
    sport: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(16.dp),
        verticalAlignment =
            Alignment.CenterVertically,
        horizontalArrangement =
            Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = glossaryTitle(sport),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = glossarySubtitle(sport),
                fontSize = 12.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )
        }

        Text(
            text = "→",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = FightRed
        )
    }
}

@Composable
private fun CompactToolButton(
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable {
            onClick()
        },
        shape = RoundedCornerShape(999.dp),
        color = FightRed.copy(alpha = 0.14f)
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.7.sp,
            color = FightRed,
            modifier = Modifier.padding(
                horizontal = 11.dp,
                vertical = 7.dp
            )
        )
    }
}

private fun glossaryTitle(
    sport: String
): String {
    return when (sport) {
        "Muay Thai" -> {
            "New to Muay Thai?"
        }

        "MMA" -> {
            "New to MMA?"
        }

        else -> {
            "New to boxing?"
        }
    }
}

private fun glossarySubtitle(
    sport: String
): String {
    return when (sport) {
        "Muay Thai" -> {
            "Learn strikes, knees and elbows"
        }

        "MMA" -> {
            "Learn strikes and fight terms"
        }

        else -> {
            "Learn what 1, 2 and 3 mean"
        }
    }
}

@Composable
private fun SessionCard(
    sport: String,
    level: String,
    sessionNumber: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(228.dp)
            .clip(
                RoundedCornerShape(22.dp)
            )
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(22.dp),
        color = Charcoal
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                FightRedDark.copy(
                                    alpha = 0.55f
                                ),
                                Color.Transparent
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .size(200.dp)
                    .blur(85.dp)
                    .background(
                        FightRed.copy(
                            alpha = 0.19f
                        ),
                        CircleShape
                    )
                    .align(
                        Alignment.BottomEnd
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp),
                verticalArrangement =
                    Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(FightRed)
                    )

                    Spacer(
                        modifier =
                            Modifier.width(8.dp)
                    )

                    Text(
                        text =
                            "SESSION $sessionNumber",
                        fontSize = 11.sp,
                        fontWeight =
                            FontWeight.Bold,
                        letterSpacing = 1.8.sp,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }

                Column {
                    Text(
                        text =
                            "$sport\nShadow + Drills",
                        fontSize = 29.sp,
                        lineHeight = 33.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            MaterialTheme.colorScheme
                                .onBackground
                    )

                    Spacer(
                        modifier =
                            Modifier.height(5.dp)
                    )

                    Text(
                        text =
                            "$level · adapted to you",
                        fontSize = 13.sp,
                        color =
                            MaterialTheme.colorScheme
                                .onSurfaceVariant
                    )
                }

                Surface(
                    modifier =
                        Modifier.fillMaxWidth(),
                    shape =
                        RoundedCornerShape(13.dp),
                    color = FightRed
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        contentAlignment =
                            Alignment.Center
                    ) {
                        Text(
                            text = "Start training  →",
                            fontSize = 15.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}