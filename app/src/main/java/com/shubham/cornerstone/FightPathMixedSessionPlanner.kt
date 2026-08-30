package com.shubham.cornerstone

/**
 * One stage inside a complete Fight Path training session.
 *
 * A mixed Fight Path session can contain:
 *
 * Conditioning warm-up
 * Technique rounds
 * Conditioning between technique sections
 * More technique rounds
 * Final conditioning finisher
 */
sealed interface FightPathMixedStage {

    val order: Int

    data class Technique(
        override val order: Int,
        val combos: List<Combo>
    ) : FightPathMixedStage

    data class Conditioning(
        override val order: Int,
        val block: PlannedConditioningBlock
    ) : FightPathMixedStage
}

/**
 * Complete ordered Fight Path session.
 */
data class FightPathMixedSessionPlan(
    val mode: ConditioningMode,
    val stages: List<FightPathMixedStage>
) {

    val techniqueStages:
            List<FightPathMixedStage.Technique>
        get() {
            return stages
                .filterIsInstance<
                        FightPathMixedStage.Technique
                        >()
        }

    val conditioningStages:
            List<FightPathMixedStage.Conditioning>
        get() {
            return stages
                .filterIsInstance<
                        FightPathMixedStage.Conditioning
                        >()
        }

    val totalTechniqueRounds: Int
        get() {
            return techniqueStages
                .sumOf {
                    it.combos.size
                }
        }

    val totalConditioningBlocks: Int
        get() {
            return conditioningStages.size
        }
}

/**
 * Combines a structured Fight Path technique session
 * with Cornerstone's conditioning plan.
 *
 * Important:
 *
 * Technique Only:
 * Technique
 *
 * Technique + Conditioning:
 * Warm-up
 * → Technique section
 * → Conditioning
 * → Technique section
 * → Conditioning if unlocked
 * → Technique section
 * → Finisher
 *
 * Conditioning Only is handled by ConditioningWorkoutScreen,
 * because there are no technique rounds to interleave.
 */
object FightPathMixedSessionPlanner {

    fun create(
        mode: ConditioningMode,
        techniqueCombos: List<Combo>,
        conditioningPlan: ConditioningSessionPlan
    ): FightPathMixedSessionPlan {

        return when (mode) {

            ConditioningMode.TECHNIQUE_ONLY -> {
                techniqueOnly(
                    techniqueCombos =
                        techniqueCombos
                )
            }

            ConditioningMode.TECHNIQUE_PLUS_CONDITIONING -> {
                techniquePlusConditioning(
                    techniqueCombos =
                        techniqueCombos,
                    conditioningPlan =
                        conditioningPlan
                )
            }

            ConditioningMode.CONDITIONING_ONLY -> {
                FightPathMixedSessionPlan(
                    mode =
                        ConditioningMode
                            .CONDITIONING_ONLY,
                    stages =
                        conditioningPlan
                            .allBlocks
                            .mapIndexed {
                                    index,
                                    block ->

                                FightPathMixedStage
                                    .Conditioning(
                                        order =
                                            index,
                                        block =
                                            block
                                    )
                            }
                )
            }
        }
    }

    // ---------------------------------------------------------
    // TECHNIQUE ONLY
    // ---------------------------------------------------------

    private fun techniqueOnly(
        techniqueCombos: List<Combo>
    ): FightPathMixedSessionPlan {

        if (
            techniqueCombos.isEmpty()
        ) {
            return FightPathMixedSessionPlan(
                mode =
                    ConditioningMode
                        .TECHNIQUE_ONLY,
                stages =
                    emptyList()
            )
        }

        return FightPathMixedSessionPlan(
            mode =
                ConditioningMode
                    .TECHNIQUE_ONLY,
            stages =
                listOf(
                    FightPathMixedStage
                        .Technique(
                            order = 0,
                            combos =
                                techniqueCombos
                        )
                )
        )
    }

    // ---------------------------------------------------------
    // TECHNIQUE + CONDITIONING
    // ---------------------------------------------------------

