package com.shubham.cornerstone

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
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
import kotlinx.coroutines.delay

/**
 * Optional information used when conditioning is one stage
 * inside a larger Fight Path workout.
 *
 * Example:
 *
 * Stage 1 of 5
 * Warm-up
 *
 * Continue to technique →
 */
data class ConditioningMixedStageContext(
    val stageNumber: Int,
    val totalStages: Int,
    val completionButtonText: String,
    val nextStageLabel: String? = null
) {
    init {
        require(stageNumber > 0) {
            "Stage number must be greater than zero."
        }

        require(totalStages > 0) {
            "Total stages must be greater than zero."
        }

        require(stageNumber <= totalStages) {
            "Stage number cannot exceed total stages."
        }

        require(completionButtonText.isNotBlank()) {
            "Completion button text cannot be blank."
        }
    }
}

data class ConditioningWorkoutResult(
    val completedBlocks: Int,
    val totalBlocks: Int,
    val activeSeconds: Int,
    val genuinelyFinished: Boolean
)

@Composable
fun ConditioningWorkoutScreen(
    blocks: List<PlannedConditioningBlock>,
    title: String = "Conditioning",
    onComplete: (ConditioningWorkoutResult) -> Unit,
    onExit: () -> Unit,
    mixedStageContext:
    ConditioningMixedStageContext? = null
) {
    if (blocks.isEmpty()) {
        EmptyConditioningWorkoutScreen(
            onExit = onExit
        )

        return
    }

    var blockIndex by remember {
        mutableIntStateOf(0)
    }

    var roundNumber by remember {
        mutableIntStateOf(1)
    }

    var phase by remember {
        mutableStateOf(
            ConditioningWorkoutPhase
                .GET_READY
        )
    }

    var remainingSeconds by remember {
        mutableIntStateOf(3)
    }

    var isPaused by remember {
        mutableStateOf(false)
    }

    var activeSeconds by remember {
        mutableIntStateOf(0)
    }

    var completedBlocks by remember {
        mutableIntStateOf(0)
    }

    var workoutFinished by remember {
        mutableStateOf(false)
    }

    val currentBlock =
        blocks[
            blockIndex
        ]

    val currentExercise =
        currentBlock.exercise

    val currentPaused by
    rememberUpdatedState(
        isPaused
    )

    val toneGenerator =
        remember {
            ToneGenerator(
                AudioManager.STREAM_MUSIC,
                100
            )
        }

    DisposableEffect(Unit) {
        onDispose {
            toneGenerator.release()
        }
    }

    fun beep() {
        toneGenerator.startTone(
            ToneGenerator.TONE_PROP_BEEP,
            140
        )
    }

    fun bell() {
        toneGenerator.startTone(
            ToneGenerator.TONE_PROP_ACK,
            220
        )
    }

    fun finishWorkout() {
        if (workoutFinished) {
            return
        }

        workoutFinished =
            true

        onComplete(
            ConditioningWorkoutResult(
                completedBlocks =
                    completedBlocks,
                totalBlocks =
                    blocks.size,
                activeSeconds =
                    activeSeconds,
                genuinelyFinished =
                    completedBlocks >=
                            blocks.size
            )
        )
    }

    fun moveToNextBlock() {
        if (
            blockIndex >=
            blocks.lastIndex
        ) {
            completedBlocks =
                blocks.size

            finishWorkout()

            return
        }

        blockIndex++

        roundNumber =
            1

        phase =
            ConditioningWorkoutPhase
                .GET_READY

        remainingSeconds =
            3

        isPaused =
            false
    }

    fun completeCurrentBlock() {
        completedBlocks =
            maxOf(
                completedBlocks,
                blockIndex + 1
            )

        moveToNextBlock()
    }

    fun finishCurrentWorkRound() {
        if (
            roundNumber <
            currentBlock.rounds
        ) {
            if (
                currentBlock
                    .restSeconds >
                0
            ) {
                phase =
                    ConditioningWorkoutPhase
                        .REST

                remainingSeconds =
                    currentBlock
                        .restSeconds
            } else {
                roundNumber++

                phase =
                    ConditioningWorkoutPhase
                        .GET_READY

                remainingSeconds =
                    3
            }
        } else {
            completeCurrentBlock()
        }
    }

    BackHandler(
        enabled =
            !workoutFinished,
        onBack = {
            onExit()
        }
    )

    LaunchedEffect(
        blockIndex,
        roundNumber,
        phase,
        isPaused,
        workoutFinished
    ) {
        if (
            workoutFinished ||
            isPaused
        ) {
            return@LaunchedEffect
        }

        when (phase) {
            ConditioningWorkoutPhase
                .GET_READY -> {

                remainingSeconds =
                    3

                while (
                    remainingSeconds >
                    0
                ) {
                    waitForConditioningSecond {
                        currentPaused
                    }

                    if (
                        currentPaused
                    ) {
                        continue
                    }

                    beep()

                    remainingSeconds--
                }

                while (
                    currentPaused
                ) {
                    delay(100L)
                }

                bell()

                phase =
                    ConditioningWorkoutPhase
                        .WORK

                remainingSeconds =
                    currentBlock
                        .workSeconds
            }

            ConditioningWorkoutPhase
                .WORK -> {

                while (
                    remainingSeconds >
                    0
                ) {
                    waitForConditioningSecond {
                        currentPaused
                    }

                    if (
                        currentPaused
                    ) {
                        continue
                    }

                    remainingSeconds--

                    activeSeconds++

                    if (
                        remainingSeconds in
                        1..3
                    ) {
                        beep()
                    }
                }

                while (
                    currentPaused
                ) {
                    delay(100L)
                }

                bell()

                finishCurrentWorkRound()
            }

            ConditioningWorkoutPhase
                .REST -> {

                while (
                    remainingSeconds >
                    0
                ) {
                    waitForConditioningSecond {
                        currentPaused
                    }

                    if (
                        currentPaused
                    ) {
                        continue
                    }

                    remainingSeconds--

                    if (
                        remainingSeconds in
                        1..3
                    ) {
                        beep()
                    }
                }

                while (
                    currentPaused
                ) {
                    delay(100L)
                }

                roundNumber++

                phase =
                    ConditioningWorkoutPhase
                        .GET_READY

                remainingSeconds =
                    3
            }
        }
    }

    val headerProgress =
        if (
            mixedStageContext !=
            null
        ) {
            (
                    mixedStageContext
                        .stageNumber
                        .toFloat() /
                            mixedStageContext
                                .totalStages
                                .toFloat()
                    )
                .coerceIn(
                    0f,
                    1f
                )
        } else {
            (
                    (blockIndex + 1)
                        .toFloat() /
                            blocks.size
                                .toFloat()
                    )
                .coerceIn(
                    0f,
                    1f
                )
        }

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors =
                            listOf(
                                Color(
                                    0xFF161518
                                ),
                                InkBlack
                            )
                    )
                )
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(
                        horizontal =
                            22.dp
                    )
        ) {
            Spacer(
                modifier =
                    Modifier.height(
                        16.dp
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
                        "Exit",
                    fontSize =
                        14.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant,
                    modifier =
                        Modifier
                            .clickable {
                                onExit()
                            }
                            .padding(
                                vertical =
                                    8.dp
                            )
                )

                Text(
                    text =
                        if (
                            mixedStageContext !=
                            null
                        ) {
                            "STAGE ${mixedStageContext.stageNumber} / ${mixedStageContext.totalStages}"
                        } else {
                            "${blockIndex + 1} / ${blocks.size}"
                        },
                    fontSize =
                        14.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        10.dp
                    )
            )

            ConditioningProgressBar(
                progress =
                    headerProgress
            )

            Spacer(
                modifier =
                    Modifier.height(
                        22.dp
                    )
            )

            Text(
                text =
                    title.uppercase(),
                fontSize =
                    10.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing =
                    1.6.sp,
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
                    currentBlock
                        .type
                        .displayName,
                fontSize =
                    15.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            if (
                mixedStageContext
                    ?.nextStageLabel !=
                null
            ) {
                Spacer(
                    modifier =
                        Modifier.height(
                            4.dp
                        )
                )

                Text(
                    text =
                        "Next: ${mixedStageContext.nextStageLabel}",
                    fontSize =
                        10.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                            .copy(
                                alpha =
                                    0.75f
                            )
                )
            }

            Spacer(
                modifier =
                    Modifier.weight(
                        0.22f
                    )
            )

            Column(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {
                Surface(
                    shape =
                        RoundedCornerShape(
                            999.dp
                        ),
                    color =
                        when {
                            isPaused -> {
                                FightRed.copy(
                                    alpha =
                                        0.18f
                                )
                            }

                            phase ==
                                    ConditioningWorkoutPhase
                                        .REST -> {

                                Color.White.copy(
                                    alpha =
                                        0.06f
                                )
                            }

                            else -> {
                                FightRed.copy(
                                    alpha =
                                        0.12f
                                )
                            }
                        }
                ) {
                    Text(
                        text =
                            when {
                                isPaused -> {
                                    "PAUSED"
                                }

                                phase ==
                                        ConditioningWorkoutPhase
                                            .GET_READY -> {

                                    "GET READY"
                                }

                                phase ==
                                        ConditioningWorkoutPhase
                                            .WORK -> {

                                    "WORK"
                                }

                                else -> {
                                    "REST"
                                }
                            },
                        fontSize =
                            11.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing =
                            1.8.sp,
                        color =
                            FightRed,
                        modifier =
                            Modifier.padding(
                                horizontal =
                                    16.dp,
                                vertical =
                                    7.dp
                            )
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            20.dp
                        )
                )

                Text(
                    text =
                        formatConditioningTime(
                            remainingSeconds
                        ),
                    fontSize =
                        58.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        if (
                            phase ==
                            ConditioningWorkoutPhase
                                .REST
                        ) {
                            MaterialTheme
                                .colorScheme
                                .onBackground
                        } else {
                            FightRed
                        }
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            18.dp
                        )
                )

                Text(
                    text =
                        currentExercise
                            .title,
                    fontSize =
                        32.sp,
                    lineHeight =
                        37.sp,
                    fontWeight =
                        FontWeight.Black,
                    textAlign =
                        TextAlign.Center,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Spacer(
                    modifier =
                        Modifier.height(
                            8.dp
                        )
                )

                Text(
                    text =
                        "${currentExercise.category.displayName} · ${currentExercise.difficulty.displayName}",
                    fontSize =
                        12.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        FightRed
                )

                if (
                    currentBlock.rounds >
                    1
                ) {
                    Spacer(
                        modifier =
                            Modifier.height(
                                6.dp
                            )
                    )

                    Text(
                        text =
                            "Round $roundNumber of ${currentBlock.rounds}",
                        fontSize =
                            12.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            20.dp
                        )
                )

                Surface(
                    modifier =
                        Modifier.fillMaxWidth(),
                    shape =
                        RoundedCornerShape(
                            16.dp
                        ),
                    color =
                        Charcoal
                ) {
                    Column(
                        modifier =
                            Modifier.padding(
                                16.dp
                            )
                    ) {
                        Text(
                            text =
                                "HOW TO DO IT",
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
                                    6.dp
                                )
                        )

                        Text(
                            text =
                                currentExercise
                                    .instructions,
                            fontSize =
                                13.sp,
                            lineHeight =
                                19.sp,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                        )

                        Spacer(
                            modifier =
                                Modifier.height(
                                    10.dp
                                )
                        )

                        Text(
                            text =
                                currentExercise
                                    .coachingCue,
                            fontSize =
                                12.sp,
                            lineHeight =
                                18.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color =
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                        )
                    }
                }

                val alternative =
                    currentExercise
                        .lowImpactAlternative

                if (
                    alternative != null
                ) {
                    Spacer(
                        modifier =
                            Modifier.height(
                                10.dp
                            )
                    )

                    Text(
                        text =
                            "Easier option: $alternative",
                        fontSize =
                            11.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.weight(
                        0.3f
                    )
            )

            Button(
                onClick = {
                    isPaused =
                        !isPaused
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(
                            54.dp
                        ),
                shape =
                    RoundedCornerShape(
                        16.dp
                    ),
                colors =
                    ButtonDefaults
                        .buttonColors(
                            containerColor =
                                if (
                                    isPaused
                                ) {
                                    FightRed
                                } else {
                                    Charcoal
                                }
                        )
            ) {
                Text(
                    text =
                        if (
                            isPaused
                        ) {
                            "Resume"
                        } else {
                            "Pause"
                        },
                    fontSize =
                        15.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        Color.White
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        10.dp
                    )
            )

            Button(
                onClick = {
                    when (phase) {
                        ConditioningWorkoutPhase
                            .REST -> {

                            roundNumber++

                            phase =
                                ConditioningWorkoutPhase
                                    .GET_READY

                            remainingSeconds =
                                3
                        }

                        else -> {
                            completeCurrentBlock()
                        }
                    }
                },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(
                            58.dp
                        ),
                shape =
                    RoundedCornerShape(
                        16.dp
                    ),
                colors =
                    ButtonDefaults
                        .buttonColors(
                            containerColor =
                                FightRed
                        )
            ) {
                Text(
                    text =
                        when {
                            phase ==
                                    ConditioningWorkoutPhase
                                        .REST -> {

                                "Skip rest  →"
                            }

                            blockIndex ==
                                    blocks.lastIndex &&
                                    mixedStageContext !=
                                    null -> {

                                mixedStageContext
                                    .completionButtonText
                            }

                            blockIndex ==
                                    blocks.lastIndex -> {

                                "Finish conditioning"
                            }

                            else -> {
                                "Next exercise  →"
                            }
                        },
                    fontSize =
                        15.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        Color.White
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        24.dp
                    )
            )
        }
    }
}

private enum class ConditioningWorkoutPhase {
    GET_READY,
    WORK,
    REST
}

@Composable
private fun ConditioningProgressBar(
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
                .height(
                    6.dp
                )
                .background(
                    Charcoal,
                    RoundedCornerShape(
                        999.dp
                    )
                )
    ) {
        if (
            safeProgress >
            0f
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(
                            safeProgress
                        )
                        .height(
                            6.dp
                        )
                        .background(
                            FightRed,
                            RoundedCornerShape(
                                999.dp
                            )
                        )
            )
        }
    }
}

