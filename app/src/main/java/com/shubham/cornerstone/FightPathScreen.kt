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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shubham.cornerstone.ui.theme.Charcoal
import com.shubham.cornerstone.ui.theme.FightRed
import com.shubham.cornerstone.ui.theme.InkBlack
import kotlin.math.ceil

@Composable
fun FightPathScreen(
    state: TrainingPathViewModel.UiState,
    onStartCurrentLesson: () -> Unit,
    onBack: () -> Unit
) {
    val progress =
        state.progress

    val currentLevel =
        progress
            ?.level
            ?.let { storedLevel ->
                runCatching {
                    TrainingPathLevel.valueOf(
                        storedLevel
                    )
                }.getOrNull()
            }
            ?: TrainingPathLevel.BEGINNER

    val completedIds =
        state.completedLessonIds

    val sport =
        state.sport

    val currentLevelLessons =
        TrainingCurriculum
            .lessonsForSport(
                sport = sport,
                level = currentLevel
            )

    val currentLevelCompleted =
        currentLevelLessons.count { lesson ->
            lesson.id in completedIds
        }

    val currentLevelProgress =
        if (currentLevelLessons.isEmpty()) {
            0f
        } else {
            (
                    currentLevelCompleted.toFloat() /
                            currentLevelLessons.size.toFloat()
                    ).coerceIn(
                    0f,
                    1f
                )
        }

    val allAvailableLessons =
        TrainingPathLevel.entries
            .flatMap { level ->
                TrainingCurriculum
                    .lessonsForSport(
                        sport = sport,
                        level = level
                    )
            }

    val completedAvailableLessons =
        allAvailableLessons.count { lesson ->
            lesson.id in completedIds
        }

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
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    horizontal = 20.dp
                )
        ) {
            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(
                text = "← Back",
                fontSize = 14.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant,
                modifier = Modifier
                    .clickable {
                        onBack()
                    }
                    .padding(
                        vertical = 8.dp
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(18.dp)
            )

            Text(
                text = "Fight Path",
                fontSize = 36.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    "$sport · ${currentLevel.displayName}",
                fontSize = 14.sp,
                fontWeight =
                    FontWeight.Bold,
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            ProgressOverviewCard(
                level =
                    currentLevel,
                xp =
                    progress?.xp ?: 0,
                completedLevelSessions =
                    currentLevelCompleted,
                totalLevelSessions =
                    currentLevelLessons.size,
                completedTotalSessions =
                    completedAvailableLessons,
                totalAvailableSessions =
                    allAvailableLessons.size,
                streakDays =
                    progress
                        ?.currentStreakDays
                        ?: 0,
                progress =
                    currentLevelProgress
            )

            Spacer(
                modifier =
                    Modifier.height(28.dp)
            )

            Text(
                text = "YOUR ROADMAP",
                fontSize = 11.sp,
                fontWeight =
                    FontWeight.Black,
                letterSpacing = 1.6.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            TrainingPathLevel.entries
                .forEachIndexed {
                        levelIndex,
                        level ->

                    val levelChapters =
                        TrainingCurriculum
                            .chaptersForSport(
                                sport = sport,
                                level = level
                            )

                    val levelLessons =
                        levelChapters
                            .flatMap {
                                it.lessons
                            }

                    val completedLevelLessons =
                        levelLessons.count {
                                lesson ->

                            lesson.id in
                                    completedIds
                        }

                    val levelHasCurriculum =
                        levelLessons.isNotEmpty()

                    val levelComplete =
                        levelHasCurriculum &&
                                completedLevelLessons >=
                                levelLessons.size

                    val isCurrentLevel =
                        level ==
                                currentLevel

                    val isBeforeCurrentLevel =
                        level.ordinal <
                                currentLevel.ordinal

                    val unlocked =
                        isCurrentLevel ||
                                isBeforeCurrentLevel

                    LevelRoadmapCard(
                        level = level,
                        chapters =
                            levelChapters,
                        completedLessonIds =
                            completedIds,
                        currentLessonId =
                            state.currentLesson
                                ?.id,
                        isCurrentLevel =
                            isCurrentLevel,
                        isCompleted =
                            levelComplete,
                        isUnlocked =
                            unlocked,
                        hasCurriculum =
                            levelHasCurriculum,
                        onStartCurrentLesson =
                            onStartCurrentLesson
                    )

                    if (
                        levelIndex <
                        TrainingPathLevel.entries
                            .lastIndex
                    ) {
                        LevelConnector(
                            completed =
                                levelComplete,
                            active =
                                isCurrentLevel
                        )
                    }
                }

            if (
                state.isAvailablePathComplete
            ) {
                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )

                PathFinishedCard(
                    level =
                        currentLevel
                            .displayName
                )
            }

            Spacer(
                modifier =
                    Modifier.height(32.dp)
            )
        }
    }
}

