package com.shubham.cornerstone

/**
 * Boxing Beginner Fight Path
 *
 * 6 chapters
 * 4 structured sessions per chapter
 * 24 total sessions
 *
 * At roughly 3 Fight Path sessions per week,
 * this represents around 8 weeks of consistent work.
 *
 * Existing lesson IDs from the first prototype are
 * intentionally preserved where possible so existing
 * user progress can remain valid.
 */
object BoxingBeginnerCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_BOXING

    val chapters:
            List<TrainingChapterDefinition> =
        listOf(
            straightPunchFoundation(),
            movementAndDistance(),
            hooksAndUppercuts(),
            basicDefence(),
            headMovement(),
            beginnerAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1
    // ---------------------------------------------------------

    private fun straightPunchFoundation():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_beginner_straight_punches"

        return TrainingChapterDefinition(
            id = chapterId,
            title =
                "Straight Punch Foundation",
            subtitle =
                "Build your jab, cross, stance recovery and basic exits.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 1,
            lessons = listOf(

                /*
                 * Existing ID preserved.
                 */
                lesson(
                    id =
                        "boxing_beginner_straight_1",
                    title =
                        "Jab & Cross",
                    subtitle =
                        "Build clean straight punches while staying balanced.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 100,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross",
                            cue =
                                "Keep your chin protected and return both hands directly to guard."
                        ),
                        combo(
                            moves =
                                "Double Jab - Cross",
                            cue =
                                "Use the second jab to close distance without falling forward."
                        )
                    )
                ),

                /*
                 * Existing ID preserved.
                 */
                lesson(
                    id =
                        "boxing_beginner_straight_2",
                    title =
                        "Straight Punch Movement",
                    subtitle =
                        "Punch without becoming stationary.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 5,
                    xp = 110,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Step Back",
                            cue =
                                "Finish the cross, rebuild your stance and step safely out."
                        ),
                        combo(
                            moves =
                                "Double Jab - Cross - Pivot Left",
                            cue =
                                "Finish your punches before turning out to the left."
                        )
                    )
                ),

                /*
                 * Existing ID preserved.
                 */
                lesson(
                    id =
                        "boxing_beginner_straight_3",
                    title =
                        "Straight Punch Review",
                    subtitle =
                        "Repeat the basics under slightly longer combinations.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 125,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Jab",
                            cue =
                                "Stay relaxed and keep every punch travelling straight."
                        ),
                        combo(
                            moves =
                                "Jab - Cross - Step Back - Cross",
                            cue =
                                "Create distance first, then return with the cross."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_straight_4",
                    title =
                        "Straight Punch Checkpoint",
                    subtitle =
                        "Show control before moving deeper into the Beginner path.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 140,
                    combos = listOf(
                        combo(
                            moves =
                                "Double Jab - Cross - Step Back",
                            cue =
                                "Keep your feet underneath you through the entire sequence."
                        ),
                        combo(
                            moves =
                                "Jab - Cross - Jab - Cross",
                            cue =
                                "Maintain the same stance width and return every hand to guard."
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2
    // ---------------------------------------------------------

    private fun movementAndDistance():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_beginner_movement_distance"

        return TrainingChapterDefinition(
            id = chapterId,
            title =
                "Movement & Distance",
            subtitle =
                "Learn to enter, exit and change position without crossing your feet.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 2,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_beginner_movement_1",
                    title =
                        "Step In & Step Out",
                    subtitle =
                        "Learn to control distance around your straight punches.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 125,
                    combos = listOf(
                        combo(
                            moves =
                                "Step In - Jab - Cross - Step Out",
                            cue =
                                "Move your feet first and stay balanced before punching."
                        ),
                        combo(
                            moves =
                                "Jab - Cross - Step Back - Jab",
                            cue =
                                "Exit under control and re-enter behind the jab."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_movement_2",
                    title =
                        "Lateral Movement",
                    subtitle =
                        "Start moving sideways instead of only forward and backward.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 130,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Step Left - Cross",
                            cue =
                                "Take a small controlled step left before firing the cross."
                        ),
                        combo(
                            moves =
                                "Jab - Step Right - Jab - Cross",
                            cue =
                                "Stay in stance while moving right and avoid bringing your feet together."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_movement_3",
                    title =
                        "Pivot Basics",
                    subtitle =
                        "Learn to change your angle after punching.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 135,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Pivot Left",
                            cue =
                                "Finish the cross before pivoting and keep your guard high."
                        ),
                        combo(
                            moves =
                                "Double Jab - Pivot Left - Cross",
                            cue =
                                "Use the jab to occupy the opponent before changing angle."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_movement_4",
                    title =
                        "Distance Checkpoint",
                    subtitle =
                        "Combine entries, exits and angle changes.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 150,
                    combos = listOf(
                        combo(
                            moves =
                                "Step In - Jab - Cross - Pivot Left",
                            cue =
                                "Enter balanced, finish the punches and then change angle."
                        ),
                        combo(
                            moves =
                                "Jab - Cross - Step Back - Jab - Cross",
                            cue =
                                "Create space before returning with another straight attack."
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3
    // ---------------------------------------------------------

    private fun hooksAndUppercuts():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_beginner_hooks_uppercuts"

        return TrainingChapterDefinition(
            id = chapterId,
            title =
                "Hooks & Uppercuts",
            subtitle =
                "Add short-range punches without losing your stance or guard.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 3,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_beginner_hooks_1",
                    title =
                        "Lead Hook",
                    subtitle =
                        "Build your first compact hook.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 135,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Lead Hook",
                            cue =
                                "Rotate through the lead side while keeping the hook compact."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Hook",
                            cue =
                                "Recover the cross before turning into the hook."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_hooks_2",
                    title =
                        "Rear Hook Introduction",
                    subtitle =
                        "Add your rear hook while keeping the combination controlled.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 140,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Lead Hook - Rear Hook",
                            cue =
                                "Keep both hooks compact and avoid swinging your arms wide."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Hook - Rear Hook",
                            cue =
                                "Let your hips rotate while your feet remain stable."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_uppercut_1",
                    title =
                        "Uppercut Basics",
                    subtitle =
                        "Introduce straight-to-uppercut combinations.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 145,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Lead Uppercut",
                            cue =
                                "Use your legs slightly and do not drop the lead hand before the uppercut."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Uppercut - Cross",
                            cue =
                                "Stay compact and bring every punch back to guard."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_hooks_4",
                    title =
                        "Short Punch Checkpoint",
                    subtitle =
                        "Connect straights, hooks and uppercuts together.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 160,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Lead Hook - Cross",
                            cue =
                                "Keep the hook tight and fire the final cross straight down the middle."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Uppercut - Lead Hook - Cross",
                            cue =
                                "Stay balanced while changing punch angles."
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4
    // ---------------------------------------------------------

    private fun basicDefence():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_beginner_basic_defence"

        return TrainingChapterDefinition(
            id = chapterId,
            title =
                "Basic Defence",
            subtitle =
                "Learn simple defensive reactions before adding more advanced counters.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 4,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_beginner_defence_1",
                    title =
                        "High Guard",
                    subtitle =
                        "Recover your hands after punching and defend immediately.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 135,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - High Guard - Cross",
                            cue =
                                "Bring both gloves back to your temples before returning with the cross."
                        ),
                        combo(
                            moves =
                                "Double Jab - High Guard - Jab - Cross",
                            cue =
                                "Defend first, rebuild your stance and then return to offense."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_defence_2",
                    title =
                        "Step Back Defence",
                    subtitle =
                        "Use distance as defence instead of standing still.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 140,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Step Back - Cross",
                            cue =
                                "Move just far enough to create space before countering."
                        ),
                        combo(
                            moves =
                                "Lead Hook - Cross - Step Back - Jab",
                            cue =
                                "Finish the combination, exit safely and return behind the jab."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_defence_3",
                    title =
                        "Parry & Return",
                    subtitle =
                        "Introduce a simple straight-punch parry.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 145,
                    combos = listOf(
                        combo(
                            moves =
                                "Lead Parry - Jab - Cross",
                            cue =
                                "Use a small parry movement and immediately return your hand to guard."
                        ),
                        combo(
                            moves =
                                "Rear Parry - Cross - Lead Hook",
                            cue =
                                "Keep the parry compact and answer without overreaching."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_defence_4",
                    title =
                        "Defence Checkpoint",
                    subtitle =
                        "Switch between guard, distance and parries.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 165,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - High Guard - Step Back - Cross",
                            cue =
                                "Defend, create distance and return only after your stance is stable."
                        ),
                        combo(
                            moves =
                                "Lead Parry - Jab - Cross - Step Back",
                            cue =
                                "Keep every defensive movement small and controlled."
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5
    // ---------------------------------------------------------

    private fun headMovement():
            TrainingChapterDefinition {

        /*
         * Existing chapter ID preserved.
         */
        val chapterId =
            "boxing_beginner_head_movement"

        return TrainingChapterDefinition(
            id = chapterId,
            title =
                "Head Movement",
            subtitle =
                "Learn exactly where to slip and roll before adding counters.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 5,
            lessons = listOf(

                /*
                 * Existing ID preserved.
                 */
                lesson(
                    id =
                        "boxing_beginner_head_1",
                    title =
                        "Slip Right",
                    subtitle =
                        "Move your head clearly to the right and return balanced.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 145,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Slip Right - Cross",
                            cue =
                                "Move your head clearly to the RIGHT, stay balanced and return with the cross."
                        ),
                        combo(
                            moves =
                                "Jab - Slip Right - Cross",
                            cue =
                                "Slip RIGHT without leaning too far outside your stance."
                        )
                    )
                ),

                /*
                 * Existing ID preserved.
                 */
                lesson(
                    id =
                        "boxing_beginner_head_2",
                    title =
                        "Slip Left",
                    subtitle =
                        "Build the opposite defensive direction.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 145,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Slip Left - Lead Hook",
                            cue =
                                "Move your head clearly to the LEFT and return with the lead hook."
                        ),
                        combo(
                            moves =
                                "Cross - Slip Left - Lead Hook",
                            cue =
                                "Slip LEFT while keeping your eyes forward and feet underneath you."
                        )
                    )
                ),

                /*
                 * Existing ID preserved.
                 */
                lesson(
                    id =
                        "boxing_beginner_head_3",
                    title =
                        "Roll Left & Right",
                    subtitle =
                        "Train both roll directions without guessing.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 160,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Roll Left - Lead Hook",
                            cue =
                                "Roll clearly to the LEFT under the imaginary hook and return balanced."
                        ),
                        combo(
                            moves =
                                "Lead Hook - Cross - Roll Right - Cross",
                            cue =
                                "Roll clearly to the RIGHT and rise back into stance before the cross."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_head_4",
                    title =
                        "Head Movement Checkpoint",
                    subtitle =
                        "Use slips and rolls inside controlled combinations.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 175,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Slip Right - Cross - Lead Hook",
                            cue =
                                "Slip RIGHT first, return balanced and then complete the counter."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Hook - Roll Left - Lead Hook - Cross",
                            cue =
                                "Roll clearly to the LEFT using your legs before returning to offense."
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6
    // ---------------------------------------------------------

    private fun beginnerAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_beginner_assessment"

        return TrainingChapterDefinition(
            id = chapterId,
            title =
                "Beginner Assessment",
            subtitle =
                "Combine the skills from the entire Beginner Fight Path.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 6,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_beginner_assessment_1",
                    title =
                        "Straight Punch Assessment",
                    subtitle =
                        "Show control while moving through straight-punch combinations.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 175,
                    combos = listOf(
                        combo(
                            moves =
                                "Double Jab - Cross - Step Back - Cross",
                            cue =
                                "Keep your stance stable through every transition."
                        ),
                        combo(
                            moves =
                                "Jab - Cross - Pivot Left - Jab - Cross",
                            cue =
                                "Change angle only after finishing the initial punches."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_assessment_2",
                    title =
                        "Punch Variety Assessment",
                    subtitle =
                        "Combine straights, hooks and uppercuts under control.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 185,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Lead Hook - Cross",
                            cue =
                                "Keep every punch compact and return to guard."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Uppercut - Lead Hook - Cross",
                            cue =
                                "Stay balanced while changing levels and punch angles."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_assessment_3",
                    title =
                        "Defence Assessment",
                    subtitle =
                        "Defend and immediately rebuild your stance.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 195,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Slip Right - Cross - Step Back",
                            cue =
                                "Slip clearly to the RIGHT before countering and exiting."
                        ),
                        combo(
                            moves =
                                "Cross - Lead Hook - Roll Left - Lead Hook - Cross",
                            cue =
                                "Roll clearly to the LEFT and return with controlled punches."
                        )
                    )
                ),

                lesson(
                    id =
                        "boxing_beginner_assessment_4",
                    title =
                        "Beginner Final Round",
                    subtitle =
                        "Complete one final integrated session before Fundamentals unlocks.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 250,
                    combos = listOf(
                        combo(
                            moves =
                                "Jab - Cross - Lead Hook - Roll Right - Cross - Step Back",
                            cue =
                                "Stay composed. Roll clearly to the RIGHT and finish balanced."
                        ),
                        combo(
                            moves =
                                "Double Jab - Cross - Slip Left - Lead Hook - Cross - Pivot Left",
                            cue =
                                "Slip clearly to the LEFT, finish the punches and then exit on the angle."
                        )
                    )
                )
            )
        )
    }

    // ---------------------------------------------------------
    // HELPERS
    // ---------------------------------------------------------

    private fun lesson(
        id: String,
        title: String,
        subtitle: String,
        chapterId: String,
        order: Int,
        minutes: Int,
        xp: Int,
        combos:
        List<TrainingLessonCombo>
    ): TrainingLessonDefinition {

        return TrainingLessonDefinition(
            id = id,
            title = title,
            subtitle = subtitle,
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            chapterId =
                chapterId,
            orderInChapter =
                order,
            combos =
                combos,
            requiredActiveSeconds =
                minutes * 60,
            xpReward =
                xp
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