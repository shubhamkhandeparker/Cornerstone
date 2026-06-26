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
import androidx.compose.foundation.layout.width
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
import java.time.LocalDate

@Composable
fun WeightSetupScreen(
    onSave: (targetKg: Float, fightInDays: Int, useKg: Boolean) -> Unit,
    onExit: () -> Unit
) {
    var useKg by remember { mutableStateOf(true) }
    var targetText by remember { mutableStateOf("") }
    var weeksText by remember { mutableStateOf("") }

    val scroll = rememberScrollState()
    val unitLabel = if (useKg) "kg" else "lbs"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF161518), InkBlack)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(56.dp))

            Text(
                text = "← Back",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable { onExit() }
                    .padding(vertical = 8.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Plan your\nweight cut.",
                fontSize = 40.sp,
                lineHeight = 44.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Set your fight-day target. We'll track your pace and adapt daily.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(32.dp))

            // Unit toggle
            Text("Units", fontWeight = FontWeight.Bold, fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                UnitChip("kg", useKg) { useKg = true }
                UnitChip("lbs", !useKg) { useKg = false }
            }

            Spacer(Modifier.height(28.dp))

            // Target weight
            Text("Fight-day target ($unitLabel)", fontWeight = FontWeight.Bold,
                fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = targetText,
                onValueChange = { targetText = it.filter { c -> c.isDigit() || c == '.' }.take(6) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. 70") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                shape = RoundedCornerShape(16.dp),
                colors = fieldColors()
            )

            Spacer(Modifier.height(24.dp))

            // Weeks until fight
            Text("Weeks until fight", fontWeight = FontWeight.Bold,
                fontSize = 14.sp, color = MaterialTheme.colorScheme.onBackground)
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = weeksText,
                onValueChange = { weeksText = it.filter { c -> c.isDigit() }.take(2) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("e.g. 4") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(16.dp),
                colors = fieldColors()
            )

            Spacer(Modifier.height(40.dp))

            Column(modifier = Modifier.navigationBarsPadding()) {
                val valid = targetText.toFloatOrNull() != null &&
                        (weeksText.toIntOrNull() ?: 0) > 0
                Button(
                    onClick = {
                        val targetInput = targetText.toFloat()
                        val targetKg = if (useKg) targetInput else targetInput / 2.20462f
                        val days = weeksText.toInt() * 7
                        onSave(targetKg, days, useKg)
                    },
                    enabled = valid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = FightRed,
                        disabledContainerColor = Charcoal
                    )
                ) {
                    Text("Start tracking", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun UnitChip(label: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = if (selected) FightRed.copy(alpha = 0.15f) else Charcoal,
        border = if (selected)
            androidx.compose.foundation.BorderStroke(2.dp, FightRed) else null
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 12.dp),
            fontWeight = FontWeight.Bold,
            color = if (selected) FightRed else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun fieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = Charcoal,
    unfocusedContainerColor = Charcoal,
    focusedIndicatorColor = FightRed,
    unfocusedIndicatorColor = Color.Transparent,
    cursorColor = FightRed
)