@Composable
private fun ProgressOverviewCard(
    level: TrainingPathLevel,
    xp: Int,
    completedLevelSessions: Int,
    totalLevelSessions: Int,
    completedTotalSessions: Int,
    totalAvailableSessions: Int,
    streakDays: Int,
    progress: Float
) {
    val estimatedWeeks =
        estimatedWeeks(
            sessions =
                totalLevelSessions
        )

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(20.dp),
        color = Charcoal
    ) {
        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {
            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.Top
            ) {
                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {
                    Text(
                        text =
                            "${level.displayName.uppercase()} PROGRESS",
                        fontSize = 10.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing = 1.3.sp,
                        color = FightRed
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(
                        text =
                            "$completedLevelSessions of $totalLevelSessions sessions",
                        fontSize = 21.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onBackground
                    )
                }

                Text(
                    text = "$xp XP",
                    fontSize = 16.sp,
                    fontWeight =
                        FontWeight.Black,
                    color = FightRed
                )
            }

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            SimpleProgressBar(
                progress = progress,
                height = 7
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            if (
                totalLevelSessions > 0
            ) {
                Text(
                    text =
                        "$totalLevelSessions structured sessions · about $estimatedWeeks weeks at 3 sessions/week",
                    fontSize = 11.sp,
                    lineHeight = 16.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )
            }

            Text(
                text =
                    "$completedTotalSessions of $totalAvailableSessions currently available Fight Path sessions completed",
                fontSize = 10.sp,
                lineHeight = 15.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                        .copy(alpha = 0.72f)
            )

            Spacer(
                modifier =
                    Modifier.height(14.dp)
            )

            Text(
                text =
                    if (streakDays > 0) {
                        "🔥 $streakDays day streak"
                    } else {
                        "No active streak yet"
                    },
                fontSize = 12.sp,
                fontWeight =
                    FontWeight.Bold,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LevelRoadmapCard(
    level: TrainingPathLevel,
    chapters: List<TrainingChapterDefinition>,
    completedLessonIds: Set<String>,
    currentLessonId: String?,
    isCurrentLevel: Boolean,
    isCompleted: Boolean,
    isUnlocked: Boolean,
    hasCurriculum: Boolean,
    onStartCurrentLesson: () -> Unit
) {
    val levelLessons =
        chapters.flatMap {
            it.lessons
        }

    val completedCount =
        levelLessons.count {
                lesson ->

            lesson.id in
                    completedLessonIds
        }

    val totalCount =
        levelLessons.size

    val progressFraction =
        if (totalCount <= 0) {
            0f
        } else {
            (
                    completedCount.toFloat() /
                            totalCount.toFloat()
                    ).coerceIn(
                    0f,
                    1f
                )
        }

    val estimatedWeeks =
        estimatedWeeks(
            sessions =
                totalCount
        )

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(22.dp),
        color =
            when {
                isCurrentLevel -> {
                    FightRed.copy(
                        alpha = 0.08f
                    )
                }

                isCompleted -> {
                    FightRed.copy(
                        alpha = 0.05f
                    )
                }

                else -> {
                    Charcoal
                }
            }
    ) {
        Column(
            modifier =
                Modifier.padding(18.dp)
        ) {
            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {
                    Text(
                        text =
                            when {
                                isCompleted -> {
                                    "LEVEL COMPLETE"
                                }

                                isCurrentLevel -> {
                                    "CURRENT LEVEL"
                                }

                                !hasCurriculum -> {
                                    "COMING SOON"
                                }

                                else -> {
                                    "LOCKED"
                                }
                            },
                        fontSize = 10.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing =
                            1.3.sp,
                        color =
                            when {
                                isCompleted ||
                                        isCurrentLevel -> {
                                    FightRed
                                }

                                else -> {
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                                }
                            }
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(
                        text =
                            level.displayName,
                        fontSize = 25.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            if (
                                isUnlocked ||
                                isCurrentLevel
                            ) {
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                            } else {
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                            }
                    )
                }

                LevelStatusCircle(
                    completed =
                        isCompleted,
                    current =
                        isCurrentLevel,
                    hasCurriculum =
                        hasCurriculum,
                    unlocked =
                        isUnlocked
                )
            }

            if (hasCurriculum) {
                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {
                    Text(
                        text =
                            "$completedCount / $totalCount sessions",
                        fontSize = 11.sp,
                        color =
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                    )

                    if (
                        isCurrentLevel
                    ) {
                        Text(
                            text =
                                "In progress",
                            fontSize = 11.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color = FightRed
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(8.dp)
                )

                SimpleProgressBar(
                    progress =
                        progressFraction,
                    height = 5
                )

                Spacer(
                    modifier =
                        Modifier.height(9.dp)
                )

                Text(
                    text =
                        "$totalCount sessions · about $estimatedWeeks weeks at 3 sessions/week",
                    fontSize = 10.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                            .copy(alpha = 0.78f)
                )

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )

                chapters
                    .forEachIndexed {
                            chapterIndex,
                            chapter ->

                        ChapterRoadmapCard(
                            chapter =
                                chapter,
                            chapterNumber =
                                chapterIndex + 1,
                            completedLessonIds =
                                completedLessonIds,
                            currentLessonId =
                                if (
                                    isCurrentLevel
                                ) {
                                    currentLessonId
                                } else {
                                    null
                                },
                            levelUnlocked =
                                isUnlocked,
                            onStartCurrentLesson =
                                onStartCurrentLesson
                        )

                        if (
                            chapterIndex <
                            chapters.lastIndex
                        ) {
                            Spacer(
                                modifier =
                                    Modifier.height(
                                        12.dp
                                    )
                            )
                        }
                    }
            } else {
                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Text(
                    text =
                        "More structured sessions will unlock here as the Fight Path expands.",
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color =
                        MaterialTheme
                            .colorScheme
                            .onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun LevelStatusCircle(
    completed: Boolean,
    current: Boolean,
    hasCurriculum: Boolean,
    unlocked: Boolean
) {
    Surface(
        modifier =
            Modifier.size(42.dp),
        shape =
            CircleShape,
        color =
            when {
                completed -> {
                    FightRed
                }

                current -> {
                    FightRed.copy(
                        alpha = 0.16f
                    )
                }

                else -> {
                    Color.White.copy(
                        alpha = 0.05f
                    )
                }
            }
    ) {
        Box(
            contentAlignment =
                Alignment.Center
        ) {
            Text(
                text =
                    when {
                        completed -> {
                            "✓"
                        }

                        current -> {
                            "▶"
                        }

                        !hasCurriculum -> {
                            "…"
                        }

                        unlocked -> {
                            "✓"
                        }

                        else -> {
                            "🔒"
                        }
                    },
                fontSize =
                    when {
                        completed -> {
                            18.sp
                        }

                        current -> {
                            14.sp
                        }

                        else -> {
                            15.sp
                        }
                    },
                fontWeight =
                    FontWeight.Black,
                color =
                    when {
                        completed -> {
                            Color.White
                        }

                        current -> {
                            FightRed
                        }

                        else -> {
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                        }
                    }
            )
        }
    }
}

@Composable
private fun LevelConnector(
    completed: Boolean,
    active: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        contentAlignment =
            Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(
                    width = 3.dp,
                    height = 26.dp
                )
                .clip(
                    RoundedCornerShape(
                        999.dp
                    )
                )
                .background(
                    when {
                        completed -> {
                            FightRed
                        }

                        active -> {
                            FightRed.copy(
                                alpha = 0.45f
                            )
                        }

                        else -> {
                            Color.White.copy(
                                alpha = 0.08f
                            )
                        }
                    }
                )
        )
    }
}

@Composable
private fun ChapterRoadmapCard(
    chapter: TrainingChapterDefinition,
    chapterNumber: Int,
    completedLessonIds: Set<String>,
    currentLessonId: String?,
    levelUnlocked: Boolean,
    onStartCurrentLesson: () -> Unit
) {
    val completedCount =
        chapter.lessons.count {
                lesson ->

            lesson.id in
                    completedLessonIds
        }

    val chapterComplete =
        completedCount >=
                chapter.lessons.size

    val chapterContainsCurrentLesson =
        chapter.lessons.any {
                lesson ->

            lesson.id ==
                    currentLessonId
        }

    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(18.dp),
        color =
            if (chapterComplete) {
                FightRed.copy(
                    alpha = 0.07f
                )
            } else {
                Color.Black.copy(
                    alpha = 0.17f
                )
            }
    ) {
        Column(
            modifier =
                Modifier.padding(16.dp)
        ) {
            Row(
                modifier =
                    Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.SpaceBetween,
                verticalAlignment =
                    Alignment.CenterVertically
            ) {
                Column(
                    modifier =
                        Modifier.weight(1f)
                ) {
                    Text(
                        text =
                            "CHAPTER $chapterNumber",
                        fontSize = 9.sp,
                        fontWeight =
                            FontWeight.Black,
                        letterSpacing =
                            1.2.sp,
                        color =
                            when {
                                chapterComplete -> {
                                    FightRed
                                }

                                chapterContainsCurrentLesson -> {
                                    FightRed
                                }

                                else -> {
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                                }
                            }
                    )

                    Spacer(
                        modifier =
                            Modifier.height(4.dp)
                    )

                    Text(
                        text =
                            chapter.title,
                        fontSize = 19.sp,
                        lineHeight = 23.sp,
                        fontWeight =
                            FontWeight.Black,
                        color =
                            if (
                                levelUnlocked
                            ) {
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                            } else {
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                            }
                    )
                }

                if (chapterComplete) {
                    Surface(
                        modifier =
                            Modifier.size(32.dp),
                        shape =
                            CircleShape,
                        color =
                            FightRed.copy(
                                alpha = 0.16f
                            )
                    ) {
                        Box(
                            contentAlignment =
                                Alignment.Center
                        ) {
                            Text(
                                text = "✓",
                                fontSize = 17.sp,
                                fontWeight =
                                    FontWeight.Black,
                                color = FightRed
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(5.dp)
            )

            Text(
                text =
                    chapter.subtitle,
                fontSize = 11.sp,
                lineHeight = 17.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )

            Spacer(
                modifier =
                    Modifier.height(7.dp)
            )

            Text(
                text =
                    "$completedCount / ${chapter.lessons.size} sessions completed",
                fontSize = 10.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
                        .copy(alpha = 0.76f)
            )

            Spacer(
                modifier =
                    Modifier.height(13.dp)
            )

            chapter.lessons
                .sortedBy {
                    it.orderInChapter
                }
                .forEachIndexed {
                        index,
                        lesson ->

                    val completed =
                        lesson.id in
                                completedLessonIds

                    val current =
                        lesson.id ==
                                currentLessonId

                    val locked =
                        !completed &&
                                !current

                    LessonRoadmapRow(
                        lesson =
                            lesson,
                        sessionNumber =
                            index + 1,
                        completed =
                            completed,
                        current =
                            current,
                        locked =
                            locked,
                        levelUnlocked =
                            levelUnlocked,
                        onStart =
                            if (
                                current &&
                                levelUnlocked
                            ) {
                                onStartCurrentLesson
                            } else {
                                null
                            }
                    )

                    if (
                        index <
                        chapter.lessons
                            .lastIndex
                    ) {
                        Spacer(
                            modifier =
                                Modifier.height(
                                    8.dp
                                )
                        )
                    }
                }
        }
    }
}

@Composable
private fun LessonRoadmapRow(
    lesson: TrainingLessonDefinition,
    sessionNumber: Int,
    completed: Boolean,
    current: Boolean,
    locked: Boolean,
    levelUnlocked: Boolean,
    onStart: (() -> Unit)?
) {
    val effectiveLocked =
        locked ||
                !levelUnlocked

    val containerColor =
        when {
            current &&
                    levelUnlocked -> {

                FightRed.copy(
                    alpha = 0.14f
                )
            }

            else -> {
                Color.Black.copy(
                    alpha = 0.18f
                )
            }
        }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (
                    current &&
                    levelUnlocked &&
                    onStart != null
                ) {
                    Modifier.clickable {
                        onStart()
                    }
                } else {
                    Modifier
                }
            ),
        shape =
            RoundedCornerShape(15.dp),
        color =
            containerColor
    ) {
        Row(
            modifier =
                Modifier.padding(14.dp),
            verticalAlignment =
                Alignment.CenterVertically
        ) {
            Surface(
                modifier =
                    Modifier.size(36.dp),
                shape =
                    CircleShape,
                color =
                    when {
                        completed -> {
                            FightRed
                        }

                        current &&
                                levelUnlocked -> {

                            FightRed.copy(
                                alpha = 0.18f
                            )
                        }

                        else -> {
                            Color.White.copy(
                                alpha = 0.05f
                            )
                        }
                    }
            ) {
                Box(
                    contentAlignment =
                        Alignment.Center
                ) {
                    Text(
                        text =
                            when {
                                completed -> {
                                    "✓"
                                }

                                effectiveLocked -> {
                                    "🔒"
                                }

                                else -> {
                                    sessionNumber
                                        .toString()
                                }
                            },
                        fontSize =
                            if (
                                effectiveLocked
                            ) {
                                14.sp
                            } else {
                                13.sp
                            },
                        fontWeight =
                            FontWeight.Black,
                        color =
                            when {
                                completed -> {
                                    Color.White
                                }

                                current &&
                                        levelUnlocked -> {

                                    FightRed
                                }

                                else -> {
                                    MaterialTheme
                                        .colorScheme
                                        .onSurfaceVariant
                                }
                            }
                    )
                }
            }

            Spacer(
                modifier =
                    Modifier.size(12.dp)
            )

            Column(
                modifier =
                    Modifier.weight(1f)
            ) {
                Text(
                    text =
                        lesson.title,
                    fontSize = 14.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color =
                        when {
                            completed -> {
                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                            }

                            current &&
                                    levelUnlocked -> {

                                MaterialTheme
                                    .colorScheme
                                    .onBackground
                            }

                            else -> {
                                MaterialTheme
                                    .colorScheme
                                    .onSurfaceVariant
                            }
                        }
                )

                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )

                Text(
                    text =
                        when {
                            completed -> {
                                "Session completed"
                            }

                            current &&
                                    levelUnlocked -> {

                                "+${lesson.xpReward} XP · Train now"
                            }

                            !levelUnlocked -> {
                                "Complete the previous level to unlock"
                            }

                            else -> {
                                "Complete previous session to unlock"
                            }
                        },
                    fontSize = 11.sp,
                    color =
                        if (
                            current &&
                            levelUnlocked
                        ) {
                            FightRed
                        } else {
                            MaterialTheme
                                .colorScheme
                                .onSurfaceVariant
                        }
                )
            }

            if (
                current &&
                levelUnlocked
            ) {
                Text(
                    text = "→",
                    fontSize = 18.sp,
                    fontWeight =
                        FontWeight.Black,
                    color = FightRed
                )
            }
        }
    }
}

@Composable
private fun PathFinishedCard(
    level: String
) {
    Surface(
        modifier =
            Modifier.fillMaxWidth(),
        shape =
            RoundedCornerShape(18.dp),
        color =
            FightRed.copy(
                alpha = 0.11f
            )
    ) {
        Column(
            modifier =
                Modifier.padding(18.dp),
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {
            Text(
                text = "✓",
                fontSize = 28.sp,
                fontWeight =
                    FontWeight.Black,
                color = FightRed
            )

            Spacer(
                modifier =
                    Modifier.height(6.dp)
            )

            Text(
                text =
                    "$level path complete",
                fontSize = 18.sp,
                fontWeight =
                    FontWeight.Black,
                color =
                    MaterialTheme
                        .colorScheme
                        .onBackground
            )

            Spacer(
                modifier =
                    Modifier.height(4.dp)
            )

            Text(
                text =
                    "Every currently available structured session at this level is complete.",
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color =
                    MaterialTheme
                        .colorScheme
                        .onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SimpleProgressBar(
    progress: Float,
    height: Int
) {
    val safeProgress =
        progress.coerceIn(
            0f,
            1f
        )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(
                RoundedCornerShape(
                    999.dp
                )
            )
            .background(
                Color.White.copy(
                    alpha = 0.07f
                )
            )
    ) {
        if (
            safeProgress > 0f
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(
                        fraction =
                            safeProgress
                    )
                    .height(height.dp)
                    .clip(
                        RoundedCornerShape(
                            999.dp
                        )
                    )
                    .background(
                        FightRed
                    )
            )
        }
    }
}

private fun estimatedWeeks(
    sessions: Int
): Int {

    if (sessions <= 0) {
        return 0
    }

    return ceil(
        sessions / 3.0
    ).toInt()
}