    private fun techniquePlusConditioning(
        techniqueCombos: List<Combo>,
        conditioningPlan: ConditioningSessionPlan
    ): FightPathMixedSessionPlan {

        if (
            techniqueCombos.isEmpty()
        ) {
            return FightPathMixedSessionPlan(
                mode =
                    ConditioningMode
                        .TECHNIQUE_PLUS_CONDITIONING,
                stages =
                    conditioningPlan
                        .allBlocks
                        .mapIndexed {
                                index,
                                block ->

                            FightPathMixedStage
                                .Conditioning(
                                    order =
                                        index,
                                    block =
                                        block
                                )
                        }
            )
        }

        val stages =
            mutableListOf<
                    FightPathMixedStage
                    >()

        var order =
            0

        // -----------------------------------------------------
        // WARM-UP FIRST
        // -----------------------------------------------------

        conditioningPlan
            .warmUpBlocks
            .forEach {
                    block ->

                stages.add(
                    FightPathMixedStage
                        .Conditioning(
                            order =
                                order++,
                            block =
                                block
                        )
                )
            }

        // -----------------------------------------------------
        // SPLIT TECHNIQUE BASED ON NUMBER OF CONDITIONING BLOCKS
        // -----------------------------------------------------

        val betweenBlocks =
            conditioningPlan
                .betweenRoundBlocks

        val techniqueSectionsNeeded =
            (
                    betweenBlocks.size +
                            1
                    )
                .coerceAtLeast(1)

        val techniqueSections =
            splitTechniqueCombos(
                combos =
                    techniqueCombos,
                sectionCount =
                    techniqueSectionsNeeded
            )

        techniqueSections
            .forEachIndexed {
                    index,
                    section ->

                if (
                    section.isNotEmpty()
                ) {
                    stages.add(
                        FightPathMixedStage
                            .Technique(
                                order =
                                    order++,
                                combos =
                                    section
                            )
                    )
                }

                val conditioningBlock =
                    betweenBlocks
                        .getOrNull(
                            index
                        )

                if (
                    conditioningBlock !=
                    null
                ) {
                    stages.add(
                        FightPathMixedStage
                            .Conditioning(
                                order =
                                    order++,
                                block =
                                    conditioningBlock
                            )
                    )
                }
            }

        // -----------------------------------------------------
        // FINISHER LAST
        // -----------------------------------------------------

        conditioningPlan
            .finisherBlocks
            .forEach {
                    block ->

                stages.add(
                    FightPathMixedStage
                        .Conditioning(
                            order =
                                order++,
                            block =
                                block
                        )
                )
            }

        return FightPathMixedSessionPlan(
            mode =
                ConditioningMode
                    .TECHNIQUE_PLUS_CONDITIONING,
            stages =
                stages
                    .sortedBy {
                        it.order
                    }
        )
    }

    // ---------------------------------------------------------
    // TECHNIQUE SPLITTING
    // ---------------------------------------------------------

    /**
     * Splits technique rounds as evenly as possible.
     *
     * Example:
     *
     * 6 rounds + 1 conditioning block
     *
     * Section 1 = rounds 1,2,3
     * Conditioning
     * Section 2 = rounds 4,5,6
     *
     *
     * 6 rounds + 2 conditioning blocks
     *
     * Section 1 = rounds 1,2
     * Conditioning
     * Section 2 = rounds 3,4
     * Conditioning
     * Section 3 = rounds 5,6
     */
    private fun splitTechniqueCombos(
        combos: List<Combo>,
        sectionCount: Int
    ): List<List<Combo>> {

        if (
            combos.isEmpty()
        ) {
            return emptyList()
        }

        val safeSectionCount =
            sectionCount
                .coerceIn(
                    minimumValue = 1,
                    maximumValue =
                        combos.size
                )

        if (
            safeSectionCount == 1
        ) {
            return listOf(
                combos
            )
        }

        val baseSize =
            combos.size /
                    safeSectionCount

        val remainder =
            combos.size %
                    safeSectionCount

        val sections =
            mutableListOf<
                    List<Combo>
                    >()

        var currentIndex =
            0

        repeat(
            safeSectionCount
        ) {
                sectionIndex ->

            val extra =
                if (
                    sectionIndex <
                    remainder
                ) {
                    1
                } else {
                    0
                }

            val sectionSize =
                baseSize +
                        extra

            val endIndex =
                (
                        currentIndex +
                                sectionSize
                        )
                    .coerceAtMost(
                        combos.size
                    )

            val section =
                combos.subList(
                    currentIndex,
                    endIndex
                )

            sections.add(
                section
            )

            currentIndex =
                endIndex
        }

        return sections
    }
}