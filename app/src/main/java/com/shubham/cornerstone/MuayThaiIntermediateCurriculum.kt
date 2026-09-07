package com.shubham.cornerstone

/**
 * Muay Thai Intermediate Fight Path
 *
 * 7 chapters
 * 6 structured sessions per chapter
 * 42 total Intermediate sessions.
 *
 * This stage develops:
 *
 * - adaptable eight-limb combinations
 * - layered feints and broken rhythm
 * - multi-level kicking strategy
 * - explicit directional defence and counters
 * - close-range continuity
 * - tactical pressure and movement
 * - independent decision-making
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object MuayThaiIntermediateCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MUAY_THAI

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            combinationAdaptation(),
            feintsAndBrokenRhythm(),
            multiLevelKickStrategy(),
            defensiveChainsAndCounters(),
            closeRangeContinuity(),
            tacticalMovementAndPressure(),
            intermediateAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — COMBINATION ADAPTATION
    // ---------------------------------------------------------

    private fun combinationAdaptation():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_intermediate_combination_adaptation"

        return chapter(
            id = chapterId,
            title = "Combination Adaptation",
            subtitle =
                "Change entries and finishes while preserving structure.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_combination_adaptation_1",
                    title = "Changing the Finish",
                    subtitle =
                        "Use one entry with two controlled finishing options.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 230,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep the entry consistent and complete the low kick before recovering.",
                    secondMoves =
                        "Jab - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Change the target without changing your balance or guard."
                ),
                lesson(
                    id = "muaythai_intermediate_combination_adaptation_2",
                    title = "Changing the Entry",
                    subtitle =
                        "Reach the same finish through different openings.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Recover the teep before entering behind straight punches.",
                    secondMoves =
                        "Lead Teep Feint - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep the feint compact and enter without reaching."
                ),
                lesson(
                    id = "muaythai_intermediate_combination_adaptation_3",
                    title = "Attack and Re-Attack",
                    subtitle =
                        "Reset quickly and begin a second balanced attack.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 240,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Reset - Jab - Rear Body Kick",
                    firstCue =
                        "Complete the first attack and rebuild your stance before re-attacking.",
                    secondMoves =
                        "Lead Teep - Reset - Cross - Lead Hook - Rear Knee",
                    secondCue =
                        "Use the reset to confirm your distance before entering again."
                ),
                lesson(
                    id = "muaythai_intermediate_combination_adaptation_4",
                    title = "Long-to-Close Transition",
                    subtitle =
                        "Move from distance weapons into knees and elbows.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Knee - Lead Horizontal Elbow",
                    firstCue =
                        "Move through each range without allowing your stance to narrow.",
                    secondMoves =
                        "Rear Body Kick - Jab - Lead Hook - Lead Knee - Rear Horizontal Elbow",
                    secondCue =
                        "Recover the kick before beginning the close-range entry."
                ),
                lesson(
                    id = "muaythai_intermediate_combination_adaptation_5",
                    title = "Close-to-Long Transition",
                    subtitle =
                        "Exit close range and finish by controlling distance.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Lead Horizontal Elbow - Rear Knee - Step Back - Lead Teep",
                    firstCue =
                        "Recover from the knee before stepping back into teep range.",
                    secondMoves =
                        "Rear Horizontal Elbow - Lead Knee - Exit Left - Rear Body Kick",
                    secondCue =
                        "Complete the exit before releasing the final kick."
                ),
                lesson(
                    id = "muaythai_intermediate_combination_adaptation_6",
                    title = "Adaptation Review",
                    subtitle =
                        "Review entry changes, finish changes and re-attacks.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 265,
                    firstMoves =
                        "Lead Teep Feint - Jab - Cross - Rear Low Kick - Reset - Rear Body Kick",
                    firstCue =
                        "Keep the feint believable and rebuild your stance before re-attacking.",
                    secondMoves =
                        "Jab - Lead Hook - Rear Knee - Lead Horizontal Elbow - Step Back - Lead Teep",
                    secondCue =
                        "Move cleanly from punching range to close range and back out."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — FEINTS AND BROKEN RHYTHM
    // ---------------------------------------------------------

    private fun feintsAndBrokenRhythm():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_intermediate_feints_rhythm"

        return chapter(
            id = chapterId,
            title = "Feints & Broken Rhythm",
            subtitle =
                "Disrupt predictable timing with controlled feints and pauses.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_feints_rhythm_1",
                    title = "Jab Feint Entries",
                    subtitle =
                        "Use the jab feint to open kicks and knees.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 235,
                    firstMoves =
                        "Jab Feint - Cross - Rear Low Kick",
                    firstCue =
                        "Keep the jab feint short and maintain your rear-hand guard.",
                    secondMoves =
                        "Jab Feint - Lead Hook - Rear Knee",
                    secondCue =
                        "Do not lean forward while selling the feint."
                ),
                lesson(
                    id = "muaythai_intermediate_feints_rhythm_2",
                    title = "Teep Feint Layers",
                    subtitle =
                        "Use teep reactions to create different attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 240,
                    firstMoves =
                        "Lead Teep Feint - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Return the lead foot beneath you before rotating the cross.",
                    secondMoves =
                        "Rear Teep Feint - Jab - Lead Knee - Rear Horizontal Elbow",
                    secondCue =
                        "Maintain posture while changing from the feint to close range."
                ),
                lesson(
                    id = "muaythai_intermediate_feints_rhythm_3",
                    title = "Low-Kick Feint",
                    subtitle =
                        "Use a low-kick chamber to open punches and body attacks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Rear Low Kick Feint - Cross - Lead Hook",
                    firstCue =
                        "Keep the feint controlled and place the rear foot into stance.",
                    secondMoves =
                        "Lead Low Kick Feint - Jab - Cross - Rear Body Kick",
                    secondCue =
                        "Do not allow the lead-side feint to widen your stance."
                ),
                lesson(
                    id = "muaythai_intermediate_feints_rhythm_4",
                    title = "Knee Feint Entries",
                    subtitle =
                        "Use the knee lift to create elbow and kicking openings.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Rear Knee Feint - Lead Horizontal Elbow - Rear Low Kick",
                    firstCue =
                        "Set the rear foot before rotating into the elbow.",
                    secondMoves =
                        "Lead Knee Feint - Cross - Rear Body Kick",
                    secondCue =
                        "Keep your torso tall while transitioning out of the feint."
                ),
                lesson(
                    id = "muaythai_intermediate_feints_rhythm_5",
                    title = "Broken-Rhythm Attacks",
                    subtitle =
                        "Use deliberate pauses without losing readiness.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Jab - Pause - Cross - Rear Low Kick",
                    firstCue =
                        "Stay balanced and guarded during the pause.",
                    secondMoves =
                        "Lead Teep - Pause - Jab - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Use the pause to change timing rather than relax your stance."
                ),
                lesson(
                    id = "muaythai_intermediate_feints_rhythm_6",
                    title = "Feint and Rhythm Review",
                    subtitle =
                        "Combine layered feints with controlled timing changes.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 270,
                    firstMoves =
                        "Jab Feint - Lead Teep - Pause - Cross - Rear Body Kick",
                    firstCue =
                        "Keep every false action compact and every real attack committed.",
                    secondMoves =
                        "Rear Low Kick Feint - Jab - Lead Hook - Rear Knee - Exit Left",
                    secondCue =
                        "Maintain your base through the feint, entry and exit."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — MULTI-LEVEL KICK STRATEGY
    // ---------------------------------------------------------

    private fun multiLevelKickStrategy():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_intermediate_kick_strategy"

        return chapter(
            id = chapterId,
            title = "Multi-Level Kick Strategy",
            subtitle =
                "Change kicking levels, sides and rhythms deliberately.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_kick_strategy_1",
                    title = "Low-to-Body Strategy",
                    subtitle =
                        "Build repeated attacks from the leg to the body.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 240,
                    firstMoves =
                        "Jab - Rear Low Kick - Jab - Rear Body Kick",
                    firstCue =
                        "Use the same entry while changing the final target.",
                    secondMoves =
                        "Lead Low Kick - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover the lead leg before transferring weight into the punches."
                ),
                lesson(
                    id = "muaythai_intermediate_kick_strategy_2",
                    title = "Body-to-High Strategy",
                    subtitle =
                        "Prepare controlled high kicks through body-level attacks.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 245,
                    firstMoves =
                        "Jab - Rear Body Kick - Jab - Rear High Kick",
                    firstCue =
                        "Use only a comfortable high-kick range and recover safely.",
                    secondMoves =
                        "Switch Lead Body Kick - Cross - Rear High Kick",
                    secondCue =
                        "Rebuild your stance before releasing the rear high kick."
                ),
                lesson(
                    id = "muaythai_intermediate_kick_strategy_3",
                    title = "Opposite-Side Kicks",
                    subtitle =
                        "Link lead and rear kicks without crossing your stance.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 250,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick",
                    firstCue =
                        "Place the lead foot under control before throwing the cross.",
                    secondMoves =
                        "Rear Low Kick - Jab - Switch Lead Body Kick",
                    secondCue =
                        "Recover the rear leg completely before beginning the switch."
                ),
                lesson(
                    id = "muaythai_intermediate_kick_strategy_4",
                    title = "Same-Side Repetition",
                    subtitle =
                        "Repeat a kicking side while maintaining balance.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Jab - Rear Low Kick - Cross - Rear Body Kick",
                    firstCue =
                        "Reset the rear leg between kicks instead of rushing.",
                    secondMoves =
                        "Lead Teep - Lead Low Kick - Jab - Lead Body Kick",
                    secondCue =
                        "Return the lead foot beneath you after every strike."
                ),
                lesson(
                    id = "muaythai_intermediate_kick_strategy_5",
                    title = "Teep-to-Round-Kick Strategy",
                    subtitle =
                        "Change between linear and circular kicking attacks.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Lead Teep - Cross - Rear Body Kick",
                    firstCue =
                        "Recover the teep before rotating into the cross.",
                    secondMoves =
                        "Rear Teep - Jab - Switch Lead Body Kick",
                    secondCue =
                        "Rebuild your stance before beginning the switch."
                ),
                lesson(
                    id = "muaythai_intermediate_kick_strategy_6",
                    title = "Kick Strategy Review",
                    subtitle =
                        "Review level changes, side changes and kick recovery.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 275,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick - Lead Teep - Exit Left",
                    firstCue =
                        "Change targets while preserving stance and finish outside range.",
                    secondMoves =
                        "Jab - Rear Body Kick - Reset - Jab Feint - Rear High Kick - Exit Right",
                    secondCue =
                        "Keep the high kick controlled and complete the exit after recovery."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — DEFENSIVE CHAINS AND COUNTERS
    // ---------------------------------------------------------

    private fun defensiveChainsAndCounters():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_intermediate_defensive_chains"

        return chapter(
            id = chapterId,
            title = "Defensive Chains & Counters",
            subtitle =
                "Link explicit defensive movements to immediate returns.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_defensive_chains_1",
                    title = "Lead-Check Counter Chain",
                    subtitle =
                        "Build a longer return after a lead-leg check.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 245,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover the checking leg before beginning the counter.",
                    secondMoves =
                        "Lead Check - Jab - Cross - Rear Knee - Exit Left",
                    secondCue =
                        "Rebuild your stance before entering knee range."
                ),
                lesson(
                    id = "muaythai_intermediate_defensive_chains_2",
                    title = "Rear-Check Counter Chain",
                    subtitle =
                        "Return from a rear-leg check without losing position.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 9,
                    xp = 250,
                    firstMoves =
                        "Rear Check - Jab - Cross - Lead Body Kick",
                    firstCue =
                        "Set the rear foot down in its original position before punching.",
                    secondMoves =
                        "Rear Check - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep your weight centred while moving from defence to attack."
                ),
                lesson(
                    id = "muaythai_intermediate_defensive_chains_3",
                    title = "Slip Left Counter Chain",
                    subtitle =
                        "Use Slip Left before returning with multiple weapons.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Slip Left - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep Slip Left compact and return your head to stance before kicking.",
                    secondMoves =
                        "Slip Left - Lead Hook - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Stay balanced as the counter moves into close range."
                ),
                lesson(
                    id = "muaythai_intermediate_defensive_chains_4",
                    title = "Slip Right Counter Chain",
                    subtitle =
                        "Use Slip Right before a structured rear-side return.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Slip Right - Cross - Lead Hook - Rear Body Kick",
                    firstCue =
                        "Complete Slip Right without leaning beyond your stance.",
                    secondMoves =
                        "Slip Right - Cross - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Recover your posture before entering knee range."
                ),
                lesson(
                    id = "muaythai_intermediate_defensive_chains_5",
                    title = "Roll Left and Roll Right",
                    subtitle =
                        "Use shallow directional rolls suitable for Muay Thai.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Roll Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Keep Roll Left shallow so your posture remains ready for knees and kicks.",
                    secondMoves =
                        "Roll Right - Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep Roll Right compact and return your eyes forward before countering."
                ),
                lesson(
                    id = "muaythai_intermediate_defensive_chains_6",
                    title = "Directional Defence Review",
                    subtitle =
                        "Review checks, Slip Left, Slip Right, Roll Left and Roll Right.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 280,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover the check and complete Slip Left before continuing.",
                    secondMoves =
                        "Rear Check - Jab - Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep every defensive movement compact and clearly directional."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — CLOSE-RANGE CONTINUITY
    // ---------------------------------------------------------

    private fun closeRangeContinuity():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_intermediate_close_range"

        return chapter(
            id = chapterId,
            title = "Close-Range Continuity",
            subtitle =
                "Connect punches, elbows and knees without losing posture.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_close_range_1",
                    title = "Punch-to-Elbow Continuity",
                    subtitle =
                        "Shorten punching combinations into elbow range.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 250,
                    firstMoves =
                        "Jab - Cross - Lead Horizontal Elbow - Rear Horizontal Elbow",
                    firstCue =
                        "Shorten the punches as you enter and protect your face with the free hand.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Horizontal Elbow - Lead Horizontal Elbow",
                    secondCue =
                        "Recover each side before rotating the next elbow."
                ),
                lesson(
                    id = "muaythai_intermediate_close_range_2",
                    title = "Elbow-to-Knee Continuity",
                    subtitle =
                        "Move from compact elbows into straight knees.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 255,
                    firstMoves =
                        "Lead Horizontal Elbow - Rear Horizontal Elbow - Lead Knee",
                    firstCue =
                        "Return your elbows to guard before lifting the lead knee.",
                    secondMoves =
                        "Rear Horizontal Elbow - Lead Hook - Rear Knee",
                    secondCue =
                        "Stay tall and avoid leaning into the knee."
                ),
                lesson(
                    id = "muaythai_intermediate_close_range_3",
                    title = "Knee-to-Kick Transition",
                    subtitle =
                        "Exit knee range with a controlled round kick.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Rear Knee - Step Back - Rear Body Kick",
                    firstCue =
                        "Recover the knee and recreate kicking distance before rotating.",
                    secondMoves =
                        "Lead Knee - Exit Left - Rear Low Kick",
                    secondCue =
                        "Complete the left exit before releasing the low kick."
                ),
                lesson(
                    id = "muaythai_intermediate_close_range_4",
                    title = "Long-Guard Continuity",
                    subtitle =
                        "Use the long guard to organise close-range attacks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Long Guard - Rear Knee - Lead Horizontal Elbow",
                    firstCue =
                        "Maintain the long guard until the knee begins.",
                    secondMoves =
                        "Long Guard - Lead Knee - Rear Horizontal Elbow - Step Back",
                    secondCue =
                        "Recover the lead foot before exiting."
                ),
                lesson(
                    id = "muaythai_intermediate_close_range_5",
                    title = "Close-Range Re-Attack",
                    subtitle =
                        "Exit briefly before returning with a second attack.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Jab - Rear Knee - Lead Horizontal Elbow - Step Back - Rear Body Kick",
                    firstCue =
                        "Create enough space for the kick before re-attacking.",
                    secondMoves =
                        "Cross - Lead Knee - Rear Horizontal Elbow - Exit Right - Lead Teep",
                    secondCue =
                        "Recover from close range and finish by controlling distance."
                ),
                lesson(
                    id = "muaythai_intermediate_close_range_6",
                    title = "Close-Range Continuity Review",
                    subtitle =
                        "Review entries, transitions, exits and re-attacks.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 285,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Knee - Lead Horizontal Elbow - Exit Left",
                    firstCue =
                        "Move through each range while maintaining a stable base.",
                    secondMoves =
                        "Jab Feint - Lead Hook - Rear Horizontal Elbow - Lead Knee - Step Back - Rear Body Kick",
                    secondCue =
                        "Keep the close-range section compact and rebuild distance before kicking."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 6 — TACTICAL MOVEMENT AND PRESSURE
    // ---------------------------------------------------------

    private fun tacticalMovementAndPressure():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_intermediate_tactical_movement"

        return chapter(
            id = chapterId,
            title = "Tactical Movement & Pressure",
            subtitle =
                "Use pressure, distance and angles with clear purpose.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_tactical_movement_1",
                    title = "Long-Range Control",
                    subtitle =
                        "Control space with jabs and teeps before attacking.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 9,
                    xp = 255,
                    firstMoves =
                        "Lead Teep - Jab - Step Back - Rear Teep",
                    firstCue =
                        "Recover every teep and keep your stance ready between attacks.",
                    secondMoves =
                        "Double Jab - Lead Teep - Exit Left",
                    secondCue =
                        "Use the jab to establish range before finishing outside the centre line."
                ),
                lesson(
                    id = "muaythai_intermediate_tactical_movement_2",
                    title = "Pressure Behind the Teep",
                    subtitle =
                        "Advance safely after controlling distance.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Lead Teep - Step Forward - Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the teep before advancing behind your punches.",
                    secondMoves =
                        "Rear Teep - Step Forward - Lead Hook - Rear Knee",
                    secondCue =
                        "Maintain tall posture as the range closes."
                ),
                lesson(
                    id = "muaythai_intermediate_tactical_movement_3",
                    title = "Left-Angle Attack",
                    subtitle =
                        "Create a left-side angle before continuing offense.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Jab - Cross - Pivot Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Complete Pivot Left before beginning the second attack.",
                    secondMoves =
                        "Lead Teep - Pivot Left - Cross - Rear Body Kick",
                    secondCue =
                        "Keep your feet separated as you create the angle."
                ),
                lesson(
                    id = "muaythai_intermediate_tactical_movement_4",
                    title = "Right-Angle Attack",
                    subtitle =
                        "Create a right-side angle while preserving guard.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Jab - Lead Hook - Pivot Right - Cross - Rear Body Kick",
                    firstCue =
                        "Finish Pivot Right before transferring weight into the cross.",
                    secondMoves =
                        "Lead Teep Feint - Pivot Right - Lead Hook - Rear Knee",
                    secondCue =
                        "Stay balanced while changing from long range to close range."
                ),
                lesson(
                    id = "muaythai_intermediate_tactical_movement_5",
                    title = "Draw and Return",
                    subtitle =
                        "Give ground deliberately before returning with structure.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 10,
                    xp = 275,
                    firstMoves =
                        "Step Back - Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Complete the step back before driving forward again.",
                    secondMoves =
                        "Step Back - Lead Teep - Jab Feint - Rear Low Kick",
                    secondCue =
                        "Use the teep to re-establish distance before attacking."
                ),
                lesson(
                    id = "muaythai_intermediate_tactical_movement_6",
                    title = "Tactical Movement Review",
                    subtitle =
                        "Review pressure, directional angles and planned exits.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 11,
                    xp = 290,
                    firstMoves =
                        "Lead Teep - Step Forward - Jab - Cross - Pivot Left - Rear Body Kick",
                    firstCue =
                        "Pressure behind structure and complete the angle before kicking.",
                    secondMoves =
                        "Step Back - Rear Teep - Jab Feint - Pivot Right - Lead Hook - Rear Knee",
                    secondCue =
                        "Control distance first, then enter from the new angle."
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
            "muaythai_intermediate_assessment"

        return chapter(
            id = chapterId,
            title = "Intermediate Assessment",
            subtitle =
                "Test adaptable Muay Thai across every striking range.",
            order = 7,
            lessons = listOf(
                lesson(
                    id = "muaythai_intermediate_assessment_1",
                    title = "Adaptive Combination Review",
                    subtitle =
                        "Review entry changes, finish changes and re-attacks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 10,
                    xp = 260,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Low Kick - Reset - Rear Body Kick",
                    firstCue =
                        "Recover every strike and make the reset deliberate.",
                    secondMoves =
                        "Lead Teep Feint - Cross - Lead Hook - Rear Knee - Lead Horizontal Elbow",
                    secondCue =
                        "Stay balanced while moving from the feint into close range."
                ),
                lesson(
                    id = "muaythai_intermediate_assessment_2",
                    title = "Feint and Rhythm Review",
                    subtitle =
                        "Review layered feints and deliberate timing changes.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 10,
                    xp = 265,
                    firstMoves =
                        "Jab Feint - Pause - Cross - Rear Body Kick",
                    firstCue =
                        "Remain guarded during the pause and commit to the final attack.",
                    secondMoves =
                        "Rear Low Kick Feint - Jab - Lead Hook - Rear Knee - Exit Left",
                    secondCue =
                        "Keep the feint compact and complete the exit after recovering."
                ),
                lesson(
                    id = "muaythai_intermediate_assessment_3",
                    title = "Multi-Level Kick Review",
                    subtitle =
                        "Review kick levels, side changes and distance control.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 10,
                    xp = 270,
                    firstMoves =
                        "Lead Low Kick - Cross - Rear Body Kick - Lead Teep",
                    firstCue =
                        "Recover after every kick and finish by controlling distance.",
                    secondMoves =
                        "Jab - Rear Body Kick - Reset - Jab Feint - Rear High Kick",
                    secondCue =
                        "Use a comfortable high-kick range and return safely to stance."
                ),
                lesson(
                    id = "muaythai_intermediate_assessment_4",
                    title = "Directional Defence Review",
                    subtitle =
                        "Review checks and every explicit directional head movement.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 11,
                    xp = 280,
                    firstMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Recover the check and complete Slip Left before countering.",
                    secondMoves =
                        "Rear Check - Jab - Slip Right - Cross - Roll Left - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Keep Slip Right and Roll Left compact and controlled."
                ),
                lesson(
                    id = "muaythai_intermediate_assessment_5",
                    title = "Close-Range Tactical Review",
                    subtitle =
                        "Review knees, elbows, exits and long-range recovery.",
                    chapterId = chapterId,
                    order = 5,
                    minutes = 11,
                    xp = 290,
                    firstMoves =
                        "Jab - Cross - Rear Knee - Lead Horizontal Elbow - Exit Left - Lead Teep",
                    firstCue =
                        "Recover each close-range strike before exiting and rebuilding distance.",
                    secondMoves =
                        "Long Guard - Lead Knee - Rear Horizontal Elbow - Step Back - Rear Body Kick",
                    secondCue =
                        "Maintain posture through the long guard and complete the step back before kicking."
                ),
                lesson(
                    id = "muaythai_intermediate_assessment_6",
                    title = "Muay Thai Intermediate Final",
                    subtitle =
                        "Complete the Muay Thai Intermediate stage.",
                    chapterId = chapterId,
                    order = 6,
                    minutes = 13,
                    xp = 320,
                    firstMoves =
                        "Lead Teep Feint - Jab - Cross - Rear Body Kick - Pivot Left - Lead Hook - Rear Knee - Exit Right",
                    firstCue =
                        "Move through every range with deliberate timing and stable recovery.",
                    secondMoves =
                        "Lead Check - Cross - Slip Left - Lead Hook - Roll Right - Cross - Rear Low Kick - Step Back - Lead Teep",
                    secondCue =
                        "Keep every defensive direction explicit and finish by rebuilding long range."
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
                TrainingPathLevel.INTERMEDIATE,
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