package com.shubham.cornerstone

import android.media.AudioManager
import android.media.ToneGenerator
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
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlinx.coroutines.delay

private const val DEFAULT_REST_SECONDS = 15

@Composable
fun SessionScreen(
    combos: List<Combo>,
    secondsPerCombo: Int,
    restSeconds: Int = DEFAULT_REST_SECONDS,
    onFinishSession: () -> Unit,
    onExit: () -> Unit
) {
    if (combos.isEmpty()) {
        EmptySessionScreen(onExit = onExit)
        return
    }

    var index by remember { mutableIntStateOf(0) }
    var repeat by remember { mutableStateOf(false) }
    var resting by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }

    val isLast = index == combos.lastIndex
    val current = combos[index]
    val timerOn = secondsPerCombo > 0
    val safeRestSeconds = restSeconds.coerceAtLeast(0)

    val toneGen = remember {
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

    fun advance() {
        isPaused = false

        if (isLast) {
            onFinishSession()
        } else {
            index++
        }
    }

    fun ringBell() {
        toneGen.startTone(
            ToneGenerator.TONE_PROP_ACK,
            200
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
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Exit",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .clickable {
                            onExit()
                        }
                        .padding(8.dp)
                )

                Text(
                    text = "${index + 1} / ${combos.size}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            ProgressBar(
                progress = (index + 1).toFloat() / combos.size
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (resting) {
                    RestView(
                        seconds = safeRestSeconds,
                        isPaused = isPaused,
                        onBeep = {
                            toneGen.startTone(
                                ToneGenerator.TONE_PROP_BEEP,
                                150
                            )
                        },
                        onDone = {
                            resting = false
                            advance()
                        }
                    )
                } else {
                    AnimatedContent(
                        targetState = current,
                        transitionSpec = {
                            fadeIn(
                                animationSpec = tween(250)
                            ) togetherWith fadeOut(
                                animationSpec = tween(150)
                            )
                        },
                        label = "combo"
                    ) { combo ->
                        ComboView(
                            combo = combo,
                            timerOn = timerOn,
                            seconds = secondsPerCombo,
                            repeat = repeat,
                            isPaused = isPaused,
                            onBeep = {
                                toneGen.startTone(
                                    ToneGenerator.TONE_PROP_BEEP,
                                    150
                                )
                            },
                            onRoundStartBell = {
                                ringBell()
                            },
                            onTimeUp = {
                                if (isLast) {
                                    onFinishSession()
                                } else {
                                    resting = true
                                }
                            }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.navigationBarsPadding()
            ) {
                if (timerOn && !resting && !isPaused) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                repeat = !repeat
                            }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (repeat) {
                                "🔁 Repeat: ON"
                            } else {
                                "🔁 Repeat: OFF"
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (repeat) {
                                FightRed
                            } else {
                                MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (timerOn) {
                    Button(
                        onClick = {
                            isPaused = !isPaused
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPaused) {
                                FightRed
                            } else {
                                Charcoal
                            }
                        )
                    ) {
                        Text(
                            text = if (isPaused) {
                                "Resume session"
                            } else {
                                "Pause session"
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }

                Button(
                    onClick = {
                        isPaused = false

                        if (resting) {
                            resting = false
                            advance()
                        } else {
                            advance()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FightRed
                    )
                ) {
                    Text(
                        text = if (isLast && !resting) {
                            "Finish session"
                        } else if (resting) {
                            "Skip rest  →"
                        } else {
                            "Skip  →"
                        },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}

@Composable
private fun ComboView(
    combo: Combo,
    timerOn: Boolean,
    seconds: Int,
    repeat: Boolean,
    isPaused: Boolean,
    onBeep: () -> Unit,
    onRoundStartBell: () -> Unit,
    onTimeUp: () -> Unit
) {
    var getReady by remember(combo, timerOn) {
        mutableIntStateOf(
            if (timerOn) 3 else 0
        )
    }

    val currentPaused by rememberUpdatedState(isPaused)
    val currentOnBeep by rememberUpdatedState(onBeep)
    val currentRoundStartBell by rememberUpdatedState(onRoundStartBell)

    LaunchedEffect(combo, timerOn) {
        if (timerOn) {
            getReady = 3

            while (getReady > 0) {
                while (currentPaused) {
                    delay(100)
                }

                currentOnBeep()

                waitForActiveSecond {
                    currentPaused
                }

                getReady--
            }

            while (currentPaused) {
                delay(100)
            }

            currentRoundStartBell()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = if (isPaused) {
                FightRed.copy(alpha = 0.18f)
            } else {
                Charcoal
            }
        ) {
            Text(
                text = when {
                    isPaused -> "PAUSED"
                    timerOn && getReady > 0 -> "GET READY"
                    else -> combo.phase
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = FightRed,
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 7.dp
                )
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        if (timerOn && getReady > 0) {
            Text(
                text = "$getReady",
                fontSize = 72.sp,
                fontWeight = FontWeight.Black,
                color = FightRed
            )

            Spacer(modifier = Modifier.height(28.dp))
        } else if (timerOn) {
            CountdownText(
                totalSeconds = seconds,
                repeat = repeat,
                isPaused = isPaused,
                onBeep = onBeep,
                onTimeUp = onTimeUp
            )

            Spacer(modifier = Modifier.height(28.dp))
        }

        Text(
            text = combo.moves,
            fontSize = 44.sp,
            lineHeight = 50.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = combo.cue,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (isPaused) {
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Timer paused",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = FightRed
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
    onTimeUp: () -> Unit
) {
    var cycle by remember {
        mutableIntStateOf(0)
    }

    var remaining by remember(cycle, totalSeconds) {
        mutableIntStateOf(totalSeconds)
    }

    val currentRepeat by rememberUpdatedState(repeat)
    val currentPaused by rememberUpdatedState(isPaused)
    val currentOnBeep by rememberUpdatedState(onBeep)
    val currentOnTimeUp by rememberUpdatedState(onTimeUp)

    LaunchedEffect(cycle, totalSeconds) {
        remaining = totalSeconds

        while (remaining > 0) {
            waitForActiveSecond {
                currentPaused
            }

            remaining--

            if (remaining in 1..3) {
                currentOnBeep()
            }
        }

        while (currentPaused) {
            delay(100)
        }

        currentOnBeep()

        if (currentRepeat) {
            cycle++
        } else {
            currentOnTimeUp()
        }
    }

    val minutes = remaining / 60
    val seconds = remaining % 60

    Text(
        text = "%d:%02d".format(
            minutes,
            seconds
        ),
        fontSize = 56.sp,
        fontWeight = FontWeight.Black,
        color = FightRed
    )
}

@Composable
private fun RestView(
    seconds: Int,
    isPaused: Boolean,
    onBeep: () -> Unit,
    onDone: () -> Unit
) {
    var remaining by remember(seconds) {
        mutableIntStateOf(seconds)
    }

    val currentPaused by rememberUpdatedState(isPaused)
    val currentOnBeep by rememberUpdatedState(onBeep)
    val currentOnDone by rememberUpdatedState(onDone)

    LaunchedEffect(seconds) {
        remaining = seconds

        while (remaining > 0) {
            waitForActiveSecond {
                currentPaused
            }

            remaining--

            if (remaining in 1..3) {
                currentOnBeep()
            }
        }

        while (currentPaused) {
            delay(100)
        }

        currentOnBeep()
        currentOnDone()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (isPaused) {
                "REST PAUSED"
            } else {
                "REST"
            },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            color = if (isPaused) {
                FightRed
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "$remaining",
            fontSize = 72.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = if (isPaused) {
                "Rest timer is paused."
            } else {
                "Breathe. Next combo coming up."
            },
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProgressBar(
    progress: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(
                Charcoal,
                RoundedCornerShape(3.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(
                    progress.coerceIn(
                        0f,
                        1f
                    )
                )
                .height(6.dp)
                .background(
                    FightRed,
                    RoundedCornerShape(3.dp)
                )
        )
    }
}

@Composable
private fun EmptySessionScreen(
    onExit: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(InkBlack)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No combos available",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(
                onClick = onExit,
                colors = ButtonDefaults.buttonColors(
                    containerColor = FightRed
                )
            ) {
                Text(
                    text = "Return home",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

private suspend fun waitForActiveSecond(
    isPaused: () -> Boolean
) {
    var activeMilliseconds = 0L

    while (activeMilliseconds < 1_000L) {
        delay(100L)

        if (!isPaused()) {
            activeMilliseconds += 100L
        }
    }
}