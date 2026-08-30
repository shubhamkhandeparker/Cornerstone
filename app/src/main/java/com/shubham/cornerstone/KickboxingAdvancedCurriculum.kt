package com.shubham.cornerstone

/**
 * Kickboxing Advanced Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Advanced sessions.
 *
 * This stage develops:
 *
 * - advanced combination architecture
 * - advanced kicking systems
 * - layered counter systems
 * - defensive mastery
 * - ring positioning
 * - fight-pace management
 * - complete Kickboxing integration
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object KickboxingAdvancedCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_KICKBOXING

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationArchitecture(),
            advancedKicking(),
            counterSystems(),
            defensiveMastery(),
            ringcraftAndPositioning(),
            fightPaceRounds(),
            advancedAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION ARCHITECTURE
    // ---------------------------------------------------------

    private fun combinationArchitecture():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_advanced_combination_architecture"

        return chapter(
            id = chapterId,
            title = "Combination Architecture",
            subtitle =
                "Construct adaptable attacks across every striking range.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_architecture_1",
                    title = "Layered Entries",
                    subtitle =
                        "Use feints and range tools before entering.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 430,
                    firstMoves =
                        "Lead Front Kick Feint - Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Keep the feint compact and enter only after rebuilding stance.",
                    secondMoves =
                        "Rear Low Kick Feint - Double Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Use the low threat to create the higher opening."
                ),
                lesson(
                    id = "kickboxing_advanced_architecture_2",
                    title = "Multi-Range Sequences",
                    subtitle =
                        "Move between long, middle and close range.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 435,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross - Lead Hook - Rear Knee - Step Back",
                    firstCue =
                        "Recover completely during every range transition.",
                    secondMoves =
                        "Rear Body Kick - Cross - Lead Hook - Lead Knee - Step Right",
                    secondCue =
                        "Shorten the punches as you enter close range."
                ),
                lesson(
                    id = "kickboxing_advanced_architecture_3",
                    title = "Double-Finish Combinations",
                    subtitle =
                        "Extend the attack after the expected finish.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 440,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick - Cross - Lead High Kick",
                    firstCue =
                        "Recover the low kick before beginning the second finish.",
                    secondMoves =
                        "Double Jab - Rear Body Kick - Cross - Lead Hook - Rear Knee",
                    secondCue =
                        "Rebuild stance between the kick and the return."
                ),
                lesson(
                    id = "kickboxing_advanced_architecture_4",
                    title = "Defensive Combination Layers",
                    subtitle =
                        "Place directional defence inside extended attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 445,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Complete Slip Right before beginning the counter layer.",
                    secondMoves =
                        "Lead Front Kick - Jab - Roll Left - Lead Hook - Cross - Rear Low Kick",
                    secondCue =
                        "Complete Roll Left and restore posture before attacking."
                ),
                lesson(
                    id = "kickboxing_advanced_architecture_5",
                    title = "Angle-Based Combinations",
                    subtitle =
                        "Continue attacking after changing position.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 450,
                    firstMoves =
                        "Jab - Cross - Pivot Left - Lead Hook - Rear Body Kick - Cross",
                    firstCue =
                        "Complete Pivot Left before restarting the attack.",
                    secondMoves =
                        "Lead Front Kick - Step Right - Cross - Lead Hook - Lead High Kick",
                    secondCue =
                        "Establish the new position before releasing offense."
                ),
                lesson(
                    id = "kickboxing_advanced_architecture_6",
                    title = "Architecture Checkpoint",
                    subtitle =
                        "Build complete attacks using feints, defence and angles.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 500,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick - Slip Right - Cross - Lead High Kick",
                    firstCue =
                        "Recover the kick before using Slip Right and beginning the second attack.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Roll Left - Lead Hook - Rear Knee - Step Left - Rear Body Kick",
                    secondCue =
                        "Keep Roll Left explicit and rebuild range before the final kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — ADVANCED KICKING
    // ---------------------------------------------------------

    private fun advancedKicking():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_advanced_kicking"

        return chapter(
            id = chapterId,
            title = "Advanced Kicking",
            subtitle =
                "Develop controlled advanced kicks and disguised setups.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_kicks_1",
                    title = "Question-Mark Kick",
                    subtitle =
                        "Disguise a controlled high kick behind a front-kick chamber.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 435,
                    firstMoves =
                        "Jab - Rear Question-Mark Kick",
                    firstCue =
                        "Lift through the front-kick chamber before turning the kick outward.",
                    secondMoves =
                        "Lead Front Kick Feint - Cross - Rear Question-Mark Kick",
                    secondCue =
                        "Prioritize balance and technical control over height."
                ),
                lesson(
                    id = "kickboxing_advanced_kicks_2",
                    title = "Switch High-Kick Setup",
                    subtitle =
                        "Hide the switch high kick behind hand combinations.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 440,
                    firstMoves =
                        "Jab - Cross - Switch Lead High Kick",
                    firstCue =
                        "Keep the switch compact and the high kick controlled.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Low Kick Feint - Switch Lead High Kick",
                    secondCue =
                        "Recover from the hook before changing your feet."
                ),
                lesson(
                    id = "kickboxing_advanced_kicks_3",
                    title = "Spinning Back Kick",
                    subtitle =
                        "Introduce a controlled spinning attack to the body.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 13,
                    xp = 445,
                    firstMoves =
                        "Jab - Cross - Spinning Back Kick",
                    firstCue =
                        "Turn your head first, find the target and recover into stance.",
                    secondMoves =
                        "Rear Low Kick - Step Back - Spinning Back Kick",
                    secondCue =
                        "Create space and complete the spin under control."
                ),
                lesson(
                    id = "kickboxing_advanced_kicks_4",
                    title = "Spinning Hook Kick",
                    subtitle =
                        "Develop controlled rotation for the spinning hook kick.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 450,
                    firstMoves =
                        "Jab Feint - Spinning Hook Kick",
                    firstCue =
                        "Turn your head before the kick and prioritize control over speed.",
                    secondMoves =
                        "Lead Front Kick - Step Back - Spinning Hook Kick",
                    secondCue =
                        "Create safe space before beginning the rotation."
                ),
                lesson(
                    id = "kickboxing_advanced_kicks_5",
                    title = "Advanced Kick Chains",
                    subtitle =
                        "Connect conventional and advanced kicking attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 455,
                    firstMoves =
                        "Rear Low Kick - Cross - Lead Hook - Rear Question-Mark Kick",
                    firstCue =
                        "Recover the low kick before beginning the hand combination.",
                    secondMoves =
                        "Switch Lead Body Kick - Cross - Spinning Back Kick",
                    secondCue =
                        "Rebuild stance and create space before spinning."
                ),
                lesson(
                    id = "kickboxing_advanced_kicks_6",
                    title = "Advanced Kicking Checkpoint",
                    subtitle =
                        "Use advanced kicks without sacrificing safe recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 505,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Cross - Rear Question-Mark Kick",
                    firstCue =
                        "Change the final target while maintaining complete control.",
                    secondMoves =
                        "Lead Front Kick - Step Left - Cross - Lead Hook - Spinning Back Kick",
                    secondCue =
                        "Confirm your stance and space before beginning the spin."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — COUNTER SYSTEMS
    // ---------------------------------------------------------

    private fun counterSystems():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_advanced_counter_systems"

        return chapter(
            id = chapterId,
            title = "Counter Systems",
            subtitle =
                "Build layered counters for punches, kicks and pressure.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_counters_1",
                    title = "Slip Right Counter System",
                    subtitle =
                        "Build multiple returns from Slip Right.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 440,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Low Kick - Cross",
                    firstCue =
                        "Complete Slip Right before building the counter sequence.",
                    secondMoves =
                        "Slip Right - Cross - Rear Knee - Step Left - Lead Body Kick",
                    secondCue =
                        "Recover from close range before releasing the final kick."
                ),
                lesson(
                    id = "kickboxing_advanced_counters_2",
                    title = "Slip Left Counter System",
                    subtitle =
                        "Build layered lead-side returns from Slip Left.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 445,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross - Lead Low Kick - Cross",
                    firstCue =
                        "Complete Slip Left before rotating into the hook.",
                    secondMoves =
                        "Slip Left - Lead Hook - Rear Body Kick - Cross - Lead Knee",
                    secondCue =
                        "Recover between the body kick and the close-range return."
                ),
                lesson(
                    id = "kickboxing_advanced_counters_3",
                    title = "Check Counter System",
                    subtitle =
                        "Build extended returns after defending low kicks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 13,
                    xp = 450,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Body Kick - Cross",
                    firstCue =
                        "Set the checking leg down before beginning the return.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Low Kick - Rear High Kick",
                    secondCue =
                        "Recover your stance between both kicks."
                ),
                lesson(
                    id = "kickboxing_advanced_counters_4",
                    title = "Pressure Counter System",
                    subtitle =
                        "Interrupt pressure before turning it into offense.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 455,
                    firstMoves =
                        "Step Back - Lead Front Kick - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Create space before intercepting and returning.",
                    secondMoves =
                        "Rear Knee - Step Left - Cross - Lead Hook - Lead Body Kick",
                    secondCue =
                        "Recover the knee before changing position."
                ),
                lesson(
                    id = "kickboxing_advanced_counters_5",
                    title = "Counter and Re-Counter",
                    subtitle =
                        "Defend again inside your counter sequence.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 460,
                    firstMoves =
                        "Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Keep Slip Right and Roll Left clearly separated.",
                    secondMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Recover from the check before using Slip Left."
                ),
                lesson(
                    id = "kickboxing_advanced_counters_6",
                    title = "Counter-System Checkpoint",
                    subtitle =
                        "Defend, counter, defend again and finish safely.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 510,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Lead Check - Cross - Rear Body Kick",
                    firstCue =
                        "Complete each defensive phase before restarting offense.",
                    secondMoves =
                        "Slip Left - Lead Hook - Roll Right - Cross - Rear Knee - Step Left - Lead High Kick",
                    secondCue =
                        "Keep Slip Left and Roll Right explicit throughout the sequence."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — DEFENSIVE MASTERY
    // ---------------------------------------------------------

    private fun defensiveMastery():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_advanced_defensive_mastery"

        return chapter(
            id = chapterId,
            title = "Defensive Mastery",
            subtitle =
                "Use layered defence while remaining ready to counter.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_defence_1",
                    title = "Four-Direction Defence",
                    subtitle =
                        "Connect all directional boxing defences.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 445,
                    firstMoves =
                        "Slip Right - Roll Left - Slip Left - Roll Right - Cross",
                    firstCue =
                        "Make all four defensive directions distinct and controlled.",
                    secondMoves =
                        "Slip Left - Roll Right - Slip Right - Roll Left - Lead Hook",
                    secondCue =
                        "Maintain posture and eye position through the full chain."
                ),
                lesson(
                    id = "kickboxing_advanced_defence_2",
                    title = "Directional Defence to Kicks",
                    subtitle =
                        "Finish head-movement chains with kicking counters.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 450,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Complete Roll Left before beginning the counter.",
                    secondMoves =
                        "Slip Left - Roll Right - Cross - Lead High Kick",
                    secondCue =
                        "Recover posture before lifting the lead leg."
                ),
                lesson(
                    id = "kickboxing_advanced_defence_3",
                    title = "Checks Inside Exchanges",
                    subtitle =
                        "Defend kicks during active combinations.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 13,
                    xp = 455,
                    firstMoves =
                        "Jab - Cross - Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Stop the combination, check and rebuild before countering.",
                    secondMoves =
                        "Lead Front Kick - Rear Check - Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Recover the rear checking foot before throwing the jab."
                ),
                lesson(
                    id = "kickboxing_advanced_defence_4",
                    title = "Defensive Angle Escapes",
                    subtitle =
                        "Use defence to create a safe exit.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 460,
                    firstMoves =
                        "Slip Right - Cross - Pivot Left - Rear Low Kick",
                    firstCue =
                        "Complete the counter before beginning Pivot Left.",
                    secondMoves =
                        "Roll Right - Cross - Lead Hook - Pivot Right - Lead Body Kick",
                    secondCue =
                        "Restore posture after Roll Right before moving."
                ),
                lesson(
                    id = "kickboxing_advanced_defence_5",
                    title = "Defend Under Pressure",
                    subtitle =
                        "Maintain structure through extended defensive sequences.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 465,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Step Left - Cross - Rear Body Kick",
                    firstCue =
                        "Do not counter until the entire defensive sequence is complete.",
                    secondMoves =
                        "Slip Left - Roll Right - Rear Check - Step Right - Lead Hook - Cross",
                    secondCue =
                        "Rebuild stance after the check before moving right."
                ),
                lesson(
                    id = "kickboxing_advanced_defence_6",
                    title = "Defensive Mastery Checkpoint",
                    subtitle =
                        "Defend punches and kicks before countering from an angle.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 515,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Lead Hook - Rear High Kick - Step Left",
                    firstCue =
                        "Keep every defensive direction clear and control the high kick.",
                    secondMoves =
                        "Slip Left - Roll Right - Rear Check - Cross - Lead Knee - Step Right - Rear Body Kick",
                    secondCue =
                        "Recover from close range before the final body kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — RINGCRAFT AND POSITIONING
    // ---------------------------------------------------------

    private fun ringcraftAndPositioning():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_advanced_ringcraft_positioning"

        return chapter(
            id = chapterId,
            title = "Ringcraft & Positioning",
            subtitle =
                "Control space, angles and imaginary ring position.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_ringcraft_1",
                    title = "Control the Center",
                    subtitle =
                        "Hold central position using long-range attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 450,
                    firstMoves =
                        "Double Jab - Lead Front Kick - Step Left - Jab",
                    firstCue =
                        "Use compact movement to maintain the center.",
                    secondMoves =
                        "Rear Front Kick - Cross - Lead Hook - Step Right",
                    secondCue =
                        "Recover every strike before changing position."
                ),
                lesson(
                    id = "kickboxing_advanced_ringcraft_2",
                    title = "Cut Off Movement",
                    subtitle =
                        "Use lateral steps to meet an imaginary moving opponent.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 455,
                    firstMoves =
                        "Step Left - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Complete the lateral step before attacking.",
                    secondMoves =
                        "Step Right - Double Jab - Lead Body Kick",
                    secondCue =
                        "Keep your feet separated while controlling the angle."
                ),
                lesson(
                    id = "kickboxing_advanced_ringcraft_3",
                    title = "Escape Left",
                    subtitle =
                        "Leave an imaginary trapped position toward the left.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 13,
                    xp = 460,
                    firstMoves =
                        "Lead Front Kick - Step Left - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Create room with the front kick before escaping left.",
                    secondMoves =
                        "Slip Right - Cross - Pivot Left - Rear Body Kick",
                    secondCue =
                        "Complete Slip Right and the cross before pivoting."
                ),
                lesson(
                    id = "kickboxing_advanced_ringcraft_4",
                    title = "Escape Right",
                    subtitle =
                        "Leave an imaginary trapped position toward the right.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 465,
                    firstMoves =
                        "Rear Front Kick - Step Right - Jab - Cross - Lead Body Kick",
                    firstCue =
                        "Recover the rear leg before escaping right.",
                    secondMoves =
                        "Slip Left - Lead Hook - Pivot Right - Rear Low Kick",
                    secondCue =
                        "Complete Slip Left and the hook before pivoting."
                ),
                lesson(
                    id = "kickboxing_advanced_ringcraft_5",
                    title = "Turn and Re-Attack",
                    subtitle =
                        "Change position before immediately restarting offense.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 470,
                    firstMoves =
                        "Jab - Cross - Pivot Left - Lead Hook - Rear High Kick",
                    firstCue =
                        "Establish the new angle before releasing the high kick.",
                    secondMoves =
                        "Lead Front Kick - Pivot Right - Cross - Lead Hook - Rear Knee",
                    secondCue =
                        "Recover the front kick before beginning Pivot Right."
                ),
                lesson(
                    id = "kickboxing_advanced_ringcraft_6",
                    title = "Ringcraft Checkpoint",
                    subtitle =
                        "Control the center, escape pressure and re-attack.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 520,
                    firstMoves =
                        "Double Jab - Rear Low Kick - Step Left - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Use the step to create a new attacking lane.",
                    secondMoves =
                        "Lead Front Kick - Slip Right - Cross - Pivot Left - Lead High Kick - Step Back",
                    secondCue =
                        "Keep Slip Right explicit and control every position change."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — FIGHT-PACE ROUNDS
    // ---------------------------------------------------------

    private fun fightPaceRounds():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_advanced_fight_pace_rounds"

        return chapter(
            id = chapterId,
            title = "Fight-Pace Rounds",
            subtitle =
                "Manage technical output across demanding rounds.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_pace_1",
                    title = "Technical Volume",
                    subtitle =
                        "Maintain clean technique during repeated combinations.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 13,
                    xp = 455,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick - Cross",
                    firstCue =
                        "Keep every repetition compact and balanced.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Prioritize consistent technique over maximum speed."
                ),
                lesson(
                    id = "kickboxing_advanced_pace_2",
                    title = "Controlled Bursts",
                    subtitle =
                        "Alternate measured movement with short attacking bursts.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 13,
                    xp = 460,
                    firstMoves =
                        "Jab - Pause - Double Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Remain protected during the pause before increasing output.",
                    secondMoves =
                        "Lead Front Kick - Pause - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Change pace without allowing technique to collapse."
                ),
                lesson(
                    id = "kickboxing_advanced_pace_3",
                    title = "Defensive Pace",
                    subtitle =
                        "Defend cleanly while maintaining active output.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 13,
                    xp = 465,
                    firstMoves =
                        "Jab - Slip Right - Cross - Roll Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep Slip Right and Roll Left deliberate even at higher pace.",
                    secondMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover from each defence before resuming offense."
                ),
                lesson(
                    id = "kickboxing_advanced_pace_4",
                    title = "Range-Pace Changes",
                    subtitle =
                        "Change output while moving between striking ranges.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 14,
                    xp = 470,
                    firstMoves =
                        "Lead Front Kick - Double Jab - Cross - Rear Knee - Step Back",
                    firstCue =
                        "Control every transition from long range to close range.",
                    secondMoves =
                        "Rear Body Kick - Cross - Lead Hook - Lead Knee - Step Right",
                    secondCue =
                        "Recover before leaving the close-range exchange."
                ),
                lesson(
                    id = "kickboxing_advanced_pace_5",
                    title = "Final-Minute Pressure",
                    subtitle =
                        "Increase output while preserving tactical discipline.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 14,
                    xp = 475,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Rear Low Kick - Cross - Lead Body Kick",
                    firstCue =
                        "Increase activity without chasing or overreaching.",
                    secondMoves =
                        "Lead Front Kick - Jab - Cross - Rear Knee - Step Left - Lead High Kick",
                    secondCue =
                        "Recover every weapon and control the final high kick."
                ),
                lesson(
                    id = "kickboxing_advanced_pace_6",
                    title = "Fight-Pace Checkpoint",
                    subtitle =
                        "Sustain adaptable offense and defence across a full round.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 525,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Body Kick - Slip Right - Cross - Rear Low Kick",
                    firstCue =
                        "Keep Slip Right explicit while maintaining technical output.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Roll Left - Lead Hook - Rear Knee - Step Back - Lead Body Kick",
                    secondCue =
                        "Complete Roll Left and rebuild range before the final kick."
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
            "kickboxing_advanced_assessment"

        return chapter(
            id = chapterId,
            title = "Advanced Assessment",
            subtitle =
                "Complete the full Cornerstone Kickboxing Fight Path.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "kickboxing_advanced_assessment_1",
                    title = "Combination Mastery",
                    subtitle =
                        "Review complete multi-range combination construction.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 13,
                    xp = 470,
                    firstMoves =
                        "Lead Front Kick Feint - Jab - Cross - Lead Hook - Rear Knee - Step Back - Rear Body Kick",
                    firstCue =
                        "Move through all ranges while preserving stance.",
                    secondMoves =
                        "Rear Low Kick Feint - Double Jab - Cross - Lead Body Kick - Cross - Rear High Kick",
                    secondCue =
                        "Control the high kick after the extended setup."
                ),
                lesson(
                    id = "kickboxing_advanced_assessment_2",
                    title = "Advanced Kicking Review",
                    subtitle =
                        "Review disguised and rotational kicking attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 13,
                    xp = 475,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Cross - Rear Question-Mark Kick",
                    firstCue =
                        "Maintain control while changing the final target.",
                    secondMoves =
                        "Lead Front Kick - Step Back - Cross - Spinning Back Kick",
                    secondCue =
                        "Confirm your space and turn your head before spinning."
                ),
                lesson(
                    id = "kickboxing_advanced_assessment_3",
                    title = "Counter-System Review",
                    subtitle =
                        "Review layered defence-to-offense transitions.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 14,
                    xp = 480,
                    firstMoves =
                        "Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick - Cross",
                    firstCue =
                        "Keep Slip Right and Roll Left distinct before countering.",
                    secondMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Knee - Step Right",
                    secondCue =
                        "Recover from the check before using Slip Left."
                ),
                lesson(
                    id = "kickboxing_advanced_assessment_4",
                    title = "Defensive Mastery Review",
                    subtitle =
                        "Review directional defence, checks and exits.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 14,
                    xp = 485,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Check - Cross - Rear High Kick - Step Left",
                    firstCue =
                        "Complete the defensive chain before beginning the counter.",
                    secondMoves =
                        "Slip Left - Roll Right - Rear Check - Lead Hook - Rear Body Kick - Pivot Right",
                    secondCue =
                        "Keep every direction explicit and recover before pivoting."
                ),
                lesson(
                    id = "kickboxing_advanced_assessment_5",
                    title = "Ringcraft and Pace Review",
                    subtitle =
                        "Review positioning, pressure and controlled output.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 15,
                    xp = 490,
                    firstMoves =
                        "Double Jab - Rear Low Kick - Pivot Left - Cross - Lead Hook - Rear Knee - Step Back",
                    firstCue =
                        "Control the angle and recover before leaving close range.",
                    secondMoves =
                        "Lead Front Kick - Step Right - Cross - Lead Body Kick - Cross - Rear High Kick",
                    secondCue =
                        "Maintain positioning and control the final kick."
                ),
                lesson(
                    id = "kickboxing_advanced_assessment_6",
                    title = "Kickboxing Final Round",
                    subtitle =
                        "Complete all 192 sessions of the Kickboxing Fight Path.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 16,
                    xp = 600,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Rear Low Kick - Slip Right - Cross - Rear Question-Mark Kick - Step Left",
                    firstCue =
                        "Use Slip Right explicitly, control the advanced kick and finish in stance.",
                    secondMoves =
                        "Lead Front Kick - Double Jab - Roll Left - Lead Hook - Rear Knee - Step Back - Cross - Spinning Back Kick",
                    secondCue =
                        "Complete Roll Left, rebuild space and control the final spinning attack."
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