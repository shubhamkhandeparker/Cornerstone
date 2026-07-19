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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

private const val ONE_HUNDRED_KICKS_CHALLENGE_ID =
    "one_hundred_kicks"

private enum class ChallengeDetailConfirmation {
    ABANDON,
    RESTART
}

@Composable
fun ChallengeDetailScreen(
    challenge: ChallengeWithProgress,
    isProcessing: Boolean,
    onStart: () -> Unit,
    onAbandon: () -> Unit,
    onRestart: () -> Unit,
    onStartKickSession: () -> Unit = {},
    onBack: () -> Unit
) {
    BackHandler(onBack = onBack)

    var confirmation by remember {
        mutableStateOf<ChallengeDetailConfirmation?>(null)
    }

    val isKickChallenge =
        challenge.definition.id ==
                ONE_HUNDRED_KICKS_CHALLENGE_ID

    val canOpenKickSession =
        isKickChallenge &&
                challenge.status ==
                ChallengeStatus.ACTIVE

    confirmation?.let { action ->
        ChallengeDetailConfirmationDialog(
            title = challenge.definition.title,
            action = action,
            onConfirm = {
                when (action) {
                    ChallengeDetailConfirmation.ABANDON -> {
                        onAbandon()
                    }

                    ChallengeDetailConfirmation.RESTART -> {
                        onRestart()
                    }
                }

                confirmation = null
            },
            onDismiss = {
                confirmation = null
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
            ChallengeDetailHeader(
                onBack = onBack
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 20.dp,
                    end = 20.dp,
                    top = 8.dp,
                    bottom = 30.dp
                ),
                verticalArrangement =
                    Arrangement.spacedBy(14.dp)
            ) {
                item {
                    ChallengeHeroCard(
                        challenge = challenge
                    )
                }

                item {
                    ChallengeDescriptionCard(
                        description =
                            challenge.definition
                                .fullDescription
                    )
                }

                item {
                    ChallengeRequirementsCard(
                        definition =
                            challenge.definition
                    )
                }

                challenge.progress?.let { progress ->
                    item {
                        ChallengeDetailProgressCard(
                            definition =
                                challenge.definition,
                            progress = progress
                        )
                    }
                }

                if (canOpenKickSession) {
                    item {
                        ChallengeKickSessionCard(
                            validatedRepetitions =
                                challenge.progress
                                    ?.totalValidatedRepetitions
                                    ?.coerceAtLeast(0)
                                    ?: 0,
                            requiredRepetitions =
                                challenge.definition
                                    .requiredRepetitionsPerDay
                                    .coerceAtLeast(1),
                            enabled = !isProcessing,
                            onStartKickSession =
                                onStartKickSession
                        )
                    }
                }

                item {
                    ChallengeDetailRewardCard(
                        reward =
                            challenge.definition.reward,
                        rewardClaimed =
                            challenge.rewardClaimed
                    )
                }

                item {
                    ChallengeSafetyCard(
                        difficulty =
                            challenge.definition
                                .difficulty
                    )
                }

                item {
                    ChallengeDetailActionButton(
                        status = challenge.status,
                        enabled = !isProcessing,
                        onStart = onStart,
                        onAbandon = {
                            confirmation =
                                ChallengeDetailConfirmation
                                    .ABANDON
                        },
                        onRestart = {
                            confirmation =
                                ChallengeDetailConfirmation
                                    .RESTART
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChallengeDetailHeader(
    onBack: () -> Unit
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
                onBack()
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
                text = "Challenge Details",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Text(
                text = "Review the goal before starting",
                fontSize = 13.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ChallengeHeroCard(
    challenge: ChallengeWithProgress
) {
    val definition = challenge.definition

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                DetailBadge(
                    text =
                        definition.difficulty
                            .displayName,
                    highlighted =
                        definition.difficulty ==
                                ChallengeDifficulty.ELITE
                )

                DetailBadge(
                    text =
                        challenge.status
                            .displayName(),
                    highlighted =
                        challenge.status ==
                                ChallengeStatus.ACTIVE ||
                                challenge.status ==
                                ChallengeStatus.COMPLETED
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = definition.title,
                fontSize = 27.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = definition.shortDescription,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            DetailBadge(
                text =
                    definition.category
                        .displayName,
                highlighted = true
            )
        }
    }
}

@Composable
private fun ChallengeDescriptionCard(
    description: String
) {
    ChallengeSectionCard(
        title = "About this challenge"
    ) {
        Text(
            text = description,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant
        )
    }
}

@Composable
private fun ChallengeRequirementsCard(
    definition: ChallengeDefinition
) {
    ChallengeSectionCard(
        title = "Daily requirements"
    ) {
        RequirementLine(
            label = "Duration",
            value =
                "${definition.durationDays} day" +
                        if (
                            definition.durationDays ==
                            1
                        ) {
                            ""
                        } else {
                            "s"
                        }
        )

        RequirementLine(
            label = "Sessions",
            value =
                "${definition.requiredSessionsPerDay} per day"
        )

        if (
            definition.requiredActiveMinutesPerDay >
            0
        ) {
            RequirementLine(
                label = "Active training",
                value =
                    "${definition.requiredActiveMinutesPerDay} minutes per day"
            )
        }

        if (
            definition.requiredRepetitionsPerDay >
            0
        ) {
            RequirementLine(
                label = "Repetitions",
                value =
                    "${definition.requiredRepetitionsPerDay} reps"
            )
        }

        RequirementLine(
            label = "Consecutive days",
            value =
                if (
                    definition.requiresConsecutiveDays
                ) {
                    "Required"
                } else {
                    "Not required"
                },
            showDivider = false
        )
    }
}

@Composable
private fun RequirementLine(
    label: String,
    value: String,
    showDivider: Boolean = true
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 13.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme
                        .onBackground,
                textAlign = TextAlign.End
            )
        }

        if (showDivider) {
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
        }
    }
}

@Composable
private fun ChallengeDetailProgressCard(
    definition: ChallengeDefinition,
    progress: ChallengeProgress
) {
    val fraction =
        if (definition.durationDays > 0) {
            progress.completedDays
                .toFloat()
                .div(
                    definition.durationDays
                        .toFloat()
                )
                .coerceIn(
                    minimumValue = 0f,
                    maximumValue = 1f
                )
        } else {
            0f
        }

    ChallengeSectionCard(
        title = "Your progress"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {
            Text(
                text =
                    "${progress.completedDays} of ${definition.durationDays} days",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            Text(
                text =
                    "${(fraction * 100).toInt()}%",
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = FightRed
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        LinearProgressIndicator(
            progress = {
                fraction
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = FightRed,
            trackColor = Color(0xFF29282C)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        ProgressValueRow(
            label = "Current day",
            value = progress.currentDay.toString()
        )

        ProgressValueRow(
            label = "Current streak",
            value =
                "${progress.currentStreakDays} days"
        )

        ProgressValueRow(
            label = "Validated sessions",
            value =
                progress.totalValidatedSessions
                    .toString()
        )

        ProgressValueRow(
            label = "Validated minutes",
            value =
                progress.totalValidatedMinutes
                    .toString()
        )

        if (
            progress.totalValidatedRepetitions >
            0 ||
            definition.requiredRepetitionsPerDay >
            0
        ) {
            ProgressValueRow(
                label = "Validated repetitions",
                value =
                    progress
                        .totalValidatedRepetitions
                        .toString()
            )
        }

        ProgressValueRow(
            label = "Integrity",
            value =
                progress.integrityStatus
                    .displayName(),
            showDivider = false
        )
    }
}

@Composable
private fun ChallengeKickSessionCard(
    validatedRepetitions: Int,
    requiredRepetitions: Int,
    enabled: Boolean,
    onStartKickSession: () -> Unit
) {
    val safeRequiredRepetitions =
        requiredRepetitions.coerceAtLeast(1)

    val safeValidatedRepetitions =
        validatedRepetitions.coerceIn(
            minimumValue = 0,
            maximumValue =
                safeRequiredRepetitions
        )

    val hasSavedProgress =
        safeValidatedRepetitions > 0

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = FightRed.copy(alpha = 0.13f)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "KICK SESSION",
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.4.sp,
                color = FightRed
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text =
                    if (hasSavedProgress) {
                        "$safeValidatedRepetitions of $safeRequiredRepetitions validated kicks"
                    } else {
                        "Choose Guided Solo or Manual Partner mode"
                    },
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Text(
                text =
                    "Guided mode counts kicks through audio cues. Manual mode allows a partner to operate the counter.",
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color =
                    MaterialTheme.colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        enabled = enabled
                    ) {
                        onStartKickSession()
                    },
                shape = RoundedCornerShape(16.dp),
                color =
                    if (enabled) {
                        FightRed
                    } else {
                        Color(0xFF29282C)
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            when {
                                !enabled -> {
                                    "Please wait..."
                                }

                                hasSavedProgress -> {
                                    "Continue today’s kicks"
                                }

                                else -> {
                                    "Start kick session"
                                }
                            },
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color =
                            if (enabled) {
                                Color.White
                            } else {
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                            }
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressValueRow(
    label: String,
    value: String,
    showDivider: Boolean = true
) {
    RequirementLine(
        label = label,
        value = value,
        showDivider = showDivider
    )
}

@Composable
private fun ChallengeDetailRewardCard(
    reward: ChallengeReward,
    rewardClaimed: Boolean
) {
    val rewardText =
        when (reward.type) {
            ChallengeRewardType.NONE -> {
                "No reward"
            }

            ChallengeRewardType.POINTS -> {
                "${reward.points} Cornerstone Points"
            }

            ChallengeRewardType.PRO_PASS -> {
                "${reward.proPassDays}-Day Cornerstone Pro Pass"
            }
        }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = FightRed.copy(alpha = 0.11f)
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "REWARD",
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.2.sp,
                color = FightRed
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = rewardText,
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            if (rewardClaimed) {
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Reward claimed",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = FightRed
                )
            }
        }
    }
}

@Composable
private fun ChallengeSafetyCard(
    difficulty: ChallengeDifficulty
) {
    val message =
        when (difficulty) {
            ChallengeDifficulty.BEGINNER -> {
                "Use controlled intensity and focus on technique. Stop if you experience sharp pain, dizziness or unusual discomfort."
            }

            ChallengeDifficulty.INTERMEDIATE -> {
                "Plan recovery, hydration and manageable intensity. Do not continue through pain or excessive fatigue."
            }

            ChallengeDifficulty.ADVANCED,
            ChallengeDifficulty.ELITE -> {
                "This is a demanding challenge. Use planned recovery, appropriate nutrition and professional guidance where necessary."
            }
        }

    ChallengeSectionCard(
        title = "Safety"
    ) {
        Text(
            text = message,
            fontSize = 13.sp,
            lineHeight = 20.sp,
            color =
                MaterialTheme.colorScheme
                    .onSurfaceVariant
        )
    }
}

@Composable
private fun ChallengeDetailActionButton(
    status: ChallengeStatus,
    enabled: Boolean,
    onStart: () -> Unit,
    onAbandon: () -> Unit,
    onRestart: () -> Unit
) {
    val text: String
    val action: () -> Unit
    val active: Boolean
    val backgroundColor: Color

    when (status) {
        ChallengeStatus.NOT_STARTED -> {
            text = "Start Challenge"
            action = onStart
            active = true
            backgroundColor = FightRed
        }

        ChallengeStatus.ACTIVE -> {
            text = "Abandon Challenge"
            action = onAbandon
            active = true
            backgroundColor = Color(0xFF29282C)
        }

        ChallengeStatus.COMPLETED -> {
            text = "Challenge Completed"
            action = {}
            active = false
            backgroundColor = Color(0xFF29282C)
        }

        ChallengeStatus.FAILED,
        ChallengeStatus.ABANDONED -> {
            text = "Restart Challenge"
            action = onRestart
            active = true
            backgroundColor = FightRed
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = enabled && active
            ) {
                action()
            },
        shape = RoundedCornerShape(16.dp),
        color = backgroundColor
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            contentAlignment =
                Alignment.Center
        ) {
            Text(
                text =
                    if (enabled) {
                        text
                    } else {
                        "Please wait..."
                    },
                fontSize = 15.sp,
                fontWeight = FontWeight.Black,
                color =
                    if (active) {
                        Color.White
                    } else {
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                    }
            )
        }
    }
}

@Composable
private fun ChallengeSectionCard(
    title: String,
    content: @Composable () -> Unit
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
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
                color =
                    MaterialTheme.colorScheme
                        .onBackground
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            content()
        }
    }
}

@Composable
private fun DetailBadge(
    text: String,
    highlighted: Boolean
) {
    Surface(
        shape = RoundedCornerShape(999.dp),
        color =
            if (highlighted) {
                FightRed.copy(alpha = 0.16f)
            } else {
                Color(0xFF29282C)
            }
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 9.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 0.8.sp,
            color =
                if (highlighted) {
                    FightRed
                } else {
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                },
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 6.dp
            )
        )
    }
}

@Composable
private fun ChallengeDetailConfirmationDialog(
    title: String,
    action: ChallengeDetailConfirmation,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val abandoning =
        action ==
                ChallengeDetailConfirmation.ABANDON

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text =
                    if (abandoning) {
                        "Abandon challenge?"
                    } else {
                        "Restart challenge?"
                    }
            )
        },
        text = {
            Text(
                text =
                    if (abandoning) {
                        "Your current progress for $title will stop. You can restart the challenge later."
                    } else {
                        "Your previous progress for $title will be deleted and the challenge will restart from day one."
                    }
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text =
                        if (abandoning) {
                            "ABANDON"
                        } else {
                            "RESTART"
                        },
                    color = FightRed,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text(
                    text = "CANCEL"
                )
            }
        }
    )
}

private fun ChallengeStatus.displayName(): String {
    return when (this) {
        ChallengeStatus.NOT_STARTED ->
            "Not Started"

        ChallengeStatus.ACTIVE ->
            "Active"

        ChallengeStatus.COMPLETED ->
            "Completed"

        ChallengeStatus.FAILED ->
            "Failed"

        ChallengeStatus.ABANDONED ->
            "Abandoned"
    }
}

private fun ChallengeIntegrityStatus.displayName():
        String {

    return when (this) {
        ChallengeIntegrityStatus.VALID ->
            "Valid"

        ChallengeIntegrityStatus.SUSPICIOUS ->
            "Needs Review"

        ChallengeIntegrityStatus.INVALID ->
            "Invalid"
    }
}