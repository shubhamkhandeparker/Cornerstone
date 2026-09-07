package com.shubham.cornerstone

/**
 * Muay Thai Developing Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Developing sessions.
 *
 * This stage develops:
 *
 * - layered eight-limb combinations
 * - teep feints and entries
 * - kick chains and target changes
 * - checks, directional slips and immediate returns
 * - close-range knees and elbows
 * - pressure, angles and controlled exits
 * - adaptable solo Muay Thai movement
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object MuayThaiDevelopingCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MUAY_THAI

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            layeredEightLimbCombinations(),
            teepFeintsAndEntries(),
            kickChainsAndTargetChanges(),
            defenceAndImmediateReturns(),
            closeRangeKneesAndElbows(),
            pressureAnglesAndExits(),
            developingAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — LAYERED EIGHT-LIMB COMBINATIONS
    // ---------------------------------------------------------

    private fun layeredEightLimbCombinations():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_developing_layered_combinations"

        return chapter(
            id = chapterId,
            title = "Layered Eight-Limb Combinations",
            subtitle =
                "Build longer combinations without losing balance or guard.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_layered_combinations_1",
                    title = "Punch-Kick Layers",
                    subtitle =
                        "Connect compact boxing combinations to round kicks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Finish the lead hook before rotating into the rear body kick.",
                    secondMoves =
                        "Double Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Keep the punches compact and recover directly into stance."
                ),
                lesson(
                    id = "muaythai_developing_layered_combinations_2",
                    title = "Kick-Punch Layers",
                    subtitle =
                        "Return from kicks ready to continue with punches.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Lead Low Kick - Cross - Lead Hook",
                    firstCue =
                        "Place the lead foot under control before throwing the cross.",
                    secondMoves =
                        "Rear Body Kick - Jab - Cross",
                    secondCue =
                        "Recover the rear leg fully before restarting with the jab."
                ),
                lesson(
                    id = "muaythai_developing_layered_combinations_3",
                    title = "Teep Into Combination",
                    subtitle =
                        "Use the teep to establish range before entering.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the lead foot before advancing behind the punches.",
                    secondMoves =
                        "Rear Teep - Jab - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Return to stance before changing from distance control to attack."
                ),
                lesson(
                    id = "muaythai_developing_layered_combinations_4",
                    title = "Low-to-Body Changes",
                    subtitle =
                        "Change targets while keeping the same attacking rhythm.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 205,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Rebuild your stance between the two kicks.",
                    secondMoves =
                        "Lead Low Kick - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep your head protected while changing levels."
                ),
                lesson(
                    id = "muaythai_developing_layered_combinations_5",
                    title = "Knee-Elbow Connections",
                    subtitle =
                        "Move smoothly from punching range into close range.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Lead Horizontal Elbow",
                    firstCue =
                        "Recover the rear knee before rotating the lead elbow.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Knee - Rear Horizontal Elbow",
                    secondCue =
                        "Keep the combination compact and the free hand at guard."
                ),
                lesson(
                    id = "muaythai_developing_layered_combinations_6",
                    title = "Layered Combination Review",
                    subtitle =
                        "Review transitions across kicking, punching and close range.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 230,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Lead Hook",
                    firstCue =
                        "Recover every strike before continuing the combination.",
                    secondMoves =
                        "Lead Low Kick - Cross - Lead Hook - Rear Knee - Lead Horizontal Elbow - Step Back",
                    secondCue =
                        "Stay balanced through each range and finish with a controlled exit."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — TEEP FEINTS AND ENTRIES
    // ---------------------------------------------------------

    private fun teepFeintsAndEntries():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_developing_teep_entries"

        return chapter(
            id = chapterId,
            title = "Teep Feints & Entries",
            subtitle =
                "Use teep rhythm to create openings and control distance.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_teep_entries_1",
                    title = "Lead Teep Feint",
                    subtitle =
                        "Use the lead knee lift to open punching attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Lead Teep Feint - Jab - Cross",
                    firstCue =
                        "Lift the lead knee without leaning back, then step into the punches.",
                    secondMoves =
                        "Lead Teep Feint - Cross - Lead Hook",
                    secondCue =
                        "Return the lead foot beneath you before rotating the cross."
                ),
                lesson(
                    id = "muaythai_developing_teep_entries_2",
                    title = "Rear Teep Feint",
                    subtitle =
                        "Use a rear-side feint to prepare kicks and knees.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Rear Teep Feint - Jab - Rear Body Kick",
                    firstCue =
                        "Set the rear foot before beginning the kick.",
                    secondMoves =
                        "Rear Teep Feint - Cross - Lead Knee",
                    secondCue =
                        "Maintain a tall posture as you enter knee range."
                ),
                lesson(
                    id = "muaythai_developing_teep_entries_3",
                    title = "Jab-to-Teep Entry",
                    subtitle =
                        "Hide the teep behind straight punches.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Double Jab - Lead Teep",
                    firstCue =
                        "Use the double jab to establish distance before extending the teep.",
                    secondMoves =
                        "Jab - Cross - Rear Teep",
                    secondCue =
                        "Return both hands to guard before lifting the rear knee."
                ),
                lesson(
                    id = "muaythai_developing_teep_entries_4",
                    title = "Teep-to-Round-Kick Entry",
                    subtitle =
                        "Change from linear attacks to round attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 210,
                    firstMoves =
                        "Lead Teep - Cross - Rear Body Kick",
                    firstCue =
                        "Recover the lead foot before rotating into the cross.",
                    secondMoves =
                        "Rear Teep - Jab - Lead Body Kick",
                    secondCue =
                        "Return to stance before switching to the lead-side kick."
                ),
                lesson(
                    id = "muaythai_developing_teep_entries_5",
                    title = "Double-Teep Rhythm",
                    subtitle =
                        "Control distance with repeated teep attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Lead Teep - Lead Teep - Jab - Cross",
                    firstCue =
                        "Reset the lead foot after each teep without crossing your stance.",
                    secondMoves =
                        "Lead Teep - Rear Teep - Rear Low Kick",
                    secondCue =
                        "Recover your balance between every leg attack."
                ),
                lesson(
                    id = "muaythai_developing_teep_entries_6",
                    title = "Teep Setup Review",
                    subtitle =
                        "Combine real teeps, feints and follow-up attacks.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Lead Teep - Lead Teep Feint - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Keep the real teep and feint visually similar.",
                    secondMoves =
                        "Rear Teep Feint - Cross - Lead Hook - Rear Body Kick - Step Back",
                    secondCue =
                        "Enter behind the feint and leave range under control."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — KICK CHAINS AND TARGET CHANGES
    // ---------------------------------------------------------

    private fun kickChainsAndTargetChanges():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_developing_kick_chains"

        return chapter(
            id = chapterId,
            title = "Kick Chains & Target Changes",
            subtitle =
                "Link kicks together while changing sides and targets.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_kick_chains_1",
                    title = "Rear Low-to-Body Kick",
                    subtitle =
                        "Use the same rear side to change kicking targets.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Rear Low Kick - Jab - Rear Body Kick",
                    firstCue =
                        "Return the rear foot to stance before repeating the attack.",
                    secondMoves =
                        "Cross - Rear Low Kick - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Maintain the same rhythm while changing targets."
                ),
                lesson(
                    id = "muaythai_developing_kick_chains_2",
                    title = "Lead Low-to-Rear Body Kick",
                    subtitle =
                        "Connect opposite-side kicks through stable stance recovery.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick",
                    firstCue =
                        "Recover the lead leg before transferring weight into the cross.",
                    secondMoves =
                        "Jab - Lead Low Kick - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep your feet beneath you during the side change."
                ),
                lesson(
                    id = "muaythai_developing_kick_chains_3",
                    title = "Body-to-Low Kick Change",
                    subtitle =
                        "Attack the body before returning to the leg.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Rear Low Kick",
                    firstCue =
                        "Reset the rear leg before repeating from the same side.",
                    secondMoves =
                        "Lead Body Kick - Cross - Rear Low Kick",
                    secondCue =
                        "Land in stance before driving the cross forward."
                ),
                lesson(
                    id = "muaythai_developing_kick_chains_4",
                    title = "Switch-Kick Chains",
                    subtitle =
                        "Use the switch step without widening or crossing your stance.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Jab - Switch Lead Body Kick - Cross",
                    firstCue =
                        "Keep the switch compact and recover before punching.",
                    secondMoves =
                        "Rear Low Kick - Jab - Switch Lead Body Kick",
                    secondCue =
                        "Rebuild your stance before beginning the switch."
                ),
                lesson(
                    id = "muaythai_developing_kick_chains_5",
                    title = "High-Kick Setup",
                    subtitle =
                        "Prepare controlled high kicks behind lower attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Rear Low Kick - Jab - Rear High Kick",
                    firstCue =
                        "Kick only within a comfortable range and maintain full control.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Hook - Rear High Kick",
                    secondCue =
                        "Keep the support foot stable and return safely to stance."
                ),
                lesson(
                    id = "muaythai_developing_kick_chains_6",
                    title = "Kick-Chain Review",
                    subtitle =
                        "Review side changes, level changes and safe recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick - Lead Teep",
                    firstCue =
                        "Recover fully between kicks and finish by controlling distance.",
                    secondMoves =
                        "Jab - Switch Lead Body Kick - Cross - Rear Low Kick - Step Back",
                    secondCue =
                        "Keep the switch compact and exit without crossing your feet."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — DEFENCE AND IMMEDIATE RETURNS
    // ---------------------------------------------------------

    private fun defenceAndImmediateReturns():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_developing_defensive_returns"

        return chapter(
            id = chapterId,
            title = "Defence & Immediate Returns",
            subtitle =
                "Recover from clear defensive actions and return safely.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_defensive_returns_1",
                    title = "Lead Check Return",
                    subtitle =
                        "Return immediately after defending with the lead leg.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Set the lead foot down under control before throwing the cross.",
                    secondMoves =
                        "Lead Check - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Rebuild your stance before beginning the return."
                ),
                lesson(
                    id = "muaythai_developing_defensive_returns_2",
                    title = "Rear Check Return",
                    subtitle =
                        "Recover the rear leg before countering.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Rear Check - Jab - Cross - Lead Low Kick",
                    firstCue =
                        "Return the rear foot to its original position before punching.",
                    secondMoves =
                        "Rear Check - Cross - Lead Hook - Rear Knee",
                    secondCue =
                        "Stay tall and stable as you move into knee range."
                ),
                lesson(
                    id = "muaythai_developing_defensive_returns_3",
                    title = "Long-Guard Exit",
                    subtitle =
                        "Create space with the long guard before returning.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Long Guard - Step Back - Lead Teep",
                    firstCue =
                        "Keep your rear hand protecting your face while creating distance.",
                    secondMoves =
                        "Long Guard - Step Back - Jab - Rear Body Kick",
                    secondCue =
                        "Complete the exit before beginning your return."
                ),
                lesson(
                    id = "muaythai_developing_defensive_returns_4",
                    title = "Slip Left Counter",
                    subtitle =
                        "Use Slip Left before returning with balanced offense.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Move your head left without allowing your feet to collapse.",
                    secondMoves =
                        "Slip Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Return your head to stance before rotating into the kick."
                ),
                lesson(
                    id = "muaythai_developing_defensive_returns_5",
                    title = "Slip Right Counter",
                    subtitle =
                        "Use Slip Right before returning from the rear side.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Keep Slip Right compact and maintain your high guard.",
                    secondMoves =
                        "Slip Right - Cross - Rear Body Kick",
                    secondCue =
                        "Recover your posture before releasing the kick."
                ),
                lesson(
                    id = "muaythai_developing_defensive_returns_6",
                    title = "Defensive Return Review",
                    subtitle =
                        "Combine checks, long guard and directional movement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover after the check and keep Slip Left compact.",
                    secondMoves =
                        "Long Guard - Step Back - Slip Right - Cross - Rear Body Kick",
                    secondCue =
                        "Create space first, then complete Slip Right before countering."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — CLOSE-RANGE KNEES AND ELBOWS
    // ---------------------------------------------------------

    private fun closeRangeKneesAndElbows():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_developing_close_range"

        return chapter(
            id = chapterId,
            title = "Close-Range Knees & Elbows",
            subtitle =
                "Develop controlled knee and elbow combinations in shadowboxing.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_close_range_1",
                    title = "Straight-Knee Entries",
                    subtitle =
                        "Enter knee range behind compact punches.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Rear Knee",
                    firstCue =
                        "Shorten the final step and stay tall through the knee.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Knee",
                    secondCue =
                        "Recover the lead foot without falling forward."
                ),
                lesson(
                    id = "muaythai_developing_close_range_2",
                    title = "Lead Knee to Rear Elbow",
                    subtitle =
                        "Connect the lead knee to a compact rear elbow.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Jab - Cross - Lead Knee - Rear Horizontal Elbow",
                    firstCue =
                        "Set the lead foot before rotating the rear elbow.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Knee - Rear Horizontal Elbow",
                    secondCue =
                        "Move through each range without rushing the transition."
                ),
                lesson(
                    id = "muaythai_developing_close_range_3",
                    title = "Rear Knee to Lead Elbow",
                    subtitle =
                        "Recover the rear knee into a lead-side elbow.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Rear Knee - Lead Horizontal Elbow",
                    firstCue =
                        "Return the rear foot before rotating your lead side.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Keep your free hand protecting your face."
                ),
                lesson(
                    id = "muaythai_developing_close_range_4",
                    title = "Horizontal-Elbow Chains",
                    subtitle =
                        "Link lead and rear elbows with controlled rotation.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Lead Horizontal Elbow - Rear Horizontal Elbow",
                    firstCue =
                        "Recover each elbow before rotating the opposite side.",
                    secondMoves =
                        "Cross - Lead Horizontal Elbow - Rear Knee",
                    secondCue =
                        "Stay compact and maintain your posture at close range."
                ),
                lesson(
                    id = "muaythai_developing_close_range_5",
                    title = "Long Guard to Knee",
                    subtitle =
                        "Use the long guard to create a controlled knee entry.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Long Guard - Rear Knee - Lead Horizontal Elbow",
                    firstCue =
                        "Keep the long guard structured as you lift the rear knee.",
                    secondMoves =
                        "Long Guard - Lead Knee - Rear Horizontal Elbow - Step Back",
                    secondCue =
                        "Recover the lead foot before completing the exit."
                ),
                lesson(
                    id = "muaythai_developing_close_range_6",
                    title = "Close-Range Review",
                    subtitle =
                        "Review controlled entries, knees, elbows and exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Lead Horizontal Elbow - Step Back",
                    firstCue =
                        "Stay compact during the attack and balanced during the exit.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Hook - Lead Knee - Rear Horizontal Elbow - Lead Teep",
                    secondCue =
                        "Move from long range to close range and finish by rebuilding distance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — PRESSURE, ANGLES AND EXITS
    // ---------------------------------------------------------

    private fun pressureAnglesAndExits():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_developing_pressure_angles"

        return chapter(
            id = chapterId,
            title = "Pressure, Angles & Exits",
            subtitle =
                "Advance with structure and leave exchanges safely.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_pressure_angles_1",
                    title = "Jab Pressure",
                    subtitle =
                        "Use repeated jabs to advance without losing stance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Step Forward - Jab - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Move your feet with the jabs and avoid reaching forward.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep your stance beneath you as pressure builds."
                ),
                lesson(
                    id = "muaythai_developing_pressure_angles_2",
                    title = "Teep Pressure",
                    subtitle =
                        "Use teeps to control space while advancing.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 220,
                    firstMoves =
                        "Lead Teep - Step Forward - Jab - Cross",
                    firstCue =
                        "Recover the teep before advancing behind your hands.",
                    secondMoves =
                        "Rear Teep - Step Forward - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Keep the step controlled and finish in stance."
                ),
                lesson(
                    id = "muaythai_developing_pressure_angles_3",
                    title = "Left-Angle Exit",
                    subtitle =
                        "Exit to the left after completing your attack.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Exit Left",
                    firstCue =
                        "Complete the kick before moving to the left.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Hook - Exit Left",
                    secondCue =
                        "Keep your feet separated as you leave the exchange."
                ),
                lesson(
                    id = "muaythai_developing_pressure_angles_4",
                    title = "Right-Angle Exit",
                    subtitle =
                        "Exit to the right while keeping your guard ready.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Jab - Lead Hook - Rear Body Kick - Exit Right",
                    firstCue =
                        "Recover the rear kick before moving to the right.",
                    secondMoves =
                        "Cross - Lead Knee - Rear Horizontal Elbow - Exit Right",
                    secondCue =
                        "Finish the close-range attack before creating the angle."
                ),
                lesson(
                    id = "muaythai_developing_pressure_angles_5",
                    title = "Pressure-to-Exit",
                    subtitle =
                        "Combine forward pressure with a deliberate exit.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Double Jab - Cross - Rear Body Kick - Exit Left",
                    firstCue =
                        "Advance behind the punches and leave after the kick recovers.",
                    secondMoves =
                        "Lead Teep - Jab - Cross - Rear Knee - Exit Right",
                    secondCue =
                        "Maintain posture while changing from long range to close range."
                ),
                lesson(
                    id = "muaythai_developing_pressure_angles_6",
                    title = "Ring-Craft Review",
                    subtitle =
                        "Review pressure, angle changes and controlled exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Step Forward - Jab - Cross - Rear Low Kick - Exit Left - Lead Teep",
                    firstCue =
                        "Pressure in stance, exit cleanly and rebuild distance.",
                    secondMoves =
                        "Lead Teep Feint - Cross - Lead Hook - Rear Knee - Exit Right",
                    secondCue =
                        "Use the feint to enter and keep your exit deliberate."
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
            "muaythai_developing_assessment"

        return chapter(
            id = chapterId,
            title = "Developing Assessment",
            subtitle =
                "Bring the Developing Muay Thai stage together.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "muaythai_developing_assessment_1",
                    title = "Combination Adaptation",
                    subtitle =
                        "Change your finish while preserving stance and rhythm.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep the punching entry identical before changing the finish.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Change the target without changing your balance."
                ),
                lesson(
                    id = "muaythai_developing_assessment_2",
                    title = "Teep and Range Review",
                    subtitle =
                        "Review teeps, feints and controlled entries.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Lead Teep - Lead Teep Feint - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Make the real teep and feint begin with the same posture.",
                    secondMoves =
                        "Rear Teep Feint - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover your stance before every range change."
                ),
                lesson(
                    id = "muaythai_developing_assessment_3",
                    title = "Kick-Chain Assessment",
                    subtitle =
                        "Review opposite-side kicks and target changes.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick - Lead Teep",
                    firstCue =
                        "Keep every landing controlled and finish by creating distance.",
                    secondMoves =
                        "Jab - Switch Lead Body Kick - Cross - Rear Low Kick",
                    secondCue =
                        "Maintain a compact switch and stable recovery."
                ),
                lesson(
                    id = "muaythai_developing_assessment_4",
                    title = "Defensive Return Assessment",
                    subtitle =
                        "Review checks, Slip Left and Slip Right returns.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover after the check and complete Slip Left before countering.",
                    secondMoves =
                        "Rear Check - Jab - Slip Right - Cross - Rear Body Kick",
                    secondCue =
                        "Return the rear foot and complete Slip Right without leaning."
                ),
                lesson(
                    id = "muaythai_developing_assessment_5",
                    title = "Close-Range Assessment",
                    subtitle =
                        "Review knee and elbow transitions with safe exits.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Lead Horizontal Elbow - Exit Left",
                    firstCue =
                        "Recover every close-range weapon before leaving the exchange.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Knee - Rear Horizontal Elbow - Step Back",
                    secondCue =
                        "Move through each range while keeping your guard structured."
                ),
                lesson(
                    id = "muaythai_developing_assessment_6",
                    title = "Muay Thai Developing Final",
                    subtitle =
                        "Complete the Muay Thai Developing stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 300,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Lead Hook - Rear Knee - Exit Left",
                    firstCue =
                        "Control distance, enter with balance and leave after recovering.",
                    secondMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick - Lead Teep Feint - Rear Body Kick - Exit Right",
                    secondCue =
                        "Defend clearly, rebuild your stance and control every transition."
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