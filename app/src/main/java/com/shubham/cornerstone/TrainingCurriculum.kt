package com.shubham.cornerstone

/**
 * One combo that belongs to a structured Fight Path lesson.
 */
data class TrainingLessonCombo(
    val moves: String,
    val cue: String
)

/**
 * One structured lesson.
 *
 * A lesson teaches a small number of related combinations instead
 * of giving the fighter a completely random session.
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
 * One curriculum chapter.
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
 * Version 1 of Cornerstone's structured Fight Path curriculum.
 *
 * Important:
 * AI will later be used to create controlled variations around
 * unlocked techniques.
 *
 * AI does NOT decide what the fighter learns next.
 */
object TrainingCurriculum {

    const val VERSION = 1

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

    val chapters: List<TrainingChapterDefinition> =
        boxingBeginnerChapters() +
                kickboxingBeginnerChapters() +
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

    private fun boxingBeginnerChapters():
            List<TrainingChapterDefinition> {

        val chapterOneId =
            "boxing_beginner_straight_punches"

        val chapterTwoId =
            "boxing_beginner_head_movement"

        return listOf(
            TrainingChapterDefinition(
                id = chapterOneId,
                title =
                    "Straight Punch Foundation",
                subtitle =
                    "Build your jab, cross and basic exits.",
                sport =
                    SPORT_BOXING,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 1,
                lessons = listOf(
                    lesson(
                        id =
                            "boxing_beginner_straight_1",
                        title =
                            "Jab & Cross",
                        subtitle =
                            "Learn your two most important straight punches.",
                        sport =
                            SPORT_BOXING,
                        chapterId =
                            chapterOneId,
                        order = 1,
                        xp = 100,
                        combos = listOf(
                            combo(
                                "Jab - Cross",
                                "Stay balanced and bring both hands straight back to guard."
                            ),
                            combo(
                                "Double Jab - Cross",
                                "Use the second jab to close distance before the cross."
                            )
                        )
                    ),

                    lesson(
                        id =
                            "boxing_beginner_straight_2",
                        title =
                            "Straight Punch Movement",
                        subtitle =
                            "Punch without becoming stationary.",
                        sport =
                            SPORT_BOXING,
                        chapterId =
                            chapterOneId,
                        order = 2,
                        xp = 110,
                        combos = listOf(
                            combo(
                                "Jab - Cross - Step Back",
                                "Finish the cross, recover your stance and step safely out."
                            ),
                            combo(
                                "Double Jab - Cross - Pivot Left",
                                "Finish your punches before turning out to the left."
                            )
                        )
                    ),

                    lesson(
                        id =
                            "boxing_beginner_straight_3",
                        title =
                            "Straight Punch Review",
                        subtitle =
                            "Connect your punches with controlled movement.",
                        sport =
                            SPORT_BOXING,
                        chapterId =
                            chapterOneId,
                        order = 3,
                        xp = 125,
                        combos = listOf(
                            combo(
                                "Jab - Cross - Jab",
                                "Stay relaxed and keep every punch straight."
                            ),
                            combo(
                                "Jab - Cross - Step Back - Cross",
                                "Create distance, then fire the cross when you return."
                            )
                        )
                    )
                )
            ),

            TrainingChapterDefinition(
                id = chapterTwoId,
                title =
                    "Head Movement",
                subtitle =
                    "Learn exactly where to slip and roll.",
                sport =
                    SPORT_BOXING,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 2,
                lessons = listOf(
                    lesson(
                        id =
                            "boxing_beginner_head_1",
                        title =
                            "Slip Right",
                        subtitle =
                            "Move your head to the right and counter.",
                        sport =
                            SPORT_BOXING,
                        chapterId =
                            chapterTwoId,
                        order = 1,
                        xp = 125,
                        combos = listOf(
                            combo(
                                "Jab - Cross - Slip Right - Cross",
                                "Move your head to the RIGHT, stay balanced and return with the cross."
                            ),
                            combo(
                                "Jab - Slip Right - Cross",
                                "Slip RIGHT without leaning too far, then fire straight."
                            )
                        )
                    ),

                    lesson(
                        id =
                            "boxing_beginner_head_2",
                        title =
                            "Slip Left",
                        subtitle =
                            "Build the opposite defensive direction.",
                        sport =
                            SPORT_BOXING,
                        chapterId =
                            chapterTwoId,
                        order = 2,
                        xp = 125,
                        combos = listOf(
                            combo(
                                "Jab - Cross - Slip Left - Lead Hook",
                                "Move your head to the LEFT and return with the lead hook."
                            ),
                            combo(
                                "Cross - Slip Left - Lead Hook",
                                "Slip LEFT while keeping your eyes forward."
                            )
                        )
                    ),

                    lesson(
                        id =
                            "boxing_beginner_head_3",
                        title =
                            "Roll Left & Right",
                        subtitle =
                            "Learn both directions without guessing.",
                        sport =
                            SPORT_BOXING,
                        chapterId =
                            chapterTwoId,
                        order = 3,
                        xp = 150,
                        combos = listOf(
                            combo(
                                "Jab - Cross - Roll Left - Lead Hook",
                                "Roll to the LEFT under the imaginary hook and return balanced."
                            ),
                            combo(
                                "Lead Hook - Cross - Roll Right - Cross",
                                "Roll to the RIGHT and come back with a straight cross."
                            )
                        )
                    )
                )
            )
        )
    }

