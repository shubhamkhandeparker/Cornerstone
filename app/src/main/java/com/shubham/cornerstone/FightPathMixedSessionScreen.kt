package com.shubham.cornerstone

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

data class FightPathMixedSessionResult(
    val techniqueResult: SessionCompletionResult,
    val conditioningActiveSeconds: Int,
    val completedConditioningBlocks: Int,
    val totalConditioningBlocks: Int,
    val genuinelyFinished: Boolean
)

@Composable
fun FightPathMixedSessionScreen(
    plan: FightPathMixedSessionPlan,
    techniqueSecondsPerCombo: Int,
    techniqueRestSeconds: Int,
    fightPathInfo: FightPathSessionInfo,
    onComplete: (FightPathMixedSessionResult) -> Unit,
    onExit: () -> Unit
) {
    if (plan.stages.isEmpty()) {
        EmptyMixedSessionScreen(
            onExit = onExit
        )

        return
    }

    var stageIndex by remember {
        mutableIntStateOf(0)
    }

    var techniqueActiveSeconds by remember {
        mutableIntStateOf(0)
    }

    var techniqueCompletedCombos by remember {
        mutableIntStateOf(0)
    }

    var techniqueSkippedCombos by remember {
        mutableIntStateOf(0)
    }

    var techniquePausedSeconds by remember {
        mutableIntStateOf(0)
    }

    var techniqueRestSecondsTracked by remember {
        mutableIntStateOf(0)
    }

    var conditioningActiveSeconds by remember {
        mutableIntStateOf(0)
    }

    var completedConditioningBlocks by remember {
        mutableIntStateOf(0)
    }

    var sessionFinished by remember {
        mutableStateOf(false)
    }

    val currentStage =
        plan.stages[
            stageIndex
        ]

    val totalTechniqueCombos =
        plan.totalTechniqueRounds

    val totalConditioningBlocks =
        plan.totalConditioningBlocks

    fun finishSession(
        finalTechniqueActiveSeconds: Int =
            techniqueActiveSeconds,
        finalTechniqueCompletedCombos: Int =
            techniqueCompletedCombos,
        finalTechniqueSkippedCombos: Int =
            techniqueSkippedCombos,
        finalTechniquePausedSeconds: Int =
            techniquePausedSeconds,
        finalTechniqueRestSeconds: Int =
            techniqueRestSecondsTracked,
        finalConditioningActiveSeconds: Int =
            conditioningActiveSeconds,
        finalCompletedConditioningBlocks: Int =
            completedConditioningBlocks
    ) {
        if (sessionFinished) {
            return
        }

        sessionFinished =
            true

        onComplete(
            FightPathMixedSessionResult(
                techniqueResult =
                    SessionCompletionResult(
                        activeTrainingSeconds =
                            finalTechniqueActiveSeconds,
                        completedCombos =
                            finalTechniqueCompletedCombos,
                        skippedCombos =
                            finalTechniqueSkippedCombos,
                        pausedSeconds =
                            finalTechniquePausedSeconds,
                        restSeconds =
                            finalTechniqueRestSeconds,
                        genuinelyFinished =
                            true,
                        totalCombos =
                            totalTechniqueCombos
                    ),
                conditioningActiveSeconds =
                    finalConditioningActiveSeconds,
                completedConditioningBlocks =
                    finalCompletedConditioningBlocks,
                totalConditioningBlocks =
                    totalConditioningBlocks,
                genuinelyFinished =
                    true
            )
        )
    }

    BackHandler(
        enabled =
            !sessionFinished,
        onBack = {
            onExit()
        }
    )

    when (
        val stage =
            currentStage
    ) {
        is FightPathMixedStage.Technique -> {

            val nextStage =
                plan.stages
                    .getOrNull(
                        stageIndex + 1
                    )

            val techniqueStageContext =
                TechniqueMixedStageContext(
                    stageNumber =
                        stageIndex + 1,
                    totalStages =
                        plan.stages.size,
                    nextStageLabel =
                        mixedStageLabel(
                            stage =
                                nextStage
                        )
                )

            SessionScreen(
                combos =
                    stage.combos,
                secondsPerCombo =
                    techniqueSecondsPerCombo,
                restSeconds =
                    techniqueRestSeconds,
                onFinishSession = {},
                onExit = {
                    onExit()
                },
                onSessionResult = {
                        result ->

                    val newActiveSeconds =
                        techniqueActiveSeconds +
                                result
                                    .activeTrainingSeconds

                    val newCompletedCombos =
                        techniqueCompletedCombos +
                                result
                                    .completedCombos

                    val newSkippedCombos =
                        techniqueSkippedCombos +
                                result
                                    .skippedCombos

                    val newPausedSeconds =
                        techniquePausedSeconds +
                                result
                                    .pausedSeconds

                    val newRestSeconds =
                        techniqueRestSecondsTracked +
                                result
                                    .restSeconds

                    techniqueActiveSeconds =
                        newActiveSeconds

                    techniqueCompletedCombos =
                        newCompletedCombos

                    techniqueSkippedCombos =
                        newSkippedCombos

                    techniquePausedSeconds =
                        newPausedSeconds

                    techniqueRestSecondsTracked =
                        newRestSeconds

                    if (
                        stageIndex >=
                        plan.stages.lastIndex
                    ) {
                        finishSession(
                            finalTechniqueActiveSeconds =
                                newActiveSeconds,
                            finalTechniqueCompletedCombos =
                                newCompletedCombos,
                            finalTechniqueSkippedCombos =
                                newSkippedCombos,
                            finalTechniquePausedSeconds =
                                newPausedSeconds,
                            finalTechniqueRestSeconds =
                                newRestSeconds
                        )
                    } else {
                        stageIndex++
                    }
                },
                fightPathInfo =
                    fightPathInfo,
                mixedStageContext =
                    techniqueStageContext
            )
        }

        is FightPathMixedStage.Conditioning -> {

            val nextStage =
                plan.stages
                    .getOrNull(
                        stageIndex + 1
                    )

            val stageContext =
                ConditioningMixedStageContext(
                    stageNumber =
                        stageIndex + 1,
                    totalStages =
                        plan.stages.size,
                    completionButtonText =
                        conditioningCompletionButtonText(
                            currentStage =
                                stage,
                            nextStage =
                                nextStage
                        ),
                    nextStageLabel =
                        mixedStageLabel(
                            stage =
                                nextStage
                        )
                )

            ConditioningWorkoutScreen(
                blocks =
                    listOf(
                        stage.block
                    ),
                title =
                    conditioningStageTitle(
                        block =
                            stage.block
                    ),
                onComplete = {
                        result ->

                    val newConditioningSeconds =
                        conditioningActiveSeconds +
                                result.activeSeconds

                    val newCompletedBlocks =
                        completedConditioningBlocks +
                                result.completedBlocks

                    conditioningActiveSeconds =
                        newConditioningSeconds

                    completedConditioningBlocks =
                        newCompletedBlocks

                    if (
                        stageIndex >=
                        plan.stages.lastIndex
                    ) {
                        finishSession(
                            finalConditioningActiveSeconds =
                                newConditioningSeconds,
                            finalCompletedConditioningBlocks =
                                newCompletedBlocks
                        )
                    } else {
                        stageIndex++
                    }
                },
                onExit = {
                    onExit()
                },
                mixedStageContext =
                    stageContext
            )
        }
    }
}

