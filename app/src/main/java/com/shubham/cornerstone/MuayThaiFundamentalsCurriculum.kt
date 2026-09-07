package com.shubham.cornerstone

/**
 * Muay Thai Fundamentals Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Fundamentals sessions.
 *
 * This stage develops:
 *
 * - Muay Thai rhythm and high-guard discipline
 * - lead and rear teep systems
 * - low-kick and body-kick combinations
 * - checks and immediate returns
 * - knees and elbows
 * - range control and balanced movement
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object MuayThaiFundamentalsCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MUAY_THAI

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            rhythmAndGuard(),
            teepSystems(),
            roundKickSystems(),
            checkAndCounter(),
            kneesAndElbows(),
            rangeAndMovement(),
            fundamentalsAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — RHYTHM AND GUARD
    // ---------------------------------------------------------

    private fun rhythmAndGuard():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_rhythm_guard"

        return chapter(
            id = chapterId,
            title = "Rhythm & Guard",
            subtitle =
                "Develop balance, posture and high-guard discipline.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_rhythm_1",
                    title = "Balanced Punching Rhythm",
                    subtitle =
                        "Punch without becoming heavy on the lead leg.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 180,
                    firstMoves =
                        "Jab - Cross - Step Back",
                    firstCue =
                        "Stay tall and return both hands to your high guard.",
                    secondMoves =
                        "Double Jab - Cross - Lead Teep",
                    secondCue =
                        "Recover the punches before lifting the lead knee."
                ),
                lesson(
                    id = "muaythai_fundamentals_rhythm_2",
                    title = "High-Guard Returns",
                    subtitle =
                        "Return to protection after every strike.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 185,
                    firstMoves =
                        "Jab - Cross - Lead Hook",
                    firstCue =
                        "Return each hand to guard before the next punch begins.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Keep the rear hand high while throwing the hook and kick."
                ),
                lesson(
                    id = "muaythai_fundamentals_rhythm_3",
                    title = "Long-Guard Control",
                    subtitle =
                        "Use the long guard to manage approaching pressure.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Long Guard - Rear Teep - Jab",
                    firstCue =
                        "Frame without locking the lead arm and recover the rear foot.",
                    secondMoves =
                        "Jab - Long Guard - Lead Teep",
                    secondCue =
                        "Keep your rear hand protecting your face."
                ),
                lesson(
                    id = "muaythai_fundamentals_rhythm_4",
                    title = "Punch and Kick Rhythm",
                    subtitle =
                        "Maintain posture while transitioning into kicks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Finish the punches before rotating through the kick.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Low Kick",
                    secondCue =
                        "Recover your balance before lifting the lead leg."
                ),
                lesson(
                    id = "muaythai_fundamentals_rhythm_5",
                    title = "Rhythm Changes",
                    subtitle =
                        "Use deliberate pauses without dropping your guard.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Pause - Cross - Rear Low Kick",
                    firstCue =
                        "Remain balanced and protected during the pause.",
                    secondMoves =
                        "Lead Teep - Pause - Jab - Cross",
                    secondCue =
                        "Use the pause to change timing, not to relax."
                ),
                lesson(
                    id = "muaythai_fundamentals_rhythm_6",
                    title = "Rhythm Checkpoint",
                    subtitle =
                        "Combine posture, guard and controlled timing.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Maintain a tall stance and recover every weapon.",
                    secondMoves =
                        "Long Guard - Lead Teep - Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Move from defence to offense without losing your high guard."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — TEEP SYSTEMS
    // ---------------------------------------------------------

    private fun teepSystems():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_teep_systems"

        return chapter(
            id = chapterId,
            title = "Teep Systems",
            subtitle =
                "Use lead and rear teeps to control range and create attacks.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_teeps_1",
                    title = "Lead Teep Entry",
                    subtitle =
                        "Enter behind the lead teep without rushing.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 185,
                    firstMoves =
                        "Lead Teep - Jab - Cross",
                    firstCue =
                        "Place the lead foot into stance before punching.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Hook",
                    secondCue =
                        "Recover your balance before beginning the hand combination."
                ),
                lesson(
                    id = "muaythai_fundamentals_teeps_2",
                    title = "Rear Teep Entry",
                    subtitle =
                        "Use the rear teep to establish strong distance.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Jab - Rear Teep - Jab",
                    firstCue =
                        "Return the rear foot before restarting with the jab.",
                    secondMoves =
                        "Rear Teep - Cross - Lead Hook",
                    secondCue =
                        "Rebuild stance before throwing the cross."
                ),
                lesson(
                    id = "muaythai_fundamentals_teeps_3",
                    title = "Teep Into Round Kick",
                    subtitle =
                        "Use the teep to prepare a round-kick attack.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Lead Teep - Jab - Rear Body Kick",
                    firstCue =
                        "Use the jab to bridge the distance after the teep.",
                    secondMoves =
                        "Rear Teep - Step Back - Lead Body Kick",
                    secondCue =
                        "Create the correct range before releasing the lead kick."
                ),
                lesson(
                    id = "muaythai_fundamentals_teeps_4",
                    title = "Teep and Exit",
                    subtitle =
                        "Create distance and leave safely.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Lead Teep - Step Back - Jab",
                    firstCue =
                        "Recover the lead foot before stepping back.",
                    secondMoves =
                        "Rear Teep - Step Left - Cross",
                    secondCue =
                        "Return the rear leg before moving left."
                ),
                lesson(
                    id = "muaythai_fundamentals_teeps_5",
                    title = "Double-Teep Control",
                    subtitle =
                        "Alternate teep sides while keeping your stance.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Lead Teep - Rear Teep - Jab",
                    firstCue =
                        "Reset your feet fully between the two teeps.",
                    secondMoves =
                        "Rear Teep - Lead Teep - Cross",
                    secondCue =
                        "Stay tall and avoid falling forward."
                ),
                lesson(
                    id = "muaythai_fundamentals_teeps_6",
                    title = "Teep Checkpoint",
                    subtitle =
                        "Use teeps for entries, exits and range control.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Step Back",
                    firstCue =
                        "Control range before entering and recover before exiting.",
                    secondMoves =
                        "Rear Teep - Step Left - Cross - Lead Hook - Lead Low Kick",
                    secondCue =
                        "Establish the new position before attacking."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — ROUND-KICK SYSTEMS
    // ---------------------------------------------------------

    private fun roundKickSystems():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_round_kick_systems"

        return chapter(
            id = chapterId,
            title = "Round-Kick Systems",
            subtitle =
                "Set up low kicks and body kicks using punches.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_round_kicks_1",
                    title = "Rear Low-Kick Setup",
                    subtitle =
                        "Hide the rear low kick behind straight punches.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Use the punches to bring attention high before kicking low.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Recover the hook before rotating into the kick."
                ),
                lesson(
                    id = "muaythai_fundamentals_round_kicks_2",
                    title = "Lead Low-Kick Setup",
                    subtitle =
                        "Create openings for the lead low kick.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 195,
                    firstMoves =
                        "Cross - Lead Hook - Lead Low Kick",
                    firstCue =
                        "Return the lead hand to guard before lifting the leg.",
                    secondMoves =
                        "Jab - Cross - Lead Low Kick - Cross",
                    secondCue =
                        "Recover the lead foot before throwing the final cross."
                ),
                lesson(
                    id = "muaythai_fundamentals_round_kicks_3",
                    title = "Rear Body-Kick Setup",
                    subtitle =
                        "Build strong rear body-kick combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Keep the lead hand protecting your face while kicking.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Finish the hook before turning through the kick."
                ),
                lesson(
                    id = "muaythai_fundamentals_round_kicks_4",
                    title = "Lead Body-Kick Setup",
                    subtitle =
                        "Attack the body from the lead side.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Cross - Lead Body Kick",
                    firstCue =
                        "Recover from the cross before lifting the lead leg.",
                    secondMoves =
                        "Jab - Cross - Lead Body Kick - Cross",
                    secondCue =
                        "Return the lead foot before the final cross."
                ),
                lesson(
                    id = "muaythai_fundamentals_round_kicks_5",
                    title = "Low-to-Body Changes",
                    subtitle =
                        "Change kick targets while maintaining balance.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Reset your stance between the two kicks.",
                    secondMoves =
                        "Cross - Lead Low Kick - Jab - Rear Body Kick",
                    secondCue =
                        "Recover every kick before changing sides."
                ),
                lesson(
                    id = "muaythai_fundamentals_round_kicks_6",
                    title = "Round-Kick Checkpoint",
                    subtitle =
                        "Combine kick setups, target changes and recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick - Cross",
                    firstCue =
                        "Recover the low kick before returning with the cross.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Body Kick - Rear Body Kick",
                    secondCue =
                        "Reset completely between opposite-side kicks."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — CHECK AND COUNTER
    // ---------------------------------------------------------

    private fun checkAndCounter():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_check_counter"

        return chapter(
            id = chapterId,
            title = "Check & Counter",
            subtitle =
                "Defend low kicks and answer from a stable stance.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_checks_1",
                    title = "Lead Check Return",
                    subtitle =
                        "Return with punches after a lead check.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 195,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook",
                    firstCue =
                        "Set the checking leg down before throwing the cross.",
                    secondMoves =
                        "Lead Check - Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Rebuild your stance before beginning the combination."
                ),
                lesson(
                    id = "muaythai_fundamentals_checks_2",
                    title = "Rear Check Return",
                    subtitle =
                        "Counter safely after checking with the rear leg.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 200,
                    firstMoves =
                        "Rear Check - Jab - Cross",
                    firstCue =
                        "Return the rear foot to position before punching.",
                    secondMoves =
                        "Rear Check - Cross - Lead Hook - Lead Low Kick",
                    secondCue =
                        "Recover your balance before lifting the lead leg."
                ),
                lesson(
                    id = "muaythai_fundamentals_checks_3",
                    title = "Check Into Body Kick",
                    subtitle =
                        "Answer a defended kick with a body kick.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Lead Check - Cross - Rear Body Kick",
                    firstCue =
                        "Set the checking leg down before turning through the body kick.",
                    secondMoves =
                        "Rear Check - Jab - Lead Body Kick",
                    secondCue =
                        "Recover the rear foot before beginning the return."
                ),
                lesson(
                    id = "muaythai_fundamentals_checks_4",
                    title = "Check Into Knee",
                    subtitle =
                        "Move from kick defence into close-range offense.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Recover the checking leg before entering knee range.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Knee",
                    secondCue =
                        "Return to stance before lifting the lead knee."
                ),
                lesson(
                    id = "muaythai_fundamentals_checks_5",
                    title = "Check and Exit",
                    subtitle =
                        "Counter before leaving the exchange.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Lead Check - Cross - Rear Low Kick - Step Left",
                    firstCue =
                        "Recover the kick before moving left.",
                    secondMoves =
                        "Rear Check - Jab - Lead Body Kick - Step Right",
                    secondCue =
                        "Return the lead foot before exiting right."
                ),
                lesson(
                    id = "muaythai_fundamentals_checks_6",
                    title = "Check-Counter Checkpoint",
                    subtitle =
                        "Defend, counter and exit with balance.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 240,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Body Kick - Step Left",
                    firstCue =
                        "Complete each phase before moving to the next.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Knee - Step Back - Lead Teep",
                    secondCue =
                        "Recover from close range before rebuilding distance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — KNEES AND ELBOWS
    // ---------------------------------------------------------

    private fun kneesAndElbows():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_knees_elbows"

        return chapter(
            id = chapterId,
            title = "Knees & Elbows",
            subtitle =
                "Build compact close-range combinations.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_close_range_1",
                    title = "Rear Knee Combinations",
                    subtitle =
                        "Enter safely behind punches before using the rear knee.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Shorten your punches as you enter knee range.",
                    secondMoves =
                        "Double Jab - Rear Knee - Step Back",
                    secondCue =
                        "Return the rear leg before leaving range."
                ),
                lesson(
                    id = "muaythai_fundamentals_close_range_2",
                    title = "Lead Knee Combinations",
                    subtitle =
                        "Connect hand combinations to the lead knee.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Cross - Lead Hook - Lead Knee",
                    firstCue =
                        "Recover the hook before lifting the lead knee.",
                    secondMoves =
                        "Jab - Cross - Lead Knee - Cross",
                    secondCue =
                        "Return the lead foot before throwing the final cross."
                ),
                lesson(
                    id = "muaythai_fundamentals_close_range_3",
                    title = "Horizontal Elbow Combinations",
                    subtitle =
                        "Connect lead and rear horizontal elbows.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Lead Horizontal Elbow",
                    firstCue =
                        "Keep the elbow compact and the opposite hand high.",
                    secondMoves =
                        "Lead Horizontal Elbow - Rear Horizontal Elbow - Step Back",
                    secondCue =
                        "Recover each elbow before leaving close range."
                ),
                lesson(
                    id = "muaythai_fundamentals_close_range_4",
                    title = "Up-Elbow Introduction",
                    subtitle =
                        "Introduce controlled upward elbow attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Jab - Lead Up Elbow",
                    firstCue =
                        "Drive the elbow upward while keeping your rear hand at guard.",
                    secondMoves =
                        "Cross - Rear Up Elbow",
                    secondCue =
                        "Recover your posture before lifting the rear elbow."
                ),
                lesson(
                    id = "muaythai_fundamentals_close_range_5",
                    title = "Elbow-to-Knee Transitions",
                    subtitle =
                        "Connect elbows and knees without losing posture.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Lead Horizontal Elbow - Rear Knee",
                    firstCue =
                        "Recover the elbow before driving the knee.",
                    secondMoves =
                        "Cross - Rear Horizontal Elbow - Lead Knee",
                    secondCue =
                        "Keep your free hand protecting your face."
                ),
                lesson(
                    id = "muaythai_fundamentals_close_range_6",
                    title = "Close-Range Checkpoint",
                    subtitle =
                        "Combine punches, elbows, knees and safe exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Jab - Cross - Lead Horizontal Elbow - Rear Knee - Step Back",
                    firstCue =
                        "Recover the knee before creating distance.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Up Elbow - Lead Knee - Step Right",
                    secondCue =
                        "Return the lead foot before moving right."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — RANGE AND MOVEMENT
    // ---------------------------------------------------------

    private fun rangeAndMovement():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_range_movement"

        return chapter(
            id = chapterId,
            title = "Range & Movement",
            subtitle =
                "Move between long, middle and close range safely.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_movement_1",
                    title = "Long-Range Movement",
                    subtitle =
                        "Use teeps and steps to control distance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Lead Teep - Step Left - Jab",
                    firstCue =
                        "Recover the lead foot before moving left.",
                    secondMoves =
                        "Rear Teep - Step Right - Cross",
                    secondCue =
                        "Return the rear foot before changing position."
                ),
                lesson(
                    id = "muaythai_fundamentals_movement_2",
                    title = "Mid-Range Movement",
                    subtitle =
                        "Reposition after punches and round kicks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Left",
                    firstCue =
                        "Recover the kick before moving.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Body Kick - Step Right",
                    secondCue =
                        "Return the lead leg before exiting right."
                ),
                lesson(
                    id = "muaythai_fundamentals_movement_3",
                    title = "Close-Range Exit",
                    subtitle =
                        "Leave safely after knees and elbows.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Step Back - Lead Teep",
                    firstCue =
                        "Recover the knee before creating distance.",
                    secondMoves =
                        "Lead Horizontal Elbow - Rear Knee - Step Left - Jab",
                    secondCue =
                        "Return to stance before exiting left."
                ),
                lesson(
                    id = "muaythai_fundamentals_movement_4",
                    title = "Left-Side Repositioning",
                    subtitle =
                        "Attack before moving toward the left.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Step Left",
                    firstCue =
                        "Finish and recover the kick before stepping.",
                    secondMoves =
                        "Lead Teep - Step Left - Cross - Rear Low Kick",
                    secondCue =
                        "Establish the new position before attacking."
                ),
                lesson(
                    id = "muaythai_fundamentals_movement_5",
                    title = "Right-Side Repositioning",
                    subtitle =
                        "Attack before moving toward the right.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Cross - Lead Hook - Lead Body Kick - Step Right",
                    firstCue =
                        "Recover the kick before stepping right.",
                    secondMoves =
                        "Rear Teep - Step Right - Jab - Cross",
                    secondCue =
                        "Return the rear foot before taking the angle."
                ),
                lesson(
                    id = "muaythai_fundamentals_movement_6",
                    title = "Movement Checkpoint",
                    subtitle =
                        "Control range and position across complete combinations.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Step Left",
                    firstCue =
                        "Move only after recovering the body kick.",
                    secondMoves =
                        "Lead Check - Cross - Lead Horizontal Elbow - Rear Knee - Step Back",
                    secondCue =
                        "Defend, work at close range and exit under control."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 7 — FUNDAMENTALS ASSESSMENT
    // ---------------------------------------------------------

    private fun fundamentalsAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_fundamentals_assessment"

        return chapter(
            id = chapterId,
            title = "Fundamentals Assessment",
            subtitle =
                "Prove control across foundational Muay Thai ranges.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "muaythai_fundamentals_assessment_1",
                    title = "Rhythm and Guard Review",
                    subtitle =
                        "Review posture, timing and high-guard recovery.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Maintain your high guard during every rhythm change.",
                    secondMoves =
                        "Long Guard - Lead Teep - Jab - Cross",
                    secondCue =
                        "Move from defence to offense while staying balanced."
                ),
                lesson(
                    id = "muaythai_fundamentals_assessment_2",
                    title = "Teep Review",
                    subtitle =
                        "Review lead and rear teep systems.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Recover the teep before entering behind punches.",
                    secondMoves =
                        "Rear Teep - Step Left - Cross - Lead Low Kick",
                    secondCue =
                        "Establish the new angle before attacking."
                ),
                lesson(
                    id = "muaythai_fundamentals_assessment_3",
                    title = "Round-Kick Review",
                    subtitle =
                        "Review low-kick and body-kick combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Reset your stance between opposite-side kicks.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Body Kick - Step Back",
                    secondCue =
                        "Recover completely before leaving range."
                ),
                lesson(
                    id = "muaythai_fundamentals_assessment_4",
                    title = "Check-Counter Review",
                    subtitle =
                        "Review kick defence and immediate returns.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Set the checking leg down before beginning the return.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Knee - Step Back",
                    secondCue =
                        "Recover the knee before leaving close range."
                ),
                lesson(
                    id = "muaythai_fundamentals_assessment_5",
                    title = "Eight-Limb Review",
                    subtitle =
                        "Review punches, kicks, knees and elbows.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Step Back",
                    firstCue =
                        "Control long and middle range before exiting.",
                    secondMoves =
                        "Jab - Cross - Lead Horizontal Elbow - Rear Knee",
                    secondCue =
                        "Keep the close-range sequence compact and protected."
                ),
                lesson(
                    id = "muaythai_fundamentals_assessment_6",
                    title = "Fundamentals Final Round",
                    subtitle =
                        "Complete the Muay Thai Fundamentals stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 300,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Lead Hook - Rear Body Kick - Step Left",
                    firstCue =
                        "Control range, attack and reposition from a stable stance.",
                    secondMoves =
                        "Lead Check - Cross - Lead Horizontal Elbow - Rear Knee - Step Back - Rear Teep",
                    secondCue =
                        "Defend, enter close range and finish by rebuilding distance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // HELPERS
    // ---------------------------------------------------------

    private fun chapter(
        id: String,
        title: String,
        subtitle: String,
        order: Int,
        lessons: List<TrainingLessonDefinition>
    ): TrainingChapterDefinition {

        return TrainingChapterDefinition(
            id = id,
            title = title,
            subtitle = subtitle,
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = order,
            lessons = lessons
        )
    }

    private fun lesson(
        id: String,
        title: String,
        subtitle: String,
        chapterId: String,
        order: Int,
        minutes: Int,
        xp: Int,
        firstMoves: String,
        firstCue: String,
        secondMoves: String,
        secondCue: String
    ): TrainingLessonDefinition {

        return TrainingLessonDefinition(
            id = id,
            title = title,
            subtitle = subtitle,
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            chapterId = chapterId,
            orderInChapter = order,
            combos = listOf(
                TrainingLessonCombo(
                    moves = firstMoves,
                    cue = firstCue
                ),
                TrainingLessonCombo(
                    moves = secondMoves,
                    cue = secondCue
                )
            ),
            requiredActiveSeconds =
                minutes * 60,
            xpReward = xp
        )
    }
}