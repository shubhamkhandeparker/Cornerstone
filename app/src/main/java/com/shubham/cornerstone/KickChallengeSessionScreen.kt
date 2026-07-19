package com.shubham.cornerstone

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.SystemClock
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import kotlin.math.ceil

private const val DEFAULT_KICK_TARGET = 100
private const val DEFAULT_SET_SIZE = 10
private const val DEFAULT_SET_REST_SECONDS = 15

private const val MIN_KICKS_PER_SET = 5
private const val KICKS_PER_SET_STEP = 5

private const val MIN_REST_SECONDS = 0
private const val MAX_REST_SECONDS = 120
private const val REST_SECONDS_STEP = 5

enum class KickCountingMode {
    GUIDED_SOLO,
    MANUAL_PARTNER
}

enum class GuidedKickPace(
    val title: String,
    val description: String,
    val intervalMilliseconds: Long
) {
    BEGINNER(
        title = "Beginner",
        description = "1 kick every 2 sec",
        intervalMilliseconds = 2_000L
    ),

    NORMAL(
        title = "Normal",
        description = "1 kick every 1.5 sec",
        intervalMilliseconds = 1_500L
    ),

    FAST(
        title = "Fast",
        description = "1 kick every 1 sec",
        intervalMilliseconds = 1_000L
    )
}

data class KickChallengeSessionResult(
    val mode: KickCountingMode,
    val repetitionCount: Int,
    val activeSeconds: Int,
    val targetRepetitions: Int,
    val completedTarget: Boolean,
    val genuinelyFinished: Boolean
)

private enum class KickSessionPhase {
    SETUP,
    GET_READY,
    KICKING,
    RESTING,
    PAUSED,
    COMPLETED
}

private class KickActiveTimeTracker {

    var activeMilliseconds: Long = 0L
        private set

    private var lastTimestampMilliseconds: Long =
        SystemClock.elapsedRealtime()

    fun reset() {
        activeMilliseconds = 0L
        lastTimestampMilliseconds =
            SystemClock.elapsedRealtime()
    }

    fun recordUntilNow(
        shouldCount: Boolean
    ) {
        val now =
            SystemClock.elapsedRealtime()

        val elapsed =
            (
                    now -
                            lastTimestampMilliseconds
                    ).coerceAtLeast(0L)

        if (shouldCount) {
            activeMilliseconds += elapsed
        }

        lastTimestampMilliseconds = now
    }

    fun activeSeconds(): Int {
        if (activeMilliseconds <= 0L) {
            return 0
        }

        return ceil(
            activeMilliseconds / 1_000.0
        )
            .coerceAtMost(
                Int.MAX_VALUE.toDouble()
            )
            .toInt()
    }
}

