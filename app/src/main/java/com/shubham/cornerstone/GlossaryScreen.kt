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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

// A single entry in the boxing glossary.
private data class PunchEntry(
    val symbol: String,
    val name: String,
    val description: String
)

private val boxingGlossary = listOf(
    PunchEntry("1", "Jab", "Lead-hand straight punch. Your range-finder and setup."),
    PunchEntry("2", "Cross", "Rear-hand straight punch. Your power shot down the middle."),
    PunchEntry("3", "Lead Hook", "Lead-hand punch that curves in from the side."),
    PunchEntry("4", "Rear Hook", "Rear-hand hook. Heavier, swings from your back side."),
    PunchEntry("5", "Lead Uppercut", "Lead-hand punch rising up into the chin."),
    PunchEntry("6", "Rear Uppercut", "Rear-hand uppercut. Big power from underneath."),
    PunchEntry("Slip", "Slip", "Small head movement to dodge a straight punch."),
    PunchEntry("Roll", "Roll", "Bend and rotate under a hook, like riding a wave.")
)

@Composable
fun GlossaryScreen(
    onExit: () -> Unit
) {
    val scroll = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF161518), InkBlack)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            // Top bar
            Text(
                text = "← Back",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .clickable { onExit() }
                    .padding(vertical = 8.dp)
            )

            Spacer(Modifier.height(20.dp))

            // Header
            Text(
                text = "The numbers.",
                fontSize = 38.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Every combo is built from these. Learn them once.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(28.dp))

            // Scrolling list
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scroll),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                boxingGlossary.forEach { entry ->
                    PunchRow(entry)
                }
                Spacer(Modifier.navigationBarsPadding())
                Spacer(Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun PunchRow(entry: PunchEntry) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Charcoal
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Number / symbol badge
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = FightRed.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = entry.symbol,
                        fontSize = if (entry.symbol.length > 2) 13.sp else 20.sp,
                        fontWeight = FontWeight.Black,
                        color = FightRed
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = entry.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = entry.description,
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}