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
 * One Fight Path curriculum chapter.
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
 * Version 11 of Cornerstone's structured Fight Path curriculum.
 *
 * BOXING:
 *
 * Beginner      = 24 sessions
 * Fundamentals  = 42 sessions
 * Developing    = 42 sessions
 * Intermediate  = 42 sessions
 * Advanced      = 42 sessions
 *
 * TOTAL BOXING FIGHT PATH:
 * 192 structured sessions.
 *
 * KICKBOXING:
 *
 * Beginner      = 24 sessions
 * Fundamentals  = 42 sessions
 * Developing    = 42 sessions
 * Intermediate  = 42 sessions
 * Advanced      = 42 sessions
 *
 * TOTAL KICKBOXING FIGHT PATH:
 * 192 structured sessions.
 *
 * Muay Thai and MMA currently retain their temporary
 * Beginner prototype paths.
 */
object TrainingCurriculum {

    const val VERSION = 11

    const val SPORT_BOXING =
        "Boxing"

    const val SPORT_KICKBOXING =
        "Kickboxing"

    const val SPORT_MUAY_THAI =
        "Muay Thai"

    const val SPORT_MMA =
        "MMA"

    private const val BEGINNER_LESSON_ACTIVE_SECONDS =
        5 * 60

    val supportedSports =
        listOf(
            SPORT_BOXING,
            SPORT_KICKBOXING,
            SPORT_MUAY_THAI,
            SPORT_MMA
        )

    /**
     * Boxing and Kickboxing now have complete
     * five-stage Fight Path curricula.
     *
     * Muay Thai and MMA currently retain their temporary
     * Beginner prototype content.
     */
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
                muayThaiBeginnerChapters() +
                mmaBeginnerChapters()

