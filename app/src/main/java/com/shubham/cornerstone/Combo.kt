package com.shubham.cornerstone

/**
 * One shadowboxing combo in a session.
 * `moves` is the punch sequence (e.g. "1 - 2 - 3").
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
 * Numbers = standard boxing notation:
 * 1 jab · 2 cross · 3 lead hook · 4 rear hook · 5 lead uppercut · 6 rear uppercut
 *
 * Later, Groq AI will generate this list based on sport + level + history.
 */
object SessionData {

    fun beginnerBoxing(): List<Combo> = listOf(
        Combo(
            number = 1,
            phase = "WARMUP",
            moves = "1 - 1",
            cue = "Double jab. Stay light on your feet, find your range."
        ),
        Combo(
            number = 2,
            phase = "WARMUP",
            moves = "1 - 2",
            cue = "Jab, cross. Rotate your hips into the cross."
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
            moves = "1 - 2 - slip - 2",
            cue = "Throw, slip the imaginary counter, fire back the cross."
        ),
        Combo(
            number = 5,
            phase = "WORK",
            moves = "1 - 6 - 3 - 2",
            cue = "Jab, rear uppercut, hook, cross. Flow — don't muscle it."
        ),
        Combo(
            number = 6,
            phase = "FINISH",
            moves = "1 - 2 - roll - 2",
            cue = "Last one. Roll under, come back with a sharp cross. Breathe."
        )
    )
}