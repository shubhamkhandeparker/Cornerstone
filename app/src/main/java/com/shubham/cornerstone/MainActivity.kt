package com.shubham.cornerstone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubham.cornerstone.ui.theme.CornerstoneTheme
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.get(applicationContext)
        val repository = UserProfileRepository(database.userProfileDao())

        setContent {
            CornerstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CornerstoneApp(repository = repository)
                }
            }
        }
    }
}

private enum class Screen { HOME, DURATION, SESSION, GLOSSARY }

@Composable
fun CornerstoneApp(repository: UserProfileRepository) {
    val profile by repository.profile.collectAsStateWithLifecycle(initialValue = null)
    var screen by remember { mutableStateOf(Screen.HOME) }
    var secondsPerCombo by remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()

    when {
        profile == null || !profile!!.onboardingComplete -> {
            val onboardingVm: OnboardingViewModel = viewModel(
                factory = OnboardingViewModel.Factory(repository)
            )
            OnboardingScreen(
                viewModel = onboardingVm,
                onFinished = { }
            )
        }

        else -> {
            val currentProfile = profile!!
            when (screen) {
                Screen.HOME -> HomeScreen(
                    profile = currentProfile,
                    onStartSession = { screen = Screen.DURATION },
                    onOpenGlossary = { screen = Screen.GLOSSARY }
                )

                Screen.DURATION -> DurationPickerScreen(
                    onStart = { seconds ->
                        secondsPerCombo = seconds
                        screen = Screen.SESSION
                    },
                    onExit = { screen = Screen.HOME }
                )

                Screen.SESSION -> {
                    val sessionVm: SessionViewModel = viewModel(key = "session")
                    val state by sessionVm.state.collectAsStateWithLifecycle()

                    LaunchedEffect(Unit) {
                        sessionVm.load(
                            sport = currentProfile.sport,
                            level = currentProfile.level,
                            dominance = currentProfile.dominance,
                            stance = currentProfile.stance
                        )
                    }

                    when (val s = state) {
                        is SessionViewModel.State.Loading -> {
                            GeneratingScreen()
                        }
                        is SessionViewModel.State.Ready -> {
                            SessionScreen(
                                combos = s.combos,
                                secondsPerCombo = secondsPerCombo,
                                onFinishSession = {
                                    scope.launch { repository.incrementSessionsCompleted() }
                                    screen = Screen.HOME
                                },
                                onExit = { screen = Screen.HOME }
                            )
                        }
                    }
                }

                Screen.GLOSSARY -> GlossaryScreen(
                    onExit = { screen = Screen.HOME }
                )
            }
        }
    }
}

@Composable
private fun GeneratingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(Color(0xFF161518), InkBlack))
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(color = FightRed)
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Building tonight's session…",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Adapting to your level",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}