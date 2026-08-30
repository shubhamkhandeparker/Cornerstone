package com.shubham.cornerstone

enum class ConditioningBlockType(
    val displayName: String
) {
    WARM_UP(
        "Warm-up"
    ),

    BETWEEN_ROUNDS(
        "Conditioning"
    ),

    FINISHER(
        "Finisher"
    ),

    CONDITIONING_ROUND(
        "Conditioning Round"
    )
}

data class PlannedConditioningBlock(
    val type: ConditioningBlockType,
    val exercise: ConditioningExerciseDefinition,
    val workSeconds: Int,
    val restSeconds: Int,
    val rounds: Int
) {
    init {
        require(workSeconds > 0) {
            "Conditioning work time must be greater than zero."
        }

        require(restSeconds >= 0) {
            "Conditioning rest time cannot be negative."
        }

        require(rounds > 0) {
            "Conditioning block must have at least one round."
        }
    }

    val estimatedTotalSeconds: Int
        get() {
            val work =
                workSeconds * rounds

            val rest =
                if (rounds <= 1) {
                    0
                } else {
                    restSeconds *
                            (rounds - 1)
                }

            return work + rest
        }
}

data class ConditioningSessionPlan(
    val mode: ConditioningMode,
    val warmUpBlocks:
    List<PlannedConditioningBlock>,
    val betweenRoundBlocks:
    List<PlannedConditioningBlock>,
    val finisherBlocks:
    List<PlannedConditioningBlock>,
    val conditioningOnlyBlocks:
    List<PlannedConditioningBlock>
) {

    /**
     * The order changes depending on the selected mode.
     *
     * Technique + Conditioning:
     *
     * Warm-up
     * → between-technique conditioning
     * → finisher
     *
     * Conditioning Only:
     *
     * Warm-up
     * → conditioning circuit
     * → finisher
     */
    val allBlocks:
            List<PlannedConditioningBlock>
        get() {
            return when (mode) {

                ConditioningMode
                    .TECHNIQUE_ONLY -> {

                    emptyList()
                }

                ConditioningMode
                    .TECHNIQUE_PLUS_CONDITIONING -> {

                    warmUpBlocks +
                            betweenRoundBlocks +
                            finisherBlocks
                }

                ConditioningMode
                    .CONDITIONING_ONLY -> {

                    warmUpBlocks +
                            conditioningOnlyBlocks +
                            finisherBlocks
                }
            }
        }

    val estimatedConditioningSeconds: Int
        get() {
            return allBlocks
                .sumOf {
                    it.estimatedTotalSeconds
                }
        }

    val hasConditioning: Boolean
        get() {
            return allBlocks.isNotEmpty()
        }
}

object ConditioningSessionPlanner {

    fun create(
        mode: ConditioningMode,
        completedFightPathSessions: Int,
        sessionSeed: Int =
            completedFightPathSessions
    ): ConditioningSessionPlan {

        val safeCompletedSessions =
            completedFightPathSessions
                .coerceAtLeast(0)

        val safeSeed =
            sessionSeed
                .coerceAtLeast(0)

        return when (mode) {

            ConditioningMode
                .TECHNIQUE_ONLY -> {

                emptyPlan(
                    mode =
                        mode
                )
            }

            ConditioningMode
                .TECHNIQUE_PLUS_CONDITIONING -> {

                createTechniquePlusConditioningPlan(
                    completedFightPathSessions =
                        safeCompletedSessions,
                    sessionSeed =
                        safeSeed
                )
            }

            ConditioningMode
                .CONDITIONING_ONLY -> {

                createConditioningOnlyPlan(
                    completedFightPathSessions =
                        safeCompletedSessions,
                    sessionSeed =
                        safeSeed
                )
            }
        }
    }

    // ---------------------------------------------------------
    // TECHNIQUE + CONDITIONING
    // ---------------------------------------------------------

