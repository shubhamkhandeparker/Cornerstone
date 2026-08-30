package com.shubham.cornerstone

/**
 * Kickboxing Fundamentals Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Fundamentals sessions.
 *
 * This stage develops:
 *
 * - stronger punch-to-kick transitions
 * - lead and rear low-kick systems
 * - body-kick and front-kick control
 * - directional boxing defence
 * - kick checks and counters
 * - angles, exits and range control
 * - controlled pressure
 *
 * Directional defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object KickboxingFundamentalsCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_KICKBOXING

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationMechanics(),
            lowKickSystems(),
            bodyKickSystems(),
            defensiveKickboxing(),
            countersAndAngles(),
            pressureAndRange(),
            fundamentalsAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION MECHANICS
    // ---------------------------------------------------------

    private fun combinationMechanics():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_fundamentals_combination_mechanics"

        return chapter(
            id = chapterId,
            title = "Combination Mechanics",
            subtitle =
                "Build smoother transitions between punches and kicks.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_combinations_1",
                    title = "Straight Punch Rhythm",
                    subtitle =
                        "Develop clean rhythm without overcommitting.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 180,
                    firstMoves =
                        "Jab - Cross - Jab - Rear Low Kick",
                    firstCue =
                        "Keep every straight punch compact before rotating into the kick.",
                    secondMoves =
                        "Double Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Recover your punching stance before lifting the lead leg."
                ),
                lesson(
                    id = "kickboxing_fundamentals_combinations_2",
                    title = "Hook Into Rear Kick",
                    subtitle =
                        "Connect the lead hook to rear-side kicks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 185,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Let the lead hook load the rear hip without becoming square.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Bring the hook back to guard before releasing the kick."
                ),
                lesson(
                    id = "kickboxing_fundamentals_combinations_3",
                    title = "Lead-Side Connections",
                    subtitle =
                        "Link rear-hand punches to lead-side kicks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Lead Low Kick",
                    firstCue =
                        "Control your forward weight before lifting the lead leg.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Body Kick",
                    secondCue =
                        "Finish the hook and recover balance before kicking."
                ),
                lesson(
                    id = "kickboxing_fundamentals_combinations_4",
                    title = "Kick Into Punches",
                    subtitle =
                        "Continue attacking after recovering from a kick.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Rear Low Kick - Jab - Cross",
                    firstCue =
                        "Return the rear leg completely before beginning the punches.",
                    secondMoves =
                        "Lead Body Kick - Cross - Lead Hook",
                    secondCue =
                        "Place the lead foot into stance before throwing the cross."
                ),
                lesson(
                    id = "kickboxing_fundamentals_combinations_5",
                    title = "Changing Levels",
                    subtitle =
                        "Move attacks between the head, body and legs.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Draw the guard high before attacking the leg.",
                    secondMoves =
                        "Jab - Rear Body Kick - Cross - Lead Hook",
                    secondCue =
                        "Recover the kick before returning to head-level punches."
                ),
                lesson(
                    id = "kickboxing_fundamentals_combinations_6",
                    title = "Combination Checkpoint",
                    subtitle =
                        "Show balanced transitions on both sides.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Maintain stance width throughout the entire combination.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Recover from each weapon before moving to the next."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — LOW-KICK SYSTEMS
    // ---------------------------------------------------------

    private fun lowKickSystems():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_fundamentals_low_kick_systems"

        return chapter(
            id = chapterId,
            title = "Low-Kick Systems",
            subtitle =
                "Set up, disguise and recover from low kicks.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_low_kicks_1",
                    title = "Rear Low-Kick Setup",
                    subtitle =
                        "Use straight punches to prepare the rear low kick.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 185,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Use the double jab to establish range before turning the hip.",
                    secondMoves =
                        "Jab - Cross - Jab - Rear Low Kick",
                    secondCue =
                        "Keep the final jab short so the kick remains balanced."
                ),
                lesson(
                    id = "kickboxing_fundamentals_low_kicks_2",
                    title = "Lead Low-Kick Setup",
                    subtitle =
                        "Create reliable openings for the lead low kick.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Cross - Lead Hook - Lead Low Kick",
                    firstCue =
                        "Recover your lead side after the hook before kicking.",
                    secondMoves =
                        "Jab - Cross - Lead Low Kick - Cross",
                    secondCue =
                        "Return the lead leg to stance before throwing the final cross."
                ),
                lesson(
                    id = "kickboxing_fundamentals_low_kicks_3",
                    title = "Inside and Outside Rhythm",
                    subtitle =
                        "Alternate lead and rear low-kick attacks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Jab - Lead Low Kick - Cross - Rear Low Kick",
                    firstCue =
                        "Reset your feet between the two kicks.",
                    secondMoves =
                        "Cross - Rear Low Kick - Jab - Lead Low Kick",
                    secondCue =
                        "Do not punch until the first kick has fully recovered."
                ),
                lesson(
                    id = "kickboxing_fundamentals_low_kicks_4",
                    title = "Low Kick After Movement",
                    subtitle =
                        "Attack the leg after changing position.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Step Left - Rear Low Kick",
                    firstCue =
                        "Complete the step and rebuild stance before kicking.",
                    secondMoves =
                        "Double Jab - Step Right - Lead Low Kick",
                    secondCue =
                        "Keep your feet separated while repositioning."
                ),
                lesson(
                    id = "kickboxing_fundamentals_low_kicks_5",
                    title = "Low-Kick Returns",
                    subtitle =
                        "Return safely with punches after kicking.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Rear Low Kick - Cross - Lead Hook",
                    firstCue =
                        "Set the rear foot down before beginning the counter punches.",
                    secondMoves =
                        "Lead Low Kick - Jab - Cross - Step Back",
                    secondCue =
                        "Recover, punch and leave range in that order."
                ),
                lesson(
                    id = "kickboxing_fundamentals_low_kicks_6",
                    title = "Low-Kick Checkpoint",
                    subtitle =
                        "Combine setups, movement and safe recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick - Cross",
                    firstCue =
                        "Recover the kicking leg before returning with the cross.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Low Kick - Step Right - Jab",
                    secondCue =
                        "Finish the kick, reposition and restart from stance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — BODY-KICK SYSTEMS
    // ---------------------------------------------------------

    private fun bodyKickSystems():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_fundamentals_body_kick_systems"

        return chapter(
            id = chapterId,
            title = "Body-Kick Systems",
            subtitle =
                "Control range with body kicks and front kicks.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_body_kicks_1",
                    title = "Rear Body-Kick Setup",
                    subtitle =
                        "Build strong rear body-kick entries.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Use the punches to establish range before rotating.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep the hook compact so you remain balanced for the kick."
                ),
                lesson(
                    id = "kickboxing_fundamentals_body_kicks_2",
                    title = "Lead Body-Kick Setup",
                    subtitle =
                        "Connect punching pressure to the lead body kick.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 195,
                    firstMoves =
                        "Cross - Lead Hook - Lead Body Kick",
                    firstCue =
                        "Recover from the hook before lifting the lead leg.",
                    secondMoves =
                        "Jab - Cross - Lead Body Kick - Cross",
                    secondCue =
                        "Set the kicking foot down before throwing the final cross."
                ),
                lesson(
                    id = "kickboxing_fundamentals_body_kicks_3",
                    title = "Lead Front-Kick Entry",
                    subtitle =
                        "Use the lead front kick to begin combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the front kick before entering behind your hands.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook",
                    secondCue =
                        "Place the lead foot into stance before punching."
                ),
                lesson(
                    id = "kickboxing_fundamentals_body_kicks_4",
                    title = "Rear Front-Kick Control",
                    subtitle =
                        "Manage distance using the rear front kick.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Jab - Rear Front Kick - Step Back",
                    firstCue =
                        "Stay tall through the kick and recover without falling forward.",
                    secondMoves =
                        "Rear Front Kick - Jab - Cross - Lead Low Kick",
                    secondCue =
                        "Rebuild stance before starting the punching combination."
                ),
                lesson(
                    id = "kickboxing_fundamentals_body_kicks_5",
                    title = "Body and Leg Changes",
                    subtitle =
                        "Switch attacks between the body and legs.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Rear Body Kick - Cross - Lead Low Kick",
                    firstCue =
                        "Recover between the body kick and the cross.",
                    secondMoves =
                        "Cross - Lead Body Kick - Jab - Rear Low Kick",
                    secondCue =
                        "Keep your stance stable while changing sides."
                ),
                lesson(
                    id = "kickboxing_fundamentals_body_kicks_6",
                    title = "Kick-Range Checkpoint",
                    subtitle =
                        "Combine front kicks, body kicks and exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross - Rear Body Kick - Step Back",
                    firstCue =
                        "Control range, enter behind punches and exit after the kick.",
                    secondMoves =
                        "Rear Front Kick - Step Left - Lead Body Kick - Cross",
                    secondCue =
                        "Complete the step before attacking from the new angle."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — DEFENSIVE KICKBOXING
    // ---------------------------------------------------------

    private fun defensiveKickboxing():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_fundamentals_defensive_kickboxing"

        return chapter(
            id = chapterId,
            title = "Defensive Kickboxing",
            subtitle =
                "Defend punches and kicks before answering safely.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_defence_1",
                    title = "Lead Check Returns",
                    subtitle =
                        "Recover from a lead check before countering.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 195,
                    firstMoves =
                        "Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Set the checking leg down before throwing the cross.",
                    secondMoves =
                        "Lead Check - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Rebuild stance before beginning the combination."
                ),
                lesson(
                    id = "kickboxing_fundamentals_defence_2",
                    title = "Rear Check Returns",
                    subtitle =
                        "Counter safely after checking from the rear side.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 200,
                    firstMoves =
                        "Rear Check - Jab - Cross - Lead Low Kick",
                    firstCue =
                        "Recover your rear foot before throwing the jab.",
                    secondMoves =
                        "Rear Check - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Do not rush the counter while your base is rebuilding."
                ),
                lesson(
                    id = "kickboxing_fundamentals_defence_3",
                    title = "Slip Right Counters",
                    subtitle =
                        "Use Slip Right before returning with controlled offense.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Move clearly right, counter with your hands and finish low.",
                    secondMoves =
                        "Jab - Slip Right - Cross - Rear Body Kick",
                    secondCue =
                        "Keep your eyes forward throughout Slip Right."
                ),
                lesson(
                    id = "kickboxing_fundamentals_defence_4",
                    title = "Slip Left Counters",
                    subtitle =
                        "Use Slip Left to create lead-side returns.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross - Lead Low Kick",
                    firstCue =
                        "Move clearly left and rebuild posture before countering.",
                    secondMoves =
                        "Jab - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Keep Slip Left compact enough to remain ready for kicks."
                ),
                lesson(
                    id = "kickboxing_fundamentals_defence_5",
                    title = "Directional Rolls",
                    subtitle =
                        "Use explicit roll direction before countering.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Roll Left - Lead Hook - Cross - Rear Low Kick",
                    firstCue =
                        "Complete Roll Left without dropping your eyes.",
                    secondMoves =
                        "Roll Right - Cross - Lead Hook - Lead Body Kick",
                    secondCue =
                        "Complete Roll Right and recover your stance before attacking."
                ),
                lesson(
                    id = "kickboxing_fundamentals_defence_6",
                    title = "Defence Checkpoint",
                    subtitle =
                        "Combine checks and directional head movement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 240,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover from the check before using Slip Left.",
                    secondMoves =
                        "Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep both defensive movements clear and directional."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — COUNTERS AND ANGLES
    // ---------------------------------------------------------

    private fun countersAndAngles():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_fundamentals_counters_angles"

        return chapter(
            id = chapterId,
            title = "Counters & Angles",
            subtitle =
                "Counter before leaving on a controlled angle.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_counters_1",
                    title = "Counter and Step Left",
                    subtitle =
                        "Finish your counter before moving left.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Step Left",
                    firstCue =
                        "Complete the counter before stepping away.",
                    secondMoves =
                        "Lead Check - Cross - Rear Low Kick - Step Left",
                    secondCue =
                        "Recover the kick before changing position."
                ),
                lesson(
                    id = "kickboxing_fundamentals_counters_2",
                    title = "Counter and Step Right",
                    subtitle =
                        "Use right-side movement after attacking.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross - Step Right",
                    firstCue =
                        "Complete Slip Left and both punches before moving.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Low Kick - Step Right",
                    secondCue =
                        "Return to stance before taking the angle."
                ),
                lesson(
                    id = "kickboxing_fundamentals_counters_3",
                    title = "Kick Counter Returns",
                    subtitle =
                        "Answer kicks with balanced combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Check, recover, punch and kick in a controlled sequence.",
                    secondMoves =
                        "Rear Check - Cross - Lead Low Kick - Cross",
                    secondCue =
                        "Set your base before every return."
                ),
                lesson(
                    id = "kickboxing_fundamentals_counters_4",
                    title = "Head-Movement Returns",
                    subtitle =
                        "Counter punches using directional defence.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Slip Right - Cross - Rear Body Kick - Cross",
                    firstCue =
                        "Recover the body kick before throwing the final cross.",
                    secondMoves =
                        "Slip Left - Lead Hook - Rear Low Kick - Jab",
                    secondCue =
                        "Return the kicking leg before restarting with the jab."
                ),
                lesson(
                    id = "kickboxing_fundamentals_counters_5",
                    title = "Angle Re-entry",
                    subtitle =
                        "Leave the center and safely re-enter.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Cross - Step Left - Jab - Rear Body Kick",
                    firstCue =
                        "Rebuild stance after stepping before you re-enter.",
                    secondMoves =
                        "Lead Front Kick - Step Right - Cross - Lead Hook",
                    secondCue =
                        "Use the front kick to create room for the angle."
                ),
                lesson(
                    id = "kickboxing_fundamentals_counters_6",
                    title = "Counters Checkpoint",
                    subtitle =
                        "Defend, counter and reposition.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Low Kick - Step Left",
                    firstCue =
                        "Finish each phase before moving to the next.",
                    secondMoves =
                        "Slip Right - Cross - Roll Left - Lead Hook - Lead Body Kick - Step Right",
                    secondCue =
                        "Keep Slip Right and Roll Left distinct and controlled."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — PRESSURE AND RANGE
    // ---------------------------------------------------------

    private fun pressureAndRange():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_fundamentals_pressure_range"

        return chapter(
            id = chapterId,
            title = "Pressure & Range",
            subtitle =
                "Enter, work and exit without losing your stance.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_pressure_1",
                    title = "Safe Entry",
                    subtitle =
                        "Enter punching range behind controlled attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Use the jabs to enter without leaning forward.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross",
                    secondCue =
                        "Recover the lead foot before advancing."
                ),
                lesson(
                    id = "kickboxing_fundamentals_pressure_2",
                    title = "Pressure Combinations",
                    subtitle =
                        "Maintain offense without rushing.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Cross - Rear Low Kick",
                    firstCue =
                        "Keep the punches compact while moving forward.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Do not allow forward pressure to narrow your stance."
                ),
                lesson(
                    id = "kickboxing_fundamentals_pressure_3",
                    title = "Range Reset",
                    subtitle =
                        "Create distance and restart safely.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back - Jab",
                    firstCue =
                        "Recover the kick before stepping back.",
                    secondMoves =
                        "Lead Front Kick - Step Back - Cross - Lead Hook",
                    secondCue =
                        "Set your feet again before returning with punches."
                ),
                lesson(
                    id = "kickboxing_fundamentals_pressure_4",
                    title = "Pressure Exit Left",
                    subtitle =
                        "Leave pressure exchanges toward the left.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick - Step Left",
                    firstCue =
                        "Finish the body kick before exiting left.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Low Kick - Step Left - Jab",
                    secondCue =
                        "Rebuild stance after the step before using the jab."
                ),
                lesson(
                    id = "kickboxing_fundamentals_pressure_5",
                    title = "Pressure Exit Right",
                    subtitle =
                        "Leave pressure exchanges toward the right.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Double Jab - Cross - Lead Low Kick - Step Right",
                    firstCue =
                        "Recover the lead leg before exiting right.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook - Step Right",
                    secondCue =
                        "Keep the exit controlled and avoid crossing your feet."
                ),
                lesson(
                    id = "kickboxing_fundamentals_pressure_6",
                    title = "Pressure Checkpoint",
                    subtitle =
                        "Control entries, combinations and exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Lead Front Kick - Double Jab - Cross - Rear Body Kick - Step Left",
                    firstCue =
                        "Control range before entering and leave after the kick.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Cross - Step Right",
                    secondCue =
                        "Recover the kick before punching and exiting."
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
            "kickboxing_fundamentals_assessment"

        return chapter(
            id = chapterId,
            title = "Fundamentals Assessment",
            subtitle =
                "Prove control across combinations, defence and movement.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "kickboxing_fundamentals_assessment_1",
                    title = "Punch-to-Kick Review",
                    subtitle =
                        "Review balanced punch-to-kick transitions.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Keep your stance stable from the first jab to the final kick.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Body Kick - Cross",
                    secondCue =
                        "Recover the lead leg before throwing the final cross."
                ),
                lesson(
                    id = "kickboxing_fundamentals_assessment_2",
                    title = "Low-Kick Review",
                    subtitle =
                        "Review lead and rear low-kick systems.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Lead Low Kick - Cross - Rear Low Kick",
                    firstCue =
                        "Reset between attacks instead of rushing the second kick.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Low Kick - Jab - Lead Low Kick",
                    secondCue =
                        "Maintain balance while changing sides."
                ),
                lesson(
                    id = "kickboxing_fundamentals_assessment_3",
                    title = "Kick-Range Review",
                    subtitle =
                        "Review body kicks and front-kick control.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross - Rear Body Kick - Step Back",
                    firstCue =
                        "Manage the distance before entering and exiting.",
                    secondMoves =
                        "Rear Front Kick - Step Left - Lead Body Kick - Cross",
                    secondCue =
                        "Rebuild stance after every range change."
                ),
                lesson(
                    id = "kickboxing_fundamentals_assessment_4",
                    title = "Defence Review",
                    subtitle =
                        "Review checks and directional boxing defence.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover the check before performing Slip Left.",
                    secondMoves =
                        "Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Make Slip Right and Roll Left clearly distinct."
                ),
                lesson(
                    id = "kickboxing_fundamentals_assessment_5",
                    title = "Movement Review",
                    subtitle =
                        "Review controlled angles and exits.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Left - Jab",
                    firstCue =
                        "Recover the kick before taking the angle.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Complete the step before throwing the final cross."
                ),
                lesson(
                    id = "kickboxing_fundamentals_assessment_6",
                    title = "Fundamentals Final Round",
                    subtitle =
                        "Complete the Kickboxing Fundamentals stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 300,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Low Kick - Step Left - Jab",
                    firstCue =
                        "Defend, recover, counter, kick and reposition with control.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross - Slip Right - Cross - Lead Hook - Rear Body Kick - Step Back",
                    secondCue =
                        "Keep Slip Right explicit and finish the complete sequence in stance."
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