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
    onOpenProgressCamera: () -> Unit
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
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Evening, fighter.",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.height(2.dp))

                    Text(
                        text = "Time to work.",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = CircleShape,
                    color = Charcoal
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = profile.sport.take(1),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = FightRed
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            TodayTrainingCard(
                todaySessionCount = todaySessionCount,
                todayDurationSeconds = todayDurationSeconds
            )

            Spacer(Modifier.height(18.dp))

            SessionCard(
                sport = profile.sport,
                level = profile.level,
                sessionNumber = profile.sessionsCompleted + 1,
                onClick = onStartSession
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "No equipment needed. Build your round and get moving.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            PlaylistsCard(
                onClick = onOpenPlaylists
            )

            Spacer(Modifier.height(14.dp))

            LearnTechniquesCard(
                onClick = onOpenTechniques
            )

            Spacer(Modifier.height(14.dp))

            WeightCutCard(
                hasPlan = profile.targetWeightKg != null && profile.fightDateEpochDay != null,
                onOpenWeightCut = onOpenWeightCut,
                onOpenProgressCamera = onOpenProgressCamera
            )

            Spacer(Modifier.height(14.dp))

            GlossaryCard(
                sport = profile.sport,
                onClick = onOpenGlossary
            )

            Spacer(Modifier.height(40.dp))
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

    val durationText = when {
        todayDurationSeconds <= 0 -> "0 min"
        minutes <= 0 -> "${seconds}s"
        seconds == 0 -> "$minutes min"
        else -> "$minutes min ${seconds}s"
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = FightRed.copy(alpha = 0.13f)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Today’s work",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = if (todaySessionCount == 0) {
                        "No sessions finished yet"
                    } else {
                        "Keep the streak alive"
                    },
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                TodayStatBlock(
                    value = todaySessionCount.toString(),
                    label = if (todaySessionCount == 1) {
                        "session"
                    } else {
                        "sessions"
                    }
                )

                Spacer(Modifier.width(18.dp))

                TodayStatBlock(
                    value = durationText,
                    label = "trained"
                )
            }
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
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            color = FightRed
        )

        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun PlaylistsCard(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        color = Charcoal
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Combo playlists",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = "Save coach combos and train them later",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "->",
                fontSize = 20.sp,
                color = FightRed
            )
        }
    }
}

@Composable
private fun LearnTechniquesCard(
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        color = FightRed.copy(alpha = 0.12f)
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Learn techniques",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(3.dp))

                Text(
                    text = "Jab, cross, hook, sprawl and beginner fight basics explained clearly.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.width(12.dp))

            Surface(
                shape = RoundedCornerShape(999.dp),
                color = FightRed.copy(alpha = 0.18f)
            ) {
                Text(
                    text = "LEARN",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = FightRed,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun WeightCutCard(
    hasPlan: Boolean,
    onOpenWeightCut: () -> Unit,
    onOpenProgressCamera: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Charcoal
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = if (hasPlan) {
                        "Log today and check your pace"
                    } else {
                        "Set target and fight timeline"
                    },
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.width(10.dp))

            Surface(
                modifier = Modifier.clickable {
                    onOpenProgressCamera()
                },
                shape = RoundedCornerShape(999.dp),
                color = FightRed.copy(alpha = 0.13f)
            ) {
                Text(
                    text = "CAM",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = FightRed,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    )
                )
            }

            Spacer(Modifier.width(8.dp))

            Surface(
                modifier = Modifier.clickable {
                    onOpenWeightCut()
                },
                shape = RoundedCornerShape(999.dp),
                color = FightRed.copy(alpha = 0.16f)
            ) {
                Text(
                    text = if (hasPlan) {
                        "TRACK"
                    } else {
                        "SET UP"
                    },
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    color = FightRed,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 7.dp
                    )
                )
            }
        }
    }
}

@Composable
private fun GlossaryCard(
    sport: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        color = Charcoal
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = glossaryTitle(sport),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = glossarySubtitle(sport),
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "->",
                fontSize = 20.sp,
                color = FightRed
            )
        }
    }
}

private fun glossaryTitle(sport: String): String {
    return when (sport) {
        "Muay Thai" -> "New to Muay Thai?"
        "MMA" -> "New to MMA?"
        else -> "New to boxing?"
    }
}

private fun glossarySubtitle(sport: String): String {
    return when (sport) {
        "Muay Thai" -> "Learn strikes, knees, elbows"
        "MMA" -> "Learn strikes and fight terms"
        else -> "Learn what 1, 2, 3 mean"
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
            .height(250.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        color = Charcoal
    ) {
        Box {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                FightRedDark.copy(alpha = 0.55f),
                                Color.Transparent
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .size(220.dp)
                    .blur(90.dp)
                    .background(
                        FightRed.copy(alpha = 0.20f),
                        CircleShape
                    )
                    .align(Alignment.BottomEnd)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(FightRed)
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "SESSION $sessionNumber",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Column {
                    Text(
                        text = "$sport\nShadow + Drills",
                        fontSize = 32.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(Modifier.height(6.dp))

                    Text(
                        text = "$level · adapted to you",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = FightRed,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Start training  ->",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}