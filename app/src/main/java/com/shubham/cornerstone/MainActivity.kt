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
import androidx.compose.foundation.layout.fillMaxWidth
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
                api =
                    FightGearApiFactory.api,
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
    FIGHT_PATH,
    TRAIN_HUB,
    PROGRESS_HUB,
    MORE_HUB,

    CONDITIONING_MODE,
    CONDITIONING_STANDALONE,

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
            initialValue =
                emptyList()
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

    var conditioningPickerBackScreenName by
    rememberSaveable {
        mutableStateOf(
            Screen.HOME.name
        )
    }

    var selectedConditioningModeName by
    rememberSaveable {
        mutableStateOf(
            ConditioningMode
                .TECHNIQUE_PLUS_CONDITIONING
                .name
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
        mutableStateOf<
                TrainingLessonCompleteUiData?
                >(
            null
        )
    }

    var cutStatus by
    remember {
        mutableStateOf<
                CutStatus?
                >(
            null
        )
    }

    var playlistSessionCombos by
    remember {
        mutableStateOf<
                List<Combo>?
                >(
            null
        )
    }

    var selectedTechniqueId by
    rememberSaveable {
        mutableStateOf(
            "jab"
        )
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
        fightGearRepository
            .refresh()
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

            fun currentConditioningMode():
                    ConditioningMode {

                return runCatching {
                    ConditioningMode
                        .valueOf(
                            selectedConditioningModeName
                        )
                }.getOrDefault(
                    ConditioningMode
                        .TECHNIQUE_PLUS_CONDITIONING
                )
            }

            fun openWeightCut() {
                if (
                    !effectiveIsPro
                ) {
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
                if (
                    !effectiveIsPro
                ) {
                    paywallDestination =
                        PaywallDestination
                            .PROGRESS_CAMERA

                    screen =
                        Screen.PAYWALL
                } else {
                    screen =
                        Screen.PROGRESS_CAMERA
                }
            }

            fun openRestPaywall() {
                paywallDestination =
                    PaywallDestination
                        .DURATION

                screen =
                    Screen.PAYWALL
            }

            fun clearStructuredSessionState() {
                activeStructuredLessonId =
                    null

                selectedConditioningModeName =
                    ConditioningMode
                        .TECHNIQUE_PLUS_CONDITIONING
                        .name
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

                clearStructuredSessionState()

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME

                if (
                    !shouldSaveSession
                ) {
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
                                currentProfile.sport,
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
                    sessionType =
                        "ai",
                    result =
                        result
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

            fun finishConditioningOnlyAndGoHome(
                result:
                ConditioningWorkoutResult
            ) {
                val activeSeconds =
                    result.activeSeconds
                        .coerceAtLeast(0)

                val completedBlocks =
                    result.completedBlocks
                        .coerceAtLeast(0)

                val shouldSave =
                    result.genuinelyFinished &&
                            completedBlocks > 0 &&
                            activeSeconds > 0

                playlistSessionCombos =
                    null

                clearStructuredSessionState()

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME

                if (
                    !shouldSave
                ) {
                    Toast.makeText(
                        context,
                        "Conditioning session was not saved.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                scope.launch {
                    trainingSessionRepository
                        .logFinishedSession(
                            sessionType =
                                "conditioning_only",
                            sport =
                                currentProfile.sport,
                            comboCount =
                                0,
                            secondsPerCombo =
                                0,
                            activeTrainingSeconds =
                                activeSeconds
                        )

                    userRepository
                        .incrementSessionsCompleted()

                    challengeProgressEngine
                        .refreshActiveChallenges()

                    Toast.makeText(
                        context,
                        "Conditioning complete.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            fun finishStructuredLesson(
                result:
                SessionCompletionResult,
                conditioningActiveSeconds:
                Int = 0
            ) {
                val lessonId =
                    activeStructuredLessonId

                val lesson =
                    lessonId?.let {
                            id ->

                        TrainingCurriculum
                            .getLesson(
                                lessonId =
                                    id
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

                val techniqueActiveSeconds =
                    result.activeTrainingSeconds
                        .coerceAtLeast(0)

                val safeConditioningSeconds =
                    conditioningActiveSeconds
                        .coerceAtLeast(0)

                val totalWorkoutActiveSeconds =
                    techniqueActiveSeconds +
                            safeConditioningSeconds

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
                            techniqueActiveSeconds > 0

                playlistSessionCombos =
                    null

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME

                if (
                    lesson == null
                ) {
                    clearStructuredSessionState()

                    Toast.makeText(
                        context,
                        "Lesson could not be found.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return
                }

                if (
                    !shouldSaveSession
                ) {
                    clearStructuredSessionState()

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
                                    totalWorkoutActiveSeconds
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
                                    techniqueActiveSeconds,
                                completedCombos =
                                    completedCombos,
                                skippedCombos =
                                    skippedCombos
                            )

                    clearStructuredSessionState()

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
                                        totalWorkoutActiveSeconds,
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
                                completionResult.reason,
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

                clearStructuredSessionState()

                lessonCompleteUiData =
                    null

                screen =
                    Screen.HOME
            }

            fun prepareStructuredLesson(
                lessonId: String? = null,
                backScreen:
                Screen = Screen.HOME
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

                activeStructuredLessonId =
                    lesson.id

                selectedConditioningModeName =
                    ConditioningMode
                        .TECHNIQUE_PLUS_CONDITIONING
                        .name

                conditioningPickerBackScreenName =
                    backScreen.name

                playlistSessionCombos =
                    null

                screen =
                    Screen.CONDITIONING_MODE
            }

            fun beginStructuredLesson(
                mode:
                ConditioningMode
            ) {
                val lesson =
                    activeStructuredLessonId
                        ?.let {
                                lessonId ->

                            TrainingCurriculum
                                .getLesson(
                                    lessonId =
                                        lessonId
                                )
                        }

                if (
                    lesson == null
                ) {
                    Toast.makeText(
                        context,
                        "Lesson could not be found.",
                        Toast.LENGTH_SHORT
                    ).show()

                    clearStructuredSessionState()

                    screen =
                        Screen.HOME

                    return
                }

                val techniquePlan =
                    TrainingPathSessionFactory
                        .create(
                            lesson =
                                lesson
                        )

                selectedConditioningModeName =
                    mode.name

                lessonCompleteUiData =
                    null

                playlistSessionCombos =
                    null

                secondsPerCombo =
                    techniquePlan
                        .secondsPerCombo

                restSeconds =
                    techniquePlan
                        .restSeconds

                combosPerSession =
                    techniquePlan
                        .combos
                        .size

                screen =
                    Screen.SESSION
            }

            fun startStandaloneConditioning() {
                playlistSessionCombos =
                    null

                activeStructuredLessonId =
                    null

                lessonCompleteUiData =
                    null

                selectedConditioningModeName =
                    ConditioningMode
                        .CONDITIONING_ONLY
                        .name

                screen =
                    Screen
                        .CONDITIONING_STANDALONE
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
                                    currentProfile.sport,
                                comboCount =
                                    0,
                                secondsPerCombo =
                                    0,
                                activeTrainingSeconds =
                                    activeSeconds
                            )

                    trainingRepetitionRepository
                        .logKickRepetitions(
                            sessionType =
                                sessionType,
                            sport =
                                currentProfile.sport,
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

            val showBottomNavigation =
                screen ==
                        Screen.HOME ||
                        screen ==
                        Screen.FIGHT_PATH ||
                        screen ==
                        Screen.TRAIN_HUB ||
                        screen ==
                        Screen.PROGRESS_HUB ||
                        screen ==
                        Screen.MORE_HUB

            val selectedMainTab =
                when (
                    screen
                ) {
                    Screen.FIGHT_PATH -> {
                        CornerstoneMainTab
                            .FIGHT_PATH
                    }

                    Screen.TRAIN_HUB -> {
                        CornerstoneMainTab
                            .TRAIN
                    }

                    Screen.PROGRESS_HUB -> {
                        CornerstoneMainTab
                            .PROGRESS
                    }

                    Screen.MORE_HUB -> {
                        CornerstoneMainTab
                            .MORE
                    }

                    else -> {
                        CornerstoneMainTab
                            .HOME
                    }
                }

            Column(
                modifier =
                    Modifier.fillMaxSize()
            ) {
                Box(
                    modifier =
                        Modifier
                            .weight(1f)
                            .fillMaxWidth()
                ) {
                    when (
                        screen
                    ) {
                        Screen.HOME -> {
                            HomeScreen(
                                profile =
                                    effectiveProfile,
                                todaySessionCount =
                                    todaySessionCount,
                                todayDurationSeconds =
                                    todayDurationSeconds,
                                onStartSession = {
                                    clearStructuredSessionState()

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
                                    prepareStructuredLesson(
                                        backScreen =
                                            Screen.HOME
                                    )
                                },
                                onOpenFightPath = {
                                    screen =
                                        Screen.FIGHT_PATH
                                },
                                onOpenFreeTraining = {
                                    clearStructuredSessionState()

                                    playlistSessionCombos =
                                        null

                                    lessonCompleteUiData =
                                        null

                                    screen =
                                        Screen.DURATION
                                },
                                onOpenPlaylists = {
                                    clearStructuredSessionState()

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

                        Screen.FIGHT_PATH -> {
                            if (
                                trainingPathUiState
                                    .isLoading
                            ) {
                                GeneratingScreen()
                            } else {
                                FightPathScreen(
                                    state =
                                        trainingPathUiState,
                                    onStartCurrentLesson = {
                                        prepareStructuredLesson(
                                            backScreen =
                                                Screen
                                                    .FIGHT_PATH
                                        )
                                    },
                                    onBack = {
                                        screen =
                                            Screen.HOME
                                    }
                                )
                            }
                        }

                        Screen.TRAIN_HUB -> {
                            TrainHubScreen(
                                sport =
                                    currentProfile.sport,
                                trainingPathState =
                                    trainingPathUiState,
                                onContinueFightPath = {
                                    prepareStructuredLesson(
                                        backScreen =
                                            Screen.TRAIN_HUB
                                    )
                                },
                                onFreeTraining = {
                                    clearStructuredSessionState()

                                    playlistSessionCombos =
                                        null

                                    lessonCompleteUiData =
                                        null

                                    screen =
                                        Screen.DURATION
                                },
                                onConditioningOnly = {
                                    startStandaloneConditioning()
                                },
                                onOpenPlaylists = {
                                    clearStructuredSessionState()

                                    lessonCompleteUiData =
                                        null

                                    screen =
                                        Screen.PLAYLISTS
                                }
                            )
                        }

                        Screen.PROGRESS_HUB -> {
                            ProgressHubScreen(
                                sport =
                                    currentProfile.sport,
                                todaySessionCount =
                                    todaySessionCount,
                                todayDurationSeconds =
                                    todayDurationSeconds,
                                trainingPathState =
                                    trainingPathUiState
                            )
                        }

                        Screen.MORE_HUB -> {
                            MoreHubScreen(
                                sport =
                                    currentProfile.sport,
                                hasWeightPlan =
                                    currentProfile
                                        .targetWeightKg !=
                                            null &&
                                            currentProfile
                                                .fightDateEpochDay !=
                                            null,
                                onOpenChallenges = {
                                    selectedChallengeId =
                                        null

                                    screen =
                                        Screen.CHALLENGES

                                    scope.launch {
                                        challengeProgressEngine
                                            .refreshActiveChallenges()
                                    }
                                },
                                onOpenTechniques = {
                                    screen =
                                        Screen.TECHNIQUES
                                },
                                onOpenFightGear = {
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
                                onOpenWeightCut = {
                                    openWeightCut()
                                },
                                onOpenProgressCamera = {
                                    openProgressCamera()
                                },
                                onOpenGlossary = {
                                    screen =
                                        Screen.GLOSSARY
                                }
                            )
                        }

                        Screen.CONDITIONING_MODE -> {
                            val lesson =
                                activeStructuredLessonId
                                    ?.let {
                                            lessonId ->

                                        TrainingCurriculum
                                            .getLesson(
                                                lessonId =
                                                    lessonId
                                            )
                                    }

                            if (
                                lesson == null
                            ) {
                                LaunchedEffect(
                                    Unit
                                ) {
                                    clearStructuredSessionState()

                                    screen =
                                        Screen.HOME
                                }

                                GeneratingScreen()
                            } else {
                                val completedFightPathSessions =
                                    trainingPathUiState
                                        .progress
                                        ?.totalLessonsCompleted
                                        ?.coerceAtLeast(0)
                                        ?: 0

                                ConditioningModePickerScreen(
                                    lessonTitle =
                                        lesson.title,
                                    levelName =
                                        lesson
                                            .level
                                            .displayName,
                                    completedFightPathSessions =
                                        completedFightPathSessions,
                                    onSelectMode = {
                                            mode ->

                                        beginStructuredLesson(
                                            mode =
                                                mode
                                        )
                                    },
                                    onBack = {
                                        val destination =
                                            runCatching {
                                                Screen.valueOf(
                                                    conditioningPickerBackScreenName
                                                )
                                            }.getOrDefault(
                                                Screen.HOME
                                            )

                                        clearStructuredSessionState()

                                        screen =
                                            destination
                                    }
                                )
                            }
                        }

                        Screen.CONDITIONING_STANDALONE -> {
                            val completedFightPathSessions =
                                trainingPathUiState
                                    .progress
                                    ?.totalLessonsCompleted
                                    ?.coerceAtLeast(0)
                                    ?: 0

                            val conditioningPlan =
                                ConditioningSessionPlanner
                                    .create(
                                        mode =
                                            ConditioningMode
                                                .CONDITIONING_ONLY,
                                        completedFightPathSessions =
                                            completedFightPathSessions,
                                        sessionSeed =
                                            completedFightPathSessions *
                                                    31 +
                                                    todaySessionCount
                                    )

                            ConditioningWorkoutScreen(
                                blocks =
                                    conditioningPlan
                                        .allBlocks,
                                title =
                                    "Conditioning Only",
                                onComplete = {
                                        result ->

                                    finishConditioningOnlyAndGoHome(
                                        result =
                                            result
                                    )
                                },
                                onExit = {
                                    clearStructuredSessionState()

                                    screen =
                                        Screen.TRAIN_HUB
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
                                        Screen.MORE_HUB
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

                        Screen.KICK_CHALLENGE_SESSION -> {
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
                                            result =
                                                result
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

                        Screen.FIGHT_GEAR_DEALS -> {
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

                                    if (
                                        shouldRecord
                                    ) {
                                        scope.launch {
                                            val savedSuccessfully =
                                                runCatching {
                                                    fightGearAnalyticsRepository
                                                        .logProductImpression(
                                                            product =
                                                                product
                                                        )
                                                }
                                                    .isSuccess

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
                                                }
                                                    .isSuccess

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

                                        if (
                                            !opened
                                        ) {
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
                                        Screen.MORE_HUB
                                },
                                onOpenAnalytics = {
                                    screen =
                                        Screen
                                            .FIGHT_GEAR_ANALYTICS
                                }
                            )
                        }

                        Screen.FIGHT_GEAR_ANALYTICS -> {
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
                                        Screen.MORE_HUB
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
                                        Screen.TRAIN_HUB
                                },
                                onRunPlaylist = {
                                        playlistCombos ->

                                    clearStructuredSessionState()

                                    lessonCompleteUiData =
                                        null

                                    playlistSessionCombos =
                                        playlistCombos
                                            .toSessionCombos()

                                    if (
                                        secondsPerCombo <=
                                        0
                                    ) {
                                        secondsPerCombo =
                                            30
                                    }

                                    if (
                                        !effectiveIsPro
                                    ) {
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

                                    clearStructuredSessionState()

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
                                    clearStructuredSessionState()

                                    playlistSessionCombos =
                                        null

                                    lessonCompleteUiData =
                                        null

                                    screen =
                                        Screen.TRAIN_HUB
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

                                    clearStructuredSessionState()

                                    lessonCompleteUiData =
                                        null

                                    secondsPerCombo =
                                        seconds

                                    combosPerSession =
                                        comboCount

                                    restSeconds =
                                        if (
                                            effectiveIsPro
                                        ) {
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
                                structuredLesson !=
                                        null -> {

                                    val techniquePlan =
                                        TrainingPathSessionFactory
                                            .create(
                                                lesson =
                                                    structuredLesson
                                            )

                                    val structuredChapter =
                                        TrainingCurriculum
                                            .getChapter(
                                                chapterId =
                                                    structuredLesson
                                                        .chapterId
                                            )

                                    val levelLessons =
                                        TrainingCurriculum
                                            .lessonsForSport(
                                                sport =
                                                    structuredLesson
                                                        .sport,
                                                level =
                                                    structuredLesson
                                                        .level
                                            )

                                    val levelSessionNumber =
                                        (
                                                levelLessons
                                                    .indexOfFirst {
                                                            lesson ->

                                                        lesson.id ==
                                                                structuredLesson
                                                                    .id
                                                    } + 1
                                                )
                                            .coerceAtLeast(
                                                1
                                            )

                                    val levelSessionTotal =
                                        levelLessons
                                            .size
                                            .coerceAtLeast(
                                                1
                                            )

                                    val chapterLessons =
                                        structuredChapter
                                            ?.lessons
                                            ?.sortedBy {
                                                    lesson ->

                                                lesson
                                                    .orderInChapter
                                            }
                                            .orEmpty()

                                    val chapterSessionNumber =
                                        (
                                                chapterLessons
                                                    .indexOfFirst {
                                                            lesson ->

                                                        lesson.id ==
                                                                structuredLesson
                                                                    .id
                                                    } + 1
                                                )
                                            .coerceAtLeast(
                                                1
                                            )

                                    val chapterSessionTotal =
                                        chapterLessons
                                            .size
                                            .coerceAtLeast(
                                                1
                                            )

                                    val fightPathInfo =
                                        FightPathSessionInfo(
                                            levelName =
                                                structuredLesson
                                                    .level
                                                    .displayName,
                                            levelSessionNumber =
                                                levelSessionNumber,
                                            levelSessionTotal =
                                                levelSessionTotal,
                                            chapterTitle =
                                                structuredChapter
                                                    ?.title
                                                    ?: "Fight Path",
                                            chapterSessionNumber =
                                                chapterSessionNumber,
                                            chapterSessionTotal =
                                                chapterSessionTotal,
                                            sessionTitle =
                                                structuredLesson
                                                    .title
                                        )

                                    val completedFightPathSessions =
                                        trainingPathUiState
                                            .progress
                                            ?.totalLessonsCompleted
                                            ?.coerceAtLeast(
                                                0
                                            )
                                            ?: 0

                                    val conditioningMode =
                                        currentConditioningMode()

                                    val conditioningPlan =
                                        ConditioningSessionPlanner
                                            .create(
                                                mode =
                                                    conditioningMode,
                                                completedFightPathSessions =
                                                    completedFightPathSessions,
                                                sessionSeed =
                                                    completedFightPathSessions *
                                                            31 +
                                                            structuredLesson
                                                                .orderInChapter
                                            )

                                    when (
                                        conditioningMode
                                    ) {
                                        ConditioningMode
                                            .TECHNIQUE_ONLY -> {

                                            SessionScreen(
                                                combos =
                                                    techniquePlan
                                                        .combos,
                                                secondsPerCombo =
                                                    techniquePlan
                                                        .secondsPerCombo,
                                                restSeconds =
                                                    techniquePlan
                                                        .restSeconds,
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
                                                },
                                                fightPathInfo =
                                                    fightPathInfo
                                            )
                                        }

                                        ConditioningMode
                                            .TECHNIQUE_PLUS_CONDITIONING -> {

                                            val mixedPlan =
                                                FightPathMixedSessionPlanner
                                                    .create(
                                                        mode =
                                                            conditioningMode,
                                                        techniqueCombos =
                                                            techniquePlan
                                                                .combos,
                                                        conditioningPlan =
                                                            conditioningPlan
                                                    )

                                            FightPathMixedSessionScreen(
                                                plan =
                                                    mixedPlan,
                                                techniqueSecondsPerCombo =
                                                    techniquePlan
                                                        .secondsPerCombo,
                                                techniqueRestSeconds =
                                                    techniquePlan
                                                        .restSeconds,
                                                fightPathInfo =
                                                    fightPathInfo,
                                                onComplete = {
                                                        result ->

                                                    finishStructuredLesson(
                                                        result =
                                                            result
                                                                .techniqueResult,
                                                        conditioningActiveSeconds =
                                                            result
                                                                .conditioningActiveSeconds
                                                    )
                                                },
                                                onExit = {
                                                    exitSessionAndGoHome()
                                                }
                                            )
                                        }

                                        ConditioningMode
                                            .CONDITIONING_ONLY -> {

                                            ConditioningWorkoutScreen(
                                                blocks =
                                                    conditioningPlan
                                                        .allBlocks,
                                                title =
                                                    "Conditioning Only",
                                                onComplete = {
                                                        result ->

                                                    finishConditioningOnlyAndGoHome(
                                                        result =
                                                            result
                                                    )
                                                },
                                                onExit = {
                                                    exitSessionAndGoHome()
                                                }
                                            )
                                        }
                                    }
                                }

                                currentPlaylistSessionCombos !=
                                        null -> {

                                    val effectiveRestSeconds =
                                        if (
                                            effectiveIsPro
                                        ) {
                                            restSeconds
                                                .coerceAtLeast(
                                                    1
                                                )
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
                                                result =
                                                    result
                                            )
                                        }
                                    )
                                }

                                else -> {
                                    val effectiveRestSeconds =
                                        if (
                                            effectiveIsPro
                                        ) {
                                            restSeconds
                                                .coerceAtLeast(
                                                    1
                                                )
                                        } else {
                                            FREE_REST_SECONDS
                                        }

                                    val sessionVm:
                                            SessionViewModel =
                                        viewModel(
                                            key =
                                                "session"
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
                                                currentProfile.sport,
                                            level =
                                                currentProfile.level,
                                            dominance =
                                                currentProfile.dominance,
                                            stance =
                                                currentProfile.stance,
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
                                LaunchedEffect(
                                    Unit
                                ) {
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
                                            nextId !=
                                            null
                                        ) {
                                            prepareStructuredLesson(
                                                lessonId =
                                                    nextId,
                                                backScreen =
                                                    Screen
                                                        .LESSON_COMPLETE
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
                                        Screen.MORE_HUB
                                }
                            )
                        }

                        Screen.PAYWALL -> {
                            PaywallScreen(
                                onProEntitled = {
                                    scope.launch {
                                        userRepository
                                            .setPro(
                                                true
                                            )

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

                                                Screen
                                                    .TRAIN_HUB
                                            }

                                            PaywallDestination
                                                .PROGRESS_CAMERA,
                                            PaywallDestination
                                                .WEIGHT_CUT -> {

                                                Screen
                                                    .MORE_HUB
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
                                            Screen
                                                .WEIGHT_CUT
                                    }
                                },
                                onExit = {
                                    screen =
                                        Screen.MORE_HUB
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
                                        Screen.MORE_HUB
                                }
                            )
                        }

                        Screen.PROGRESS_CAMERA -> {
                            ProgressCameraScreen(
                                repository =
                                    progressPhotoRepository,
                                onExit = {
                                    screen =
                                        Screen.MORE_HUB
                                }
                            )
                        }
                    }
                }

                if (
                    showBottomNavigation
                ) {
                    CornerstoneBottomBar(
                        selectedTab =
                            selectedMainTab,
                        onTabSelected = {
                                tab ->

                            screen =
                                when (
                                    tab
                                ) {
                                    CornerstoneMainTab
                                        .HOME -> {

                                        Screen.HOME
                                    }

                                    CornerstoneMainTab
                                        .FIGHT_PATH -> {

                                        Screen.FIGHT_PATH
                                    }

                                    CornerstoneMainTab
                                        .TRAIN -> {

                                        Screen.TRAIN_HUB
                                    }

                                    CornerstoneMainTab
                                        .PROGRESS -> {

                                        Screen.PROGRESS_HUB
                                    }

                                    CornerstoneMainTab
                                        .MORE -> {

                                        Screen.MORE_HUB
                                    }
                                }
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
                Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color =
                    FightRed
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
                fontSize =
                    15.sp,
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
                Alignment.CenterHorizontally,
            verticalArrangement =
                Arrangement.Center
        ) {
            CircularProgressIndicator(
                color =
                    FightRed
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
                fontSize =
                    16.sp,
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
                fontSize =
                    13.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}