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

private const val REST_SECONDS = 15

@Composable
fun SessionScreen(
    combos: List<Combo>,
    secondsPerCombo: Int,   // 0 = tap-to-advance (no timer)
    onFinishSession: () -> Unit,
    onExit: () -> Unit
) {
    var index by remember { mutableIntStateOf(0) }
    var repeat by remember { mutableStateOf(false) }
    var resting by remember { mutableStateOf(false) }

    val isLast = index == combos.lastIndex
    val current = combos[index]
    val timerOn = secondsPerCombo > 0

    // Beep helper.
    val toneGen = remember { ToneGenerator(AudioManager.STREAM_MUSIC, 100) }
    DisposableEffect(Unit) {
        onDispose { toneGen.release() }
    }

    fun advance() {
        if (isLast) {
            onFinishSession()
        } else {
            index++
        }
    }

    // Round-start signal: a sharp acknowledge tone, like a boxing bell.
    fun ringBell() {
        toneGen.startTone(ToneGenerator.TONE_PROP_ACK, 200)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(Color(0xFF161518), InkBlack))
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            // Top: exit + progress count
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
                        .clickable { onExit() }
                        .padding(8.dp)
                )
                Text(
                    text = "${index + 1} / ${combos.size}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(Modifier.height(12.dp))

            ProgressBar(progress = (index + 1).toFloat() / combos.size)

            // Center content
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (resting) {
                    RestView(
                        seconds = REST_SECONDS,
                        onBeep = { toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 150) },
                        onDone = {
                            resting = false
                            advance()
                        }
                    )
                } else {
                    AnimatedContent(
                        targetState = current,
                        transitionSpec = {
                            fadeIn(tween(250)) togetherWith fadeOut(tween(150))
                        },
                        label = "combo"
                    ) { combo ->
                        ComboView(
                            combo = combo,
                            timerOn = timerOn,
                            seconds = secondsPerCombo,
                            repeat = repeat,
                            onBeep = { toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 150) },
                            onRoundStartBell = { ringBell() },
                            onTimeUp = {
                                if (repeat) {
                                    // loop: do nothing, the timer restarts via key change
                                } else if (isLast) {
                                    onFinishSession()
                                } else {
                                    resting = true
                                }
                            }
                        )
                    }
                }
            }

            // Bottom controls
            Column(modifier = Modifier.navigationBarsPadding()) {
                if (timerOn && !resting) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { repeat = !repeat }
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (repeat) "🔁 Repeat: ON" else "🔁 Repeat: OFF",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (repeat) FightRed else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                }

                Button(
                    onClick = {
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
                    colors = ButtonDefaults.buttonColors(containerColor = FightRed)
                ) {
                    Text(
                        text = if (isLast && !resting) "Finish session" else "Skip  →",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(28.dp))
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
    onBeep: () -> Unit,
    onRoundStartBell: () -> Unit,
    onTimeUp: () -> Unit
) {
    // Get-ready phase before the round timer begins.
    var getReady by remember(combo) { mutableIntStateOf(if (timerOn) 3 else 0) }

    LaunchedEffect(combo, timerOn) {
        if (timerOn) {
            getReady = 3
            while (getReady > 0) {
                onBeep()
                delay(1000)
                getReady--
            }
            onRoundStartBell()   // round begins
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Phase tag (shows GET READY during the count-in)
        Surface(shape = RoundedCornerShape(20.dp), color = Charcoal) {
            Text(
                text = if (timerOn && getReady > 0) "GET READY" else combo.phase,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp,
                color = FightRed,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 7.dp)
            )
        }

        Spacer(Modifier.height(28.dp))

        if (timerOn && getReady > 0) {
            // Big get-ready countdown
            Text(
                text = "$getReady",
                fontSize = 72.sp,
                fontWeight = FontWeight.Black,
                color = FightRed
            )
            Spacer(Modifier.height(28.dp))
        } else if (timerOn) {
            CountdownText(
                totalSeconds = seconds,
                repeat = repeat,
                onBeep = onBeep,
                onTimeUp = onTimeUp
            )
            Spacer(Modifier.height(28.dp))
        }

        // The combo
        Text(
            text = combo.moves,
            fontSize = 44.sp,
            lineHeight = 50.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = combo.cue,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun CountdownText(
    totalSeconds: Int,
    repeat: Boolean,
    onBeep: () -> Unit,
    onTimeUp: () -> Unit
) {
    var cycle by remember { mutableIntStateOf(0) }
    var remaining by remember(cycle) { mutableIntStateOf(totalSeconds) }

    LaunchedEffect(cycle) {
        remaining = totalSeconds
        while (remaining > 0) {
            delay(1000)
            remaining--
            if (remaining in 1..3) onBeep()  // count-out beeps
        }
        onBeep()
        if (repeat) {
            cycle++          // loop again
        } else {
            onTimeUp()
        }
    }

    val mins = remaining / 60
    val secs = remaining % 60
    Text(
        text = "%d:%02d".format(mins, secs),
        fontSize = 56.sp,
        fontWeight = FontWeight.Black,
        color = FightRed
    )
}

@Composable
private fun RestView(
    seconds: Int,
    onBeep: () -> Unit,
    onDone: () -> Unit
) {
    var remaining by remember { mutableIntStateOf(seconds) }

    LaunchedEffect(Unit) {
        remaining = seconds
        while (remaining > 0) {
            delay(1000)
            remaining--
            if (remaining in 1..3) onBeep()
        }
        onBeep()
        onDone()
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "REST",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "$remaining",
            fontSize = 72.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Breathe. Next combo coming up.",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .background(Charcoal, RoundedCornerShape(3.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .height(6.dp)
                .background(FightRed, RoundedCornerShape(3.dp))
        )
    }
}