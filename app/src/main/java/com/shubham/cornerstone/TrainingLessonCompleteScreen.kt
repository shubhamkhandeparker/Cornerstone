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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.FightRedDark
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun TrainingLessonCompleteScreen(
    lessonTitle: String,
    chapterTitle: String,
    xpAwarded: Int,
    totalXp: Int,
    streakDays: Int,
    activeTrainingSeconds: Int,
    completedCombos: Int,
    chapterCompleted: Boolean,
    nextLessonTitle: String?,
    onContinue: () -> Unit,
    onHome: () -> Unit
) {
    val scrollState =
        rememberScrollState()

    val safeXpAwarded =
        xpAwarded.coerceAtLeast(0)

    val safeTotalXp =
        totalXp.coerceAtLeast(0)

    val safeStreak =
        streakDays.coerceAtLeast(0)

    val safeSeconds =
        activeTrainingSeconds
            .coerceAtLeast(0)

    val safeCompletedCombos =
        completedCombos
            .coerceAtLeast(0)

    val minutes =
        safeSeconds / 60

    val seconds =
        safeSeconds % 60

    val trainingTimeText =
        when {
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF181719),
                        InkBlack
                    )
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(320.dp)
                .blur(120.dp)
                .background(
                    FightRed.copy(
                        alpha = 0.16f
                    ),
                    CircleShape
                )
                .align(
                    Alignment.TopCenter
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(scrollState)
                .padding(
                    horizontal = 22.dp
                ),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier =
                    Modifier.height(34.dp)
            )

            Surface(
                modifier =
                    Modifier.size(82.dp),
                shape = CircleShape,
                color =
                    FightRed.copy(
                        alpha = 0.16f
                    )
            ) {
                Box(
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text = "✓",
                        fontSize = 40.sp,
                        fontWeight =
                            FontWeight.Black,
                        color = FightRed
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(22.dp)
            )

            Text(
                text =
                    if (chapterCompleted) {
                        "CHAPTER COMPLETE"
                    } else {
                        "LESSON COMPLETE"
                    },
                fontSize = 12.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 2.sp,
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    if (chapterCompleted) {
                        "You cleared it."
                    } else {
                        "Work done."
                    },
                fontSize = 38.sp,
                lineHeight = 42.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground,
                textAlign =
                    TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text = lessonTitle,
                fontSize = 18.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,
                textAlign =
                    TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.height(30.dp)
            )

            XpRewardCard(
                xpAwarded =
                    safeXpAwarded,
                totalXp =
                    safeTotalXp
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        12.dp
                    )
            ) {
                ResultStatCard(
                    value =
                        trainingTimeText,
                    label =
                        "ACTIVE TIME",
                    modifier =
                        Modifier.weight(1f)
                )

                ResultStatCard(
                    value =
                        safeCompletedCombos
                            .toString(),
                    label =
                        "ROUNDS",
                    modifier =
                        Modifier.weight(1f)
                )

                ResultStatCard(
                    value =
                        if (
                            safeStreak >
                            0
                        ) {
                            "🔥 $safeStreak"
                        } else {
                            "1"
                        },
                    label =
                        "STREAK",
                    modifier =
                        Modifier.weight(1f)
                )
            }

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            Surface(
                modifier =
                    Modifier.fillMaxWidth(),
                shape =
                    RoundedCornerShape(
                        20.dp
                    ),
                color = Charcoal
            ) {
                Column(
                    modifier =
                        Modifier.padding(
                            18.dp
                        )
                ) {
                    Text(
                        text =
                            if (
                                chapterCompleted
                            ) {
                                "CHAPTER CLEARED"
                            } else {
                                "PROGRESS UPDATED"
                            },
                        fontSize = 10.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing =
                            1.3.sp,
                        color = FightRed
                    )

                    Spacer(
                        modifier =
                            Modifier.height(
                                7.dp
                            )
                    )

                    Text(
                        text =
                            chapterTitle,
                        fontSize = 20.sp,
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
                            when {
                                chapterCompleted &&
                                        nextLessonTitle !=
                                        null -> {

                                    "New chapter unlocked. Your next lesson is ready."
                                }

                                chapterCompleted -> {
                                    "You completed every lesson in this chapter."
                                }

                                nextLessonTitle !=
                                        null -> {

                                    "One step stronger. Your next lesson is unlocked."
                                }

                                else -> {
                                    "Your Fight Path progress has been saved."
                                }
                            },
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }
            }

            if (
                nextLessonTitle != null
            ) {
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
                        FightRed.copy(
                            alpha = 0.10f
                        )
                ) {
                    Column(
                        modifier =
                            Modifier.padding(
                                18.dp
                            )
                    ) {
                        Text(
                            text =
                                "NEXT LESSON",
                            fontSize = 10.sp,
                            fontWeight =
                                FontWeight.Black,
                            letterSpacing =
                                1.3.sp,
                            color = FightRed
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    6.dp
                                )
                        )

                        Text(
                            text =
                                nextLessonTitle,
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
                                "Unlocked and ready.",
                            fontSize = 12.sp,
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
                    Modifier.height(24.dp)
            )

            if (
                nextLessonTitle != null
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onContinue()
                        },
                    shape =
                        RoundedCornerShape(
                            17.dp
                        ),
                    color = FightRed
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp),
                        contentAlignment =
                            Alignment.Center
                    ) {
                        Text(
                            text =
                                "Continue path  →",
                            fontSize = 16.sp,
                            fontWeight =
                                FontWeight.Black,
                            color = Color.White
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            10.dp
                        )
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onHome()
                    },
                shape =
                    RoundedCornerShape(
                        17.dp
                    ),
                color = Charcoal
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            "Back to home",
                        fontSize = 15.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onBackground
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(32.dp)
            )
        }
    }
}

@Composable
private fun XpRewardCard(
    xpAwarded: Int,
    totalXp: Int
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(22.dp),
        color =
            FightRed.copy(
                alpha = 0.13f
            )
    ) {
        Column(
            modifier =
                Modifier.padding(
                    vertical = 24.dp,
                    horizontal = 20.dp
                ),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {
            Text(
                text =
                    "+$xpAwarded XP",
                fontSize = 38.sp,
                fontWeight =
                    FontWeight.Black,
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    "$totalXp total Fight Path XP",
                fontSize = 13.sp,
                fontWeight =
                    FontWeight.Medium,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ResultStatCard(
    value: String,
    label: String,
    modifier: Modifier =
        Modifier
) {
    Surface(
        modifier =
            modifier.height(94.dp),
        shape =
            RoundedCornerShape(
                18.dp
            ),
        color = Charcoal
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
                text = value,
                fontSize = 19.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground,
                textAlign =
                    TextAlign.Center
            )

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text = label,
                fontSize = 9.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 0.8.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,
                textAlign =
                    TextAlign.Center
            )
        }
    }
}