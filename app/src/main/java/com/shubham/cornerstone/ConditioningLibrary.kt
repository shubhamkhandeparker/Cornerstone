package com.shubham.cornerstone

/**
 * How the fighter wants to train.
 */
enum class ConditioningMode(
    val displayName: String
) {
    TECHNIQUE_ONLY(
        "Technique Only"
    ),

    TECHNIQUE_PLUS_CONDITIONING(
        "Technique + Conditioning"
    ),

    CONDITIONING_ONLY(
        "Conditioning Only"
    )
}

/**
 * Main conditioning libraries.
 */
enum class ConditioningCategory(
    val displayName: String
) {
    CARDIO(
        "Cardio"
    ),

    STRENGTH(
        "Strength"
    ),

    CORE(
        "Core"
    ),

    MOBILITY(
        "Mobility"
    ),

    AGILITY(
        "Agility"
    ),

    ENDURANCE(
        "Endurance"
    )
}

/**
 * Exercise difficulty.
 *
 * This is independent from the fighter's exact Fight Path level,
 * but unlocking is connected to completed Fight Path work.
 */
enum class ConditioningDifficulty(
    val displayName: String,
    val rank: Int
) {
    BEGINNER(
        "Beginner",
        0
    ),

    INTERMEDIATE(
        "Intermediate",
        1
    ),

    ADVANCED(
        "Advanced",
        2
    )
}

/**
 * One conditioning exercise available inside Cornerstone.
 *
 * All exercises in the initial library require no equipment.
 */
data class ConditioningExerciseDefinition(
    val id: String,
    val title: String,
    val category: ConditioningCategory,
    val difficulty: ConditioningDifficulty,
    val defaultWorkSeconds: Int,
    val defaultRestSeconds: Int,
    val instructions: String,
    val coachingCue: String,
    val lowImpactAlternative: String?,
    val unlockAfterFightPathSessions: Int,
    val requiresEquipment: Boolean = false
) {
    init {
        require(
            id.isNotBlank()
        ) {
            "Conditioning exercise ID cannot be blank."
        }

        require(
            title.isNotBlank()
        ) {
            "Conditioning exercise title cannot be blank."
        }

        require(
            defaultWorkSeconds > 0
        ) {
            "Conditioning work time must be greater than zero."
        }

        require(
            defaultRestSeconds >= 0
        ) {
            "Conditioning rest time cannot be negative."
        }

        require(
            unlockAfterFightPathSessions >= 0
        ) {
            "Unlock requirement cannot be negative."
        }
    }
}

/**
 * Cornerstone Conditioning Library.
 *
 * Initial foundation:
 *
 * 6 categories
 * ×
 * 6 exercises each
 * =
 * 36 no-equipment conditioning exercises.
 *
 * This architecture is intentionally expandable.
 *
 * We can later add hundreds/thousands of exercises without
 * changing the rest of the conditioning engine.
 */
object ConditioningLibrary {

