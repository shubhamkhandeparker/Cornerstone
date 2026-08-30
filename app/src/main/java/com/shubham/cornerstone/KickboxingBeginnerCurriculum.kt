package com.shubham.cornerstone

/**
 * Kickboxing Beginner Fight Path
 *
 * 6 chapters
 * 4 structured sessions per chapter
 * 24 total Beginner sessions.
 *
 * At approximately 3 Fight Path sessions per week:
 * roughly 8 weeks of structured Beginner progression.
 *
 * Main Beginner goals:
 *
 * - build a stable kickboxing stance
 * - learn straight punches
 * - connect punches to kicks
 * - learn lead and rear low kicks
 * - introduce body kicks
 * - introduce front kicks
 * - learn basic kick defence
 * - learn simple counters
 * - build basic footwork
 * - finish combinations in stance
 *
 * The user should NOT leave Beginner simply because
 * they memorised a few combinations.
 *
 * The 24 sessions repeatedly revisit the same core
 * mechanics in progressively more demanding situations.
 *
 * Directional boxing defence remains explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 *
 * Never use ambiguous standalone "Slip" or "Roll".
 */
object KickboxingBeginnerCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_KICKBOXING

    val chapters:
            List<TrainingChapterDefinition> =
        listOf(
            stanceAndStraightWeapons(),
            lowKickFoundation(),
            handsIntoKicks(),
            bodyAndFrontKicks(),
            defenceAndCounters(),
            beginnerAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — STANCE & STRAIGHT WEAPONS
    // ---------------------------------------------------------

    private fun stanceAndStraightWeapons():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_beginner_stance_straights"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Kickboxing Base",
            subtitle =
                "Build your stance, straight punches and basic movement.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 1,
            lessons = listOf(

                lesson(
                    id =
                        "kickboxing_beginner_base_1",
                    title =
                        "Jab & Cross",
                    subtitle =
                        "Build clean straight punches from a kick-ready stance.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 100,
                    firstMoves =
                        "Jab - Cross",
                    firstCue =
                        "Return both hands to guard and keep your stance ready to kick.",
                    secondMoves =
                        "Double Jab - Cross",
                    secondCue =
                        "Stay balanced instead of reaching forward."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_base_2",
                    title =
                        "Straight Punch Movement",
                    subtitle =
                        "Punch without becoming stationary.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 5,
                    xp = 110,
                    firstMoves =
                        "Jab - Cross - Step Back",
                    firstCue =
                        "Finish the cross before creating distance.",
                    secondMoves =
                        "Double Jab - Cross - Step Left",
                    secondCue =
                        "Move after punching without crossing your feet."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_base_3",
                    title =
                        "Lead Hook Introduction",
                    subtitle =
                        "Add your first curved punch while protecting balance.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Jab - Cross - Lead Hook",
                    firstCue =
                        "Rotate through the hook without allowing your feet to become square.",
                    secondMoves =
                        "Double Jab - Lead Hook - Cross",
                    secondCue =
                        "Bring the hook back to guard before the cross."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_base_4",
                    title =
                        "Base Checkpoint",
                    subtitle =
                        "Combine straight punches, hook and basic movement.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Step Back",
                    firstCue =
                        "Complete every punch before leaving range.",
                    secondMoves =
                        "Double Jab - Cross - Step Left - Jab",
                    secondCue =
                        "Rebuild your stance before throwing the final jab."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — LOW KICK FOUNDATION
    // ---------------------------------------------------------

    private fun lowKickFoundation():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_beginner_low_kicks"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Low Kick Foundation",
            subtitle =
                "Learn to attack the legs while returning safely to stance.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 2,
            lessons = listOf(

                lesson(
                    id =
                        "kickboxing_beginner_lowkick_1",
                    title =
                        "Rear Low Kick",
                    subtitle =
                        "Build your first reliable rear-leg kick.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 110,
                    firstMoves =
                        "Jab - Rear Low Kick",
                    firstCue =
                        "Use the jab to hide the kick and recover your stance immediately.",
                    secondMoves =
                        "Cross - Rear Low Kick",
                    secondCue =
                        "Finish the cross before rotating into the kick."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_lowkick_2",
                    title =
                        "Lead Low Kick",
                    subtitle =
                        "Introduce low kicks from the lead side.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 5,
                    xp = 120,
                    firstMoves =
                        "Jab - Lead Low Kick",
                    firstCue =
                        "Keep the lead kick quick and return the foot under control.",
                    secondMoves =
                        "Cross - Lead Low Kick",
                    secondCue =
                        "Do not allow the cross to pull your weight too far forward."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_lowkick_3",
                    title =
                        "Hands to Rear Low Kick",
                    subtitle =
                        "Use boxing combinations to hide the kick.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Let the punches draw attention high before the low kick.",
                    secondMoves =
                        "Jab - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Recover from the hook before committing to the kick."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_lowkick_4",
                    title =
                        "Low Kick Checkpoint",
                    subtitle =
                        "Switch between lead and rear low kicks.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Double Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Keep your stance underneath you throughout the combination.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Lead Low Kick",
                    secondCue =
                        "Finish your hands before attacking the leg."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — HANDS INTO KICKS
    // ---------------------------------------------------------

    private fun handsIntoKicks():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_beginner_hands_into_kicks"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Hands Into Kicks",
            subtitle =
                "Make your punches and kicks work as one system.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 3,
            lessons = listOf(

                lesson(
                    id =
                        "kickboxing_beginner_handskick_1",
                    title =
                        "Jab to Rear Kick",
                    subtitle =
                        "Use the lead hand to prepare the rear leg.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Jab - Rear Body Kick",
                    firstCue =
                        "Use the jab to establish distance before rotating into the kick.",
                    secondMoves =
                        "Double Jab - Rear Body Kick",
                    secondCue =
                        "Finish the second jab before beginning the kick."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_handskick_2",
                    title =
                        "Cross to Lead Kick",
                    subtitle =
                        "Connect the rear hand to the lead leg.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Cross - Lead Body Kick",
                    firstCue =
                        "Recover your punching stance before lifting the lead leg.",
                    secondMoves =
                        "Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Stay balanced as the combination changes sides."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_handskick_3",
                    title =
                        "Punch-Kick-Punch",
                    subtitle =
                        "Continue attacking after the kick.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross",
                    firstCue =
                        "Return the kicking leg to stance before throwing the cross.",
                    secondMoves =
                        "Cross - Lead Low Kick - Jab",
                    secondCue =
                        "Rebuild your feet before restarting with the jab."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_handskick_4",
                    title =
                        "Hands & Kicks Checkpoint",
                    subtitle =
                        "Move naturally between punching and kicking.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick - Cross",
                    firstCue =
                        "Recover the kick completely before returning with the cross.",
                    secondMoves =
                        "Double Jab - Lead Hook - Rear Low Kick - Jab",
                    secondCue =
                        "Finish the hook, kick, recover and begin again from stance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — BODY & FRONT KICKS
    // ---------------------------------------------------------

    private fun bodyAndFrontKicks():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_beginner_body_front_kicks"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Body & Front Kicks",
            subtitle =
                "Add body attacks and simple long-range kicking tools.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 4,
            lessons = listOf(

                lesson(
                    id =
                        "kickboxing_beginner_bodykick_1",
                    title =
                        "Rear Body Kick",
                    subtitle =
                        "Build control and recovery on the rear round kick.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Use the straight punches to create the opening for the body kick.",
                    secondMoves =
                        "Double Jab - Rear Body Kick",
                    secondCue =
                        "Return quickly to stance after impact."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_bodykick_2",
                    title =
                        "Lead Body Kick",
                    subtitle =
                        "Attack the body from your lead side.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Jab - Lead Body Kick",
                    firstCue =
                        "Recover the lead leg without falling forward.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Body Kick",
                    secondCue =
                        "Finish the hands before turning into the kick."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_frontkick_1",
                    title =
                        "Front Kick Control",
                    subtitle =
                        "Use the front kick to manage distance.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 145,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross",
                    firstCue =
                        "Place the kicking foot back into stance before punching.",
                    secondMoves =
                        "Jab - Rear Front Kick - Step Back",
                    secondCue =
                        "Stay tall through the front kick and recover under control."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_bodykick_4",
                    title =
                        "Kick Range Checkpoint",
                    subtitle =
                        "Move between front kicks, body kicks and punches.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 170,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Use the front kick for range before entering behind your hands.",
                    secondMoves =
                        "Jab - Cross - Lead Body Kick - Step Back",
                    secondCue =
                        "Finish the kick and leave range without losing balance."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — DEFENCE & COUNTERS
    // ---------------------------------------------------------

    private fun defenceAndCounters():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_beginner_defence_counters"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Defence & Counters",
            subtitle =
                "Defend punches and kicks before returning safely.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 5,
            lessons = listOf(

                lesson(
                    id =
                        "kickboxing_beginner_defence_1",
                    title =
                        "Lead Kick Check",
                    subtitle =
                        "Defend a low kick and rebuild your stance.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Lead Check - Jab - Cross",
                    firstCue =
                        "Put the checking leg down under control before punching.",
                    secondMoves =
                        "Lead Check - Cross - Rear Low Kick",
                    secondCue =
                        "Recover fully before beginning the counter combination."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_defence_2",
                    title =
                        "Rear Kick Check",
                    subtitle =
                        "Defend from the rear side and answer.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 145,
                    firstMoves =
                        "Rear Check - Jab - Cross",
                    firstCue =
                        "Recover your stance immediately after the check.",
                    secondMoves =
                        "Rear Check - Cross - Lead Low Kick",
                    secondCue =
                        "Do not counter until the checking foot is stable."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_defence_3",
                    title =
                        "Straight Punch Defence",
                    subtitle =
                        "Introduce directional head movement inside kickboxing.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Slip Right - Cross - Rear Low Kick",
                    firstCue =
                        "Slip clearly RIGHT, return with the cross and finish with the low kick.",
                    secondMoves =
                        "Slip Left - Lead Hook - Cross",
                    secondCue =
                        "Slip clearly LEFT and rebuild your stance before countering."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_defence_4",
                    title =
                        "Defence Checkpoint",
                    subtitle =
                        "Defend punches and kicks before returning with combinations.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 175,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Check, recover, complete the hands and then kick.",
                    secondMoves =
                        "Slip Right - Cross - Lead Hook - Step Back",
                    secondCue =
                        "Slip clearly RIGHT and leave range after the counter."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — BEGINNER ASSESSMENT
    // ---------------------------------------------------------

    private fun beginnerAssessment():
            TrainingChapterDefinition {

        val chapterId =
            "kickboxing_beginner_assessment"

        return TrainingChapterDefinition(
            id = chapterId,
            title = "Beginner Assessment",
            subtitle =
                "Bring your punches, kicks, defence and movement together.",
            sport = SPORT,
            level =
                TrainingPathLevel.BEGINNER,
            orderInLevel = 6,
            lessons = listOf(

                lesson(
                    id =
                        "kickboxing_beginner_assessment_1",
                    title =
                        "Hands Review",
                    subtitle =
                        "Show clean boxing mechanics from your kickboxing stance.",
                    chapterId =
                        chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 145,
                    firstMoves =
                        "Double Jab - Cross - Lead Hook - Step Back",
                    firstCue =
                        "Stay balanced and keep the stance ready for kicks.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Step Left - Jab",
                    secondCue =
                        "Move only after completing the punching sequence."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_assessment_2",
                    title =
                        "Kick Review",
                    subtitle =
                        "Show control with lead and rear kicks.",
                    chapterId =
                        chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 155,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross - Lead Low Kick",
                    firstCue =
                        "Recover your stance between every kick and punch.",
                    secondMoves =
                        "Double Jab - Rear Body Kick - Step Back",
                    secondCue =
                        "Finish the kick before leaving range."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_assessment_3",
                    title =
                        "Defence Review",
                    subtitle =
                        "Defend, recover and counter without rushing.",
                    chapterId =
                        chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 165,
                    firstMoves =
                        "Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Check first, recover completely and then counter.",
                    secondMoves =
                        "Slip Left - Lead Hook - Cross - Step Right",
                    secondCue =
                        "Slip clearly LEFT and reposition after the counter."
                ),

                lesson(
                    id =
                        "kickboxing_beginner_assessment_4",
                    title =
                        "Beginner Final Round",
                    subtitle =
                        "Complete the Beginner Kickboxing stage.",
                    chapterId =
                        chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Lead Front Kick - Jab - Cross - Rear Body Kick - Step Back",
                    firstCue =
                        "Control range, enter behind the hands, kick and leave balanced.",
                    secondMoves =
                        "Jab - Cross - Slip Right - Cross - Lead Hook - Rear Low Kick",
                    secondCue =
                        "Slip clearly RIGHT, complete the counter punches and finish with the kick."
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
                TrainingPathLevel.BEGINNER,
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