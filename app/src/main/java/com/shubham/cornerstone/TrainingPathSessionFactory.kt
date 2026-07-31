package com.shubham.cornerstone

import kotlin.math.ceil

/**
 * The actual SessionScreen configuration for a structured Fight Path lesson.
 */
data class TrainingPathSessionPlan(
    val lessonId: String,
    val combos: List<Combo>,
    val secondsPerCombo: Int,
    val restSeconds: Int
)

/**
 * Converts a curriculum lesson into a real shadowboxing session.
 *
 * Curriculum lessons intentionally contain only a few focused combinations.
 * Instead of showing each combo once, this factory repeats them through
 * multiple training rounds until the lesson reaches its required active time.
 */
object TrainingPathSessionFactory {

    /**
     * One minute of focused work per displayed training round.
     */
    const val SECONDS_PER_COMBO = 60

    /**
     * Structured Fight Path training is available to everyone,
     * so it uses the normal free recovery period.
     */
    const val REST_SECONDS = 15

    fun create(
        lesson: TrainingLessonDefinition
    ): TrainingPathSessionPlan {

        require(lesson.combos.isNotEmpty()) {
            "Structured lesson must contain at least one combo."
        }

        val activeSecondsPerCycle =
            lesson.combos.size *
                    SECONDS_PER_COMBO

        val requiredCycles =
            ceil(
                lesson.requiredActiveSeconds
                    .toDouble() /
                        activeSecondsPerCycle
                            .toDouble()
            )
                .toInt()
                .coerceAtLeast(1)

        val sessionCombos =
            buildList {

                repeat(requiredCycles) {
                        cycleIndex ->

                    lesson.combos.forEach {
                            lessonCombo ->

                        val comboNumber =
                            size + 1

                        val phase =
                            phaseForCycle(
                                cycleIndex =
                                    cycleIndex,
                                totalCycles =
                                    requiredCycles
                            )

                        add(
                            Combo(
                                number =
                                    comboNumber,
                                phase =
                                    phase,
                                moves =
                                    lessonCombo.moves,
                                cue =
                                    lessonCombo.cue
                            )
                        )
                    }
                }
            }

        return TrainingPathSessionPlan(
            lessonId =
                lesson.id,
            combos =
                sessionCombos,
            secondsPerCombo =
                SECONDS_PER_COMBO,
            restSeconds =
                REST_SECONDS
        )
    }

    private fun phaseForCycle(
        cycleIndex: Int,
        totalCycles: Int
    ): String {

        if (totalCycles <= 1) {
            return "WORK"
        }

        return when (cycleIndex) {

            0 -> {
                "LEARN"
            }

            totalCycles - 1 -> {
                "FINISH"
            }

            else -> {
                "DRILL"
            }
        }
    }
}