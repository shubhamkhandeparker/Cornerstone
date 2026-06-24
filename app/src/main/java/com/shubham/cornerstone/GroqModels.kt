package com.shubham.cornerstone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/* ---------- REQUEST (what we send to Groq) ---------- */

@Serializable
data class GroqRequest(
    val model: String,
    val messages: List<GroqMessage>,
    val temperature: Double = 0.8
)

@Serializable
data class GroqMessage(
    val role: String,      // "system" or "user"
    val content: String
)

/* ---------- RESPONSE (what Groq sends back) ---------- */

@Serializable
data class GroqResponse(
    val choices: List<GroqChoice> = emptyList()
)

@Serializable
data class GroqChoice(
    val message: GroqMessage
)

/* ---------- The combo shape we ask the AI to return ----------
   The AI replies with JSON matching this, which we parse into Combo. */

@Serializable
data class AiCombo(
    @SerialName("phase") val phase: String,
    @SerialName("moves") val moves: String,
    @SerialName("cue") val cue: String
)

@Serializable
data class AiSession(
    @SerialName("combos") val combos: List<AiCombo>
)