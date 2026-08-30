package com.shubham.cornerstone

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.SystemClock
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlinx.coroutines.delay

private const val DEFAULT_REST_SECONDS = 15

data class FightPathSessionInfo(
    val levelName: String,
    val levelSessionNumber: Int,
    val levelSessionTotal: Int,
    val chapterTitle: String,
    val chapterSessionNumber: Int,
    val chapterSessionTotal: Int,
    val sessionTitle: String
)

/**
 * Used when SessionScreen is one technique stage
 * inside a larger Fight Path mixed workout.
 */
data class TechniqueMixedStageContext(
    val stageNumber: Int,
    val totalStages: Int,
    val nextStageLabel: String? = null
) {
    init {
        require(stageNumber > 0)
        require(totalStages > 0)
        require(stageNumber <= totalStages)
    }
}

data class SessionCompletionResult(
    val activeTrainingSeconds: Int,
    val completedCombos: Int,
    val skippedCombos: Int,
    val pausedSeconds: Int,
    val restSeconds: Int,
    val genuinelyFinished: Boolean,
    val totalCombos: Int
)

private class SessionTrackingState {
    var timerActiveSeconds: Int = 0
    var tapActiveMilliseconds: Long = 0L
    var pausedMilliseconds: Long = 0L
    var restMilliseconds: Long = 0L

    var lastTimestampMilliseconds: Long =
        SystemClock.elapsedRealtime()

    var completedCombos: Int = 0
    var skippedCombos: Int = 0

    val resolvedComboIndexes =
        mutableSetOf<Int>()
}