    val exercises:
            List<ConditioningExerciseDefinition> =
        listOf(

            // =================================================
            // CARDIO
            // =================================================

            exercise(
                id =
                    "cardio_march_high_knees",
                title =
                    "Marching High Knees",
                category =
                    ConditioningCategory.CARDIO,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "March quickly in place while raising each knee toward hip height.",
                cue =
                    "Stay tall and keep your hands active.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "cardio_high_knees",
                title =
                    "High Knees",
                category =
                    ConditioningCategory.CARDIO,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Run in place while driving the knees upward with quick controlled steps.",
                cue =
                    "Stay light on your feet and keep breathing.",
                alternative =
                    "Marching High Knees",
                unlockAfterSessions = 4
            ),

            exercise(
                id =
                    "cardio_mountain_climbers",
                title =
                    "Mountain Climbers",
                category =
                    ConditioningCategory.CARDIO,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Start in a strong plank and alternate driving each knee toward the chest.",
                cue =
                    "Keep your hips controlled instead of bouncing.",
                alternative =
                    "Standing Knee Drives",
                unlockAfterSessions = 8
            ),

            exercise(
                id =
                    "cardio_fast_feet",
                title =
                    "Fast Feet",
                category =
                    ConditioningCategory.CARDIO,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Stay in a fighting stance and make very quick short steps in place.",
                cue =
                    "Fast feet, relaxed shoulders.",
                alternative =
                    "Controlled Stance Steps",
                unlockAfterSessions = 24
            ),

            exercise(
                id =
                    "cardio_squat_thrust",
                title =
                    "Squat Thrust",
                category =
                    ConditioningCategory.CARDIO,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Squat down, place your hands on the floor, jump or step your feet back, then return to standing.",
                cue =
                    "Stay controlled when your feet return underneath you.",
                alternative =
                    "Step-Back Squat Thrust",
                unlockAfterSessions = 36
            ),

            exercise(
                id =
                    "cardio_burpees",
                title =
                    "Burpees",
                category =
                    ConditioningCategory.CARDIO,
                difficulty =
                    ConditioningDifficulty.ADVANCED,
                workSeconds = 45,
                restSeconds = 20,
                instructions =
                    "Move from standing to the floor, return your feet underneath you and finish by standing or jumping.",
                cue =
                    "Keep moving but do not sacrifice control.",
                alternative =
                    "Step-Back Burpee",
                unlockAfterSessions = 66
            ),

            // =================================================
            // STRENGTH
            // =================================================

            exercise(
                id =
                    "strength_bodyweight_squat",
                title =
                    "Bodyweight Squats",
                category =
                    ConditioningCategory.STRENGTH,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Sit the hips down and back while keeping the chest controlled, then stand tall.",
                cue =
                    "Keep your knees tracking naturally over your feet.",
                alternative =
                    "Half Squats",
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "strength_wall_pushup",
                title =
                    "Wall Push-Ups",
                category =
                    ConditioningCategory.STRENGTH,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Place your hands against a wall, lower your chest toward it and press yourself away.",
                cue =
                    "Keep your body in one straight line.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "strength_reverse_lunges",
                title =
                    "Reverse Lunges",
                category =
                    ConditioningCategory.STRENGTH,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 35,
                restSeconds = 15,
                instructions =
                    "Step one leg backward, lower under control and drive through the front leg to return.",
                cue =
                    "Stay tall and control every step.",
                alternative =
                    "Supported Reverse Lunges",
                unlockAfterSessions = 8
            ),

            exercise(
                id =
                    "strength_pushups",
                title =
                    "Push-Ups",
                category =
                    ConditioningCategory.STRENGTH,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 35,
                restSeconds = 20,
                instructions =
                    "Lower your chest toward the floor from a strong plank position and press back up.",
                cue =
                    "Keep your hips and shoulders moving together.",
                alternative =
                    "Knee Push-Ups",
                unlockAfterSessions = 24
            ),

            exercise(
                id =
                    "strength_split_squats",
                title =
                    "Split Squats",
                category =
                    ConditioningCategory.STRENGTH,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Hold a split stance and repeatedly lower the back knee toward the floor before standing.",
                cue =
                    "Stay balanced and keep pressure through the front foot.",
                alternative =
                    "Supported Split Squats",
                unlockAfterSessions = 36
            ),

            exercise(
                id =
                    "strength_explosive_pushups",
                title =
                    "Explosive Push-Ups",
                category =
                    ConditioningCategory.STRENGTH,
                difficulty =
                    ConditioningDifficulty.ADVANCED,
                workSeconds = 30,
                restSeconds = 25,
                instructions =
                    "Push forcefully enough for the hands to briefly become light or leave the floor.",
                cue =
                    "Explode up and land softly through the hands.",
                alternative =
                    "Fast Push-Ups",
                unlockAfterSessions = 66
            ),

            // =================================================
            // CORE
            // =================================================

            exercise(
                id =
                    "core_dead_bug",
                title =
                    "Dead Bug",
                category =
                    ConditioningCategory.CORE,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Lie on your back and slowly extend opposite arm and leg while keeping your torso controlled.",
                cue =
                    "Keep your lower back controlled against the floor.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "core_knee_plank",
                title =
                    "Knee Plank",
                category =
                    ConditioningCategory.CORE,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Support yourself on your forearms and knees while keeping a straight line from shoulders to knees.",
                cue =
                    "Brace your stomach and keep breathing.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "core_plank",
                title =
                    "Plank",
                category =
                    ConditioningCategory.CORE,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 35,
                restSeconds = 15,
                instructions =
                    "Hold a strong forearm plank with the hips aligned between shoulders and heels.",
                cue =
                    "Brace as if preparing to absorb a body shot.",
                alternative =
                    "Knee Plank",
                unlockAfterSessions = 8
            ),

            exercise(
                id =
                    "core_plank_shoulder_taps",
                title =
                    "Plank Shoulder Taps",
                category =
                    ConditioningCategory.CORE,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "From a high plank, alternate touching the opposite shoulder without allowing the hips to rotate heavily.",
                cue =
                    "Keep the torso quiet while the hands move.",
                alternative =
                    "Wide-Stance Shoulder Taps",
                unlockAfterSessions = 24
            ),

            exercise(
                id =
                    "core_bicycle_crunch",
                title =
                    "Bicycle Crunches",
                category =
                    ConditioningCategory.CORE,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Alternate bringing one knee toward the opposite elbow while extending the other leg.",
                cue =
                    "Rotate through the torso instead of pulling your neck.",
                alternative =
                    "Slow Alternating Knee Crunch",
                unlockAfterSessions = 36
            ),

            exercise(
                id =
                    "core_hollow_hold",
                title =
                    "Hollow Hold",
                category =
                    ConditioningCategory.CORE,
                difficulty =
                    ConditioningDifficulty.ADVANCED,
                workSeconds = 35,
                restSeconds = 25,
                instructions =
                    "Lie on your back, brace your core and hold the shoulders and legs slightly off the floor.",
                cue =
                    "Keep your lower back pressed down.",
                alternative =
                    "Bent-Knee Hollow Hold",
                unlockAfterSessions = 66
            ),

            // =================================================
            // MOBILITY
            // =================================================

            exercise(
                id =
                    "mobility_ankle_rocks",
                title =
                    "Ankle Rocks",
                category =
                    ConditioningCategory.MOBILITY,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 10,
                instructions =
                    "Shift your knee gently forward over the toes while keeping the heel controlled on the floor.",
                cue =
                    "Use smooth motion, not bouncing.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "mobility_hip_openers",
                title =
                    "Standing Hip Openers",
                category =
                    ConditioningCategory.MOBILITY,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 10,
                instructions =
                    "Lift one knee and rotate it outward through a comfortable controlled range.",
                cue =
                    "Keep your torso tall while the hip moves.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "mobility_worlds_greatest",
                title =
                    "Lunge & Rotation",
                category =
                    ConditioningCategory.MOBILITY,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 40,
                restSeconds = 10,
                instructions =
                    "Move into a controlled lunge and rotate the upper body toward the front leg.",
                cue =
                    "Move slowly and breathe through the rotation.",
                alternative =
                    "Standing Torso Rotation",
                unlockAfterSessions = 8
            ),

            exercise(
                id =
                    "mobility_deep_squat_hold",
                title =
                    "Deep Squat Hold",
                category =
                    ConditioningCategory.MOBILITY,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 15,
                instructions =
                    "Sit into a comfortable deep squat and hold your balance while keeping the chest upright.",
                cue =
                    "Stay relaxed and keep your heels controlled.",
                alternative =
                    "Supported Squat Hold",
                unlockAfterSessions = 24
            ),

            exercise(
                id =
                    "mobility_cossack_shift",
                title =
                    "Cossack Side Shift",
                category =
                    ConditioningCategory.MOBILITY,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 15,
                instructions =
                    "Shift from side to side by bending one knee while keeping the opposite leg longer.",
                cue =
                    "Control the range rather than forcing depth.",
                alternative =
                    "Wide-Stance Side Shift",
                unlockAfterSessions = 36
            ),

            exercise(
                id =
                    "mobility_spiderman_rotation",
                title =
                    "Spiderman Rotation",
                category =
                    ConditioningCategory.MOBILITY,
                difficulty =
                    ConditioningDifficulty.ADVANCED,
                workSeconds = 45,
                restSeconds = 15,
                instructions =
                    "From a long lunge position, place one hand down and rotate the opposite arm toward the ceiling.",
                cue =
                    "Use smooth controlled rotation through the upper body.",
                alternative =
                    "Lunge & Rotation",
                unlockAfterSessions = 66
            ),

            // =================================================
            // AGILITY
            // =================================================

            exercise(
                id =
                    "agility_stance_bounce",
                title =
                    "Stance Bounce",
                category =
                    ConditioningCategory.AGILITY,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Stay in your fighting stance and make small relaxed rhythmic weight shifts.",
                cue =
                    "Stay light without letting your feet come together.",
                alternative =
                    null,
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "agility_forward_back_steps",
                title =
                    "Forward & Back Steps",
                category =
                    ConditioningCategory.AGILITY,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Move forward and backward using short fighting-stance steps.",
                cue =
                    "The lead foot starts forward and the rear foot starts backward.",
                alternative =
                    "Slow Stance Steps",
                unlockAfterSessions = 4
            ),

            exercise(
                id =
                    "agility_lateral_steps",
                title =
                    "Lateral Fight Steps",
                category =
                    ConditioningCategory.AGILITY,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 35,
                restSeconds = 15,
                instructions =
                    "Move left and right while maintaining your normal fighting stance.",
                cue =
                    "Do not cross your feet.",
                alternative =
                    "Slow Lateral Steps",
                unlockAfterSessions = 8
            ),

            exercise(
                id =
                    "agility_quick_direction_change",
                title =
                    "Quick Direction Changes",
                category =
                    ConditioningCategory.AGILITY,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Move forward, backward, left and right on changing imaginary commands.",
                cue =
                    "React quickly but keep your stance underneath you.",
                alternative =
                    "Controlled Four-Way Steps",
                unlockAfterSessions = 24
            ),

            exercise(
                id =
                    "agility_pivot_flow",
                title =
                    "Pivot Flow",
                category =
                    ConditioningCategory.AGILITY,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Alternate stance movement with controlled left and right pivots.",
                cue =
                    "Turn first, then rebuild your fighting stance.",
                alternative =
                    "Slow Pivot Practice",
                unlockAfterSessions = 36
            ),

            exercise(
                id =
                    "agility_sprawl_recover",
                title =
                    "Sprawl & Recover",
                category =
                    ConditioningCategory.AGILITY,
                difficulty =
                    ConditioningDifficulty.ADVANCED,
                workSeconds = 40,
                restSeconds = 25,
                instructions =
                    "Drop into a controlled sprawl and return quickly to your fighting stance.",
                cue =
                    "Recover directly into a usable fight position.",
                alternative =
                    "Step-Back Sprawl",
                unlockAfterSessions = 66
            ),

            // =================================================
            // ENDURANCE
            // =================================================

            exercise(
                id =
                    "endurance_shadow_straights",
                title =
                    "Continuous Straight Punches",
                category =
                    ConditioningCategory.ENDURANCE,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 40,
                restSeconds = 20,
                instructions =
                    "Continuously throw relaxed jabs and crosses while maintaining your stance.",
                cue =
                    "Stay loose and breathe with every punch.",
                alternative =
                    "Slow Jab-Cross",
                unlockAfterSessions = 0
            ),

            exercise(
                id =
                    "endurance_squat_hold",
                title =
                    "Squat Hold",
                category =
                    ConditioningCategory.ENDURANCE,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 30,
                restSeconds = 15,
                instructions =
                    "Hold a controlled partial squat position while keeping your chest upright.",
                cue =
                    "Keep breathing while your legs work.",
                alternative =
                    "Higher Squat Hold",
                unlockAfterSessions = 4
            ),

            exercise(
                id =
                    "endurance_shadowboxing_flow",
                title =
                    "Continuous Shadowboxing",
                category =
                    ConditioningCategory.ENDURANCE,
                difficulty =
                    ConditioningDifficulty.BEGINNER,
                workSeconds = 60,
                restSeconds = 20,
                instructions =
                    "Shadowbox continuously using simple punches and movement without stopping.",
                cue =
                    "Keep the pace sustainable and technique clean.",
                alternative =
                    "Slow Shadowboxing",
                unlockAfterSessions = 8
            ),

            exercise(
                id =
                    "endurance_pushup_plank",
                title =
                    "Push-Up to Plank",
                category =
                    ConditioningCategory.ENDURANCE,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 45,
                restSeconds = 20,
                instructions =
                    "Alternate controlled push-ups with short plank holds.",
                cue =
                    "Keep your trunk stable even as fatigue builds.",
                alternative =
                    "Knee Push-Up to Plank",
                unlockAfterSessions = 24
            ),

            exercise(
                id =
                    "endurance_fighter_circuit",
                title =
                    "Fighter Movement Circuit",
                category =
                    ConditioningCategory.ENDURANCE,
                difficulty =
                    ConditioningDifficulty.INTERMEDIATE,
                workSeconds = 60,
                restSeconds = 20,
                instructions =
                    "Cycle continuously through stance movement, straight punches, level changes and controlled squats.",
                cue =
                    "Keep moving without becoming technically sloppy.",
                alternative =
                    "Slow Fighter Movement Circuit",
                unlockAfterSessions = 36
            ),

            exercise(
                id =
                    "endurance_burpee_shadow_combo",
                title =
                    "Burpee to Shadow Combo",
                category =
                    ConditioningCategory.ENDURANCE,
                difficulty =
                    ConditioningDifficulty.ADVANCED,
                workSeconds = 60,
                restSeconds = 30,
                instructions =
                    "Complete one controlled burpee, recover into stance and immediately throw a short shadowboxing combination.",
                cue =
                    "Recover your stance before throwing punches.",
                alternative =
                    "Step-Back Burpee to Jab-Cross",
                unlockAfterSessions = 66
            )
        )

