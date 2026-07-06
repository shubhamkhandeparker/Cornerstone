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

        val userRepository = UserProfileRepository(
            database.userProfileDao()
        )

        val weightRepository = WeightRepository(
            weightDao = database.weightDao(),
            profileDao = database.userProfileDao()
        )

        val comboLibraryRepository = ComboLibraryRepository(
            database.comboLibraryDao()
        )

        val trainingSessionRepository = TrainingSessionRepository(
            database.trainingSessionDao()
        )

        val progressPhotoRepository = ProgressPhotoRepository(
            dao = database.progressPhotoDao(),
            appContext = applicationContext
        )

        setContent {
            CornerstoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CornerstoneApp(
                        userRepository = userRepository,
                        weightRepository = weightRepository,
                        comboLibraryRepository = comboLibraryRepository,
                        trainingSessionRepository = trainingSessionRepository,
                        progressPhotoRepository = progressPhotoRepository
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
    TECHNIQUES,
    TECHNIQUE_DETAIL,
    PLAYLISTS,
    PAYWALL,
    WEIGHT_SETUP,
    WEIGHT_CUT,
    PROGRESS_CAMERA
}

@Composable
fun CornerstoneApp(
    userRepository: UserProfileRepository,
    weightRepository: WeightRepository,
    comboLibraryRepository: ComboLibraryRepository,
    trainingSessionRepository: TrainingSessionRepository,
    progressPhotoRepository: ProgressPhotoRepository
) {
    val profile by userRepository.profile.collectAsStateWithLifecycle(
        initialValue = null
    )

    val weightEntries by weightRepository.entries.collectAsStateWithLifecycle(
        initialValue = emptyList()
    )

    val todaySessionCount by trainingSessionRepository
        .observeTodaySessionCount()
        .collectAsStateWithLifecycle(initialValue = 0)

    val todayDurationSeconds by trainingSessionRepository
        .observeTodayDurationSeconds()
        .collectAsStateWithLifecycle(initialValue = 0)

    val currentProfile = profile

    var screen by remember {
        mutableStateOf(Screen.HOME)
    }

    var secondsPerCombo by remember {
        mutableIntStateOf(0)
    }

    var combosPerSession by remember {
        mutableIntStateOf(6)
    }

    var aiSessionOffset by remember {
        mutableIntStateOf(0)
    }

    var cutStatus by remember {
        mutableStateOf<CutStatus?>(null)
    }

    var playlistSessionCombos by remember {
        mutableStateOf<List<Combo>?>(null)
    }

    var selectedTechniqueId by remember {
        mutableStateOf("jab")
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(
        screen,
        weightEntries,
        currentProfile?.targetWeightKg,
        currentProfile?.fightDateEpochDay
    ) {
        if (screen == Screen.WEIGHT_CUT) {
            cutStatus = weightRepository.computeStatus()
        }
    }

    when {
        currentProfile == null || !currentProfile.introSeen -> {
            IntroStoryScreen(
                onFinished = {
                    scope.launch {
                        userRepository.markIntroSeen()
                    }
                }
            )
        }

        !currentProfile.onboardingComplete -> {
            val onboardingVm: OnboardingViewModel = viewModel(
                factory = OnboardingViewModel.Factory(userRepository)
            )

            OnboardingScreen(
                viewModel = onboardingVm,
                onFinished = { }
            )
        }

        else -> {
            fun openWeightCut() {
                screen = when {
                    !currentProfile.isPro -> Screen.PAYWALL

                    currentProfile.targetWeightKg != null &&
                            currentProfile.fightDateEpochDay != null -> Screen.WEIGHT_CUT

                    else -> Screen.WEIGHT_SETUP
                }
            }

            fun openProgressCamera() {
                screen = if (!currentProfile.isPro) {
                    Screen.PAYWALL
                } else {
                    Screen.PROGRESS_CAMERA
                }
            }

            fun finishSessionAndGoHome(
                sessionType: String,
                comboCount: Int
            ) {
                scope.launch {
                    userRepository.incrementSessionsCompleted()

                    trainingSessionRepository.logFinishedSession(
                        sessionType = sessionType,
                        sport = currentProfile.sport,
                        comboCount = comboCount,
                        secondsPerCombo = secondsPerCombo
                    )
                }

                playlistSessionCombos = null
                screen = Screen.HOME
            }

            fun finishAiSessionAndGoHome(
                actualComboCount: Int
            ) {
                aiSessionOffset += actualComboCount

                finishSessionAndGoHome(
                    sessionType = "ai",
                    comboCount = actualComboCount
                )
            }

            fun finishPlaylistSessionAndGoHome(
                actualComboCount: Int
            ) {
                finishSessionAndGoHome(
                    sessionType = "playlist",
                    comboCount = actualComboCount
                )
            }

            fun exitSessionAndGoHome() {
                playlistSessionCombos = null
                screen = Screen.HOME
            }

            when (screen) {
                Screen.HOME -> HomeScreen(
                    profile = currentProfile,
                    todaySessionCount = todaySessionCount,
                    todayDurationSeconds = todayDurationSeconds,
                    onStartSession = {
                        playlistSessionCombos = null
                        screen = Screen.DURATION
                    },
                    onOpenPlaylists = {
                        screen = Screen.PLAYLISTS
                    },
                    onOpenGlossary = {
                        screen = Screen.GLOSSARY
                    },
                    onOpenTechniques = {
                        screen = Screen.TECHNIQUES
                    },
                    onOpenWeightCut = {
                        openWeightCut()
                    },
                    onOpenProgressCamera = {
                        openProgressCamera()
                    }
                )

                Screen.TECHNIQUES -> TechniquesScreen(
                    onTechniqueClick = { techniqueId ->
                        selectedTechniqueId = techniqueId
                        screen = Screen.TECHNIQUE_DETAIL
                    },
                    onExit = {
                        screen = Screen.HOME
                    }
                )

                Screen.TECHNIQUE_DETAIL -> TechniqueDetailScreen(
                    techniqueId = selectedTechniqueId,
                    onBack = {
                        screen = Screen.TECHNIQUES
                    }
                )

                Screen.PLAYLISTS -> PlaylistsScreen(
                    repository = comboLibraryRepository,
                    onBack = {
                        screen = Screen.HOME
                    },
                    onRunPlaylist = { playlistCombos ->
                        playlistSessionCombos = playlistCombos.toSessionCombos()

                        if (secondsPerCombo <= 0) {
                            secondsPerCombo = 30
                        }

                        screen = Screen.SESSION
                    }
                )

                Screen.DURATION -> DurationPickerScreen(
                    onStart = { seconds, comboCount ->
                        secondsPerCombo = seconds
                        combosPerSession = comboCount
                        playlistSessionCombos = null
                        screen = Screen.SESSION
                    },
                    onExit = {
                        playlistSessionCombos = null
                        screen = Screen.HOME
                    }
                )

                Screen.SESSION -> {
                    val currentPlaylistSessionCombos = playlistSessionCombos

                    if (currentPlaylistSessionCombos != null) {
                        SessionScreen(
                            combos = currentPlaylistSessionCombos,
                            secondsPerCombo = secondsPerCombo,
                            onFinishSession = {
                                finishPlaylistSessionAndGoHome(
                                    actualComboCount = currentPlaylistSessionCombos.size
                                )
                            },
                            onExit = {
                                exitSessionAndGoHome()
                            }
                        )
                    } else {
                        val sessionVm: SessionViewModel = viewModel(
                            key = "session"
                        )

                        val state by sessionVm.state.collectAsStateWithLifecycle()

                        LaunchedEffect(
                            currentProfile.sport,
                            currentProfile.level,
                            currentProfile.dominance,
                            currentProfile.stance,
                            combosPerSession,
                            aiSessionOffset
                        ) {
                            sessionVm.load(
                                sport = currentProfile.sport,
                                level = currentProfile.level,
                                dominance = currentProfile.dominance,
                                stance = currentProfile.stance,
                                count = combosPerSession,
                                offset = aiSessionOffset
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
                                        finishAiSessionAndGoHome(
                                            actualComboCount = s.combos.size
                                        )
                                    },
                                    onExit = {
                                        exitSessionAndGoHome()
                                    }
                                )
                            }
                        }
                    }
                }

                Screen.GLOSSARY -> GlossaryScreen(
                    onExit = {
                        screen = Screen.HOME
                    }
                )

                Screen.PAYWALL -> PaywallScreen(
                    onProEntitled = {
                        scope.launch {
                            userRepository.setPro(true)

                            screen = if (
                                currentProfile.targetWeightKg != null &&
                                currentProfile.fightDateEpochDay != null
                            ) {
                                Screen.WEIGHT_CUT
                            } else {
                                Screen.WEIGHT_SETUP
                            }
                        }
                    },
                    onExit = {
                        screen = Screen.HOME
                    }
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
                    onExit = {
                        screen = Screen.HOME
                    }
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
                    onExit = {
                        screen = Screen.HOME
                    }
                )

                Screen.PROGRESS_CAMERA -> ProgressCameraScreen(
                    repository = progressPhotoRepository,
                    onExit = {
                        screen = Screen.HOME
                    }
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
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF161518),
                        InkBlack
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = FightRed
            )

            Spacer(
                Modifier.height(24.dp)
            )

            Text(
                text = "Building tonight's session...",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(
                Modifier.height(6.dp)
            )

            Text(
                text = "Adapting to your level",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}