package com.shubham.cornerstone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onFinished: () -> Unit
) {
    val sport by viewModel.sport.collectAsStateWithLifecycle()
    val level by viewModel.level.collectAsStateWithLifecycle()
    val dominance by viewModel.dominance.collectAsStateWithLifecycle()
    val stance by viewModel.stance.collectAsStateWithLifecycle()
    val scroll = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        // --- Ambient depth: subtle vertical gradient ---
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF161518), InkBlack)
                    )
                )
        )
        // --- Soft red glow, high and off-screen so only the bleed shows ---
        Box(
            modifier = Modifier
                .size(360.dp)
                .offset(x = 180.dp, y = (-200).dp)
                .blur(160.dp)
                .background(FightRed.copy(alpha = 0.16f), CircleShape)
        )

        // --- Layout: scrolling content on top, fixed button bar at bottom ---
        Column(modifier = Modifier.fillMaxSize()) {

            // SCROLLABLE region — fills all space above the button bar
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(scroll)
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(Modifier.height(64.dp))

                // Signature: red corner mark + wordmark
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        modifier = Modifier.size(34.dp),
                        color = FightRed,
                        shape = RoundedCornerShape(topStart = 11.dp, bottomEnd = 11.dp)
                    ) {}
                    Spacer(Modifier.width(12.dp))
                    Text(
                        text = "CORNERSTONE",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 3.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(Modifier.height(36.dp))

                // Hero
                Text(
                    text = "Set your\ncorner.",
                    fontSize = 46.sp,
                    lineHeight = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "A few taps. We learn the rest every time you train.",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(Modifier.height(40.dp))

                SectionLabel("01", "Your sport")
                Spacer(Modifier.height(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Boxing", "Muay Thai", "MMA").forEach { option ->
                        SelectableChip(
                            label = option,
                            selected = option == sport,
                            onClick = { viewModel.selectSport(option) }
                        )
                    }
                }

                Spacer(Modifier.height(30.dp))

                SectionLabel("02", "Your level")
                Spacer(Modifier.height(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Beginner", "Intermediate", "Advanced").forEach { option ->
                        SelectableChip(
                            label = option,
                            selected = option == level,
                            onClick = { viewModel.selectLevel(option) }
                        )
                    }
                }

                Spacer(Modifier.height(30.dp))

                SectionLabel("03", "Your style")
                Spacer(Modifier.height(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Striker", "Grappler", "All-rounder").forEach { option ->
                        SelectableChip(
                            label = option,
                            selected = option == dominance,
                            onClick = { viewModel.selectDominance(option) }
                        )
                    }
                }

                Spacer(Modifier.height(30.dp))

                SectionLabel("04", "Your stance")
                Spacer(Modifier.height(14.dp))
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Orthodox", "Southpaw").forEach { option ->
                        SelectableChip(
                            label = option,
                            selected = option == stance,
                            onClick = { viewModel.selectStance(option) }
                        )
                    }
                }

                // Breathing room so the last option never hides behind the button
                Spacer(Modifier.height(32.dp))
            }

            // FIXED BUTTON BAR — clears the system nav bar on every phone
            Surface(
                color = InkBlack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 16.dp,
                            bottom = 24.dp
                        )
                ) {
                    Button(
                        onClick = { viewModel.finish(onFinished) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = FightRed)
                    ) {
                        Text(
                            text = "Enter the gym",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionLabel(number: String, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(26.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = CircleShape
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                    text = number,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = FightRed
                )
            }
        }
        Spacer(Modifier.width(10.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}