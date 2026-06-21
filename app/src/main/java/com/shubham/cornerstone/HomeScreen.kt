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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
    onStartSession: () -> Unit,
    onOpenGlossary: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF161518), InkBlack)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            // --- Top bar: greeting + profile dot ---
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
                // Profile badge (sport initial)
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

            Spacer(Modifier.height(32.dp))

            // --- HERO: tonight's session card ---
            SessionCard(
                sport = profile.sport,
                level = profile.level,
                sessionNumber = profile.sessionsCompleted + 1,
                onClick = onStartSession
            )

            Spacer(Modifier.height(20.dp))

            // --- Small footer hint ---
            Text(
                text = "No equipment needed. 20 minutes. Let's go.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            // --- Glossary link ---
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onOpenGlossary() },
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
                            text = "New to boxing?",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Learn what 1, 2, 3 mean",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Text(
                        text = "→",
                        fontSize = 20.sp,
                        color = FightRed
                    )
                }
            }
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
            .height(280.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        color = Charcoal
    ) {
        Box {
            // Red gradient wash for energy
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
            // Soft glow bottom-right
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .blur(90.dp)
                    .background(FightRed.copy(alpha = 0.20f), CircleShape)
                    .align(Alignment.BottomEnd)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top label
                Row(verticalAlignment = Alignment.CenterVertically) {
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

                // Middle: the pitch
                Column {
                    Text(
                        text = "$sport\nShadow + Drills",
                        fontSize = 34.sp,
                        lineHeight = 38.sp,
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

                // Bottom: call to action
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
                            text = "Start training  →",
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