    private fun kickboxingBeginnerChapters():
            List<TrainingChapterDefinition> {

        val chapterOneId =
            "kickboxing_beginner_hands_kicks"

        val chapterTwoId =
            "kickboxing_beginner_low_kicks"

        return listOf(
            TrainingChapterDefinition(
                id = chapterOneId,
                title =
                    "Hands Into Kicks",
                subtitle =
                    "Learn to connect basic boxing with kicks.",
                sport =
                    SPORT_KICKBOXING,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 1,
                lessons = listOf(
                    lesson(
                        id =
                            "kickboxing_beginner_hands_1",
                        title =
                            "Jab, Cross & Rear Kick",
                        subtitle =
                            "Start connecting punches to your rear leg.",
                        sport =
                            SPORT_KICKBOXING,
                        chapterId =
                            chapterOneId,
                        order = 1,
                        xp = 100,
                        combos = listOf(
                            combo(
                                "Jab - Rear Kick",
                                "Use the jab to hide the kick and return to stance."
                            ),
                            combo(
                                "Jab - Cross - Rear Kick",
                                "Finish the hands before rotating into the rear kick."
                            )
                        )
                    ),

                    lesson(
                        id =
                            "kickboxing_beginner_hands_2",
                        title =
                            "Lead Kick Entries",
                        subtitle =
                            "Add your lead side without losing balance.",
                        sport =
                            SPORT_KICKBOXING,
                        chapterId =
                            chapterOneId,
                        order = 2,
                        xp = 110,
                        combos = listOf(
                            combo(
                                "Lead Kick - Cross",
                                "Recover your stance before throwing the cross."
                            ),
                            combo(
                                "Jab - Lead Kick - Cross",
                                "Keep the transitions controlled instead of rushing."
                            )
                        )
                    )
                )
            ),

            TrainingChapterDefinition(
                id = chapterTwoId,
                title =
                    "Low Kick Foundation",
                subtitle =
                    "Build simple punch-to-low-kick combinations.",
                sport =
                    SPORT_KICKBOXING,
                level =
                    TrainingPathLevel.BEGINNER,
                orderInLevel = 2,
                lessons = listOf(
                    lesson(
                        id =
                            "kickboxing_beginner_low_1",
                        title =
                            "Rear Low Kick",
                        subtitle =
                            "Use your hands to set up the rear low kick.",
                        sport =
                            SPORT_KICKBOXING,
                        chapterId =
                            chapterTwoId,
                        order = 1,
                        xp = 125,
                        combos = listOf(
                            combo(
                                "Jab - Rear Low Kick",
                                "Touch with the jab before turning through the rear low kick."
                            ),
                            combo(
                                "Jab - Cross - Rear Low Kick",
                                "Let the punches bring your opponent's attention high."
                            )
                        )
                    ),

                    lesson(
                        id =
                            "kickboxing_beginner_low_2",
                        title =
                            "Lead Low Kick",
                        subtitle =
                            "Introduce attacks from the lead side.",
                        sport =
                            SPORT_KICKBOXING,
                        chapterId =
                            chapterTwoId,
                        order = 2,
                        xp = 125,
                        combos = listOf(
                            combo(
                                "Jab - Lead Low Kick",
                                "Stay balanced as you transition from jab to lead low kick."
                            ),
                            combo(
                                "Cross - Lead Hook - Lead Low Kick",
                                "Finish your boxing position before kicking."
                            )
                        )
                    )
                )
            )
        )
    }

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
                                "Jab - Rear Teep",
                                "Use the jab to establish range before extending the rear teep."
                            ),
                            combo(
                                "Double Jab - Rear Teep",
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
                                "Lead Teep - Jab - Cross",
                                "Recover the lead foot before starting your punches."
                            ),
                            combo(
                                "Jab - Lead Teep - Cross",
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
                                "Jab - Cross - Rear Low Kick",
                                "Use your hands to hide the rotation into the kick."
                            ),
                            combo(
                                "Jab - Lead Hook - Rear Low Kick",
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
                                "Lead Check - Cross - Rear Kick",
                                "Set the checking leg down under control before countering."
                            ),
                            combo(
                                "Rear Check - Jab - Cross",
                                "Recover your stance first, then answer with straight punches."
                            )
                        )
                    )
                )
            )
        )
    }

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
                                "Jab - Cross",
                                "Keep your stance stable enough to defend a level change."
                            ),
                            combo(
                                "Double Jab - Cross",
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
                                "Jab - Rear Low Kick",
                                "Kick and recover your stance immediately."
                            ),
                            combo(
                                "Jab - Cross - Rear Low Kick",
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
                                "Jab - Cross - Level Change",
                                "Change levels with your posture controlled and eyes forward."
                            ),
                            combo(
                                "Level Change - Cross - Lead Hook",
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
                                "Sprawl - Jab - Cross",
                                "Recover your stance completely before punching."
                            ),
                            combo(
                                "Jab - Cross - Sprawl - Cross",
                                "Finish the sprawl, rebuild your base and counter."
                            )
                        )
                    )
                )
            )
        )
    }

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