@Composable
fun KickChallengeSessionScreen(
    targetRepetitions: Int = DEFAULT_KICK_TARGET,
    setSize: Int = DEFAULT_SET_SIZE,
    restBetweenSetsSeconds: Int =
        DEFAULT_SET_REST_SECONDS,
    onSaveResult: (
        KickChallengeSessionResult
    ) -> Unit,
    onExit: () -> Unit
) {
    val lifecycleOwner =
        LocalLifecycleOwner.current

    val safeTarget =
        targetRepetitions.coerceAtLeast(
            MIN_KICKS_PER_SET
        )

    val initialSetSize =
        setSize.coerceIn(
            minimumValue =
                MIN_KICKS_PER_SET,
            maximumValue =
                safeTarget
        )

    val initialRestSeconds =
        restBetweenSetsSeconds.coerceIn(
            minimumValue =
                MIN_REST_SECONDS,
            maximumValue =
                MAX_REST_SECONDS
        )

    var selectedSetSize by remember(
        safeTarget,
        setSize
    ) {
        mutableIntStateOf(
            initialSetSize
        )
    }

    var selectedRestSeconds by remember(
        restBetweenSetsSeconds
    ) {
        mutableIntStateOf(
            initialRestSeconds
        )
    }

    val safeSetSize =
        selectedSetSize.coerceIn(
            minimumValue =
                MIN_KICKS_PER_SET,
            maximumValue =
                safeTarget
        )

    val safeRestSeconds =
        selectedRestSeconds.coerceIn(
            minimumValue =
                MIN_REST_SECONDS,
            maximumValue =
                MAX_REST_SECONDS
        )

    val totalSets =
        ceil(
            safeTarget.toDouble() /
                    safeSetSize.toDouble()
        ).toInt()

    var selectedMode by remember {
        mutableStateOf(
            KickCountingMode.GUIDED_SOLO
        )
    }

    var selectedPace by remember {
        mutableStateOf(
            GuidedKickPace.NORMAL
        )
    }

    var sessionStarted by remember {
        mutableStateOf(false)
    }

    var sessionEnded by remember {
        mutableStateOf(false)
    }

    var isPaused by remember {
        mutableStateOf(false)
    }

    var isLifecycleResumed by remember {
        mutableStateOf(
            lifecycleOwner.lifecycle
                .currentState
                .isAtLeast(
                    Lifecycle.State.RESUMED
                )
        )
    }

    var phase by remember {
        mutableStateOf(
            KickSessionPhase.SETUP
        )
    }

    var repetitionCount by remember {
        mutableIntStateOf(0)
    }

    var currentSet by remember {
        mutableIntStateOf(1)
    }

    var getReadySeconds by remember {
        mutableIntStateOf(3)
    }

    var restRemainingSeconds by remember {
        mutableIntStateOf(0)
    }

    var isKickPhase by remember {
        mutableStateOf(false)
    }

    var displayedActiveSeconds by remember {
        mutableIntStateOf(0)
    }

    var runToken by remember {
        mutableIntStateOf(0)
    }

    var showExitDialog by remember {
        mutableStateOf(false)
    }

    val tracker = remember {
        KickActiveTimeTracker()
    }

    val toneGenerator = remember {
        ToneGenerator(
            AudioManager.STREAM_MUSIC,
            100
        )
    }

    val effectivePaused =
        isPaused || !isLifecycleResumed

    val shouldCountActiveTime =
        sessionStarted &&
                !sessionEnded &&
                !effectivePaused &&
                isKickPhase

    val currentShouldCountActiveTime by
    rememberUpdatedState(
        shouldCountActiveTime
    )

    val currentSessionEnded by
    rememberUpdatedState(
        sessionEnded
    )

    val currentEffectivePaused by
    rememberUpdatedState(
        effectivePaused
    )

    val currentRepetitionCount by
    rememberUpdatedState(
        repetitionCount
    )

    val kicksInsideCurrentSet =
        repetitionCount %
                safeSetSize

    val kicksUntilRest =
        if (kicksInsideCurrentSet == 0) {
            safeSetSize
        } else {
            safeSetSize -
                    kicksInsideCurrentSet
        }

    val remainingTargetKicks =
        (
                safeTarget -
                        repetitionCount
                ).coerceAtLeast(0)

    val manualQuickAddAmount =
        minOf(
            5,
            kicksUntilRest,
            remainingTargetKicks
        ).coerceAtLeast(1)

    fun playKickCue() {
        toneGenerator.startTone(
            ToneGenerator.TONE_PROP_BEEP,
            140
        )
    }

    fun playSetBell() {
        toneGenerator.startTone(
            ToneGenerator.TONE_PROP_ACK,
            220
        )
    }

    fun recordActiveTimeNow() {
        tracker.recordUntilNow(
            shouldCount =
                shouldCountActiveTime
        )

        displayedActiveSeconds =
            tracker.activeSeconds()
    }

    fun buildResult(
        completedTarget: Boolean
    ): KickChallengeSessionResult {
        recordActiveTimeNow()

        return KickChallengeSessionResult(
            mode = selectedMode,
            repetitionCount =
                repetitionCount.coerceIn(
                    minimumValue = 0,
                    maximumValue =
                        safeTarget
                ),
            activeSeconds =
                tracker.activeSeconds(),
            targetRepetitions =
                safeTarget,
            completedTarget =
                completedTarget,
            genuinelyFinished =
                repetitionCount > 0
        )
    }

    fun saveSession(
        completedTarget: Boolean
    ) {
        if (sessionEnded) {
            return
        }

        recordActiveTimeNow()

        sessionEnded = true
        sessionStarted = false
        isKickPhase = false
        isPaused = false

        phase =
            if (completedTarget) {
                KickSessionPhase.COMPLETED
            } else {
                KickSessionPhase.SETUP
            }

        if (
            repetitionCount <= 0 ||
            tracker.activeSeconds() <= 0
        ) {
            onExit()
            return
        }

        onSaveResult(
            buildResult(
                completedTarget =
                    completedTarget
            )
        )
    }

    fun discardAndExit() {
        recordActiveTimeNow()

        sessionEnded = true
        sessionStarted = false
        isKickPhase = false
        isPaused = false
        showExitDialog = false

        onExit()
    }

    fun requestExit() {
        when {
            sessionEnded -> {
                onExit()
            }

            sessionStarted &&
                    repetitionCount > 0 -> {

                recordActiveTimeNow()
                isPaused = true
                showExitDialog = true
            }

            else -> {
                discardAndExit()
            }
        }
    }

    fun startSession() {
        tracker.reset()

        displayedActiveSeconds = 0
        repetitionCount = 0
        currentSet = 1
        getReadySeconds = 3
        restRemainingSeconds = 0

        sessionEnded = false
        sessionStarted = true
        isPaused = false
        isKickPhase = false
        phase =
            KickSessionPhase.GET_READY

        runToken += 1
    }

    fun beginManualRest() {
        isKickPhase = false
        phase = KickSessionPhase.RESTING
        restRemainingSeconds =
            safeRestSeconds
    }

    fun addManualKicks(
        requestedAmount: Int
    ) {
        if (
            selectedMode !=
            KickCountingMode.MANUAL_PARTNER ||
            !sessionStarted ||
            sessionEnded ||
            effectivePaused ||
            phase !=
            KickSessionPhase.KICKING
        ) {
            return
        }

        recordActiveTimeNow()

        val currentKicksInSet =
            repetitionCount %
                    safeSetSize

        val availableBeforeRest =
            if (currentKicksInSet == 0) {
                safeSetSize
            } else {
                safeSetSize -
                        currentKicksInSet
            }

        val availableBeforeTarget =
            (
                    safeTarget -
                            repetitionCount
                    ).coerceAtLeast(0)

        val amountToAdd =
            minOf(
                requestedAmount
                    .coerceAtLeast(0),
                availableBeforeRest,
                availableBeforeTarget
            )

        if (amountToAdd <= 0) {
            return
        }

        repetitionCount +=
            amountToAdd

        currentSet =
            (
                    (repetitionCount - 1) /
                            safeSetSize
                    ).coerceAtMost(
                    totalSets - 1
                ) + 1

        playKickCue()

        if (
            repetitionCount >=
            safeTarget
        ) {
            saveSession(
                completedTarget = true
            )

            return
        }

        val setFinished =
            repetitionCount %
                    safeSetSize == 0

        if (setFinished) {
            beginManualRest()
        }
    }

    fun undoManualKick() {
        if (
            selectedMode !=
            KickCountingMode.MANUAL_PARTNER ||
            !sessionStarted ||
            sessionEnded ||
            repetitionCount <= 0
        ) {
            return
        }

        recordActiveTimeNow()

        repetitionCount =
            (repetitionCount - 1)
                .coerceAtLeast(0)

        currentSet =
            if (repetitionCount <= 0) {
                1
            } else {
                (
                        (repetitionCount - 1) /
                                safeSetSize
                        ).coerceAtMost(
                        totalSets - 1
                    ) + 1
            }
    }

    BackHandler {
        requestExit()
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
                        tracker.recordUntilNow(
                            shouldCount = false
                        )

                        isLifecycleResumed = true
                    }

                    Lifecycle.Event.ON_PAUSE,
                    Lifecycle.Event.ON_STOP,
                    Lifecycle.Event.ON_DESTROY -> {

                        recordActiveTimeNow()
                        isLifecycleResumed = false
                    }

                    else -> Unit
                }
            }

        lifecycleOwner.lifecycle
            .addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle
                .removeObserver(observer)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            toneGenerator.release()
        }
    }

    LaunchedEffect(
        sessionStarted,
        sessionEnded
    ) {
        tracker.recordUntilNow(
            shouldCount = false
        )

        while (
            sessionStarted &&
            !sessionEnded
        ) {
            delay(100L)

            tracker.recordUntilNow(
                shouldCount =
                    currentShouldCountActiveTime
            )

            displayedActiveSeconds =
                tracker.activeSeconds()
        }
    }

    /*
     * Guided Solo Mode.
     *
     * The app automatically produces one audio cue
     * for every kick and counts the repetition.
     */
    LaunchedEffect(
        runToken,
        selectedMode
    ) {
        if (
            !sessionStarted ||
            sessionEnded ||
            selectedMode !=
            KickCountingMode.GUIDED_SOLO
        ) {
            return@LaunchedEffect
        }

        phase =
            KickSessionPhase.GET_READY

        isKickPhase = false
        getReadySeconds = 3

        while (
            getReadySeconds > 0 &&
            !currentSessionEnded
        ) {
            while (
                currentEffectivePaused &&
                !currentSessionEnded
            ) {
                delay(100L)
            }

            if (currentSessionEnded) {
                return@LaunchedEffect
            }

            playKickCue()

            waitForKickActiveMilliseconds(
                durationMilliseconds =
                    1_000L,
                isPaused = {
                    currentEffectivePaused
                },
                shouldStop = {
                    currentSessionEnded
                }
            )

            getReadySeconds -= 1
        }

        if (currentSessionEnded) {
            return@LaunchedEffect
        }

        playSetBell()

        phase =
            KickSessionPhase.KICKING

        isKickPhase = true

        while (
            currentRepetitionCount <
            safeTarget &&
            !currentSessionEnded
        ) {
            while (
                currentEffectivePaused &&
                !currentSessionEnded
            ) {
                delay(100L)
            }

            if (currentSessionEnded) {
                return@LaunchedEffect
            }

            playKickCue()

            repetitionCount =
                (
                        currentRepetitionCount + 1
                        ).coerceAtMost(
                        safeTarget
                    )

            currentSet =
                (
                        (repetitionCount - 1) /
                                safeSetSize
                        ).coerceAtMost(
                        totalSets - 1
                    ) + 1

            val targetReached =
                repetitionCount >=
                        safeTarget

            if (targetReached) {
                waitForKickActiveMilliseconds(
                    durationMilliseconds =
                        500L,
                    isPaused = {
                        currentEffectivePaused
                    },
                    shouldStop = {
                        currentSessionEnded
                    }
                )

                if (!currentSessionEnded) {
                    playSetBell()

                    saveSession(
                        completedTarget = true
                    )
                }

                return@LaunchedEffect
            }

            waitForKickActiveMilliseconds(
                durationMilliseconds =
                    selectedPace
                        .intervalMilliseconds,
                isPaused = {
                    currentEffectivePaused
                },
                shouldStop = {
                    currentSessionEnded
                }
            )

            val setFinished =
                repetitionCount %
                        safeSetSize == 0

            if (
                setFinished &&
                repetitionCount <
                safeTarget &&
                !currentSessionEnded
            ) {
                isKickPhase = false

                phase =
                    KickSessionPhase.RESTING

                playSetBell()

                restRemainingSeconds =
                    safeRestSeconds

                while (
                    restRemainingSeconds > 0 &&
                    !currentSessionEnded
                ) {
                    waitForKickActiveMilliseconds(
                        durationMilliseconds =
                            1_000L,
                        isPaused = {
                            currentEffectivePaused
                        },
                        shouldStop = {
                            currentSessionEnded
                        }
                    )

                    if (!currentSessionEnded) {
                        restRemainingSeconds -= 1

                        if (
                            restRemainingSeconds in
                            1..3
                        ) {
                            playKickCue()
                        }
                    }
                }

                if (currentSessionEnded) {
                    return@LaunchedEffect
                }

                currentSet =
                    (
                            repetitionCount /
                                    safeSetSize
                            ).coerceAtMost(
                            totalSets - 1
                        ) + 1

                phase =
                    KickSessionPhase.KICKING

                isKickPhase = true

                playSetBell()
            }
        }
    }

    /*
     * Manual Partner Mode preparation countdown.
     */
    LaunchedEffect(
        runToken,
        selectedMode
    ) {
        if (
            !sessionStarted ||
            sessionEnded ||
            selectedMode !=
            KickCountingMode.MANUAL_PARTNER
        ) {
            return@LaunchedEffect
        }

        phase =
            KickSessionPhase.GET_READY

        isKickPhase = false
        getReadySeconds = 3

        while (
            getReadySeconds > 0 &&
            !currentSessionEnded
        ) {
            while (
                currentEffectivePaused &&
                !currentSessionEnded
            ) {
                delay(100L)
            }

            if (currentSessionEnded) {
                return@LaunchedEffect
            }

            playKickCue()

            waitForKickActiveMilliseconds(
                durationMilliseconds =
                    1_000L,
                isPaused = {
                    currentEffectivePaused
                },
                shouldStop = {
                    currentSessionEnded
                }
            )

            getReadySeconds -= 1
        }

        if (!currentSessionEnded) {
            playSetBell()

            phase =
                KickSessionPhase.KICKING

            isKickPhase = true
        }
    }

    /*
     * Manual Partner Mode automatic rest.
     */
    LaunchedEffect(
        runToken,
        selectedMode,
        phase
    ) {
        if (
            !sessionStarted ||
            sessionEnded ||
            selectedMode !=
            KickCountingMode.MANUAL_PARTNER ||
            phase !=
            KickSessionPhase.RESTING
        ) {
            return@LaunchedEffect
        }

        while (
            restRemainingSeconds > 0 &&
            !currentSessionEnded
        ) {
            waitForKickActiveMilliseconds(
                durationMilliseconds =
                    1_000L,
                isPaused = {
                    currentEffectivePaused
                },
                shouldStop = {
                    currentSessionEnded
                }
            )

            if (!currentSessionEnded) {
                restRemainingSeconds -= 1

                if (
                    restRemainingSeconds in
                    1..3
                ) {
                    playKickCue()
                }
            }
        }

        if (
            !currentSessionEnded &&
            sessionStarted
        ) {
            currentSet =
                (
                        repetitionCount /
                                safeSetSize
                        ).coerceAtMost(
                        totalSets - 1
                    ) + 1

            phase =
                KickSessionPhase.KICKING

            isKickPhase = true

            playSetBell()
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = {
                showExitDialog = false
            },
            title = {
                Text(
                    text = "End kick session?"
                )
            },
            text = {
                Text(
                    text =
                        "You have counted $repetitionCount kicks. Save this progress or discard the current session?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showExitDialog = false

                        saveSession(
                            completedTarget =
                                repetitionCount >=
                                        safeTarget
                        )
                    }
                ) {
                    Text(
                        text = "Save progress",
                        color = FightRed,
                        fontWeight =
                            FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        discardAndExit()
                    }
                ) {
                    Text(
                        text = "Discard"
                    )
                }
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
        if (!sessionStarted) {
            KickChallengeSetupContent(
                targetRepetitions =
                    safeTarget,
                selectedSetSize =
                    safeSetSize,
                selectedRestSeconds =
                    safeRestSeconds,
                totalSets =
                    totalSets,
                selectedMode =
                    selectedMode,
                selectedPace =
                    selectedPace,
                onModeSelected = {
                        mode ->

                    selectedMode = mode
                },
                onPaceSelected = {
                        pace ->

                    selectedPace = pace
                },
                onDecreaseSetSize = {
                    selectedSetSize =
                        (
                                selectedSetSize -
                                        KICKS_PER_SET_STEP
                                ).coerceAtLeast(
                                MIN_KICKS_PER_SET
                            )
                },
                onIncreaseSetSize = {
                    selectedSetSize =
                        (
                                selectedSetSize +
                                        KICKS_PER_SET_STEP
                                ).coerceAtMost(
                                safeTarget
                            )
                },
                onDecreaseRest = {
                    selectedRestSeconds =
                        (
                                selectedRestSeconds -
                                        REST_SECONDS_STEP
                                ).coerceAtLeast(
                                MIN_REST_SECONDS
                            )
                },
                onIncreaseRest = {
                    selectedRestSeconds =
                        (
                                selectedRestSeconds +
                                        REST_SECONDS_STEP
                                ).coerceAtMost(
                                MAX_REST_SECONDS
                            )
                },
                onStart = {
                    startSession()
                },
                onExit = {
                    requestExit()
                }
            )
        } else {
            KickChallengeActiveContent(
                targetRepetitions =
                    safeTarget,
                repetitionCount =
                    repetitionCount,
                activeSeconds =
                    displayedActiveSeconds,
                currentSet =
                    currentSet,
                totalSets =
                    totalSets,
                selectedMode =
                    selectedMode,
                selectedPace =
                    selectedPace,
                phase =
                    if (
                        effectivePaused &&
                        phase !=
                        KickSessionPhase.COMPLETED
                    ) {
                        KickSessionPhase.PAUSED
                    } else {
                        phase
                    },
                getReadySeconds =
                    getReadySeconds,
                restRemainingSeconds =
                    restRemainingSeconds,
                isPaused =
                    isPaused,
                controlsEnabled =
                    !effectivePaused &&
                            phase ==
                            KickSessionPhase.KICKING,
                manualQuickAddAmount =
                    manualQuickAddAmount,
                onAddOne = {
                    addManualKicks(1)
                },
                onQuickAdd = {
                    addManualKicks(
                        manualQuickAddAmount
                    )
                },
                onUndo = {
                    undoManualKick()
                },
                onPauseToggle = {
                    recordActiveTimeNow()
                    isPaused = !isPaused
                },
                onEndAndSave = {
                    if (repetitionCount > 0) {
                        recordActiveTimeNow()
                        isPaused = true
                        showExitDialog = true
                    } else {
                        requestExit()
                    }
                }
            )
        }
    }
}

