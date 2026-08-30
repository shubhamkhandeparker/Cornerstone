package com.shubham.cornerstone

/**
 * Boxing Intermediate Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Intermediate sessions.
 *
 * Progress before entering Intermediate:
 *
 * Beginner      = 24 sessions
 * Fundamentals  = 42 sessions
 * Developing    = 42 sessions
 *
 * Total before Intermediate = 108 sessions.
 *
 * At roughly 3 Fight Path sessions per week:
 * 108 sessions = roughly 36 weeks of structured work.
 *
 * Intermediate should therefore feel very different from
 * Beginner/Fundamentals.
 *
 * Main goals:
 * - rhythm changes
 * - broken rhythm
 * - combination layering
 * - angle creation during exchanges
 * - pocket awareness
 * - counter traps
 * - front-foot / back-foot transitions
 * - tactical decision making
 * - longer integrated rounds
 *
 * Directional defence rule:
 *
 * Never use ambiguous:
 * "Slip"
 * "Roll"
 *
 * Always use:
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object BoxingIntermediateCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_BOXING

    val chapters:
            List<TrainingChapterDefinition> =
        listOf(
            rhythmAndTempo(),
            layeredCombinations(),
            anglesInsideExchanges(),
            pocketBoxing(),
            trapsAndCounters(),
            frontFootBackFoot(),
            intermediateAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — RHYTHM & TEMPO
    // ---------------------------------------------------------

    private fun rhythmAndTempo():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_intermediate_rhythm_tempo"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Rhythm & Tempo",
            subtitle =
                "Stop moving and punching at one predictable speed.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 1,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_rhythm_1",
                    title =
                        "Fast-Slow-Fast",
                    subtitle =
                        "Change speed inside the same attacking sequence.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 420,
                    firstMoves =
                        "Fast Jab - Slow Jab - Fast Cross - Lead Hook",
                    firstCue =
                        "Do not make every punch the same speed. Break the rhythm deliberately.",
                    secondMoves =
                        "Double Jab - Pause - Cross - Lead Hook - Cross",
                    secondCue =
                        "Use the pause without relaxing your stance or dropping your guard."
                ),

                lesson(
                    id =
                        "boxing_intermediate_rhythm_2",
                    title =
                        "Broken Rhythm Jab",
                    subtitle =
                        "Make your jab harder to read by changing timing.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 430,
                    firstMoves =
                        "Jab - Pause - Jab - Cross",
                    firstCue =
                        "The second jab should arrive on a different beat.",
                    secondMoves =
                        "Jab Feint - Pause - Double Jab - Cross",
                    secondCue =
                        "Make the imagined opponent react before suddenly changing tempo."
                ),

                lesson(
                    id =
                        "boxing_intermediate_rhythm_3",
                    title =
                        "Delayed Finish",
                    subtitle =
                        "Interrupt the combination before the final attack.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 440,
                    firstMoves =
                        "Jab - Cross - Pause - Lead Hook - Cross",
                    firstCue =
                        "Stay ready during the pause instead of mentally ending the combination.",
                    secondMoves =
                        "Double Jab - Cross - Pause - Rear Uppercut - Lead Hook",
                    secondCue =
                        "Let the pause create space for the second layer."
                ),

                lesson(
                    id =
                        "boxing_intermediate_rhythm_4",
                    title =
                        "Rhythm Into Defence",
                    subtitle =
                        "Change tempo before transitioning into head movement.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 455,
                    firstMoves =
                        "Jab - Pause - Cross - Slip Right - Cross",
                    firstCue =
                        "Change rhythm first, then slip clearly RIGHT before returning.",
                    secondMoves =
                        "Double Jab - Pause - Lead Hook - Slip Left - Cross",
                    secondCue =
                        "Slip clearly LEFT after completing the hook."
                ),

                lesson(
                    id =
                        "boxing_intermediate_rhythm_5",
                    title =
                        "Rhythm Into Angles",
                    subtitle =
                        "Change timing before changing position.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 470,
                    firstMoves =
                        "Jab Feint - Pause - Cross - Lead Hook - Pivot Left",
                    firstCue =
                        "Make the opponent hesitate before turning the angle.",
                    secondMoves =
                        "Double Jab - Pause - Cross - Step Right - Cross",
                    secondCue =
                        "Do not step away until the first attack is completely finished."
                ),

                lesson(
                    id =
                        "boxing_intermediate_rhythm_6",
                    title =
                        "Rhythm Checkpoint",
                    subtitle =
                        "Control several tempos without becoming mechanically sloppy.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 520,
                    firstMoves =
                        "Jab Feint - Double Jab - Pause - Cross - Slip Right - Lead Hook - Cross",
                    firstCue =
                        "Change the rhythm, slip clearly RIGHT and return without rushing.",
                    secondMoves =
                        "Jab - Cross - Pause - Lead Hook to Body - Lead Hook - Pivot Left",
                    secondCue =
                        "Use tempo, target change and angle as one connected sequence."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — LAYERED COMBINATIONS
    // ---------------------------------------------------------

    private fun layeredCombinations():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_intermediate_layered_combinations"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Layered Combinations",
            subtitle =
                "Build attacks in multiple phases instead of throwing one memorised combination.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 2,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_layers_1",
                    title =
                        "First Attack, Second Attack",
                    subtitle =
                        "Finish one combination, reset briefly and attack again.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 430,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Reset - Double Jab - Cross",
                    firstCue =
                        "The second attack begins from your rebuilt stance.",
                    secondMoves =
                        "Double Jab - Cross - Reset - Lead Hook - Cross",
                    secondCue =
                        "Do not allow the reset to become a complete stop."
                ),

                lesson(
                    id =
                        "boxing_intermediate_layers_2",
                    title =
                        "Defence Between Layers",
                    subtitle =
                        "Expect resistance between your first and second attacks.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 445,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross - Lead Hook",
                    firstCue =
                        "Finish the first attack, slip clearly RIGHT, then begin layer two.",
                    secondMoves =
                        "Double Jab - Cross - Roll Left - Lead Hook - Cross",
                    secondCue =
                        "Roll clearly LEFT before restarting offence."
                ),

                lesson(
                    id =
                        "boxing_intermediate_layers_3",
                    title =
                        "Body Between Layers",
                    subtitle =
                        "Use level changes to connect different phases of attack.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 460,
                    firstMoves =
                        "Jab - Cross - Lead Hook to Body - Lead Hook - Cross",
                    firstCue =
                        "Use the body attack to create the second opening.",
                    secondMoves =
                        "Double Jab - Cross to Body - Reset - Cross - Lead Hook",
                    secondCue =
                        "Return to stance after changing levels."
                ),

                lesson(
                    id =
                        "boxing_intermediate_layers_4",
                    title =
                        "Angle Between Layers",
                    subtitle =
                        "Reposition before beginning the second attack.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 475,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Pivot Left - Jab - Cross",
                    firstCue =
                        "The pivot creates a new line for the second attack.",
                    secondMoves =
                        "Double Jab - Cross - Step Right - Lead Hook - Cross",
                    secondCue =
                        "Move into the new position before throwing again."
                ),

                lesson(
                    id =
                        "boxing_intermediate_layers_5",
                    title =
                        "Three-Phase Attack",
                    subtitle =
                        "Attack, defend and immediately build another attack.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 495,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross - Pivot Left - Lead Hook - Cross",
                    firstCue =
                        "Layer attack, defence and angle without losing your stance.",
                    secondMoves =
                        "Double Jab - Roll Left - Lead Hook - Step Back - Cross - Lead Hook",
                    secondCue =
                        "Roll clearly LEFT, create space and then begin the next layer."
                ),

                lesson(
                    id =
                        "boxing_intermediate_layers_6",
                    title =
                        "Combination Layering Checkpoint",
                    subtitle =
                        "Build long sequences without turning them into uncontrolled punch volume.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 540,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Lead Hook - Pivot Left - Jab - Cross",
                    firstCue =
                        "Every phase must have a purpose: create, attack, defend, reposition, attack.",
                    secondMoves =
                        "Jab to Body - Cross - Roll Left - Lead Hook - Step Right - Cross - Lead Hook",
                    secondCue =
                        "Change level, roll clearly LEFT and continue only after position is rebuilt."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — ANGLES INSIDE EXCHANGES
    // ---------------------------------------------------------

    private fun anglesInsideExchanges():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_intermediate_angles_exchanges"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Angles Inside Exchanges",
            subtitle =
                "Create new attacking lines while the exchange is still happening.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 3,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_angles_1",
                    title =
                        "Punch & Pivot",
                    subtitle =
                        "Use the lead hook to help turn the corner.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 440,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Pivot Left - Cross",
                    firstCue =
                        "Complete the hook before turning and punching from the new line.",
                    secondMoves =
                        "Double Jab - Lead Hook - Pivot Left - Jab - Cross",
                    secondCue =
                        "Keep your stance compact as your feet change direction."
                ),

                lesson(
                    id =
                        "boxing_intermediate_angles_2",
                    title =
                        "Defence Into Angle",
                    subtitle =
                        "Use defensive movement to begin your repositioning.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 455,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Pivot Left",
                    firstCue =
                        "Slip clearly RIGHT, return and turn away from the centre.",
                    secondMoves =
                        "Roll Left - Lead Hook - Cross - Step Right",
                    secondCue =
                        "Roll clearly LEFT and finish in a different position."
                ),

                lesson(
                    id =
                        "boxing_intermediate_angles_3",
                    title =
                        "Step Outside the Line",
                    subtitle =
                        "Move laterally before restarting the exchange.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 470,
                    firstMoves =
                        "Jab - Cross - Step Right - Cross - Lead Hook",
                    firstCue =
                        "Complete your first punches before stepping off the centre line.",
                    secondMoves =
                        "Double Jab - Step Left - Cross - Lead Hook",
                    secondCue =
                        "Use your feet to create a clearer lane for the rear hand."
                ),

                lesson(
                    id =
                        "boxing_intermediate_angles_4",
                    title =
                        "Angle After Body Attack",
                    subtitle =
                        "Use body work before leaving through a new line.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 485,
                    firstMoves =
                        "Jab - Cross to Body - Lead Hook - Pivot Left - Cross",
                    firstCue =
                        "Return upright before using the pivot.",
                    secondMoves =
                        "Lead Hook to Body - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Finish the head hook before changing position."
                ),

                lesson(
                    id =
                        "boxing_intermediate_angles_5",
                    title =
                        "Angle & Re-entry",
                    subtitle =
                        "Leave the line and deliberately attack again.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 505,
                    firstMoves =
                        "Jab - Cross - Pivot Left - Step Out - Step In - Cross - Lead Hook",
                    firstCue =
                        "Do not immediately chase after your pivot. Re-enter under control.",
                    secondMoves =
                        "Slip Right - Cross - Step Right - Jab - Cross - Lead Hook",
                    secondCue =
                        "Slip clearly RIGHT, reposition and establish your new attack."
                ),

                lesson(
                    id =
                        "boxing_intermediate_angles_6",
                    title =
                        "Angle Checkpoint",
                    subtitle =
                        "Create and exploit several new attacking lines.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 14,
                    xp = 555,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Pivot Left - Jab - Slip Right - Cross",
                    firstCue =
                        "Create the opening, change angle, then slip clearly RIGHT during the second exchange.",
                    secondMoves =
                        "Jab to Body - Cross - Roll Left - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Roll clearly LEFT before leaving the centre and finishing from the new position."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — POCKET BOXING
    // ---------------------------------------------------------

    private fun pocketBoxing():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_intermediate_pocket_boxing"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Pocket Boxing",
            subtitle =
                "Stay compact at close range without becoming reckless or square.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 4,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_pocket_1",
                    title =
                        "Compact Hooks",
                    subtitle =
                        "Work short combinations without widening your punches.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 450,
                    firstMoves =
                        "Lead Hook - Cross - Lead Hook",
                    firstCue =
                        "Keep both hooks compact and return each hand to guard.",
                    secondMoves =
                        "Rear Uppercut - Lead Hook - Cross",
                    secondCue =
                        "Do not lift your chin while punching from close range."
                ),

                lesson(
                    id =
                        "boxing_intermediate_pocket_2",
                    title =
                        "Uppercut-Hook Layer",
                    subtitle =
                        "Connect vertical and horizontal short-range punches.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 465,
                    firstMoves =
                        "Lead Uppercut - Cross - Lead Hook",
                    firstCue =
                        "Keep the uppercut short and immediately rebuild your guard.",
                    secondMoves =
                        "Rear Uppercut - Lead Hook - Cross - Lead Hook",
                    secondCue =
                        "Rotate without becoming square."
                ),

                lesson(
                    id =
                        "boxing_intermediate_pocket_3",
                    title =
                        "Pocket Defence",
                    subtitle =
                        "Use directional rolls while remaining ready to punch.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 480,
                    firstMoves =
                        "Lead Hook - Roll Right - Cross - Lead Hook",
                    firstCue =
                        "Roll clearly RIGHT using your legs, not your waist.",
                    secondMoves =
                        "Cross - Roll Left - Lead Hook - Rear Uppercut",
                    secondCue =
                        "Roll clearly LEFT and stay compact before returning."
                ),

                lesson(
                    id =
                        "boxing_intermediate_pocket_4",
                    title =
                        "Body Work in the Pocket",
                    subtitle =
                        "Attack the body without remaining folded forward.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 495,
                    firstMoves =
                        "Lead Hook to Body - Lead Hook - Cross",
                    firstCue =
                        "Use your legs to change level and come back upstairs immediately.",
                    secondMoves =
                        "Cross to Body - Lead Hook - Rear Uppercut - Lead Hook",
                    secondCue =
                        "Stay balanced while alternating levels."
                ),

                lesson(
                    id =
                        "boxing_intermediate_pocket_5",
                    title =
                        "Pocket Exit",
                    subtitle =
                        "Finish the close-range exchange and leave cleanly.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 515,
                    firstMoves =
                        "Rear Uppercut - Lead Hook - Cross - Pivot Left - Step Out",
                    firstCue =
                        "Finish the combination before turning out.",
                    secondMoves =
                        "Lead Hook to Body - Lead Hook - Roll Right - Cross - Step Right",
                    secondCue =
                        "Roll clearly RIGHT, answer once and leave the pocket."
                ),

                lesson(
                    id =
                        "boxing_intermediate_pocket_6",
                    title =
                        "Pocket Checkpoint",
                    subtitle =
                        "Attack, defend and leave close range under control.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 570,
                    firstMoves =
                        "Lead Uppercut - Cross - Lead Hook - Roll Right - Cross - Pivot Left",
                    firstCue =
                        "Stay compact, roll clearly RIGHT and leave from the angle.",
                    secondMoves =
                        "Cross to Body - Lead Hook - Roll Left - Rear Uppercut - Lead Hook - Step Right",
                    secondCue =
                        "Change level, roll clearly LEFT and exit only after rebuilding stance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — TRAPS & COUNTERS
    // ---------------------------------------------------------

    private fun trapsAndCounters():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_intermediate_traps_counters"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Traps & Counters",
            subtitle =
                "Invite predictable reactions and prepare the answer before they happen.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 5,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_traps_1",
                    title =
                        "Invite the Jab",
                    subtitle =
                        "Use distance and positioning to prepare your jab counter.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 460,
                    firstMoves =
                        "Step Back - Lead Parry - Cross - Lead Hook",
                    firstCue =
                        "Create the space, imagine the jab arriving, then parry and answer.",
                    secondMoves =
                        "Jab Feint - Slip Right - Cross - Lead Hook",
                    secondCue =
                        "Use the feint to provoke the imagined response, then slip clearly RIGHT."
                ),

                lesson(
                    id =
                        "boxing_intermediate_traps_2",
                    title =
                        "Invite the Cross",
                    subtitle =
                        "Prepare the defensive answer before the rear hand arrives.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 475,
                    firstMoves =
                        "Jab Feint - Slip Left - Lead Hook - Cross",
                    firstCue =
                        "Imagine drawing the cross, then slip clearly LEFT.",
                    secondMoves =
                        "Step Back - Slip Left - Cross - Lead Hook",
                    secondCue =
                        "Use distance first, then directional head movement."
                ),

                lesson(
                    id =
                        "boxing_intermediate_traps_3",
                    title =
                        "Body Trap",
                    subtitle =
                        "Create a body reaction before returning upstairs.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 490,
                    firstMoves =
                        "Jab to Body - Jab Feint - Cross - Lead Hook",
                    firstCue =
                        "Make the imagined guard react low before returning to the head.",
                    secondMoves =
                        "Body Feint - Cross to Body - Lead Hook - Cross",
                    secondCue =
                        "Layer the body threat rather than attacking the same opening repeatedly."
                ),

                lesson(
                    id =
                        "boxing_intermediate_traps_4",
                    title =
                        "Counter Trap",
                    subtitle =
                        "Expect the opponent to answer your first attack.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 510,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross - Slip Left - Lead Hook",
                    firstCue =
                        "Slip clearly RIGHT after your attack, then clearly LEFT during the next response.",
                    secondMoves =
                        "Lead Hook - Cross - Roll Right - Cross - Lead Hook",
                    secondCue =
                        "Finish your first attack before rolling clearly RIGHT."
                ),

                lesson(
                    id =
                        "boxing_intermediate_traps_5",
                    title =
                        "Trap & Reposition",
                    subtitle =
                        "Use the expected reaction to create your angle.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 530,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook - Pivot Left - Cross",
                    firstCue =
                        "Use the reaction to help hide the angle change.",
                    secondMoves =
                        "Body Feint - Jab - Slip Right - Cross - Step Right - Lead Hook",
                    secondCue =
                        "Slip clearly RIGHT and use the new position for the final hook."
                ),

                lesson(
                    id =
                        "boxing_intermediate_traps_6",
                    title =
                        "Traps & Counters Checkpoint",
                    subtitle =
                        "Create the reaction, predict the answer and punish the pattern.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 585,
                    firstMoves =
                        "Jab Feint - Cross - Slip Left - Lead Hook - Roll Right - Cross - Pivot Left",
                    firstCue =
                        "Create the reaction, slip clearly LEFT, roll clearly RIGHT and finish from an angle.",
                    secondMoves =
                        "Jab to Body - Double Jab - Cross - Slip Right - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Change targets, draw the response, slip clearly RIGHT and reposition."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — FRONT FOOT & BACK FOOT
    // ---------------------------------------------------------

    private fun frontFootBackFoot():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_intermediate_front_back_foot"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Front Foot & Back Foot",
            subtitle =
                "Switch between applying pressure and boxing while giving ground.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 6,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_footwork_1",
                    title =
                        "Front-Foot Entry",
                    subtitle =
                        "Apply pressure without simply walking forward.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 470,
                    firstMoves =
                        "Step In - Double Jab - Cross - Lead Hook",
                    firstCue =
                        "Advance behind punches while maintaining stance width.",
                    secondMoves =
                        "Jab Feint - Step In - Jab - Cross - Lead Hook",
                    secondCue =
                        "Use the feint to make the entry safer."
                ),

                lesson(
                    id =
                        "boxing_intermediate_footwork_2",
                    title =
                        "Back-Foot Counter",
                    subtitle =
                        "Create space without becoming passive.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 12,
                    xp = 485,
                    firstMoves =
                        "Step Back - Cross - Lead Hook - Cross",
                    firstCue =
                        "Make the imagined punch fall short, then return immediately.",
                    secondMoves =
                        "Step Back - Jab - Cross - Pivot Left",
                    secondCue =
                        "Use the jab to regain control before turning out."
                ),

                lesson(
                    id =
                        "boxing_intermediate_footwork_3",
                    title =
                        "Pressure to Retreat",
                    subtitle =
                        "Attack forward, then deliberately transition to the back foot.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 500,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Step Back - Cross",
                    firstCue =
                        "Finish your pressure sequence before creating distance.",
                    secondMoves =
                        "Jab - Cross - Step Back - Jab - Cross",
                    secondCue =
                        "Do not lose your stance as your direction changes."
                ),

                lesson(
                    id =
                        "boxing_intermediate_footwork_4",
                    title =
                        "Back Foot to Pressure",
                    subtitle =
                        "Give ground, counter and immediately reclaim space.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 13,
                    xp = 520,
                    firstMoves =
                        "Step Back - Cross - Step In - Lead Hook - Cross",
                    firstCue =
                        "Counter first, then deliberately reclaim your position.",
                    secondMoves =
                        "Slip Right - Cross - Step In - Double Jab - Lead Hook",
                    secondCue =
                        "Slip clearly RIGHT before turning defence into pressure."
                ),

                lesson(
                    id =
                        "boxing_intermediate_footwork_5",
                    title =
                        "Direction Change",
                    subtitle =
                        "Change between forward, backward and lateral movement.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 540,
                    firstMoves =
                        "Step In - Jab - Cross - Step Back - Step Right - Cross",
                    firstCue =
                        "Each direction change should happen from a balanced stance.",
                    secondMoves =
                        "Double Jab - Pivot Left - Step Out - Step In - Cross - Lead Hook",
                    secondCue =
                        "Control every transition rather than rushing between positions."
                ),

                lesson(
                    id =
                        "boxing_intermediate_footwork_6",
                    title =
                        "Front & Back Foot Checkpoint",
                    subtitle =
                        "Control the direction of the exchange instead of being dragged by it.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 600,
                    firstMoves =
                        "Jab Feint - Step In - Double Jab - Cross - Step Back - Slip Right - Cross",
                    firstCue =
                        "Pressure first, create space, then slip clearly RIGHT before countering.",
                    secondMoves =
                        "Step Back - Jab - Cross - Pivot Left - Step In - Lead Hook - Cross",
                    secondCue =
                        "Move backward, change angle and then reclaim space deliberately."
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
            "boxing_intermediate_final_assessment"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Intermediate Assessment",
            subtitle =
                "Combine timing, layered attacks, pocket work, counters and movement.",
            sport = SPORT,
            level =
                TrainingPathLevel.INTERMEDIATE,
            orderInLevel = 7,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_intermediate_assessment_1",
                    title =
                        "Rhythm Review",
                    subtitle =
                        "Prove that your timing is no longer predictable.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 12,
                    xp = 480,
                    firstMoves =
                        "Jab Feint - Pause - Double Jab - Cross - Lead Hook",
                    firstCue =
                        "Change the rhythm without losing your stance.",
                    secondMoves =
                        "Jab - Cross - Pause - Slip Right - Cross - Lead Hook",
                    secondCue =
                        "Pause under control, then slip clearly RIGHT."
                ),

                lesson(
                    id =
                        "boxing_intermediate_assessment_2",
                    title =
                        "Layering Review",
                    subtitle =
                        "Connect multiple phases of offence and defence.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 13,
                    xp = 500,
                    firstMoves =
                        "Jab - Cross - Roll Left - Lead Hook - Pivot Left - Jab - Cross",
                    firstCue =
                        "Roll clearly LEFT between attacks and rebuild from the new angle.",
                    secondMoves =
                        "Jab to Body - Cross - Slip Right - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Change levels, slip clearly RIGHT and reposition before finishing."
                ),

                lesson(
                    id =
                        "boxing_intermediate_assessment_3",
                    title =
                        "Pocket Review",
                    subtitle =
                        "Stay composed during close-range exchanges.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 13,
                    xp = 520,
                    firstMoves =
                        "Lead Uppercut - Cross - Lead Hook - Roll Right - Cross",
                    firstCue =
                        "Stay compact and roll clearly RIGHT before the final counter.",
                    secondMoves =
                        "Cross to Body - Lead Hook - Roll Left - Rear Uppercut - Lead Hook",
                    secondCue =
                        "Change levels and roll clearly LEFT without standing square."
                ),

                lesson(
                    id =
                        "boxing_intermediate_assessment_4",
                    title =
                        "Counter Trap Review",
                    subtitle =
                        "Create and punish predictable reactions.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 14,
                    xp = 545,
                    firstMoves =
                        "Jab Feint - Cross - Slip Left - Lead Hook - Cross - Pivot Left",
                    firstCue =
                        "Create the response, slip clearly LEFT and finish from the angle.",
                    secondMoves =
                        "Body Feint - Jab - Slip Right - Cross - Roll Left - Lead Hook",
                    secondCue =
                        "Slip clearly RIGHT, then roll clearly LEFT as the exchange develops."
                ),

                lesson(
                    id =
                        "boxing_intermediate_assessment_5",
                    title =
                        "Integrated Intermediate Round",
                    subtitle =
                        "Move naturally between offence, defence, pressure and positioning.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 15,
                    xp = 580,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Lead Hook - Pivot Left - Cross",
                    firstCue =
                        "Create, attack, slip clearly RIGHT, turn the angle and finish.",
                    secondMoves =
                        "Step Back - Cross - Lead Hook to Body - Roll Right - Cross - Step Right - Jab",
                    secondCue =
                        "Counter from the back foot, attack the body, roll clearly RIGHT and reposition."
                ),

                lesson(
                    id =
                        "boxing_intermediate_assessment_6",
                    title =
                        "Intermediate Final Round",
                    subtitle =
                        "Complete the Intermediate stage before entering Advanced.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 16,
                    xp = 650,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Pause - Slip Right - Lead Hook - Roll Left - Cross - Pivot Left",
                    firstCue =
                        "Control rhythm, slip clearly RIGHT, roll clearly LEFT and leave through the angle.",
                    secondMoves =
                        "Jab to Body - Cross - Step Back - Cross - Slip Left - Lead Hook - Step Right - Jab - Cross",
                    secondCue =
                        "Change levels, change distance, slip clearly LEFT, reposition and finish balanced."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // HELPER
    // ---------------------------------------------------------

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