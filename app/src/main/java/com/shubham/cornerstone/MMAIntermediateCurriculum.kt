package com.shubham.cornerstone

/**
 * MMA Intermediate Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Intermediate sessions.
 *
 * This stage develops:
 *
 * - adaptable MMA combinations
 * - linked takedown attacks
 * - defensive transition chains
 * - guard recovery systems
 * - positional control movement
 * - tactical pace and range management
 *
 * Ground movements should be practised on a suitable
 * padded surface with enough clear space.
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object MMAIntermediateCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MMA

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationAdaptation(),
            linkedTakedownAttacks(),
            defensiveTransitionChains(),
            guardRecoverySystems(),
            positionalControlSystems(),
            tacticalPaceAndRange(),
            intermediateAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION ADAPTATION
    // ---------------------------------------------------------

    private fun combinationAdaptation():
            TrainingChapterDefinition {

        val chapterId =
            "mma_intermediate_combination_adaptation"

        return chapter(
            id = chapterId,
            title = "Combination Adaptation",
            subtitle =
                "Change MMA attacks while preserving balance and structure.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_combination_adaptation_1",
                    title = "Changing the Finish",
                    subtitle =
                        "Use the same entry with different finishing attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Complete the lead hook before rotating into the low kick.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Level Change - Penetration Step",
                    secondCue =
                        "Finish the hook and rebuild your stance before lowering your level."
                ),
                lesson(
                    id = "mma_intermediate_combination_adaptation_2",
                    title = "Changing the Entry",
                    subtitle =
                        "Reach the same attack through different openings.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Lead Teep - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Recover the teep before entering with the cross.",
                    secondMoves =
                        "Level Change Feint - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Return to striking height before beginning the punches."
                ),
                lesson(
                    id = "mma_intermediate_combination_adaptation_3",
                    title = "Attack and Re-Attack",
                    subtitle =
                        "Recover from the first attack and immediately rebuild.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back - Cross - Lead Hook",
                    firstCue =
                        "Recover the kick before stepping back and beginning the re-attack.",
                    secondMoves =
                        "Jab - Level Change - Penetration Step - Return to Stance - Cross - Rear Knee",
                    secondCue =
                        "Return fully to your stance before throwing the cross."
                ),
                lesson(
                    id = "mma_intermediate_combination_adaptation_4",
                    title = "Long-to-Close Transition",
                    subtitle =
                        "Move from long-range strikes into compact attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Shorten your stance gradually as the combination moves closer.",
                    secondMoves =
                        "Rear Body Kick - Step Forward - Lead Hook - Cross - Lead Knee",
                    secondCue =
                        "Recover the body kick before stepping into close range."
                ),
                lesson(
                    id = "mma_intermediate_combination_adaptation_5",
                    title = "Directional Counter Layers",
                    subtitle =
                        "Use explicit directional defence before changing attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook - Level Change - Penetration Step",
                    firstCue =
                        "Complete Slip Left before countering and lowering your level.",
                    secondMoves =
                        "Slip Right - Cross - Rear Low Kick - Step Left - Lead Hook",
                    secondCue =
                        "Complete Slip Right and recover the kick before stepping left."
                ),
                lesson(
                    id = "mma_intermediate_combination_adaptation_6",
                    title = "Adaptive Combination Review",
                    subtitle =
                        "Review changing entries, finishes and re-attacks.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 270,
                    firstMoves =
                        "Lead Teep - Cross - Lead Hook - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Recover every strike before transitioning into the entry.",
                    secondMoves =
                        "Slip Left - Cross - Rear Knee - Step Back - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Complete Slip Left and keep every transition balanced."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — LINKED TAKEDOWN ATTACKS
    // ---------------------------------------------------------

    private fun linkedTakedownAttacks():
            TrainingChapterDefinition {

        val chapterId =
            "mma_intermediate_linked_takedown_attacks"

        return chapter(
            id = chapterId,
            title = "Linked Takedown Attacks",
            subtitle =
                "Connect controlled solo takedown entries and recovery movements.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_linked_takedown_attacks_1",
                    title = "Double-to-Single Transition",
                    subtitle =
                        "Change entry direction after the first takedown movement.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Level Change - Penetration Step - Step Outside - Shadow Single-Leg Entry - Return to Stance",
                    firstCue =
                        "Use a padded surface and keep both entry movements controlled.",
                    secondMoves =
                        "Jab - Cross - Penetration Step - Step Right - Shadow Single-Leg Entry",
                    secondCue =
                        "Finish the cross before lowering your level."
                ),
                lesson(
                    id = "mma_intermediate_linked_takedown_attacks_2",
                    title = "Single-to-Body-Lock Movement",
                    subtitle =
                        "Link an outside entry to close-range control movement.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 240,
                    firstMoves =
                        "Step Left - Shadow Single-Leg Entry - Return to Stance - Body-Lock Position",
                    firstCue =
                        "Keep your spine upright as you return into the body-lock position.",
                    secondMoves =
                        "Jab - Lead Hook - Step Outside - Shadow Single-Leg Entry - Body-Lock Position",
                    secondCue =
                        "Complete the outside step before changing your upper-body position."
                ),
                lesson(
                    id = "mma_intermediate_linked_takedown_attacks_3",
                    title = "Knee-Tap Entry Movement",
                    subtitle =
                        "Practise controlled level and angle changes.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Jab - Cross - Step Left - Level Change - Shadow Knee-Tap Entry",
                    firstCue =
                        "Keep the movement shallow and do not collapse your posture.",
                    secondMoves =
                        "Lead Teep - Cross - Step Right - Level Change - Shadow Knee-Tap Entry",
                    secondCue =
                        "Recover the teep completely before stepping right."
                ),
                lesson(
                    id = "mma_intermediate_linked_takedown_attacks_4",
                    title = "Entry Re-Attack",
                    subtitle =
                        "Return from a defended entry and attack again.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Penetration Step - Return to Stance - Cross - Lead Hook - Shadow Single-Leg Entry",
                    firstCue =
                        "Stand completely before beginning the striking re-attack.",
                    secondMoves =
                        "Shadow Single-Leg Entry - Exit Left - Jab - Cross - Penetration Step",
                    secondCue =
                        "Complete the left exit before rebuilding your punching stance."
                ),
                lesson(
                    id = "mma_intermediate_linked_takedown_attacks_5",
                    title = "Kick-to-Takedown Chain",
                    subtitle =
                        "Use controlled kicks to create takedown-entry movement.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Rear Low Kick - Jab - Cross - Level Change - Penetration Step",
                    firstCue =
                        "Recover the rear kick before entering behind the punches.",
                    secondMoves =
                        "Lead Teep - Step Right - Cross - Shadow Single-Leg Entry - Exit Left",
                    secondCue =
                        "Place the lead foot down safely before stepping right."
                ),
                lesson(
                    id = "mma_intermediate_linked_takedown_attacks_6",
                    title = "Takedown Chain Review",
                    subtitle =
                        "Review linked entries, changes and standing recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 275,
                    firstMoves =
                        "Jab - Cross - Penetration Step - Step Outside - Shadow Single-Leg Entry - Return to Stance",
                    firstCue =
                        "Move smoothly without driving the lead knee into the floor.",
                    secondMoves =
                        "Lead Teep - Cross - Level Change - Shadow Knee-Tap Entry - Exit Right - Rear Low Kick",
                    secondCue =
                        "Return to a stable stance before finishing with the kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — DEFENSIVE TRANSITION CHAINS
    // ---------------------------------------------------------

    private fun defensiveTransitionChains():
            TrainingChapterDefinition {

        val chapterId =
            "mma_intermediate_defensive_transition_chains"

        return chapter(
            id = chapterId,
            title = "Defensive Transition Chains",
            subtitle =
                "Link striking defence, takedown defence and recovery.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_defensive_transition_chains_1",
                    title = "Strike Defence Into Sprawl",
                    subtitle =
                        "Move from directional defence into takedown defence.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 240,
                    firstMoves =
                        "Slip Left - Cross - Sprawl - Circle Left - Return to Stance",
                    firstCue =
                        "Complete Slip Left before lowering into the sprawl.",
                    secondMoves =
                        "Slip Right - Lead Hook - Sprawl - Circle Right - Jab",
                    secondCue =
                        "Complete Slip Right and return to stance before the final jab."
                ),
                lesson(
                    id = "mma_intermediate_defensive_transition_chains_2",
                    title = "Down Block Re-Attack",
                    subtitle =
                        "Defend the entry line and immediately rebuild offense.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 245,
                    firstMoves =
                        "Jab - Down Block - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep the down block compact and return the hand to guard.",
                    secondMoves =
                        "Down Block - Step Left - Cross - Rear Knee - Exit Right",
                    secondCue =
                        "Complete each directional step before continuing."
                ),
                lesson(
                    id = "mma_intermediate_defensive_transition_chains_3",
                    title = "Sprawl-to-Stand Counter",
                    subtitle =
                        "Recover from the floor and restart your attack safely.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Sprawl - Circle Left - Technical Stand-Up - Jab - Cross",
                    firstCue =
                        "Use a padded surface and establish your stance before punching.",
                    secondMoves =
                        "Sprawl - Circle Right - Technical Stand-Up - Lead Teep",
                    secondCue =
                        "Stand fully and regain balance before extending the teep."
                ),
                lesson(
                    id = "mma_intermediate_defensive_transition_chains_4",
                    title = "Roll-to-Level Defence",
                    subtitle =
                        "Connect explicit rolling movement to entry defence.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Roll Left - Lead Hook - Down Block - Cross - Exit Left",
                    firstCue =
                        "Complete Roll Left before throwing the lead hook.",
                    secondMoves =
                        "Roll Right - Cross - Sprawl - Circle Right - Rear Knee",
                    secondCue =
                        "Complete Roll Right and rebuild your stance after the sprawl."
                ),
                lesson(
                    id = "mma_intermediate_defensive_transition_chains_5",
                    title = "Multi-Phase Defence",
                    subtitle =
                        "Respond to changing striking and wrestling threats.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Slip Left - Slip Right - Cross - Down Block - Lead Hook",
                    firstCue =
                        "Perform both slips separately and return your head to centre.",
                    secondMoves =
                        "Roll Left - Cross - Sprawl - Circle Left - Technical Stand-Up",
                    secondCue =
                        "Keep each defensive phase controlled and clearly separated."
                ),
                lesson(
                    id = "mma_intermediate_defensive_transition_chains_6",
                    title = "Defensive Chain Review",
                    subtitle =
                        "Review directional defence, sprawls and counters.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 280,
                    firstMoves =
                        "Slip Right - Cross - Rear Low Kick - Down Block - Lead Hook - Exit Left",
                    firstCue =
                        "Complete Slip Right and recover every attack before defending again.",
                    secondMoves =
                        "Roll Right - Cross - Sprawl - Circle Right - Technical Stand-Up - Jab - Rear Body Kick",
                    secondCue =
                        "Stand completely before beginning the final combination."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — GUARD RECOVERY SYSTEMS
    // ---------------------------------------------------------

    private fun guardRecoverySystems():
            TrainingChapterDefinition {

        val chapterId =
            "mma_intermediate_guard_recovery_systems"

        return chapter(
            id = chapterId,
            title = "Guard Recovery Systems",
            subtitle =
                "Link safe ground movement to standing recovery.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_guard_recovery_systems_1",
                    title = "Bridge-and-Escape Chain",
                    subtitle =
                        "Connect bridging direction to hip escape movement.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 245,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Seated Guard - Technical Stand-Up",
                    firstCue =
                        "Keep the movements controlled on a padded surface.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Combat Base - Technical Stand-Up",
                    secondCue =
                        "Build a stable base before standing."
                ),
                lesson(
                    id = "mma_intermediate_guard_recovery_systems_2",
                    title = "Double Hip Escape",
                    subtitle =
                        "Create additional space before returning to guard.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 250,
                    firstMoves =
                        "Hip Escape Left - Hip Escape Left - Seated Guard - Technical Stand-Up",
                    firstCue =
                        "Move your hips without dragging or twisting the knee.",
                    secondMoves =
                        "Hip Escape Right - Hip Escape Right - Combat Base - Technical Stand-Up",
                    secondCue =
                        "Keep your posting hand stable and away from your feet."
                ),
                lesson(
                    id = "mma_intermediate_guard_recovery_systems_3",
                    title = "Reverse Escape Recovery",
                    subtitle =
                        "Change hip direction and rebuild a safe base.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Reverse Hip Escape Left - Hip Escape Right - Seated Guard - Technical Stand-Up",
                    firstCue =
                        "Use small movements and keep your neck relaxed.",
                    secondMoves =
                        "Reverse Hip Escape Right - Hip Escape Left - Combat Base - Technical Stand-Up",
                    secondCue =
                        "Control the direction change before building your base."
                ),
                lesson(
                    id = "mma_intermediate_guard_recovery_systems_4",
                    title = "Sit-Through Recovery",
                    subtitle =
                        "Use rotational ground movement to regain standing position.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Sit-Through Left - Combat Base - Technical Stand-Up - Jab - Cross",
                    firstCue =
                        "Keep the supporting shoulder active and stand before punching.",
                    secondMoves =
                        "Sit-Through Right - Seated Guard - Technical Stand-Up - Lead Teep",
                    secondCue =
                        "Establish balance before extending the lead teep."
                ),
                lesson(
                    id = "mma_intermediate_guard_recovery_systems_5",
                    title = "Recovery Under Pressure",
                    subtitle =
                        "Link multiple escape directions before standing.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Reverse Hip Escape Left - Technical Stand-Up",
                    firstCue =
                        "Keep each movement deliberate and avoid rushing the transition.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Sit-Through Right - Technical Stand-Up - Cross",
                    secondCue =
                        "Return to your striking stance before throwing the cross."
                ),
                lesson(
                    id = "mma_intermediate_guard_recovery_systems_6",
                    title = "Guard Recovery Review",
                    subtitle =
                        "Review escape chains and standing re-entry.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 285,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Combat Base - Technical Stand-Up - Jab - Cross",
                    firstCue =
                        "Use a padded surface and control every change of position.",
                    secondMoves =
                        "Bridge Right - Reverse Hip Escape Left - Seated Guard - Technical Stand-Up - Lead Teep - Rear Low Kick",
                    secondCue =
                        "Stand fully and recover the teep before finishing with the kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — POSITIONAL CONTROL SYSTEMS
    // ---------------------------------------------------------

    private fun positionalControlSystems():
            TrainingChapterDefinition {

        val chapterId =
            "mma_intermediate_positional_control_systems"

        return chapter(
            id = chapterId,
            title = "Positional Control Systems",
            subtitle =
                "Develop stable solo movement between ground positions.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_positional_control_systems_1",
                    title = "Combat Base Transitions",
                    subtitle =
                        "Move through a stable base without losing posture.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 250,
                    firstMoves =
                        "Seated Guard - Combat Base - Knee Slide Left - Return to Combat Base",
                    firstCue =
                        "Keep your chest upright and move slowly on a padded surface.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Hip Switch Left - Return to Combat Base",
                    secondCue =
                        "Keep your supporting knee and foot comfortably aligned."
                ),
                lesson(
                    id = "mma_intermediate_positional_control_systems_2",
                    title = "Knee-Slide Direction Changes",
                    subtitle =
                        "Change direction while maintaining a controlled base.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 255,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Knee Slide Right",
                    firstCue =
                        "Control the hip switch and avoid dropping your weight suddenly.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Hip Switch Left - Knee Slide Left",
                    secondCue =
                        "Keep both directions smooth and symmetrical."
                ),
                lesson(
                    id = "mma_intermediate_positional_control_systems_3",
                    title = "Ground Strike Positioning",
                    subtitle =
                        "Coordinate controlled strike motions with a stable base.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Rear Ground Straight - Hip Switch Left",
                    firstCue =
                        "Shadow every strike and never punch the floor.",
                    secondMoves =
                        "Knee Slide Right - Combat Base - Rear Ground Straight - Lead Ground Straight",
                    secondCue =
                        "Maintain posture and stop each strike before floor contact."
                ),
                lesson(
                    id = "mma_intermediate_positional_control_systems_4",
                    title = "Sit-Through Offense",
                    subtitle =
                        "Connect rotational movement to controlled ground offense.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Sit-Through Left - Combat Base - Lead Ground Straight - Rear Ground Straight",
                    firstCue =
                        "Stabilize your base before beginning the strike motions.",
                    secondMoves =
                        "Sit-Through Right - Hip Switch Left - Rear Ground Straight - Return to Combat Base",
                    secondCue =
                        "Keep the strike controlled and avoid hitting the floor."
                ),
                lesson(
                    id = "mma_intermediate_positional_control_systems_5",
                    title = "Position-to-Standing Attack",
                    subtitle =
                        "Return safely from ground movement into striking range.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Combat Base - Technical Stand-Up - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Stand fully before beginning the punching combination.",
                    secondMoves =
                        "Sit-Through Left - Seated Guard - Technical Stand-Up - Lead Teep - Cross",
                    secondCue =
                        "Regain balance before extending the lead teep."
                ),
                lesson(
                    id = "mma_intermediate_positional_control_systems_6",
                    title = "Positional Control Review",
                    subtitle =
                        "Review ground transitions, offense and standing recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 290,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Lead Ground Straight - Rear Ground Straight",
                    firstCue =
                        "Maintain a stable base and never strike the floor.",
                    secondMoves =
                        "Sit-Through Right - Combat Base - Technical Stand-Up - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Rebuild your standing stance before finishing the attack."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — TACTICAL PACE AND RANGE
    // ---------------------------------------------------------

    private fun tacticalPaceAndRange():
            TrainingChapterDefinition {

        val chapterId =
            "mma_intermediate_tactical_pace_and_range"

        return chapter(
            id = chapterId,
            title = "Tactical Pace and Range",
            subtitle =
                "Control distance, tempo and transition timing.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_tactical_pace_and_range_1",
                    title = "Long-Range Control",
                    subtitle =
                        "Use straight attacks and exits to manage distance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 255,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Step Back - Lead Teep",
                    firstCue =
                        "Recover each teep and keep your stance underneath you.",
                    secondMoves =
                        "Jab - Rear Body Kick - Exit Left - Jab - Cross",
                    secondCue =
                        "Recover the body kick before completing the left exit."
                ),
                lesson(
                    id = "mma_intermediate_tactical_pace_and_range_2",
                    title = "Close-Range Control",
                    subtitle =
                        "Enter and leave close range without losing structure.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 260,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Knee - Exit Right",
                    firstCue =
                        "Keep the knee compact and complete the right exit.",
                    secondMoves =
                        "Lead Teep - Step Forward - Lead Hook - Cross - Lead Knee - Step Back",
                    secondCue =
                        "Recover every strike before changing range."
                ),
                lesson(
                    id = "mma_intermediate_tactical_pace_and_range_3",
                    title = "Broken Rhythm Attacks",
                    subtitle =
                        "Change tempo while keeping every movement controlled.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Use a brief controlled pause without relaxing your guard.",
                    secondMoves =
                        "Lead Teep - Pause - Cross - Level Change - Penetration Step",
                    secondCue =
                        "Keep the pause deliberate and return to stance before the entry."
                ),
                lesson(
                    id = "mma_intermediate_tactical_pace_and_range_4",
                    title = "Pressure and Exit",
                    subtitle =
                        "Build forward pressure and leave on a clear angle.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick - Exit Left",
                    firstCue =
                        "Recover the body kick before completing the left exit.",
                    secondMoves =
                        "Jab - Cross - Rear Knee - Step Right - Cross - Step Back",
                    secondCue =
                        "Keep your posture stable while changing direction."
                ),
                lesson(
                    id = "mma_intermediate_tactical_pace_and_range_5",
                    title = "Range-to-Level Change",
                    subtitle =
                        "Use distance changes to disguise wrestling entries.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Lead Teep - Step Back - Jab - Cross - Level Change - Penetration Step",
                    firstCue =
                        "Recover the teep and complete the punches before lowering your level.",
                    secondMoves =
                        "Rear Low Kick - Exit Right - Level Change Feint - Cross - Shadow Single-Leg Entry",
                    secondCue =
                        "Recover the kick and return to striking height after the feint."
                ),
                lesson(
                    id = "mma_intermediate_tactical_pace_and_range_6",
                    title = "Tactical Range Review",
                    subtitle =
                        "Review long range, close range, tempo and exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 295,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick - Step Back - Level Change - Penetration Step",
                    firstCue =
                        "Keep every range change deliberate and balanced.",
                    secondMoves =
                        "Slip Left - Cross - Lead Hook - Rear Knee - Exit Right - Rear Body Kick",
                    secondCue =
                        "Complete Slip Left and recover the knee before exiting."
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
            "mma_intermediate_assessment"

        return chapter(
            id = chapterId,
            title = "Intermediate Assessment",
            subtitle =
                "Bring your Intermediate MMA systems together.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "mma_intermediate_assessment_1",
                    title = "Adaptive Combination Review",
                    subtitle =
                        "Review changing entries, finishes and re-attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Lead Teep - Cross - Lead Hook - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Recover each strike before changing into the wrestling entry.",
                    secondMoves =
                        "Slip Right - Cross - Rear Knee - Step Back - Jab - Rear Body Kick",
                    secondCue =
                        "Complete Slip Right and preserve your stance through every transition."
                ),
                lesson(
                    id = "mma_intermediate_assessment_2",
                    title = "Linked Takedown Review",
                    subtitle =
                        "Review entry changes and standing recovery.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Jab - Cross - Penetration Step - Step Outside - Shadow Single-Leg Entry - Return to Stance",
                    firstCue =
                        "Use a padded surface and keep the entry movements controlled.",
                    secondMoves =
                        "Lead Teep - Cross - Shadow Knee-Tap Entry - Exit Left - Rear Low Kick",
                    secondCue =
                        "Return to a stable stance before finishing with the kick."
                ),
                lesson(
                    id = "mma_intermediate_assessment_3",
                    title = "Defensive Transition Review",
                    subtitle =
                        "Review directional defence and wrestling recovery.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Slip Left - Cross - Down Block - Lead Hook - Sprawl - Circle Left",
                    firstCue =
                        "Complete Slip Left and every defensive movement separately.",
                    secondMoves =
                        "Roll Right - Cross - Sprawl - Circle Right - Technical Stand-Up - Jab",
                    secondCue =
                        "Complete Roll Right and stand fully before the final jab."
                ),
                lesson(
                    id = "mma_intermediate_assessment_4",
                    title = "Guard Recovery Review",
                    subtitle =
                        "Review ground escapes and standing re-entry.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Combat Base - Technical Stand-Up",
                    firstCue =
                        "Control every ground movement on a suitable padded surface.",
                    secondMoves =
                        "Bridge Right - Reverse Hip Escape Left - Seated Guard - Technical Stand-Up - Lead Teep",
                    secondCue =
                        "Establish standing balance before extending the teep."
                ),
                lesson(
                    id = "mma_intermediate_assessment_5",
                    title = "Positional Control Review",
                    subtitle =
                        "Review positional movement and controlled offense.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Combat Base - Knee Slide Right - Hip Switch Left - Lead Ground Straight - Rear Ground Straight",
                    firstCue =
                        "Maintain a stable base and never strike the floor.",
                    secondMoves =
                        "Sit-Through Right - Combat Base - Technical Stand-Up - Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Stand completely before beginning the final combination."
                ),
                lesson(
                    id = "mma_intermediate_assessment_6",
                    title = "MMA Intermediate Final",
                    subtitle =
                        "Complete the MMA Intermediate stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 350,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick - Level Change - Penetration Step - Return to Stance - Sprawl - Circle Left",
                    firstCue =
                        "Control every transition between striking, wrestling and recovery.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Sit-Through Right - Technical Stand-Up - Slip Left - Cross - Lead Hook - Rear Body Kick - Exit Right",
                    secondCue =
                        "Stand completely before performing Slip Left and beginning the final attack."
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