    private fun createTechniquePlusConditioningPlan(
        completedFightPathSessions: Int,
        sessionSeed: Int
    ): ConditioningSessionPlan {

        val warmupPool =
            ConditioningLibrary
                .unlockedExercises(
                    completedFightPathSessions =
                        completedFightPathSessions
                )
                .filter {
                        exercise ->

                    exercise.category ==
                            ConditioningCategory.MOBILITY ||
                            exercise.category ==
                            ConditioningCategory.AGILITY
                }

        val mainPool =
            ConditioningLibrary
                .techniqueConditioningPool(
                    completedFightPathSessions =
                        completedFightPathSessions
                )

        val finisherPool =
            ConditioningLibrary
                .finisherPool(
                    completedFightPathSessions =
                        completedFightPathSessions
                )

        val warmupExercise =
            rotatedPick(
                items =
                    warmupPool,
                seed =
                    sessionSeed
            )

        val firstMainExercise =
            rotatedPick(
                items =
                    mainPool,
                seed =
                    sessionSeed + 1
            )

        val secondMainExercise =
            rotatedPickDifferent(
                items =
                    mainPool,
                seed =
                    sessionSeed + 2,
                excludedId =
                    firstMainExercise
                        ?.id
            )

        val finisherExercise =
            rotatedPick(
                items =
                    finisherPool,
                seed =
                    sessionSeed + 3
            )

        val warmupBlocks =
            if (
                warmupExercise != null
            ) {
                listOf(
                    createWarmupBlock(
                        exercise =
                            warmupExercise,
                        completedFightPathSessions =
                            completedFightPathSessions
                    )
                )
            } else {
                emptyList()
            }

        val mainBlockCount =
            techniqueConditioningBlockCount(
                completedFightPathSessions =
                    completedFightPathSessions
            )

        val selectedMainExercises =
            listOfNotNull(
                firstMainExercise,
                secondMainExercise
            )
                .take(
                    mainBlockCount
                )

        val betweenBlocks =
            selectedMainExercises
                .mapIndexed {
                        index,
                        exercise ->

                    createBetweenRoundBlock(
                        exercise =
                            exercise,
                        completedFightPathSessions =
                            completedFightPathSessions,
                        blockIndex =
                            index
                    )
                }

        val finisherBlocks =
            if (
                finisherExercise != null
            ) {
                listOf(
                    createFinisherBlock(
                        exercise =
                            finisherExercise,
                        completedFightPathSessions =
                            completedFightPathSessions
                    )
                )
            } else {
                emptyList()
            }

        return ConditioningSessionPlan(
            mode =
                ConditioningMode
                    .TECHNIQUE_PLUS_CONDITIONING,
            warmUpBlocks =
                warmupBlocks,
            betweenRoundBlocks =
                betweenBlocks,
            finisherBlocks =
                finisherBlocks,
            conditioningOnlyBlocks =
                emptyList()
        )
    }

    // ---------------------------------------------------------
    // CONDITIONING ONLY
    // ---------------------------------------------------------

    private fun createConditioningOnlyPlan(
        completedFightPathSessions: Int,
        sessionSeed: Int
    ): ConditioningSessionPlan {

        val unlocked =
            ConditioningLibrary
                .unlockedExercises(
                    completedFightPathSessions =
                        completedFightPathSessions
                )

        val mobilityPool =
            unlocked.filter {
                    exercise ->

                exercise.category ==
                        ConditioningCategory.MOBILITY
            }

        val workPool =
            unlocked.filter {
                    exercise ->

                exercise.category in
                        setOf(
                            ConditioningCategory.CARDIO,
                            ConditioningCategory.STRENGTH,
                            ConditioningCategory.CORE,
                            ConditioningCategory.AGILITY,
                            ConditioningCategory.ENDURANCE
                        )
            }

        val finisherPool =
            ConditioningLibrary
                .finisherPool(
                    completedFightPathSessions =
                        completedFightPathSessions
                )

        val warmupExercise =
            rotatedPick(
                items =
                    mobilityPool,
                seed =
                    sessionSeed
            )

        val conditioningExerciseCount =
            conditioningOnlyExerciseCount(
                completedFightPathSessions =
                    completedFightPathSessions
            )

        val selectedWorkExercises =
            rotatingUniqueSelection(
                items =
                    workPool,
                count =
                    conditioningExerciseCount,
                seed =
                    sessionSeed + 1
            )

        val finisherExercise =
            rotatedPickDifferent(
                items =
                    finisherPool,
                seed =
                    sessionSeed + 10,
                excludedId =
                    selectedWorkExercises
                        .lastOrNull()
                        ?.id
            )

        val warmupBlocks =
            if (
                warmupExercise != null
            ) {
                listOf(
                    createWarmupBlock(
                        exercise =
                            warmupExercise,
                        completedFightPathSessions =
                            completedFightPathSessions
                    )
                )
            } else {
                emptyList()
            }

        val circuitBlocks =
            selectedWorkExercises
                .mapIndexed {
                        index,
                        exercise ->

                    createConditioningOnlyBlock(
                        exercise =
                            exercise,
                        completedFightPathSessions =
                            completedFightPathSessions,
                        blockIndex =
                            index
                    )
                }

        val finisherBlocks =
            if (
                finisherExercise != null
            ) {
                listOf(
                    createFinisherBlock(
                        exercise =
                            finisherExercise,
                        completedFightPathSessions =
                            completedFightPathSessions
                    )
                )
            } else {
                emptyList()
            }

        return ConditioningSessionPlan(
            mode =
                ConditioningMode
                    .CONDITIONING_ONLY,
            warmUpBlocks =
                warmupBlocks,
            betweenRoundBlocks =
                emptyList(),
            finisherBlocks =
                finisherBlocks,
            conditioningOnlyBlocks =
                circuitBlocks
        )
    }

