package com.shubham.cornerstone

/**
 * Boxing Fundamentals Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total sessions
 *
 * Combined with the 24-session Beginner path:
 *
 * 24 Beginner
 * + 42 Fundamentals
 * = 66 validated Fight Path sessions
 *
 * At roughly 3 structured sessions per week,
 * Developing is reached after about 5 months of
 * consistent completed work.
 *
 * Existing prototype lesson IDs are intentionally
 * preserved where possible.
 */
object BoxingFundamentalsCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_BOXING

    val chapters:
            List<TrainingChapterDefinition> =
        listOf(
            hooksAndUppercuts(),
            defendAndReturn(),
            bodyAttacks(),
            anglesAndExits(),
            combinationBuilding(),
            counterpunching(),
            pressureAndAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — HOOKS & UPPERCUTS
    // ---------------------------------------------------------

    private fun hooksAndUppercuts():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_hooks_uppercuts"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Hooks & Uppercuts",
            subtitle =
                "Build compact short-range punches and connect them with your straight attacks.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 1,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_hooks_1",
                    title =
                        "Lead Hook Foundation",
                    subtitle =
                        "Strengthen the lead hook after straight punches.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Jab - Cross - Lead Hook",
                    firstCue =
                        "Recover the cross before rotating into a compact lead hook.",
                    secondMoves =
                        "Double Jab - Cross - Lead Hook",
                    secondCue =
                        "Use the jabs to close distance without falling forward."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_hooks_2",
                    title =
                        "Hook Combinations",
                    subtitle =
                        "Connect hooks with straight punches from both directions.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Jab - Lead Hook - Cross",
                    firstCue =
                        "Keep the lead hook tight and send the cross directly down the middle.",
                    secondMoves =
                        "Cross - Lead Hook - Cross",
                    secondCue =
                        "Recover your balance between every punch."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_hooks_3",
                    title =
                        "Uppercut Introduction",
                    subtitle =
                        "Attack through the middle without dropping your guard.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 175,
                    firstMoves =
                        "Jab - Cross - Lead Uppercut",
                    firstCue =
                        "Use your legs slightly instead of dropping the lead hand.",
                    secondMoves =
                        "Cross - Lead Uppercut - Cross",
                    secondCue =
                        "Stay compact and protect your chin through the uppercut."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_hooks_4",
                    title =
                        "Rear Uppercut",
                    subtitle =
                        "Introduce the rear uppercut inside controlled combinations.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 180,
                    firstMoves =
                        "Jab - Lead Hook - Rear Uppercut",
                    firstCue =
                        "Keep the rear uppercut short and drive through the legs.",
                    secondMoves =
                        "Lead Hook - Rear Uppercut - Lead Hook",
                    secondCue =
                        "Do not square your stance while changing punch angles."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_hooks_5",
                    title =
                        "Short Range Flow",
                    subtitle =
                        "Move naturally between hooks, uppercuts and straights.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 190,
                    firstMoves =
                        "Cross - Lead Hook - Rear Uppercut - Lead Hook",
                    firstCue =
                        "Stay compact and let your hips connect each short punch.",
                    secondMoves =
                        "Jab - Cross - Lead Uppercut - Lead Hook - Cross",
                    secondCue =
                        "Finish every punch before moving into the next angle."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_hooks_6",
                    title =
                        "Short Range Checkpoint",
                    subtitle =
                        "Prove control across straights, hooks and uppercuts.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Uppercut - Lead Hook",
                    firstCue =
                        "Keep the combination compact and return to guard after every shot.",
                    secondMoves =
                        "Cross - Lead Uppercut - Lead Hook - Cross - Step Back",
                    secondCue =
                        "Finish balanced and leave range under control."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — DEFEND & RETURN
    // ---------------------------------------------------------

    private fun defendAndReturn():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_defend_return"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Defend & Return",
            subtitle =
                "Stop defending only to survive. Defend, recover and answer.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 2,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_counter_1",
                    title =
                        "Slip Right & Return",
                    subtitle =
                        "Move your head right and answer immediately.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 180,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross",
                    firstCue =
                        "Move your head clearly to the RIGHT and return balanced.",
                    secondMoves =
                        "Jab - Slip Right - Cross - Lead Hook",
                    secondCue =
                        "Slip RIGHT without leaning outside your stance."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counter_2",
                    title =
                        "Slip Left & Return",
                    subtitle =
                        "Build the opposite defensive direction.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Slip Left - Lead Hook",
                    firstCue =
                        "Move clearly to the LEFT before returning with the hook.",
                    secondMoves =
                        "Cross - Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Slip LEFT while keeping your eyes forward."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counter_3",
                    title =
                        "Roll & Counter",
                    subtitle =
                        "Use directional rolls and return immediately.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Roll Right - Cross",
                    firstCue =
                        "Roll clearly to the RIGHT and rise back into stance.",
                    secondMoves =
                        "Cross - Lead Hook - Roll Left - Lead Hook - Cross",
                    secondCue =
                        "Roll clearly to the LEFT using your legs, not your waist."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counter_4",
                    title =
                        "Guard & Return",
                    subtitle =
                        "Absorb imaginary pressure behind your guard and answer.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - High Guard - Cross - Lead Hook",
                    firstCue =
                        "Close your guard first, rebuild your stance and then counter.",
                    secondMoves =
                        "High Guard - Jab - Cross - Pivot Left",
                    secondCue =
                        "Do not attack until your balance is rebuilt."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counter_5",
                    title =
                        "Step Back Counter",
                    subtitle =
                        "Make the opponent miss with distance and return.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Jab - Cross - Step Back - Cross - Lead Hook",
                    firstCue =
                        "Create enough distance before returning with the cross.",
                    secondMoves =
                        "Lead Hook - Step Back - Jab - Cross",
                    secondCue =
                        "Exit balanced and re-enter behind straight punches."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counter_6",
                    title =
                        "Defend & Return Checkpoint",
                    subtitle =
                        "Mix slips, rolls, guard and distance defence.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Jab - Cross - Slip Right - Cross - Roll Left - Lead Hook",
                    firstCue =
                        "Slip RIGHT first, then roll LEFT clearly before returning.",
                    secondMoves =
                        "High Guard - Step Back - Jab - Cross - Pivot Left",
                    secondCue =
                        "Defend, create space, answer and leave on an angle."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — BODY ATTACKS
    // ---------------------------------------------------------

    private fun bodyAttacks():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_body_attacks"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Body Attacks",
            subtitle =
                "Learn to change levels without exposing your head or losing position.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 3,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_body_1",
                    title =
                        "Jab to Body",
                    subtitle =
                        "Introduce level changes behind the jab.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 185,
                    firstMoves =
                        "Jab to Body - Cross",
                    firstCue =
                        "Bend through your legs slightly instead of folding at the waist.",
                    secondMoves =
                        "Jab - Jab to Body - Cross",
                    secondCue =
                        "Use the head jab to disguise the level change."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_body_2",
                    title =
                        "Cross to Body",
                    subtitle =
                        "Attack the body from your rear hand.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross to Body - Lead Hook",
                    firstCue =
                        "Keep your rear shoulder protecting your chin as you lower the cross.",
                    secondMoves =
                        "Double Jab - Cross to Body - Cross",
                    secondCue =
                        "Change the target without changing your balance."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_body_3",
                    title =
                        "Lead Hook to Body",
                    subtitle =
                        "Add a compact body hook after straight punches.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Lead Hook to Body",
                    firstCue =
                        "Lower through your legs and keep the body hook compact.",
                    secondMoves =
                        "Cross - Lead Hook to Body - Lead Hook",
                    secondCue =
                        "Move smoothly from the body back to the head."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_body_4",
                    title =
                        "Head to Body",
                    subtitle =
                        "Make the opponent defend high before attacking low.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Jab - Cross - Lead Hook to Body - Cross",
                    firstCue =
                        "Use the first two punches to draw the guard upward.",
                    secondMoves =
                        "Double Jab - Cross to Body - Lead Hook",
                    secondCue =
                        "Change levels without allowing your feet to collapse together."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_body_5",
                    title =
                        "Body to Head",
                    subtitle =
                        "Attack low before returning upstairs.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Jab to Body - Cross - Lead Hook",
                    firstCue =
                        "Return upright before finishing the head combination.",
                    secondMoves =
                        "Lead Hook to Body - Lead Hook - Cross",
                    secondCue =
                        "Keep the body and head hooks compact."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_body_6",
                    title =
                        "Body Attack Checkpoint",
                    subtitle =
                        "Mix body and head targets under control.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Jab - Cross to Body - Lead Hook - Cross",
                    firstCue =
                        "Change targets while keeping your guard and stance intact.",
                    secondMoves =
                        "Jab to Body - Cross - Lead Hook to Body - Lead Hook - Cross",
                    secondCue =
                        "Stay balanced through every level change."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — ANGLES & EXITS
    // ---------------------------------------------------------

    private fun anglesAndExits():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_angles_exits"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Angles & Exits",
            subtitle =
                "Stop finishing combinations directly in front of the opponent.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 4,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_angles_1",
                    title =
                        "Pivot After Straights",
                    subtitle =
                        "Change your position after the jab and cross.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 190,
                    firstMoves =
                        "Jab - Cross - Pivot Left",
                    firstCue =
                        "Finish the cross before turning out to the left.",
                    secondMoves =
                        "Double Jab - Cross - Pivot Left - Jab",
                    secondCue =
                        "Change angle first and then re-establish your jab."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_angles_2",
                    title =
                        "Hook & Pivot",
                    subtitle =
                        "Use the lead hook to help create an exit angle.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Pivot Left",
                    firstCue =
                        "Let the lead hook finish before your feet create the new angle.",
                    secondMoves =
                        "Cross - Lead Hook - Pivot Left - Cross",
                    secondCue =
                        "Do not cross your feet during the pivot."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_angles_3",
                    title =
                        "Exit Right",
                    subtitle =
                        "Build a controlled exit to your right.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Jab - Cross - Step Right - Jab",
                    firstCue =
                        "Take a short right-side exit while maintaining stance width.",
                    secondMoves =
                        "Lead Hook - Cross - Step Right - Cross",
                    secondCue =
                        "Finish your punches before leaving the centre line."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_angles_4",
                    title =
                        "L-Step Exit",
                    subtitle =
                        "Add another controlled way to leave pressure.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 215,
                    firstMoves =
                        "Jab - Cross - L-Step - Jab",
                    firstCue =
                        "Use small footwork and rebuild your stance before punching again.",
                    secondMoves =
                        "Lead Hook - Cross - L-Step - Cross",
                    secondCue =
                        "Do not rush the exit or allow your feet to cross."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_angles_5",
                    title =
                        "Angle Re-entry",
                    subtitle =
                        "Exit and then attack again from your new position.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Jab - Cross - Pivot Left - Jab - Cross",
                    firstCue =
                        "Establish the new angle before beginning the second attack.",
                    secondMoves =
                        "Lead Hook - Pivot Left - Cross - Lead Hook",
                    secondCue =
                        "Stay balanced while attacking from the changed position."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_angles_6",
                    title =
                        "Angles Checkpoint",
                    subtitle =
                        "Attack, change position and attack again.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Pivot Left - Cross",
                    firstCue =
                        "Finish the hook, change angle and fire the final cross.",
                    secondMoves =
                        "Jab - Cross - Step Right - Jab - Cross - Step Back",
                    secondCue =
                        "Move intelligently after every offensive sequence."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — COMBINATION BUILDING
    // ---------------------------------------------------------

    private fun combinationBuilding():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_combination_building"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Combination Building",
            subtitle =
                "Build longer combinations without becoming wild or predictable.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 5,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_combos_1",
                    title =
                        "Three Punch Flow",
                    subtitle =
                        "Make basic three-punch combinations smooth.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 195,
                    firstMoves =
                        "Jab - Cross - Lead Hook",
                    firstCue =
                        "Stay relaxed and keep the rhythm controlled.",
                    secondMoves =
                        "Cross - Lead Hook - Cross",
                    secondCue =
                        "Keep every punch mechanically clean."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_combos_2",
                    title =
                        "Four Punch Flow",
                    subtitle =
                        "Extend combinations while maintaining your stance.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Cross",
                    firstCue =
                        "Do not sacrifice balance for speed.",
                    secondMoves =
                        "Double Jab - Cross - Lead Hook",
                    secondCue =
                        "Stay composed as the combination becomes longer."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_combos_3",
                    title =
                        "Rhythm Changes",
                    subtitle =
                        "Stop throwing every punch at exactly the same rhythm.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 215,
                    firstMoves =
                        "Jab - Pause - Cross - Lead Hook",
                    firstCue =
                        "Use the pause to break rhythm without freezing your stance.",
                    secondMoves =
                        "Double Jab - Pause - Cross - Cross",
                    secondCue =
                        "Change timing while remaining technically controlled."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_combos_4",
                    title =
                        "Double Attacks",
                    subtitle =
                        "Repeat weapons without losing structure.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook",
                    firstCue =
                        "Make both jabs purposeful rather than identical arm taps.",
                    secondMoves =
                        "Jab - Cross - Double Lead Hook",
                    secondCue =
                        "Reset your lead side enough to make the second hook compact."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_combos_5",
                    title =
                        "Finish With Defence",
                    subtitle =
                        "Build the habit of protecting yourself after combinations.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Slip Right",
                    firstCue =
                        "Finish the hook and move your head clearly to the RIGHT.",
                    secondMoves =
                        "Cross - Lead Hook - Cross - Roll Left",
                    secondCue =
                        "Finish the cross before rolling clearly to the LEFT."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_combos_6",
                    title =
                        "Combination Checkpoint",
                    subtitle =
                        "Combine length, rhythm, defence and movement.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 270,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Slip Right - Cross",
                    firstCue =
                        "Stay technically clean while switching from offence to defence.",
                    secondMoves =
                        "Jab - Cross - Lead Uppercut - Lead Hook - Roll Left - Cross",
                    secondCue =
                        "Roll clearly to the LEFT and return balanced."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — COUNTERPUNCHING
    // ---------------------------------------------------------

    private fun counterpunching():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_counterpunching"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Counterpunching",
            subtitle =
                "Recognise an imaginary attack, defend and immediately take your turn.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 6,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_counterpunching_1",
                    title =
                        "Parry the Jab",
                    subtitle =
                        "Build a simple counter against the opponent's jab.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 8,
                    xp = 205,
                    firstMoves =
                        "Lead Parry - Jab - Cross",
                    firstCue =
                        "Keep the parry small and return your hand immediately.",
                    secondMoves =
                        "Lead Parry - Cross - Lead Hook",
                    secondCue =
                        "Use the opening without reaching forward."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counterpunching_2",
                    title =
                        "Catch & Return",
                    subtitle =
                        "Use your guard to receive a straight attack and counter.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 8,
                    xp = 210,
                    firstMoves =
                        "Rear Hand Catch - Cross",
                    firstCue =
                        "Make the catch small and fire the cross straight back.",
                    secondMoves =
                        "Rear Hand Catch - Jab - Cross",
                    secondCue =
                        "Stay behind your guard before returning."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counterpunching_3",
                    title =
                        "Slip Counter",
                    subtitle =
                        "Turn directional head movement into immediate offence.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook",
                    firstCue =
                        "Slip clearly to the RIGHT and return from your stance.",
                    secondMoves =
                        "Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Slip clearly to the LEFT before countering."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counterpunching_4",
                    title =
                        "Roll Counter",
                    subtitle =
                        "Use both roll directions before answering.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Roll Right - Cross - Lead Hook",
                    firstCue =
                        "Roll clearly to the RIGHT using your legs.",
                    secondMoves =
                        "Roll Left - Lead Hook - Cross",
                    secondCue =
                        "Roll clearly to the LEFT and return upright before punching."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counterpunching_5",
                    title =
                        "Counter & Exit",
                    subtitle =
                        "Do not remain directly in front after your counter.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Pivot Left",
                    firstCue =
                        "Counter first, then leave on the angle.",
                    secondMoves =
                        "Lead Parry - Jab - Cross - Step Right",
                    secondCue =
                        "Finish your return before taking the exit."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_counterpunching_6",
                    title =
                        "Counterpunching Checkpoint",
                    subtitle =
                        "React to multiple imaginary attacks and return safely.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 280,
                    firstMoves =
                        "Lead Parry - Cross - Slip Left - Lead Hook - Cross",
                    firstCue =
                        "Parry first, then slip clearly to the LEFT during the next defensive beat.",
                    secondMoves =
                        "Roll Right - Cross - Lead Hook - Pivot Left - Cross",
                    secondCue =
                        "Roll clearly to the RIGHT and finish from your new angle."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 7 — PRESSURE & FINAL ASSESSMENT
    // ---------------------------------------------------------

    private fun pressureAndAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_fundamentals_pressure_assessment"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Pressure & Assessment",
            subtitle =
                "Control longer shadowboxing sequences before earning Developing.",
            sport = SPORT,
            level =
                TrainingPathLevel.FUNDAMENTALS,
            orderInLevel = 7,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_fundamentals_pressure_1",
                    title =
                        "Forward Behind the Jab",
                    subtitle =
                        "Apply pressure without walking forward carelessly.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 220,
                    firstMoves =
                        "Step In - Double Jab - Cross - Step Out",
                    firstCue =
                        "Let your jab control the entry instead of rushing forward.",
                    secondMoves =
                        "Jab - Step In - Cross - Lead Hook - Step Back",
                    secondCue =
                        "Maintain stance width while moving through range."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_pressure_2",
                    title =
                        "Pressure Without Squaring",
                    subtitle =
                        "Stay in your boxing stance while moving forward.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 225,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Step Left",
                    firstCue =
                        "Stay balanced instead of allowing your hips to square forward.",
                    secondMoves =
                        "Jab - Cross - Step Right - Cross - Lead Hook",
                    secondCue =
                        "Use controlled feet to keep pressure without chasing."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_pressure_3",
                    title =
                        "Reset After Exchanges",
                    subtitle =
                        "Build the habit of rebuilding stance after longer combinations.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 235,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Cross - Reset - Jab",
                    firstCue =
                        "Take a deliberate reset before beginning the second attack.",
                    secondMoves =
                        "Double Jab - Cross - Lead Hook - Step Back - Reset",
                    secondCue =
                        "Leave range and rebuild your base instead of admiring the combination."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_pressure_4",
                    title =
                        "Pressure Escape",
                    subtitle =
                        "Shadowbox your way out when imaginary pressure closes in.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "High Guard - Jab - Cross - Pivot Left",
                    firstCue =
                        "Defend first, then use punches to create your exit.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Step Right",
                    secondCue =
                        "Slip clearly to the RIGHT before leaving on the opposite angle."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_pressure_5",
                    title =
                        "Fundamentals Review",
                    subtitle =
                        "Bring together body attacks, counters, combinations and angles.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 275,
                    firstMoves =
                        "Jab - Cross to Body - Lead Hook - Slip Right - Cross - Pivot Left",
                    firstCue =
                        "Change levels, slip clearly to the RIGHT and leave on an angle.",
                    secondMoves =
                        "Lead Parry - Cross - Lead Hook to Body - Lead Hook - Step Back",
                    secondCue =
                        "Stay balanced while changing from defence to body and head attacks."
                ),

                lesson(
                    id =
                        "boxing_fundamentals_pressure_6",
                    title =
                        "Fundamentals Final Round",
                    subtitle =
                        "Complete the final integrated session before Developing unlocks.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 12,
                    xp = 350,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Roll Right - Cross - Pivot Left",
                    firstCue =
                        "Roll clearly to the RIGHT, rebuild your stance and finish from the angle.",
                    secondMoves =
                        "Jab to Body - Cross - Slip Left - Lead Hook - Cross - Step Right",
                    secondCue =
                        "Slip clearly to the LEFT and finish the full sequence under control."
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
                TrainingPathLevel.FUNDAMENTALS,
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