    // ---------------------------------------------------------
    // LOOKUPS
    // ---------------------------------------------------------

    fun getExercise(
        exerciseId: String
    ): ConditioningExerciseDefinition? {

        return exercises
            .firstOrNull {
                    exercise ->

                exercise.id ==
                        exerciseId
            }
    }

    fun exercisesForCategory(
        category: ConditioningCategory
    ): List<ConditioningExerciseDefinition> {

        return exercises
            .filter {
                    exercise ->

                exercise.category ==
                        category
            }
    }

    fun exercisesForDifficulty(
        difficulty: ConditioningDifficulty
    ): List<ConditioningExerciseDefinition> {

        return exercises
            .filter {
                    exercise ->

                exercise.difficulty ==
                        difficulty
            }
    }

    /**
     * Returns only exercises that the fighter has actually earned.
     *
     * Missing calendar days do not unlock exercises.
     * Completed Fight Path work does.
     */
    fun unlockedExercises(
        completedFightPathSessions: Int
    ): List<ConditioningExerciseDefinition> {

        val safeCompletedSessions =
            completedFightPathSessions
                .coerceAtLeast(0)

        return exercises
            .filter {
                    exercise ->

                safeCompletedSessions >=
                        exercise
                            .unlockAfterFightPathSessions
            }
    }

