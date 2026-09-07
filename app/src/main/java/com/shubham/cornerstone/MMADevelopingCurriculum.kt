package com.shubham.cornerstone

/**
 * MMA Developing Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Developing sessions.
 *
 * This stage develops:
 *
 * - layered MMA striking combinations
 * - takedown-entry systems
 * - defensive wrestling chains
 * - linked ground escapes
 * - positional offense movement
 * - range and transition control
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
object MMADevelopingCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MMA

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            layeredMmaCombinations(),
            takedownEntrySystems(),
            defensiveWrestlingChains(),
            groundEscapeSequences(),
            positionalOffenseMovement(),
            rangeAndTransitionControl(),
            developingAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — LAYERED MMA COMBINATIONS
    // ---------------------------------------------------------

    private fun layeredMmaCombinations():
            TrainingChapterDefinition {

        val chapterId =
            "mma_developing_layered_combinations"

        return chapter(
            id = chapterId,
            title = "Layered MMA Combinations",
            subtitle =
                "Combine striking and wrestling threats without losing structure.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "mma_developing_layered_combinations_1",
                    title = "Punch-Kick Layers",
                    subtitle =
                        "Connect compact boxing attacks to controlled kicks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Finish the lead hook before rotating into the kick.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover the teep before entering behind your punches."
                ),
                lesson(
                    id = "mma_developing_layered_combinations_2",
                    title = "Strikes Into Level Changes",
                    subtitle =
                        "Hide wrestling movements behind longer combinations.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Level Change",
                    firstCue =
                        "Recover the low kick before lowering your level.",
                    secondMoves =
                        "Jab - Lead Hook - Cross - Level Change - Penetration Step",
                    secondCue =
                        "Finish the punches and place your lead knee down gently."
                ),
                lesson(
                    id = "mma_developing_layered_combinations_3",
                    title = "Level-Change Re-Attack",
                    subtitle =
                        "Use wrestling threats to reopen striking attacks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 200,
                    firstMoves =
                        "Level Change Feint - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Return to striking height before throwing the cross.",
                    secondMoves =
                        "Jab - Level Change Feint - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Keep the feint shallow and rebuild your stance before kicking."
                ),
                lesson(
                    id = "mma_developing_layered_combinations_4",
                    title = "Knee-Wrestle Connections",
                    subtitle =
                        "Blend close-range knees with wrestling threats.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 205,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Level Change",
                    firstCue =
                        "Recover the rear foot before lowering your level.",
                    secondMoves =
                        "Lead Teep - Cross - Lead Knee - Shadow Single-Leg Entry",
                    secondCue =
                        "Return the lead foot beneath you before beginning the entry."
                ),
                lesson(
                    id = "mma_developing_layered_combinations_5",
                    title = "Defence-to-Offense Layers",
                    subtitle =
                        "Use explicit directional movement before attacking.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep Slip Left compact and return your head before kicking.",
                    secondMoves =
                        "Slip Right - Cross - Level Change - Penetration Step",
                    secondCue =
                        "Complete Slip Right before lowering your level."
                ),
                lesson(
                    id = "mma_developing_layered_combinations_6",
                    title = "Layered Combination Review",
                    subtitle =
                        "Review strikes, wrestling threats and directional movement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 230,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Slip Left - Lead Hook - Rear Knee",
                    firstCue =
                        "Recover each strike and complete Slip Left before countering.",
                    secondMoves =
                        "Lead Teep - Level Change Feint - Cross - Rear Body Kick - Penetration Step",
                    secondCue =
                        "Use the feint to reopen the attack and protect your lead knee."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — TAKEDOWN-ENTRY SYSTEMS
    // ---------------------------------------------------------

    private fun takedownEntrySystems():
            TrainingChapterDefinition {

        val chapterId =
            "mma_developing_takedown_entries"

        return chapter(
            id = chapterId,
            title = "Takedown-Entry Systems",
            subtitle =
                "Build adaptable solo entries from punches, kicks and feints.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "mma_developing_takedown_entries_1",
                    title = "Double-Leg Entry Footwork",
                    subtitle =
                        "Develop a controlled penetration step and recovery.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Level Change - Penetration Step - Rear Leg Step Up - Return to Stance",
                    firstCue =
                        "Use a padded surface and keep the lead knee aligned.",
                    secondMoves =
                        "Jab - Cross - Level Change - Penetration Step - Rear Leg Step Up",
                    secondCue =
                        "Finish the cross before lowering your level."
                ),
                lesson(
                    id = "mma_developing_takedown_entries_2",
                    title = "Single-Leg Entry Footwork",
                    subtitle =
                        "Create an outside angle for a shadow single-leg entry.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Step Outside - Shadow Single-Leg Entry - Return to Stance",
                    firstCue =
                        "Complete the outside step before lowering your level.",
                    secondMoves =
                        "Cross - Lead Hook - Step Right - Shadow Single-Leg Entry",
                    secondCue =
                        "Keep your planted knee aligned while changing direction."
                ),
                lesson(
                    id = "mma_developing_takedown_entries_3",
                    title = "Kick-to-Takedown Entry",
                    subtitle =
                        "Use kick recovery to prepare wrestling attacks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 205,
                    firstMoves =
                        "Rear Low Kick - Jab - Level Change - Penetration Step",
                    firstCue =
                        "Recover the rear leg before driving forward.",
                    secondMoves =
                        "Lead Teep - Cross - Shadow Single-Leg Entry - Exit Right",
                    secondCue =
                        "Return the lead foot before throwing the cross."
                ),
                lesson(
                    id = "mma_developing_takedown_entries_4",
                    title = "Angle Entry",
                    subtitle =
                        "Create a left or right angle before changing levels.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 210,
                    firstMoves =
                        "Jab - Step Left - Cross - Level Change - Penetration Step",
                    firstCue =
                        "Complete the left step before lowering your level.",
                    secondMoves =
                        "Cross - Step Right - Lead Hook - Shadow Single-Leg Entry",
                    secondCue =
                        "Keep your feet separated while moving right."
                ),
                lesson(
                    id = "mma_developing_takedown_entries_5",
                    title = "Entry-to-Striking Reset",
                    subtitle =
                        "Abandon the shadow entry and return safely to striking.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Level Change - Penetration Step - Return to Stance - Cross - Lead Hook",
                    firstCue =
                        "Recover your standing base before throwing punches.",
                    secondMoves =
                        "Shadow Single-Leg Entry - Exit Left - Jab - Rear Low Kick",
                    secondCue =
                        "Complete the left exit and recreate kicking range."
                ),
                lesson(
                    id = "mma_developing_takedown_entries_6",
                    title = "Takedown-Entry Review",
                    subtitle =
                        "Review double-leg, single-leg and angle-entry footwork.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Level Change - Penetration Step - Rear Leg Step Up",
                    firstCue =
                        "Recover the kick and keep the entry controlled.",
                    secondMoves =
                        "Level Change Feint - Lead Hook - Step Right - Shadow Single-Leg Entry - Exit Left",
                    secondCue =
                        "Return to striking height after the feint and protect your knees."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — DEFENSIVE WRESTLING CHAINS
    // ---------------------------------------------------------

    private fun defensiveWrestlingChains():
            TrainingChapterDefinition {

        val chapterId =
            "mma_developing_defensive_wrestling"

        return chapter(
            id = chapterId,
            title = "Defensive Wrestling Chains",
            subtitle =
                "Link takedown defence to movement and counter attacks.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "mma_developing_defensive_wrestling_1",
                    title = "Down-Block Counter",
                    subtitle =
                        "Recover from a down block into immediate offense.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Down Block - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover your posture before beginning the counter.",
                    secondMoves =
                        "Jab - Down Block - Lead Knee - Step Back",
                    secondCue =
                        "Rebuild your base before lifting the knee."
                ),
                lesson(
                    id = "mma_developing_defensive_wrestling_2",
                    title = "Sprawl-to-Angle",
                    subtitle =
                        "Create a clear angle after defending the shot.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Sprawl - Circle Left - Return to Stance - Cross",
                    firstCue =
                        "Keep your hips controlled while circling left.",
                    secondMoves =
                        "Sprawl - Circle Right - Return to Stance - Jab - Rear Low Kick",
                    secondCue =
                        "Rebuild your stance before releasing the kick."
                ),
                lesson(
                    id = "mma_developing_defensive_wrestling_3",
                    title = "Sprawl Re-Attack",
                    subtitle =
                        "Defend, recover and begin a second attack.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Sprawl - Return to Stance - Cross - Lead Hook",
                    firstCue =
                        "Bring both feet beneath you before re-attacking.",
                    secondMoves =
                        "Rear Low Kick - Sprawl - Circle Left - Jab - Rear Body Kick",
                    secondCue =
                        "Complete the circle and recreate kicking range."
                ),
                lesson(
                    id = "mma_developing_defensive_wrestling_4",
                    title = "Down Block to Sprawl",
                    subtitle =
                        "Link two defensive wrestling reactions.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Down Block - Sprawl - Return to Stance",
                    firstCue =
                        "Keep your hands ready to support the sprawl safely.",
                    secondMoves =
                        "Jab - Down Block - Cross - Sprawl - Circle Right",
                    secondCue =
                        "Treat each defensive action as a separate controlled movement."
                ),
                lesson(
                    id = "mma_developing_defensive_wrestling_5",
                    title = "Sprawl-to-Ground Base",
                    subtitle =
                        "Move from a sprawl into a stable floor position.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Sprawl - Circle Left - Combat Base - Technical Stand-Up",
                    firstCue =
                        "Use a padded surface and settle your base before standing.",
                    secondMoves =
                        "Sprawl - Circle Right - Sit-Through Left - Return to Base",
                    secondCue =
                        "Support your weight through the planted hand."
                ),
                lesson(
                    id = "mma_developing_defensive_wrestling_6",
                    title = "Defensive Wrestling Review",
                    subtitle =
                        "Review down blocks, sprawls, angles and recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Jab - Down Block - Cross - Sprawl - Circle Left - Return to Stance - Rear Low Kick",
                    firstCue =
                        "Recover each defensive position before continuing.",
                    secondMoves =
                        "Lead Teep - Sprawl - Circle Right - Combat Base - Technical Stand-Up - Jab - Cross",
                    secondCue =
                        "Control every standing and ground transition."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — GROUND ESCAPE SEQUENCES
    // ---------------------------------------------------------

    private fun groundEscapeSequences():
            TrainingChapterDefinition {

        val chapterId =
            "mma_developing_ground_escapes"

        return chapter(
            id = chapterId,
            title = "Ground Escape Sequences",
            subtitle =
                "Link solo escape movements and return to standing.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "mma_developing_ground_escapes_1",
                    title = "Bridge-Escape Chain",
                    subtitle =
                        "Combine directional bridges and hip escapes.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Hip Escape Right - Reset",
                    firstCue =
                        "Drive through your feet and avoid pressure on your neck.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Hip Escape Left - Reset",
                    secondCue =
                        "Complete the bridge before beginning the hip escapes."
                ),
                lesson(
                    id = "mma_developing_ground_escapes_2",
                    title = "Forward-Reverse Escape",
                    subtitle =
                        "Change between normal and reverse hip escapes.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Hip Escape Left - Reverse Hip Escape Right - Reset",
                    firstCue =
                        "Keep the movements small and support your head.",
                    secondMoves =
                        "Hip Escape Right - Reverse Hip Escape Left - Reset",
                    secondCue =
                        "Turn through your shoulders without forcing your lower back."
                ),
                lesson(
                    id = "mma_developing_ground_escapes_3",
                    title = "Escape to Seated Guard",
                    subtitle =
                        "Create space before building a protected seated position.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Seated Guard",
                    firstCue =
                        "Build your seated posture only after creating space.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Seated Guard - Step Back",
                    secondCue =
                        "Keep one hand available to protect your face."
                ),
                lesson(
                    id = "mma_developing_ground_escapes_4",
                    title = "Escape to Technical Stand-Up",
                    subtitle =
                        "Link multiple escape movements to standing recovery.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Hip Escape Left - Reverse Hip Escape Right - Technical Stand-Up",
                    firstCue =
                        "Establish a protected seated position before standing.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Technical Stand-Up - Jab",
                    secondCue =
                        "Rebuild your MMA stance before throwing the jab."
                ),
                lesson(
                    id = "mma_developing_ground_escapes_5",
                    title = "Sit-Through Escape Chain",
                    subtitle =
                        "Use directional sit-throughs to change position.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Base Position - Sit-Through Left - Hip Escape Right - Technical Stand-Up",
                    firstCue =
                        "Keep your supporting hand planted during Sit-Through Left.",
                    secondMoves =
                        "Base Position - Sit-Through Right - Hip Escape Left - Technical Stand-Up",
                    secondCue =
                        "Control Sit-Through Right before beginning the escape."
                ),
                lesson(
                    id = "mma_developing_ground_escapes_6",
                    title = "Ground Escape Review",
                    subtitle =
                        "Review linked escapes and standing recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Seated Guard - Technical Stand-Up",
                    firstCue =
                        "Complete every movement on a suitable padded surface.",
                    secondMoves =
                        "Bridge Right - Reverse Hip Escape Left - Sit-Through Right - Technical Stand-Up - Jab - Cross",
                    secondCue =
                        "Establish your stance before finishing with punches."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — POSITIONAL OFFENSE MOVEMENT
    // ---------------------------------------------------------

    private fun positionalOffenseMovement():
            TrainingChapterDefinition {

        val chapterId =
            "mma_developing_positional_offense"

        return chapter(
            id = chapterId,
            title = "Positional Offense Movement",
            subtitle =
                "Maintain a stable floor base while changing position.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "mma_developing_positional_offense_1",
                    title = "Combat-Base Striking",
                    subtitle =
                        "Shadow controlled ground strikes from a stable base.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Rear Ground Straight - Reset",
                    firstCue =
                        "Strike only into open space or a suitable pad.",
                    secondMoves =
                        "Posture Up - Rear Ground Straight - Lead Ground Straight - Hip Switch Left",
                    secondCue =
                        "Avoid leaning beyond your stable base."
                ),
                lesson(
                    id = "mma_developing_positional_offense_2",
                    title = "Knee-Slide Chains",
                    subtitle =
                        "Link controlled directional knee-slide movements.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Reset",
                    firstCue =
                        "Use a padded surface and keep the movements short.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Hip Switch Left - Reset",
                    secondCue =
                        "Protect your knees while changing direction."
                ),
                lesson(
                    id = "mma_developing_positional_offense_3",
                    title = "Hip-Switch Striking",
                    subtitle =
                        "Change hip position before shadow ground strikes.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Hip Switch Left - Lead Ground Straight - Rear Ground Straight",
                    firstCue =
                        "Settle your base after Hip Switch Left before striking.",
                    secondMoves =
                        "Hip Switch Right - Rear Ground Straight - Lead Ground Straight",
                    secondCue =
                        "Keep one hand available for balance while switching right."
                ),
                lesson(
                    id = "mma_developing_positional_offense_4",
                    title = "Ground Strike to Disengage",
                    subtitle =
                        "Finish controlled ground offense and return to standing.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Rear Ground Straight - Technical Stand-Up",
                    firstCue =
                        "Stop striking before beginning the technical stand-up.",
                    secondMoves =
                        "Hip Switch Left - Rear Ground Straight - Return to Base - Step Back",
                    secondCue =
                        "Recover a stable base before disengaging."
                ),
                lesson(
                    id = "mma_developing_positional_offense_5",
                    title = "Ground Re-Attack",
                    subtitle =
                        "Change floor position before beginning a second attack.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Knee Slide Left - Rear Ground Straight",
                    firstCue =
                        "Settle your balance after the knee slide before re-attacking.",
                    secondMoves =
                        "Hip Switch Right - Lead Ground Straight - Sit-Through Left - Return to Base",
                    secondCue =
                        "Keep the strikes controlled and avoid hitting the floor."
                ),
                lesson(
                    id = "mma_developing_positional_offense_6",
                    title = "Positional Offense Review",
                    subtitle =
                        "Review base, position changes, strikes and disengagement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Lead Ground Straight - Rear Ground Straight - Technical Stand-Up",
                    firstCue =
                        "Maintain control throughout every floor transition.",
                    secondMoves =
                        "Sit-Through Right - Return to Base - Knee Slide Right - Rear Ground Straight - Technical Stand-Up - Jab",
                    secondCue =
                        "Rebuild your standing stance before finishing with the jab."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — RANGE AND TRANSITION CONTROL
    // ---------------------------------------------------------

    private fun rangeAndTransitionControl():
            TrainingChapterDefinition {

        val chapterId =
            "mma_developing_range_transitions"

        return chapter(
            id = chapterId,
            title = "Range & Transition Control",
            subtitle =
                "Choose when to strike, wrestle, disengage or return.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "mma_developing_range_transitions_1",
                    title = "Long-Range Control",
                    subtitle =
                        "Use jabs and teeps to maintain striking distance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Lead Teep - Jab - Step Back - Rear Teep",
                    firstCue =
                        "Recover every teep and keep your stance ready.",
                    secondMoves =
                        "Double Jab - Rear Low Kick - Exit Left",
                    secondCue =
                        "Complete the kick before leaving the centre line."
                ),
                lesson(
                    id = "mma_developing_range_transitions_2",
                    title = "Striking-to-Wrestling Range",
                    subtitle =
                        "Close distance safely behind striking attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Lead Teep Feint - Jab - Cross - Level Change",
                    firstCue =
                        "Return the lead foot before entering behind your punches.",
                    secondMoves =
                        "Jab - Lead Hook - Rear Low Kick - Shadow Single-Leg Entry",
                    secondCue =
                        "Recover the kick before lowering your level."
                ),
                lesson(
                    id = "mma_developing_range_transitions_3",
                    title = "Wrestling-to-Striking Range",
                    subtitle =
                        "Leave a wrestling entry and rebuild striking distance.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Penetration Step - Return to Stance - Step Back - Jab - Cross",
                    firstCue =
                        "Recover fully before stepping back into striking range.",
                    secondMoves =
                        "Shadow Single-Leg Entry - Exit Right - Lead Teep - Rear Low Kick",
                    secondCue =
                        "Complete the right exit before extending the teep."
                ),
                lesson(
                    id = "mma_developing_range_transitions_4",
                    title = "Ground-to-Standing Range",
                    subtitle =
                        "Return from the floor and establish safe distance.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Hip Escape Left - Technical Stand-Up - Step Back - Lead Teep",
                    firstCue =
                        "Stand completely before creating distance with the teep.",
                    secondMoves =
                        "Sit-Through Right - Return to Base - Technical Stand-Up - Jab - Rear Low Kick",
                    secondCue =
                        "Rebuild your stance before beginning the striking attack."
                ),
                lesson(
                    id = "mma_developing_range_transitions_5",
                    title = "Transition Re-Attack",
                    subtitle =
                        "Use a completed transition to begin another attack.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Jab - Sprawl - Circle Left - Return to Stance - Cross - Rear Body Kick",
                    firstCue =
                        "Recover from the sprawl before beginning the re-attack.",
                    secondMoves =
                        "Technical Stand-Up - Lead Teep - Level Change Feint - Cross - Lead Hook",
                    secondCue =
                        "Establish distance after standing before using the feint."
                ),
                lesson(
                    id = "mma_developing_range_transitions_6",
                    title = "Range-Control Review",
                    subtitle =
                        "Review striking, wrestling, ground and disengagement ranges.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 255,
                    firstMoves =
                        "Lead Teep - Jab - Rear Low Kick - Level Change - Penetration Step - Return to Stance",
                    firstCue =
                        "Recover each strike before entering wrestling range.",
                    secondMoves =
                        "Sprawl - Circle Right - Combat Base - Technical Stand-Up - Step Back - Jab - Cross",
                    secondCue =
                        "Control every range change and finish in a stable stance."
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
            "mma_developing_assessment"

        return chapter(
            id = chapterId,
            title = "Developing Assessment",
            subtitle =
                "Bring your Developing MMA systems together.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "mma_developing_assessment_1",
                    title = "Layered Striking Review",
                    subtitle =
                        "Review strikes, level changes and re-attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Slip Left - Lead Hook - Rear Knee",
                    firstCue =
                        "Recover the kick and complete Slip Left before countering.",
                    secondMoves =
                        "Level Change Feint - Cross - Lead Hook - Rear Body Kick - Step Back",
                    secondCue =
                        "Return to striking height before beginning the attack."
                ),
                lesson(
                    id = "mma_developing_assessment_2",
                    title = "Takedown-Entry Review",
                    subtitle =
                        "Review double-leg and single-leg shadow entries.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Jab - Cross - Level Change - Penetration Step - Rear Leg Step Up",
                    firstCue =
                        "Use a padded surface and protect your lead knee.",
                    secondMoves =
                        "Lead Teep - Cross - Step Right - Shadow Single-Leg Entry - Exit Left",
                    secondCue =
                        "Recover the teep and complete each directional step."
                ),
                lesson(
                    id = "mma_developing_assessment_3",
                    title = "Defensive Wrestling Review",
                    subtitle =
                        "Review down blocks, sprawls and directional recovery.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Jab - Down Block - Cross - Sprawl - Circle Left - Return to Stance",
                    firstCue =
                        "Complete each defensive movement before continuing.",
                    secondMoves =
                        "Rear Low Kick - Sprawl - Circle Right - Technical Stand-Up - Jab",
                    secondCue =
                        "Recover each position and finish in a stable stance."
                ),
                lesson(
                    id = "mma_developing_assessment_4",
                    title = "Ground Escape Review",
                    subtitle =
                        "Review linked escapes and technical standing recovery.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Technical Stand-Up",
                    firstCue =
                        "Keep every movement controlled on a padded surface.",
                    secondMoves =
                        "Bridge Right - Reverse Hip Escape Left - Seated Guard - Technical Stand-Up - Lead Teep",
                    secondCue =
                        "Establish your standing balance before extending the teep."
                ),
                lesson(
                    id = "mma_developing_assessment_5",
                    title = "Positional Offense Review",
                    subtitle =
                        "Review ground position changes and controlled offense.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Lead Ground Straight - Rear Ground Straight",
                    firstCue =
                        "Avoid striking the floor and maintain a stable base.",
                    secondMoves =
                        "Sit-Through Right - Combat Base - Rear Ground Straight - Technical Stand-Up - Jab - Cross",
                    secondCue =
                        "Rebuild your standing stance before finishing the combination."
                ),
                lesson(
                    id = "mma_developing_assessment_6",
                    title = "MMA Developing Final",
                    subtitle =
                        "Complete the MMA Developing stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 300,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick - Level Change - Penetration Step - Return to Stance - Sprawl - Circle Left",
                    firstCue =
                        "Control every transition between striking and wrestling.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Technical Stand-Up - Slip Right - Cross - Lead Hook - Rear Body Kick - Exit Right",
                    secondCue =
                        "Stand completely before completing Slip Right and beginning the final attack."
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