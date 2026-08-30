package com.shubham.cornerstone

/**
 * Boxing Advanced Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Advanced sessions.
 *
 * Fighter reaches Advanced after:
 *
 * Beginner      = 24 sessions
 * Fundamentals  = 42 sessions
 * Developing    = 42 sessions
 * Intermediate  = 42 sessions
 *
 * Total before Advanced = 150 validated sessions.
 *
 * Advanced adds another 42 sessions.
 *
 * Full Boxing Fight Path after this stage:
 *
 * 192 structured sessions.
 *
 * At approximately 3 Fight Path sessions per week:
 * roughly 64 weeks of structured progression.
 *
 * Advanced is not simply "longer combinations".
 *
 * The focus becomes:
 * - controlling initiative
 * - manipulating reactions
 * - false entries
 * - layered counter traps
 * - defensive recovery
 * - range transitions
 * - pressure management
 * - tactical resets
 * - ring generalship
 * - maintaining technique while fatigued
 *
 * Directional defence rule remains strict:
 *
 * Never:
 * Slip
 * Roll
 *
 * Always:
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object BoxingAdvancedCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_BOXING

    val chapters:
            List<TrainingChapterDefinition> =
        listOf(
            controlTheInitiative(),
            falseEntriesAndReactions(),
            advancedDefensiveTransitions(),
            rangeTransitions(),
            ringGeneralship(),
            championshipRounds(),
            advancedAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — CONTROL THE INITIATIVE
    // ---------------------------------------------------------

    private fun controlTheInitiative():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_advanced_initiative"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Control the Initiative",
            subtitle =
                "Decide when exchanges begin, pause and restart.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 1,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_initiative_1",
                    title =
                        "Own the First Beat",
                    subtitle =
                        "Take control before the imagined opponent settles.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 13,
                    xp = 600,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Pivot Left",
                    firstCue =
                        "Create hesitation first, then establish the exchange on your timing.",
                    secondMoves =
                        "Step In - Jab - Cross - Step Right - Jab",
                    secondCue =
                        "Take space behind straight punches and finish from a safer line."
                ),

                lesson(
                    id =
                        "boxing_advanced_initiative_2",
                    title =
                        "Take, Pause, Retake",
                    subtitle =
                        "Control the exchange without attacking continuously.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 13,
                    xp = 615,
                    firstMoves =
                        "Double Jab - Cross - Pause - Jab - Lead Hook - Cross",
                    firstCue =
                        "Stay dangerous during the pause rather than mentally switching off.",
                    secondMoves =
                        "Jab - Lead Hook - Step Back - Pause - Cross - Lead Hook",
                    secondCue =
                        "Create distance, wait for the imagined reaction and reclaim initiative."
                ),

                lesson(
                    id =
                        "boxing_advanced_initiative_3",
                    title =
                        "Interrupt the Exchange",
                    subtitle =
                        "Break the opponent's imagined rhythm before building your attack.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 14,
                    xp = 630,
                    firstMoves =
                        "Jab - Slip Right - Jab - Cross - Lead Hook",
                    firstCue =
                        "Slip clearly RIGHT and immediately interrupt with the jab.",
                    secondMoves =
                        "Lead Parry - Jab - Cross - Step Left - Cross",
                    secondCue =
                        "Use defence to steal the turn instead of waiting passively."
                ),

                lesson(
                    id =
                        "boxing_advanced_initiative_4",
                    title =
                        "Reset on Your Terms",
                    subtitle =
                        "End one exchange and deliberately start the next.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 14,
                    xp = 645,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Pivot Left - Reset - Double Jab",
                    firstCue =
                        "Finish the first exchange, rebuild your stance and restart deliberately.",
                    secondMoves =
                        "Cross - Lead Hook - Step Back - Reset - Jab Feint - Cross",
                    secondCue =
                        "The reset should create another decision, not simply end the work."
                ),

                lesson(
                    id =
                        "boxing_advanced_initiative_5",
                    title =
                        "Steal the Turn",
                    subtitle =
                        "Defend and immediately become the attacker.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 14,
                    xp = 665,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Slip Left - Cross",
                    firstCue =
                        "Slip clearly RIGHT, take the turn, then slip clearly LEFT as the response returns.",
                    secondMoves =
                        "Roll Left - Lead Hook - Cross - Pivot Left - Jab",
                    secondCue =
                        "Roll clearly LEFT and establish control from the new angle."
                ),

                lesson(
                    id =
                        "boxing_advanced_initiative_6",
                    title =
                        "Initiative Checkpoint",
                    subtitle =
                        "Control when the exchange begins, changes and ends.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 16,
                    xp = 720,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Lead Hook - Pivot Left - Reset - Cross",
                    firstCue =
                        "Create, attack, slip clearly RIGHT, reposition and consciously restart.",
                    secondMoves =
                        "Lead Parry - Cross - Roll Left - Lead Hook - Step Right - Jab - Cross",
                    secondCue =
                        "Take the turn after defence, roll clearly LEFT and finish from a new line."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — FALSE ENTRIES & REACTIONS
    // ---------------------------------------------------------

    private fun falseEntriesAndReactions():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_advanced_false_entries"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "False Entries & Reactions",
            subtitle =
                "Make the imagined opponent react to attacks that never fully arrive.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 2,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_false_1",
                    title =
                        "False Step Entry",
                    subtitle =
                        "Threaten distance before committing.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 13,
                    xp = 610,
                    firstMoves =
                        "False Step In - Jab - Cross - Pivot Left",
                    firstCue =
                        "Make the first step believable without allowing your stance to collapse.",
                    secondMoves =
                        "False Step In - Step Back - Cross - Lead Hook",
                    secondCue =
                        "Use the false entry to draw the imagined response before countering."
                ),

                lesson(
                    id =
                        "boxing_advanced_false_2",
                    title =
                        "False Jab Entry",
                    subtitle =
                        "Use the jab threat to enter behind another attack.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 13,
                    xp = 625,
                    firstMoves =
                        "Jab Feint - Step In - Cross - Lead Hook",
                    firstCue =
                        "The jab feint creates the entry; your feet must remain balanced.",
                    secondMoves =
                        "Jab Feint - Step Left - Jab - Cross",
                    secondCue =
                        "Make the imagined guard react before entering from the new line."
                ),

                lesson(
                    id =
                        "boxing_advanced_false_3",
                    title =
                        "False Body Attack",
                    subtitle =
                        "Threaten downstairs before attacking upstairs.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 14,
                    xp = 640,
                    firstMoves =
                        "Body Feint - Cross - Lead Hook - Cross",
                    firstCue =
                        "Change level enough to create the threat without sacrificing posture.",
                    secondMoves =
                        "Jab to Body Feint - Jab - Cross - Lead Hook",
                    secondCue =
                        "Return immediately to your boxing stance after the body threat."
                ),

                lesson(
                    id =
                        "boxing_advanced_false_4",
                    title =
                        "Reaction Into Counter",
                    subtitle =
                        "Create the response that gives you the counter.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 14,
                    xp = 655,
                    firstMoves =
                        "Jab Feint - Slip Right - Cross - Lead Hook",
                    firstCue =
                        "Imagine the feint drawing the jab, then slip clearly RIGHT.",
                    secondMoves =
                        "Body Feint - Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Use the low threat to draw the rear hand, then slip clearly LEFT."
                ),

                lesson(
                    id =
                        "boxing_advanced_false_5",
                    title =
                        "Multiple False Starts",
                    subtitle =
                        "Show more than one threat before committing.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 15,
                    xp = 680,
                    firstMoves =
                        "Jab Feint - Body Feint - Double Jab - Cross - Lead Hook",
                    firstCue =
                        "Keep every fake compact so the real attack remains fast.",
                    secondMoves =
                        "False Step In - Jab Feint - Step Right - Cross - Lead Hook",
                    secondCue =
                        "Use two different reactions before attacking from the angle."
                ),

                lesson(
                    id =
                        "boxing_advanced_false_6",
                    title =
                        "Reaction Manipulation Checkpoint",
                    subtitle =
                        "Create predictable reactions before exploiting them.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 16,
                    xp = 735,
                    firstMoves =
                        "False Step In - Jab Feint - Cross - Slip Left - Lead Hook - Pivot Left",
                    firstCue =
                        "Threaten entry, draw the response, slip clearly LEFT and finish from the angle.",
                    secondMoves =
                        "Body Feint - Double Jab - Cross - Roll Right - Cross - Step Right",
                    secondCue =
                        "Create the low reaction, attack upstairs, roll clearly RIGHT and reposition."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — ADVANCED DEFENSIVE TRANSITIONS
    // ---------------------------------------------------------

    private fun advancedDefensiveTransitions():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_advanced_defensive_transitions"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Defensive Transitions",
            subtitle =
                "Move between guard, parries, head movement, counters and exits.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 3,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_defence_1",
                    title =
                        "Guard to Counter",
                    subtitle =
                        "Absorb safely and immediately reclaim the exchange.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 13,
                    xp = 620,
                    firstMoves =
                        "High Guard - Cross - Lead Hook - Pivot Left",
                    firstCue =
                        "Do not remain shelled after defending. Rebuild vision and answer.",
                    secondMoves =
                        "High Guard - Lead Hook - Cross - Step Right",
                    secondCue =
                        "Return from a compact guard and leave the centre line."
                ),

                lesson(
                    id =
                        "boxing_advanced_defence_2",
                    title =
                        "Parry to Slip Chain",
                    subtitle =
                        "Use hand defence before directional head movement.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 14,
                    xp = 640,
                    firstMoves =
                        "Lead Parry - Slip Right - Cross - Lead Hook",
                    firstCue =
                        "Parry compactly, then slip clearly RIGHT before returning.",
                    secondMoves =
                        "Rear Hand Catch - Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Catch first, then slip clearly LEFT without opening your stance."
                ),

                lesson(
                    id =
                        "boxing_advanced_defence_3",
                    title =
                        "Slip to Roll Chain",
                    subtitle =
                        "Handle changing punch trajectories inside one exchange.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 14,
                    xp = 655,
                    firstMoves =
                        "Slip Right - Roll Left - Cross - Lead Hook",
                    firstCue =
                        "Slip clearly RIGHT and roll clearly LEFT without standing upright between them.",
                    secondMoves =
                        "Slip Left - Roll Right - Lead Hook - Cross",
                    secondCue =
                        "Slip clearly LEFT and roll clearly RIGHT while maintaining balance."
                ),

                lesson(
                    id =
                        "boxing_advanced_defence_4",
                    title =
                        "Defend the Return",
                    subtitle =
                        "Assume your counter creates another counter.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 15,
                    xp = 675,
                    firstMoves =
                        "Slip Right - Cross - Slip Left - Lead Hook - Roll Right - Cross",
                    firstCue =
                        "Slip RIGHT, counter, slip LEFT, then roll clearly RIGHT before the final return.",
                    secondMoves =
                        "Lead Parry - Cross - Roll Left - Lead Hook - Slip Right - Cross",
                    secondCue =
                        "Parry, counter, roll clearly LEFT and slip clearly RIGHT during the next layer."
                ),

                lesson(
                    id =
                        "boxing_advanced_defence_5",
                    title =
                        "Defence Into Exit",
                    subtitle =
                        "Finish difficult exchanges by recovering position.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 15,
                    xp = 695,
                    firstMoves =
                        "High Guard - Slip Right - Cross - Roll Left - Lead Hook - Pivot Left",
                    firstCue =
                        "Guard first, slip RIGHT, roll LEFT, counter and leave through the angle.",
                    secondMoves =
                        "Lead Parry - Slip Left - Cross - Lead Hook - Step Right - Step Out",
                    secondCue =
                        "Parry, slip clearly LEFT and finish by removing yourself from the exchange."
                ),

                lesson(
                    id =
                        "boxing_advanced_defence_6",
                    title =
                        "Defensive Transition Checkpoint",
                    subtitle =
                        "Move smoothly through several defensive systems.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 17,
                    xp = 755,
                    firstMoves =
                        "High Guard - Lead Parry - Slip Right - Cross - Roll Left - Lead Hook - Pivot Left",
                    firstCue =
                        "Transition from guard to parry, slip clearly RIGHT, roll clearly LEFT and exit.",
                    secondMoves =
                        "Rear Hand Catch - Slip Left - Cross - Roll Right - Lead Hook - Step Right - Jab",
                    secondCue =
                        "Catch, slip clearly LEFT, roll clearly RIGHT and regain range with the jab."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — RANGE TRANSITIONS
    // ---------------------------------------------------------

    private fun rangeTransitions():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_advanced_range_transitions"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Range Transitions",
            subtitle =
                "Move deliberately between long range, mid range and the pocket.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 4,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_range_1",
                    title =
                        "Long to Mid Range",
                    subtitle =
                        "Enter behind straight punches before working shorter combinations.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 14,
                    xp = 630,
                    firstMoves =
                        "Double Jab - Step In - Cross - Lead Hook",
                    firstCue =
                        "Use the jab to safely move from long range into combination range.",
                    secondMoves =
                        "Jab - Cross - Step In - Rear Uppercut - Lead Hook",
                    secondCue =
                        "Adjust your feet before using the shorter punches."
                ),

                lesson(
                    id =
                        "boxing_advanced_range_2",
                    title =
                        "Mid Range to Pocket",
                    subtitle =
                        "Enter close range without smothering your own work.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 14,
                    xp = 650,
                    firstMoves =
                        "Jab - Cross - Step In - Lead Uppercut - Lead Hook",
                    firstCue =
                        "Shorten the punches naturally as the distance closes.",
                    secondMoves =
                        "Double Jab - Cross - Rear Uppercut - Lead Hook - Cross",
                    secondCue =
                        "Keep your stance underneath you while the range changes."
                ),

                lesson(
                    id =
                        "boxing_advanced_range_3",
                    title =
                        "Pocket to Mid Range",
                    subtitle =
                        "Create space after close-range work.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 14,
                    xp = 665,
                    firstMoves =
                        "Lead Uppercut - Lead Hook - Step Back - Cross - Jab",
                    firstCue =
                        "Create distance before extending the straight punches.",
                    secondMoves =
                        "Rear Uppercut - Lead Hook - Pivot Left - Jab - Cross",
                    secondCue =
                        "Leave the pocket on an angle before returning to long weapons."
                ),

                lesson(
                    id =
                        "boxing_advanced_range_4",
                    title =
                        "Long-Pocket-Long",
                    subtitle =
                        "Move through multiple ranges during the same exchange.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 15,
                    xp = 685,
                    firstMoves =
                        "Double Jab - Cross - Step In - Lead Hook - Step Back - Jab",
                    firstCue =
                        "Enter, work close enough to hook, then regain long range.",
                    secondMoves =
                        "Jab - Cross - Rear Uppercut - Lead Hook - Pivot Left - Double Jab",
                    secondCue =
                        "Transition back to straight punches only after creating space."
                ),

                lesson(
                    id =
                        "boxing_advanced_range_5",
                    title =
                        "Defensive Range Change",
                    subtitle =
                        "Use defence to move between distances.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 15,
                    xp = 705,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Step Back - Jab",
                    firstCue =
                        "Slip clearly RIGHT, counter at mid range and leave behind the jab.",
                    secondMoves =
                        "Roll Left - Lead Hook - Pivot Left - Step Out - Cross",
                    secondCue =
                        "Roll clearly LEFT and use the angle to create longer range."
                ),

                lesson(
                    id =
                        "boxing_advanced_range_6",
                    title =
                        "Range Transition Checkpoint",
                    subtitle =
                        "Control where every part of the exchange takes place.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 17,
                    xp = 770,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Step In - Lead Uppercut - Lead Hook - Pivot Left - Jab",
                    firstCue =
                        "Control the journey from long range to pocket and back out through an angle.",
                    secondMoves =
                        "Jab - Cross - Roll Right - Rear Uppercut - Lead Hook - Step Back - Cross - Jab",
                    secondCue =
                        "Roll clearly RIGHT in close range, then create distance before extending."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — RING GENERALSHIP
    // ---------------------------------------------------------

    private fun ringGeneralship():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_advanced_ring_generalship"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Ring Generalship",
            subtitle =
                "Control position, space and direction instead of simply following an opponent.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 5,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_ring_1",
                    title =
                        "Control the Centre",
                    subtitle =
                        "Use the jab and feet to repeatedly reclaim strong position.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 14,
                    xp = 640,
                    firstMoves =
                        "Jab - Step Left - Jab - Cross - Reset",
                    firstCue =
                        "Take space without crossing your feet or chasing.",
                    secondMoves =
                        "Double Jab - Step Right - Cross - Lead Hook - Reset",
                    secondCue =
                        "Control the line first, then rebuild your stance."
                ),

                lesson(
                    id =
                        "boxing_advanced_ring_2",
                    title =
                        "Cut the Ring",
                    subtitle =
                        "Close escape routes rather than following directly.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 14,
                    xp = 655,
                    firstMoves =
                        "Step Left - Jab - Cross - Step Right - Lead Hook",
                    firstCue =
                        "Move across the imagined opponent's escape route before attacking.",
                    secondMoves =
                        "Step Right - Double Jab - Cross - Pivot Left",
                    secondCue =
                        "Use lateral positioning to reduce available space."
                ),

                lesson(
                    id =
                        "boxing_advanced_ring_3",
                    title =
                        "Escape the Corner",
                    subtitle =
                        "Defend, create space and leave pressure intelligently.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 15,
                    xp = 675,
                    firstMoves =
                        "High Guard - Jab - Cross - Pivot Left - Step Out",
                    firstCue =
                        "Use punches to create respect before leaving through the angle.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Step Right - Double Jab",
                    secondCue =
                        "Slip clearly RIGHT, counter and regain space with straight punches."
                ),

                lesson(
                    id =
                        "boxing_advanced_ring_4",
                    title =
                        "Pressure Without Chasing",
                    subtitle =
                        "Advance while staying balanced enough to react.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 15,
                    xp = 695,
                    firstMoves =
                        "Jab Feint - Step In - Double Jab - Cross - Step Left",
                    firstCue =
                        "Make the imagined opponent react before taking ground.",
                    secondMoves =
                        "Body Feint - Step Right - Jab - Cross - Lead Hook",
                    secondCue =
                        "Use the angle to pressure instead of simply moving straight forward."
                ),

                lesson(
                    id =
                        "boxing_advanced_ring_5",
                    title =
                        "Change Who Is Pressuring",
                    subtitle =
                        "Move smoothly between giving ground and taking ground.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 16,
                    xp = 720,
                    firstMoves =
                        "Step Back - Cross - Lead Hook - Step In - Double Jab - Cross",
                    firstCue =
                        "Make space first, counter, then deliberately become the pressure fighter.",
                    secondMoves =
                        "Slip Left - Lead Hook - Step Back - Jab - Step In - Cross",
                    secondCue =
                        "Slip clearly LEFT, create distance and retake space only when balanced."
                ),

                lesson(
                    id =
                        "boxing_advanced_ring_6",
                    title =
                        "Ring Generalship Checkpoint",
                    subtitle =
                        "Control centre, pressure, exits and resets in one continuous round.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 17,
                    xp = 785,
                    firstMoves =
                        "Jab Feint - Step Left - Double Jab - Cross - Lead Hook - Pivot Left - Reset - Jab",
                    firstCue =
                        "Create the route, pressure behind punches, turn the angle and reclaim centre.",
                    secondMoves =
                        "High Guard - Slip Right - Cross - Step Right - Step Back - Double Jab - Cross",
                    secondCue =
                        "Defend, slip clearly RIGHT, escape pressure and rebuild control."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — CHAMPIONSHIP ROUNDS
    // ---------------------------------------------------------

    private fun championshipRounds():
            TrainingChapterDefinition {

        val chapterId =
            "boxing_advanced_championship_rounds"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Championship Rounds",
            subtitle =
                "Maintain tactical discipline as rounds become longer and more demanding.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 6,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_championship_1",
                    title =
                        "Technical Pace",
                    subtitle =
                        "Work longer without sacrificing mechanics.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 15,
                    xp = 660,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Pivot Left - Jab",
                    firstCue =
                        "Stay relaxed. Quality remains more important than throwing faster.",
                    secondMoves =
                        "Jab - Cross - Slip Right - Cross - Step Back - Jab",
                    secondCue =
                        "Slip clearly RIGHT and maintain clean stance during every transition."
                ),

                lesson(
                    id =
                        "boxing_advanced_championship_2",
                    title =
                        "Pressure Pace",
                    subtitle =
                        "Maintain disciplined forward pressure.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 15,
                    xp = 680,
                    firstMoves =
                        "Step In - Double Jab - Cross - Lead Hook - Step Left",
                    firstCue =
                        "Advance with stance and punches rather than leaning forward.",
                    secondMoves =
                        "Jab Feint - Step Right - Jab - Cross - Lead Hook - Cross",
                    secondCue =
                        "Use controlled positioning even as the work rate increases."
                ),

                lesson(
                    id =
                        "boxing_advanced_championship_3",
                    title =
                        "Counterpunching Pace",
                    subtitle =
                        "Stay patient while repeatedly defending and answering.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 16,
                    xp = 700,
                    firstMoves =
                        "Slip Right - Cross - Slip Left - Lead Hook - Cross",
                    firstCue =
                        "Slip clearly RIGHT and then clearly LEFT without rushing the returns.",
                    secondMoves =
                        "Roll Left - Lead Hook - Roll Right - Cross - Lead Hook",
                    secondCue =
                        "Roll clearly LEFT and clearly RIGHT while keeping the movements economical."
                ),

                lesson(
                    id =
                        "boxing_advanced_championship_4",
                    title =
                        "Mixed Range Pace",
                    subtitle =
                        "Maintain control while repeatedly changing distance.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 16,
                    xp = 725,
                    firstMoves =
                        "Double Jab - Cross - Step In - Lead Uppercut - Lead Hook - Step Back - Jab",
                    firstCue =
                        "Do not allow fatigue to blur the difference between long and short range.",
                    secondMoves =
                        "Jab - Cross - Roll Right - Rear Uppercut - Pivot Left - Double Jab",
                    secondCue =
                        "Roll clearly RIGHT, work close and create space before returning long."
                ),

                lesson(
                    id =
                        "boxing_advanced_championship_5",
                    title =
                        "Tactical Fatigue",
                    subtitle =
                        "Make deliberate decisions even when the session becomes demanding.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 17,
                    xp = 750,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Lead Hook - Step Back - Reset - Cross",
                    firstCue =
                        "Do not rush when tired. Create, attack, slip clearly RIGHT and deliberately reset.",
                    secondMoves =
                        "Body Feint - Cross - Roll Left - Lead Hook - Pivot Left - Jab - Cross",
                    secondCue =
                        "Maintain target changes, roll clearly LEFT and keep the final punches clean."
                ),

                lesson(
                    id =
                        "boxing_advanced_championship_6",
                    title =
                        "Championship Round",
                    subtitle =
                        "Complete a demanding tactical round without abandoning technique.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 18,
                    xp = 820,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Roll Left - Lead Hook - Pivot Left - Jab - Cross",
                    firstCue =
                        "Keep every phase controlled: create, attack, slip RIGHT, roll LEFT, angle and re-enter.",
                    secondMoves =
                        "Jab to Body - Cross - Step Back - Slip Left - Cross - Lead Hook - Step Right - Reset - Double Jab",
                    secondCue =
                        "Change targets, change distance, slip clearly LEFT and finish with disciplined movement."
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
            "boxing_advanced_final_assessment"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Advanced Assessment",
            subtitle =
                "Bring the entire Boxing Fight Path together.",
            sport = SPORT,
            level =
                TrainingPathLevel.ADVANCED,
            orderInLevel = 7,
            lessons = listOf(

                lesson(
                    id =
                        "boxing_advanced_assessment_1",
                    title =
                        "Initiative Review",
                    subtitle =
                        "Control when and where the exchange begins.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 15,
                    xp = 700,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Pause - Lead Hook - Pivot Left",
                    firstCue =
                        "Control the timing before changing position.",
                    secondMoves =
                        "Lead Parry - Cross - Step Right - Jab - Cross - Reset",
                    secondCue =
                        "Take the turn after defence and consciously end the exchange."
                ),

                lesson(
                    id =
                        "boxing_advanced_assessment_2",
                    title =
                        "Reaction Review",
                    subtitle =
                        "Create reactions before attacking the opening.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 15,
                    xp = 720,
                    firstMoves =
                        "False Step In - Jab Feint - Cross - Slip Left - Lead Hook",
                    firstCue =
                        "Use two false threats, then slip clearly LEFT during the imagined response.",
                    secondMoves =
                        "Body Feint - Double Jab - Cross - Roll Right - Cross",
                    secondCue =
                        "Change attention before attacking and roll clearly RIGHT before returning."
                ),

                lesson(
                    id =
                        "boxing_advanced_assessment_3",
                    title =
                        "Defensive Review",
                    subtitle =
                        "Transition through multiple defensive layers.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 16,
                    xp = 745,
                    firstMoves =
                        "High Guard - Lead Parry - Slip Right - Cross - Roll Left - Lead Hook",
                    firstCue =
                        "Move from guard to parry, slip clearly RIGHT and roll clearly LEFT.",
                    secondMoves =
                        "Rear Hand Catch - Slip Left - Lead Hook - Roll Right - Cross - Pivot Left",
                    secondCue =
                        "Catch, slip clearly LEFT, roll clearly RIGHT and finish through the angle."
                ),

                lesson(
                    id =
                        "boxing_advanced_assessment_4",
                    title =
                        "Range & Ring Review",
                    subtitle =
                        "Control distance and position during changing exchanges.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 16,
                    xp = 770,
                    firstMoves =
                        "Double Jab - Step In - Cross - Lead Hook - Pivot Left - Step Out - Jab",
                    firstCue =
                        "Enter, attack, change angle and deliberately regain long range.",
                    secondMoves =
                        "Step Back - Cross - Step Right - Double Jab - Cross - Lead Hook",
                    secondCue =
                        "Use space first, then retake position from a better line."
                ),

                lesson(
                    id =
                        "boxing_advanced_assessment_5",
                    title =
                        "Integrated Advanced Round",
                    subtitle =
                        "Combine reaction creation, defence, counters, angles and range.",
                    chapterId =
                        chapterId,
                    order = 5,
                    minutes = 17,
                    xp = 810,
                    firstMoves =
                        "Jab Feint - Cross - Slip Right - Lead Hook to Body - Roll Left - Cross - Pivot Left - Jab",
                    firstCue =
                        "Create the reaction, slip clearly RIGHT, change level, roll clearly LEFT and finish from the angle.",
                    secondMoves =
                        "False Step In - Double Jab - Cross - Step Back - Slip Left - Lead Hook - Step Right - Cross",
                    secondCue =
                        "Threaten entry, attack, create space, slip clearly LEFT and reposition before finishing."
                ),

                lesson(
                    id =
                        "boxing_advanced_assessment_6",
                    title =
                        "Advanced Final Round",
                    subtitle =
                        "Complete the full structured Boxing Fight Path.",
                    chapterId =
                        chapterId,
                    order = 6,
                    minutes = 20,
                    xp = 1000,
                    firstMoves =
                        "Jab Feint - Double Jab - Cross - Slip Right - Lead Hook - Roll Left - Cross - Pivot Left - Step Out - Jab - Cross",
                    firstCue =
                        "Control every phase: create, attack, slip clearly RIGHT, roll clearly LEFT, change angle, exit and re-enter.",
                    secondMoves =
                        "Body Feint - Jab - Cross - Step Back - Slip Left - Cross - Lead Hook to Body - Roll Right - Lead Hook - Step Right - Double Jab",
                    secondCue =
                        "Change target and distance, slip clearly LEFT, roll clearly RIGHT and finish with disciplined positioning."
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
                TrainingPathLevel.ADVANCED,
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