    fun unlockedExercises(
        completedFightPathSessions: Int,
        category: ConditioningCategory
    ): List<ConditioningExerciseDefinition> {

        return unlockedExercises(
            completedFightPathSessions =
                completedFightPathSessions
        ).filter {
                exercise ->

            exercise.category ==
                    category
        }
    }

    fun unlockedExercises(
        completedFightPathSessions: Int,
        maximumDifficulty:
        ConditioningDifficulty
    ): List<ConditioningExerciseDefinition> {

        return unlockedExercises(
            completedFightPathSessions =
                completedFightPathSessions
        ).filter {
                exercise ->

            exercise
                .difficulty
                .rank <=
                    maximumDifficulty.rank
        }
    }

    /**
     * Exercises suitable for short conditioning blocks
     * inserted between Fight Path sections.
     */
    fun techniqueConditioningPool(
        completedFightPathSessions: Int
    ): List<ConditioningExerciseDefinition> {

        return unlockedExercises(
            completedFightPathSessions =
                completedFightPathSessions
        ).filter {
                exercise ->

            exercise.category in
                    setOf(
                        ConditioningCategory.CARDIO,
                        ConditioningCategory.STRENGTH,
                        ConditioningCategory.CORE,
                        ConditioningCategory.AGILITY
                    )
        }
    }

