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
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.get(applicationContext)
        val userRepository = UserProfileRepository(database.userProfileDao())
        val weightRepository = WeightRepository(
            weightDao = database.weightDao(),
            profileDao = database.userProfileDao()
        )

        setContent {
            CornerstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CornerstoneApp(
                        userRepository = userRepository,
                        weightRepository = weightRepository
                    )
                }
            }
        }
    }
}

private enum class Screen {
    HOME,
    DURATION,
    SESSION,
    GLOSSARY,
    WEIGHT_SETUP,
    WEIGHT_CUT
}

@Composable
fun CornerstoneApp(
    userRepository: UserProfileRepository,
    weightRepository: WeightRepository
) {
    val profile by userRepository.profile.collectAsStateWithLifecycle(initialValue = null)
    val weightEntries by weightRepository.entries.collectAsStateWithLifecycle(initialValue = emptyList())

    var screen by remember { mutableStateOf(Screen.HOME) }
    var secondsPerCombo by remember { mutableIntStateOf(0) }
    var cutStatus by remember { mutableStateOf<CutStatus?>(null) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(screen, weightEntries, profile?.targetWeightKg, profile?.fightDateEpochDay) {
        if (screen == Screen.WEIGHT_CUT) {
            cutStatus = weightRepository.computeStatus()
        }
    }

    when {
        profile == null || !profile!!.onboardingComplete -> {
            val onboardingVm: OnboardingViewModel = viewModel(
                factory = OnboardingViewModel.Factory(userRepository)
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
                    onOpenGlossary = { screen = Screen.GLOSSARY },
                    onOpenWeightCut = {
                        screen = if (
                            currentProfile.targetWeightKg != null &&
                            currentProfile.fightDateEpochDay != null
                        ) {
                            Screen.WEIGHT_CUT
                        } else {
                            Screen.WEIGHT_SETUP
                        }
                    }
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
                                    scope.launch {
                                        userRepository.incrementSessionsCompleted()
                                    }
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

                Screen.WEIGHT_SETUP -> WeightSetupScreen(
                    onSave = { targetKg, fightInDays, useKg ->
                        scope.launch {
                            weightRepository.setPlan(
                                targetKg = targetKg,
                                fightDate = LocalDate.now().plusDays(fightInDays.toLong()),
                                useKg = useKg
                            )
                            cutStatus = weightRepository.computeStatus()
                            screen = Screen.WEIGHT_CUT
                        }
                    },
                    onExit = { screen = Screen.HOME }
                )

                Screen.WEIGHT_CUT -> WeightCutScreen(
                    status = cutStatus,
                    entries = weightEntries,
                    useKg = currentProfile.weightUnit == "kg",
                    onLogWeight = { weightKg ->
                        scope.launch {
                            weightRepository.logWeight(weightKg)
                            cutStatus = weightRepository.computeStatus()
                        }
                    },
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
                text = "Building tonight's session...",
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