@Composable
private fun KickChallengeSetupContent(
    targetRepetitions: Int,
    selectedSetSize: Int,
    selectedRestSeconds: Int,
    totalSets: Int,
    selectedMode: KickCountingMode,
    selectedPace: GuidedKickPace,
    onModeSelected: (KickCountingMode) -> Unit,
    onPaceSelected: (GuidedKickPace) -> Unit,
    onDecreaseSetSize: () -> Unit,
    onIncreaseSetSize: () -> Unit,
    onDecreaseRest: () -> Unit,
    onIncreaseRest: () -> Unit,
    onStart: () -> Unit,
    onExit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(horizontal = 22.dp)
    ) {
        Spacer(
            modifier = Modifier.height(16.dp)
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
                text = "Exit",
                fontSize = 14.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,
                modifier = Modifier
                    .clickable {
                        onExit()
                    }
                    .padding(8.dp)
            )

            Text(
                text = "KICK CHALLENGE",
                fontSize = 12.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 2.sp,
                color = FightRed
            )
        }

        Spacer(
            modifier = Modifier.height(26.dp)
        )

        Text(
            text =
                "$targetRepetitions Kicks",
            fontSize = 38.sp,
            fontWeight = FontWeight.Black,
            color =
                MaterialTheme
                    .colorScheme
                    .onBackground
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text =
                "The official challenge target stays fixed at $targetRepetitions kicks. Choose how each workout set will be structured.",
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        KickModeSelectionCard(
            title = "Guided Solo",
            description =
                "Put the phone near the bag and kick on every audio cue.",
            selected =
                selectedMode ==
                        KickCountingMode
                            .GUIDED_SOLO,
            onClick = {
                onModeSelected(
                    KickCountingMode
                        .GUIDED_SOLO
                )
            }
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        KickModeSelectionCard(
            title = "Manual Partner",
            description =
                "A training partner taps the counter while the fighter performs each kick.",
            selected =
                selectedMode ==
                        KickCountingMode
                            .MANUAL_PARTNER,
            onClick = {
                onModeSelected(
                    KickCountingMode
                        .MANUAL_PARTNER
                )
            }
        )

        if (
            selectedMode ==
            KickCountingMode.GUIDED_SOLO
        ) {
            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            Text(
                text = "KICK PACE",
                fontSize = 12.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 1.5.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            GuidedKickPace.entries.forEach {
                    pace ->

                KickPaceRow(
                    pace = pace,
                    selected =
                        pace ==
                                selectedPace,
                    onClick = {
                        onPaceSelected(pace)
                    }
                )

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )
            }
        }

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        Text(
            text = "SESSION FORMAT",
            fontSize = 12.sp,
            fontWeight =
                FontWeight.Black,
            letterSpacing = 1.5.sp,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        KickSettingStepper(
            title = "Kicks per set",
            value =
                "$selectedSetSize",
            description =
                "$totalSets total sets",
            decreaseEnabled =
                selectedSetSize >
                        MIN_KICKS_PER_SET,
            increaseEnabled =
                selectedSetSize <
                        targetRepetitions,
            onDecrease =
                onDecreaseSetSize,
            onIncrease =
                onIncreaseSetSize
        )

        Spacer(
            modifier =
                Modifier.height(10.dp)
        )

        KickSettingStepper(
            title = "Rest between sets",
            value =
                "$selectedRestSeconds sec",
            description =
                if (
                    selectedRestSeconds == 0
                ) {
                    "No automatic rest"
                } else {
                    "Paused and rest time do not count"
                },
            decreaseEnabled =
                selectedRestSeconds >
                        MIN_REST_SECONDS,
            increaseEnabled =
                selectedRestSeconds <
                        MAX_REST_SECONDS,
            onDecrease =
                onDecreaseRest,
            onIncrease =
                onIncreaseRest
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Surface(
            color = FightRed.copy(
                alpha = 0.11f
            ),
            shape =
                RoundedCornerShape(18.dp),
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Text(
                text =
                    "$selectedSetSize kicks per set • $totalSets sets • $selectedRestSeconds sec rest",
                fontSize = 13.sp,
                lineHeight = 20.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground,
                modifier =
                    Modifier.padding(16.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(26.dp)
        )

        Button(
            onClick = onStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),
            shape =
                RoundedCornerShape(18.dp),
            colors =
                ButtonDefaults
                    .buttonColors(
                        containerColor =
                            FightRed
                    )
        ) {
            Text(
                text =
                    if (
                        selectedMode ==
                        KickCountingMode
                            .GUIDED_SOLO
                    ) {
                        "Start guided session"
                    } else {
                        "Start partner counter"
                    },
                fontSize = 16.sp,
                fontWeight =
                    FontWeight.Black
            )
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )
    }
}

@Composable
private fun KickSettingStepper(
    title: String,
    value: String,
    description: String,
    decreaseEnabled: Boolean,
    increaseEnabled: Boolean,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        color = Charcoal,
        shape =
            RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier =
                Modifier.padding(16.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column(
                modifier =
                    Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight =
                        FontWeight.Bold,
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
                    text = description,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            StepperButton(
                text = "−",
                enabled =
                    decreaseEnabled,
                onClick =
                    onDecrease
            )

            Text(
                text = value,
                fontSize = 15.sp,
                fontWeight =
                    FontWeight.Black,
                textAlign =
                    TextAlign.Center,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground,
                modifier =
                    Modifier.padding(
                        horizontal = 14.dp
                    )
            )

            StepperButton(
                text = "+",
                enabled =
                    increaseEnabled,
                onClick =
                    onIncrease
            )
        }
    }
}

@Composable
private fun StepperButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable(
                enabled = enabled
            ) {
                onClick()
            },
        color =
            if (enabled) {
                FightRed.copy(
                    alpha = 0.18f
                )
            } else {
                Color(0xFF29282C)
            },
        shape =
            RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    horizontal = 14.dp,
                    vertical = 8.dp
                ),
            contentAlignment =
                Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 20.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    if (enabled) {
                        FightRed
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
private fun KickChallengeActiveContent(
    targetRepetitions: Int,
    repetitionCount: Int,
    activeSeconds: Int,
    currentSet: Int,
    totalSets: Int,
    selectedMode: KickCountingMode,
    selectedPace: GuidedKickPace,
    phase: KickSessionPhase,
    getReadySeconds: Int,
    restRemainingSeconds: Int,
    isPaused: Boolean,
    controlsEnabled: Boolean,
    manualQuickAddAmount: Int,
    onAddOne: () -> Unit,
    onQuickAdd: () -> Unit,
    onUndo: () -> Unit,
    onPauseToggle: () -> Unit,
    onEndAndSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = 22.dp),
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Text(
            text =
                if (
                    selectedMode ==
                    KickCountingMode
                        .GUIDED_SOLO
                ) {
                    "GUIDED SOLO"
                } else {
                    "MANUAL PARTNER"
                },
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 2.sp,
            color = FightRed
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        KickPhaseBadge(
            phase = phase
        )

        Spacer(
            modifier = Modifier.height(26.dp)
        )

        when (phase) {
            KickSessionPhase.GET_READY -> {
                Text(
                    text = "$getReadySeconds",
                    fontSize = 76.sp,
                    fontWeight =
                        FontWeight.Black,
                    color = FightRed
                )

                Text(
                    text = "Get ready",
                    fontSize = 16.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            KickSessionPhase.RESTING -> {
                Text(
                    text =
                        "$restRemainingSeconds",
                    fontSize = 76.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text =
                        "Breathe. Next set is coming.",
                    fontSize = 15.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            else -> {
                Text(
                    text =
                        "$repetitionCount",
                    fontSize = 82.sp,
                    fontWeight =
                        FontWeight.Black,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text =
                        "of $targetRepetitions kicks",
                    fontSize = 17.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }
        }

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        KickProgressBar(
            progress =
                repetitionCount.toFloat() /
                        targetRepetitions
                            .toFloat()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier =
                Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {
            KickStat(
                title = "SET",
                value =
                    "$currentSet / $totalSets"
            )

            KickStat(
                title = "ACTIVE",
                value =
                    formatKickTime(
                        activeSeconds
                    )
            )

            KickStat(
                title = "MODE",
                value =
                    if (
                        selectedMode ==
                        KickCountingMode
                            .GUIDED_SOLO
                    ) {
                        selectedPace.title
                    } else {
                        "Partner"
                    }
            )
        }

        Spacer(
            modifier =
                Modifier.weight(1f)
        )

        if (
            selectedMode ==
            KickCountingMode.MANUAL_PARTNER
        ) {
            Button(
                onClick = onAddOne,
                enabled = controlsEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                shape =
                    RoundedCornerShape(24.dp),
                colors =
                    ButtonDefaults
                        .buttonColors(
                            containerColor =
                                FightRed,
                            disabledContainerColor =
                                Charcoal
                        )
            ) {
                Text(
                    text = "+1 KICK",
                    fontSize = 28.sp,
                    fontWeight =
                        FontWeight.Black
                )
            }

            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )

            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        10.dp
                    )
            ) {
                OutlinedButton(
                    onClick = onUndo,
                    enabled =
                        repetitionCount > 0,
                    modifier =
                        Modifier.weight(1f),
                    shape =
                        RoundedCornerShape(
                            16.dp
                        )
                ) {
                    Text(
                        text = "Undo",
                        fontWeight =
                            FontWeight.Bold
                    )
                }

                OutlinedButton(
                    onClick = onQuickAdd,
                    enabled =
                        controlsEnabled,
                    modifier =
                        Modifier.weight(1f),
                    shape =
                        RoundedCornerShape(
                            16.dp
                        )
                ) {
                    Text(
                        text =
                            "+$manualQuickAddAmount",
                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )
        }

        Button(
            onClick = onPauseToggle,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape =
                RoundedCornerShape(16.dp),
            colors =
                ButtonDefaults
                    .buttonColors(
                        containerColor =
                            if (isPaused) {
                                FightRed
                            } else {
                                Charcoal
                            }
                    )
        ) {
            Text(
                text =
                    if (isPaused) {
                        "Resume session"
                    } else {
                        "Pause session"
                    },
                fontSize = 15.sp,
                fontWeight =
                    FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        TextButton(
            onClick = onEndAndSave,
            modifier =
                Modifier.fillMaxWidth()
        ) {
            Text(
                text =
                    if (
                        repetitionCount > 0
                    ) {
                        "End and save progress"
                    } else {
                        "Exit session"
                    },
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )
    }
}

@Composable
private fun KickModeSelectionCard(
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        color =
            if (selected) {
                FightRed.copy(
                    alpha = 0.16f
                )
            } else {
                Charcoal
            },
        shape =
            RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier =
                Modifier.padding(18.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .height(22.dp)
                    .fillMaxWidth(0.07f)
                    .background(
                        color =
                            if (selected) {
                                FightRed
                            } else {
                                Color.DarkGray
                            },
                        shape =
                            RoundedCornerShape(
                                20.dp
                            )
                    )
            )

            Spacer(
                modifier =
                    Modifier.fillMaxWidth(
                        0.04f
                    )
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 17.sp,
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
                    text = description,
                    fontSize = 13.sp,
                    lineHeight = 19.sp,
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
private fun KickPaceRow(
    pace: GuidedKickPace,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        color =
            if (selected) {
                FightRed.copy(
                    alpha = 0.14f
                )
            } else {
                Charcoal
            },
        shape =
            RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier =
                Modifier.padding(14.dp),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = pace.title,
                    fontSize = 15.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onBackground
                )

                Text(
                    text =
                        pace.description,
                    fontSize = 12.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }

            Text(
                text =
                    if (selected) {
                        "SELECTED"
                    } else {
                        "SELECT"
                    },
                fontSize = 11.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 1.sp,
                color =
                    if (selected) {
                        FightRed
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
private fun KickPhaseBadge(
    phase: KickSessionPhase
) {
    val text =
        when (phase) {
            KickSessionPhase.SETUP ->
                "READY"

            KickSessionPhase.GET_READY ->
                "GET READY"

            KickSessionPhase.KICKING ->
                "KICK"

            KickSessionPhase.RESTING ->
                "REST"

            KickSessionPhase.PAUSED ->
                "PAUSED"

            KickSessionPhase.COMPLETED ->
                "COMPLETED"
        }

    Surface(
        color =
            if (
                phase ==
                KickSessionPhase.PAUSED
            ) {
                FightRed.copy(
                    alpha = 0.18f
                )
            } else {
                Charcoal
            },
        shape =
            RoundedCornerShape(50.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 2.sp,
            color = FightRed,
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 8.dp
            )
        )
    }
}

@Composable
private fun KickStat(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 1.2.sp,
            color =
                MaterialTheme
                    .colorScheme
                    .onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color =
                MaterialTheme
                    .colorScheme
                    .onBackground
        )
    }
}

@Composable
private fun KickProgressBar(
    progress: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(
                color = Charcoal,
                shape =
                    RoundedCornerShape(10.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(
                    progress.coerceIn(
                        minimumValue = 0f,
                        maximumValue = 1f
                    )
                )
                .height(8.dp)
                .background(
                    color = FightRed,
                    shape =
                        RoundedCornerShape(
                            10.dp
                        )
                )
        )
    }
}

private suspend fun waitForKickActiveMilliseconds(
    durationMilliseconds: Long,
    isPaused: () -> Boolean,
    shouldStop: () -> Boolean
) {
    var activeMilliseconds = 0L

    while (
        activeMilliseconds <
        durationMilliseconds &&
        !shouldStop()
    ) {
        delay(100L)

        if (
            !isPaused() &&
            !shouldStop()
        ) {
            activeMilliseconds += 100L
        }
    }
}

private fun formatKickTime(
    totalSeconds: Int
): String {
    val safeSeconds =
        totalSeconds.coerceAtLeast(0)

    val minutes =
        safeSeconds / 60

    val seconds =
        safeSeconds % 60

    return "%d:%02d".format(
        minutes,
        seconds
    )
}