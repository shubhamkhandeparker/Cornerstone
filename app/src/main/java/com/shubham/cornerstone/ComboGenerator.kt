package com.shubham.cornerstone

import android.util.Log
import kotlinx.serialization.json.Json

/**
 * Generates a training session using Groq AI.
 * If the AI call fails (no internet, bad key, etc.), it falls back
 * to the hardcoded session so the app NEVER breaks.
 */
class ComboGenerator {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    // Groq's fast, free model. Good enough for combo text.
    private val model = "llama-3.3-70b-versatile"

    suspend fun generate(sport: String, level: String): List<Combo> {
        return try {
            val aiCombos = callGroq(sport, level)
            if (aiCombos.isNotEmpty()) {
                // Convert AI combos into our Combo type, numbering them.
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

    private suspend fun callGroq(sport: String, level: String): List<AiCombo> {
        val systemPrompt = """
            You are an elite $sport coach creating a solo shadowboxing session
            for a $level fighter training alone at home with no equipment.
            Return EXACTLY 6 combos that flow: 2 warmup, 3 work, 1 finish.
            Use standard boxing numbering (1 jab, 2 cross, 3 lead hook,
            4 rear hook, 5 lead uppercut, 6 rear uppercut) plus words like
            slip, roll, pivot where useful. Keep combos appropriate for a
            $level. Each cue is one short coaching sentence.

            Respond with ONLY valid JSON, no markdown, in this exact shape:
            {"combos":[{"phase":"warmup","moves":"1 - 1","cue":"..."}]}
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

        // Strip any accidental markdown fences, then parse.
        val cleaned = content
            .removePrefix("```json")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()

        val session = json.decodeFromString<AiSession>(cleaned)
        return session.combos
    }

    // Safety net — the hardcoded beginner session.
    private fun fallback(): List<Combo> = SessionData.beginnerBoxing()
}