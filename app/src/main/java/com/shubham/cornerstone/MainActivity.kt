package com.shubham.cornerstone

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shubham.cornerstone.ui.theme.CornerstoneTheme
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import java.time.LocalDate
import kotlinx.coroutines.launch

private const val FREE_REST_SECONDS = 15

private const val ONE_HUNDRED_KICKS_CHALLENGE_ID =
    "one_hundred_kicks"

private const val FIGHT_GEAR_CATALOG_URL =
    "https://shubhamkhandeparker.github.io/cornerstone-privacy-policy/fight-gear-catalog.json"

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database =
            AppDatabase.get(
                applicationContext
            )

        val userRepository =
            UserProfileRepository(
                database.userProfileDao()
            )

        val weightRepository =
            WeightRepository(
                weightDao =
                    database.weightDao(),
                profileDao =
                    database.userProfileDao()
            )

        val comboLibraryRepository =
            ComboLibraryRepository(
                database.comboLibraryDao()
            )

        val trainingSessionRepository =
            TrainingSessionRepository(
                database.trainingSessionDao()
            )

        val trainingRepetitionRepository =
            TrainingRepetitionRepository(
                database.trainingRepetitionDao()
            )

        val trainingPathRepository =
            TrainingPathRepository(
                database.trainingPathDao()
            )

        val cornerstonePointsRepository =
            CornerstonePointsRepository(
                database.cornerstonePointsDao()
            )

        val earnedProPassRepository =
            EarnedProPassRepository(
                database = database
            )

        val proEntitlementRepository =
            ProEntitlementRepository(
                userRepository =
                    userRepository,
                earnedProPassRepository =
                    earnedProPassRepository
            )

        val progressPhotoRepository =
            ProgressPhotoRepository(
                dao =
                    database.progressPhotoDao(),
                appContext =
                    applicationContext
            )

        val fightGearRepository:
                FightGearRepository =
            RemoteFightGearRepository(
                api = FightGearApiFactory.api,
                catalogUrl =
                    FIGHT_GEAR_CATALOG_URL,
                context =
                    applicationContext
            )

        val fightGearAnalyticsRepository =
            FightGearAnalyticsRepository(
                dao =
                    database
                        .fightGearAnalyticsDao()
            )

        val challengeRepository =
            ChallengeRepository(
                dao =
                    database
                        .challengeProgressDao(),
                pointsRepository =
                    cornerstonePointsRepository,
                earnedProPassRepository =
                    earnedProPassRepository
            )

        val challengeProgressEngine =
            ChallengeProgressEngine(
                challengeProgressDao =
                    database
                        .challengeProgressDao(),
                trainingSessionRepository =
                    trainingSessionRepository,
                trainingRepetitionRepository =
                    trainingRepetitionRepository
            )

        setContent {
            CornerstoneTheme {
                Surface(
                    modifier =
                        Modifier.fillMaxSize(),
                    color =
                        MaterialTheme
                            .colorScheme
                            .background
                ) {
                    CornerstoneApp(
                        userRepository =
                            userRepository,
                        weightRepository =
                            weightRepository,
                        comboLibraryRepository =
                            comboLibraryRepository,
                        trainingSessionRepository =
                            trainingSessionRepository,
                        trainingRepetitionRepository =
                            trainingRepetitionRepository,
                        trainingPathRepository =
                            trainingPathRepository,
                        progressPhotoRepository =
                            progressPhotoRepository,
                        fightGearRepository =
                            fightGearRepository,
                        fightGearAnalyticsRepository =
                            fightGearAnalyticsRepository,
                        challengeRepository =
                            challengeRepository,
                        challengeProgressEngine =
                            challengeProgressEngine,
                        proEntitlementRepository =
                            proEntitlementRepository
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
    LESSON_COMPLETE,
    GLOSSARY,
    TECHNIQUES,
    TECHNIQUE_DETAIL,
    PLAYLISTS,
    CHALLENGES,
    CHALLENGE_DETAIL,
    KICK_CHALLENGE_SESSION,
    FIGHT_GEAR_DEALS,
    FIGHT_GEAR_ANALYTICS,
    PAYWALL,
    WEIGHT_SETUP,
    WEIGHT_CUT,
    PROGRESS_CAMERA
}

private enum class PaywallDestination {
    WEIGHT_CUT,
    PROGRESS_CAMERA,
    DURATION
}

private data class TrainingLessonCompleteUiData(
    val lessonTitle: String,
    val chapterTitle: String,
    val xpAwarded: Int,
    val totalXp: Int,
    val streakDays: Int,
    val activeTrainingSeconds: Int,
    val completedCombos: Int,
    val chapterCompleted: Boolean,
    val nextLessonId: String?,
    val nextLessonTitle: String?
)

@Composable
fun CornerstoneApp(
    userRepository:
    UserProfileRepository,
    weightRepository:
    WeightRepository,
    comboLibraryRepository:
    ComboLibraryRepository,
    trainingSessionRepository:
    TrainingSessionRepository,
    trainingRepetitionRepository:
    TrainingRepetitionRepository,
    trainingPathRepository:
    TrainingPathRepository,
    progressPhotoRepository:
    ProgressPhotoRepository,
    fightGearRepository:
    FightGearRepository,
    fightGearAnalyticsRepository:
    FightGearAnalyticsRepository,
    challengeRepository:
    ChallengeRepository,
    challengeProgressEngine:
    ChallengeProgressEngine,
    proEntitlementRepository:
    ProEntitlementRepository
) {
    val context =
        LocalContext.current

    val profile by
    userRepository.profile
        .collectAsStateWithLifecycle(
            initialValue = null
        )

    val entitlementFlow =
        remember(
            proEntitlementRepository
        ) {
            proEntitlementRepository
                .observeEntitlement()
        }

    val proEntitlementState by
    entitlementFlow
        .collectAsStateWithLifecycle(
            initialValue =
                ProEntitlementState()
        )

    val weightEntries by
    weightRepository.entries
        .collectAsStateWithLifecycle(
            initialValue = emptyList()
        )

    val todaySessionCount by
    trainingSessionRepository
        .observeTodaySessionCount()
        .collectAsStateWithLifecycle(
            initialValue = 0
        )

    val todayDurationSeconds by
    trainingSessionRepository
        .observeTodayDurationSeconds()
        .collectAsStateWithLifecycle(
            initialValue = 0
        )

    val fightGearUiState by
    fightGearRepository.uiState
        .collectAsStateWithLifecycle()

    val challengeViewModel:
            ChallengeViewModel =
        viewModel(
            key =
                "challenge_view_model",
            factory =
                ChallengeViewModel.Factory(
                    repository =
                        challengeRepository
                )
        )

    val challengeUiState by
    challengeViewModel.uiState
        .collectAsStateWithLifecycle()

    val trainingPathViewModel:
            TrainingPathViewModel =
        viewModel(
            key =
                "training_path_view_model",
            factory =
                TrainingPathViewModel.Factory(
                    repository =
                        trainingPathRepository
                )
        )

    val trainingPathUiState by
    trainingPathViewModel.uiState
        .collectAsStateWithLifecycle()

    val currentProfile =
        profile

    val effectiveIsPro =
        proEntitlementState.isPro ||
                currentProfile?.isPro == true

    var screen by
    rememberSaveable {
        mutableStateOf(
            Screen.HOME
        )
    }

    var paywallDestination by
    rememberSaveable {
        mutableStateOf(
            PaywallDestination
                .WEIGHT_CUT
        )
    }

    var secondsPerCombo by
    rememberSaveable {
        mutableIntStateOf(0)
    }

    var restSeconds by
    rememberSaveable {
        mutableIntStateOf(
            FREE_REST_SECONDS
        )
    }

    var combosPerSession by
    rememberSaveable {
        mutableIntStateOf(6)
    }

    var aiSessionOffset by
    rememberSaveable {
        mutableIntStateOf(0)
    }

    var activeStructuredLessonId by
    rememberSaveable {
        mutableStateOf<String?>(
            null
        )
    }

    var lessonCompleteUiData by
    remember {
        mutableStateOf<TrainingLessonCompleteUiData?>(
            null
        )
    }

    var cutStatus by
    remember {
        mutableStateOf<CutStatus?>(
            null
        )
    }

    var playlistSessionCombos by
    remember {
        mutableStateOf<List<Combo>?>(
            null
        )
    }

    var selectedTechniqueId by
    rememberSaveable {
        mutableStateOf("jab")
    }

    var selectedChallengeId by
    rememberSaveable {
        mutableStateOf<String?>(
            null
        )
    }

    var selectedFightGearCategory by
    rememberSaveable {
        mutableStateOf(
            FightGearCategory.ALL
        )
    }

    val impressedFightGearProductIdsThisVisit =
        remember {
            mutableSetOf<String>()
        }

    val clickedFightGearProductIdsThisVisit =
        remember {
            mutableSetOf<String>()
        }

    val scope =
        rememberCoroutineScope()

    LaunchedEffect(
        fightGearRepository
    ) {
        fightGearRepository.refresh()
    }

    LaunchedEffect(
        fightGearAnalyticsRepository
    ) {
        fightGearAnalyticsRepository
            .deleteEventsOlderThan90Days()
    }

    LaunchedEffect(
        challengeProgressEngine
    ) {
        challengeProgressEngine
            .refreshActiveChallenges()
    }

    LaunchedEffect(
        currentProfile?.sport,
        currentProfile?.onboardingComplete
    ) {
        val fighter =
            currentProfile
                ?: return@LaunchedEffect

        if (
            fighter.onboardingComplete
        ) {
            trainingPathViewModel
                .loadSport(
                    sport =
                        fighter.sport
                )
        }
    }

    LaunchedEffect(
        challengeUiState.message
    ) {
        val currentMessage =
            challengeUiState.message
                ?: return@LaunchedEffect

        Toast.makeText(
            context,
            currentMessage,
            Toast.LENGTH_SHORT
        ).show()

        challengeViewModel
            .clearMessage()
    }

    LaunchedEffect(
        screen,
        weightEntries,
        currentProfile?.targetWeightKg,
        currentProfile?.fightDateEpochDay
    ) {
        if (
            screen ==
            Screen.WEIGHT_CUT
        ) {
            cutStatus =
                weightRepository
                    .computeStatus()
        }
    }

    when {
        currentProfile == null ||
                !currentProfile.introSeen -> {

            IntroStoryScreen(
                onFinished = {
                    scope.launch {
                        userRepository
                            .markIntroSeen()
                    }
                }
            )
        }

        !currentProfile
            .onboardingComplete -> {

            val onboardingVm:
                    OnboardingViewModel =
                viewModel(
                    factory =
                        OnboardingViewModel
                            .Factory(
                                userRepository
                            )
                )

            OnboardingScreen(
                viewModel =
                    onboardingVm,
                onFinished = {}
            )
        }

        else -> {
            val effectiveProfile =
                currentProfile.copy(
                    isPro =
                        effectiveIsPro
                )

            fun openWeightCut() {
                if (!effectiveIsPro) {
                    paywallDestination =
                        PaywallDestination
                            .WEIGHT_CUT

                    screen =
                        Screen.PAYWALL
                } else {
                    screen =
                        if (
                            currentProfile
                                .targetWeightKg !=
                            null &&
                            currentProfile
                                .fightDateEpochDay !=
                            null
                        ) {
                            Screen.WEIGHT_CUT
                        } else {
                            Screen.WEIGHT_SETUP
                        }
                }
            }

            fun openProgressCamera() {
                if (!effectiveIsPro) {
                    paywallDestination =
                        PaywallDestination
                            .PROGRESS_CAMERA

                    screen =
                        Screen.PAYWALL
                } else {
                    screen =
                        Screen
                            .PROGRESS_CAMERA
                }
            }

            fun openRestPaywall() {
                paywallDestination =
                    PaywallDestination
                        .DURATION

                screen =
                    Screen.PAYWALL
            }

            fun finishSessionAndGoHome(
                sessionType: String,
                result:
                SessionCompletionResult
            ) {
                val completedCombos =
                    result.completedCombos
                        .coerceIn(
                            minimumValue = 0,
                            maximumValue =
                                result.totalCombos
                                    .coerceAtLeast(
                                        0
                                    )
                        )

                val activeTrainingSeconds =
                    result.activeTrainingSeconds
                        .coerceAtLeast(0)

                val shouldSaveSession =
                    result.genuinelyFinished &&
                            completedCombos > 0 &&
                            activeTrainingSeconds > 0

                playlistSessionCombos =
                    null

                activeStructuredLessonId =
                    null

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME

                if (!shouldSaveSession) {
                    Toast.makeText(
                        context,
                        "Session not saved because no active drill was completed.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                scope.launch {
                    trainingSessionRepository
                        .logFinishedSession(
                            sessionType =
                                sessionType,
                            sport =
                                currentProfile
                                    .sport,
                            comboCount =
                                completedCombos,
                            secondsPerCombo =
                                secondsPerCombo,
                            activeTrainingSeconds =
                                activeTrainingSeconds
                        )

                    userRepository
                        .incrementSessionsCompleted()

                    challengeProgressEngine
                        .refreshActiveChallenges()
                }
            }

            fun finishAiSessionAndGoHome(
                result:
                SessionCompletionResult
            ) {
                aiSessionOffset +=
                    result.totalCombos
                        .coerceAtLeast(0)

                finishSessionAndGoHome(
                    sessionType = "ai",
                    result = result
                )
            }

            fun finishPlaylistSessionAndGoHome(
                result:
                SessionCompletionResult
            ) {
                finishSessionAndGoHome(
                    sessionType =
                        "playlist",
                    result =
                        result
                )
            }

            fun finishStructuredLesson(
                result:
                SessionCompletionResult
            ) {
                val lessonId =
                    activeStructuredLessonId

                val lesson =
                    lessonId?.let {
                            id ->

                        TrainingCurriculum
                            .getLesson(
                                lessonId = id
                            )
                    }

                val completedCombos =
                    result.completedCombos
                        .coerceIn(
                            minimumValue = 0,
                            maximumValue =
                                result.totalCombos
                                    .coerceAtLeast(0)
                        )

                val activeTrainingSeconds =
                    result.activeTrainingSeconds
                        .coerceAtLeast(0)

                val skippedCombos =
                    result.skippedCombos
                        .coerceAtLeast(0)

                val shouldSaveSession =
                    lesson != null &&
                            result.genuinelyFinished &&
                            (
                                    completedCombos > 0 ||
                                            skippedCombos > 0
                                    ) &&
                            activeTrainingSeconds > 0

                playlistSessionCombos =
                    null

                activeStructuredLessonId =
                    null

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME

                if (
                    lesson == null
                ) {
                    Toast.makeText(
                        context,
                        "Lesson could not be found.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                if (!shouldSaveSession) {
                    Toast.makeText(
                        context,
                        "Lesson was not saved because the training was not completed.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                scope.launch {
                    val trainingSessionId =
                        trainingSessionRepository
                            .logFinishedSession(
                                sessionType =
                                    "fight_path",
                                sport =
                                    lesson.sport,
                                comboCount =
                                    completedCombos,
                                secondsPerCombo =
                                    TrainingPathSessionFactory
                                        .SECONDS_PER_COMBO,
                                activeTrainingSeconds =
                                    activeTrainingSeconds
                            )

                    userRepository
                        .incrementSessionsCompleted()

                    challengeProgressEngine
                        .refreshActiveChallenges()

                    val completionResult =
                        trainingPathViewModel
                            .completeCurrentLesson(
                                trainingSessionId =
                                    trainingSessionId,
                                activeTrainingSeconds =
                                    activeTrainingSeconds,
                                completedCombos =
                                    completedCombos,
                                skippedCombos =
                                    skippedCombos
                            )

                    when (
                        completionResult
                    ) {
                        is StructuredLessonCompletionResult
                        .Completed -> {

                            val nextLesson =
                                completionResult
                                    .nextLesson

                            val chapter =
                                TrainingCurriculum
                                    .getChapter(
                                        chapterId =
                                            completionResult
                                                .lesson
                                                .chapterId
                                    )

                            lessonCompleteUiData =
                                TrainingLessonCompleteUiData(
                                    lessonTitle =
                                        completionResult
                                            .lesson
                                            .title,
                                    chapterTitle =
                                        chapter
                                            ?.title
                                            ?: "Fight Path",
                                    xpAwarded =
                                        completionResult
                                            .xpAwarded,
                                    totalXp =
                                        completionResult
                                            .totalXp,
                                    streakDays =
                                        completionResult
                                            .currentStreakDays,
                                    activeTrainingSeconds =
                                        activeTrainingSeconds,
                                    completedCombos =
                                        completedCombos,
                                    chapterCompleted =
                                        completionResult
                                            .chapterCompleted,
                                    nextLessonId =
                                        nextLesson
                                            ?.id,
                                    nextLessonTitle =
                                        nextLesson
                                            ?.title
                                )

                            screen =
                                Screen
                                    .LESSON_COMPLETE
                        }

                        is StructuredLessonCompletionResult
                        .AlreadyCompleted -> {

                            Toast.makeText(
                                context,
                                "Lesson already completed.",
                                Toast.LENGTH_SHORT
                            ).show()

                            screen =
                                Screen.HOME
                        }

                        is StructuredLessonCompletionResult
                        .InvalidSession -> {

                            Toast.makeText(
                                context,
                                completionResult
                                    .reason,
                                Toast.LENGTH_LONG
                            ).show()

                            screen =
                                Screen.HOME
                        }

                        StructuredLessonCompletionResult
                            .LessonNotFound -> {

                            Toast.makeText(
                                context,
                                "Lesson could not be found.",
                                Toast.LENGTH_SHORT
                            ).show()

                            screen =
                                Screen.HOME
                        }
                    }
                }
            }

            fun exitSessionAndGoHome() {
                playlistSessionCombos =
                    null

                activeStructuredLessonId =
                    null

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME
            }

            fun startStructuredLesson(
                lessonId: String? = null
            ) {
                val lesson =
                    if (
                        lessonId != null
                    ) {
                        TrainingCurriculum
                            .getLesson(
                                lessonId =
                                    lessonId
                            )
                    } else {
                        trainingPathUiState
                            .currentLesson
                    }

                if (
                    lesson == null
                ) {
                    Toast.makeText(
                        context,
                        "Your Fight Path is still loading.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                val plan =
                    TrainingPathSessionFactory
                        .create(
                            lesson = lesson
                        )

                lessonCompleteUiData =
                    null

                activeStructuredLessonId =
                    lesson.id

                playlistSessionCombos =
                    null

                secondsPerCombo =
                    plan.secondsPerCombo

                restSeconds =
                    plan.restSeconds

                combosPerSession =
                    plan.combos.size

                screen =
                    Screen.SESSION
            }

            fun saveKickChallengeResult(
                result:
                KickChallengeSessionResult
            ) {
                val repetitions =
                    result.repetitionCount
                        .coerceAtLeast(0)

                val activeSeconds =
                    result.activeSeconds
                        .coerceAtLeast(0)

                if (
                    !result.genuinelyFinished ||
                    repetitions <= 0 ||
                    activeSeconds <= 0
                ) {
                    screen =
                        Screen
                            .CHALLENGE_DETAIL

                    Toast.makeText(
                        context,
                        "Kick session was not saved.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                scope.launch {
                    val sessionType =
                        when (
                            result.mode
                        ) {
                            KickCountingMode
                                .GUIDED_SOLO -> {

                                "kick_guided"
                            }

                            KickCountingMode
                                .MANUAL_PARTNER -> {

                                "kick_manual"
                            }
                        }

                    val trainingSessionId =
                        trainingSessionRepository
                            .logFinishedSession(
                                sessionType =
                                    sessionType,
                                sport =
                                    currentProfile
                                        .sport,
                                comboCount = 0,
                                secondsPerCombo = 0,
                                activeTrainingSeconds =
                                    activeSeconds
                            )

                    trainingRepetitionRepository
                        .logKickRepetitions(
                            sessionType =
                                sessionType,
                            sport =
                                currentProfile
                                    .sport,
                            repetitionCount =
                                repetitions,
                            activeSeconds =
                                activeSeconds,
                            trainingSessionId =
                                trainingSessionId
                        )

                    userRepository
                        .incrementSessionsCompleted()

                    challengeProgressEngine
                        .refreshActiveChallenges()

                    screen =
                        Screen
                            .CHALLENGE_DETAIL

                    Toast.makeText(
                        context,
                        if (
                            result.completedTarget
                        ) {
                            "Kick target completed."
                        } else {
                            "$repetitions kicks saved. Complete 100 in one session."
                        },
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            when (screen) {
                Screen.HOME -> {
                    HomeScreen(
                        profile =
                            effectiveProfile,
                        todaySessionCount =
                            todaySessionCount,
                        todayDurationSeconds =
                            todayDurationSeconds,
                        onStartSession = {
                            activeStructuredLessonId =
                                null

                            playlistSessionCombos =
                                null

                            lessonCompleteUiData =
                                null

                            screen =
                                Screen.DURATION
                        },
                        trainingPathState =
                            trainingPathUiState,
                        onContinueFightPath = {
                            startStructuredLesson()
                        },
                        onOpenFreeTraining = {
                            activeStructuredLessonId =
                                null

                            playlistSessionCombos =
                                null

                            lessonCompleteUiData =
                                null

                            screen =
                                Screen.DURATION
                        },
                        onOpenPlaylists = {
                            activeStructuredLessonId =
                                null

                            lessonCompleteUiData =
                                null

                            screen =
                                Screen.PLAYLISTS
                        },
                        onOpenGlossary = {
                            screen =
                                Screen.GLOSSARY
                        },
                        onOpenTechniques = {
                            screen =
                                Screen.TECHNIQUES
                        },
                        onOpenWeightCut = {
                            openWeightCut()
                        },
                        onOpenProgressCamera = {
                            openProgressCamera()
                        },
                        onOpenFightGearDeals = {
                            selectedFightGearCategory =
                                FightGearCategory.ALL

                            impressedFightGearProductIdsThisVisit
                                .clear()

                            clickedFightGearProductIdsThisVisit
                                .clear()

                            screen =
                                Screen
                                    .FIGHT_GEAR_DEALS
                        },
                        onOpenChallenges = {
                            selectedChallengeId =
                                null

                            screen =
                                Screen.CHALLENGES

                            scope.launch {
                                challengeProgressEngine
                                    .refreshActiveChallenges()
                            }
                        }
                    )
                }

                Screen.CHALLENGES -> {
                    ChallengesScreen(
                        viewModel =
                            challengeViewModel,
                        onOpenChallenge = {
                                challengeId ->

                            selectedChallengeId =
                                challengeId

                            screen =
                                Screen
                                    .CHALLENGE_DETAIL
                        },
                        onExit = {
                            screen =
                                Screen.HOME
                        }
                    )
                }

                Screen.CHALLENGE_DETAIL -> {
                    val challenge =
                        challengeUiState
                            .challenges
                            .firstOrNull {
                                    item ->

                                item.definition.id ==
                                        selectedChallengeId
                            }

                    if (
                        challenge == null
                    ) {
                        ChallengeLoadingScreen()
                    } else {
                        ChallengeDetailScreen(
                            challenge =
                                challenge,
                            isProcessing =
                                challengeUiState
                                    .isProcessing,
                            onStart = {
                                challengeViewModel
                                    .startChallenge(
                                        challenge
                                            .definition
                                            .id
                                    )
                            },
                            onAbandon = {
                                challengeViewModel
                                    .abandonChallenge(
                                        challenge
                                            .definition
                                            .id
                                    )
                            },
                            onRestart = {
                                challengeViewModel
                                    .restartChallenge(
                                        challenge
                                            .definition
                                            .id
                                    )
                            },
                            onClaimReward = {
                                challengeViewModel
                                    .claimReward(
                                        challenge
                                            .definition
                                            .id
                                    )
                            },
                            onStartKickSession = {
                                if (
                                    challenge
                                        .definition
                                        .id ==
                                    ONE_HUNDRED_KICKS_CHALLENGE_ID &&
                                    challenge.status ==
                                    ChallengeStatus.ACTIVE
                                ) {
                                    screen =
                                        Screen
                                            .KICK_CHALLENGE_SESSION
                                }
                            },
                            onBack = {
                                screen =
                                    Screen.CHALLENGES
                            }
                        )
                    }
                }

                Screen
                    .KICK_CHALLENGE_SESSION -> {

                    val challenge =
                        challengeUiState
                            .challenges
                            .firstOrNull {
                                    item ->

                                item.definition.id ==
                                        selectedChallengeId
                            }

                    if (
                        challenge == null ||
                        challenge.definition.id !=
                        ONE_HUNDRED_KICKS_CHALLENGE_ID ||
                        challenge.status !=
                        ChallengeStatus.ACTIVE
                    ) {
                        ChallengeLoadingScreen()

                        LaunchedEffect(
                            challenge?.status
                        ) {
                            if (
                                challenge != null &&
                                challenge.status !=
                                ChallengeStatus.ACTIVE
                            ) {
                                screen =
                                    Screen
                                        .CHALLENGE_DETAIL
                            }
                        }
                    } else {
                        val requiredRepetitions =
                            challenge.definition
                                .requiredRepetitionsPerDay
                                .coerceAtLeast(1)

                        KickChallengeSessionScreen(
                            targetRepetitions =
                                requiredRepetitions,
                            onSaveResult = {
                                    result ->

                                saveKickChallengeResult(
                                    result = result
                                )
                            },
                            onExit = {
                                screen =
                                    Screen
                                        .CHALLENGE_DETAIL
                            }
                        )
                    }
                }

                Screen
                    .FIGHT_GEAR_DEALS -> {

                    FightGearDealsScreen(
                        uiState =
                            fightGearUiState,
                        selectedCategory =
                            selectedFightGearCategory,
                        onCategorySelected = {
                                category ->

                            selectedFightGearCategory =
                                category
                        },
                        onProductImpression = {
                                product ->

                            val shouldRecord =
                                impressedFightGearProductIdsThisVisit
                                    .add(
                                        product.id
                                    )

                            if (shouldRecord) {
                                scope.launch {
                                    val savedSuccessfully =
                                        runCatching {
                                            fightGearAnalyticsRepository
                                                .logProductImpression(
                                                    product =
                                                        product
                                                )
                                        }.isSuccess

                                    if (
                                        !savedSuccessfully
                                    ) {
                                        impressedFightGearProductIdsThisVisit
                                            .remove(
                                                product.id
                                            )
                                    }
                                }
                            }
                        },
                        onViewDeal = {
                                product ->

                            val shouldRecordClick =
                                clickedFightGearProductIdsThisVisit
                                    .add(
                                        product.id
                                    )

                            scope.launch {
                                if (
                                    shouldRecordClick
                                ) {
                                    val savedSuccessfully =
                                        runCatching {
                                            fightGearAnalyticsRepository
                                                .logViewDealClick(
                                                    product =
                                                        product
                                                )
                                        }.isSuccess

                                    if (
                                        !savedSuccessfully
                                    ) {
                                        clickedFightGearProductIdsThisVisit
                                            .remove(
                                                product.id
                                            )
                                    }
                                }

                                val opened =
                                    FightGearLinkOpener
                                        .open(
                                            context =
                                                context,
                                            productUrl =
                                                product
                                                    .productUrl
                                        )

                                if (!opened) {
                                    Toast.makeText(
                                        context,
                                        "Unable to open the deal link.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },
                        onRetry = {
                            scope.launch {
                                fightGearRepository
                                    .refresh()
                            }
                        },
                        onExit = {
                            screen =
                                Screen.HOME
                        },
                        onOpenAnalytics = {
                            screen =
                                Screen
                                    .FIGHT_GEAR_ANALYTICS
                        }
                    )
                }

                Screen
                    .FIGHT_GEAR_ANALYTICS -> {

                    FightGearAnalyticsScreen(
                        repository =
                            fightGearAnalyticsRepository,
                        onExit = {
                            screen =
                                Screen
                                    .FIGHT_GEAR_DEALS
                        },
                        onAnalyticsReset = {
                            impressedFightGearProductIdsThisVisit
                                .clear()

                            clickedFightGearProductIdsThisVisit
                                .clear()
                        }
                    )
                }

                Screen.TECHNIQUES -> {
                    TechniquesScreen(
                        onTechniqueClick = {
                                techniqueId ->

                            selectedTechniqueId =
                                techniqueId

                            screen =
                                Screen
                                    .TECHNIQUE_DETAIL
                        },
                        onExit = {
                            screen =
                                Screen.HOME
                        }
                    )
                }

                Screen.TECHNIQUE_DETAIL -> {
                    TechniqueDetailScreen(
                        techniqueId =
                            selectedTechniqueId,
                        onBack = {
                            screen =
                                Screen.TECHNIQUES
                        }
                    )
                }

                Screen.PLAYLISTS -> {
                    PlaylistsScreen(
                        repository =
                            comboLibraryRepository,
                        onBack = {
                            screen =
                                Screen.HOME
                        },
                        onRunPlaylist = {
                                playlistCombos ->

                            activeStructuredLessonId =
                                null

                            lessonCompleteUiData =
                                null

                            playlistSessionCombos =
                                playlistCombos
                                    .toSessionCombos()

                            if (
                                secondsPerCombo <= 0
                            ) {
                                secondsPerCombo =
                                    30
                            }

                            if (!effectiveIsPro) {
                                restSeconds =
                                    FREE_REST_SECONDS
                            }

                            screen =
                                Screen.SESSION
                        }
                    )
                }

                Screen.DURATION -> {
                    DurationPickerScreen(
                        onStart = {
                                seconds,
                                comboCount ->

                            activeStructuredLessonId =
                                null

                            lessonCompleteUiData =
                                null

                            secondsPerCombo =
                                seconds

                            combosPerSession =
                                comboCount

                            restSeconds =
                                FREE_REST_SECONDS

                            playlistSessionCombos =
                                null

                            screen =
                                Screen.SESSION
                        },
                        onExit = {
                            activeStructuredLessonId =
                                null

                            playlistSessionCombos =
                                null

                            lessonCompleteUiData =
                                null

                            screen =
                                Screen.HOME
                        },
                        isPro =
                            effectiveIsPro,
                        onUnlockPro = {
                            openRestPaywall()
                        },
                        onStartWithRest = {
                                seconds,
                                comboCount,
                                selectedRestSeconds ->

                            activeStructuredLessonId =
                                null

                            lessonCompleteUiData =
                                null

                            secondsPerCombo =
                                seconds

                            combosPerSession =
                                comboCount

                            restSeconds =
                                if (effectiveIsPro) {
                                    selectedRestSeconds
                                        .coerceAtLeast(
                                            1
                                        )
                                } else {
                                    FREE_REST_SECONDS
                                }

                            playlistSessionCombos =
                                null

                            screen =
                                Screen.SESSION
                        }
                    )
                }

                Screen.SESSION -> {
                    val currentPlaylistSessionCombos =
                        playlistSessionCombos

                    val structuredLesson =
                        activeStructuredLessonId
                            ?.let {
                                    lessonId ->

                                TrainingCurriculum
                                    .getLesson(
                                        lessonId =
                                            lessonId
                                    )
                            }

                    when {
                        structuredLesson != null -> {
                            val plan =
                                TrainingPathSessionFactory
                                    .create(
                                        lesson =
                                            structuredLesson
                                    )

                            SessionScreen(
                                combos =
                                    plan.combos,
                                secondsPerCombo =
                                    plan.secondsPerCombo,
                                restSeconds =
                                    plan.restSeconds,
                                onFinishSession = {},
                                onExit = {
                                    exitSessionAndGoHome()
                                },
                                onSessionResult = {
                                        result ->

                                    finishStructuredLesson(
                                        result =
                                            result
                                    )
                                }
                            )
                        }

                        currentPlaylistSessionCombos !=
                                null -> {

                            val effectiveRestSeconds =
                                if (effectiveIsPro) {
                                    restSeconds
                                        .coerceAtLeast(1)
                                } else {
                                    FREE_REST_SECONDS
                                }

                            SessionScreen(
                                combos =
                                    currentPlaylistSessionCombos,
                                secondsPerCombo =
                                    secondsPerCombo,
                                restSeconds =
                                    effectiveRestSeconds,
                                onFinishSession = {},
                                onExit = {
                                    exitSessionAndGoHome()
                                },
                                onSessionResult = {
                                        result ->

                                    finishPlaylistSessionAndGoHome(
                                        result = result
                                    )
                                }
                            )
                        }

                        else -> {
                            val effectiveRestSeconds =
                                if (effectiveIsPro) {
                                    restSeconds
                                        .coerceAtLeast(1)
                                } else {
                                    FREE_REST_SECONDS
                                }

                            val sessionVm:
                                    SessionViewModel =
                                viewModel(
                                    key = "session"
                                )

                            val state by
                            sessionVm.state
                                .collectAsStateWithLifecycle()

                            LaunchedEffect(
                                currentProfile.sport,
                                currentProfile.level,
                                currentProfile.dominance,
                                currentProfile.stance,
                                combosPerSession,
                                aiSessionOffset
                            ) {
                                sessionVm.load(
                                    sport =
                                        currentProfile
                                            .sport,
                                    level =
                                        currentProfile
                                            .level,
                                    dominance =
                                        currentProfile
                                            .dominance,
                                    stance =
                                        currentProfile
                                            .stance,
                                    count =
                                        combosPerSession,
                                    offset =
                                        aiSessionOffset
                                )
                            }

                            when (
                                val sessionState =
                                    state
                            ) {
                                is SessionViewModel
                                .State.Loading -> {

                                    GeneratingScreen()
                                }

                                is SessionViewModel
                                .State.Ready -> {

                                    SessionScreen(
                                        combos =
                                            sessionState
                                                .combos,
                                        secondsPerCombo =
                                            secondsPerCombo,
                                        restSeconds =
                                            effectiveRestSeconds,
                                        onFinishSession = {},
                                        onExit = {
                                            exitSessionAndGoHome()
                                        },
                                        onSessionResult = {
                                                result ->

                                            finishAiSessionAndGoHome(
                                                result =
                                                    result
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                Screen.LESSON_COMPLETE -> {
                    val summary =
                        lessonCompleteUiData

                    if (
                        summary == null
                    ) {
                        LaunchedEffect(Unit) {
                            screen =
                                Screen.HOME
                        }

                        GeneratingScreen()
                    } else {
                        TrainingLessonCompleteScreen(
                            lessonTitle =
                                summary.lessonTitle,
                            chapterTitle =
                                summary.chapterTitle,
                            xpAwarded =
                                summary.xpAwarded,
                            totalXp =
                                summary.totalXp,
                            streakDays =
                                summary.streakDays,
                            activeTrainingSeconds =
                                summary.activeTrainingSeconds,
                            completedCombos =
                                summary.completedCombos,
                            chapterCompleted =
                                summary.chapterCompleted,
                            nextLessonTitle =
                                summary.nextLessonTitle,
                            onContinue = {
                                val nextId =
                                    summary
                                        .nextLessonId

                                if (
                                    nextId != null
                                ) {
                                    startStructuredLesson(
                                        lessonId =
                                            nextId
                                    )
                                } else {
                                    lessonCompleteUiData =
                                        null

                                    screen =
                                        Screen.HOME
                                }
                            },
                            onHome = {
                                lessonCompleteUiData =
                                    null

                                screen =
                                    Screen.HOME
                            }
                        )
                    }
                }

                Screen.GLOSSARY -> {
                    GlossaryScreen(
                        onExit = {
                            screen =
                                Screen.HOME
                        }
                    )
                }

                Screen.PAYWALL -> {
                    PaywallScreen(
                        onProEntitled = {
                            scope.launch {
                                userRepository
                                    .setPro(true)

                                screen =
                                    when (
                                        paywallDestination
                                    ) {
                                        PaywallDestination
                                            .DURATION -> {

                                            Screen.DURATION
                                        }

                                        PaywallDestination
                                            .PROGRESS_CAMERA -> {

                                            Screen
                                                .PROGRESS_CAMERA
                                        }

                                        PaywallDestination
                                            .WEIGHT_CUT -> {

                                            if (
                                                currentProfile
                                                    .targetWeightKg !=
                                                null &&
                                                currentProfile
                                                    .fightDateEpochDay !=
                                                null
                                            ) {
                                                Screen
                                                    .WEIGHT_CUT
                                            } else {
                                                Screen
                                                    .WEIGHT_SETUP
                                            }
                                        }
                                    }
                            }
                        },
                        onExit = {
                            screen =
                                when (
                                    paywallDestination
                                ) {
                                    PaywallDestination
                                        .DURATION -> {

                                        Screen.DURATION
                                    }

                                    PaywallDestination
                                        .PROGRESS_CAMERA,
                                    PaywallDestination
                                        .WEIGHT_CUT -> {

                                        Screen.HOME
                                    }
                                }
                        }
                    )
                }

                Screen.WEIGHT_SETUP -> {
                    WeightSetupScreen(
                        onSave = {
                                targetKg,
                                fightInDays,
                                useKg ->

                            scope.launch {
                                weightRepository
                                    .setPlan(
                                        targetKg =
                                            targetKg,
                                        fightDate =
                                            LocalDate
                                                .now()
                                                .plusDays(
                                                    fightInDays
                                                        .toLong()
                                                ),
                                        useKg =
                                            useKg
                                    )

                                cutStatus =
                                    weightRepository
                                        .computeStatus()

                                screen =
                                    Screen.WEIGHT_CUT
                            }
                        },
                        onExit = {
                            screen =
                                Screen.HOME
                        }
                    )
                }

                Screen.WEIGHT_CUT -> {
                    WeightCutScreen(
                        status =
                            cutStatus,
                        entries =
                            weightEntries,
                        useKg =
                            currentProfile
                                .weightUnit ==
                                    "kg",
                        onLogWeight = {
                                weightKg ->

                            scope.launch {
                                weightRepository
                                    .logWeight(
                                        weightKg
                                    )

                                cutStatus =
                                    weightRepository
                                        .computeStatus()
                            }
                        },
                        onExit = {
                            screen =
                                Screen.HOME
                        }
                    )
                }

                Screen.PROGRESS_CAMERA -> {
                    ProgressCameraScreen(
                        repository =
                            progressPhotoRepository,
                        onExit = {
                            screen =
                                Screen.HOME
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChallengeLoadingScreen() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors =
                            listOf(
                                Color(
                                    0xFF161518
                                ),
                                InkBlack
                            )
                    )
                ),
        contentAlignment =
            Alignment.Center
    ) {
        Column(
            horizontalAlignment =
                Alignment
                    .CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(
                        18.dp
                    )
            )

            Text(
                text =
                    "Loading challenge...",
                fontSize = 15.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )
        }
    }
}

@Composable
private fun GeneratingScreen() {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors =
                            listOf(
                                Color(
                                    0xFF161518
                                ),
                                InkBlack
                            )
                    )
                ),
        contentAlignment =
            Alignment.Center
    ) {
        Column(
            horizontalAlignment =
                Alignment
                    .CenterHorizontally,
            verticalArrangement =
                Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(
                        24.dp
                    )
            )

            Text(
                text =
                    "Building tonight's session...",
                fontSize = 16.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(
                        6.dp
                    )
            )

            Text(
                text =
                    "Adapting to your level",
                fontSize = 13.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}