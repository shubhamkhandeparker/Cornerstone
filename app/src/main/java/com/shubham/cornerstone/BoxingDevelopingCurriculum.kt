package com.shubham.cornerstone

/**
 * Boxing Developing Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Developing sessions
 *
 * Previous work:
 *
 * 24 Beginner
 * + 42 Fundamentals
 * = 66 sessions before Developing
 *
 * Developing adds another 42 sessions.
 *
 * Total after completing Developing:
 *
 * 108 validated Boxing Fight Path sessions.
 *
 * At roughly 3 structured sessions per week:
 *
 * Beginner + Fundamentals:
 * about 22 weeks / roughly 5–6 months
 *
 * Developing:
 * another 14 weeks
 *
 * The fighter should now be learning to:
 * - control distance instead of only throwing combinations
 * - use feints
 * - create reactions
 * - chain defensive movements
 * - counter after multiple defensive beats
 * - attack body/head with purpose
 * - change rhythm
 * - exit intelligently
 * - reset after exchanges
 *
 * Directional defence rule remains strict:
 * Slip Left / Slip Right
 * Roll Left / Roll Right
 *
 * Never use ambiguous standalone "Slip" or "Roll".
 */
object BoxingDevelopingCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_BOXING

    val chapters:
            List<TrainingChapterDefinition> =
        listOf(
            distanceAndRange(),
            feintsAndReactions(),
            defensiveChains(),
            layeredCounterpunching(),
            bodyHeadSetups(),
            pressureRingCraft(),
            developingAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — DISTANCE & RANGE
    // ---------------------------------------------------------

    private fun distanceAndRange():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_developing_distance_range"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Distance & Range",
            subtitle =
                "Learn to enter, attack and leave without standing at the same distance.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 1,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_distance_1",
                    title =
                        "Long Range Control",
                    subtitle =
                        "Use the jab and feet to control the outside.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 260,
                    firstMoves =
                        "Step In - Jab - Step Out - Jab",
                    firstCue =
                        "Enter behind the jab and leave before becoming stationary.",
                    secondMoves =
                        "Double Jab - Cross - Step Back - Jab",
                    secondCue =
                        "Re-establish distance with the jab after creating space."
                ),

                lesson(
                    id =
                        "boxing_developing_distance_2",
                    title =
                        "Cross the Gap",
                    subtitle =
                        "Close distance without rushing into range.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 270,
                    firstMoves =
                        "Double Jab - Step In - Cross - Lead Hook",
                    firstCue =
                        "Let the jab carry you into range instead of jumping forward.",
                    secondMoves =
                        "Jab - Step In - Jab - Cross - Pivot Left",
                    secondCue =
                        "Build the entry in stages, then leave on the angle."
                ),

                lesson(
                    id =
                        "boxing_developing_distance_3",
                    title =
                        "Outside to Mid Range",
                    subtitle =
                        "Move smoothly from long punches into combination range.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 280,
                    firstMoves =
                        "Jab - Cross - Step In - Lead Hook - Cross",
                    firstCue =
                        "Do not smother the hook when closing the distance.",
                    secondMoves =
                        "Double Jab - Cross - Lead Uppercut - Lead Hook",
                    secondCue =
                        "Use your feet to arrive at the correct range before short punches."
                ),

                lesson(
                    id =
                        "boxing_developing_distance_4",
                    title =
                        "Attack & Disappear",
                    subtitle =
                        "Finish combinations by removing yourself from the centre.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 290,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Pivot Left - Step Out",
                    firstCue =
                        "Finish the hook first, then change position and create distance.",
                    secondMoves =
                        "Double Jab - Cross - Step Right - Step Back",
                    secondCue =
                        "Do not remain directly in front after finishing."
                ),

                lesson(
                    id =
                        "boxing_developing_distance_5",
                    title =
                        "Range Re-entry",
                    subtitle =
                        "Leave range and deliberately enter again.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 300,
                    firstMoves =
                        "Jab - Cross - Step Back - Step In - Jab - Cross",
                    firstCue =
                        "Make the second entry deliberate rather than immediately rushing back.",
                    secondMoves =
                        "Lead Hook - Cross - Pivot Left - Jab - Cross",
                    secondCue =
                        "Re-enter only after establishing your new position."
                ),

                lesson(
                    id =
                        "boxing_developing_distance_6",
                    title =
                        "Distance Checkpoint",
                    subtitle =
                        "Control several ranges during one continuous sequence.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 350,
                    firstMoves =
                        "Step In - Double Jab - Cross - Lead Hook - Pivot Left - Step Out",
                    firstCue =
                        "Enter safely, attack, change angle and leave under control.",
                    secondMoves =
                        "Jab - Cross - Step Back - Jab - Step In - Cross - Lead Hook",
                    secondCue =
                        "Control both the exit and the return instead of chasing."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — FEINTS & REACTIONS
    // ---------------------------------------------------------

    private fun feintsAndReactions():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_developing_feints_reactions"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Feints & Reactions",
            subtitle =
                "Stop attacking every opening directly. Create the reaction first.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 2,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_feints_1",
                    title =
                        "Jab Feint",
                    subtitle =
                        "Use a believable jab feint before your real attack.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 275,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook",
                    firstCue =
                        "Make the feint small but believable before committing to the cross.",
                    secondMoves =
                        "Jab Feint - Jab - Cross",
                    secondCue =
                        "Do not exaggerate the feint or lose your stance."
                ),

                lesson(
                    id =
                        "boxing_developing_feints_2",
                    title =
                        "Level Feint",
                    subtitle =
                        "Threaten the body before attacking the head.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 285,
                    firstMoves =
                        "Body Feint - Jab - Cross",
                    firstCue =
                        "Change level slightly without folding your posture.",
                    secondMoves =
                        "Body Feint - Lead Hook - Cross",
                    secondCue =
                        "Sell the low threat, then return upstairs with balance."
                ),

                lesson(
                    id =
                        "boxing_developing_feints_3",
                    title =
                        "Feint to Body",
                    subtitle =
                        "Show the head attack before changing targets.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 295,
                    firstMoves =
                        "Jab Feint - Cross to Body - Lead Hook",
                    firstCue =
                        "Use the feint to lift the imagined guard before changing level.",
                    secondMoves =
                        "Jab - Cross Feint - Lead Hook to Body - Lead Hook",
                    secondCue =
                        "Stay compact while moving from the body back to the head."
                ),

                lesson(
                    id =
                        "boxing_developing_feints_4",
                    title =
                        "Feint & Angle",
                    subtitle =
                        "Use a fake attack to help create a new position.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 305,
                    firstMoves =
                        "Jab Feint - Step Left - Jab - Cross",
                    firstCue =
                        "Create the reaction before moving to the new line.",
                    secondMoves =
                        "Cross Feint - Pivot Left - Lead Hook - Cross",
                    secondCue =
                        "Do not move your feet before the feint has meaning."
                ),

                lesson(
                    id =
                        "boxing_developing_feints_5",
                    title =
                        "Double Reaction",
                    subtitle =
                        "Use one threat to create a second opening.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 315,
                    firstMoves =
                        "Jab Feint - Jab to Body - Cross - Lead Hook",
                    firstCue =
                        "Layer the feint and level change without rushing.",
                    secondMoves =
                        "Body Feint - Jab - Lead Hook - Cross",
                    secondCue =
                        "Change the imagined opponent's attention before attacking."
                ),

                lesson(
                    id =
                        "boxing_developing_feints_6",
                    title =
                        "Feints Checkpoint",
                    subtitle =
                        "Create openings instead of waiting for them.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 365,
                    firstMoves =
                        "Jab Feint - Cross to Body - Lead Hook - Pivot Left - Cross",
                    firstCue =
                        "Sell the first threat, change target and finish from a new angle.",
                    secondMoves =
                        "Body Feint - Double Jab - Cross - Slip Right - Cross",
                    secondCue =
                        "Use the feint first, then slip clearly to the RIGHT before returning."
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
            "boxing_developing_defensive_chains"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Defensive Chains",
            subtitle =
                "Handle more than one imaginary attack before immediately firing back.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 3,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_defence_1",
                    title =
                        "Slip Right to Roll Left",
                    subtitle =
                        "Connect two defensive directions smoothly.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 290,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Hook - Cross",
                    firstCue =
                        "Slip clearly RIGHT, then roll clearly LEFT using your legs.",
                    secondMoves =
                        "Jab - Cross - Slip Right - Roll Left - Cross",
                    secondCue =
                        "Do not stand tall between the two defensive movements."
                ),

                lesson(
                    id =
                        "boxing_developing_defence_2",
                    title =
                        "Slip Left to Roll Right",
                    subtitle =
                        "Build the opposite defensive chain.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 300,
                    firstMoves =
                        "Slip Left - Roll Right - Cross - Lead Hook",
                    firstCue =
                        "Slip clearly LEFT before rolling clearly RIGHT.",
                    secondMoves =
                        "Cross - Slip Left - Roll Right - Cross",
                    secondCue =
                        "Keep your eyes forward throughout both movements."
                ),

                lesson(
                    id =
                        "boxing_developing_defence_3",
                    title =
                        "Guard to Slip",
                    subtitle =
                        "Move from a closed guard into active head movement.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 310,
                    firstMoves =
                        "High Guard - Slip Right - Cross - Lead Hook",
                    firstCue =
                        "Rebuild your stance behind the guard before slipping RIGHT.",
                    secondMoves =
                        "High Guard - Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Slip clearly LEFT without opening your guard too early."
                ),

                lesson(
                    id =
                        "boxing_developing_defence_4",
                    title =
                        "Parry to Head Movement",
                    subtitle =
                        "Layer hand defence with directional movement.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 320,
                    firstMoves =
                        "Lead Parry - Slip Right - Cross - Lead Hook",
                    firstCue =
                        "Keep the parry compact, then slip clearly RIGHT.",
                    secondMoves =
                        "Rear Hand Catch - Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Catch first, then move clearly LEFT before countering."
                ),

                lesson(
                    id =
                        "boxing_developing_defence_5",
                    title =
                        "Defend & Exit",
                    subtitle =
                        "Survive the exchange and leave the danger line.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 330,
                    firstMoves =
                        "Slip Right - Roll Left - Lead Hook - Pivot Left",
                    firstCue =
                        "Defend twice, answer once and leave on the angle.",
                    secondMoves =
                        "High Guard - Slip Left - Cross - Step Right",
                    secondCue =
                        "Do not remain directly in front after the return."
                ),

                lesson(
                    id =
                        "boxing_developing_defence_6",
                    title =
                        "Defensive Chain Checkpoint",
                    subtitle =
                        "Stay composed through longer defensive sequences.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 385,
                    firstMoves =
                        "Lead Parry - Slip Right - Roll Left - Lead Hook - Cross - Pivot Left",
                    firstCue =
                        "Parry, slip RIGHT, roll LEFT and only then begin your return.",
                    secondMoves =
                        "High Guard - Slip Left - Roll Right - Cross - Lead Hook - Step Back",
                    secondCue =
                        "Move clearly LEFT, roll clearly RIGHT and finish balanced."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — LAYERED COUNTERPUNCHING
    // ---------------------------------------------------------

    private fun layeredCounterpunching():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_developing_layered_counters"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Layered Counterpunching",
            subtitle =
                "Counter the first attack and remain ready for the opponent's next reaction.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 4,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_counters_1",
                    title =
                        "Counter the Jab",
                    subtitle =
                        "Build several answers to the imagined jab.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 300,
                    firstMoves =
                        "Lead Parry - Cross - Lead Hook",
                    firstCue =
                        "Keep the parry short and take the opening immediately.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Cross",
                    secondCue =
                        "Slip clearly RIGHT before firing the counter."
                ),

                lesson(
                    id =
                        "boxing_developing_counters_2",
                    title =
                        "Counter the Cross",
                    subtitle =
                        "Build answers after avoiding the rear straight.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 310,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross",
                    firstCue =
                        "Slip clearly LEFT and return from your stance.",
                    secondMoves =
                        "Step Back - Cross - Lead Hook - Pivot Left",
                    secondCue =
                        "Make the punch miss with distance, then answer and leave."
                ),

                lesson(
                    id =
                        "boxing_developing_counters_3",
                    title =
                        "Counter the Hook",
                    subtitle =
                        "Use directional rolls before answering.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 320,
                    firstMoves =
                        "Roll Right - Cross - Lead Hook - Cross",
                    firstCue =
                        "Roll clearly RIGHT and rise into your counter.",
                    secondMoves =
                        "Roll Left - Lead Hook - Cross - Pivot Left",
                    secondCue =
                        "Roll clearly LEFT before returning and changing angle."
                ),

                lesson(
                    id =
                        "boxing_developing_counters_4",
                    title =
                        "Counter the Counter",
                    subtitle =
                        "Expect another attack after your first return.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 335,
                    firstMoves =
                        "Slip Right - Cross - Slip Left - Lead Hook - Cross",
                    firstCue =
                        "Slip RIGHT, counter, then slip LEFT before continuing.",
                    secondMoves =
                        "Lead Parry - Cross - Roll Right - Cross - Lead Hook",
                    secondCue =
                        "Do not assume the exchange ends after your first counter."
                ),

                lesson(
                    id =
                        "boxing_developing_counters_5",
                    title =
                        "Counter & Reposition",
                    subtitle =
                        "Turn defensive success into better positioning.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 345,
                    firstMoves =
                        "Slip Left - Lead Hook - Cross - Pivot Left - Jab",
                    firstCue =
                        "Slip clearly LEFT, counter and establish the new angle with your jab.",
                    secondMoves =
                        "Roll Right - Cross - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Roll clearly RIGHT and change position after answering."
                ),

                lesson(
                    id =
                        "boxing_developing_counters_6",
                    title =
                        "Counterpunching Checkpoint",
                    subtitle =
                        "Defend, counter, defend again and reposition.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 400,
                    firstMoves =
                        "Lead Parry - Cross - Slip Left - Lead Hook - Roll Right - Cross",
                    firstCue =
                        "Parry, counter, slip clearly LEFT, then roll clearly RIGHT.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Step Back - Cross - Pivot Left",
                    secondCue =
                        "Slip RIGHT, counter, create distance and finish from a new angle."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — BODY & HEAD SETUPS
    // ---------------------------------------------------------

    private fun bodyHeadSetups():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_developing_body_head_setups"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Body & Head Setups",
            subtitle =
                "Use target changes to create openings instead of throwing randomly.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 5,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_body_1",
                    title =
                        "Body Jab Setup",
                    subtitle =
                        "Use the body jab to prepare the next attack.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 300,
                    firstMoves =
                        "Jab to Body - Jab - Cross",
                    firstCue =
                        "Return upright after the body jab before attacking the head.",
                    secondMoves =
                        "Jab to Body - Cross - Lead Hook",
                    secondCue =
                        "Change levels without allowing your stance to collapse."
                ),

                lesson(
                    id =
                        "boxing_developing_body_2",
                    title =
                        "Body Cross Setup",
                    subtitle =
                        "Use the rear hand downstairs before finishing upstairs.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 310,
                    firstMoves =
                        "Jab - Cross to Body - Lead Hook - Cross",
                    firstCue =
                        "Bring your posture back up before the hook.",
                    secondMoves =
                        "Double Jab - Cross to Body - Lead Hook",
                    secondCue =
                        "Hide the level change behind the double jab."
                ),

                lesson(
                    id =
                        "boxing_developing_body_3",
                    title =
                        "Body Hook Setup",
                    subtitle =
                        "Use the lead body hook to open the head.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 320,
                    firstMoves =
                        "Jab - Cross - Lead Hook to Body - Lead Hook",
                    firstCue =
                        "Use your legs for the body hook and return upstairs compactly.",
                    secondMoves =
                        "Cross - Lead Hook to Body - Cross",
                    secondCue =
                        "Do not stay folded after the body attack."
                ),

                lesson(
                    id =
                        "boxing_developing_body_4",
                    title =
                        "Head-Body-Head",
                    subtitle =
                        "Move through three target layers without losing form.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 335,
                    firstMoves =
                        "Jab - Cross - Lead Hook to Body - Lead Hook - Cross",
                    firstCue =
                        "Keep each target change deliberate and mechanically clean.",
                    secondMoves =
                        "Lead Hook - Cross to Body - Lead Hook - Cross",
                    secondCue =
                        "Use your legs for every level change."
                ),

                lesson(
                    id =
                        "boxing_developing_body_5",
                    title =
                        "Body Attack & Exit",
                    subtitle =
                        "Do not remain in the pocket after attacking downstairs.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 345,
                    firstMoves =
                        "Jab - Cross to Body - Lead Hook - Pivot Left",
                    firstCue =
                        "Attack low, return high and leave on the angle.",
                    secondMoves =
                        "Jab to Body - Cross - Lead Hook - Step Right",
                    secondCue =
                        "Finish your head attack before exiting to the right."
                ),

                lesson(
                    id =
                        "boxing_developing_body_6",
                    title =
                        "Body & Head Checkpoint",
                    subtitle =
                        "Use body attacks as tactical setups inside longer sequences.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 405,
                    firstMoves =
                        "Jab Feint - Cross to Body - Lead Hook - Cross - Pivot Left",
                    firstCue =
                        "Create the reaction, attack the body and finish from an angle.",
                    secondMoves =
                        "Jab to Body - Cross - Lead Hook to Body - Lead Hook - Slip Right - Cross",
                    secondCue =
                        "Change targets, then slip clearly RIGHT before the final cross."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — PRESSURE & RING CRAFT
    // ---------------------------------------------------------

    private fun pressureRingCraft():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_developing_pressure_ringcraft"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Pressure & Ring Craft",
            subtitle =
                "Apply pressure without chasing and escape pressure without panicking.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 6,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_ringcraft_1",
                    title =
                        "Cut the Exit",
                    subtitle =
                        "Move with the imagined opponent instead of following directly.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 310,
                    firstMoves =
                        "Step Left - Jab - Cross - Lead Hook",
                    firstCue =
                        "Take the angle first instead of simply walking forward.",
                    secondMoves =
                        "Step Right - Double Jab - Cross",
                    secondCue =
                        "Use your feet to control where the exchange happens."
                ),

                lesson(
                    id =
                        "boxing_developing_ringcraft_2",
                    title =
                        "Pressure Behind Straights",
                    subtitle =
                        "Use straight punches to advance safely.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 320,
                    firstMoves =
                        "Step In - Double Jab - Cross - Step Left",
                    firstCue =
                        "Advance behind punches and finish by changing position.",
                    secondMoves =
                        "Jab - Step In - Cross - Lead Hook - Step Right",
                    secondCue =
                        "Pressure with stance, not by leaning forward."
                ),

                lesson(
                    id =
                        "boxing_developing_ringcraft_3",
                    title =
                        "Pressure With Feints",
                    subtitle =
                        "Use threats to move the imagined opponent before attacking.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 11,
                    xp = 335,
                    firstMoves =
                        "Jab Feint - Step Left - Jab - Cross - Lead Hook",
                    firstCue =
                        "Feint before stepping into the new attacking line.",
                    secondMoves =
                        "Body Feint - Step Right - Cross - Lead Hook",
                    secondCue =
                        "Move after creating the reaction, not before."
                ),

                lesson(
                    id =
                        "boxing_developing_ringcraft_4",
                    title =
                        "Escape the Ropes",
                    subtitle =
                        "Use defence and angles to leave imaginary pressure.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 345,
                    firstMoves =
                        "High Guard - Jab - Cross - Pivot Left - Step Out",
                    firstCue =
                        "Defend first, create space with punches and leave on the angle.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Step Right - Jab",
                    secondCue =
                        "Slip clearly RIGHT before creating your escape."
                ),

                lesson(
                    id =
                        "boxing_developing_ringcraft_5",
                    title =
                        "Pressure Reset",
                    subtitle =
                        "Know when to stop attacking and rebuild position.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 12,
                    xp = 360,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Reset - Jab",
                    firstCue =
                        "Do not force the next attack when your position is gone.",
                    secondMoves =
                        "Jab - Cross - Pivot Left - Reset - Double Jab",
                    secondCue =
                        "Rebuild your stance before beginning the second sequence."
                ),

                lesson(
                    id =
                        "boxing_developing_ringcraft_6",
                    title =
                        "Ring Craft Checkpoint",
                    subtitle =
                        "Pressure, reposition, escape and reset within one session.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 420,
                    firstMoves =
                        "Jab Feint - Step Left - Double Jab - Cross - Lead Hook - Pivot Left",
                    firstCue =
                        "Create the reaction, control the line and leave from the angle.",
                    secondMoves =
                        "High Guard - Slip Right - Cross - Lead Hook - Step Right - Reset - Jab",
                    secondCue =
                        "Defend, slip clearly RIGHT, escape and deliberately reset."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 7 — DEVELOPING FINAL ASSESSMENT
    // ---------------------------------------------------------

    private fun developingAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_developing_final_assessment"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Developing Assessment",
            subtitle =
                "Bring distance, defence, counters, feints and ring craft together.",
            sport = SPORT,
            level =
                TrainingPathLevel.DEVELOPING,
            orderInLevel = 7,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_developing_assessment_1",
                    title =
                        "Range Review",
                    subtitle =
                        "Prove you can control entries and exits.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 11,
                    xp = 340,
                    firstMoves =
                        "Step In - Double Jab - Cross - Lead Hook - Pivot Left - Step Out",
                    firstCue =
                        "Control every phase: entry, attack, angle and exit.",
                    secondMoves =
                        "Jab - Cross - Step Back - Step In - Cross - Lead Hook",
                    secondCue =
                        "Do not rush the second entry."
                ),

                lesson(
                    id =
                        "boxing_developing_assessment_2",
                    title =
                        "Reaction Review",
                    subtitle =
                        "Use feints and target changes to create openings.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 11,
                    xp = 350,
                    firstMoves =
                        "Jab Feint - Cross to Body - Lead Hook - Cross",
                    firstCue =
                        "Create the reaction before changing level.",
                    secondMoves =
                        "Body Feint - Jab - Cross - Pivot Left - Jab",
                    secondCue =
                        "Use the fake before moving into the second attack."
                ),

                lesson(
                    id =
                        "boxing_developing_assessment_3",
                    title =
                        "Defence Review",
                    subtitle =
                        "Chain multiple defensive responses without losing stance.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 12,
                    xp = 365,
                    firstMoves =
                        "Lead Parry - Slip Right - Roll Left - Cross - Lead Hook",
                    firstCue =
                        "Parry, slip clearly RIGHT and roll clearly LEFT before returning.",
                    secondMoves =
                        "High Guard - Slip Left - Roll Right - Lead Hook - Cross",
                    secondCue =
                        "Defend behind the guard, slip LEFT and roll RIGHT."
                ),

                lesson(
                    id =
                        "boxing_developing_assessment_4",
                    title =
                        "Counter Review",
                    subtitle =
                        "Counter without assuming the exchange is finished.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 12,
                    xp = 380,
                    firstMoves =
                        "Slip Right - Cross - Slip Left - Lead Hook - Cross - Pivot Left",
                    firstCue =
                        "Slip RIGHT, counter, slip LEFT and finish from the angle.",
                    secondMoves =
                        "Roll Left - Lead Hook - Cross - Step Back - Cross",
                    secondCue =
                        "Roll clearly LEFT, answer and create distance before the final return."
                ),

                lesson(
                    id =
                        "boxing_developing_assessment_5",
                    title =
                        "Integrated Developing Round",
                    subtitle =
                        "Connect tactical offence, defence and movement.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 13,
                    xp = 410,
                    firstMoves =
                        "Jab Feint - Cross - Lead Hook to Body - Slip Right - Cross - Pivot Left",
                    firstCue =
                        "Create the opening, change target, slip RIGHT and leave on the angle.",
                    secondMoves =
                        "Jab to Body - Cross - Roll Left - Lead Hook - Cross - Step Right",
                    secondCue =
                        "Change level, roll clearly LEFT and reposition after the return."
                ),

                lesson(
                    id =
                        "boxing_developing_assessment_6",
                    title =
                        "Developing Final Round",
                    subtitle =
                        "Complete the final Developing session before Intermediate.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 15,
                    xp = 500,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Roll Left - Lead Hook - Pivot Left",
                    firstCue =
                        "Use the feint, attack, slip clearly RIGHT, roll clearly LEFT and finish from the angle.",
                    secondMoves =
                        "Jab to Body - Cross - Lead Hook - Slip Left - Cross - Step Back - Step In - Jab - Cross",
                    secondCue =
                        "Change targets, slip clearly LEFT, control distance and rebuild the final entry."
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
                TrainingPathLevel.DEVELOPING,
            chapterId =
                chapterId,
            orderInChapter =
                order,
            combos = listOf(
                TrainingLessonCombo(
                    moves =
                        firstMoves,
                    cue =
                        firstCue
                ),
                TrainingLessonCombo(
                    moves =
                        secondMoves,
                    cue =
                        secondCue
                )
            ),
            requiredActiveSeconds =
                minutes * 60,
            xpReward =
                xp
        )
    }
}