private fun conditioningCompletionButtonText(
    currentStage:
    FightPathMixedStage.Conditioning,
    nextStage:
    FightPathMixedStage?
): String {

    if (nextStage == null) {
        return "Finish workout"
    }

    return when (nextStage) {
        is FightPathMixedStage.Technique -> {
            when (
                currentStage
                    .block
                    .type
            ) {
                ConditioningBlockType.WARM_UP -> {
                    "Continue to technique  →"
                }

                else -> {
                    "Continue training  →"
                }
            }
        }

        is FightPathMixedStage.Conditioning -> {
            when (
                nextStage
                    .block
                    .type
            ) {
                ConditioningBlockType.FINISHER -> {
                    "Continue to finisher  →"
                }

                else -> {
                    "Continue conditioning  →"
                }
            }
        }
    }
}

private fun mixedStageLabel(
    stage:
    FightPathMixedStage?
): String? {

    return when (stage) {
        null -> {
            null
        }

        is FightPathMixedStage.Technique -> {
            "Technique"
        }

        is FightPathMixedStage.Conditioning -> {
            when (
                stage
                    .block
                    .type
            ) {
                ConditioningBlockType.WARM_UP -> {
                    "Warm-up"
                }

                ConditioningBlockType.BETWEEN_ROUNDS -> {
                    "Conditioning"
                }

                ConditioningBlockType.FINISHER -> {
                    "Finisher"
                }

                ConditioningBlockType.CONDITIONING_ROUND -> {
                    "Conditioning"
                }
            }
        }
    }
}

private fun conditioningStageTitle(
    block:
    PlannedConditioningBlock
): String {

    return when (
        block.type
    ) {
        ConditioningBlockType.WARM_UP -> {
            "Fight Path Warm-up"
        }

        ConditioningBlockType.BETWEEN_ROUNDS -> {
            "Fight Path Conditioning"
        }

        ConditioningBlockType.FINISHER -> {
            "Fight Path Finisher"
        }

        ConditioningBlockType.CONDITIONING_ROUND -> {
            "Conditioning"
        }
    }
}

@Composable
private fun EmptyMixedSessionScreen(
    onExit: () -> Unit
) {
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
                .padding(
                    24.dp
                ),
        contentAlignment =
            Alignment.Center
    ) {
        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.Center
        ) {
            Text(
                text =
                    "Session unavailable",
                fontSize =
                    25.sp,
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
                        8.dp
                    )
            )

            Text(
                text =
                    "Cornerstone could not build this training session.",
                fontSize =
                    13.sp,
                lineHeight =
                    19.sp,
                textAlign =
                    TextAlign.Center,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(
                        20.dp
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
                        "Return home",
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        Color.White
                )
            }
        }
    }
}