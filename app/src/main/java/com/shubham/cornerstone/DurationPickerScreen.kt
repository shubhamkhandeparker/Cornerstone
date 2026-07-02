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

@Composable
fun DurationPickerScreen(
    onStart: (
        secondsPerCombo: Int,
        comboCount: Int
    ) -> Unit,
    onExit: () -> Unit
) {
    val presets = listOf(
        "Tap to advance" to 0,
        "30 seconds" to 30,
        "60 seconds" to 60,
        "90 seconds" to 90,
        "3 minutes" to 180
    )

    var selected by remember { mutableIntStateOf(0) }
    var customText by remember { mutableStateOf("") }
    var customActive by remember { mutableStateOf(false) }
    var comboCount by remember { mutableIntStateOf(6) }

    val selectedSeconds = if (customActive && customText.isNotEmpty()) {
        customText.toIntOrNull() ?: 0
    } else {
        presets[selected].second
    }

    val totalSeconds = selectedSeconds * comboCount

    val totalMinutesText = when {
        selectedSeconds == 0 -> "Tap mode session"
        totalSeconds < 60 -> "$totalSeconds sec session"
        totalSeconds % 60 == 0 -> "${totalSeconds / 60} min session"
        else -> "${totalSeconds / 60} min ${totalSeconds % 60} sec session"
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = "← Back",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable { onExit() }
                    .padding(vertical = 8.dp)
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Build your\nsession",
                fontSize = 40.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(10.dp))

            Text(
                text = "Choose time per combo and how many combos you want today.",
                fontSize = 15.sp,
                lineHeight = 24.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(28.dp))

            Text(
                text = "Time per combo",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(12.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                presets.forEachIndexed { index, pair ->
                    DurationOption(
                        label = pair.first,
                        selected = selected == index && !customActive,
                        onClick = {
                            selected = index
                            customActive = false
                        }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = customText,
                onValueChange = {
                    customText = it.filter { ch -> ch.isDigit() }.take(3)
                    customActive = customText.isNotEmpty()
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

            Spacer(Modifier.height(22.dp))

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

            Spacer(Modifier.height(12.dp))

            Text(
                text = totalMinutesText,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    onStart(
                        selectedSeconds,
                        comboCount
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FightRed
                )
            ) {
                Text(
                    text = "Start session",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(32.dp))
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

            Spacer(Modifier.height(14.dp))

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

            Spacer(Modifier.height(8.dp))

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