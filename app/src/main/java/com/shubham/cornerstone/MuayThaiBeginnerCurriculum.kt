package com.shubham.cornerstone

/**
 * Muay Thai Beginner Fight Path
 *
 * 6 chapters
 * 4 structured sessions per chapter
 * 24 total Beginner sessions.
 *
 * This stage develops:
 *
 * - balanced Muay Thai stance and rhythm
 * - straight punches and teeps
 * - lead and rear round kicks
 * - low-kick checks and returns
 * - basic knees and elbows
 * - simple eight-limb combinations
 * - safe stance recovery
 *
 * Directional boxing defence must always remain explicit:
 *
 * Slip Left
 * Slip Right
 * Roll Left
 * Roll Right
 */
object MuayThaiBeginnerCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MUAY_THAI

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            muayThaiBase(),
            roundKickFoundation(),
            checksAndReturns(),
            kneesAndElbows(),
            eightLimbCombinations(),
            beginnerAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — MUAY THAI BASE
    // ---------------------------------------------------------

    private fun muayThaiBase():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_beginner_base"

        return chapter(
            id = chapterId,
            title = "Muay Thai Base",
            subtitle =
                "Build your stance, rhythm, straight punches and teeps.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "muaythai_beginner_base_1",
                    title = "Stance & Straight Punches",
                    subtitle =
                        "Build straight punches from a balanced Muay Thai stance.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 100,
                    firstMoves =
                        "Jab - Cross",
                    firstCue =
                        "Stay tall, keep your guard high and return both hands quickly.",
                    secondMoves =
                        "Double Jab - Cross",
                    secondCue =
                        "Maintain your stance without leaning onto the front leg."
                ),
                lesson(
                    id = "muaythai_beginner_base_2",
                    title = "Lead Teep",
                    subtitle =
                        "Use the lead teep to control distance.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 5,
                    xp = 110,
                    firstMoves =
                        "Lead Teep - Jab",
                    firstCue =
                        "Return the lead foot to stance before throwing the jab.",
                    secondMoves =
                        "Jab - Lead Teep",
                    secondCue =
                        "Stay tall and extend the teep without falling backward."
                ),
                lesson(
                    id = "muaythai_beginner_base_3",
                    title = "Rear Teep",
                    subtitle =
                        "Build a strong rear-side distance weapon.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Jab - Rear Teep",
                    firstCue =
                        "Use the jab to establish range before lifting the rear knee.",
                    secondMoves =
                        "Double Jab - Rear Teep",
                    secondCue =
                        "Recover the rear foot directly into your stance."
                ),
                lesson(
                    id = "muaythai_beginner_base_4",
                    title = "Base Checkpoint",
                    subtitle =
                        "Combine punches, teeps and controlled movement.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Step Back",
                    firstCue =
                        "Recover the teep before punching and leave range in stance.",
                    secondMoves =
                        "Jab - Cross - Rear Teep",
                    secondCue =
                        "Finish the punches before extending the rear teep."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — ROUND-KICK FOUNDATION
    // ---------------------------------------------------------

    private fun roundKickFoundation():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_beginner_round_kicks"

        return chapter(
            id = chapterId,
            title = "Round-Kick Foundation",
            subtitle =
                "Learn lead and rear kicks with controlled recovery.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "muaythai_beginner_round_kicks_1",
                    title = "Rear Low Kick",
                    subtitle =
                        "Build your first reliable Muay Thai low kick.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 110,
                    firstMoves =
                        "Jab - Rear Low Kick",
                    firstCue =
                        "Use the jab to hide the kick and return directly to stance.",
                    secondMoves =
                        "Cross - Rear Low Kick",
                    secondCue =
                        "Finish the cross before rotating through the low kick."
                ),
                lesson(
                    id = "muaythai_beginner_round_kicks_2",
                    title = "Lead Low Kick",
                    subtitle =
                        "Attack the leg from your lead side.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 5,
                    xp = 120,
                    firstMoves =
                        "Jab - Lead Low Kick",
                    firstCue =
                        "Keep the lead kick quick and recover the foot under control.",
                    secondMoves =
                        "Cross - Lead Low Kick",
                    secondCue =
                        "Do not allow the cross to pull your weight too far forward."
                ),
                lesson(
                    id = "muaythai_beginner_round_kicks_3",
                    title = "Rear Body Kick",
                    subtitle =
                        "Connect straight punches to the rear body kick.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Use the punches to establish range before turning the hip.",
                    secondMoves =
                        "Double Jab - Rear Body Kick",
                    secondCue =
                        "Return the kicking leg safely into stance."
                ),
                lesson(
                    id = "muaythai_beginner_round_kicks_4",
                    title = "Round-Kick Checkpoint",
                    subtitle =
                        "Switch between low kicks and body kicks.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Keep your guard high and recover immediately after the kick.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Finish the hook before rotating into the body kick."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — CHECKS AND RETURNS
    // ---------------------------------------------------------

    private fun checksAndReturns():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_beginner_checks_returns"

        return chapter(
            id = chapterId,
            title = "Checks & Returns",
            subtitle =
                "Defend low kicks and return from a stable stance.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "muaythai_beginner_checks_1",
                    title = "Lead Check",
                    subtitle =
                        "Defend a low kick using the lead leg.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Lead Check - Jab - Cross",
                    firstCue =
                        "Set the checking leg down under control before punching.",
                    secondMoves =
                        "Lead Check - Cross - Rear Low Kick",
                    secondCue =
                        "Recover your stance completely before returning."
                ),
                lesson(
                    id = "muaythai_beginner_checks_2",
                    title = "Rear Check",
                    subtitle =
                        "Defend a kick using the rear leg.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Rear Check - Jab - Cross",
                    firstCue =
                        "Return the rear foot to its original position before punching.",
                    secondMoves =
                        "Rear Check - Cross - Lead Low Kick",
                    secondCue =
                        "Rebuild your base before lifting the lead leg."
                ),
                lesson(
                    id = "muaythai_beginner_checks_3",
                    title = "Step-Back Defence",
                    subtitle =
                        "Create space before returning with long-range weapons.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Step Back - Lead Teep - Jab",
                    firstCue =
                        "Complete the step before lifting the lead knee.",
                    secondMoves =
                        "Step Back - Jab - Rear Body Kick",
                    secondCue =
                        "Re-establish your range before releasing the kick."
                ),
                lesson(
                    id = "muaythai_beginner_checks_4",
                    title = "Defence Checkpoint",
                    subtitle =
                        "Combine checks, distance and controlled returns.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Lead Check - Cross - Lead Hook - Rear Low Kick",
                    firstCue =
                        "Check, recover, complete the punches and then kick.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Teep",
                    secondCue =
                        "Recover the checking foot before beginning the return."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — KNEES AND ELBOWS
    // ---------------------------------------------------------

    private fun kneesAndElbows():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_beginner_knees_elbows"

        return chapter(
            id = chapterId,
            title = "Knees & Elbows",
            subtitle =
                "Introduce controlled close-range Muay Thai weapons.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "muaythai_beginner_knees_elbows_1",
                    title = "Rear Straight Knee",
                    subtitle =
                        "Connect basic punches to the rear knee.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Jab - Cross - Rear Knee",
                    firstCue =
                        "Stay tall and return the rear leg directly into stance.",
                    secondMoves =
                        "Double Jab - Rear Knee",
                    secondCue =
                        "Shorten the final step as you enter knee range."
                ),
                lesson(
                    id = "muaythai_beginner_knees_elbows_2",
                    title = "Lead Straight Knee",
                    subtitle =
                        "Build a controlled lead-side knee attack.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Cross - Lead Knee",
                    firstCue =
                        "Recover from the cross before lifting the lead knee.",
                    secondMoves =
                        "Jab - Cross - Lead Knee",
                    secondCue =
                        "Return the lead foot to stance without falling forward."
                ),
                lesson(
                    id = "muaythai_beginner_knees_elbows_3",
                    title = "Lead Horizontal Elbow",
                    subtitle =
                        "Introduce a compact lead elbow in shadowboxing.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 145,
                    firstMoves =
                        "Jab - Lead Horizontal Elbow",
                    firstCue =
                        "Keep the elbow compact and your opposite hand at guard.",
                    secondMoves =
                        "Cross - Lead Horizontal Elbow",
                    secondCue =
                        "Recover your posture before rotating the lead elbow."
                ),
                lesson(
                    id = "muaythai_beginner_knees_elbows_4",
                    title = "Rear Horizontal Elbow",
                    subtitle =
                        "Introduce a controlled rear elbow.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 165,
                    firstMoves =
                        "Jab - Rear Horizontal Elbow",
                    firstCue =
                        "Step only enough to reach close range and protect your chin.",
                    secondMoves =
                        "Lead Horizontal Elbow - Rear Horizontal Elbow",
                    secondCue =
                        "Return the lead side to guard before rotating the rear elbow."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — EIGHT-LIMB COMBINATIONS
    // ---------------------------------------------------------

    private fun eightLimbCombinations():
            TrainingChapterDefinition {

        val chapterId =
            "muaythai_beginner_eight_limbs"

        return chapter(
            id = chapterId,
            title = "Eight-Limb Combinations",
            subtitle =
                "Connect punches, kicks, knees and elbows.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "muaythai_beginner_eight_limbs_1",
                    title = "Hands Into Kicks",
                    subtitle =
                        "Use boxing combinations to prepare round kicks.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick",
                    firstCue =
                        "Finish the punches before rotating into the kick.",
                    secondMoves =
                        "Cross - Lead Hook - Rear Body Kick",
                    secondCue =
                        "Recover the hook and keep the opposite hand high."
                ),
                lesson(
                    id = "muaythai_beginner_eight_limbs_2",
                    title = "Hands Into Knees",
                    subtitle =
                        "Enter knee range behind compact punches.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Jab - Cross - Lead Hook - Rear Knee",
                    firstCue =
                        "Shorten your punches as you enter knee range.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Knee",
                    secondCue =
                        "Recover the hook before lifting the lead knee."
                ),
                lesson(
                    id = "muaythai_beginner_eight_limbs_3",
                    title = "Hands Into Elbows",
                    subtitle =
                        "Enter elbow range without losing your guard.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Jab - Cross - Lead Horizontal Elbow",
                    firstCue =
                        "Keep the elbow tight and the rear hand protecting your face.",
                    secondMoves =
                        "Jab - Lead Horizontal Elbow - Rear Horizontal Elbow",
                    secondCue =
                        "Recover each side before throwing the next elbow."
                ),
                lesson(
                    id = "muaythai_beginner_eight_limbs_4",
                    title = "Eight-Limb Checkpoint",
                    subtitle =
                        "Move safely between long and close-range weapons.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 180,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick",
                    firstCue =
                        "Control range with the teep before entering behind punches.",
                    secondMoves =
                        "Jab - Cross - Lead Horizontal Elbow - Rear Knee - Step Back",
                    secondCue =
                        "Recover from close range before creating distance."
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
            "muaythai_beginner_assessment"

        return chapter(
            id = chapterId,
            title = "Beginner Assessment",
            subtitle =
                "Bring your foundational Muay Thai weapons together.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "muaythai_beginner_assessment_1",
                    title = "Range Review",
                    subtitle =
                        "Review punches, teeps and distance control.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Step Back",
                    firstCue =
                        "Recover each weapon and finish outside striking range.",
                    secondMoves =
                        "Double Jab - Rear Teep - Jab",
                    secondCue =
                        "Return the rear foot before restarting with the jab."
                ),
                lesson(
                    id = "muaythai_beginner_assessment_2",
                    title = "Kick and Check Review",
                    subtitle =
                        "Review round kicks, checks and returns.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Lead Check - Cross - Rear Low Kick",
                    firstCue =
                        "Recover the checking leg before returning with offense.",
                    secondMoves =
                        "Rear Check - Jab - Cross - Lead Body Kick",
                    secondCue =
                        "Rebuild your stance before lifting the lead leg."
                ),
                lesson(
                    id = "muaythai_beginner_assessment_3",
                    title = "Knee and Elbow Review",
                    subtitle =
                        "Review controlled close-range weapons.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 170,
                    firstMoves =
                        "Jab - Cross - Lead Horizontal Elbow - Rear Knee",
                    firstCue =
                        "Keep the combination compact and your free hand at guard.",
                    secondMoves =
                        "Cross - Lead Hook - Lead Knee - Step Back",
                    secondCue =
                        "Recover the lead foot before leaving close range."
                ),
                lesson(
                    id = "muaythai_beginner_assessment_4",
                    title = "Muay Thai Beginner Final",
                    subtitle =
                        "Complete the Muay Thai Beginner stage.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 200,
                    firstMoves =
                        "Lead Teep - Jab - Cross - Rear Body Kick - Step Back",
                    firstCue =
                        "Control range, enter, kick and leave from a balanced stance.",
                    secondMoves =
                        "Lead Check - Cross - Lead Horizontal Elbow - Rear Knee - Step Back - Lead Teep",
                    secondCue =
                        "Defend, recover, work at close range and finish by rebuilding distance."
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
                TrainingPathLevel.BEGINNER,
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
                TrainingPathLevel.BEGINNER,
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