    // ---------------------------------------------------------
    // BLOCK BUILDERS
    // ---------------------------------------------------------

    private fun createWarmupBlock(
        exercise:
        ConditioningExerciseDefinition,
        completedFightPathSessions: Int
    ): PlannedConditioningBlock {

        val workSeconds =
            when {
                completedFightPathSessions >=
                        66 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        40
                    )
                }

                completedFightPathSessions >=
                        24 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        35
                    )
                }

                else -> {
                    exercise.defaultWorkSeconds
                }
            }

        return PlannedConditioningBlock(
            type =
                ConditioningBlockType
                    .WARM_UP,
            exercise =
                exercise,
            workSeconds =
                workSeconds,
            restSeconds =
                10,
            rounds =
                1
        )
    }

    private fun createBetweenRoundBlock(
        exercise:
        ConditioningExerciseDefinition,
        completedFightPathSessions: Int,
        blockIndex: Int
    ): PlannedConditioningBlock {

        val rounds =
            when {
                completedFightPathSessions >=
                        66 -> {
                    2
                }

                completedFightPathSessions >=
                        36 &&
                        blockIndex == 0 -> {
                    2
                }

                else -> {
                    1
                }
            }

        val workSeconds =
            when {
                completedFightPathSessions >=
                        66 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        45
                    )
                }

                completedFightPathSessions >=
                        24 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        40
                    )
                }

                completedFightPathSessions >=
                        8 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        30
                    )
                }

                else -> {
                    minOf(
                        exercise.defaultWorkSeconds,
                        30
                    )
                }
            }

        return PlannedConditioningBlock(
            type =
                ConditioningBlockType
                    .BETWEEN_ROUNDS,
            exercise =
                exercise,
            workSeconds =
                workSeconds,
            restSeconds =
                exercise
                    .defaultRestSeconds,
            rounds =
                rounds
        )
    }

    private fun createFinisherBlock(
        exercise:
        ConditioningExerciseDefinition,
        completedFightPathSessions: Int
    ): PlannedConditioningBlock {

        val rounds =
            when {
                completedFightPathSessions >=
                        66 -> {
                    3
                }

                completedFightPathSessions >=
                        36 -> {
                    2
                }

                else -> {
                    1
                }
            }

        val workSeconds =
            when {
                completedFightPathSessions >=
                        66 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        60
                    )
                }

                completedFightPathSessions >=
                        24 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        45
                    )
                }

                completedFightPathSessions >=
                        8 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        35
                    )
                }

                else -> {
                    minOf(
                        exercise.defaultWorkSeconds,
                        30
                    )
                }
            }

        return PlannedConditioningBlock(
            type =
                ConditioningBlockType
                    .FINISHER,
            exercise =
                exercise,
            workSeconds =
                workSeconds,
            restSeconds =
                exercise
                    .defaultRestSeconds,
            rounds =
                rounds
        )
    }

    private fun createConditioningOnlyBlock(
        exercise:
        ConditioningExerciseDefinition,
        completedFightPathSessions: Int,
        blockIndex: Int
    ): PlannedConditioningBlock {

        val rounds =
            when {
                completedFightPathSessions >=
                        66 -> {
                    3
                }

                completedFightPathSessions >=
                        36 -> {
                    2
                }

                completedFightPathSessions >=
                        24 &&
                        blockIndex < 2 -> {
                    2
                }

                else -> {
                    1
                }
            }

        val workSeconds =
            when {
                completedFightPathSessions >=
                        66 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        50
                    )
                }

                completedFightPathSessions >=
                        36 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        45
                    )
                }

                completedFightPathSessions >=
                        24 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        40
                    )
                }

                completedFightPathSessions >=
                        8 -> {

                    maxOf(
                        exercise.defaultWorkSeconds,
                        35
                    )
                }

                else -> {
                    exercise.defaultWorkSeconds
                }
            }

        return PlannedConditioningBlock(
            type =
                ConditioningBlockType
                    .CONDITIONING_ROUND,
            exercise =
                exercise,
            workSeconds =
                workSeconds,
            restSeconds =
                exercise
                    .defaultRestSeconds,
            rounds =
                rounds
        )
    }

    // ---------------------------------------------------------
    // PROGRESSION
    // ---------------------------------------------------------

    private fun techniqueConditioningBlockCount(
        completedFightPathSessions: Int
    ): Int {

        return if (
            completedFightPathSessions >=
            24
        ) {
            2
        } else {
            1
        }
    }

    private fun conditioningOnlyExerciseCount(
        completedFightPathSessions: Int
    ): Int {

        return when {
            completedFightPathSessions >=
                    66 -> {
                6
            }

            completedFightPathSessions >=
                    36 -> {
                6
            }

            completedFightPathSessions >=
                    24 -> {
                5
            }

            completedFightPathSessions >=
                    8 -> {
                5
            }

            else -> {
                4
            }
        }
    }

    // ---------------------------------------------------------
    // ROTATION
    // ---------------------------------------------------------

    private fun <T> rotatedPick(
        items: List<T>,
        seed: Int
    ): T? {

        if (
            items.isEmpty()
        ) {
            return null
        }

        val index =
            seed.floorMod(
                items.size
            )

        return items[
            index
        ]
    }

    private fun rotatedPickDifferent(
        items:
        List<ConditioningExerciseDefinition>,
        seed: Int,
        excludedId: String?
    ): ConditioningExerciseDefinition? {

        if (
            items.isEmpty()
        ) {
            return null
        }

        if (
            excludedId == null ||
            items.size == 1
        ) {
            return rotatedPick(
                items =
                    items,
                seed =
                    seed
            )
        }

        val startIndex =
            seed.floorMod(
                items.size
            )

        repeat(
            items.size
        ) {
                offset ->

            val candidate =
                items[
                    (
                            startIndex +
                                    offset
                            )
                        .floorMod(
                            items.size
                        )
                ]

            if (
                candidate.id !=
                excludedId
            ) {
                return candidate
            }
        }

        return items[
            startIndex
        ]
    }

    private fun rotatingUniqueSelection(
        items:
        List<ConditioningExerciseDefinition>,
        count: Int,
        seed: Int
    ): List<ConditioningExerciseDefinition> {

        if (
            items.isEmpty() ||
            count <= 0
        ) {
            return emptyList()
        }

        val safeCount =
            count.coerceAtMost(
                items.size
            )

        val startIndex =
            seed.floorMod(
                items.size
            )

        val selected =
            mutableListOf<
                    ConditioningExerciseDefinition
                    >()

        var offset =
            0

        while (
            selected.size <
            safeCount &&
            offset <
            items.size * 2
        ) {
            val candidate =
                items[
                    (
                            startIndex +
                                    offset
                            )
                        .floorMod(
                            items.size
                        )
                ]

            if (
                selected.none {
                        existing ->

                    existing.id ==
                            candidate.id
                }
            ) {
                selected.add(
                    candidate
                )
            }

            offset++
        }

        return selected
    }

    // ---------------------------------------------------------
    // EMPTY
    // ---------------------------------------------------------

    private fun emptyPlan(
        mode:
        ConditioningMode
    ): ConditioningSessionPlan {

        return ConditioningSessionPlan(
            mode =
                mode,
            warmUpBlocks =
                emptyList(),
            betweenRoundBlocks =
                emptyList(),
            finisherBlocks =
                emptyList(),
            conditioningOnlyBlocks =
                emptyList()
        )
    }

    // ---------------------------------------------------------
    // HELPER
    // ---------------------------------------------------------

    private fun Int.floorMod(
        other: Int
    ): Int {

        if (
            other <= 0
        ) {
            return 0
        }

        return (
                (
                        this %
                                other
                        ) +
                        other
                ) %
                other
    }
}