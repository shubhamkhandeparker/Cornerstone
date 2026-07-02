package com.shubham.cornerstone

import android.util.Log
import kotlinx.serialization.json.Json

/**
 * Generates a fighter-aware training session using Groq AI.
 *
 * New behavior:
 * - Can generate custom session length.
 * - Uses offset/session progression so every new session does not feel like it starts from combo 1 again.
 * - Falls back to a larger local combo bank if AI fails.
 */
class ComboGenerator {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    private val model = "llama-3.3-70b-versatile"

    suspend fun generate(
        sport: String,
        level: String,
        dominance: String,
        stance: String,
        count: Int = 6,
        offset: Int = 0
    ): List<Combo> {
        val safeCount = count.coerceIn(
            minimumValue = 1,
            maximumValue = 50
        )

        return try {
            val aiCombos = callGroq(
                sport = sport,
                level = level,
                dominance = dominance,
                stance = stance,
                count = safeCount,
                offset = offset
            )

            if (aiCombos.isNotEmpty()) {
                aiCombos
                    .take(safeCount)
                    .mapIndexed { index, c ->
                        Combo(
                            number = index + 1,
                            phase = cleanPhase(
                                phase = c.phase,
                                index = index,
                                total = safeCount
                            ),
                            moves = c.moves,
                            cue = c.cue
                        )
                    }
            } else {
                fallback(
                    sport = sport,
                    count = safeCount,
                    offset = offset
                )
            }
        } catch (e: Exception) {
            Log.e("ComboGenerator", "AI generation failed, using fallback", e)

            fallback(
                sport = sport,
                count = safeCount,
                offset = offset
            )
        }
    }

    private fun cleanPhase(
        phase: String,
        index: Int,
        total: Int
    ): String {
        val clean = phase.trim().uppercase()

        return when {
            clean.contains("WARM") -> "WARMUP"
            clean.contains("WORK") -> "WORK"
            clean.contains("FINISH") -> "FINISH"
            clean.isNotBlank() -> clean
            else -> phaseForIndex(index, total)
        }
    }

    private fun phaseForIndex(
        index: Int,
        total: Int
    ): String {
        return when {
            index == total - 1 -> "FINISH"
            index < 2 -> "WARMUP"
            else -> "WORK"
        }
    }

    private fun arsenalFor(sport: String): String = when (sport) {
        "Boxing" -> """
            Boxing uses ONLY hands: jab, cross, lead hook, rear hook,
            lead uppercut, rear uppercut, plus head movement such as slip,
            roll, pull, pivot, step back, and angle exits.
            No kicks, knees, elbows, or grappling.
        """.trimIndent()

        "Muay Thai" -> """
            Muay Thai is the art of 8 limbs: punches, kicks, teeps,
            low kicks, body kicks, knees, elbows, clinch entries,
            frames, checks, and exits. Use the full arsenal.
        """.trimIndent()

        "MMA" -> """
            MMA blends striking and grappling: punches, kicks, knees,
            elbows, clinch, level changes, double-leg entries, single-leg
            entries, sprawls, underhooks, and cage-style pressure movement.
        """.trimIndent()

        else -> "Use standard striking fundamentals."
    }

    private fun styleGuidance(dominance: String): String = when (dominance) {
        "Striker" -> "Favor crisp striking combinations, exits, counters, and footwork."
        "Grappler" -> "Mix strikes with level changes, clinch entries, takedown entries, and sprawls."
        "All-rounder" -> "Balance striking, defense, angles, clinch, and grappling entries."
        else -> "Balanced approach."
    }

