package com.shubham.cornerstone

import android.util.Log
import kotlinx.serialization.json.Json

/**
 * Generates a fighter-aware training session using Groq AI.
 * Adapts to sport, level, fighting style (dominance), and stance.
 * Falls back to hardcoded combos if the AI call fails.
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
        stance: String
    ): List<Combo> {
        return try {
            val aiCombos = callGroq(sport, level, dominance, stance)
            if (aiCombos.isNotEmpty()) {
                aiCombos.mapIndexed { i, c ->
                    Combo(
                        number = i + 1,
                        phase = c.phase.uppercase(),
                        moves = c.moves,
                        cue = c.cue
                    )
                }
            } else {
                fallback()
            }
        } catch (e: Exception) {
            Log.e("ComboGenerator", "AI generation failed, using fallback", e)
            fallback()
        }
    }

    // The real arsenal per sport — pulled from how these sports actually train.
    private fun arsenalFor(sport: String): String = when (sport) {
        "Boxing" -> """
            Boxing uses ONLY hands: jab, cross, lead hook, rear hook,
            lead uppercut, rear uppercut, plus head movement (slip, roll,
            pivot). No kicks, knees, elbows, or grappling.
        """.trimIndent()

        "Muay Thai" -> """
            Muay Thai is the art of 8 limbs: punches (jab, cross, hooks,
            uppercuts), kicks (teep/push kick, roundhouse/rear kick, switch
            kick), knees (rear knee, lead knee, flying knee), elbows
            (horizontal, upward), and clinch work. Use the full arsenal.
        """.trimIndent()

        "MMA" -> """
            MMA blends striking and grappling: punches, kicks (rear kick,
            teep, low kick), knees, elbows, clinch, plus level changes,
            double-leg takedowns, and sprawls. Mix striking with takedown
            entries where it fits.
        """.trimIndent()

        else -> "Use standard striking fundamentals."
    }

    // How fighting style should shape the combos.
    private fun styleGuidance(dominance: String): String = when (dominance) {
        "Striker" -> "Favor crisp striking combinations and footwork. Keep them on their feet."
        "Grappler" -> "Weave in level changes, takedown entries, and clinch work alongside strikes."
        "All-rounder" -> "Balance striking and grappling — flow between both ranges."
        else -> "Balanced approach."
    }

    private suspend fun callGroq(
        sport: String,
        level: String,
        dominance: String,
        stance: String
    ): List<AiCombo> {

        val systemPrompt = """
            You are an elite $sport coach creating a solo shadow-training
            session for a fighter training alone at home with no equipment.

            FIGHTER PROFILE:
            - Sport: $sport
            - Level: $level
            - Style: $dominance
            - Stance: $stance

            ARSENAL (use moves appropriate to this sport):
            ${arsenalFor(sport)}

            STYLE: ${styleGuidance(dominance)}

            LEVEL RULES:
            - Beginner: short, simple combos (2-3 moves). Focus on fundamentals.
            - Intermediate: 3-4 move combos with some defense and variety.
            - Advanced: 4-6 move combos, complex sequences, feints, angles.

            Write combos for a $stance fighter. Return EXACTLY 6 combos that
            flow: 2 warmup, 3 work, 1 finish. Each cue is one short coaching
            sentence. Use real move names (e.g. "Jab - Cross - Rear Kick",
            "Level Change - Double Leg"), not just numbers.

            Respond with ONLY valid JSON, no markdown, in this exact shape:
            {"combos":[{"phase":"warmup","moves":"Jab - Cross","cue":"..."}]}
        """.trimIndent()

        val request = GroqRequest(
            model = model,
            messages = listOf(
                GroqMessage(role = "system", content = systemPrompt),
                GroqMessage(role = "user", content = "Generate my session.")
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

    private fun fallback(): List<Combo> = SessionData.beginnerBoxing()
}