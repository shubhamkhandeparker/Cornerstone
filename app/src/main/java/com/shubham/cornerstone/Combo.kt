package com.shubham.cornerstone

/**
 * One shadowboxing combo in a session.
 *
 * `moves` is the punch/defense sequence.
 * `cue` is the coaching tip shown underneath.
 * `phase` groups combos into Warmup / Work / Finish.
 */
data class Combo(
    val number: Int,
    val phase: String,
    val moves: String,
    val cue: String
)

/**
 * Hardcoded beginner boxing session.
 *
 * Boxing notation:
 * 1 = Jab
 * 2 = Cross
 * 3 = Lead Hook
 * 4 = Rear Hook
 * 5 = Lead Uppercut
 * 6 = Rear Uppercut
 */
object SessionData {

    fun beginnerBoxing(): List<Combo> = listOf(

        Combo(
            number = 1,
            phase = "WARMUP",
            moves = "1 - 1",
            cue = "Double jab. Stay light on your feet and find your range."
        ),

        Combo(
            number = 2,
            phase = "WARMUP",
            moves = "1 - 2",
            cue = "Jab, cross. Rotate your hips into the cross and return to guard."
        ),

        Combo(
            number = 3,
            phase = "WORK",
            moves = "1 - 2 - 3",
            cue = "Jab, cross, lead hook. Keep your guard up after the hook."
        ),

        Combo(
            number = 4,
            phase = "WORK",
            moves = "1 - 2 - slip right - 2",
            cue = "Jab, cross, slip to your RIGHT, then fire the cross."
        ),

        Combo(
            number = 5,
            phase = "WORK",
            moves = "1 - 6 - 3 - 2",
            cue = "Jab, rear uppercut, lead hook, cross. Flow — don't muscle it."
        ),

        Combo(
            number = 6,
            phase = "FINISH",
            moves = "1 - 2 - roll left - 2",
            cue = "Jab, cross, roll to your LEFT, then come back with a sharp cross."
        )
    )
}