@Composable
private fun EmptyConditioningWorkoutScreen(
    onExit: () -> Unit
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    InkBlack
                )
                .padding(
                    24.dp
                ),
        contentAlignment =
            Alignment.Center
    ) {
        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {
            Text(
                text =
                    "No conditioning exercises available",
                fontSize =
                    22.sp,
                fontWeight =
                    FontWeight.Black,
                textAlign =
                    TextAlign.Center,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(
                        18.dp
                    )
            )

            Button(
                onClick =
                    onExit,
                colors =
                    ButtonDefaults
                        .buttonColors(
                            containerColor =
                                FightRed
                        )
            ) {
                Text(
                    text =
                        "Return",
                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}

private suspend fun waitForConditioningSecond(
    isPaused: () -> Boolean
) {
    var activeMilliseconds =
        0L

    while (
        activeMilliseconds <
        1_000L
    ) {
        delay(100L)

        if (
            !isPaused()
        ) {
            activeMilliseconds +=
                100L
        }
    }
}

private fun formatConditioningTime(
    seconds: Int
): String {

    val safeSeconds =
        seconds.coerceAtLeast(
            0
        )

    val minutes =
        safeSeconds / 60

    val remaining =
        safeSeconds % 60

    return "%d:%02d".format(
        minutes,
        remaining
    )
}