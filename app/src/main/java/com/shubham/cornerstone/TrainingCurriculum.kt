package com.shubham.cornerstone

/**
 * One combo that belongs to a structured Fight Path lesson.
 */
data class TrainingLessonCombo(
    val moves: String,
    val cue: String
)

/**
 * One structured Fight Path lesson.
 */
data class TrainingLessonDefinition(
    val id: String,
    val title: String,
    val subtitle: String,
    val sport: String,
    val level: TrainingPathLevel,
    val chapterId: String,
    val orderInChapter: Int,
    val combos: List<TrainingLessonCombo>,
    val requiredActiveSeconds: Int,
    val xpReward: Int
) {
    init {
        require(id.isNotBlank()) {
            "Lesson ID cannot be blank."
        }

        require(title.isNotBlank()) {
            "Lesson title cannot be blank."
        }

        require(sport.isNotBlank()) {
            "Lesson sport cannot be blank."
        }

        require(chapterId.isNotBlank()) {
            "Chapter ID cannot be blank."
        }

        require(orderInChapter > 0) {
            "Lesson order must be greater than zero."
        }

        require(combos.isNotEmpty()) {
            "A lesson must contain at least one combo."
        }

        require(requiredActiveSeconds > 0) {
            "Required active time must be greater than zero."
        }

        require(xpReward > 0) {
            "XP reward must be greater than zero."
        }
    }
}

/**
 * One chapter belonging to a Fight Path level.
 */
data class TrainingChapterDefinition(
    val id: String,
    val title: String,
    val subtitle: String,
    val sport: String,
    val level: TrainingPathLevel,
    val orderInLevel: Int,
    val lessons: List<TrainingLessonDefinition>
) {
    init {
        require(id.isNotBlank()) {
            "Chapter ID cannot be blank."
        }

        require(title.isNotBlank()) {
            "Chapter title cannot be blank."
        }

        require(sport.isNotBlank()) {
            "Chapter sport cannot be blank."
        }

        require(orderInLevel > 0) {
            "Chapter order must be greater than zero."
        }

        require(lessons.isNotEmpty()) {
            "A chapter must contain lessons."
        }
    }
}

/**
 * Version 21
 *
 * BOXING
 * Beginner = 24 sessions
 * Fundamentals = 42 sessions
 * Developing = 42 sessions
 * Intermediate = 42 sessions
 * Advanced = 42 sessions
 * Total Boxing = 192 sessions
 *
 * KICKBOXING
 * Beginner = 24 sessions
 * Fundamentals = 42 sessions
 * Developing = 42 sessions
 * Intermediate = 42 sessions
 * Advanced = 42 sessions
 * Total Kickboxing = 192 sessions
 *
 * MUAY THAI
 * Beginner = 24 sessions
 * Fundamentals = 42 sessions
 * Developing = 42 sessions
 * Intermediate = 42 sessions
 * Advanced = 42 sessions
 * Total Muay Thai = 192 sessions
 *
 * MMA
 * Beginner = 24 sessions
 * Fundamentals = 42 sessions
 * Developing = 42 sessions
 * Intermediate = 42 sessions
 * Advanced = 42 sessions
 * Total MMA = 192 sessions
 *
 * Total structured Fight Path sessions = 768.
 */
object TrainingCurriculum {

    const val VERSION = 21

    const val SPORT_BOXING = "Boxing"
    const val SPORT_KICKBOXING = "Kickboxing"
    const val SPORT_MUAY_THAI = "Muay Thai"
    const val SPORT_MMA = "MMA"

    val supportedSports: List<String> = listOf(
        SPORT_BOXING,
        SPORT_KICKBOXING,
        SPORT_MUAY_THAI,
        SPORT_MMA
    )

    val chapters: List<TrainingChapterDefinition> =
        BoxingBeginnerCurriculum.chapters +
                BoxingFundamentalsCurriculum.chapters +
                BoxingDevelopingCurriculum.chapters +
                BoxingIntermediateCurriculum.chapters +
                BoxingAdvancedCurriculum.chapters +
                KickboxingBeginnerCurriculum.chapters +
                KickboxingFundamentalsCurriculum.chapters +
                KickboxingDevelopingCurriculum.chapters +
                KickboxingIntermediateCurriculum.chapters +
                KickboxingAdvancedCurriculum.chapters +
                MuayThaiBeginnerCurriculum.chapters +
                MuayThaiFundamentalsCurriculum.chapters +
                MuayThaiDevelopingCurriculum.chapters +
                MuayThaiIntermediateCurriculum.chapters +
                MuayThaiAdvancedCurriculum.chapters +
                MMABeginnerCurriculum.chapters +
                MMAFundamentalsCurriculum.chapters +
                MMADevelopingCurriculum.chapters +
                MMAIntermediateCurriculum.chapters +
                MMAAdvancedCurriculum.chapters

    fun chaptersForSport(
        sport: String,
        level: TrainingPathLevel
    ): List<TrainingChapterDefinition> {
        return chapters
            .filter { chapter ->
                chapter.sport.equals(
                    other = sport,
                    ignoreCase = true
                ) && chapter.level == level
            }
            .sortedBy { chapter ->
                chapter.orderInLevel
            }
    }

    fun lessonsForSport(
        sport: String,
        level: TrainingPathLevel
    ): List<TrainingLessonDefinition> {
        return chaptersForSport(
            sport = sport,
            level = level
        ).flatMap { chapter ->
            chapter.lessons.sortedBy { lesson ->
                lesson.orderInChapter
            }
        }
    }

    fun getChapter(
        chapterId: String
    ): TrainingChapterDefinition? {
        return chapters.firstOrNull { chapter ->
            chapter.id == chapterId
        }
    }

    fun getLesson(
        lessonId: String
    ): TrainingLessonDefinition? {
        return chapters
            .asSequence()
            .flatMap { chapter ->
                chapter.lessons.asSequence()
            }
            .firstOrNull { lesson ->
                lesson.id == lessonId
            }
    }

    fun firstChapter(
        sport: String
    ): TrainingChapterDefinition? {
        return chaptersForSport(
            sport = sport,
            level = TrainingPathLevel.BEGINNER
        ).firstOrNull()
    }

    fun firstLesson(
        sport: String
    ): TrainingLessonDefinition? {
        return firstChapter(sport = sport)
            ?.lessons
            ?.minByOrNull { lesson ->
                lesson.orderInChapter
            }
    }
}