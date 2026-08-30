package com.shubham.cornerstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlin.math.ceil

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
    onOpenChallenges: () -> Unit = {},
    trainingPathState: TrainingPathViewModel.UiState? = null,
    onContinueFightPath: () -> Unit = onStartSession,
    onOpenFreeTraining: () -> Unit = onStartSession,
    onOpenFightPath: () -> Unit = {}
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
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp)
        ) {
            Spacer(
                modifier =
                    Modifier.height(26.dp)
            )

            ModernHomeHeader(
                sport = profile.sport
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            CompactTodayCard(
                sessionCount = todaySessionCount,
                durationSeconds = todayDurationSeconds
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            val pathState =
                trainingPathState

            when {
                pathState != null &&
                        !pathState.isLoading &&
                        pathState.currentLesson != null &&
                        pathState.progress != null -> {

                    CurrentSessionHeroCard(
                        state = pathState,
                        onContinue = onContinueFightPath,
                        onOpenPath = onOpenFightPath
                    )
                }

                pathState != null &&
                        !pathState.isLoading &&
                        pathState.isAvailablePathComplete -> {

                    PathCompleteHeroCard(
                        state = pathState,
                        onOpenPath = onOpenFightPath
                    )
                }

                else -> {
                    SimpleTrainingHeroCard(
                        sport = profile.sport,
                        onClick = onStartSession
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Text(
                text = "Quick start",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                ModernShortcutCard(
                    title = "Challenges",
                    subtitle = "Goals & rewards",
                    symbol = "◆",
                    highlighted = true,
                    modifier =
                        Modifier.weight(1f),
                    onClick = onOpenChallenges
                )

                ModernShortcutCard(
                    title = "Free Train",
                    subtitle = "Your own session",
                    symbol = "◉",
                    modifier =
                        Modifier.weight(1f),
                    onClick = onOpenFreeTraining
                )
            }

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            HomeTipCard()

            Spacer(
                modifier =
                    Modifier.height(40.dp)
            )
        }
    }
}

@Composable
private fun ModernHomeHeader(
    sport: String
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
                text = "Evening, fighter.",
                fontSize = 13.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(2.dp)
            )

            Text(
                text = "Time to work.",
                fontSize = 29.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )
        }

        Surface(
            modifier =
                Modifier.size(48.dp),
            shape =
                CircleShape,
            color =
                Charcoal
        ) {
            Box(
                contentAlignment =
                    Alignment.Center
            ) {
                Text(
                    text =
                        sport
                            .take(1)
                            .uppercase(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color =
                        FightRed
                )
            }
        }
    }
}

@Composable
private fun CompactTodayCard(
    sessionCount: Int,
    durationSeconds: Int
) {
    val minutes =
        durationSeconds / 60

    val seconds =
        durationSeconds % 60

    val duration =
        when {
            durationSeconds <= 0 -> {
                "0 min"
            }

            minutes <= 0 -> {
                "${seconds}s"
            }

            seconds == 0 -> {
                "$minutes min"
            }

            else -> {
                "$minutes:${seconds.toString().padStart(2, '0')}"
            }
        }

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(18.dp),
        color =
            FightRed.copy(
                alpha = 0.10f
            )
    ) {
        Row(
            modifier =
                Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column(
                modifier =
                    Modifier.weight(1f)
            ) {
                Text(
                    text = "Today's work",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )

                Text(
                    text =
                        if (sessionCount > 0) {
                            "Keep the momentum going."
                        } else {
                            "Your first round is waiting."
                        },
                    fontSize = 10.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            MiniTodayStat(
                value =
                    sessionCount.toString(),
                label =
                    if (sessionCount == 1) {
                        "session"
                    } else {
                        "sessions"
                    }
            )

            Spacer(
                modifier =
                    Modifier.size(18.dp)
            )

            MiniTodayStat(
                value = duration,
                label = "trained"
            )
        }
    }
}

@Composable
private fun MiniTodayStat(
    value: String,
    label: String
) {
    Column(
        horizontalAlignment =
            Alignment.End
    ) {
        Text(
            text = value,
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
            color = FightRed
        )

        Text(
            text = label,
            fontSize = 9.sp,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )
    }
}

@Composable
private fun CurrentSessionHeroCard(
    state: TrainingPathViewModel.UiState,
    onContinue: () -> Unit,
    onOpenPath: () -> Unit
) {
    val progress =
        state.progress
            ?: return

    val lesson =
        state.currentLesson
            ?: return

    val level =
        runCatching {
            TrainingPathLevel.valueOf(
                progress.level
            )
        }.getOrDefault(
            TrainingPathLevel.BEGINNER
        )

    val heroImageResource =
        when {
            state.sport.equals(
                TrainingCurriculum.SPORT_KICKBOXING,
                ignoreCase = true
            ) -> {
                R.drawable.home_hero_kickboxing
            }

            else -> {
                R.drawable.cornerstone_fighter_hero
            }
        }

    val levelLessons =
        TrainingCurriculum
            .lessonsForSport(
                sport = state.sport,
                level = level
            )

    val completed =
        levelLessons.count {
            it.id in state.completedLessonIds
        }

    val sessionNumber =
        (
                levelLessons
                    .indexOfFirst {
                        it.id == lesson.id
                    } + 1
                )
            .coerceAtLeast(1)

    val totalSessions =
        levelLessons
            .size
            .coerceAtLeast(1)

    val progressFraction =
        (
                completed.toFloat() /
                        totalSessions.toFloat()
                )
            .coerceIn(
                0f,
                1f
            )

    val estimatedWeeks =
        ceil(
            totalSessions / 3.0
        ).toInt()

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(26.dp),
        color =
            Charcoal
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors =
                                listOf(
                                    Color(0xFF74201D),
                                    Color(0xFF3C1919),
                                    Color(0xFF1C1B1E),
                                    Color(0xFF18171A)
                                )
                        )
                    )
        ) {
            Box(
                modifier =
                    Modifier.matchParentSize()
            ) {
                Image(
                    painter =
                        painterResource(
                            id =
                                heroImageResource
                        ),
                    contentDescription =
                        null,
                    contentScale =
                        ContentScale.Crop,
                    alignment =
                        Alignment.Center,
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.63f)
                            .align(
                                Alignment.CenterEnd
                            )
                )

                Box(
                    modifier =
                        Modifier
                            .matchParentSize()
                            .background(
                                Brush.horizontalGradient(
                                    colorStops =
                                        arrayOf(
                                            0.00f to
                                                    Color(0xFF74201D),

                                            0.34f to
                                                    Color(0xFF51201E)
                                                        .copy(
                                                            alpha = 0.97f
                                                        ),

                                            0.50f to
                                                    Color(0xFF2A1B1D)
                                                        .copy(
                                                            alpha = 0.78f
                                                        ),

                                            0.66f to
                                                    Color(0xFF18171A)
                                                        .copy(
                                                            alpha = 0.27f
                                                        ),

                                            0.84f to
                                                    Color.Transparent,

                                            1.00f to
                                                    Color.Black.copy(
                                                        alpha = 0.08f
                                                    )
                                        )
                                )
                            )
                )

                Box(
                    modifier =
                        Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    colorStops =
                                        arrayOf(
                                            0.00f to
                                                    Color.Black.copy(
                                                        alpha = 0.03f
                                                    ),

                                            0.38f to
                                                    Color.Transparent,

                                            0.64f to
                                                    Color(0xFF18171A)
                                                        .copy(
                                                            alpha = 0.20f
                                                        ),

                                            0.82f to
                                                    Color(0xFF18171A)
                                                        .copy(
                                                            alpha = 0.76f
                                                        ),

                                            1.00f to
                                                    Color(0xFF18171A)
                                        )
                                )
                            )
                )

                Box(
                    modifier =
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.58f)
                            .align(
                                Alignment.CenterEnd
                            )
                            .background(
                                FightRed.copy(
                                    alpha = 0.035f
                                )
                            )
                )
            }

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {
                Row(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier =
                            Modifier.fillMaxWidth(0.58f)
                    ) {
                        Surface(
                            shape =
                                RoundedCornerShape(
                                    999.dp
                                ),
                            color =
                                FightRed.copy(
                                    alpha = 0.20f
                                )
                        ) {
                            Text(
                                text =
                                    "${state.sport.uppercase()} · ${level.displayName.uppercase()}",
                                modifier =
                                    Modifier.padding(
                                        horizontal = 10.dp,
                                        vertical = 6.dp
                                    ),
                                fontSize = 9.sp,
                                letterSpacing = 0.8.sp,
                                fontWeight = FontWeight.Black,
                                color = FightRed
                            )
                        }

                        Spacer(
                            modifier =
                                Modifier.height(24.dp)
                        )

                        Text(
                            text =
                                "CURRENT SESSION",
                            fontSize = 9.sp,
                            letterSpacing = 1.2.sp,
                            fontWeight = FontWeight.Black,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                lesson.title,
                            maxLines = 3,
                            overflow =
                                TextOverflow.Ellipsis,
                            fontSize = 22.sp,
                            lineHeight = 25.sp,
                            fontWeight = FontWeight.Black,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "Session $sessionNumber of $totalSessions",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = FightRed
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(70.dp)
                )

                HomeProgressBar(
                    progress =
                        progressFraction
                )

                Spacer(
                    modifier =
                        Modifier.height(7.dp)
                )

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {
                    Text(
                        text =
                            "$completed / $totalSessions completed",
                        fontSize = 10.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )

                    Text(
                        text =
                            "~$estimatedWeeks weeks",
                        fontSize = 10.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {
                    Column(
                        modifier =
                            Modifier.weight(1f)
                    ) {
                        Text(
                            text =
                                if (
                                    progress.currentStreakDays > 0
                                ) {
                                    "🔥 ${progress.currentStreakDays} day streak"
                                } else {
                                    "Start your streak"
                                },
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                        )

                        Text(
                            text =
                                "+${lesson.xpReward} XP · ${lesson.requiredActiveSeconds / 60} min minimum",
                            fontSize = 9.sp,
                            color = FightRed
                        )
                    }

                    Text(
                        text =
                            "${progress.xp} XP",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onBackground
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )

                Surface(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .clickable {
                                onContinue()
                            },
                    shape =
                        RoundedCornerShape(15.dp),
                    color =
                        FightRed
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                        contentAlignment =
                            Alignment.Center
                    ) {
                        Text(
                            text =
                                "Continue session  →",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(5.dp)
                )

                Text(
                    text =
                        "View full Fight Path  →",
                    modifier =
                        Modifier
                            .clickable {
                                onOpenPath()
                            }
                            .padding(
                                vertical = 7.dp
                            ),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
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
private fun ModernShortcutCard(
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
                .height(126.dp)
                .clickable {
                    onClick()
                },
        shape =
            RoundedCornerShape(20.dp),
        color =
            if (highlighted) {
                FightRed.copy(
                    alpha = 0.11f
                )
            } else {
                Charcoal
            }
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(15.dp),
            verticalArrangement =
                Arrangement.SpaceBetween
        ) {
            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {
                Surface(
                    modifier =
                        Modifier.size(38.dp),
                    shape =
                        CircleShape,
                    color =
                        if (highlighted) {
                            FightRed.copy(
                                alpha = 0.17f
                            )
                        } else {
                            Color.White.copy(
                                alpha = 0.05f
                            )
                        }
                ) {
                    Box(
                        contentAlignment =
                            Alignment.Center
                    ) {
                        Text(
                            text = symbol,
                            fontSize = 17.sp,
                            fontWeight =
                                FontWeight.Black,
                            color =
                                if (highlighted) {
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
                    fontSize = 17.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        if (highlighted) {
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
                    text = title,
                    fontSize = 15.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text = subtitle,
                    fontSize = 9.sp,
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
private fun HomeTipCard() {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(16.dp),
        color =
            Color.White.copy(
                alpha = 0.035f
            )
    ) {
        Row(
            modifier =
                Modifier.padding(14.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Text(
                text = "🥊",
                fontSize = 22.sp
            )

            Spacer(
                modifier =
                    Modifier.size(10.dp)
            )

            Column {
                Text(
                    text = "Keep it simple.",
                    fontSize = 11.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text =
                        "Your main job today is the next Fight Path session.",
                    fontSize = 9.sp,
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
private fun HomeProgressBar(
    progress: Float
) {
    val safeProgress =
        progress.coerceIn(
            0f,
            1f
        )

    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(
                    color =
                        Color.White.copy(
                            alpha = 0.08f
                        ),
                    shape =
                        RoundedCornerShape(
                            999.dp
                        )
                )
    ) {
        if (safeProgress > 0f) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(
                            safeProgress
                        )
                        .height(6.dp)
                        .background(
                            color = FightRed,
                            shape =
                                RoundedCornerShape(
                                    999.dp
                                )
                        )
            )
        }
    }
}

@Composable
private fun PathCompleteHeroCard(
    state: TrainingPathViewModel.UiState,
    onOpenPath: () -> Unit
) {
    val progress =
        state.progress

    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onOpenPath()
                },
        shape =
            RoundedCornerShape(24.dp),
        color =
            FightRed.copy(
                alpha = 0.11f
            )
    ) {
        Row(
            modifier =
                Modifier.padding(20.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Surface(
                modifier =
                    Modifier.size(54.dp),
                shape =
                    CircleShape,
                color =
                    FightRed.copy(
                        alpha = 0.18f
                    )
            ) {
                Box(
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text = "✓",
                        fontSize = 24.sp,
                        fontWeight =
                            FontWeight.Black,
                        color = FightRed
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.size(14.dp)
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {
                Text(
                    text =
                        "Fight Path complete",
                    fontSize = 19.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text =
                        "${progress?.xp ?: 0} XP · View your completed path",
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
                color = FightRed
            )
        }
    }
}

@Composable
private fun SimpleTrainingHeroCard(
    sport: String,
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
            RoundedCornerShape(24.dp),
        color =
            Charcoal
    ) {
        Column(
            modifier =
                Modifier.padding(20.dp)
        ) {
            Text(
                text = "START TRAINING",
                fontSize = 9.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 1.2.sp,
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    "$sport training",
                fontSize = 24.sp,
                fontWeight =
                    FontWeight.Black,
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
                    "Build today's session and start moving.",
                fontSize = 11.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Text(
                text = "Start  →",
                fontSize = 13.sp,
                fontWeight =
                    FontWeight.Black,
                color = FightRed
            )
        }
    }
}