@Composable
fun SessionScreen(
    combos: List<Combo>,
    secondsPerCombo: Int,
    restSeconds: Int =
        DEFAULT_REST_SECONDS,
    onFinishSession: () -> Unit,
    onExit: () -> Unit,
    onSessionResult:
        (SessionCompletionResult) -> Unit = {},
    fightPathInfo:
    FightPathSessionInfo? = null,
    mixedStageContext:
    TechniqueMixedStageContext? = null
) {
    if (combos.isEmpty()) {
        EmptySessionScreen(
            onExit = onExit
        )

        return
    }

    val timerOn =
        secondsPerCombo > 0

    val safeRestSeconds =
        restSeconds.coerceAtLeast(0)

    val lifecycleOwner =
        LocalLifecycleOwner.current

    var index by remember {
        mutableIntStateOf(0)
    }

    var repeat by remember {
        mutableStateOf(false)
    }

    var resting by remember {
        mutableStateOf(false)
    }

    var isPaused by remember {
        mutableStateOf(false)
    }

    var sessionFinished by remember {
        mutableStateOf(false)
    }

    var isLifecycleResumed by remember {
        mutableStateOf(
            lifecycleOwner
                .lifecycle
                .currentState
                .isAtLeast(
                    Lifecycle.State.RESUMED
                )
        )
    }

    var drillActive by remember {
        mutableStateOf(
            !timerOn
        )
    }

    val tracking =
        remember {
            SessionTrackingState()
        }

    val isLast =
        index == combos.lastIndex

    val effectivePaused =
        isPaused ||
                !isLifecycleResumed

    fun recordElapsedUntilNow() {
        val now =
            SystemClock.elapsedRealtime()

        val elapsedMilliseconds =
            (
                    now -
                            tracking
                                .lastTimestampMilliseconds
                    )
                .coerceAtLeast(0L)

        tracking.lastTimestampMilliseconds =
            now

        if (
            sessionFinished ||
            elapsedMilliseconds == 0L
        ) {
            return
        }

        when {
            effectivePaused -> {
                tracking.pausedMilliseconds +=
                    elapsedMilliseconds
            }

            resting -> {
                tracking.restMilliseconds +=
                    elapsedMilliseconds
            }

            !timerOn &&
                    drillActive -> {

                tracking.tapActiveMilliseconds +=
                    elapsedMilliseconds
            }
        }
    }

    fun setDrillActive(
        active: Boolean
    ) {
        recordElapsedUntilNow()

        drillActive =
            active
    }

    fun resolveCurrentCombo(
        completed: Boolean
    ) {
        if (
            !tracking
                .resolvedComboIndexes
                .add(index)
        ) {
            return
        }

        if (completed) {
            tracking.completedCombos++
        } else {
            tracking.skippedCombos++
        }
    }

    fun buildCompletionResult():
            SessionCompletionResult {

        val activeSeconds =
            if (timerOn) {
                tracking
                    .timerActiveSeconds
            } else {
                tracking
                    .tapActiveMilliseconds
                    .toRoundedSeconds()
            }

        return SessionCompletionResult(
            activeTrainingSeconds =
                activeSeconds,
            completedCombos =
                tracking.completedCombos,
            skippedCombos =
                tracking.skippedCombos,
            pausedSeconds =
                tracking
                    .pausedMilliseconds
                    .toRoundedSeconds(),
            restSeconds =
                tracking
                    .restMilliseconds
                    .toRoundedSeconds(),
            genuinelyFinished =
                true,
            totalCombos =
                combos.size
        )
    }

    fun finishSession() {
        if (sessionFinished) {
            return
        }

        recordElapsedUntilNow()

        sessionFinished =
            true

        drillActive =
            false

        resting =
            false

        isPaused =
            false

        onSessionResult(
            buildCompletionResult()
        )

        onFinishSession()
    }

    fun advanceAfterResolvedCombo() {
        recordElapsedUntilNow()

        isPaused =
            false

        drillActive =
            false

        if (isLast) {
            finishSession()
        } else {
            index++

            drillActive =
                !timerOn
        }
    }

    fun completeCurrentComboAndAdvance() {
        recordElapsedUntilNow()

        setDrillActive(
            false
        )

        resolveCurrentCombo(
            completed = true
        )

        advanceAfterResolvedCombo()
    }

    fun skipCurrentComboAndAdvance() {
        recordElapsedUntilNow()

        setDrillActive(
            false
        )

        resolveCurrentCombo(
            completed = false
        )

        advanceAfterResolvedCombo()
    }

    fun leaveWithoutCompletion() {
        if (sessionFinished) {
            return
        }

        recordElapsedUntilNow()

        sessionFinished =
            true

        drillActive =
            false

        resting =
            false

        isPaused =
            false

        onExit()
    }

    BackHandler(
        enabled =
            !sessionFinished,
        onBack = {
            leaveWithoutCompletion()
        }
    )

    val currentRecordElapsed by
    rememberUpdatedState {
        recordElapsedUntilNow()
    }

    DisposableEffect(
        lifecycleOwner
    ) {
        val observer =
            LifecycleEventObserver {
                    _,
                    event ->

                when (event) {
                    Lifecycle.Event.ON_RESUME -> {
                        currentRecordElapsed()

                        isLifecycleResumed =
                            true
                    }

                    Lifecycle.Event.ON_PAUSE,
                    Lifecycle.Event.ON_STOP,
                    Lifecycle.Event.ON_DESTROY -> {

                        currentRecordElapsed()

                        isLifecycleResumed =
                            false
                    }

                    else -> Unit
                }
            }

        lifecycleOwner
            .lifecycle
            .addObserver(
                observer
            )

        onDispose {
            lifecycleOwner
                .lifecycle
                .removeObserver(
                    observer
                )
        }
    }

    LaunchedEffect(
        sessionFinished
    ) {
        while (
            !sessionFinished
        ) {
            delay(100L)

            recordElapsedUntilNow()
        }
    }

    val toneGen =
        remember {
            ToneGenerator(
                AudioManager.STREAM_MUSIC,
                100
            )
        }

    DisposableEffect(Unit) {
        onDispose {
            toneGen.release()
        }
    }

    fun ringBell() {
        toneGen.startTone(
            ToneGenerator.TONE_PROP_ACK,
            200
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
                    .padding(
                        horizontal =
                            24.dp
                    )
        ) {
            Spacer(
                modifier =
                    Modifier.height(16.dp)
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
                                leaveWithoutCompletion()
                            }
                            .padding(
                                8.dp
                            )
                )

                Text(
                    text =
                        when {
                            mixedStageContext != null -> {
                                "STAGE ${mixedStageContext.stageNumber} / ${mixedStageContext.totalStages}"
                            }

                            fightPathInfo != null -> {
                                "ROUND ${index + 1} / ${combos.size}"
                            }

                            else -> {
                                "${index + 1} / ${combos.size}"
                            }
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

            if (
                mixedStageContext !=
                null
            ) {
                Spacer(
                    modifier =
                        Modifier.height(
                            8.dp
                        )
                )

                MixedTechniqueStageStrip(
                    stageContext =
                        mixedStageContext,
                    roundNumber =
                        index + 1,
                    totalRounds =
                        combos.size
                )
            }

            if (
                fightPathInfo != null
            ) {
                Spacer(
                    modifier =
                        Modifier.height(
                            10.dp
                        )
                )

                FightPathSessionHeader(
                    info =
                        fightPathInfo
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        12.dp
                    )
            )

            ProgressBar(
                progress =
                    (index + 1)
                        .toFloat() /
                            combos.size
            )

            Box(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                contentAlignment =
                    Alignment.Center
            ) {
                if (resting) {
                    RestView(
                        seconds =
                            safeRestSeconds,
                        isPaused =
                            effectivePaused,
                        onBeep = {
                            toneGen.startTone(
                                ToneGenerator
                                    .TONE_PROP_BEEP,
                                150
                            )
                        },
                        onDone = {
                            recordElapsedUntilNow()

                            resting =
                                false

                            advanceAfterResolvedCombo()
                        }
                    )
                } else {
                    AnimatedContent(
                        targetState =
                            index,
                        transitionSpec = {
                            fadeIn(
                                animationSpec =
                                    tween(250)
                            ) togetherWith
                                    fadeOut(
                                        animationSpec =
                                            tween(150)
                                    )
                        },
                        label =
                            "combo"
                    ) {
                            comboIndex ->

                        ComboView(
                            combo =
                                combos[
                                    comboIndex
                                ],
                            comboKey =
                                comboIndex,
                            timerOn =
                                timerOn,
                            seconds =
                                secondsPerCombo,
                            repeat =
                                repeat,
                            isPaused =
                                effectivePaused,
                            onBeep = {
                                toneGen.startTone(
                                    ToneGenerator
                                        .TONE_PROP_BEEP,
                                    150
                                )
                            },
                            onRoundStartBell = {
                                ringBell()
                            },
                            onDrillActiveChanged = {
                                    active ->

                                setDrillActive(
                                    active
                                )
                            },
                            onActiveSecond = {
                                tracking
                                    .timerActiveSeconds++
                            },
                            onCycleCompleted = {
                                resolveCurrentCombo(
                                    completed =
                                        true
                                )
                            },
                            onTimeUp = {
                                setDrillActive(
                                    false
                                )

                                if (isLast) {
                                    finishSession()
                                } else {
                                    recordElapsedUntilNow()

                                    resting =
                                        true
                                }
                            }
                        )
                    }
                }
            }

            Column(
                modifier =
                    Modifier
                        .navigationBarsPadding()
            ) {
                if (
                    timerOn &&
                    !resting &&
                    !isPaused
                ) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    repeat =
                                        !repeat
                                }
                                .padding(
                                    vertical =
                                        8.dp
                                ),
                        horizontalArrangement =
                            Arrangement.Center,
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {
                        Text(
                            text =
                                if (repeat) {
                                    "🔁 Repeat: ON"
                                } else {
                                    "🔁 Repeat: OFF"
                                },
                            fontSize =
                                14.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color =
                                if (repeat) {
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
                            Modifier.height(
                                8.dp
                            )
                    )
                }

                if (
                    fightPathInfo != null &&
                    timerOn &&
                    !resting
                ) {
                    FightPathSkipWarning()

                    Spacer(
                        modifier =
                            Modifier.height(
                                10.dp
                            )
                    )
                }

                if (timerOn) {
                    Button(
                        onClick = {
                            recordElapsedUntilNow()

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
                                    "Resume session"
                                } else {
                                    "Pause session"
                                },
                            fontSize =
                                16.sp,
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
                }

                Button(
                    onClick = {
                        recordElapsedUntilNow()

                        isPaused =
                            false

                        when {
                            resting -> {
                                resting =
                                    false

                                advanceAfterResolvedCombo()
                            }

                            timerOn -> {
                                skipCurrentComboAndAdvance()
                            }

                            else -> {
                                completeCurrentComboAndAdvance()
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
                                    if (
                                        fightPathInfo != null &&
                                        timerOn &&
                                        !resting
                                    ) {
                                        Charcoal
                                    } else {
                                        FightRed
                                    }
                            )
                ) {
                    Text(
                        text =
                            when {
                                resting -> {
                                    "Skip rest  →"
                                }

                                !timerOn &&
                                        isLast -> {

                                    "Finish session"
                                }

                                !timerOn -> {
                                    "Complete & next  →"
                                }

                                mixedStageContext != null &&
                                        isLast -> {

                                    "Skip round & continue  →"
                                }

                                fightPathInfo != null &&
                                        isLast -> {

                                    "Skip round & finish"
                                }

                                fightPathInfo != null -> {
                                    "Skip round  →"
                                }

                                isLast -> {
                                    "Skip & finish"
                                }

                                else -> {
                                    "Skip  →"
                                }
                            },
                        fontSize =
                            16.sp,
                        fontWeight =
                            FontWeight.Bold,
                        color =
                            Color.White
                    )
                }

                Spacer(
                    modifier =
                        Modifier.height(
                            28.dp
                        )
                )
            }
        }
    }
}

@Composable
private fun MixedTechniqueStageStrip(
    stageContext:
    TechniqueMixedStageContext,
    roundNumber: Int,
    totalRounds: Int
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(
                12.dp
            ),
        color =
            Color.White.copy(
                alpha =
                    0.045f
            )
    ) {
        Row(
            modifier =
                Modifier.padding(
                    horizontal =
                        12.dp,
                    vertical =
                        8.dp
                ),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text =
                        "TECHNIQUE",
                    fontSize =
                        9.sp,
                    fontWeight =
                        FontWeight.Black,
                    letterSpacing =
                        1.2.sp,
                    color =
                        FightRed
                )

                Text(
                    text =
                        "Round $roundNumber of $totalRounds",
                    fontSize =
                        10.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            val nextStage =
                stageContext
                    .nextStageLabel

            if (
                nextStage != null
            ) {
                Text(
                    text =
                        "Next: $nextStage",
                    fontSize =
                        10.sp,
                    fontWeight =
                        FontWeight.Bold,
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
private fun FightPathSessionHeader(
    info: FightPathSessionInfo
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(
                14.dp
            ),
        color =
            FightRed.copy(
                alpha =
                    0.10f
            )
    ) {
        Column(
            modifier =
                Modifier.padding(
                    horizontal =
                        14.dp,
                    vertical =
                        12.dp
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
                Text(
                    text =
                        "FIGHT PATH · ${info.levelName.uppercase()}",
                    fontSize =
                        9.sp,
                    fontWeight =
                        FontWeight.Black,
                    letterSpacing =
                        1.2.sp,
                    color =
                        FightRed
                )

                Text(
                    text =
                        "SESSION ${info.levelSessionNumber} / ${info.levelSessionTotal}",
                    fontSize =
                        9.sp,
                    fontWeight =
                        FontWeight.Black,
                    letterSpacing =
                        0.8.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            Spacer(
                modifier =
                    Modifier.height(
                        6.dp
                    )
            )

            Text(
                text =
                    info.sessionTitle,
                fontSize =
                    16.sp,
                lineHeight =
                    20.sp,
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
                        3.dp
                    )
            )

            Text(
                text =
                    "${info.chapterTitle} · Chapter session ${info.chapterSessionNumber} of ${info.chapterSessionTotal}",
                fontSize =
                    10.sp,
                lineHeight =
                    15.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FightPathSkipWarning() {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(
                12.dp
            ),
        color =
            FightRed.copy(
                alpha =
                    0.10f
            )
    ) {
        Text(
            text =
                "Skipping a round means this Fight Path session will not count toward progression.",
            modifier =
                Modifier.padding(
                    horizontal =
                        14.dp,
                    vertical =
                        9.dp
                ),
            fontSize =
                10.sp,
            lineHeight =
                14.sp,
            fontWeight =
                FontWeight.Bold,
            textAlign =
                TextAlign.Center,
            color =
                FightRed
        )
    }
}

@Composable
private fun ComboView(
    combo: Combo,
    comboKey: Int,
    timerOn: Boolean,
    seconds: Int,
    repeat: Boolean,
    isPaused: Boolean,
    onBeep: () -> Unit,
    onRoundStartBell: () -> Unit,
    onDrillActiveChanged:
        (Boolean) -> Unit,
    onActiveSecond: () -> Unit,
    onCycleCompleted: () -> Unit,
    onTimeUp: () -> Unit
) {
    var getReady by
    remember(
        comboKey,
        timerOn
    ) {
        mutableIntStateOf(
            if (timerOn) {
                3
            } else {
                0
            }
        )
    }

    val currentPaused by
    rememberUpdatedState(
        isPaused
    )

    val currentOnBeep by
    rememberUpdatedState(
        onBeep
    )

    val currentRoundStartBell by
    rememberUpdatedState(
        onRoundStartBell
    )

    val currentOnDrillActiveChanged by
    rememberUpdatedState(
        onDrillActiveChanged
    )

    LaunchedEffect(
        comboKey,
        timerOn
    ) {
        if (timerOn) {
            currentOnDrillActiveChanged(
                false
            )

            getReady =
                3

            while (
                getReady > 0
            ) {
                while (
                    currentPaused
                ) {
                    delay(100L)
                }

                currentOnBeep()

                waitForActiveSecond {
                    currentPaused
                }

                getReady--
            }

            while (
                currentPaused
            ) {
                delay(100L)
            }

            currentRoundStartBell()

            currentOnDrillActiveChanged(
                true
            )
        } else {
            currentOnDrillActiveChanged(
                true
            )
        }
    }

    Column(
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Surface(
            shape =
                RoundedCornerShape(
                    20.dp
                ),
            color =
                if (
                    isPaused
                ) {
                    FightRed.copy(
                        alpha =
                            0.18f
                    )
                } else {
                    Charcoal
                }
        ) {
            Text(
                text =
                    when {
                        isPaused -> {
                            "PAUSED"
                        }

                        timerOn &&
                                getReady >
                                0 -> {

                            "GET READY"
                        }

                        else -> {
                            combo.phase
                        }
                    },
                fontSize =
                    12.sp,
                fontWeight =
                    FontWeight.Bold,
                letterSpacing =
                    2.sp,
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
                    28.dp
                )
        )

        if (
            timerOn &&
            getReady > 0
        ) {
            Text(
                text =
                    "$getReady",
                fontSize =
                    72.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(
                        28.dp
                    )
            )
        } else if (timerOn) {
            CountdownText(
                totalSeconds =
                    seconds,
                repeat =
                    repeat,
                isPaused =
                    isPaused,
                onBeep =
                    onBeep,
                onActiveSecond =
                    onActiveSecond,
                onCycleCompleted =
                    onCycleCompleted,
                onTimeUp = {
                    currentOnDrillActiveChanged(
                        false
                    )

                    onTimeUp()
                }
            )

            Spacer(
                modifier =
                    Modifier.height(
                        28.dp
                    )
            )
        }

        Text(
            text =
                combo.moves,
            fontSize =
                44.sp,
            lineHeight =
                50.sp,
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
                    20.dp
                )
        )

        Text(
            text =
                combo.cue,
            fontSize =
                16.sp,
            lineHeight =
                24.sp,
            textAlign =
                TextAlign.Center,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant,
            modifier =
                Modifier.padding(
                    horizontal =
                        16.dp
                )
        )

        if (isPaused) {
            Spacer(
                modifier =
                    Modifier.height(
                        18.dp
                    )
            )

            Text(
                text =
                    "Timer paused",
                fontSize =
                    14.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    FightRed
            )
        }
    }
}

@Composable
private fun CountdownText(
    totalSeconds: Int,
    repeat: Boolean,
    isPaused: Boolean,
    onBeep: () -> Unit,
    onActiveSecond: () -> Unit,
    onCycleCompleted: () -> Unit,
    onTimeUp: () -> Unit
) {
    var cycle by remember {
        mutableIntStateOf(0)
    }

    var remaining by
    remember(
        cycle,
        totalSeconds
    ) {
        mutableIntStateOf(
            totalSeconds
        )
    }

    val currentRepeat by
    rememberUpdatedState(
        repeat
    )

    val currentPaused by
    rememberUpdatedState(
        isPaused
    )

    val currentOnBeep by
    rememberUpdatedState(
        onBeep
    )

    val currentOnActiveSecond by
    rememberUpdatedState(
        onActiveSecond
    )

    val currentOnCycleCompleted by
    rememberUpdatedState(
        onCycleCompleted
    )

    val currentOnTimeUp by
    rememberUpdatedState(
        onTimeUp
    )

    LaunchedEffect(
        cycle,
        totalSeconds
    ) {
        remaining =
            totalSeconds

        while (
            remaining > 0
        ) {
            waitForActiveSecond {
                currentPaused
            }

            remaining--

            currentOnActiveSecond()

            if (
                remaining in 1..3
            ) {
                currentOnBeep()
            }
        }

        while (
            currentPaused
        ) {
            delay(100L)
        }

        currentOnBeep()

        currentOnCycleCompleted()

        if (
            currentRepeat
        ) {
            cycle++
        } else {
            currentOnTimeUp()
        }
    }

    val minutes =
        remaining / 60

    val seconds =
        remaining % 60

    Text(
        text =
            "%d:%02d".format(
                minutes,
                seconds
            ),
        fontSize =
            56.sp,
        fontWeight =
            FontWeight.Black,
        color =
            FightRed
    )
}

@Composable
private fun RestView(
    seconds: Int,
    isPaused: Boolean,
    onBeep: () -> Unit,
    onDone: () -> Unit
) {
    var remaining by
    remember(seconds) {
        mutableIntStateOf(
            seconds
        )
    }

    val currentPaused by
    rememberUpdatedState(
        isPaused
    )

    val currentOnBeep by
    rememberUpdatedState(
        onBeep
    )

    val currentOnDone by
    rememberUpdatedState(
        onDone
    )

    LaunchedEffect(
        seconds
    ) {
        remaining =
            seconds

        while (
            remaining > 0
        ) {
            waitForActiveSecond {
                currentPaused
            }

            remaining--

            if (
                remaining in 1..3
            ) {
                currentOnBeep()
            }
        }

        while (
            currentPaused
        ) {
            delay(100L)
        }

        currentOnBeep()

        currentOnDone()
    }

    Column(
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            text =
                if (
                    isPaused
                ) {
                    "REST PAUSED"
                } else {
                    "REST"
                },
            fontSize =
                16.sp,
            fontWeight =
                FontWeight.Bold,
            letterSpacing =
                4.sp,
            color =
                if (
                    isPaused
                ) {
                    FightRed
                } else {
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                }
        )

        Spacer(
            modifier =
                Modifier.height(
                    20.dp
                )
        )

        Text(
            text =
                "$remaining",
            fontSize =
                72.sp,
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
                    12.dp
                )
        )

        Text(
            text =
                if (
                    isPaused
                ) {
                    "Rest timer is paused."
                } else {
                    "Breathe. Next combo coming up."
                },
            fontSize =
                14.sp,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )
    }
}

@Composable
private fun ProgressBar(
    progress: Float
) {
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
                        3.dp
                    )
                )
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth(
                        progress.coerceIn(
                            0f,
                            1f
                        )
                    )
                    .height(
                        6.dp
                    )
                    .background(
                        FightRed,
                        RoundedCornerShape(
                            3.dp
                        )
                    )
        )
    }
}

@Composable
private fun EmptySessionScreen(
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
                    "No combos available",
                fontSize =
                    24.sp,
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
                        "Return home",
                    fontWeight =
                        FontWeight.Bold
                )
            }
        }
    }
}

private suspend fun waitForActiveSecond(
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

private fun Long.toRoundedSeconds():
        Int {

    if (this <= 0L) {
        return 0
    }

    return (
            (this + 500L) /
                    1_000L
            )
        .coerceAtMost(
            Int.MAX_VALUE
                .toLong()
        )
        .toInt()
}