    private suspend fun callGroq(
        sport: String,
        level: String,
        dominance: String,
        stance: String,
        count: Int,
        offset: Int
    ): List<AiCombo> {
        val startIndex = offset + 1
        val endIndex = offset + count

        val systemPrompt = """
            You are an elite $sport coach creating a solo shadow-training
            session for a fighter training alone at home with no equipment.

            FIGHTER PROFILE:
            - Sport: $sport
            - Level: $level
            - Style: $dominance
            - Stance: $stance

            ARSENAL:
            ${arsenalFor(sport)}

            STYLE:
            ${styleGuidance(dominance)}

            LEVEL RULES:
            - Beginner: simple, clean combos. Mostly 2-3 moves.
            - Intermediate: 3-4 move combos with defense, rhythm changes, and exits.
            - Advanced: 4-6 move combos with feints, counters, angles, and layered setups.

            SESSION PROGRESSION:
            - Generate EXACTLY $count combos.
            - Treat this as progression numbers $startIndex to $endIndex in the user's training day.
            - Do NOT always start with "1 - 1" or the same basic double jab.
            - If progression is above 6, increase variety and avoid repeating the earlier beginner openers.
            - Use 2 warmup-style combos if the session is long enough.
            - Use mostly work combos in the middle.
            - The final combo should feel like a finisher.
            - Each cue must be one short coaching sentence.
            - Use real move names or clear fight notation.
            - For boxing, do not include kicks, knees, elbows, or takedowns.
            - For Muay Thai, you may include kicks, knees, elbows, teeps, and clinch.
            - For MMA, you may include level changes, sprawls, and takedown entries.

            PHASE RULES:
            Use only these phase values:
            - warmup
            - work
            - finish

            Respond with ONLY valid JSON, no markdown, in this exact shape:
            {"combos":[{"phase":"warmup","moves":"Jab - Cross","cue":"Stay loose and return to guard."}]}
        """.trimIndent()

        val request = GroqRequest(
            model = model,
            messages = listOf(
                GroqMessage(
                    role = "system",
                    content = systemPrompt
                ),
                GroqMessage(
                    role = "user",
                    content = "Generate my session."
                )
            )
        )

        val response = GroqClient.api.chat(
            authorization = "Bearer ${BuildConfig.GROQ_API_KEY}",
            request = request
        )

        val content = response.choices.firstOrNull()?.message?.content?.trim()
            ?: return emptyList()

        val cleaned = content
            .removePrefix("```json")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()

        val session = json.decodeFromString<AiSession>(cleaned)
        return session.combos
    }

    private fun fallback(
        sport: String,
        count: Int,
        offset: Int
    ): List<Combo> {
        val bank = fallbackBankForSport(sport)

        return List(count) { index ->
            val template = bank[
                (offset + index).floorMod(bank.size)
            ]

            Combo(
                number = index + 1,
                phase = phaseForIndex(
                    index = index,
                    total = count
                ),
                moves = template.moves,
                cue = template.cue
            )
        }
    }

    private fun fallbackBankForSport(sport: String): List<ComboTemplate> {
        return when (sport) {
            "Muay Thai" -> muayThaiFallbackBank()
            "MMA" -> mmaFallbackBank()
            else -> boxingFallbackBank()
        }
    }

