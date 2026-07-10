package com.shubham.cornerstone

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

private const val FREE_REST_SECONDS = 15

@Composable
fun DurationPickerScreen(
    onStart: (
        secondsPerCombo: Int,
        comboCount: Int
    ) -> Unit,
    onExit: () -> Unit,
    isPro: Boolean = false,
    onUnlockPro: () -> Unit = {},
    onStartWithRest: ((
        secondsPerCombo: Int,
        comboCount: Int,
        restSeconds: Int
    ) -> Unit)? = null
) {
    val durationPresets = listOf(
        "Tap to advance" to 0,
        "30 seconds" to 30,
        "60 seconds" to 60,
        "90 seconds" to 90,
        "3 minutes" to 180
    )

    val restPresets = listOf(
        15,
        30,
        45,
        60,
        90
    )

    var selectedDurationIndex by remember {
        mutableIntStateOf(0)
    }

    var customDurationText by remember {
        mutableStateOf("")
    }

    var customDurationActive by remember {
        mutableStateOf(false)
    }

    var comboCount by remember {
        mutableIntStateOf(6)
    }

    var selectedRestSeconds by remember {
        mutableIntStateOf(FREE_REST_SECONDS)
    }

    var customRestActive by remember {
        mutableStateOf(false)
    }

    var customRestText by remember {
        mutableStateOf("")
    }

    val selectedSeconds = if (
        customDurationActive &&
        customDurationText.isNotEmpty()
    ) {
        customDurationText.toIntOrNull() ?: 0
    } else {
        durationPresets[selectedDurationIndex].second
    }

    val customRestValue = customRestText
        .toIntOrNull()
        ?.coerceIn(1, 999)

    val effectiveRestSeconds = when {
        !isPro -> FREE_REST_SECONDS
        customRestActive -> customRestValue ?: FREE_REST_SECONDS
        else -> selectedRestSeconds
    }

    val customRestValid = !customRestActive ||
            customRestValue != null

    val activeRoundSeconds = selectedSeconds * comboCount

    val totalRestSeconds = if (
        selectedSeconds > 0 &&
        comboCount > 1
    ) {
        effectiveRestSeconds * (comboCount - 1)
    } else {
        0
    }

    val estimatedTotalSeconds =
        activeRoundSeconds + totalRestSeconds

    val totalMinutesText = when {
        selectedSeconds == 0 -> {
            "Tap mode session"
        }

        estimatedTotalSeconds < 60 -> {
            "$estimatedTotalSeconds sec including rests"
        }

        estimatedTotalSeconds % 60 == 0 -> {
            "${estimatedTotalSeconds / 60} min including rests"
        }

        else -> {
            "${estimatedTotalSeconds / 60} min " +
                    "${estimatedTotalSeconds % 60} sec including rests"
        }
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
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(horizontal = 24.dp)
        ) {
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "← Back",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable {
                        onExit()
                    }
                    .padding(vertical = 8.dp)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Build your\nsession",
                fontSize = 40.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Choose time per combo, rest between rounds, and how many combos you want today.",
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Text(
                text = "Time per combo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                durationPresets.forEachIndexed { index, pair ->
                    DurationOption(
                        label = pair.first,
                        selected = selectedDurationIndex == index &&
                                !customDurationActive,
                        onClick = {
                            selectedDurationIndex = index
                            customDurationActive = false
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = customDurationText,
                onValueChange = { newValue ->
                    customDurationText = newValue
                        .filter { character ->
                            character.isDigit()
                        }
                        .take(3)

                    customDurationActive =
                        customDurationText.isNotEmpty()
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Custom seconds")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Charcoal,
                    unfocusedContainerColor = Charcoal,
                    focusedIndicatorColor = FightRed,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedLabelColor = FightRed,
                    cursorColor = FightRed
                )
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Rest between rounds",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(
                        modifier = Modifier.height(3.dp)
                    )

                    Text(
                        text = if (isPro) {
                            "Choose how long you recover before the next combo."
                        } else {
                            "Free plan includes a fixed 15-second rest."
                        },
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                if (isPro) {
                    Surface(
                        shape = RoundedCornerShape(999.dp),
                        color = FightRed.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "PRO",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            color = FightRed,
                            modifier = Modifier.padding(
                                horizontal = 11.dp,
                                vertical = 6.dp
                            )
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                restPresets
                    .take(3)
                    .forEach { seconds ->
                        RestOption(
                            label = "${seconds}s",
                            selected = !customRestActive &&
                                    effectiveRestSeconds == seconds,
                            locked = !isPro &&
                                    seconds != FREE_REST_SECONDS,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                if (
                                    !isPro &&
                                    seconds != FREE_REST_SECONDS
                                ) {
                                    onUnlockPro()
                                } else {
                                    customRestActive = false
                                    selectedRestSeconds = seconds
                                }
                            }
                        )
                    }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                restPresets
                    .drop(3)
                    .forEach { seconds ->
                        RestOption(
                            label = "${seconds}s",
                            selected = !customRestActive &&
                                    effectiveRestSeconds == seconds,
                            locked = !isPro,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                if (!isPro) {
                                    onUnlockPro()
                                } else {
                                    customRestActive = false
                                    selectedRestSeconds = seconds
                                }
                            }
                        )
                    }

                RestOption(
                    label = "Custom",
                    selected = customRestActive,
                    locked = !isPro,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (!isPro) {
                            onUnlockPro()
                        } else {
                            customRestActive = true
                        }
                    }
                )
            }

            if (isPro && customRestActive) {
                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = customRestText,
                    onValueChange = { newValue ->
                        customRestText = newValue
                            .filter { character ->
                                character.isDigit()
                            }
                            .take(3)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Custom rest seconds")
                    },
                    supportingText = {
                        Text(
                            text = if (
                                customRestText.isNotEmpty() &&
                                customRestValue == null
                            ) {
                                "Enter at least 1 second."
                            } else {
                                "Choose between 1 and 999 seconds."
                            }
                        )
                    },
                    isError = customRestText.isNotEmpty() &&
                            customRestValue == null,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Charcoal,
                        unfocusedContainerColor = Charcoal,
                        focusedIndicatorColor = FightRed,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = FightRed,
                        cursorColor = FightRed
                    )
                )
            }

            if (!isPro) {
                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onUnlockPro()
                        },
                    shape = RoundedCornerShape(16.dp),
                    color = FightRed.copy(alpha = 0.10f),
                    border = BorderStroke(
                        width = 1.dp,
                        color = FightRed.copy(alpha = 0.35f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Custom rest is a Pro feature",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Spacer(
                                modifier = Modifier.height(3.dp)
                            )

                            Text(
                                text = "Unlock custom rest together with Weight Cut and progress tools.",
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Text(
                            text = "UNLOCK",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = FightRed
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            ComboCountCard(
                comboCount = comboCount,
                onDecrease = {
                    if (comboCount > 1) {
                        comboCount -= 1
                    }
                },
                onIncrease = {
                    if (comboCount < 50) {
                        comboCount += 1
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = totalMinutesText,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (selectedSeconds > 0) {
                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "${effectiveRestSeconds}s rest between rounds",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = FightRed
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {
                    val startWithRest = onStartWithRest

                    if (startWithRest != null) {
                        startWithRest(
                            selectedSeconds,
                            comboCount,
                            effectiveRestSeconds
                        )
                    } else {
                        onStart(
                            selectedSeconds,
                            comboCount
                        )
                    }
                },
                enabled = customRestValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FightRed,
                    disabledContainerColor = Charcoal,
                    disabledContentColor = Color(0xFF777777)
                )
            ) {
                Text(
                    text = "Start session",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )
        }
    }
}

@Composable
private fun DurationOption(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        color = if (selected) {
            FightRed.copy(alpha = 0.15f)
        } else {
            Charcoal
        },
        border = if (selected) {
            BorderStroke(
                width = 2.dp,
                color = FightRed
            )
        } else {
            null
        }
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = 20.dp,
                vertical = 18.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 17.sp,
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Medium
                },
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            if (selected) {
                Text(
                    text = "✓",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = FightRed
                )
            }
        }
    }
}

@Composable
private fun RestOption(
    label: String,
    selected: Boolean,
    locked: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(58.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(15.dp),
        color = when {
            selected -> FightRed.copy(alpha = 0.16f)
            else -> Charcoal
        },
        border = when {
            selected -> BorderStroke(
                width = 2.dp,
                color = FightRed
            )

            locked -> BorderStroke(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.08f)
            )

            else -> null
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = label,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (locked) {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )

                if (locked) {
                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )

                    Text(
                        text = "PRO",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp,
                        color = FightRed
                    )
                }
            }
        }
    }
}

@Composable
private fun ComboCountCard(
    comboCount: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Charcoal
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = "Combos this session",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CountButton(
                    text = "−",
                    enabled = comboCount > 1,
                    onClick = onDecrease
                )

                Text(
                    text = comboCount.toString(),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                CountButton(
                    text = "+",
                    enabled = comboCount < 50,
                    onClick = onIncrease
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Minimum 1, maximum 50 combos.",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CountButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .height(48.dp)
            .clickable(
                enabled = enabled
            ) {
                onClick()
            },
        shape = RoundedCornerShape(14.dp),
        color = if (enabled) {
            FightRed
        } else {
            Color(0xFF2B2B2E)
        }
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = 22.dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 26.sp,
                fontWeight = FontWeight.Black,
                color = if (enabled) {
                    Color.White
                } else {
                    Color(0xFF8A8A8A)
                }
            )
        }
    }
}