    /**
     * Exercises suitable for the final end-of-session finisher.
     */
    fun finisherPool(
        completedFightPathSessions: Int
    ): List<ConditioningExerciseDefinition> {

        return unlockedExercises(
            completedFightPathSessions =
                completedFightPathSessions
        ).filter {
                exercise ->

            exercise.category in
                    setOf(
                        ConditioningCategory.CARDIO,
                        ConditioningCategory.CORE,
                        ConditioningCategory.ENDURANCE
                    )
        }
    }

    // ---------------------------------------------------------
    // HELPER
    // ---------------------------------------------------------

    private fun exercise(
        id: String,
        title: String,
        category:
        ConditioningCategory,
        difficulty:
        ConditioningDifficulty,
        workSeconds: Int,
        restSeconds: Int,
        instructions: String,
        cue: String,
        alternative: String?,
        unlockAfterSessions: Int
    ): ConditioningExerciseDefinition {

        return ConditioningExerciseDefinition(
            id =
                id,
            title =
                title,
            category =
                category,
            difficulty =
                difficulty,
            defaultWorkSeconds =
                workSeconds,
            defaultRestSeconds =
                restSeconds,
            instructions =
                instructions,
            coachingCue =
                cue,
            lowImpactAlternative =
                alternative,
            unlockAfterFightPathSessions =
                unlockAfterSessions,
            requiresEquipment =
                false
        )
    }
}