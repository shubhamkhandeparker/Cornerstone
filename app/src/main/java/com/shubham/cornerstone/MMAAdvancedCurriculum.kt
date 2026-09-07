package com.shubham.cornerstone

/**
 * MMA Advanced Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Advanced sessions.
 *
 * This stage develops:
 *
 * - advanced combination architecture
 * - chained takedown-entry systems
 * - defensive re-attack systems
 * - ground escape decision chains
 * - positional offense transitions
 * - integrated MMA fight management
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
object MMAAdvancedCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MMA

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationArchitecture(),
            takedownChainArchitecture(),
            defensiveReAttackSystems(),
            groundEscapeDecisionChains(),
            positionalOffenseArchitecture(),
            integratedFightManagement(),
            advancedAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION ARCHITECTURE
    // ---------------------------------------------------------

    private fun combinationArchitecture():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_combination_architecture"

        return chapter(
            id = chapterId,
            title = "Combination Architecture",
            subtitle =
                "Construct adaptable attacks across every MMA range.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_combination_architecture_1",
                    title = "Layered Entries",
                    subtitle =
                        "Build multiple threats from a single opening.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Jab - Level Change Feint - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Return to striking height before beginning the cross.",
                    secondMoves =
                        "Lead Teep - Jab - Cross - Level Change - Penetration Step",
                    secondCue =
                        "Recover the teep and finish both punches before lowering your level."
                ),
                lesson(
                    id = "mma_advanced_combination_architecture_2",
                    title = "Multi-Range Sequences",
                    subtitle =
                        "Connect long, middle and close-range attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 285,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Lead Hook - Rear Knee - Exit Left",
                    firstCue =
                        "Shorten the range gradually and complete the left exit.",
                    secondMoves =
                        "Rear Body Kick - Step Forward - Cross - Lead Hook - Lead Knee - Step Back",
                    secondCue =
                        "Recover the kick before stepping into close range."
                ),
                lesson(
                    id = "mma_advanced_combination_architecture_3",
                    title = "Feint-to-Chain Attacks",
                    subtitle =
                        "Use controlled feints to connect striking and wrestling.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Level Change Feint - Cross - Lead Hook - Rear Body Kick - Penetration Step",
                    firstCue =
                        "Rebuild your stance after the body kick before beginning the entry.",
                    secondMoves =
                        "Jab Feint - Rear Low Kick - Cross - Level Change - Shadow Single-Leg Entry",
                    secondCue =
                        "Keep the feint compact and recover every attack."
                ),
                lesson(
                    id = "mma_advanced_combination_architecture_4",
                    title = "Re-Attack Architecture",
                    subtitle =
                        "Build a second attack immediately after recovery.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Recover the low kick before stepping back into the re-attack.",
                    secondMoves =
                        "Penetration Step - Return to Stance - Jab - Cross - Rear Body Kick - Exit Right",
                    secondCue =
                        "Stand completely before beginning the punching sequence."
                ),
                lesson(
                    id = "mma_advanced_combination_architecture_5",
                    title = "Directional Counter Architecture",
                    subtitle =
                        "Build advanced attacks from explicit directional defence.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Complete Slip Left and recover the kick before lowering your level.",
                    secondMoves =
                        "Roll Right - Cross - Rear Knee - Step Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Complete Roll Right and remain balanced through the left step."
                ),
                lesson(
                    id = "mma_advanced_combination_architecture_6",
                    title = "Combination Architecture Review",
                    subtitle =
                        "Review layered attacks, range changes and re-attacks.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 325,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick - Level Change Feint - Lead Hook - Rear Knee",
                    firstCue =
                        "Control every range change without crossing your feet.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Rear Body Kick - Step Back - Penetration Step - Return to Stance",
                    secondCue =
                        "Complete Slip Right and maintain structure throughout the sequence."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — TAKEDOWN CHAIN ARCHITECTURE
    // ---------------------------------------------------------

    private fun takedownChainArchitecture():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_takedown_chain_architecture"

        return chapter(
            id = chapterId,
            title = "Takedown Chain Architecture",
            subtitle =
                "Link multiple solo entries, angle changes and recoveries.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_takedown_chain_architecture_1",
                    title = "Double-to-Single Re-Chain",
                    subtitle =
                        "Change entry direction and return to the first attack.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 285,
                    firstMoves =
                        "Penetration Step - Step Outside - Shadow Single-Leg Entry - Return to Stance - Penetration Step",
                    firstCue =
                        "Use a padded surface and place the lead knee down gently.",
                    secondMoves =
                        "Jab - Cross - Penetration Step - Exit Left - Shadow Single-Leg Entry",
                    secondCue =
                        "Finish the punches and complete the left exit before changing entries."
                ),
                lesson(
                    id = "mma_advanced_takedown_chain_architecture_2",
                    title = "Single-to-Body-Lock Chain",
                    subtitle =
                        "Connect an outside entry to close-range control movement.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Shadow Single-Leg Entry - Return to Stance - Step Forward - Body-Lock Position",
                    firstCue =
                        "Return upright before moving into the body-lock position.",
                    secondMoves =
                        "Jab - Lead Hook - Step Outside - Shadow Single-Leg Entry - Body-Lock Position - Exit Right",
                    secondCue =
                        "Control the outside step and maintain an upright spine."
                ),
                lesson(
                    id = "mma_advanced_takedown_chain_architecture_3",
                    title = "Knee-Tap Re-Direction",
                    subtitle =
                        "Change the entry angle when the first line closes.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Level Change - Shadow Knee-Tap Entry - Step Left - Shadow Single-Leg Entry",
                    firstCue =
                        "Keep both entry movements shallow and controlled.",
                    secondMoves =
                        "Lead Teep - Cross - Shadow Knee-Tap Entry - Exit Right - Penetration Step",
                    secondCue =
                        "Recover each position before changing direction."
                ),
                lesson(
                    id = "mma_advanced_takedown_chain_architecture_4",
                    title = "Strike-Entry Re-Attack",
                    subtitle =
                        "Return to strikes between chained takedown entries.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Penetration Step - Return to Stance - Cross - Lead Hook - Shadow Single-Leg Entry",
                    firstCue =
                        "Stand completely before throwing the cross.",
                    secondMoves =
                        "Shadow Single-Leg Entry - Exit Left - Jab - Rear Low Kick - Level Change",
                    secondCue =
                        "Recover the entry and low kick before the final level change."
                ),
                lesson(
                    id = "mma_advanced_takedown_chain_architecture_5",
                    title = "Long Combination Entry",
                    subtitle =
                        "Hide wrestling movement behind layered striking.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 315,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Finish every strike and recover the kick before entering.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Hook - Rear Knee - Step Right - Shadow Single-Leg Entry",
                    secondCue =
                        "Recover the knee and complete the right step before the entry."
                ),
                lesson(
                    id = "mma_advanced_takedown_chain_architecture_6",
                    title = "Takedown Architecture Review",
                    subtitle =
                        "Review entry chains, direction changes and re-attacks.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 330,
                    firstMoves =
                        "Jab - Cross - Penetration Step - Step Outside - Shadow Single-Leg Entry - Body-Lock Position - Exit Left",
                    firstCue =
                        "Move smoothly while protecting the knees and maintaining posture.",
                    secondMoves =
                        "Lead Teep - Rear Low Kick - Level Change - Shadow Knee-Tap Entry - Exit Right - Penetration Step",
                    secondCue =
                        "Recover every strike before beginning the linked entries."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — DEFENSIVE RE-ATTACK SYSTEMS
    // ---------------------------------------------------------

    private fun defensiveReAttackSystems():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_defensive_re_attack_systems"

        return chapter(
            id = chapterId,
            title = "Defensive Re-Attack Systems",
            subtitle =
                "Defend multiple phases and rebuild controlled offense.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_defensive_re_attack_systems_1",
                    title = "Slip-to-Sprawl Re-Attack",
                    subtitle =
                        "Link directional head movement to takedown defence.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Slip Left - Cross - Sprawl - Circle Left - Technical Stand-Up - Jab",
                    firstCue =
                        "Complete Slip Left and stand fully before the final jab.",
                    secondMoves =
                        "Slip Right - Lead Hook - Sprawl - Circle Right - Cross - Rear Low Kick",
                    secondCue =
                        "Complete Slip Right and rebuild your stance after the sprawl."
                ),
                lesson(
                    id = "mma_advanced_defensive_re_attack_systems_2",
                    title = "Roll-to-Down-Block Chain",
                    subtitle =
                        "Connect explicit rolling movement to entry defence.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 295,
                    firstMoves =
                        "Roll Left - Lead Hook - Down Block - Cross - Rear Knee",
                    firstCue =
                        "Complete Roll Left before the hook and return the blocking hand to guard.",
                    secondMoves =
                        "Roll Right - Cross - Down Block - Step Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Complete Roll Right and each defensive movement separately."
                ),
                lesson(
                    id = "mma_advanced_defensive_re_attack_systems_3",
                    title = "Sprawl Direction Change",
                    subtitle =
                        "Recover from takedown defence on different angles.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Sprawl - Circle Left - Return to Stance - Cross - Lead Hook - Exit Right",
                    firstCue =
                        "Use a padded surface and stand completely before countering.",
                    secondMoves =
                        "Sprawl - Circle Right - Technical Stand-Up - Jab - Rear Body Kick - Exit Left",
                    secondCue =
                        "Rebuild your stance before beginning the final attack."
                ),
                lesson(
                    id = "mma_advanced_defensive_re_attack_systems_4",
                    title = "Layered Defensive Response",
                    subtitle =
                        "Respond to changing striking and wrestling threats.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Slip Left - Slip Right - Cross - Down Block - Lead Hook - Sprawl",
                    firstCue =
                        "Complete both slips separately and return your head to centre.",
                    secondMoves =
                        "Roll Left - Roll Right - Cross - Sprawl - Circle Left - Technical Stand-Up",
                    secondCue =
                        "Complete both rolls separately before lowering into the sprawl."
                ),
                lesson(
                    id = "mma_advanced_defensive_re_attack_systems_5",
                    title = "Defence-to-Entry Counter",
                    subtitle =
                        "Use defensive recovery to create a takedown entry.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 320,
                    firstMoves =
                        "Slip Right - Cross - Down Block - Level Change - Penetration Step",
                    firstCue =
                        "Complete Slip Right and return the blocking hand before entering.",
                    secondMoves =
                        "Roll Right - Lead Hook - Sprawl - Circle Right - Shadow Single-Leg Entry",
                    secondCue =
                        "Complete Roll Right and rebuild your base before the entry."
                ),
                lesson(
                    id = "mma_advanced_defensive_re_attack_systems_6",
                    title = "Defensive Re-Attack Review",
                    subtitle =
                        "Review directional defence, recovery and layered counters.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 335,
                    firstMoves =
                        "Slip Left - Cross - Rear Low Kick - Down Block - Lead Hook - Sprawl - Circle Left",
                    firstCue =
                        "Complete Slip Left and recover every attack before defending again.",
                    secondMoves =
                        "Roll Right - Cross - Sprawl - Technical Stand-Up - Jab - Cross - Rear Knee - Exit Right",
                    secondCue =
                        "Complete Roll Right and stand fully before the final combination."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — GROUND ESCAPE DECISION CHAINS
    // ---------------------------------------------------------

    private fun groundEscapeDecisionChains():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_ground_escape_decision_chains"

        return chapter(
            id = chapterId,
            title = "Ground Escape Decision Chains",
            subtitle =
                "Change ground recovery direction while maintaining control.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_ground_escape_decision_chains_1",
                    title = "Bridge Direction Change",
                    subtitle =
                        "Change escape direction after the first bridge.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 295,
                    firstMoves =
                        "Bridge Left - Bridge Right - Hip Escape Left - Seated Guard",
                    firstCue =
                        "Use a padded surface and keep your neck relaxed.",
                    secondMoves =
                        "Bridge Right - Bridge Left - Hip Escape Right - Combat Base",
                    secondCue =
                        "Control each bridge before changing direction."
                ),
                lesson(
                    id = "mma_advanced_ground_escape_decision_chains_2",
                    title = "Multi-Direction Hip Escape",
                    subtitle =
                        "Create space through several controlled escape directions.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 300,
                    firstMoves =
                        "Hip Escape Left - Reverse Hip Escape Right - Hip Escape Left - Seated Guard",
                    firstCue =
                        "Move the hips without forcing or twisting the knees.",
                    secondMoves =
                        "Hip Escape Right - Reverse Hip Escape Left - Hip Escape Right - Combat Base",
                    secondCue =
                        "Keep each direction change small and controlled."
                ),
                lesson(
                    id = "mma_advanced_ground_escape_decision_chains_3",
                    title = "Sit-Through Decision Chain",
                    subtitle =
                        "Change rotational movement before rebuilding your base.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Sit-Through Left - Return to Base - Sit-Through Right - Combat Base",
                    firstCue =
                        "Keep the supporting shoulder active through both directions.",
                    secondMoves =
                        "Sit-Through Right - Hip Escape Left - Seated Guard - Technical Stand-Up",
                    secondCue =
                        "Control the hip escape before beginning the stand-up."
                ),
                lesson(
                    id = "mma_advanced_ground_escape_decision_chains_4",
                    title = "Escape-to-Sprawl Recovery",
                    subtitle =
                        "Return from ground movement into immediate defence.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 315,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Technical Stand-Up - Sprawl - Circle Left",
                    firstCue =
                        "Stand completely before lowering into the sprawl.",
                    secondMoves =
                        "Sit-Through Right - Combat Base - Technical Stand-Up - Down Block - Cross",
                    secondCue =
                        "Rebuild your stance before performing the down block."
                ),
                lesson(
                    id = "mma_advanced_ground_escape_decision_chains_5",
                    title = "Escape-to-Offense Chain",
                    subtitle =
                        "Turn controlled ground recovery into standing offense.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 325,
                    firstMoves =
                        "Bridge Right - Hip Escape Left - Technical Stand-Up - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Stand fully and establish balance before punching.",
                    secondMoves =
                        "Reverse Hip Escape Right - Sit-Through Left - Combat Base - Technical Stand-Up - Lead Teep",
                    secondCue =
                        "Control every ground position before extending the teep."
                ),
                lesson(
                    id = "mma_advanced_ground_escape_decision_chains_6",
                    title = "Ground Decision Review",
                    subtitle =
                        "Review directional escapes and standing recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 340,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Reverse Hip Escape Left - Sit-Through Right - Technical Stand-Up - Jab - Cross",
                    firstCue =
                        "Use a padded surface and control every position change.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Combat Base - Technical Stand-Up - Slip Right - Cross - Rear Body Kick",
                    secondCue =
                        "Stand completely before performing Slip Right and countering."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — POSITIONAL OFFENSE ARCHITECTURE
    // ---------------------------------------------------------

    private fun positionalOffenseArchitecture():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_positional_offense_architecture"

        return chapter(
            id = chapterId,
            title = "Positional Offense Architecture",
            subtitle =
                "Connect stable ground positions to controlled offense.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_positional_offense_architecture_1",
                    title = "Multi-Position Movement",
                    subtitle =
                        "Move through several ground positions without losing posture.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 300,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Sit-Through Left - Return to Combat Base",
                    firstCue =
                        "Use a padded surface and keep every transition controlled.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Hip Switch Left - Sit-Through Right - Seated Guard",
                    secondCue =
                        "Maintain posture through each direction change."
                ),
                lesson(
                    id = "mma_advanced_positional_offense_architecture_2",
                    title = "Ground Strike Repositioning",
                    subtitle =
                        "Change position between controlled ground strike motions.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 305,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Hip Switch Left - Rear Ground Straight",
                    firstCue =
                        "Shadow every strike and never punch the floor.",
                    secondMoves =
                        "Knee Slide Right - Lead Ground Straight - Sit-Through Left - Rear Ground Straight",
                    secondCue =
                        "Stabilize each position before beginning the strike motion."
                ),
                lesson(
                    id = "mma_advanced_positional_offense_architecture_3",
                    title = "Knee-Slide Attack Chain",
                    subtitle =
                        "Connect knee-slide directions to controlled offense.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 315,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Lead Ground Straight - Rear Ground Straight - Hip Switch Right",
                    firstCue =
                        "Keep your supporting knee aligned and never strike the floor.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Rear Ground Straight - Lead Ground Straight - Sit-Through Left",
                    secondCue =
                        "Maintain a stable base through the direction change."
                ),
                lesson(
                    id = "mma_advanced_positional_offense_architecture_4",
                    title = "Position Re-Attack",
                    subtitle =
                        "Reposition and restart controlled ground offense.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 320,
                    firstMoves =
                        "Lead Ground Straight - Rear Ground Straight - Hip Switch Left - Lead Ground Straight",
                    firstCue =
                        "Stop each shadow strike safely before floor contact.",
                    secondMoves =
                        "Rear Ground Straight - Sit-Through Right - Combat Base - Lead Ground Straight - Rear Ground Straight",
                    secondCue =
                        "Rebuild your base before beginning the second attack."
                ),
                lesson(
                    id = "mma_advanced_positional_offense_architecture_5",
                    title = "Ground-to-Standing Re-Attack",
                    subtitle =
                        "Transition from positional offense into standing attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 330,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Rear Ground Straight - Technical Stand-Up - Jab - Rear Low Kick",
                    firstCue =
                        "Stand completely before beginning the jab and kick.",
                    secondMoves =
                        "Sit-Through Left - Combat Base - Technical Stand-Up - Cross - Lead Hook - Rear Knee",
                    secondCue =
                        "Establish your standing stance before the final combination."
                ),
                lesson(
                    id = "mma_advanced_positional_offense_architecture_6",
                    title = "Positional Architecture Review",
                    subtitle =
                        "Review ground position changes and standing recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 345,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Lead Ground Straight - Rear Ground Straight - Sit-Through Left",
                    firstCue =
                        "Maintain a stable base and never strike the floor.",
                    secondMoves =
                        "Sit-Through Right - Combat Base - Technical Stand-Up - Jab - Cross - Rear Body Kick - Exit Left",
                    secondCue =
                        "Stand fully before beginning the final striking sequence."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — INTEGRATED FIGHT MANAGEMENT
    // ---------------------------------------------------------

    private fun integratedFightManagement():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_integrated_fight_management"

        return chapter(
            id = chapterId,
            title = "Integrated Fight Management",
            subtitle =
                "Control pace, range, transitions and recovery.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_integrated_fight_management_1",
                    title = "Range Command",
                    subtitle =
                        "Control long, middle and close range in one sequence.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 305,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Lead Hook - Rear Knee - Step Back",
                    firstCue =
                        "Shorten the range gradually and recover before stepping back.",
                    secondMoves =
                        "Rear Body Kick - Exit Left - Jab - Cross - Level Change",
                    secondCue =
                        "Recover the kick before completing the left exit."
                ),
                lesson(
                    id = "mma_advanced_integrated_fight_management_2",
                    title = "Pace Command",
                    subtitle =
                        "Change tempo without abandoning defensive structure.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 310,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook - Rear Low Kick - Step Back",
                    firstCue =
                        "Use a brief controlled pause while maintaining your guard.",
                    secondMoves =
                        "Lead Teep - Pause - Level Change Feint - Cross - Rear Knee",
                    secondCue =
                        "Return to striking height before throwing the cross."
                ),
                lesson(
                    id = "mma_advanced_integrated_fight_management_3",
                    title = "Pressure-to-Entry Control",
                    subtitle =
                        "Use forward pressure to create a wrestling transition.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 320,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Recover the kick before lowering your level.",
                    secondMoves =
                        "Double Jab - Cross - Rear Knee - Step Right - Shadow Single-Leg Entry",
                    secondCue =
                        "Recover the knee and complete the right step before entering."
                ),
                lesson(
                    id = "mma_advanced_integrated_fight_management_4",
                    title = "Defensive Pace Reset",
                    subtitle =
                        "Use defence and movement to reset the exchange.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 325,
                    firstMoves =
                        "Slip Left - Cross - Exit Right - Lead Teep - Step Back",
                    firstCue =
                        "Complete Slip Left and every directional movement clearly.",
                    secondMoves =
                        "Roll Right - Lead Hook - Sprawl - Circle Left - Technical Stand-Up",
                    secondCue =
                        "Complete Roll Right and stand fully after the sprawl."
                ),
                lesson(
                    id = "mma_advanced_integrated_fight_management_5",
                    title = "Full-Phase Re-Attack",
                    subtitle =
                        "Move through striking, wrestling, defence and recovery.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 335,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Penetration Step - Return to Stance - Sprawl - Circle Right",
                    firstCue =
                        "Control every transition and rebuild your base between phases.",
                    secondMoves =
                        "Lead Teep - Cross - Shadow Single-Leg Entry - Exit Left - Slip Right - Cross - Rear Knee",
                    secondCue =
                        "Complete the left exit before performing Slip Right."
                ),
                lesson(
                    id = "mma_advanced_integrated_fight_management_6",
                    title = "Fight Management Review",
                    subtitle =
                        "Review range, pace, pressure and recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 350,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Level Change - Penetration Step - Return to Stance",
                    firstCue =
                        "Control every range change and maintain a stable stance.",
                    secondMoves =
                        "Slip Left - Cross - Lead Hook - Rear Knee - Sprawl - Circle Left - Technical Stand-Up - Jab",
                    secondCue =
                        "Complete Slip Left and stand fully before the final jab."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 7 — ADVANCED ASSESSMENT
    // ---------------------------------------------------------

    private fun advancedAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "mma_advanced_assessment"

        return chapter(
            id = chapterId,
            title = "Advanced Assessment",
            subtitle =
                "Complete the full MMA Fight Path.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "mma_advanced_assessment_1",
                    title = "Combination Architecture Test",
                    subtitle =
                        "Review advanced layered MMA attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 320,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Lead Hook - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Recover every attack before transitioning into the entry.",
                    secondMoves =
                        "Slip Right - Cross - Rear Body Kick - Step Back - Lead Hook - Rear Knee - Exit Left",
                    secondCue =
                        "Complete Slip Right and keep every range change balanced."
                ),
                lesson(
                    id = "mma_advanced_assessment_2",
                    title = "Takedown Architecture Test",
                    subtitle =
                        "Review chained entries and direction changes.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 330,
                    firstMoves =
                        "Jab - Cross - Penetration Step - Step Outside - Shadow Single-Leg Entry - Body-Lock Position - Exit Right",
                    firstCue =
                        "Use a padded surface and maintain posture throughout.",
                    secondMoves =
                        "Lead Teep - Cross - Shadow Knee-Tap Entry - Exit Left - Penetration Step - Return to Stance",
                    secondCue =
                        "Recover each position before changing the entry."
                ),
                lesson(
                    id = "mma_advanced_assessment_3",
                    title = "Defensive Re-Attack Test",
                    subtitle =
                        "Review layered defence and controlled counters.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 340,
                    firstMoves =
                        "Slip Left - Cross - Down Block - Lead Hook - Sprawl - Circle Left - Technical Stand-Up",
                    firstCue =
                        "Complete Slip Left and each defensive phase separately.",
                    secondMoves =
                        "Roll Right - Cross - Rear Low Kick - Sprawl - Circle Right - Jab - Rear Knee",
                    secondCue =
                        "Complete Roll Right and rebuild your stance after the sprawl."
                ),
                lesson(
                    id = "mma_advanced_assessment_4",
                    title = "Ground Decision Test",
                    subtitle =
                        "Review escape choices and standing recovery.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 350,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Reverse Hip Escape Left - Sit-Through Right - Technical Stand-Up",
                    firstCue =
                        "Use a padded surface and control every ground movement.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Combat Base - Technical Stand-Up - Slip Right - Cross - Rear Body Kick",
                    secondCue =
                        "Stand completely before performing Slip Right."
                ),
                lesson(
                    id = "mma_advanced_assessment_5",
                    title = "Integrated MMA Review",
                    subtitle =
                        "Review striking, wrestling, defence and positional movement.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 365,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick - Penetration Step - Return to Stance - Sprawl - Circle Left",
                    firstCue =
                        "Control every transition and preserve your stance.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Hip Switch Left - Technical Stand-Up - Lead Hook - Rear Knee - Exit Right",
                    secondCue =
                        "Stand fully before beginning the final striking sequence."
                ),
                lesson(
                    id = "mma_advanced_assessment_6",
                    title = "MMA Advanced Final",
                    subtitle =
                        "Complete the entire MMA Fight Path.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 400,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Level Change - Penetration Step - Return to Stance - Sprawl - Circle Right - Technical Stand-Up",
                    firstCue =
                        "Control every striking, wrestling and defensive transition.",
                    secondMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Combat Base - Technical Stand-Up - Slip Left - Cross - Lead Hook - Rear Low Kick - Exit Right",
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
                TrainingPathLevel.ADVANCED,
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
                TrainingPathLevel.ADVANCED,
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