package com.shubham.cornerstone

/**
 * MMA Beginner Fight Path
 *
 * 6 chapters
 * 4 structured sessions per chapter
 * 24 total Beginner sessions.
 *
 * This stage develops:
 *
 * - balanced MMA stance and movement
 * - fundamental punches and low kicks
 * - safe level changes and shadow takedown entries
 * - sprawls and basic takedown defence
 * - solo ground movement
 * - controlled transitions back to standing
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
object MMABeginnerCurriculum {

    private const val SPORT =
        TrainingCurriculum.SPORT_MMA

    val chapters: List<TrainingChapterDefinition> =
        listOf(
            mmaStrikingBase(),
            strikingIntoWrestling(),
            takedownDefence(),
            groundMovementFoundation(),
            mmaTransitions(),
            beginnerAssessment()
        )

    // ---------------------------------------------------------
    // CHAPTER 1 — MMA STRIKING BASE
    // ---------------------------------------------------------

    private fun mmaStrikingBase():
            TrainingChapterDefinition {

        val chapterId =
            "mma_beginner_striking_base"

        return chapter(
            id = chapterId,
            title = "MMA Striking Base",
            subtitle =
                "Build punches, kicks and movement from a wrestling-ready stance.",
            order = 1,
            lessons = listOf(
                lesson(
                    id = "mma_beginner_striking_base_1",
                    title = "MMA Stance & Straight Punches",
                    subtitle =
                        "Build a stable stance for striking and wrestling.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 100,
                    firstMoves =
                        "Jab - Cross",
                    firstCue =
                        "Keep your stance stable enough to defend a level change.",
                    secondMoves =
                        "Double Jab - Cross",
                    secondCue =
                        "Keep your feet beneath you and avoid overreaching."
                ),
                lesson(
                    id = "mma_beginner_striking_base_2",
                    title = "Straight-Punch Movement",
                    subtitle =
                        "Move forward and backward behind straight punches.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 5,
                    xp = 110,
                    firstMoves =
                        "Step Forward - Jab - Cross - Step Back",
                    firstCue =
                        "Move your feet before reaching with your punches.",
                    secondMoves =
                        "Jab - Step Left - Cross",
                    secondCue =
                        "Keep your stance wide enough to remain ready to wrestle."
                ),
                lesson(
                    id = "mma_beginner_striking_base_3",
                    title = "Punch & Low Kick",
                    subtitle =
                        "Connect basic punches to controlled low kicks.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Jab - Rear Low Kick",
                    firstCue =
                        "Return the rear leg directly into your MMA stance.",
                    secondMoves =
                        "Jab - Cross - Rear Low Kick",
                    secondCue =
                        "Keep your hands high and recover immediately after the kick."
                ),
                lesson(
                    id = "mma_beginner_striking_base_4",
                    title = "Striking Base Checkpoint",
                    subtitle =
                        "Combine straight punches, low kicks and movement.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 6,
                    xp = 140,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back",
                    firstCue =
                        "Complete the kick before leaving the exchange.",
                    secondMoves =
                        "Double Jab - Cross - Step Left - Jab",
                    secondCue =
                        "Keep your stance balanced through the direction change."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 2 — STRIKING INTO WRESTLING
    // ---------------------------------------------------------

    private fun strikingIntoWrestling():
            TrainingChapterDefinition {

        val chapterId =
            "mma_beginner_striking_wrestling"

        return chapter(
            id = chapterId,
            title = "Striking Into Wrestling",
            subtitle =
                "Connect simple striking attacks to wrestling movements.",
            order = 2,
            lessons = listOf(
                lesson(
                    id = "mma_beginner_striking_wrestling_1",
                    title = "Level Change",
                    subtitle =
                        "Learn a controlled wrestling level change.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 5,
                    xp = 110,
                    firstMoves =
                        "Level Change - Return to Stance",
                    firstCue =
                        "Bend through your knees while keeping your chest controlled.",
                    secondMoves =
                        "Jab - Level Change - Return to Stance",
                    secondCue =
                        "Use the jab to hide the level change and keep your eyes forward."
                ),
                lesson(
                    id = "mma_beginner_striking_wrestling_2",
                    title = "Penetration Step",
                    subtitle =
                        "Practise a safe solo takedown-entry movement.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Level Change - Penetration Step - Return to Stance",
                    firstCue =
                        "Use a padded surface and place the lead knee down gently.",
                    secondMoves =
                        "Jab - Level Change - Penetration Step - Return to Stance",
                    secondCue =
                        "Keep your spine controlled and recover without twisting the knee."
                ),
                lesson(
                    id = "mma_beginner_striking_wrestling_3",
                    title = "Punches Into Level Change",
                    subtitle =
                        "Blend punching combinations with wrestling threats.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Jab - Cross - Level Change - Return to Stance",
                    firstCue =
                        "Finish the cross before lowering your level.",
                    secondMoves =
                        "Double Jab - Level Change - Penetration Step - Return to Stance",
                    secondCue =
                        "Keep the entry controlled and rebuild your base before continuing."
                ),
                lesson(
                    id = "mma_beginner_striking_wrestling_4",
                    title = "Strike-Wrestle Checkpoint",
                    subtitle =
                        "Review punches, kicks and shadow takedown entries.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Level Change",
                    firstCue =
                        "Recover the kick before changing levels.",
                    secondMoves =
                        "Jab - Lead Hook - Level Change - Penetration Step - Return to Stance",
                    secondCue =
                        "Keep the hook compact and place the knee down gently."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 3 — TAKEDOWN DEFENCE
    // ---------------------------------------------------------

    private fun takedownDefence():
            TrainingChapterDefinition {

        val chapterId =
            "mma_beginner_takedown_defence"

        return chapter(
            id = chapterId,
            title = "Takedown Defence",
            subtitle =
                "Build sprawls, down blocks and safe standing recovery.",
            order = 3,
            lessons = listOf(
                lesson(
                    id = "mma_beginner_takedown_defence_1",
                    title = "Down Block",
                    subtitle =
                        "Protect your hips against an imaginary takedown entry.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 120,
                    firstMoves =
                        "Down Block - Return to Stance",
                    firstCue =
                        "Drop your hands toward the imaginary shoulders while moving your hips back.",
                    secondMoves =
                        "Jab - Cross - Down Block - Cross",
                    secondCue =
                        "Complete the down block before returning to punches."
                ),
                lesson(
                    id = "mma_beginner_takedown_defence_2",
                    title = "Basic Sprawl",
                    subtitle =
                        "Defend an imaginary shot and return safely to stance.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Sprawl - Return to Stance",
                    firstCue =
                        "Use a padded surface and place your hands down before extending your hips.",
                    secondMoves =
                        "Jab - Cross - Sprawl - Return to Stance",
                    secondCue =
                        "Recover both feet beneath you before standing."
                ),
                lesson(
                    id = "mma_beginner_takedown_defence_3",
                    title = "Sprawl & Circle",
                    subtitle =
                        "Create an angle after defending the takedown.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 140,
                    firstMoves =
                        "Sprawl - Circle Left - Return to Stance",
                    firstCue =
                        "Keep your weight controlled while circling left.",
                    secondMoves =
                        "Sprawl - Circle Right - Return to Stance",
                    secondCue =
                        "Move right with small steps before rebuilding your stance."
                ),
                lesson(
                    id = "mma_beginner_takedown_defence_4",
                    title = "Takedown Defence Checkpoint",
                    subtitle =
                        "Combine down blocks, sprawls and striking returns.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 7,
                    xp = 160,
                    firstMoves =
                        "Jab - Cross - Down Block - Cross - Rear Low Kick",
                    firstCue =
                        "Recover your posture before beginning the counter.",
                    secondMoves =
                        "Jab - Sprawl - Circle Left - Return to Stance - Jab - Cross",
                    secondCue =
                        "Complete the sprawl and circle before returning to strikes."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 4 — GROUND MOVEMENT FOUNDATION
    // ---------------------------------------------------------

    private fun groundMovementFoundation():
            TrainingChapterDefinition {

        val chapterId =
            "mma_beginner_ground_movement"

        return chapter(
            id = chapterId,
            title = "Ground Movement Foundation",
            subtitle =
                "Develop safe solo movement from basic ground positions.",
            order = 4,
            lessons = listOf(
                lesson(
                    id = "mma_beginner_ground_movement_1",
                    title = "Bridge Movement",
                    subtitle =
                        "Build hip power and controlled ground posture.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 125,
                    firstMoves =
                        "Bridge - Reset - Bridge",
                    firstCue =
                        "Drive through your feet and avoid placing pressure on your neck.",
                    secondMoves =
                        "Bridge Left - Reset - Bridge Right",
                    secondCue =
                        "Rotate through your shoulders while keeping the movement controlled."
                ),
                lesson(
                    id = "mma_beginner_ground_movement_2",
                    title = "Hip Escape Left & Right",
                    subtitle =
                        "Create space using clear directional hip escapes.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 6,
                    xp = 135,
                    firstMoves =
                        "Hip Escape Left - Reset",
                    firstCue =
                        "Turn onto your side and move your hips left without pulling your neck.",
                    secondMoves =
                        "Hip Escape Right - Reset",
                    secondCue =
                        "Plant your foot and move your hips right under control."
                ),
                lesson(
                    id = "mma_beginner_ground_movement_3",
                    title = "Technical Stand-Up",
                    subtitle =
                        "Return to standing while protecting yourself.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 145,
                    firstMoves =
                        "Seated Guard - Technical Stand-Up - MMA Stance",
                    firstCue =
                        "Keep one hand protecting your face and stand without crossing your feet.",
                    secondMoves =
                        "Hip Escape Left - Technical Stand-Up - MMA Stance",
                    secondCue =
                        "Create space first and rise into a balanced stance."
                ),
                lesson(
                    id = "mma_beginner_ground_movement_4",
                    title = "Ground Movement Checkpoint",
                    subtitle =
                        "Combine bridges, hip escapes and standing recovery.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 165,
                    firstMoves =
                        "Bridge Left - Hip Escape Right - Technical Stand-Up",
                    firstCue =
                        "Complete each ground movement before beginning the next.",
                    secondMoves =
                        "Bridge Right - Hip Escape Left - Technical Stand-Up - Jab - Cross",
                    secondCue =
                        "Rebuild your MMA stance completely before punching."
                )
            )
        )
    }

    // ---------------------------------------------------------
    // CHAPTER 5 — MMA TRANSITIONS
    // ---------------------------------------------------------

    private fun mmaTransitions():
            TrainingChapterDefinition {

        val chapterId =
            "mma_beginner_transitions"

        return chapter(
            id = chapterId,
            title = "MMA Transitions",
            subtitle =
                "Move safely between striking, wrestling and ground positions.",
            order = 5,
            lessons = listOf(
                lesson(
                    id = "mma_beginner_transitions_1",
                    title = "Sprawl to Striking",
                    subtitle =
                        "Return from takedown defence directly into offense.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 6,
                    xp = 130,
                    firstMoves =
                        "Sprawl - Return to Stance - Jab - Cross",
                    firstCue =
                        "Stand with both feet beneath you before punching.",
                    secondMoves =
                        "Sprawl - Circle Right - Return to Stance - Rear Low Kick",
                    secondCue =
                        "Complete the circle and recover your stance before kicking."
                ),
                lesson(
                    id = "mma_beginner_transitions_2",
                    title = "Sit-Through Left & Right",
                    subtitle =
                        "Develop controlled directional movement from the floor.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 140,
                    firstMoves =
                        "Base Position - Sit-Through Left - Reset",
                    firstCue =
                        "Support your weight through your planted hand and move left under control.",
                    secondMoves =
                        "Base Position - Sit-Through Right - Reset",
                    secondCue =
                        "Keep your hips off the floor while moving right."
                ),
                lesson(
                    id = "mma_beginner_transitions_3",
                    title = "Level Change to Angle",
                    subtitle =
                        "Change levels before moving to a safer attacking angle.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 7,
                    xp = 150,
                    firstMoves =
                        "Jab - Level Change - Step Left - Cross",
                    firstCue =
                        "Return to your normal height before throwing the cross.",
                    secondMoves =
                        "Cross - Level Change - Step Right - Lead Hook",
                    secondCue =
                        "Keep your feet separated as you move right."
                ),
                lesson(
                    id = "mma_beginner_transitions_4",
                    title = "Transition Checkpoint",
                    subtitle =
                        "Review standing, wrestling and ground transitions.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 8,
                    xp = 175,
                    firstMoves =
                        "Jab - Cross - Sprawl - Circle Left - Return to Stance - Rear Low Kick",
                    firstCue =
                        "Complete every transition before beginning the next attack.",
                    secondMoves =
                        "Level Change - Penetration Step - Return to Stance - Sprawl - Technical Stand-Up",
                    secondCue =
                        "Use a padded surface and keep every floor transition controlled."
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
            "mma_beginner_assessment"

        return chapter(
            id = chapterId,
            title = "Beginner Assessment",
            subtitle =
                "Bring your fundamental MMA skills together.",
            order = 6,
            lessons = listOf(
                lesson(
                    id = "mma_beginner_assessment_1",
                    title = "MMA Striking Review",
                    subtitle =
                        "Review punches, low kicks and movement.",
                    chapterId = chapterId,
                    order = 1,
                    minutes = 7,
                    xp = 145,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Step Back",
                    firstCue =
                        "Recover every strike and finish in a wrestling-ready stance.",
                    secondMoves =
                        "Double Jab - Cross - Step Left - Jab",
                    secondCue =
                        "Keep your stance stable through the direction change."
                ),
                lesson(
                    id = "mma_beginner_assessment_2",
                    title = "Wrestling Entry Review",
                    subtitle =
                        "Review level changes and solo takedown entries.",
                    chapterId = chapterId,
                    order = 2,
                    minutes = 7,
                    xp = 155,
                    firstMoves =
                        "Jab - Cross - Level Change - Return to Stance",
                    firstCue =
                        "Finish the punches before lowering your level.",
                    secondMoves =
                        "Double Jab - Level Change - Penetration Step - Return to Stance",
                    secondCue =
                        "Place the knee gently and recover with your posture controlled."
                ),
                lesson(
                    id = "mma_beginner_assessment_3",
                    title = "Defence & Ground Review",
                    subtitle =
                        "Review sprawls, directional ground movement and standing recovery.",
                    chapterId = chapterId,
                    order = 3,
                    minutes = 8,
                    xp = 170,
                    firstMoves =
                        "Sprawl - Circle Right - Return to Stance - Jab - Cross",
                    firstCue =
                        "Complete the defensive movement before returning to offense.",
                    secondMoves =
                        "Bridge Left - Hip Escape Right - Technical Stand-Up",
                    secondCue =
                        "Use a padded surface and keep pressure away from your neck."
                ),
                lesson(
                    id = "mma_beginner_assessment_4",
                    title = "MMA Beginner Final",
                    subtitle =
                        "Complete the MMA Beginner stage.",
                    chapterId = chapterId,
                    order = 4,
                    minutes = 9,
                    xp = 200,
                    firstMoves =
                        "Jab - Cross - Rear Low Kick - Level Change - Step Left - Cross",
                    firstCue =
                        "Recover the kick, change levels and finish from a balanced angle.",
                    secondMoves =
                        "Jab - Sprawl - Circle Right - Return to Stance - Lead Hook - Rear Low Kick - Step Back",
                    secondCue =
                        "Defend, rebuild your stance, counter and leave the exchange safely."
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