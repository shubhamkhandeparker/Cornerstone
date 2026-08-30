package com.shubham.cornerstone

/**
 * Kickboxing Developing Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Developing sessions.
 *
 * This stage develops:
 *
 * - layered punch-and-kick combinations
 * - feints and setup sequences
 * - defensive chains
 * - kickboxing counters
 * - angles and controlled exits
 * - pressure and pace management
 * - reliable stance recovery
 *
 * Directional boxing defence must always be explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object KickboxingDevelopingCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_KICKBOXING

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            layeredCombinations(),
            feintsAndSetups(),
            defensiveChains(),
            counterKickboxing(),
            anglesAndExits(),
            paceAndPressure(),
            developingAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — LAYERED COMBINATIONS
    // ---------------------------------------------------------

    private fun layeredCombinations():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_layered_combinations"

        return chapter(
            id = chapterId,
            title = "Layered Combinations",
            subtitle =
                "Build longer combinations without losing structure.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_layers_1",
                    title = "Double-Level Attacks",
                    subtitle =
                        "Move cleanly between head, body and leg attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 250,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Cross",
                    firstCue =
                        "Recover the low kick completely before returning upstairs.",
                    secondMoves =
                        "Double Jab - Rear Body Kick - Cross - Lead Low Kick",
                    secondCue =
                        "Reset your stance between each change of level."
                ),
                lesson(
                    id = "kickboxing_developing_layers_2",
                    title = "Lead-Side Layers",
                    subtitle =
                        "Build multiple attacks from the lead side.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 255,
                    firstMoves =
                        "Jab - Lead Hook - Lead Body Kick - Cross",
                    firstCue =
                        "Return the lead foot to stance before throwing the cross.",
                    secondMoves =
                        "Lead Front Kick - Jab - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Use the front kick to establish range before entering."
                ),
                lesson(
                    id = "kickboxing_developing_layers_3",
                    title = "Rear-Side Layers",
                    subtitle =
                        "Connect rear-hand and rear-leg attacks safely.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 260,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Cross - Lead Hook",
                    firstCue =
                        "Recover the rear leg before returning with the cross.",
                    secondMoves =
                        "Double Jab - Cross - Rear Low Kick - Cross",
                    secondCue =
                        "Do not allow the kick recovery to narrow your stance."
                ),
                lesson(
                    id = "kickboxing_developing_layers_4",
                    title = "Kick-Punch-Kick",
                    subtitle =
                        "Attack continuously while recovering between weapons.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Lead Body Kick - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Set the lead foot before punching and finish with balance.",
                    secondMoves =
                        "Rear Low Kick - Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Recover the rear leg before starting the punches."
                ),
                lesson(
                    id = "kickboxing_developing_layers_5",
                    title = "Extended Combination Control",
                    subtitle =
                        "Maintain posture through longer sequences.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Body Kick - Cross",
                    firstCue =
                        "Keep every transition controlled instead of chasing speed.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross - Lead Low Kick - Cross",
                    secondCue =
                        "Rebuild your base before the final cross."
                ),
                lesson(
                    id = "kickboxing_developing_layers_6",
                    title = "Layered Combination Checkpoint",
                    subtitle =
                        "Show reliable transitions across every striking range.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Recover after every kick and finish in stance.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Cross - Rear Body Kick - Step Back",
                    secondCue =
                        "Enter and exit without allowing the combination to pull you forward."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — FEINTS AND SETUPS
    // ---------------------------------------------------------

    private fun feintsAndSetups():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_feints_setups"

        return chapter(
            id = chapterId,
            title = "Feints & Setups",
            subtitle =
                "Create openings before committing to an attack.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_feints_1",
                    title = "Jab Feint Entries",
                    subtitle =
                        "Use a jab feint to prepare punches and kicks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 255,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep the feint small enough to preserve your stance.",
                    secondMoves =
                        "Jab Feint - Rear Body Kick - Cross",
                    secondCue =
                        "Sell the hand threat before rotating into the kick."
                ),
                lesson(
                    id = "kickboxing_developing_feints_2",
                    title = "Cross Feint Attacks",
                    subtitle =
                        "Use rear-hand movement to open the lead side.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 260,
                    firstMoves =
                        "Cross Feint - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Do not shift excessive weight during the feint.",
                    secondMoves =
                        "Cross Feint - Lead Low Kick - Cross",
                    secondCue =
                        "Return the lead leg before throwing the final cross."
                ),
                lesson(
                    id = "kickboxing_developing_feints_3",
                    title = "Low-Kick Feints",
                    subtitle =
                        "Use low-kick threats to create openings upstairs.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 265,
                    firstMoves =
                        "Rear Low Kick Feint - Cross - Lead Hook",
                    firstCue =
                        "Keep the feint controlled and return immediately to stance.",
                    secondMoves =
                        "Lead Low Kick Feint - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Use the feint to draw attention down before attacking high."
                ),
                lesson(
                    id = "kickboxing_developing_feints_4",
                    title = "Front-Kick Feints",
                    subtitle =
                        "Create punching entries using front-kick threats.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Lead Front Kick Feint - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Lift the knee without leaning backward.",
                    secondMoves =
                        "Rear Front Kick Feint - Cross - Lead Hook - Lead Body Kick",
                    secondCue =
                        "Place the rear foot correctly before beginning the punches."
                ),
                lesson(
                    id = "kickboxing_developing_feints_5",
                    title = "Double Feint Sequences",
                    subtitle =
                        "Combine two controlled threats before attacking.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Jab Feint - Rear Low Kick Feint - Cross - Lead Hook",
                    firstCue =
                        "Keep both feints compact and preserve your balance.",
                    secondMoves =
                        "Lead Front Kick Feint - Jab Feint - Rear Body Kick",
                    secondCue =
                        "Change the opponent's attention without rushing."
                ),
                lesson(
                    id = "kickboxing_developing_feints_6",
                    title = "Setup Checkpoint",
                    subtitle =
                        "Use feints to create clear attacking opportunities.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick - Cross",
                    firstCue =
                        "Make the feint believable while keeping the real attack sharp.",
                    secondMoves =
                        "Rear Low Kick Feint - Jab - Cross - Lead Body Kick - Step Right",
                    secondCue =
                        "Attack the opening and exit only after recovering the kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — DEFENSIVE CHAINS
    // ---------------------------------------------------------

    private fun defensiveChains():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_defensive_chains"

        return chapter(
            id = chapterId,
            title = "Defensive Chains",
            subtitle =
                "Connect multiple defensive actions before countering.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_defence_1",
                    title = "Slip Right to Check",
                    subtitle =
                        "Defend a punch and prepare for a kick.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 260,
                    firstMoves =
                        "Slip Right - Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Complete Slip Right before lifting the checking leg.",
                    secondMoves =
                        "Jab - Slip Right - Rear Check - Cross",
                    secondCue =
                        "Recover each defensive position before countering."
                ),
                lesson(
                    id = "kickboxing_developing_defence_2",
                    title = "Slip Left to Check",
                    subtitle =
                        "Connect left-side head movement to kick defence.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 265,
                    firstMoves =
                        "Slip Left - Rear Check - Lead Hook - Cross",
                    firstCue =
                        "Complete Slip Left and rebuild posture before checking.",
                    secondMoves =
                        "Jab - Cross - Slip Left - Lead Check - Rear Body Kick",
                    secondCue =
                        "Recover the checking leg before releasing the body kick."
                ),
                lesson(
                    id = "kickboxing_developing_defence_3",
                    title = "Roll Left Returns",
                    subtitle =
                        "Use Roll Left before returning with kicks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Roll Left - Lead Hook - Cross - Rear Body Kick",
                    firstCue =
                        "Keep your eyes forward throughout Roll Left.",
                    secondMoves =
                        "Jab - Cross - Roll Left - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Recover your posture before beginning the counter."
                ),
                lesson(
                    id = "kickboxing_developing_defence_4",
                    title = "Roll Right Returns",
                    subtitle =
                        "Use Roll Right to create rear-hand counters.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Roll Right - Cross - Lead Hook - Lead Body Kick",
                    firstCue =
                        "Complete Roll Right before throwing the cross.",
                    secondMoves =
                        "Jab - Roll Right - Cross - Rear Low Kick - Cross",
                    secondCue =
                        "Return the kicking leg before repeating the cross."
                ),
                lesson(
                    id = "kickboxing_developing_defence_5",
                    title = "Mixed Defensive Chain",
                    subtitle =
                        "Move between directional head movement and checks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Keep Slip Right and Roll Left separate and deliberate.",
                    secondMoves =
                        "Slip Left - Roll Right - Rear Check - Lead Hook - Cross",
                    secondCue =
                        "Rebuild stance after each defensive action."
                ),
                lesson(
                    id = "kickboxing_developing_defence_6",
                    title = "Defensive Chain Checkpoint",
                    subtitle =
                        "Defend multiple attacks before returning safely.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Finish the defensive chain before beginning your counter.",
                    secondMoves =
                        "Slip Left - Rear Check - Roll Right - Cross - Lead Low Kick - Step Left",
                    secondCue =
                        "Stay balanced through every defensive transition."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — COUNTER KICKBOXING
    // ---------------------------------------------------------

    private fun counterKickboxing():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_counter_kickboxing"

        return chapter(
            id = chapterId,
            title = "Counter Kickboxing",
            subtitle =
                "Turn successful defence into structured offense.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_counters_1",
                    title = "Check and Return",
                    subtitle =
                        "Answer low kicks with immediate controlled offense.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 265,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Set the checking leg down before throwing the cross.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Rebuild your base before starting the return."
                ),
                lesson(
                    id = "kickboxing_developing_counters_2",
                    title = "Cross Counter to Kick",
                    subtitle =
                        "Connect rear-hand counters to kicks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 270,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Use Slip Right to load the cross without overrotating.",
                    secondMoves =
                        "Lead Parry - Cross - Rear Low Kick - Cross",
                    secondCue =
                        "Keep the parry small and recover the kick before punching again."
                ),
                lesson(
                    id = "kickboxing_developing_counters_3",
                    title = "Lead-Hook Counter",
                    subtitle =
                        "Build returns around the lead hook.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross - Lead Low Kick",
                    firstCue =
                        "Complete Slip Left before rotating into the hook.",
                    secondMoves =
                        "Roll Left - Lead Hook - Rear Body Kick - Cross",
                    secondCue =
                        "Recover the body kick before throwing the final cross."
                ),
                lesson(
                    id = "kickboxing_developing_counters_4",
                    title = "Front-Kick Counter",
                    subtitle =
                        "Use front kicks to interrupt forward pressure.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Step Back - Lead Front Kick - Jab - Cross",
                    firstCue =
                        "Create space before extending the front kick.",
                    secondMoves =
                        "Rear Front Kick - Step Left - Cross - Lead Hook",
                    secondCue =
                        "Recover the rear leg before taking the angle."
                ),
                lesson(
                    id = "kickboxing_developing_counters_5",
                    title = "Counter and Exit",
                    subtitle =
                        "Leave safely after completing the return.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 285,
                    firstMoves =
                        "Lead Check - Cross - Rear Low Kick - Step Left",
                    firstCue =
                        "Recover the kick before exiting left.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Lead Body Kick - Step Right",
                    secondCue =
                        "Finish the body kick before moving right."
                ),
                lesson(
                    id = "kickboxing_developing_counters_6",
                    title = "Counter Checkpoint",
                    subtitle =
                        "Defend, return and exit with full control.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 315,
                    firstMoves =
                        "Rear Check - Jab - Cross - Lead Hook - Rear Body Kick - Step Left",
                    firstCue =
                        "Recover from the check before beginning the counter.",
                    secondMoves =
                        "Slip Left - Lead Hook - Cross - Lead Low Kick - Cross - Step Right",
                    secondCue =
                        "Keep Slip Left compact and recover before exiting."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — ANGLES AND EXITS
    // ---------------------------------------------------------

    private fun anglesAndExits():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_angles_exits"

        return chapter(
            id = chapterId,
            title = "Angles & Exits",
            subtitle =
                "Attack from new positions and leave exchanges safely.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_angles_1",
                    title = "Left-Angle Entry",
                    subtitle =
                        "Reposition left before beginning the attack.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 270,
                    firstMoves =
                        "Jab - Step Left - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Complete the left step before throwing the cross.",
                    secondMoves =
                        "Lead Front Kick - Step Left - Jab - Rear Body Kick",
                    secondCue =
                        "Use the front kick to create room for the angle."
                ),
                lesson(
                    id = "kickboxing_developing_angles_2",
                    title = "Right-Angle Entry",
                    subtitle =
                        "Reposition right before attacking.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 275,
                    firstMoves =
                        "Double Jab - Step Right - Cross - Lead Low Kick",
                    firstCue =
                        "Keep your feet separated while stepping right.",
                    secondMoves =
                        "Rear Front Kick - Step Right - Jab - Cross",
                    secondCue =
                        "Recover the rear foot before changing position."
                ),
                lesson(
                    id = "kickboxing_developing_angles_3",
                    title = "Kick and Pivot Left",
                    subtitle =
                        "Create a new position after kicking.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Pivot Left - Jab",
                    firstCue =
                        "Return the rear leg before beginning Pivot Left.",
                    secondMoves =
                        "Lead Body Kick - Pivot Left - Cross - Lead Hook",
                    secondCue =
                        "Complete the kick recovery before turning."
                ),
                lesson(
                    id = "kickboxing_developing_angles_4",
                    title = "Kick and Pivot Right",
                    subtitle =
                        "Exit exchanges toward the right.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 285,
                    firstMoves =
                        "Cross - Lead Hook - Lead Low Kick - Pivot Right - Cross",
                    firstCue =
                        "Recover the lead foot before beginning Pivot Right.",
                    secondMoves =
                        "Rear Body Kick - Pivot Right - Jab - Cross",
                    secondCue =
                        "Do not turn until the kicking leg has returned."
                ),
                lesson(
                    id = "kickboxing_developing_angles_5",
                    title = "Defensive Angles",
                    subtitle =
                        "Combine directional defence with repositioning.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Pivot Left - Rear Low Kick",
                    firstCue =
                        "Complete Slip Right and the punches before pivoting.",
                    secondMoves =
                        "Slip Left - Lead Hook - Pivot Right - Cross - Lead Body Kick",
                    secondCue =
                        "Recover from Slip Left before changing position."
                ),
                lesson(
                    id = "kickboxing_developing_angles_6",
                    title = "Angle Checkpoint",
                    subtitle =
                        "Enter, attack and leave from multiple directions.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 320,
                    firstMoves =
                        "Lead Front Kick - Step Left - Cross - Lead Hook - Rear Body Kick - Pivot Right",
                    firstCue =
                        "Recover every strike before changing direction.",
                    secondMoves =
                        "Jab - Cross - Lead Low Kick - Pivot Left - Cross - Rear Low Kick",
                    secondCue =
                        "Maintain stance width through both attacks and the pivot."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — PACE AND PRESSURE
    // ---------------------------------------------------------

    private fun paceAndPressure():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_pace_pressure"

        return chapter(
            id = chapterId,
            title = "Pace & Pressure",
            subtitle =
                "Control intensity without sacrificing technique.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_pressure_1",
                    title = "Measured Pressure",
                    subtitle =
                        "Move forward behind compact combinations.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Use the double jab to advance without leaning.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross - Lead Hook",
                    secondCue =
                        "Recover the front kick before applying pressure."
                ),
                lesson(
                    id = "kickboxing_developing_pressure_2",
                    title = "Pressure With Kicks",
                    subtitle =
                        "Use kicks while maintaining forward control.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Double Jab",
                    firstCue =
                        "Recover the kick before continuing forward.",
                    secondMoves =
                        "Lead Low Kick - Cross - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Reset your feet between the two kicks."
                ),
                lesson(
                    id = "kickboxing_developing_pressure_3",
                    title = "Broken Rhythm",
                    subtitle =
                        "Change the timing of your combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 285,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep your guard active during the deliberate pause.",
                    secondMoves =
                        "Double Jab - Pause - Rear Body Kick - Cross",
                    secondCue =
                        "Use the pause to change rhythm without relaxing your stance."
                ),
                lesson(
                    id = "kickboxing_developing_pressure_4",
                    title = "Pressure Reset",
                    subtitle =
                        "Disengage and restart without losing control.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back - Lead Front Kick",
                    firstCue =
                        "Recover the low kick before creating distance.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Body Kick - Step Back - Jab",
                    secondCue =
                        "Reset your stance before using the final jab."
                ),
                lesson(
                    id = "kickboxing_developing_pressure_5",
                    title = "Pressure Under Defence",
                    subtitle =
                        "Defend while maintaining your position.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 295,
                    firstMoves =
                        "Double Jab - Slip Right - Cross - Rear Body Kick",
                    firstCue =
                        "Use Slip Right without allowing your feet to stop.",
                    secondMoves =
                        "Jab - Cross - Roll Left - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Complete Roll Left before resuming pressure."
                ),
                lesson(
                    id = "kickboxing_developing_pressure_6",
                    title = "Pressure Checkpoint",
                    subtitle =
                        "Control entries, rhythm, defence and exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 325,
                    firstMoves =
                        "Lead Front Kick - Double Jab - Cross - Lead Hook - Rear Body Kick - Step Left",
                    firstCue =
                        "Enter behind structure and exit after recovering the kick.",
                    secondMoves =
                        "Jab Feint - Cross - Lead Low Kick - Cross - Rear Low Kick - Step Back",
                    secondCue =
                        "Change rhythm while preserving balance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 7 — DEVELOPING ASSESSMENT
    // ---------------------------------------------------------

    private fun developingAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_developing_assessment"

        return chapter(
            id = chapterId,
            title = "Developing Assessment",
            subtitle =
                "Prove control across layered Kickboxing skills.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "kickboxing_developing_assessment_1",
                    title = "Combination Review",
                    subtitle =
                        "Review longer combinations across multiple levels.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Maintain posture and recover between every layer.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Rear Body Kick - Cross - Lead Low Kick",
                    secondCue =
                        "Keep every transition technically controlled."
                ),
                lesson(
                    id = "kickboxing_developing_assessment_2",
                    title = "Setup Review",
                    subtitle =
                        "Review feints and disguised attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 295,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Keep the feint believable without overcommitting.",
                    secondMoves =
                        "Rear Low Kick Feint - Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Draw attention down before attacking higher."
                ),
                lesson(
                    id = "kickboxing_developing_assessment_3",
                    title = "Defensive Chain Review",
                    subtitle =
                        "Review checks and directional defensive movement.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Keep each defensive action separate and controlled.",
                    secondMoves =
                        "Slip Left - Rear Check - Roll Right - Lead Hook - Cross",
                    secondCue =
                        "Recover your stance before beginning the counter."
                ),
                lesson(
                    id = "kickboxing_developing_assessment_4",
                    title = "Counter Review",
                    subtitle =
                        "Review defence-to-offense transitions.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Body Kick - Step Left",
                    firstCue =
                        "Defend, recover, counter and exit in sequence.",
                    secondMoves =
                        "Slip Left - Lead Hook - Cross - Lead Low Kick - Cross",
                    secondCue =
                        "Complete Slip Left before returning with offense."
                ),
                lesson(
                    id = "kickboxing_developing_assessment_5",
                    title = "Angle and Pressure Review",
                    subtitle =
                        "Review controlled pressure and directional exits.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick - Pivot Left - Jab",
                    firstCue =
                        "Recover the kick before beginning Pivot Left.",
                    secondMoves =
                        "Lead Front Kick - Step Right - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Enter from the new position without crossing your feet."
                ),
                lesson(
                    id = "kickboxing_developing_assessment_6",
                    title = "Developing Final Round",
                    subtitle =
                        "Complete the Kickboxing Developing stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 375,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick - Slip Right - Cross - Lead Body Kick - Step Left",
                    firstCue =
                        "Use Slip Right explicitly and recover before the final kick.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Roll Left - Lead Hook - Rear Body Kick - Cross - Pivot Right",
                    secondCue =
                        "Keep Roll Left clear, finish the return and exit in stance."
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
                TrainingPathLevel.DEVELOPING,
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
                TrainingPathLevel.DEVELOPING,
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