    fun chaptersForSport(
        sport: String,
        level: TrainingPathLevel
    ): List<TrainingChapterDefinition> {

        return chapters
            .filter { chapter ->
                chapter.sport.equals(
                    sport,
                    ignoreCase = true
                ) &&
                        chapter.level == level
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
            chapter.lessons
                .sortedBy { lesson ->
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

        return firstChapter(
            sport = sport
        )?.lessons
            ?.minByOrNull { lesson ->
                lesson.orderInChapter
            }
    }

    // ---------------------------------------------------------
    // MUAY THAI BEGINNER PROTOTYPE
    // ---------------------------------------------------------

    private fun muayThaiBeginnerChapters():
            List<TrainingChapterDefinition> {

        val chapterOneId =
            "muaythai_beginner_long_range"

        val chapterTwoId =
            "muaythai_beginner_kicks_checks"

        return listOf(
            TrainingChapterDefinition(
                id = chapterOneId,
                title =
                    "Muay Thai Range",
                subtitle =
                    "Build punches, teeps and basic distance control.",
                sport =
                    SPORT_MUAY_THAI,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 1,
                lessons = listOf(
                    lesson(
                        id =
                            "muaythai_beginner_range_1",
                        title =
                            "Jab & Rear Teep",
                        subtitle =
                            "Learn two simple long-range weapons.",
                        sport =
                            SPORT_MUAY_THAI,
                        chapterId =
                            chapterOneId,
                        order = 1,
                        xp = 100,
                        combos = listOf(
                            combo(
                                moves =
                                    "Jab - Rear Teep",
                                cue =
                                    "Use the jab to establish range before extending the rear teep."
                            ),
                            combo(
                                moves =
                                    "Double Jab - Rear Teep",
                                cue =
                                    "Stay tall and recover your stance after the teep."
                            )
                        )
                    ),
                    lesson(
                        id =
                            "muaythai_beginner_range_2",
                        title =
                            "Lead Teep",
                        subtitle =
                            "Control distance from your lead side.",
                        sport =
                            SPORT_MUAY_THAI,
                        chapterId =
                            chapterOneId,
                        order = 2,
                        xp = 110,
                        combos = listOf(
                            combo(
                                moves =
                                    "Lead Teep - Jab - Cross",
                                cue =
                                    "Recover the lead foot before starting your punches."
                            ),
                            combo(
                                moves =
                                    "Jab - Lead Teep - Cross",
                                cue =
                                    "Keep your posture tall through the teep."
                            )
                        )
                    )
                )
            ),
            TrainingChapterDefinition(
                id = chapterTwoId,
                title =
                    "Kicks & Checks",
                subtitle =
                    "Attack the leg and learn to answer after defending.",
                sport =
                    SPORT_MUAY_THAI,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 2,
                lessons = listOf(
                    lesson(
                        id =
                            "muaythai_beginner_kicks_1",
                        title =
                            "Rear Low Kick",
                        subtitle =
                            "Set up your low kick with punches.",
                        sport =
                            SPORT_MUAY_THAI,
                        chapterId =
                            chapterTwoId,
                        order = 1,
                        xp = 125,
                        combos = listOf(
                            combo(
                                moves =
                                    "Jab - Cross - Rear Low Kick",
                                cue =
                                    "Use your hands to hide the rotation into the kick."
                            ),
                            combo(
                                moves =
                                    "Jab - Lead Hook - Rear Low Kick",
                                cue =
                                    "Stay balanced after the hook before kicking."
                            )
                        )
                    ),
                    lesson(
                        id =
                            "muaythai_beginner_kicks_2",
                        title =
                            "Check & Counter",
                        subtitle =
                            "Start defending kicks and answering immediately.",
                        sport =
                            SPORT_MUAY_THAI,
                        chapterId =
                            chapterTwoId,
                        order = 2,
                        xp = 150,
                        combos = listOf(
                            combo(
                                moves =
                                    "Lead Check - Cross - Rear Kick",
                                cue =
                                    "Set the checking leg down under control before countering."
                            ),
                            combo(
                                moves =
                                    "Rear Check - Jab - Cross",
                                cue =
                                    "Recover your stance first, then answer with straight punches."
                            )
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // MMA BEGINNER PROTOTYPE
    // ---------------------------------------------------------

    private fun mmaBeginnerChapters():
            List<TrainingChapterDefinition> {

        val chapterOneId =
            "mma_beginner_striking"

        val chapterTwoId =
            "mma_beginner_strike_wrestle"

        return listOf(
            TrainingChapterDefinition(
                id = chapterOneId,
                title =
                    "MMA Striking Base",
                subtitle =
                    "Build simple striking while staying ready to wrestle.",
                sport =
                    SPORT_MMA,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 1,
                lessons = listOf(
                    lesson(
                        id =
                            "mma_beginner_striking_1",
                        title =
                            "Jab & Cross",
                        subtitle =
                            "Build a stable striking base for MMA.",
                        sport =
                            SPORT_MMA,
                        chapterId =
                            chapterOneId,
                        order = 1,
                        xp = 100,
                        combos = listOf(
                            combo(
                                moves =
                                    "Jab - Cross",
                                cue =
                                    "Keep your stance stable enough to defend a level change."
                            ),
                            combo(
                                moves =
                                    "Double Jab - Cross",
                                cue =
                                    "Do not overreach and lose your wrestling base."
                            )
                        )
                    ),
                    lesson(
                        id =
                            "mma_beginner_striking_2",
                        title =
                            "Punch & Low Kick",
                        subtitle =
                            "Connect basic boxing with low kicks.",
                        sport =
                            SPORT_MMA,
                        chapterId =
                            chapterOneId,
                        order = 2,
                        xp = 110,
                        combos = listOf(
                            combo(
                                moves =
                                    "Jab - Rear Low Kick",
                                cue =
                                    "Kick and recover your stance immediately."
                            ),
                            combo(
                                moves =
                                    "Jab - Cross - Rear Low Kick",
                                cue =
                                    "Stay ready to defend after the kick."
                            )
                        )
                    )
                )
            ),
            TrainingChapterDefinition(
                id = chapterTwoId,
                title =
                    "Strike Into Wrestling",
                subtitle =
                    "Introduce level changes and basic takedown defense.",
                sport =
                    SPORT_MMA,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 2,
                lessons = listOf(
                    lesson(
                        id =
                            "mma_beginner_wrestle_1",
                        title =
                            "Level Change",
                        subtitle =
                            "Blend striking with your first wrestling movement.",
                        sport =
                            SPORT_MMA,
                        chapterId =
                            chapterTwoId,
                        order = 1,
                        xp = 125,
                        combos = listOf(
                            combo(
                                moves =
                                    "Jab - Cross - Level Change",
                                cue =
                                    "Change levels with your posture controlled and eyes forward."
                            ),
                            combo(
                                moves =
                                    "Level Change - Cross - Lead Hook",
                                cue =
                                    "Sell the wrestling threat before coming back upstairs."
                            )
                        )
                    ),
                    lesson(
                        id =
                            "mma_beginner_wrestle_2",
                        title =
                            "Sprawl & Counter",
                        subtitle =
                            "Defend an imaginary shot and return to offense.",
                        sport =
                            SPORT_MMA,
                        chapterId =
                            chapterTwoId,
                        order = 2,
                        xp = 150,
                        combos = listOf(
                            combo(
                                moves =
                                    "Sprawl - Jab - Cross",
                                cue =
                                    "Recover your stance completely before punching."
                            ),
                            combo(
                                moves =
                                    "Jab - Cross - Sprawl - Cross",
                                cue =
                                    "Finish the sprawl, rebuild your base and counter."
                            )
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // PROTOTYPE SPORT HELPERS
    // ---------------------------------------------------------

    private fun lesson(
        id: String,
        title: String,
        subtitle: String,
        sport: String,
        chapterId: String,
        order: Int,
        xp: Int,
        combos: List<TrainingLessonCombo>
    ): TrainingLessonDefinition {

        return TrainingLessonDefinition(
            id = id,
            title = title,
            subtitle = subtitle,
            sport = sport,
            level =
                TrainingPathLevel.BEGINNER,
            chapterId = chapterId,
            orderInChapter = order,
            combos = combos,
            requiredActiveSeconds =
                BEGINNER_LESSON_ACTIVE_SECONDS,
            xpReward = xp
        )
    }

    private fun combo(
        moves: String,
        cue: String
    ): TrainingLessonCombo {

        return TrainingLessonCombo(
            moves = moves,
            cue = cue
        )
    }
}