    private fun boxingFallbackBank(): List<ComboTemplate> {
        return listOf(
            ComboTemplate("Jab", "Stay light and touch the target."),
            ComboTemplate("Double jab", "Find your range and keep your chin tucked."),
            ComboTemplate("Jab - Cross", "Snap both punches and reset your feet."),
            ComboTemplate("Jab - Cross - Lead hook", "Bring the hand back after the hook."),
            ComboTemplate("Jab - Cross - Slip - Cross", "Slip the counter and fire back straight."),
            ComboTemplate("Cross - Lead hook - Cross", "Turn your hips on every shot."),
            ComboTemplate("Jab - Rear uppercut - Lead hook", "Change levels before the hook."),
            ComboTemplate("Jab - Body cross - Lead hook", "Drop your level, then come back upstairs."),
            ComboTemplate("Double jab - Cross - Pivot out", "Exit before the imaginary counter."),
            ComboTemplate("Jab - Pull - Cross", "Make them miss, then answer sharp."),
            ComboTemplate("Lead hook - Cross - Lead hook", "Keep your feet under you while rotating."),
            ComboTemplate("Jab - Cross - Roll - Cross", "Roll under and return with balance."),
            ComboTemplate("Jab - Step back - Cross", "Draw the attack, then punish it."),
            ComboTemplate("Cross - Lead hook - Roll - Lead hook", "Defend after your combination."),
            ComboTemplate("Jab - Cross - Lead uppercut - Cross", "Punch through the center line."),
            ComboTemplate("Double jab - Body cross - Lead hook", "Touch high, score low, finish high."),
            ComboTemplate("Jab - Slip outside - Cross - Lead hook", "Move your head before you reload."),
            ComboTemplate("Cross - Lead hook - Cross - Pivot", "Do not admire your work."),
            ComboTemplate("Jab - Jab - Cross - Roll", "Stay busy but stay defensively responsible."),
            ComboTemplate("Body jab - Cross - Lead hook", "Change the target and finish strong."),
            ComboTemplate("Jab - Cross - Slip - Lead hook - Cross", "Build rhythm, then break rhythm."),
            ComboTemplate("Lead hook to body - Lead hook to head - Cross", "Same side attack, then straight finish."),
            ComboTemplate("Jab - Cross - Pull - Cross - Lead hook", "Counter with confidence after the pull."),
            ComboTemplate("Double jab - Cross - Lead hook - Roll", "End safe after the exchange."),
            ComboTemplate("Jab - Rear uppercut - Lead hook - Cross", "Rise through the uppercut and finish straight."),
            ComboTemplate("Cross - Lead hook - Cross - Lead hook", "Keep the rotation smooth."),
            ComboTemplate("Jab - Cross - Pivot - Cross", "Create the angle before firing again."),
            ComboTemplate("Jab - Body cross - Lead uppercut - Cross", "Level change, split the guard, finish."),
            ComboTemplate("Slip - Cross - Lead hook - Cross", "Start with defense, end with offense."),
            ComboTemplate("Jab - Cross - Roll - Cross - Lead hook", "Last one. Breathe and stay sharp.")
        )
    }

    private fun muayThaiFallbackBank(): List<ComboTemplate> {
        return listOf(
            ComboTemplate("Jab - Rear low kick", "Touch the guard and chop the leg."),
            ComboTemplate("Double jab - Rear teep", "Push them back after the hands."),
            ComboTemplate("Jab - Cross - Lead hook - Rear low kick", "Finish the boxing with a kick."),
            ComboTemplate("Teep - Cross - Rear kick", "Control distance before you score."),
            ComboTemplate("Jab - Rear kick", "Step out after the kick."),
            ComboTemplate("Cross - Lead hook - Rear knee", "Punch your way into the knee."),
            ComboTemplate("Jab - Cross - Lead elbow", "Close distance before the elbow."),
            ComboTemplate("Lead teep - Jab - Cross", "Break rhythm with the teep."),
            ComboTemplate("Jab - Cross - Switch kick", "Hide the switch behind the hands."),
            ComboTemplate("Check - Cross - Rear kick", "Defend first, then answer hard."),
            ComboTemplate("Jab - Lead hook - Rear low kick", "Turn the hip through the kick."),
            ComboTemplate("Cross - Lead hook - Clinch knee", "Enter strong and posture tall."),
            ComboTemplate("Teep - Rear kick - Cross", "Kick, land balanced, punch back."),
            ComboTemplate("Jab - Cross - Rear elbow", "Shorten the range for the elbow."),
            ComboTemplate("Lead hook - Rear kick - Lead hook", "Flow from hands to kick and back."),
            ComboTemplate("Double jab - Rear knee", "Drive the knee straight through."),
            ComboTemplate("Jab - Rear body kick - Lead hook", "Kick the body, then punch the exit."),
            ComboTemplate("Rear teep - Cross - Lead elbow", "Push, enter, cut through."),
            ComboTemplate("Jab - Cross - Check - Rear kick", "Be ready to defend after attacking."),
            ComboTemplate("Lead teep - Rear low kick - Cross", "Mix distance and damage."),
            ComboTemplate("Jab - Cross - Lead hook - Rear kick", "Classic finish. Hands set up the kick."),
            ComboTemplate("Switch kick - Cross - Lead hook", "Return to stance before punching."),
            ComboTemplate("Rear knee - Lead hook - Rear low kick", "Exit the clinch with damage."),
            ComboTemplate("Jab - Cross - Rear kick - Lead hook", "Stay balanced after the kick."),
            ComboTemplate("Check - Rear kick - Cross - Lead hook", "Counter immediately after the check."),
            ComboTemplate("Teep - Jab - Cross - Rear elbow", "Long range to close range."),
            ComboTemplate("Jab - Cross - Clinch knee - Rear kick", "Build pressure through every range."),
            ComboTemplate("Lead hook - Rear low kick - Rear body kick", "Layer the same side threat."),
            ComboTemplate("Cross - Lead elbow - Rear knee", "Compact power in close."),
            ComboTemplate("Jab - Cross - Rear kick - Rear elbow", "Last one. Stay sharp through the finish.")
        )
    }

