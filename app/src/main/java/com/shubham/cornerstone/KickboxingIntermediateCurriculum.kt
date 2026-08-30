package com.shubham.cornerstone

/**
 * Kickboxing Intermediate Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Intermediate sessions.
 *
 * This stage develops:
 *
 * - adaptable combinations
 * - switch kicks and controlled high kicks
 * - knees and close-range striking
 * - interceptions and counters
 * - layered defence
 * - tactical round management
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object KickboxingIntermediateCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_KICKBOXING

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationAdaptation(),
            switchAndHighKicks(),
            kneesAndCloseRange(),
            interceptionsAndCounters(),
            layeredDefence(),
            tacticalRoundControl(),
            intermediateAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION ADAPTATION
    // ---------------------------------------------------------

    private fun combinationAdaptation():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_combination_adaptation"

        return chapter(
            id = chapterId,
            title = "Combination Adaptation",
            subtitle =
                "Change attacks while preserving balance and structure.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_adaptation_1",
                    title = "Changing the Finish",
                    subtitle =
                        "Use the same entry with different finishing attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 330,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep the entry identical and finish with a controlled low kick.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Change only the final target while preserving your balance."
                ),
                lesson(
                    id = "kickboxing_intermediate_adaptation_2",
                    title = "Changing the Entry",
                    subtitle =
                        "Reach the same attack through different openings.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 335,
                    firstMoves =
                        "Double Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Use the double jab to enter without narrowing your stance.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover the front kick before beginning the punches."
                ),
                lesson(
                    id = "kickboxing_intermediate_adaptation_3",
                    title = "Attack and Re-Attack",
                    subtitle =
                        "Reset briefly before launching a second attack.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 340,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back - Cross - Lead Hook",
                    firstCue =
                        "Recover and create distance before beginning the second attack.",
                    secondMoves =
                        "Lead Body Kick - Step Back - Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Use the reset to rebuild stance instead of stopping completely."
                ),
                lesson(
                    id = "kickboxing_intermediate_adaptation_4",
                    title = "Kick-Side Switching",
                    subtitle =
                        "Alternate lead-side and rear-side kicking attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 345,
                    firstMoves =
                        "Jab - Lead Low Kick - Cross - Rear Body Kick",
                    firstCue =
                        "Reset your feet between opposite-side kicks.",
                    secondMoves =
                        "Cross - Rear Low Kick - Lead Hook - Lead Body Kick",
                    secondCue =
                        "Recover each kick before transferring weight again."
                ),
                lesson(
                    id = "kickboxing_intermediate_adaptation_5",
                    title = "Defence Inside Combinations",
                    subtitle =
                        "Defend during offense without losing the sequence.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 350,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross - Rear Low Kick",
                    firstCue =
                        "Complete Slip Right before returning with the cross.",
                    secondMoves =
                        "Lead Front Kick - Jab - Roll Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Complete Roll Left and recover posture before the hook."
                ),
                lesson(
                    id = "kickboxing_intermediate_adaptation_6",
                    title = "Adaptation Checkpoint",
                    subtitle =
                        "Change entries, targets and finishes under control.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 385,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Change levels without allowing the sequence to pull you forward.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Slip Left - Lead Hook - Rear Body Kick - Step Right",
                    secondCue =
                        "Keep Slip Left explicit and finish from a stable stance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — SWITCH AND HIGH KICKS
    // ---------------------------------------------------------

    private fun switchAndHighKicks():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_switch_high_kicks"

        return chapter(
            id = chapterId,
            title = "Switch & High Kicks",
            subtitle =
                "Add switch attacks and controlled head-level kicks.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_switch_kicks_1",
                    title = "Switch Body Kick",
                    subtitle =
                        "Build a controlled switch into the lead body kick.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 335,
                    firstMoves =
                        "Jab - Switch Lead Body Kick",
                    firstCue =
                        "Keep the switch compact and return immediately to stance.",
                    secondMoves =
                        "Jab - Cross - Switch Lead Body Kick",
                    secondCue =
                        "Finish the cross before beginning the switch."
                ),
                lesson(
                    id = "kickboxing_intermediate_switch_kicks_2",
                    title = "Switch Low Kick",
                    subtitle =
                        "Use a quick switch to attack the leg.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 340,
                    firstMoves =
                        "Jab Feint - Switch Lead Low Kick",
                    firstCue =
                        "Keep the switch underneath your body instead of jumping forward.",
                    secondMoves =
                        "Cross - Lead Hook - Switch Lead Low Kick",
                    secondCue =
                        "Recover the hook before changing your feet."
                ),
                lesson(
                    id = "kickboxing_intermediate_switch_kicks_3",
                    title = "Rear High-Kick Setup",
                    subtitle =
                        "Introduce controlled rear high kicks after hand combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 345,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear High Kick",
                    firstCue =
                        "Prioritize control and balance rather than height or power.",
                    secondMoves =
                        "Double Jab - Cross - Rear High Kick",
                    secondCue =
                        "Keep your supporting foot active and recover safely."
                ),
                lesson(
                    id = "kickboxing_intermediate_switch_kicks_4",
                    title = "Lead High-Kick Setup",
                    subtitle =
                        "Build controlled lead-side high-kick entries.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 350,
                    firstMoves =
                        "Cross - Lead Hook - Lead High Kick",
                    firstCue =
                        "Recover from the hook before lifting the lead leg.",
                    secondMoves =
                        "Jab - Cross - Switch Lead High Kick",
                    secondCue =
                        "Keep the switch compact and the kick technically controlled."
                ),
                lesson(
                    id = "kickboxing_intermediate_switch_kicks_5",
                    title = "Low-to-High Changes",
                    subtitle =
                        "Use low-kick threats to prepare controlled high kicks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 355,
                    firstMoves =
                        "Rear Low Kick Feint - Cross - Lead Hook - Rear High Kick",
                    firstCue =
                        "Sell the low attack without sacrificing your stance.",
                    secondMoves =
                        "Lead Low Kick Feint - Jab - Cross - Switch Lead High Kick",
                    secondCue =
                        "Change the target while keeping the final kick controlled."
                ),
                lesson(
                    id = "kickboxing_intermediate_switch_kicks_6",
                    title = "Switch-Kick Checkpoint",
                    subtitle =
                        "Combine switch kicks and controlled target changes.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 390,
                    firstMoves =
                        "Jab - Cross - Switch Lead Body Kick - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the switch kick before returning with the cross.",
                    secondMoves =
                        "Jab Feint - Rear Low Kick - Lead Hook - Switch Lead High Kick",
                    secondCue =
                        "Maintain technical control when changing from low to high."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — KNEES AND CLOSE RANGE
    // ---------------------------------------------------------

    private fun kneesAndCloseRange():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_knees_close_range"

        return chapter(
            id = chapterId,
            title = "Knees & Close Range",
            subtitle =
                "Connect compact punches to controlled knee strikes.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_knees_1",
                    title = "Rear Knee Entry",
                    subtitle =
                        "Connect straight punches to the rear knee.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 340,
                    firstMoves =
                        "Jab - Cross - Rear Knee",
                    firstCue =
                        "Bring your posture tall before driving the rear knee.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Knee",
                    secondCue =
                        "Keep the hook compact before entering knee range."
                ),
                lesson(
                    id = "kickboxing_intermediate_knees_2",
                    title = "Lead Knee Entry",
                    subtitle =
                        "Build controlled attacks using the lead knee.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 345,
                    firstMoves =
                        "Cross - Lead Hook - Lead Knee",
                    firstCue =
                        "Recover from the hook before lifting the lead knee.",
                    secondMoves =
                        "Jab - Cross - Lead Knee - Cross",
                    secondCue =
                        "Return the lead foot to stance before the final cross."
                ),
                lesson(
                    id = "kickboxing_intermediate_knees_3",
                    title = "Punches Into Knees",
                    subtitle =
                        "Enter close range behind compact combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 350,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Shorten the punches as you enter knee range.",
                    secondMoves =
                        "Cross - Lead Hook - Cross - Lead Knee",
                    secondCue =
                        "Keep your guard connected during the transition."
                ),
                lesson(
                    id = "kickboxing_intermediate_knees_4",
                    title = "Knee and Exit",
                    subtitle =
                        "Leave close range immediately after the knee.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 355,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Step Back - Jab",
                    firstCue =
                        "Return the rear leg before creating distance.",
                    secondMoves =
                        "Lead Hook - Lead Knee - Step Right - Cross",
                    secondCue =
                        "Recover the lead foot before moving right."
                ),
                lesson(
                    id = "kickboxing_intermediate_knees_5",
                    title = "Knee-to-Kick Transitions",
                    subtitle =
                        "Move from close-range knees back to kicking range.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 360,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Step Back - Rear Low Kick",
                    firstCue =
                        "Create enough distance before releasing the low kick.",
                    secondMoves =
                        "Lead Hook - Lead Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Reset your stance and range before the body kick."
                ),
                lesson(
                    id = "kickboxing_intermediate_knees_6",
                    title = "Close-Range Checkpoint",
                    subtitle =
                        "Enter, use knees and exit safely.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 395,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Knee - Step Left - Jab",
                    firstCue =
                        "Recover the knee completely before taking the angle.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook - Lead Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Move between long and close range without losing structure."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — INTERCEPTIONS AND COUNTERS
    // ---------------------------------------------------------

    private fun interceptionsAndCounters():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_interceptions_counters"

        return chapter(
            id = chapterId,
            title = "Interceptions & Counters",
            subtitle =
                "Interrupt forward pressure and return immediately.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_interceptions_1",
                    title = "Jab Interception",
                    subtitle =
                        "Use the jab to interrupt an advancing opponent.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 345,
                    firstMoves =
                        "Step Back - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Create only enough distance to place the jab cleanly.",
                    secondMoves =
                        "Jab - Step Left - Cross - Lead Body Kick",
                    secondCue =
                        "Use the jab to interrupt before taking the angle."
                ),
                lesson(
                    id = "kickboxing_intermediate_interceptions_2",
                    title = "Front-Kick Interception",
                    subtitle =
                        "Use front kicks to stop forward movement.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 350,
                    firstMoves =
                        "Lead Front Kick - Step Back - Cross - Lead Hook",
                    firstCue =
                        "Extend the front kick with posture and recover under control.",
                    secondMoves =
                        "Rear Front Kick - Step Left - Jab - Rear Low Kick",
                    secondCue =
                        "Recover the rear foot before repositioning."
                ),
                lesson(
                    id = "kickboxing_intermediate_interceptions_3",
                    title = "Rear Knee Interception",
                    subtitle =
                        "Use the rear knee against forward pressure.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 355,
                    firstMoves =
                        "Step Back - Rear Knee - Cross - Lead Hook",
                    firstCue =
                        "Maintain posture and recover the knee before punching.",
                    secondMoves =
                        "Jab - Rear Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Create kicking distance before the body kick."
                ),
                lesson(
                    id = "kickboxing_intermediate_interceptions_4",
                    title = "Slip Right Interception",
                    subtitle =
                        "Use Slip Right to create an immediate counter.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 360,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Complete Slip Right before releasing the cross.",
                    secondMoves =
                        "Jab - Slip Right - Cross - Rear Knee - Step Back",
                    secondCue =
                        "Recover from the knee before leaving range."
                ),
                lesson(
                    id = "kickboxing_intermediate_interceptions_5",
                    title = "Slip Left Interception",
                    subtitle =
                        "Use Slip Left to create lead-side counters.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 365,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross - Lead Body Kick",
                    firstCue =
                        "Complete Slip Left and restore posture before the hook.",
                    secondMoves =
                        "Jab - Cross - Slip Left - Lead Hook - Rear Knee",
                    secondCue =
                        "Keep Slip Left compact enough to remain balanced."
                ),
                lesson(
                    id = "kickboxing_intermediate_interceptions_6",
                    title = "Interception Checkpoint",
                    subtitle =
                        "Interrupt, counter and reposition.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 400,
                    firstMoves =
                        "Lead Front Kick - Slip Right - Cross - Lead Hook - Rear Low Kick - Step Left",
                    firstCue =
                        "Recover from the front kick before using Slip Right.",
                    secondMoves =
                        "Step Back - Jab - Slip Left - Lead Hook - Rear Knee - Step Right",
                    secondCue =
                        "Keep Slip Left explicit and recover before exiting."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — LAYERED DEFENCE
    // ---------------------------------------------------------

    private fun layeredDefence():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_layered_defence"

        return chapter(
            id = chapterId,
            title = "Layered Defence",
            subtitle =
                "Combine directional movement, checks and safe exits.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_defence_1",
                    title = "Slip Right and Roll Left",
                    subtitle =
                        "Connect two directional boxing defences.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 350,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Hook - Cross",
                    firstCue =
                        "Keep Slip Right and Roll Left separate and controlled.",
                    secondMoves =
                        "Jab - Slip Right - Cross - Roll Left - Rear Low Kick",
                    secondCue =
                        "Recover posture after Roll Left before kicking."
                ),
                lesson(
                    id = "kickboxing_intermediate_defence_2",
                    title = "Slip Left and Roll Right",
                    subtitle =
                        "Build the opposite directional defensive chain.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 355,
                    firstMoves =
                        "Slip Left - Roll Right - Cross - Lead Hook",
                    firstCue =
                        "Keep Slip Left and Roll Right visually distinct.",
                    secondMoves =
                        "Jab - Cross - Slip Left - Lead Hook - Roll Right - Lead Body Kick",
                    secondCue =
                        "Rebuild stance after Roll Right before kicking."
                ),
                lesson(
                    id = "kickboxing_intermediate_defence_3",
                    title = "Head Movement to Check",
                    subtitle =
                        "Connect punch defence to low-kick defence.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 360,
                    firstMoves =
                        "Slip Right - Lead Check - Cross - Rear Body Kick",
                    firstCue =
                        "Complete Slip Right before lifting the checking leg.",
                    secondMoves =
                        "Slip Left - Rear Check - Lead Hook - Cross",
                    secondCue =
                        "Recover from Slip Left before performing the rear check."
                ),
                lesson(
                    id = "kickboxing_intermediate_defence_4",
                    title = "Check to Head Movement",
                    subtitle =
                        "Defend the kick before preparing for punches.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 365,
                    firstMoves =
                        "Lead Check - Slip Right - Cross - Rear Low Kick",
                    firstCue =
                        "Set the checking leg down before using Slip Right.",
                    secondMoves =
                        "Rear Check - Slip Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover the checking foot before using Slip Left."
                ),
                lesson(
                    id = "kickboxing_intermediate_defence_5",
                    title = "Defend and Angle",
                    subtitle =
                        "Leave the center after a defensive return.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 370,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Pivot Left - Rear Low Kick",
                    firstCue =
                        "Complete the punches before beginning Pivot Left.",
                    secondMoves =
                        "Lead Check - Cross - Lead Body Kick - Pivot Right - Jab",
                    secondCue =
                        "Recover the lead leg before beginning Pivot Right."
                ),
                lesson(
                    id = "kickboxing_intermediate_defence_6",
                    title = "Layered Defence Checkpoint",
                    subtitle =
                        "Defend several attacks before countering and exiting.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 405,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Complete the full defensive chain before countering.",
                    secondMoves =
                        "Slip Left - Rear Check - Roll Right - Cross - Lead Low Kick - Step Left",
                    secondCue =
                        "Maintain balance through every defensive layer."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — TACTICAL ROUND CONTROL
    // ---------------------------------------------------------

    private fun tacticalRoundControl():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_tactical_round_control"

        return chapter(
            id = chapterId,
            title = "Tactical Round Control",
            subtitle =
                "Manage range, rhythm and pressure across longer rounds.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_tactics_1",
                    title = "Long-Range Control",
                    subtitle =
                        "Use jabs and front kicks to manage distance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 355,
                    firstMoves =
                        "Double Jab - Lead Front Kick - Step Back",
                    firstCue =
                        "Control distance without allowing your stance to become narrow.",
                    secondMoves =
                        "Rear Front Kick - Jab - Cross - Step Left",
                    secondCue =
                        "Recover the rear foot before punching."
                ),
                lesson(
                    id = "kickboxing_intermediate_tactics_2",
                    title = "Mid-Range Control",
                    subtitle =
                        "Use punches and round kicks at combination range.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 360,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Maintain the correct distance for every strike.",
                    secondMoves =
                        "Cross - Lead Body Kick - Cross - Rear Low Kick",
                    secondCue =
                        "Recover between opposite-side attacks."
                ),
                lesson(
                    id = "kickboxing_intermediate_tactics_3",
                    title = "Close-Range Control",
                    subtitle =
                        "Use compact punches and knees before exiting.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 365,
                    firstMoves =
                        "Lead Hook - Cross - Lead Hook - Rear Knee - Step Back",
                    firstCue =
                        "Keep the punches compact and recover the knee before exiting.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Knee - Step Right - Cross",
                    secondCue =
                        "Place the lead foot before taking the angle."
                ),
                lesson(
                    id = "kickboxing_intermediate_tactics_4",
                    title = "Tempo Changes",
                    subtitle =
                        "Change speed without losing technical control.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 370,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Stay protected during the deliberate rhythm change.",
                    secondMoves =
                        "Double Jab - Rear Low Kick - Pause - Cross - Lead High Kick",
                    secondCue =
                        "Keep the high kick controlled after the tempo change."
                ),
                lesson(
                    id = "kickboxing_intermediate_tactics_5",
                    title = "Pressure and Reset",
                    subtitle =
                        "Apply pressure before deliberately resetting range.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 375,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Low Kick - Step Back",
                    firstCue =
                        "Finish the low kick before resetting.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross - Rear Knee - Step Left",
                    secondCue =
                        "Recover the knee before leaving close range."
                ),
                lesson(
                    id = "kickboxing_intermediate_tactics_6",
                    title = "Tactical Round Checkpoint",
                    subtitle =
                        "Move deliberately through long, middle and close range.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 410,
                    firstMoves =
                        "Lead Front Kick - Double Jab - Cross - Rear Body Kick - Step Back - Jab",
                    firstCue =
                        "Control every range change instead of rushing forward.",
                    secondMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Knee - Step Left - Lead Body Kick",
                    secondCue =
                        "Recover from close range before releasing the final kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 7 — INTERMEDIATE ASSESSMENT
    // ---------------------------------------------------------

    private fun intermediateAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_intermediate_assessment"

        return chapter(
            id = chapterId,
            title = "Intermediate Assessment",
            subtitle =
                "Prove tactical control across all Kickboxing ranges.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "kickboxing_intermediate_assessment_1",
                    title = "Adaptation Review",
                    subtitle =
                        "Review changing entries, targets and finishes.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 370,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Adapt the target without sacrificing balance.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Rear Body Kick - Cross - Lead Low Kick",
                    secondCue =
                        "Recover between every range transition."
                ),
                lesson(
                    id = "kickboxing_intermediate_assessment_2",
                    title = "Switch and High-Kick Review",
                    subtitle =
                        "Review switch attacks and controlled high kicks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 375,
                    firstMoves =
                        "Jab - Cross - Switch Lead Body Kick - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the switch kick before returning with punches.",
                    secondMoves =
                        "Rear Low Kick Feint - Cross - Lead Hook - Rear High Kick",
                    secondCue =
                        "Prioritize control and safe recovery on the high kick."
                ),
                lesson(
                    id = "kickboxing_intermediate_assessment_3",
                    title = "Close-Range Review",
                    subtitle =
                        "Review knee entries and safe exits.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 380,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Knee - Step Back",
                    firstCue =
                        "Shorten your punches as you enter knee range.",
                    secondMoves =
                        "Lead Front Kick - Cross - Lead Hook - Lead Knee - Step Right",
                    secondCue =
                        "Recover the lead foot before taking the angle."
                ),
                lesson(
                    id = "kickboxing_intermediate_assessment_4",
                    title = "Defensive Review",
                    subtitle =
                        "Review layered directional defence and checks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 385,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Rear Body Kick",
                    firstCue =
                        "Keep Slip Right and Roll Left clearly separated.",
                    secondMoves =
                        "Slip Left - Rear Check - Roll Right - Lead Hook - Cross",
                    secondCue =
                        "Recover completely before beginning the counter."
                ),
                lesson(
                    id = "kickboxing_intermediate_assessment_5",
                    title = "Tactical Review",
                    subtitle =
                        "Review range, rhythm, pressure and resets.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 390,
                    firstMoves =
                        "Lead Front Kick - Double Jab - Cross - Rear Low Kick - Pivot Left",
                    firstCue =
                        "Control long range before entering and changing position.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Create correct kicking distance after leaving close range."
                ),
                lesson(
                    id = "kickboxing_intermediate_assessment_6",
                    title = "Intermediate Final Round",
                    subtitle =
                        "Complete the Kickboxing Intermediate stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 475,
                    firstMoves =
                        "Lead Front Kick - Jab Feint - Cross - Lead Hook - Rear Knee - Step Left - Lead High Kick",
                    firstCue =
                        "Move through every range and keep the high kick controlled.",
                    secondMoves =
                        "Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick - Cross - Lead Low Kick - Step Back",
                    secondCue =
                        "Keep Slip Right and Roll Left explicit and finish safely in stance."
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
                TrainingPathLevel.INTERMEDIATE,
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
                TrainingPathLevel.INTERMEDIATE,
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