package com.shubham.cornerstone

/**
 * MMA Fundamentals Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Fundamentals sessions.
 *
 * This stage develops:
 *
 * - wrestling-ready striking
 * - strike-to-takedown connections
 * - sprawls and directional defensive returns
 * - solo ground mobility and escapes
 * - positional movement and controlled ground striking
 * - complete standing-to-ground transitions
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
object MMAFundamentalsCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MMA

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            mmaStrikingStructure(),
            strikeToWrestleConnections(),
            takedownDefenceAndCounters(),
            groundMobilityAndEscapes(),
            groundControlMovement(),
            fullMmaTransitions(),
            fundamentalsAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — MMA STRIKING STRUCTURE
    // ---------------------------------------------------------

    private fun mmaStrikingStructure():
            TrainingChapterDefinition {

        val chapterId =
            "mma_fundamentals_striking_structure"

        return chapter(
            id = chapterId,
            title = "MMA Striking Structure",
            subtitle =
                "Strike while keeping your stance ready for wrestling.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_striking_structure_1",
                    title = "Stance & Range",
                    subtitle =
                        "Control distance from a balanced MMA stance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Jab - Step Back - Jab",
                    firstCue =
                        "Keep your hips beneath you and remain ready to change levels.",
                    secondMoves =
                        "Double Jab - Step Left - Cross",
                    secondCue =
                        "Move with small steps and avoid crossing your feet."
                ),
                lesson(
                    id = "mma_fundamentals_striking_structure_2",
                    title = "Boxing Into Low Kicks",
                    subtitle =
                        "Connect compact punches to lead and rear low kicks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 155,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the rear leg into a wrestling-ready stance.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Low Kick",
                    secondCue =
                        "Keep the lead kick quick and return your hands to guard."
                ),
                lesson(
                    id = "mma_fundamentals_striking_structure_3",
                    title = "Body-Kick Connections",
                    subtitle =
                        "Add controlled body kicks without losing your base.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 160,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Finish the punches before rotating into the body kick.",
                    secondMoves =
                        "Lead Teep - Cross - Rear Body Kick",
                    secondCue =
                        "Recover the teep before driving forward with the cross."
                ),
                lesson(
                    id = "mma_fundamentals_striking_structure_4",
                    title = "Knee Entries",
                    subtitle =
                        "Enter knee range behind compact punching attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 165,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Step Back",
                    firstCue =
                        "Stay tall through the knee and recover before stepping back.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Knee - Exit Left",
                    secondCue =
                        "Return the lead foot before completing the left exit."
                ),
                lesson(
                    id = "mma_fundamentals_striking_structure_5",
                    title = "Slip Left & Slip Right",
                    subtitle =
                        "Use compact directional movement before countering.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 170,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook",
                    firstCue =
                        "Keep Slip Left compact and maintain your wrestling base.",
                    secondMoves =
                        "Slip Right - Cross - Rear Low Kick",
                    secondCue =
                        "Complete Slip Right before rotating into the counter."
                ),
                lesson(
                    id = "mma_fundamentals_striking_structure_6",
                    title = "Striking Structure Review",
                    subtitle =
                        "Review punches, kicks, knees and directional movement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 185,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Slip Left - Lead Hook",
                    firstCue =
                        "Recover the kick before completing Slip Left.",
                    secondMoves =
                        "Lead Teep - Cross - Rear Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Recreate kicking distance before releasing the final kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — STRIKE-TO-WRESTLE CONNECTIONS
    // ---------------------------------------------------------

    private fun strikeToWrestleConnections():
            TrainingChapterDefinition {

        val chapterId =
            "mma_fundamentals_strike_wrestle"

        return chapter(
            id = chapterId,
            title = "Strike-to-Wrestle Connections",
            subtitle =
                "Use striking attacks to hide solo takedown entries.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_strike_wrestle_1",
                    title = "Jab Into Level Change",
                    subtitle =
                        "Hide a wrestling level change behind the jab.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 155,
                    firstMoves =
                        "Jab - Level Change - Return to Stance",
                    firstCue =
                        "Retract the jab before lowering your level.",
                    secondMoves =
                        "Double Jab - Level Change - Penetration Step - Return to Stance",
                    secondCue =
                        "Place the lead knee down gently on a padded surface."
                ),
                lesson(
                    id = "mma_fundamentals_strike_wrestle_2",
                    title = "Cross Into Takedown Entry",
                    subtitle =
                        "Use the rear hand to prepare a shadow takedown entry.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 160,
                    firstMoves =
                        "Jab - Cross - Level Change - Penetration Step",
                    firstCue =
                        "Finish the cross before lowering your level.",
                    secondMoves =
                        "Cross - Lead Hook - Level Change - Return to Stance",
                    secondCue =
                        "Keep your eyes forward and your spine controlled."
                ),
                lesson(
                    id = "mma_fundamentals_strike_wrestle_3",
                    title = "Shadow Single-Leg Entry",
                    subtitle =
                        "Practise the footwork of a solo single-leg entry.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 165,
                    firstMoves =
                        "Jab - Step Outside - Shadow Single-Leg Entry - Return to Stance",
                    firstCue =
                        "Step outside before lowering your level and keep the movement controlled.",
                    secondMoves =
                        "Lead Hook - Level Change - Shadow Single-Leg Entry - Exit Right",
                    secondCue =
                        "Protect your lead knee and avoid twisting during the entry."
                ),
                lesson(
                    id = "mma_fundamentals_strike_wrestle_4",
                    title = "Kick Into Level Change",
                    subtitle =
                        "Recover from a kick before changing levels.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 170,
                    firstMoves =
                        "Rear Low Kick - Jab - Level Change",
                    firstCue =
                        "Return the kicking leg before beginning the wrestling movement.",
                    secondMoves =
                        "Lead Teep - Cross - Level Change - Penetration Step",
                    secondCue =
                        "Recover the teep and finish the cross before lowering your level."
                ),
                lesson(
                    id = "mma_fundamentals_strike_wrestle_5",
                    title = "Level-Change Feint",
                    subtitle =
                        "Use a wrestling feint to open striking attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 175,
                    firstMoves =
                        "Level Change Feint - Cross - Lead Hook",
                    firstCue =
                        "Keep the feint shallow and return to striking height.",
                    secondMoves =
                        "Jab - Level Change Feint - Rear Body Kick",
                    secondCue =
                        "Rebuild your stance before rotating into the kick."
                ),
                lesson(
                    id = "mma_fundamentals_strike_wrestle_6",
                    title = "Strike-Wrestle Review",
                    subtitle =
                        "Review entries, feints and safe stance recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Level Change - Penetration Step",
                    firstCue =
                        "Recover the low kick before changing levels.",
                    secondMoves =
                        "Level Change Feint - Cross - Lead Hook - Shadow Single-Leg Entry - Exit Right",
                    secondCue =
                        "Keep the feint and entry controlled and protect your knees."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — TAKEDOWN DEFENCE AND COUNTERS
    // ---------------------------------------------------------

    private fun takedownDefenceAndCounters():
            TrainingChapterDefinition {

        val chapterId =
            "mma_fundamentals_takedown_defence"

        return chapter(
            id = chapterId,
            title = "Takedown Defence & Counters",
            subtitle =
                "Defend imaginary takedowns and return with structure.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_takedown_defence_1",
                    title = "Down-Block Return",
                    subtitle =
                        "Use a down block before returning to punches.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Down Block - Cross - Lead Hook",
                    firstCue =
                        "Move your hips back and recover your posture before countering.",
                    secondMoves =
                        "Jab - Down Block - Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Complete the down block before rebuilding your attack."
                ),
                lesson(
                    id = "mma_fundamentals_takedown_defence_2",
                    title = "Sprawl Recovery",
                    subtitle =
                        "Recover quickly and safely after a basic sprawl.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 165,
                    firstMoves =
                        "Sprawl - Return to Stance - Jab - Cross",
                    firstCue =
                        "Bring both feet beneath you before standing.",
                    secondMoves =
                        "Jab - Cross - Sprawl - Return to Stance - Rear Low Kick",
                    secondCue =
                        "Recover your stance completely before kicking."
                ),
                lesson(
                    id = "mma_fundamentals_takedown_defence_3",
                    title = "Sprawl & Circle Left",
                    subtitle =
                        "Move left after defending an imaginary takedown.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 170,
                    firstMoves =
                        "Sprawl - Circle Left - Return to Stance - Cross",
                    firstCue =
                        "Keep your weight controlled while circling left.",
                    secondMoves =
                        "Jab - Sprawl - Circle Left - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Return to stance before beginning the counter."
                ),
                lesson(
                    id = "mma_fundamentals_takedown_defence_4",
                    title = "Sprawl & Circle Right",
                    subtitle =
                        "Move right after defending an imaginary takedown.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 175,
                    firstMoves =
                        "Sprawl - Circle Right - Return to Stance - Jab",
                    firstCue =
                        "Use small right-side steps and keep your hips controlled.",
                    secondMoves =
                        "Cross - Sprawl - Circle Right - Jab - Rear Body Kick",
                    secondCue =
                        "Rebuild your striking range before releasing the kick."
                ),
                lesson(
                    id = "mma_fundamentals_takedown_defence_5",
                    title = "Defensive Transition Chain",
                    subtitle =
                        "Combine down blocks, sprawls and standing returns.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 180,
                    firstMoves =
                        "Jab - Down Block - Cross - Sprawl - Return to Stance",
                    firstCue =
                        "Treat each defensive movement as a separate controlled action.",
                    secondMoves =
                        "Sprawl - Circle Left - Return to Stance - Jab - Rear Knee - Step Back",
                    secondCue =
                        "Stand fully before beginning the striking return."
                ),
                lesson(
                    id = "mma_fundamentals_takedown_defence_6",
                    title = "Takedown Defence Review",
                    subtitle =
                        "Review defensive reactions and immediate counters.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 195,
                    firstMoves =
                        "Jab - Cross - Down Block - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover your posture after the down block before countering.",
                    secondMoves =
                        "Lead Teep - Sprawl - Circle Right - Return to Stance - Jab - Cross",
                    secondCue =
                        "Complete every transition before restarting your offense."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — GROUND MOBILITY AND ESCAPES
    // ---------------------------------------------------------

    private fun groundMobilityAndEscapes():
            TrainingChapterDefinition {

        val chapterId =
            "mma_fundamentals_ground_mobility"

        return chapter(
            id = chapterId,
            title = "Ground Mobility & Escapes",
            subtitle =
                "Develop directional solo movement from the floor.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_ground_mobility_1",
                    title = "Bridge-to-Hip Escape",
                    subtitle =
                        "Combine bridging power with space-creating movement.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 165,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Reset",
                    firstCue =
                        "Drive through your feet and avoid placing pressure on your neck.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Reset",
                    secondCue =
                        "Complete the bridge before moving your hips."
                ),
                lesson(
                    id = "mma_fundamentals_ground_mobility_2",
                    title = "Repeated Hip Escapes",
                    subtitle =
                        "Create continuous space in both directions.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 170,
                    firstMoves =
                        "Hip Escape Left - Hip Escape Left - Reset",
                    firstCue =
                        "Turn onto your side and use your planted foot to move left.",
                    secondMoves =
                        "Hip Escape Right - Hip Escape Right - Reset",
                    secondCue =
                        "Keep your elbows close while moving right."
                ),
                lesson(
                    id = "mma_fundamentals_ground_mobility_3",
                    title = "Reverse Hip Escape",
                    subtitle =
                        "Move your hips forward while maintaining a protected frame.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 175,
                    firstMoves =
                        "Reverse Hip Escape Left - Reset",
                    firstCue =
                        "Use small controlled movements and keep your head supported.",
                    secondMoves =
                        "Reverse Hip Escape Right - Reset",
                    secondCue =
                        "Move your hips right without forcing your lower back."
                ),
                lesson(
                    id = "mma_fundamentals_ground_mobility_4",
                    title = "Technical Stand-Up Chain",
                    subtitle =
                        "Create space before returning safely to standing.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 180,
                    firstMoves =
                        "Hip Escape Left - Seated Guard - Technical Stand-Up",
                    firstCue =
                        "Keep one hand protecting your face while standing.",
                    secondMoves =
                        "Hip Escape Right - Seated Guard - Technical Stand-Up - Step Back",
                    secondCue =
                        "Rise into your MMA stance without crossing your feet."
                ),
                lesson(
                    id = "mma_fundamentals_ground_mobility_5",
                    title = "Sit-Through Movement",
                    subtitle =
                        "Develop controlled left and right sit-throughs.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 185,
                    firstMoves =
                        "Base Position - Sit-Through Left - Return to Base",
                    firstCue =
                        "Support your weight through the planted hand and move left smoothly.",
                    secondMoves =
                        "Base Position - Sit-Through Right - Return to Base",
                    secondCue =
                        "Keep your hips above the floor while moving right."
                ),
                lesson(
                    id = "mma_fundamentals_ground_mobility_6",
                    title = "Ground Mobility Review",
                    subtitle =
                        "Review bridges, escapes, sit-throughs and standing recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 200,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Technical Stand-Up",
                    firstCue =
                        "Complete each movement deliberately on a padded surface.",
                    secondMoves =
                        "Bridge Right - Reverse Hip Escape Left - Sit-Through Right - Technical Stand-Up - Jab",
                    secondCue =
                        "Build your standing stance before throwing the jab."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — GROUND CONTROL MOVEMENT
    // ---------------------------------------------------------

    private fun groundControlMovement():
            TrainingChapterDefinition {

        val chapterId =
            "mma_fundamentals_ground_control"

        return chapter(
            id = chapterId,
            title = "Ground Control Movement",
            subtitle =
                "Build a stable base and controlled solo ground offense.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_ground_control_1",
                    title = "Combat Base",
                    subtitle =
                        "Develop a balanced kneeling position for MMA transitions.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 170,
                    firstMoves =
                        "MMA Stance - Level Change - Combat Base - Return to Stance",
                    firstCue =
                        "Lower yourself gently and keep your spine controlled.",
                    secondMoves =
                        "Combat Base - Posture Up - Technical Stand-Up",
                    secondCue =
                        "Keep your supporting foot planted before standing."
                ),
                lesson(
                    id = "mma_fundamentals_ground_control_2",
                    title = "Knee-Slide Movement",
                    subtitle =
                        "Practise controlled directional knee-slide footwork.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 175,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Reset",
                    firstCue =
                        "Use a padded surface and keep the left movement short.",
                    secondMoves =
                        "Combat Base - Knee Slide Right - Reset",
                    secondCue =
                        "Protect your knees and move right without forcing the hips."
                ),
                lesson(
                    id = "mma_fundamentals_ground_control_3",
                    title = "Hip-Switch Movement",
                    subtitle =
                        "Change hip position while maintaining your base.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 180,
                    firstMoves =
                        "Base Position - Hip Switch Left - Return to Base",
                    firstCue =
                        "Keep your hands ready to support your weight.",
                    secondMoves =
                        "Base Position - Hip Switch Right - Return to Base",
                    secondCue =
                        "Move your hips right without collapsing your posture."
                ),
                lesson(
                    id = "mma_fundamentals_ground_control_4",
                    title = "Controlled Ground Strikes",
                    subtitle =
                        "Shadow straight ground strikes without hitting the floor.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 185,
                    firstMoves =
                        "Combat Base - Lead Ground Straight - Rear Ground Straight",
                    firstCue =
                        "Strike only into open space or a suitable pad and keep your base stable.",
                    secondMoves =
                        "Posture Up - Rear Ground Straight - Lead Ground Straight - Reset",
                    secondCue =
                        "Avoid leaning too far forward during the strikes."
                ),
                lesson(
                    id = "mma_fundamentals_ground_control_5",
                    title = "Disengage to Standing",
                    subtitle =
                        "Leave the ground position and recover striking range.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 190,
                    firstMoves =
                        "Combat Base - Posture Up - Technical Stand-Up - Step Back",
                    firstCue =
                        "Stand with control and keep your hands protecting your face.",
                    secondMoves =
                        "Hip Switch Left - Return to Base - Technical Stand-Up - Lead Teep",
                    secondCue =
                        "Establish your standing balance before extending the teep."
                ),
                lesson(
                    id = "mma_fundamentals_ground_control_6",
                    title = "Ground Control Review",
                    subtitle =
                        "Review base, movement, controlled strikes and disengagement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 205,
                    firstMoves =
                        "Combat Base - Knee Slide Left - Hip Switch Right - Lead Ground Straight - Technical Stand-Up",
                    firstCue =
                        "Keep every floor movement controlled and avoid striking the ground.",
                    secondMoves =
                        "Base Position - Sit-Through Right - Combat Base - Rear Ground Straight - Technical Stand-Up - Jab - Cross",
                    secondCue =
                        "Rebuild your MMA stance before finishing with punches."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — FULL MMA TRANSITIONS
    // ---------------------------------------------------------

    private fun fullMmaTransitions():
            TrainingChapterDefinition {

        val chapterId =
            "mma_fundamentals_full_transitions"

        return chapter(
            id = chapterId,
            title = "Full MMA Transitions",
            subtitle =
                "Connect striking, wrestling defence and ground movement.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_full_transitions_1",
                    title = "Strike-Sprawl-Strike",
                    subtitle =
                        "Return to offense after defending an imaginary shot.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 175,
                    firstMoves =
                        "Jab - Cross - Sprawl - Return to Stance - Cross",
                    firstCue =
                        "Recover both feet before throwing the final cross.",
                    secondMoves =
                        "Rear Low Kick - Sprawl - Circle Left - Return to Stance - Jab",
                    secondCue =
                        "Rebuild your stance after circling left."
                ),
                lesson(
                    id = "mma_fundamentals_full_transitions_2",
                    title = "Strike-Entry-Exit",
                    subtitle =
                        "Enter wrestling range and return safely to striking.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 180,
                    firstMoves =
                        "Jab - Cross - Level Change - Penetration Step - Return to Stance",
                    firstCue =
                        "Place your knee down gently and recover without twisting.",
                    secondMoves =
                        "Lead Teep - Cross - Shadow Single-Leg Entry - Exit Right",
                    secondCue =
                        "Recover the teep before entering and complete the right exit."
                ),
                lesson(
                    id = "mma_fundamentals_full_transitions_3",
                    title = "Ground-Stand-Strike",
                    subtitle =
                        "Return from ground movement directly into offense.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 185,
                    firstMoves =
                        "Hip Escape Left - Technical Stand-Up - Jab - Cross",
                    firstCue =
                        "Establish your stance completely before punching.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Technical Stand-Up - Rear Low Kick",
                    secondCue =
                        "Create safe kicking range before releasing the low kick."
                ),
                lesson(
                    id = "mma_fundamentals_full_transitions_4",
                    title = "Angle-to-Wrestling Entry",
                    subtitle =
                        "Create an angle before changing levels.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 190,
                    firstMoves =
                        "Jab - Step Left - Cross - Level Change",
                    firstCue =
                        "Complete the left step before punching and lowering your level.",
                    secondMoves =
                        "Cross - Step Right - Lead Hook - Shadow Single-Leg Entry",
                    secondCue =
                        "Keep your feet separated while moving right."
                ),
                lesson(
                    id = "mma_fundamentals_full_transitions_5",
                    title = "Repeated MMA Transitions",
                    subtitle =
                        "Move repeatedly between standing and ground positions.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 195,
                    firstMoves =
                        "Jab - Sprawl - Return to Stance - Level Change - Penetration Step - Return to Stance",
                    firstCue =
                        "Complete each recovery before beginning the next transition.",
                    secondMoves =
                        "Technical Stand-Up - Jab - Rear Low Kick - Sprawl - Circle Right - Return to Stance",
                    secondCue =
                        "Stay controlled as the session moves between levels."
                ),
                lesson(
                    id = "mma_fundamentals_full_transitions_6",
                    title = "Transition Review",
                    subtitle =
                        "Review complete standing, wrestling and ground chains.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Sprawl - Circle Left - Technical Stand-Up",
                    firstCue =
                        "Recover every position before moving to the next.",
                    secondMoves =
                        "Level Change Feint - Cross - Shadow Single-Leg Entry - Return to Stance - Sprawl - Jab - Cross",
                    secondCue =
                        "Keep the full sequence controlled and technically clean."
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
            "mma_fundamentals_assessment"

        return chapter(
            id = chapterId,
            title = "Fundamentals Assessment",
            subtitle =
                "Bring your core MMA striking, wrestling and ground skills together.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "mma_fundamentals_assessment_1",
                    title = "MMA Striking Review",
                    subtitle =
                        "Review wrestling-ready punches, kicks and knees.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 180,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Slip Left - Lead Hook",
                    firstCue =
                        "Recover the kick and complete Slip Left before countering.",
                    secondMoves =
                        "Lead Teep - Cross - Rear Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Recreate kicking range before the final strike."
                ),
                lesson(
                    id = "mma_fundamentals_assessment_2",
                    title = "Strike-Wrestle Review",
                    subtitle =
                        "Review takedown entries and wrestling feints.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 185,
                    firstMoves =
                        "Jab - Cross - Level Change - Penetration Step - Return to Stance",
                    firstCue =
                        "Keep the entry controlled and protect your lead knee.",
                    secondMoves =
                        "Level Change Feint - Cross - Lead Hook - Shadow Single-Leg Entry",
                    secondCue =
                        "Return to striking height after the feint."
                ),
                lesson(
                    id = "mma_fundamentals_assessment_3",
                    title = "Takedown Defence Review",
                    subtitle =
                        "Review down blocks, sprawls and directional circles.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 190,
                    firstMoves =
                        "Jab - Down Block - Cross - Rear Low Kick",
                    firstCue =
                        "Recover your posture before countering.",
                    secondMoves =
                        "Cross - Sprawl - Circle Right - Return to Stance - Jab - Cross",
                    secondCue =
                        "Complete the right circle before returning to strikes."
                ),
                lesson(
                    id = "mma_fundamentals_assessment_4",
                    title = "Ground Movement Review",
                    subtitle =
                        "Review escapes, sit-throughs and standing recovery.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 195,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Sit-Through Left - Technical Stand-Up",
                    firstCue =
                        "Use a padded surface and keep every transition controlled.",
                    secondMoves =
                        "Bridge Right - Reverse Hip Escape Left - Combat Base - Technical Stand-Up",
                    secondCue =
                        "Avoid pressure on your neck and rebuild your stance carefully."
                ),
                lesson(
                    id = "mma_fundamentals_assessment_5",
                    title = "Full Transition Review",
                    subtitle =
                        "Review movement between every major MMA range.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 205,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Sprawl - Circle Left - Technical Stand-Up",
                    firstCue =
                        "Complete each range transition before continuing.",
                    secondMoves =
                        "Lead Teep - Shadow Single-Leg Entry - Return to Stance - Down Block - Cross - Lead Hook",
                    secondCue =
                        "Recover your stance fully between offense and defence."
                ),
                lesson(
                    id = "mma_fundamentals_assessment_6",
                    title = "MMA Fundamentals Final",
                    subtitle =
                        "Complete the MMA Fundamentals stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 250,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Level Change - Penetration Step - Return to Stance - Sprawl - Circle Left",
                    firstCue =
                        "Maintain control while changing between striking and wrestling.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Technical Stand-Up - Jab - Slip Right - Cross - Rear Low Kick",
                    secondCue =
                        "Stand completely before completing Slip Right and returning to offense."
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