    private fun mmaFallbackBank(): List<ComboTemplate> {
        return listOf(
            ComboTemplate("Jab - Cross - Level change", "Show the shot after the hands."),
            ComboTemplate("Double jab - Sprawl", "Strike, then defend the takedown."),
            ComboTemplate("Jab - Rear low kick", "Damage the base and exit."),
            ComboTemplate("Jab - Cross - Double leg entry", "Punch into the level change."),
            ComboTemplate("Cross - Lead hook - Sprawl", "Finish the combo ready to defend."),
            ComboTemplate("Jab - Teep - Cross", "Manage distance before re-entering."),
            ComboTemplate("Jab - Cross - Rear kick", "Mix boxing and kicking cleanly."),
            ComboTemplate("Level change - Cross - Lead hook", "Fake the shot and come upstairs."),
            ComboTemplate("Jab - Cross - Clinch knee", "Punch your way into the clinch."),
            ComboTemplate("Lead hook - Rear low kick - Sprawl", "Attack and immediately defend."),
            ComboTemplate("Jab - Cross - Single leg entry", "Hide the entry behind straight punches."),
            ComboTemplate("Teep - Cross - Lead hook", "Keep them off balance."),
            ComboTemplate("Jab - Slip - Cross - Level change", "Defense into offense into entry."),
            ComboTemplate("Rear low kick - Cross - Lead hook", "Kick first, punch the reaction."),
            ComboTemplate("Jab - Cross - Underhook entry", "Close distance with structure."),
            ComboTemplate("Sprawl - Cross - Lead hook", "Defend, then make them pay."),
            ComboTemplate("Jab - Rear kick - Double leg entry", "Mix the threat high, low, and through."),
            ComboTemplate("Cross - Lead hook - Level change - Cross", "Break rhythm with the fake shot."),
            ComboTemplate("Jab - Cross - Rear knee", "Enter tall and balanced."),
            ComboTemplate("Jab - Pull - Cross - Sprawl", "Counter and stay wrestling-aware."),
            ComboTemplate("Double jab - Cross - Double leg entry", "Use volume to enter safely."),
            ComboTemplate("Rear low kick - Jab - Cross - Sprawl", "Strike and recover your base."),
            ComboTemplate("Level change - Lead hook - Cross", "Sell the shot before punching."),
            ComboTemplate("Jab - Cross - Clinch knee - Exit", "Score inside and leave safely."),
            ComboTemplate("Cross - Lead hook - Rear kick - Sprawl", "Chain offense and defense."),
            ComboTemplate("Jab - Single leg entry - Cross", "Change levels and come back up punching."),
            ComboTemplate("Teep - Cross - Level change", "Push, punch, then enter."),
            ComboTemplate("Sprawl - Jab - Cross - Rear low kick", "Defend first, then build pressure."),
            ComboTemplate("Jab - Cross - Double leg entry - Sprawl", "Flow between attack and defense."),
            ComboTemplate("Cross - Lead hook - Level change - Finish", "Last one. Commit with control.")
        )
    }

    private fun Int.floorMod(other: Int): Int {
        return ((this % other) + other) % other
    }

    private data class ComboTemplate(
        val moves: String,
        val cue: String
    )
}