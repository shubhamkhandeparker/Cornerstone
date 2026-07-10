package com.shubham.cornerstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

private val DetailBlack = Color(0xFF101010)
private val DetailCardBlack = Color(0xFF191919)
private val DetailRed = Color(0xFFE63946)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechniqueDetailScreen(
    techniqueId: String,
    onBack: () -> Unit
) {
    val lesson = TechniqueLibrary.find(techniqueId)

    if (lesson == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DetailBlack)
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Technique not found.",
                color = Color.White
            )
        }
        return
    }

    Scaffold(
        containerColor = DetailBlack,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = lesson.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    Button(
                        onClick = onBack,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = "Back",
                            color = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DetailBlack
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DetailBlack)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
                .navigationBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = lesson.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = lesson.sport,
                style = MaterialTheme.typography.labelLarge,
                color = DetailRed,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = lesson.shortDescription,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.82f)
            )

            Spacer(modifier = Modifier.height(18.dp))

            SectionCard(title = "Purpose") {
                Text(
                    text = lesson.purpose,
                    color = Color.White.copy(alpha = 0.78f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Visual guide",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            lesson.imagePanels.forEachIndexed { index, panel ->
                TechniquePanelCard(
                    techniqueId = lesson.id,
                    panelNumber = index + 1,
                    panel = panel
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(4.dp))

            SectionCard(title = "Steps") {
                lesson.steps.forEachIndexed { index, step ->
                    NumberedLine(
                        number = index + 1,
                        text = step
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            SectionCard(title = "Key cues") {
                lesson.cues.forEach { cue ->
                    BulletLine(text = cue)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            SectionCard(title = "Common mistakes") {
                lesson.commonMistakes.forEach { mistake ->
                    BulletLine(text = mistake)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun TechniquePanelCard(
    techniqueId: String,
    panelNumber: Int,
    panel: TechniqueImagePanel
) {
    val imageResId = techniqueImageResId(
        techniqueId = techniqueId,
        panelTitle = panel.title
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = DetailCardBlack
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(999.dp),
                    color = DetailRed.copy(alpha = 0.16f)
                ) {
                    Text(
                        text = "VIEW $panelNumber",
                        color = DetailRed,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 5.dp
                        )
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = panel.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = panel.subtitle,
                color = Color.White.copy(alpha = 0.68f),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (imageResId != null) {
                TechniqueImage(
                    imageResId = imageResId,
                    contentDescription = "$techniqueId ${panel.title} technique diagram"
                )
            } else {
                TechniqueImageSlot(
                    techniqueId = techniqueId,
                    panelTitle = panel.title
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            panel.coachingLines.forEach { coachingLine ->
                BulletLine(text = coachingLine)
            }
        }
    }
}

@Composable
private fun TechniqueImage(
    imageResId: Int,
    contentDescription: String
) {
    val painter = painterResource(id = imageResId)
    val intrinsicSize = painter.intrinsicSize

    val imageAspectRatio = if (
        intrinsicSize.width.isFinite() &&
        intrinsicSize.height.isFinite() &&
        intrinsicSize.width > 0f &&
        intrinsicSize.height > 0f
    ) {
        intrinsicSize.width / intrinsicSize.height
    } else {
        1.2f
    }

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(imageAspectRatio)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White),
        contentScale = ContentScale.Fit
    )
}

@Composable
private fun TechniqueImageSlot(
    techniqueId: String,
    panelTitle: String
) {
    val assetName = buildTechniqueAssetName(
        techniqueId = techniqueId,
        panelTitle = panelTitle
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212),
                        Color(0xFF080808)
                    )
                ),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                shape = RoundedCornerShape(999.dp),
                color = DetailRed.copy(alpha = 0.14f)
            ) {
                Text(
                    text = "IMAGE SLOT",
                    color = DetailRed,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(
                        horizontal = 12.dp,
                        vertical = 6.dp
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = panelTitle,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Asset: $assetName",
                color = Color.White.copy(alpha = 0.52f),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = DetailCardBlack
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Divider(
                color = Color.White.copy(alpha = 0.08f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            content()
        }
    }
}

@Composable
private fun NumberedLine(
    number: Int,
    text: String
) {
    Row(
        modifier = Modifier.padding(vertical = 5.dp)
    ) {
        Text(
            text = "$number.",
            color = DetailRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = Color.White.copy(alpha = 0.78f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun BulletLine(
    text: String
) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = "•",
            color = DetailRed,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            color = Color.White.copy(alpha = 0.76f),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

private fun techniqueImageResId(
    techniqueId: String,
    panelTitle: String
): Int? {
    return when {
        // Jab
        techniqueId == "jab" && panelTitle == "Front view" ->
            R.drawable.jab_front_view

        techniqueId == "jab" && panelTitle == "Side view" ->
            R.drawable.jab_side_view

        techniqueId == "jab" && panelTitle == "Foot position" ->
            R.drawable.jab_foot_position

        techniqueId == "jab" && panelTitle == "End position" ->
            R.drawable.jab_end_position

        // Cross
        techniqueId == "cross" && panelTitle == "Start position" ->
            R.drawable.cross_front_view

        techniqueId == "cross" && panelTitle == "Hip rotation" ->
            R.drawable.cross_foot_position

        techniqueId == "cross" && panelTitle == "Punch line" ->
            R.drawable.cross_side_view

        techniqueId == "cross" && panelTitle == "Recovery" ->
            R.drawable.cross_end_position

        // Lead Hook
        techniqueId == "lead_hook" && panelTitle == "Guard position" ->
            R.drawable.lead_hook_front_view

        techniqueId == "lead_hook" && panelTitle == "Elbow line" ->
            R.drawable.lead_hook_side_view

        techniqueId == "lead_hook" && panelTitle == "Hip turn" ->
            R.drawable.lead_hook_hip_rotation

        techniqueId == "lead_hook" && panelTitle == "Finish" ->
            R.drawable.lead_hook_end_position

        // Rear Hook
        techniqueId == "rear_hook" && panelTitle == "Guard position" ->
            R.drawable.rear_hook_guard_position

        techniqueId == "rear_hook" && panelTitle == "Rear-side rotation" ->
            R.drawable.rear_hook_rear_side_rotation

        techniqueId == "rear_hook" && panelTitle == "Hook line" ->
            R.drawable.rear_hook_hook_line

        techniqueId == "rear_hook" && panelTitle == "Return to guard" ->
            R.drawable.rear_hook_return_to_guard

        // Lead Uppercut
        techniqueId == "lead_uppercut" && panelTitle == "Guard position" ->
            R.drawable.lead_uppercut_guard_position

        techniqueId == "lead_uppercut" && panelTitle == "Level change" ->
            R.drawable.lead_uppercut_level_change

        techniqueId == "lead_uppercut" && panelTitle == "Punch path" ->
            R.drawable.lead_uppercut_punch_path

        techniqueId == "lead_uppercut" && panelTitle == "Return to guard" ->
            R.drawable.lead_uppercut_return_to_guard

        // Rear Uppercut
        techniqueId == "rear_uppercut" && panelTitle == "Guard position" ->
            R.drawable.rear_uppercut_guard_position

        techniqueId == "rear_uppercut" && panelTitle == "Rear-side load" ->
            R.drawable.rear_uppercut_rear_side_load

        techniqueId == "rear_uppercut" && panelTitle == "Upward drive" ->
            R.drawable.rear_uppercut_upward_drive

        techniqueId == "rear_uppercut" && panelTitle == "Recovery" ->
            R.drawable.rear_uppercut_recovery

        // Slip
        techniqueId == "slip" && panelTitle == "Starting guard" ->
            R.drawable.slip_starting_guard

        techniqueId == "slip" && panelTitle == "Outside slip" ->
            R.drawable.slip_outside_slip

        techniqueId == "slip" && panelTitle == "Inside slip" ->
            R.drawable.slip_inside_slip

        techniqueId == "slip" && panelTitle == "Counter position" ->
            R.drawable.slip_counter_position

        // Roll
        techniqueId == "roll" && panelTitle == "Starting guard" ->
            R.drawable.roll_starting_guard

        techniqueId == "roll" && panelTitle == "Level change" ->
            R.drawable.roll_level_change

        techniqueId == "roll" && panelTitle == "U-shaped movement" ->
            R.drawable.roll_u_shaped_movement

        techniqueId == "roll" && panelTitle == "Exit position" ->
            R.drawable.roll_exit_position

        // Teep
        techniqueId == "teep" && panelTitle == "Starting stance" ->
            R.drawable.teep_starting_stance

        techniqueId == "teep" && panelTitle == "Knee chamber" ->
            R.drawable.teep_knee_chamber

        techniqueId == "teep" && panelTitle == "Push extension" ->
            R.drawable.teep_push_extension

        techniqueId == "teep" && panelTitle == "Return to stance" ->
            R.drawable.teep_return_to_stance

        // Round Kick
        techniqueId == "round_kick" && panelTitle == "Starting stance" ->
            R.drawable.round_kick_starting_stance

        techniqueId == "round_kick" && panelTitle == "Support-foot pivot" ->
            R.drawable.round_kick_support_foot_pivot

        techniqueId == "round_kick" && panelTitle == "Hip rotation" ->
            R.drawable.round_kick_hip_rotation

        techniqueId == "round_kick" && panelTitle == "Recovery" ->
            R.drawable.round_kick_recovery

        // Knee
        techniqueId == "knee" && panelTitle == "Starting stance" ->
            R.drawable.knee_starting_stance

        techniqueId == "knee" && panelTitle == "Hip drive" ->
            R.drawable.knee_hip_drive

        techniqueId == "knee" && panelTitle == "Knee path" ->
            R.drawable.knee_knee_path

        techniqueId == "knee" && panelTitle == "Return to stance" ->
            R.drawable.knee_return_to_stance

        // Elbow
        techniqueId == "elbow" && panelTitle == "Starting guard" ->
            R.drawable.elbow_starting_guard

        techniqueId == "elbow" && panelTitle == "Elbow chamber" ->
            R.drawable.elbow_elbow_chamber

        techniqueId == "elbow" && panelTitle == "Striking path" ->
            R.drawable.elbow_striking_path

        techniqueId == "elbow" && panelTitle == "Recovery" ->
            R.drawable.elbow_recovery

        // Check Kick
        techniqueId == "check_kick" && panelTitle == "Starting stance" ->
            R.drawable.check_kick_starting_stance

        techniqueId == "check_kick" && panelTitle == "Knee lift" ->
            R.drawable.check_kick_knee_lift

        techniqueId == "check_kick" && panelTitle == "Shin position" ->
            R.drawable.check_kick_shin_position

        techniqueId == "check_kick" && panelTitle == "Return to stance" ->
            R.drawable.check_kick_return_to_stance

        // Sprawl
        techniqueId == "sprawl" && panelTitle == "Opponent shoots" ->
            R.drawable.sprawl_opponent_shoots

        techniqueId == "sprawl" && panelTitle == "Hips back" ->
            R.drawable.sprawl_hips_back

        techniqueId == "sprawl" && panelTitle == "Frame" ->
            R.drawable.sprawl_frame

        techniqueId == "sprawl" && panelTitle == "Control & follow-up" ->
            R.drawable.sprawl_recover

        else -> null
    }
}

private fun buildTechniqueAssetName(
    techniqueId: String,
    panelTitle: String
): String {
    val cleanPanelTitle = panelTitle
        .lowercase()
        .replace(Regex("[^a-z0-9]+"), "_")
        .trim('_')

    return "${techniqueId}_${cleanPanelTitle}.png"
}