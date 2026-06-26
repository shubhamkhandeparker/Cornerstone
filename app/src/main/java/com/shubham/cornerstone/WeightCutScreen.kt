package com.shubham.cornerstone

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import java.time.LocalDate
import kotlin.math.abs

@Composable
fun WeightCutScreen(
    status: CutStatus?,
    entries: List<WeightEntry>,
    useKg: Boolean,
    onLogWeight: (weightKg: Float) -> Unit,
    onExit: () -> Unit
) {
    var weightText by remember { mutableStateOf("") }
    val scroll = rememberScrollState()
    val unit = if (useKg) "kg" else "lbs"

    fun toDisplay(kg: Float): Float = if (useKg) kg else kg * 2.20462f
    fun fmt(kg: Float): String = "%.1f".format(toDisplay(kg))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF161518), InkBlack)))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(scroll)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = "< Back",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable { onExit() }
                    .padding(vertical = 8.dp)
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Weight cut",
                fontSize = 38.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(24.dp))

            if (status != null) {
                StatusCard(status = status, fmt = ::fmt, unit = unit)
                Spacer(Modifier.height(18.dp))
                WeightTrendCard(
                    entries = entries,
                    targetKg = status.targetKg,
                    useKg = useKg
                )
            } else {
                Text(
                    text = "Log your first weigh-in to see your pace.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp
                )
            }

            Spacer(Modifier.height(28.dp))

            Text(
                text = "Today's weigh-in ($unit)",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = weightText,
                    onValueChange = {
                        weightText = it.filter { c -> c.isDigit() || c == '.' }.take(6)
                    },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("e.g. ${if (useKg) "72.5" else "160"}") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Charcoal,
                        unfocusedContainerColor = Charcoal,
                        focusedIndicatorColor = FightRed,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = FightRed
                    )
                )

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = {
                        val input = weightText.toFloatOrNull() ?: return@Button
                        val kg = if (useKg) input else input / 2.20462f
                        onLogWeight(kg)
                        weightText = ""
                    },
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = FightRed)
                ) {
                    Text("Log", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(28.dp))

            if (entries.isNotEmpty()) {
                Text(
                    text = "Recent weigh-ins",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(12.dp))

                entries.takeLast(7).reversed().forEach { entry ->
                    val date = LocalDate.ofEpochDay(entry.epochDay)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${date.month.name.take(3)} ${date.dayOfMonth}",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "${fmt(entry.weightKg)} $unit",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(Modifier.navigationBarsPadding())
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun StatusCard(
    status: CutStatus,
    fmt: (Float) -> String,
    unit: String
) {
    val label: String
    val color: Color

    when (status.pace) {
        Pace.ON_WEIGHT -> {
            label = "ON WEIGHT"
            color = Color(0xFF34C759)
        }
        Pace.ON_TRACK -> {
            label = "ON TRACK"
            color = Color(0xFF34C759)
        }
        Pace.AGGRESSIVE -> {
            label = "AGGRESSIVE"
            color = Color(0xFFFF9500)
        }
        Pace.BEHIND -> {
            label = "BEHIND"
            color = FightRed
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = Charcoal
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = color.copy(alpha = 0.15f)
            ) {
                Text(
                    text = label,
                    color = color,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = fmt(status.toLoseKg),
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "$unit to go",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                StatCell("Current", "${fmt(status.currentKg)} $unit", Modifier.weight(1f))
                StatCell("Target", "${fmt(status.targetKg)} $unit", Modifier.weight(1f))
                StatCell("Days left", "${status.daysLeft}", Modifier.weight(1f))
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = guidanceText(status, fmt, unit),
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun guidanceText(
    status: CutStatus,
    fmt: (Float) -> String,
    unit: String
): String {
    val weeklyPercent = status.weeklyPercent * 100f

    return when (status.pace) {
        Pace.ON_WEIGHT -> {
            "You are on weight. Keep training normal and avoid big swings this close to target."
        }
        Pace.ON_TRACK -> {
            "Target pace: ${fmt(status.perWeekNeededKg)} $unit/week (${weeklyPercent.formatOne()}% bodyweight/week). This is a sensible fight-camp pace."
        }
        Pace.AGGRESSIVE -> {
            "Target pace: ${fmt(status.perWeekNeededKg)} $unit/week (${weeklyPercent.formatOne()}% bodyweight/week). This is aggressive. Keep food quality tight and check recovery."
        }
        Pace.BEHIND -> {
            "Required pace is above a sensible weekly cut. Do not force a crash cut. Adjust the target or talk to your coach."
        }
    }
}

@Composable
private fun WeightTrendCard(
    entries: List<WeightEntry>,
    targetKg: Float,
    useKg: Boolean
) {
    val recent = entries.takeLast(7)
    val unit = if (useKg) "kg" else "lbs"

    fun toDisplay(kg: Float): Float = if (useKg) kg else kg * 2.20462f
    fun fmt(kg: Float): String = "%.1f".format(toDisplay(kg))

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Charcoal
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "Trend",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Last ${recent.size.coerceAtLeast(1)} weigh-ins",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "Target ${fmt(targetKg)} $unit",
                    fontSize = 12.sp,
                    color = FightRed,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(18.dp))

            if (recent.isEmpty()) {
                Text(
                    text = "Log weight to start your curve.",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                TrendChart(
                    entries = recent,
                    targetKg = targetKg,
                    useKg = useKg
                )
            }
        }
    }
}

@Composable
private fun TrendChart(
    entries: List<WeightEntry>,
    targetKg: Float,
    useKg: Boolean
) {
    fun toDisplay(kg: Float): Float = if (useKg) kg else kg * 2.20462f

    val values = entries.map { toDisplay(it.weightKg) }
    val target = toDisplay(targetKg)

    val minValue = (values + target).minOrNull() ?: target
    val maxValue = (values + target).maxOrNull() ?: target
    val range = (maxValue - minValue).let { if (abs(it) < 0.1f) 1f else it }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val width = size.width
        val height = size.height
        val leftPad = 6.dp.toPx()
        val rightPad = 6.dp.toPx()
        val topPad = 12.dp.toPx()
        val bottomPad = 18.dp.toPx()
        val chartWidth = width - leftPad - rightPad
        val chartHeight = height - topPad - bottomPad

        fun yFor(value: Float): Float {
            val normalized = (value - minValue) / range
            return topPad + chartHeight - (normalized * chartHeight)
        }

        val targetY = yFor(target)

        drawLine(
            color = FightRed.copy(alpha = 0.45f),
            start = Offset(leftPad, targetY),
            end = Offset(width - rightPad, targetY),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )

        if (entries.size == 1) {
            val x = leftPad + chartWidth / 2f
            val y = yFor(values.first())
            drawCircle(
                color = FightRed,
                radius = 5.dp.toPx(),
                center = Offset(x, y)
            )
            return@Canvas
        }

        val path = Path()

        values.forEachIndexed { index, value ->
            val x = leftPad + (chartWidth * index / (values.lastIndex.coerceAtLeast(1)).toFloat())
            val y = yFor(value)

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }

            drawCircle(
                color = FightRed,
                radius = 4.dp.toPx(),
                center = Offset(x, y)
            )
        }

        drawPath(
            path = path,
            color = FightRed,
            style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}

@Composable
private fun StatCell(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

private fun Float.formatOne(): String {
    return "%.1f".format(this)
}