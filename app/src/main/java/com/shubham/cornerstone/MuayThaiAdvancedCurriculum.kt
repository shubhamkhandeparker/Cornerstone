package com.shubham.cornerstone

/**
 * Muay Thai Advanced Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Advanced sessions.
 *
 * This stage develops:
 *
 * - advanced combination architecture
 * - layered feint systems and traps
 * - intelligent kick selection
 * - counter-offense systems
 * - close-range command
 * - tactical round management
 * - independent advanced Muay Thai decision-making
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object MuayThaiAdvancedCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MUAY_THAI

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationArchitecture(),
            feintSystemsAndTraps(),
            advancedKickSelection(),
            counterOffenseSystems(),
            closeRangeCommand(),
            tacticalRoundManagement(),
            advancedAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION ARCHITECTURE
    // ---------------------------------------------------------

    private fun combinationArchitecture():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_advanced_combination_architecture"

        return chapter(
            id = chapterId,
            title = "Combination Architecture",
            subtitle =
                "Construct adaptable attacks across every striking range.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_combination_architecture_1",
                    title = "Layered Entries",
                    subtitle =
                        "Enter behind multiple threats without losing structure.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Jab Feint - Lead Teep - Cross - Rear Body Kick",
                    firstCue =
                        "Make each entry action believable and recover before changing range.",
                    secondMoves =
                        "Lead Teep Feint - Jab - Lead Hook - Rear Knee",
                    secondCue =
                        "Stay tall while moving from long range into knee range."
                ),
                lesson(
                    id = "muaythai_advanced_combination_architecture_2",
                    title = "Multi-Range Sequences",
                    subtitle =
                        "Connect long, middle and close-range attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Lead Horizontal Elbow",
                    firstCue =
                        "Recover every strike before continuing into the next range.",
                    secondMoves =
                        "Rear Low Kick - Jab - Lead Hook - Rear Knee - Exit Left",
                    secondCue =
                        "Maintain your stance through every distance change."
                ),
                lesson(
                    id = "muaythai_advanced_combination_architecture_3",
                    title = "Finish Selection",
                    subtitle =
                        "Choose between kicks, knees and exits from one entry.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 280,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Use the body kick when the combination finishes at kicking range.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Knee - Exit Right",
                    secondCue =
                        "Use the knee only after entering close range with stable posture."
                ),
                lesson(
                    id = "muaythai_advanced_combination_architecture_4",
                    title = "Re-Attack Branches",
                    subtitle =
                        "Build a second attack after a deliberate reset.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 285,
                    firstMoves =
                        "Jab - Rear Low Kick - Reset - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Rebuild your stance during the reset before selecting the second attack.",
                    secondMoves =
                        "Lead Teep - Reset - Jab Feint - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Use the reset to confirm range rather than rushing forward."
                ),
                lesson(
                    id = "muaythai_advanced_combination_architecture_5",
                    title = "Exit and Rebuild",
                    subtitle =
                        "Finish complex attacks with safe positioning.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 290,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Lead Hook - Exit Left - Lead Teep",
                    firstCue =
                        "Complete the exit before using the teep to rebuild distance.",
                    secondMoves =
                        "Lead Teep Feint - Cross - Rear Knee - Lead Horizontal Elbow - Exit Right",
                    secondCue =
                        "Recover every close-range strike before leaving the exchange."
                ),
                lesson(
                    id = "muaythai_advanced_combination_architecture_6",
                    title = "Architecture Review",
                    subtitle =
                        "Review layered entries, branches, re-attacks and exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 305,
                    firstMoves =
                        "Jab Feint - Lead Teep - Cross - Rear Body Kick - Reset - Lead Hook - Rear Knee",
                    firstCue =
                        "Maintain clear structure through the entry, reset and re-attack.",
                    secondMoves =
                        "Lead Low Kick - Cross - Lead Horizontal Elbow - Rear Knee - Exit Left - Rear Body Kick",
                    secondCue =
                        "Recreate kicking range before releasing the final body kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — FEINT SYSTEMS AND TRAPS
    // ---------------------------------------------------------

    private fun feintSystemsAndTraps():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_advanced_feint_systems"

        return chapter(
            id = chapterId,
            title = "Feint Systems & Traps",
            subtitle =
                "Layer believable threats to create planned openings.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_feint_systems_1",
                    title = "Jab-Teep Disguise",
                    subtitle =
                        "Make jab and teep entries difficult to predict.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Jab Feint - Lead Teep - Cross",
                    firstCue =
                        "Keep the jab feint compact and the lead teep direct.",
                    secondMoves =
                        "Lead Teep Feint - Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Begin the teep and feint from the same balanced posture."
                ),
                lesson(
                    id = "muaythai_advanced_feint_systems_2",
                    title = "Low-Body Disguise",
                    subtitle =
                        "Hide target changes behind matching kick preparation.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Rear Low Kick Feint - Jab - Rear Body Kick",
                    firstCue =
                        "Keep the initial chamber controlled before changing the target.",
                    secondMoves =
                        "Lead Low Kick Feint - Cross - Lead Body Kick",
                    secondCue =
                        "Return the lead foot beneath you before throwing the cross."
                ),
                lesson(
                    id = "muaythai_advanced_feint_systems_3",
                    title = "Knee-Elbow Traps",
                    subtitle =
                        "Use close-range feints to open compact attacks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 285,
                    firstMoves =
                        "Rear Knee Feint - Lead Horizontal Elbow - Rear Knee",
                    firstCue =
                        "Recover the rear foot before rotating the lead elbow.",
                    secondMoves =
                        "Lead Knee Feint - Cross - Rear Horizontal Elbow - Lead Knee",
                    secondCue =
                        "Maintain tall posture throughout the close-range sequence."
                ),
                lesson(
                    id = "muaythai_advanced_feint_systems_4",
                    title = "Draw-and-Counter Trap",
                    subtitle =
                        "Give ground deliberately before returning.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 290,
                    firstMoves =
                        "Step Back - Jab Feint - Cross - Rear Body Kick",
                    firstCue =
                        "Complete the step back before driving forward with the counter.",
                    secondMoves =
                        "Step Back - Lead Teep - Lead Teep Feint - Rear Low Kick",
                    secondCue =
                        "Use the real teep to establish the reaction before the feint."
                ),
                lesson(
                    id = "muaythai_advanced_feint_systems_5",
                    title = "Broken-Rhythm Trap",
                    subtitle =
                        "Use pauses and acceleration to disrupt timing.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 295,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Remain guarded during the pause and accelerate without overreaching.",
                    secondMoves =
                        "Lead Teep - Pause - Jab Feint - Rear Body Kick",
                    secondCue =
                        "Keep your stance active while changing the rhythm."
                ),
                lesson(
                    id = "muaythai_advanced_feint_systems_6",
                    title = "Feint-System Review",
                    subtitle =
                        "Combine layered disguises, traps and timing changes.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 310,
                    firstMoves =
                        "Jab Feint - Lead Teep - Rear Low Kick Feint - Cross - Rear Body Kick",
                    firstCue =
                        "Keep each false attack compact and every real attack controlled.",
                    secondMoves =
                        "Lead Teep Feint - Jab - Pause - Lead Hook - Rear Knee - Exit Right",
                    secondCue =
                        "Use the rhythm change to enter and complete the exit after recovering."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — ADVANCED KICK SELECTION
    // ---------------------------------------------------------

    private fun advancedKickSelection():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_advanced_kick_selection"

        return chapter(
            id = chapterId,
            title = "Advanced Kick Selection",
            subtitle =
                "Select kicking targets according to range and position.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_kick_selection_1",
                    title = "Low-Body-High Chain",
                    subtitle =
                        "Progress safely through three kicking levels.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 280,
                    firstMoves =
                        "Jab - Rear Low Kick - Jab - Rear Body Kick",
                    firstCue =
                        "Use the same entry while changing the final target.",
                    secondMoves =
                        "Rear Low Kick - Reset - Rear Body Kick - Reset - Rear High Kick",
                    secondCue =
                        "Use a comfortable high-kick range and reset completely between kicks."
                ),
                lesson(
                    id = "muaythai_advanced_kick_selection_2",
                    title = "Switch-Kick Selection",
                    subtitle =
                        "Use a compact switch step to attack different levels.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 285,
                    firstMoves =
                        "Jab - Switch Lead Body Kick - Cross",
                    firstCue =
                        "Keep the switch narrow and recover before throwing the cross.",
                    secondMoves =
                        "Lead Low Kick - Jab Feint - Switch Lead High Kick",
                    secondCue =
                        "Use only a controlled high-kick height and return safely to stance."
                ),
                lesson(
                    id = "muaythai_advanced_kick_selection_3",
                    title = "Teep Target Selection",
                    subtitle =
                        "Use lead and rear teeps to control changing distance.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 290,
                    firstMoves =
                        "Lead Teep - Jab - Rear Teep - Rear Low Kick",
                    firstCue =
                        "Recover each teep before beginning the next attack.",
                    secondMoves =
                        "Rear Teep - Lead Teep Feint - Cross - Rear Body Kick",
                    secondCue =
                        "Maintain tall posture while changing from linear to circular attacks."
                ),
                lesson(
                    id = "muaythai_advanced_kick_selection_4",
                    title = "Same-Side Double Attacks",
                    subtitle =
                        "Repeat one kicking side without sacrificing balance.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 295,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross - Rear Body Kick",
                    firstCue =
                        "Return the rear foot to stance between the two kicks.",
                    secondMoves =
                        "Lead Teep - Lead Low Kick - Jab - Lead Body Kick",
                    secondCue =
                        "Keep every lead-foot landing controlled."
                ),
                lesson(
                    id = "muaythai_advanced_kick_selection_5",
                    title = "Kicks From Angles",
                    subtitle =
                        "Create an angle before selecting the kick.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Jab - Cross - Pivot Left - Rear Body Kick",
                    firstCue =
                        "Complete Pivot Left before rotating into the kick.",
                    secondMoves =
                        "Lead Teep Feint - Pivot Right - Lead Body Kick",
                    secondCue =
                        "Keep your feet separated while creating the right-side angle."
                ),
                lesson(
                    id = "muaythai_advanced_kick_selection_6",
                    title = "Kick-Selection Review",
                    subtitle =
                        "Review targets, sides, angles and controlled recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 315,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick - Pivot Left - Lead Teep",
                    firstCue =
                        "Choose each kick from a stable range and finish by controlling distance.",
                    secondMoves =
                        "Jab Feint - Switch Lead Body Kick - Cross - Rear High Kick - Exit Right",
                    secondCue =
                        "Keep the high kick comfortable and complete the exit after recovery."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — COUNTER-OFFENSE SYSTEMS
    // ---------------------------------------------------------

    private fun counterOffenseSystems():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_advanced_counter_offense"

        return chapter(
            id = chapterId,
            title = "Counter-Offense Systems",
            subtitle =
                "Turn explicit defensive actions into structured attacks.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_counter_offense_1",
                    title = "Lead-Check Counter Branches",
                    subtitle =
                        "Choose between kicking and close-range returns.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 285,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Recover the checking leg before transferring weight into the cross.",
                    secondMoves =
                        "Lead Check - Jab - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Rebuild your stance before entering close range."
                ),
                lesson(
                    id = "muaythai_advanced_counter_offense_2",
                    title = "Rear-Check Counter Branches",
                    subtitle =
                        "Build balanced returns after a rear-leg check.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Rear Check - Jab - Cross - Lead Body Kick",
                    firstCue =
                        "Return the rear foot to its original position before punching.",
                    secondMoves =
                        "Rear Check - Cross - Lead Hook - Rear Knee - Exit Left",
                    secondCue =
                        "Maintain posture through the counter and complete the exit."
                ),
                lesson(
                    id = "muaythai_advanced_counter_offense_3",
                    title = "Slip Left Counter System",
                    subtitle =
                        "Use Slip Left to begin layered counter-offense.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 295,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep Slip Left compact and return your head before kicking.",
                    secondMoves =
                        "Slip Left - Lead Hook - Rear Knee - Lead Horizontal Elbow - Exit Right",
                    secondCue =
                        "Stay balanced as the counter moves through close range."
                ),
                lesson(
                    id = "muaythai_advanced_counter_offense_4",
                    title = "Slip Right Counter System",
                    subtitle =
                        "Use Slip Right to create rear-side counter attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Complete Slip Right without leaning beyond your stance.",
                    secondMoves =
                        "Slip Right - Cross - Rear Knee - Lead Horizontal Elbow - Step Back",
                    secondCue =
                        "Recover your posture before entering knee range."
                ),
                lesson(
                    id = "muaythai_advanced_counter_offense_5",
                    title = "Roll Left and Roll Right Counters",
                    subtitle =
                        "Use shallow directional rolls with immediate returns.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Roll Left - Lead Hook - Rear Low Kick - Cross",
                    firstCue =
                        "Keep Roll Left shallow and return your eyes forward before countering.",
                    secondMoves =
                        "Roll Right - Cross - Lead Hook - Rear Knee",
                    secondCue =
                        "Keep Roll Right compact so your posture remains ready for knees."
                ),
                lesson(
                    id = "muaythai_advanced_counter_offense_6",
                    title = "Counter-Offense Review",
                    subtitle =
                        "Combine checks and explicit directional head movement.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 320,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Recover after the check and complete Slip Left before continuing.",
                    secondMoves =
                        "Rear Check - Jab - Slip Right - Cross - Roll Left - Lead Hook - Rear Knee - Exit Right",
                    secondCue =
                        "Keep every defensive action compact, directional and balanced."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — CLOSE-RANGE COMMAND
    // ---------------------------------------------------------

    private fun closeRangeCommand():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_advanced_close_range"

        return chapter(
            id = chapterId,
            title = "Close-Range Command",
            subtitle =
                "Control knee and elbow sequences with disciplined posture.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_close_range_1",
                    title = "Long-Guard Entry",
                    subtitle =
                        "Use the long guard to organise a safe close-range entry.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Long Guard - Rear Knee - Lead Horizontal Elbow",
                    firstCue =
                        "Keep the rear hand protecting your face during the long guard.",
                    secondMoves =
                        "Long Guard - Lead Knee - Rear Horizontal Elbow - Exit Left",
                    secondCue =
                        "Recover the lead foot before rotating the elbow and exiting."
                ),
                lesson(
                    id = "muaythai_advanced_close_range_2",
                    title = "Punch-Elbow-Knee Chain",
                    subtitle =
                        "Build continuous offense through close range.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 295,
                    firstMoves =
                        "Jab - Cross - Lead Horizontal Elbow - Rear Knee",
                    firstCue =
                        "Shorten the punches as range closes and keep the free hand high.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Horizontal Elbow - Lead Knee",
                    secondCue =
                        "Recover each strike before beginning the next rotation."
                ),
                lesson(
                    id = "muaythai_advanced_close_range_3",
                    title = "Alternating-Knee Rhythm",
                    subtitle =
                        "Alternate knees while maintaining balance and posture.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Rear Knee - Lead Knee - Rear Horizontal Elbow",
                    firstCue =
                        "Return each foot beneath you before lifting the opposite knee.",
                    secondMoves =
                        "Lead Knee - Rear Knee - Lead Horizontal Elbow - Step Back",
                    secondCue =
                        "Stay tall during the knees and recover before stepping back."
                ),
                lesson(
                    id = "muaythai_advanced_close_range_4",
                    title = "Elbow Exit System",
                    subtitle =
                        "Finish elbow combinations with a planned exit.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Lead Horizontal Elbow - Rear Horizontal Elbow - Exit Left - Rear Body Kick",
                    firstCue =
                        "Complete the left exit and recreate kicking distance.",
                    secondMoves =
                        "Rear Horizontal Elbow - Lead Knee - Exit Right - Lead Teep",
                    secondCue =
                        "Recover the knee before exiting and controlling distance."
                ),
                lesson(
                    id = "muaythai_advanced_close_range_5",
                    title = "Close-Range Re-Entry",
                    subtitle =
                        "Exit briefly before returning with another close attack.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Rear Knee - Lead Horizontal Elbow - Step Back - Jab - Rear Knee",
                    firstCue =
                        "Use the step back to rebuild position before re-entering.",
                    secondMoves =
                        "Lead Knee - Rear Horizontal Elbow - Exit Left - Cross - Lead Horizontal Elbow",
                    secondCue =
                        "Complete the exit before driving back into close range."
                ),
                lesson(
                    id = "muaythai_advanced_close_range_6",
                    title = "Close-Range Command Review",
                    subtitle =
                        "Review entries, knees, elbows, exits and re-entries.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 325,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Knee - Lead Horizontal Elbow - Exit Left",
                    firstCue =
                        "Move through each range while maintaining disciplined posture.",
                    secondMoves =
                        "Long Guard - Lead Knee - Rear Horizontal Elbow - Step Back - Cross - Rear Body Kick",
                    secondCue =
                        "Recover from close range before beginning the final attack."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — TACTICAL ROUND MANAGEMENT
    // ---------------------------------------------------------

    private fun tacticalRoundManagement():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_advanced_round_management"

        return chapter(
            id = chapterId,
            title = "Tactical Round Management",
            subtitle =
                "Control pace, position and output through complete rounds.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_round_management_1",
                    title = "Opening-Range Control",
                    subtitle =
                        "Begin rounds by establishing distance and rhythm.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 295,
                    firstMoves =
                        "Lead Teep - Jab - Step Back - Rear Teep",
                    firstCue =
                        "Use long-range weapons to establish distance before committing.",
                    secondMoves =
                        "Double Jab - Lead Low Kick - Exit Left",
                    secondCue =
                        "Score from stable range and leave the centre line."
                ),
                lesson(
                    id = "muaythai_advanced_round_management_2",
                    title = "Controlled Pressure",
                    subtitle =
                        "Increase output without allowing technique to collapse.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Lead Teep - Step Forward - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Advance behind your guard and recover the kick before continuing.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Shorten the strikes as pressure moves into close range."
                ),
                lesson(
                    id = "muaythai_advanced_round_management_3",
                    title = "Score and Exit",
                    subtitle =
                        "Complete clean attacks before leaving safely.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Exit Left",
                    firstCue =
                        "Recover the kick completely before exiting left.",
                    secondMoves =
                        "Lead Teep Feint - Cross - Rear Knee - Exit Right",
                    secondCue =
                        "Finish the knee with control before leaving close range."
                ),
                lesson(
                    id = "muaythai_advanced_round_management_4",
                    title = "Recovery Under Pressure",
                    subtitle =
                        "Use structured movement to regain position.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Long Guard - Step Back - Lead Teep - Exit Left",
                    firstCue =
                        "Create distance without crossing your feet.",
                    secondMoves =
                        "Lead Check - Cross - Step Back - Rear Teep",
                    secondCue =
                        "Recover the checking leg before countering and rebuilding range."
                ),
                lesson(
                    id = "muaythai_advanced_round_management_5",
                    title = "Final-Minute Pace",
                    subtitle =
                        "Raise output while maintaining technical control.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 315,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Reset - Lead Teep - Jab - Rear Body Kick",
                    firstCue =
                        "Increase pace through clean resets rather than rushed movement.",
                    secondMoves =
                        "Lead Check - Cross - Lead Hook - Rear Knee - Exit Left - Jab - Rear Low Kick",
                    secondCue =
                        "Keep every transition balanced as output increases."
                ),
                lesson(
                    id = "muaythai_advanced_round_management_6",
                    title = "Round-Management Review",
                    subtitle =
                        "Review opening control, pressure, recovery and finishing pace.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 330,
                    firstMoves =
                        "Lead Teep - Jab Feint - Cross - Rear Body Kick - Exit Left - Rear Teep",
                    firstCue =
                        "Control the opening, score cleanly and rebuild distance.",
                    secondMoves =
                        "Long Guard - Step Back - Lead Check - Cross - Lead Hook - Rear Knee - Exit Right",
                    secondCue =
                        "Recover position before building the final counter attack."
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
            "muaythai_advanced_assessment"

        return chapter(
            id = chapterId,
            title = "Advanced Assessment",
            subtitle =
                "Complete the full Cornerstone Muay Thai Fight Path.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "muaythai_advanced_assessment_1",
                    title = "Combination Architecture Review",
                    subtitle =
                        "Review layered entries, branches and safe exits.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 300,
                    firstMoves =
                        "Jab Feint - Lead Teep - Cross - Rear Body Kick - Reset - Lead Hook - Rear Knee",
                    firstCue =
                        "Maintain structure through every range and transition.",
                    secondMoves =
                        "Lead Low Kick - Cross - Lead Horizontal Elbow - Exit Left - Rear Body Kick",
                    secondCue =
                        "Rebuild kicking distance before finishing the sequence."
                ),
                lesson(
                    id = "muaythai_advanced_assessment_2",
                    title = "Feint-System Review",
                    subtitle =
                        "Review disguises, traps and broken rhythm.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 305,
                    firstMoves =
                        "Lead Teep Feint - Jab - Pause - Cross - Rear Low Kick",
                    firstCue =
                        "Remain balanced during the pause and commit to the real attack.",
                    secondMoves =
                        "Rear Low Kick Feint - Lead Hook - Rear Knee - Lead Horizontal Elbow - Exit Right",
                    secondCue =
                        "Keep the feint compact and recover before exiting."
                ),
                lesson(
                    id = "muaythai_advanced_assessment_3",
                    title = "Advanced Kick Review",
                    subtitle =
                        "Review target selection, angles and kick recovery.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 310,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick - Pivot Left - Lead Teep",
                    firstCue =
                        "Select each target from stable range and finish with distance control.",
                    secondMoves =
                        "Jab Feint - Switch Lead Body Kick - Cross - Rear High Kick - Exit Right",
                    secondCue =
                        "Use a comfortable high-kick range and exit after recovering."
                ),
                lesson(
                    id = "muaythai_advanced_assessment_4",
                    title = "Counter-Offense Review",
                    subtitle =
                        "Review checks and explicit directional defence.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 320,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Recover the check and complete Slip Left before countering.",
                    secondMoves =
                        "Rear Check - Jab - Slip Right - Cross - Roll Left - Lead Hook - Rear Knee",
                    secondCue =
                        "Keep Slip Right and Roll Left compact and clearly directional."
                ),
                lesson(
                    id = "muaythai_advanced_assessment_5",
                    title = "Close-Range Command Review",
                    subtitle =
                        "Review knees, elbows, exits and range rebuilding.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 330,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Knee - Lead Horizontal Elbow - Exit Left",
                    firstCue =
                        "Move through every range without sacrificing guard or posture.",
                    secondMoves =
                        "Long Guard - Lead Knee - Rear Horizontal Elbow - Step Back - Rear Body Kick",
                    secondCue =
                        "Recover from close range before releasing the body kick."
                ),
                lesson(
                    id = "muaythai_advanced_assessment_6",
                    title = "Muay Thai Advanced Final",
                    subtitle =
                        "Complete all 192 structured Muay Thai Fight Path sessions.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 350,
                    firstMoves =
                        "Lead Teep Feint - Jab - Cross - Rear Body Kick - Pivot Left - Lead Horizontal Elbow - Rear Knee - Exit Right",
                    firstCue =
                        "Control every transition and finish in a balanced defensive stance.",
                    secondMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Roll Right - Cross - Rear Low Kick - Step Back - Lead Teep",
                    secondCue =
                        "Keep every defensive direction explicit and finish by rebuilding distance."
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