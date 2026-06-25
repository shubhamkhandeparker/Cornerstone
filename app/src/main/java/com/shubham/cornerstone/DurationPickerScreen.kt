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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun DurationPickerScreen(
    onStart: (secondsPerCombo: Int) -> Unit,
    onExit: () -> Unit
) {
    // Presets in seconds. -1 means "tap to advance" (no timer).
    val presets = listOf(
        "Tap to advance" to 0,
        "30 seconds" to 30,
        "60 seconds" to 60,
        "90 seconds" to 90,
        "3 minutes" to 180
    )

    var selected by remember { mutableIntStateOf(0) } // default: tap to advance
    var customText by remember { mutableStateOf("") }
    var customActive by remember { mutableStateOf(false) }

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
                text = "How long\nper combo?",
                fontSize = 40.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Drill each combo for a set time, then auto-advance with a 15s rest.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(32.dp))

            // Presets
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                presets.forEachIndexed { index, (label, _) ->
                    DurationOption(
                        label = label,
                        selected = selected == index && !customActive,
                        onClick = {
                            selected = index
                            customActive = false
                        }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Custom time
            OutlinedTextField(
                value = customText,
                onValueChange = {
                    customText = it.filter { ch -> ch.isDigit() }.take(3)
                    customActive = customText.isNotEmpty()
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Custom (seconds)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

            Spacer(Modifier.weight(1f))

            // Start
            Column(modifier = Modifier.navigationBarsPadding()) {
                Button(
                    onClick = {
                        val seconds = if (customActive && customText.isNotEmpty()) {
                            customText.toInt()
                        } else {
                            presets[selected].second
                        }
                        onStart(seconds)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FightRed)
                ) {
                    Text(
                        text = "Start session",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(24.dp))
            }
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
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = if (selected) FightRed.copy(alpha = 0.15f) else Charcoal,
        border = if (selected)
            androidx.compose.foundation.BorderStroke(2.dp, FightRed)
        else null
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 17.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
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