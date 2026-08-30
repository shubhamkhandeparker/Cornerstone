package com.shubham.cornerstone

import android.util.Log
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.json.Json

/**
 * Generates fighter-aware Free Training sessions using Groq AI.
 *
 * Important:
 * - Fight Path decides structured progression.
 * - This generator is for Free Training/custom sessions.
 * - Defensive head movement must always be directional:
 *   Slip Left, Slip Right, Roll Left, Roll Right.
 */
class ComboGenerator {

    private val json =
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }

    private val model =
        "llama-3.3-70b-versatile"

    suspend fun generate(
        sport: String,
        level: String,
        dominance: String,
        stance: String,
        count: Int = 6,
        offset: Int = 0
    ): List<Combo> {

        val safeCount =
            count.coerceIn(
                minimumValue = 1,
                maximumValue = 50
            )

        Log.d(
            "ComboGenerator",
            "Groq key length = ${BuildConfig.GROQ_API_KEY.length}"
        )

        return try {
            val aiCombos =
                withTimeoutOrNull(
                    15_000L
                ) {
                    callGroq(
                        sport = sport,
                        level = level,
                        dominance = dominance,
                        stance = stance,
                        count = safeCount,
                        offset = offset
                    )
                } ?: emptyList()

            if (aiCombos.isNotEmpty()) {
                Log.d(
                    "ComboGenerator",
                    "AI success. Groq returned ${aiCombos.size} combos."
                )

                aiCombos
                    .take(safeCount)
                    .mapIndexed {
                            index,
                            aiCombo ->

                        val cleanedMoves =
                            enforceDirectionalDefense(
                                moves =
                                    aiCombo
                                        .moves
                                        .trim(),
                                index =
                                    offset +
                                            index
                            )

                        Combo(
                            number =
                                index + 1,
                            phase =
                                cleanPhase(
                                    phase =
                                        aiCombo.phase,
                                    index =
                                        index,
                                    total =
                                        safeCount
                                ),
                            moves =
                                cleanedMoves,
                            cue =
                                aiCombo
                                    .cue
                                    .trim()
                        )
                    }
            } else {
                Log.w(
                    "ComboGenerator",
                    "AI timeout or empty response, using fallback"
                )

                fallback(
                    sport =
                        sport,
                    count =
                        safeCount,
                    offset =
                        offset
                )
            }
        } catch (
            exception: Exception
        ) {
            Log.e(
                "ComboGenerator",
                "AI generation failed, using fallback",
                exception
            )

            fallback(
                sport =
                    sport,
                count =
                    safeCount,
                offset =
                    offset
            )
        }
    }

    // ---------------------------------------------------------
    // PHASES
    // ---------------------------------------------------------

    private fun cleanPhase(
        phase: String,
        index: Int,
        total: Int
    ): String {

        val clean =
            phase
                .trim()
                .uppercase()

        return when {
            clean.contains(
                "WARM"
            ) -> {
                "WARMUP"
            }

            clean.contains(
                "WORK"
            ) -> {
                "WORK"
            }

            clean.contains(
                "FINISH"
            ) -> {
                "FINISH"
            }

            clean.isNotBlank() -> {
                clean
            }

            else -> {
                phaseForIndex(
                    index =
                        index,
                    total =
                        total
                )
            }
        }
    }

    private fun phaseForIndex(
        index: Int,
        total: Int
    ): String {

        return when {
            index ==
                    total - 1 -> {

                "FINISH"
            }

            index < 2 -> {
                "WARMUP"
            }

            else -> {
                "WORK"
            }
        }
    }

    // ---------------------------------------------------------
    // SPORT RULES
    // ---------------------------------------------------------

    private fun arsenalFor(
        sport: String
    ): String {

        return when (sport) {
            "Boxing" -> {
                """
                Boxing uses ONLY boxing techniques:
                jab, cross, lead hook, rear hook, lead uppercut,
                rear uppercut, body jab, body cross, body hooks,
                pivots, step backs, lateral steps, pulls,
                parries and directional head movement.

                HEAD MOVEMENT RULE:
                Never write "Slip" by itself.
                Never write "Roll" by itself.
                Always explicitly write:
                Slip Left,
                Slip Right,
                Roll Left,
                or Roll Right.

                No kicks, knees, elbows, teeps or grappling.
                """.trimIndent()
            }

            "Kickboxing" -> {
                """
                Kickboxing combines boxing with kicking.

                Allowed techniques include:
                jab,
                cross,
                hooks,
                uppercuts,
                low kicks,
                body kicks,
                high kicks,
                rear round kick,
                lead round kick,
                switch kick,
                front kick,
                checks,
                pivots,
                step backs,
                lateral exits,
                and directional head movement.

                HEAD MOVEMENT RULE:
                Never write "Slip" by itself.
                Never write "Roll" by itself.
                Always explicitly write:
                Slip Left,
                Slip Right,
                Roll Left,
                or Roll Right.

                Do not include takedowns, wrestling,
                submissions or ground techniques.

                Avoid elbows and prolonged clinch work.
                """.trimIndent()
            }

            "Muay Thai" -> {
                """
                Muay Thai uses the full striking arsenal:
                punches,
                low kicks,
                body kicks,
                head kicks,
                teeps,
                knees,
                elbows,
                clinch entries,
                frames,
                checks,
                pivots and exits.

                HEAD MOVEMENT RULE:
                Never write "Slip" by itself.
                Never write "Roll" by itself.
                Always explicitly write:
                Slip Left,
                Slip Right,
                Roll Left,
                or Roll Right.
                """.trimIndent()
            }

            "MMA" -> {
                """
                MMA blends striking and grappling:
                punches,
                kicks,
                knees,
                elbows,
                clinch,
                level changes,
                double-leg entries,
                single-leg entries,
                sprawls,
                underhooks,
                pivots,
                cage-style pressure movement
                and directional head movement.

                HEAD MOVEMENT RULE:
                Never write "Slip" by itself.
                Never write "Roll" by itself.
                Always explicitly write:
                Slip Left,
                Slip Right,
                Roll Left,
                or Roll Right.
                """.trimIndent()
            }

            else -> {
                """
                Use safe standard striking fundamentals.

                Never write "Slip" or "Roll" without
                explicitly stating Left or Right.
                """.trimIndent()
            }
        }
    }

    private fun styleGuidance(
        dominance: String
    ): String {

        return when (
            dominance
        ) {
            "Striker" -> {
                "Favor crisp striking combinations, exits, counters and footwork."
            }

            "Grappler" -> {
                "For MMA, mix strikes with level changes, clinch entries, takedown entries and sprawls. For striking-only sports, stay within that sport's legal arsenal."
            }

            "All-rounder" -> {
                "Balance striking, defense, angles and the techniques allowed by the selected sport."
            }

            else -> {
                "Use a balanced technical approach."
            }
        }
    }

    // ---------------------------------------------------------
    // GROQ
    // ---------------------------------------------------------

    private suspend fun callGroq(
        sport: String,
        level: String,
        dominance: String,
        stance: String,
        count: Int,
        offset: Int
    ): List<AiCombo> {

        val startIndex =
            offset + 1

        val endIndex =
            offset + count

        val systemPrompt =
            """
            You are an experienced $sport coach creating a solo
            shadow-training session for a fighter training alone
            with no equipment.

            FIGHTER PROFILE:
            - Sport: $sport
            - Level: $level
            - Style: $dominance
            - Stance: $stance

            SPORT ARSENAL:
            ${arsenalFor(sport)}

            STYLE:
            ${styleGuidance(dominance)}

            LEVEL RULES:

            Beginner:
            - Prefer simple combinations.
            - Mostly 2 to 3 offensive actions.
            - Keep defensive movement simple.
            - Do not overload the fighter with unnecessary complexity.

            Intermediate:
            - Use approximately 3 to 5 actions.
            - Add defense, counters, rhythm changes and exits.
            - Include purposeful footwork.

            Advanced:
            - Use approximately 4 to 6 actions.
            - Include feints, counters, angle changes,
              target changes and layered setups.
            - Complexity must still make technical sense.

            ABSOLUTE DIRECTION RULE:

            You MUST NEVER output:
            - Slip
            - Roll
            - Slip outside
            - Slip inside
            - Roll under

            without naming a physical direction.

            Every slip must be written as exactly:
            - Slip Left
            or
            - Slip Right

            Every roll must be written as exactly:
            - Roll Left
            or
            - Roll Right

            The fighter must never have to guess which
            direction to move their head.

            Apply this rule inside BOTH:
            - moves
            - coaching cue

            Examples:

            GOOD:
            Jab - Cross - Slip Right - Cross

            GOOD:
            Cross - Lead Hook - Roll Left - Lead Hook

            BAD:
            Jab - Cross - Slip - Cross

            BAD:
            Jab - Cross - Slip Outside - Cross

            BAD:
            Cross - Lead Hook - Roll - Cross

            SESSION PROGRESSION:

            - Generate EXACTLY $count combos.
            - Treat this as progression numbers
              $startIndex through $endIndex
              in the user's Free Training sequence.
            - Do not always begin with the same
              Double Jab combination.
            - Avoid unnecessary repetition.
            - Use 2 warmup-style combinations when
              the session is long enough.
            - Use mostly work combinations in the middle.
            - The final combination should feel like a finisher.
            - Every cue must be one short coaching sentence.
            - Use clear real technique names.

            SPORT RESTRICTIONS:

            Boxing:
            - No kicks.
            - No knees.
            - No elbows.
            - No takedowns.
            - No grappling.

            Kickboxing:
            - Use boxing and kicks.
            - Low, body and high kicks are allowed.
            - Front kicks and checks are allowed.
            - No takedowns.
            - No submissions.
            - No ground fighting.
            - Avoid elbows and prolonged clinch work.

            Muay Thai:
            - Punches, kicks, knees, elbows,
              teeps, checks and clinch work are allowed.

            MMA:
            - Striking, level changes,
              sprawls and takedown entries are allowed.

            PHASE RULES:

            Use only:
            - warmup
            - work
            - finish

            JSON RULES:

            - Respond with ONLY valid JSON.
            - Do not include markdown.
            - Do not include explanation.
            - Do not use code fences.

            Use exactly this shape:

            {"combos":[{"phase":"warmup","moves":"Jab - Cross","cue":"Stay loose and return both hands to guard."}]}
            """.trimIndent()

        val request =
            GroqRequest(
                model =
                    model,
                messages =
                    listOf(
                        GroqMessage(
                            role =
                                "system",
                            content =
                                systemPrompt
                        ),
                        GroqMessage(
                            role =
                                "user",
                            content =
                                "Generate my session."
                        )
                    )
            )

        val response =
            GroqClient.api.chat(
                authorization =
                    "Bearer ${BuildConfig.GROQ_API_KEY}",
                request =
                    request
            )

        val content =
            response
                .choices
                .firstOrNull()
                ?.message
                ?.content
                ?.trim()
                ?: return emptyList()

        Log.d(
            "ComboGenerator",
            "Raw AI response length = ${content.length}"
        )

        val cleaned =
            extractJsonObject(
                content
            )

        val session =
            json.decodeFromString<AiSession>(
                cleaned
            )

        return session.combos
    }

    private fun extractJsonObject(
        raw: String
    ): String {

        val withoutMarkdown =
            raw
                .removePrefix(
                    "```json"
                )
                .removePrefix(
                    "```"
                )
                .removeSuffix(
                    "```"
                )
                .trim()

        val firstBrace =
            withoutMarkdown
                .indexOf('{')

        val lastBrace =
            withoutMarkdown
                .lastIndexOf('}')

        return if (
            firstBrace >= 0 &&
            lastBrace >=
            firstBrace
        ) {
            withoutMarkdown.substring(
                firstBrace,
                lastBrace + 1
            )
        } else {
            withoutMarkdown
        }
    }

    // ---------------------------------------------------------
    // DEFENSIVE DIRECTION SAFETY
    // ---------------------------------------------------------

    /**
     * AI prompts are not treated as perfect guarantees.
     *
     * If AI still returns a vague Slip/Roll variation,
     * this cleanup makes the final move shown to the
     * fighter explicitly directional.
     */
    private fun enforceDirectionalDefense(
        moves: String,
        index: Int
    ): String {

        var cleaned =
            moves

        val slipDirection =
            if (
                index % 2 == 0
            ) {
                "Right"
            } else {
                "Left"
            }

        val rollDirection =
            if (
                index % 2 == 0
            ) {
                "Left"
            } else {
                "Right"
            }

        cleaned =
            cleaned.replace(
                Regex(
                    pattern =
                        """(?i)\bSlip\s+(Outside|Inside)\b"""
                ),
                "Slip $slipDirection"
            )

        cleaned =
            cleaned.replace(
                Regex(
                    pattern =
                        """(?i)\bSlip\b(?!\s+(Left|Right))"""
                ),
                "Slip $slipDirection"
            )

        cleaned =
            cleaned.replace(
                Regex(
                    pattern =
                        """(?i)\bRoll\s+Under\b"""
                ),
                "Roll $rollDirection"
            )

        cleaned =
            cleaned.replace(
                Regex(
                    pattern =
                        """(?i)\bRoll\b(?!\s+(Left|Right))"""
                ),
                "Roll $rollDirection"
            )

        return cleaned
            .replace(
                Regex(
                    """\s+"""
                ),
                " "
            )
            .trim()
    }

    // ---------------------------------------------------------
    // FALLBACK
    // ---------------------------------------------------------

    private fun fallback(
        sport: String,
        count: Int,
        offset: Int
    ): List<Combo> {

        Log.w(
            "ComboGenerator",
            "Fallback active. sport=$sport count=$count offset=$offset"
        )

        val bank =
            fallbackBankForSport(
                sport
            )

        return List(count) {
                index ->

            val template =
                bank[
                    (
                            offset +
                                    index
                            )
                        .floorMod(
                            bank.size
                        )
                ]

            Combo(
                number =
                    index + 1,
                phase =
                    phaseForIndex(
                        index =
                            index,
                        total =
                            count
                    ),
                moves =
                    enforceDirectionalDefense(
                        moves =
                            template.moves,
                        index =
                            offset +
                                    index
                    ),
                cue =
                    template.cue
            )
        }
    }

    private fun fallbackBankForSport(
        sport: String
    ): List<ComboTemplate> {

        return when (
            sport
        ) {
            "Kickboxing" -> {
                kickboxingFallbackBank()
            }

            "Muay Thai" -> {
                muayThaiFallbackBank()
            }

            "MMA" -> {
                mmaFallbackBank()
            }

            else -> {
                boxingFallbackBank()
            }
        }
    }

    // ---------------------------------------------------------
    // BOXING FALLBACK
    // ---------------------------------------------------------

    private fun boxingFallbackBank():
            List<ComboTemplate> {

        return listOf(
            ComboTemplate(
                "Jab",
                "Stay light and return the jab directly to guard."
            ),

            ComboTemplate(
                "Double Jab",
                "Use the second jab without bringing your feet together."
            ),

            ComboTemplate(
                "Jab - Cross",
                "Snap both straight punches and rebuild your stance."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Hook",
                "Keep the hook compact and finish balanced."
            ),

            ComboTemplate(
                "Jab - Cross - Slip Right - Cross",
                "Slip clearly to the RIGHT before returning with the cross."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Cross",
                "Turn your hips without letting your feet collapse."
            ),

            ComboTemplate(
                "Jab - Rear Uppercut - Lead Hook",
                "Keep the uppercut compact and finish with the lead hook."
            ),

            ComboTemplate(
                "Jab - Body Cross - Lead Hook",
                "Change levels through your legs before coming back upstairs."
            ),

            ComboTemplate(
                "Double Jab - Cross - Pivot Left",
                "Finish the punches before turning out to the LEFT."
            ),

            ComboTemplate(
                "Jab - Pull - Cross",
                "Make the imaginary punch miss before answering."
            ),

            ComboTemplate(
                "Lead Hook - Cross - Lead Hook",
                "Keep your feet underneath you through every rotation."
            ),

            ComboTemplate(
                "Jab - Cross - Roll Left - Cross",
                "Roll clearly to the LEFT and return balanced."
            ),

            ComboTemplate(
                "Jab - Step Back - Cross",
                "Create distance before firing the counter."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Roll Right - Lead Hook",
                "Roll clearly to the RIGHT before returning with the hook."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Uppercut - Cross",
                "Stay compact through the centre line."
            ),

            ComboTemplate(
                "Double Jab - Body Cross - Lead Hook",
                "Touch high, attack the body and finish upstairs."
            ),

            ComboTemplate(
                "Jab - Slip Right - Cross - Lead Hook",
                "Slip clearly to the RIGHT before reloading."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Cross - Pivot Left",
                "Finish the combination and exit to the LEFT."
            ),

            ComboTemplate(
                "Jab - Jab - Cross - Roll Left",
                "Roll clearly to the LEFT after finishing the straight punches."
            ),

            ComboTemplate(
                "Body Jab - Cross - Lead Hook",
                "Change the target and finish with balance."
            ),

            ComboTemplate(
                "Jab - Cross - Slip Left - Lead Hook - Cross",
                "Slip clearly to the LEFT and return with controlled offense."
            ),

            ComboTemplate(
                "Lead Hook to Body - Lead Hook to Head - Cross",
                "Change levels while keeping both hooks compact."
            ),

            ComboTemplate(
                "Jab - Cross - Pull - Cross - Lead Hook",
                "Counter immediately after making the attack miss."
            ),

            ComboTemplate(
                "Double Jab - Cross - Lead Hook - Roll Right",
                "Finish the exchange by rolling clearly to the RIGHT."
            ),

            ComboTemplate(
                "Jab - Rear Uppercut - Lead Hook - Cross",
                "Rise through the uppercut and finish straight."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Cross - Lead Hook",
                "Keep the rotation smooth without over-swinging."
            ),

            ComboTemplate(
                "Jab - Cross - Pivot Left - Cross",
                "Create the LEFT angle before throwing again."
            ),

            ComboTemplate(
                "Jab - Body Cross - Lead Uppercut - Cross",
                "Change level, attack through the middle and finish."
            ),

            ComboTemplate(
                "Slip Right - Cross - Lead Hook - Cross",
                "Slip clearly to the RIGHT before beginning the counter."
            ),

            ComboTemplate(
                "Jab - Cross - Roll Left - Cross - Lead Hook",
                "Roll clearly to the LEFT and finish the session balanced."
            )
        )
    }

    // ---------------------------------------------------------
    // KICKBOXING FALLBACK
    // ---------------------------------------------------------

    private fun kickboxingFallbackBank():
            List<ComboTemplate> {

        return listOf(
            ComboTemplate(
                "Jab - Rear Low Kick",
                "Touch with the jab and finish the kick balanced."
            ),

            ComboTemplate(
                "Double Jab - Rear Low Kick",
                "Use the jabs to establish range before kicking."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Low Kick",
                "Finish your hands before rotating into the kick."
            ),

            ComboTemplate(
                "Jab - Lead Low Kick",
                "Recover your stance immediately after the lead kick."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Hook - Rear Low Kick",
                "Let the hands draw attention high before attacking the leg."
            ),

            ComboTemplate(
                "Lead Front Kick - Jab - Cross",
                "Recover the kicking foot before beginning the punches."
            ),

            ComboTemplate(
                "Jab - Rear Body Kick",
                "Hide the body kick behind the jab."
            ),

            ComboTemplate(
                "Double Jab - Rear Body Kick",
                "Use the double jab to close distance without rushing."
            ),

            ComboTemplate(
                "Jab - Cross - Switch Kick",
                "Finish the cross before switching your stance for the kick."
            ),

            ComboTemplate(
                "Lead Low Kick - Cross - Lead Hook",
                "Land back in stance before punching."
            ),

            ComboTemplate(
                "Jab - Cross - Slip Right - Cross",
                "Slip clearly to the RIGHT before returning with the cross."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Rear Low Kick",
                "Keep the hook compact before finishing with the kick."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Body Kick - Step Back",
                "Recover from the kick before exiting."
            ),

            ComboTemplate(
                "Lead Front Kick - Cross - Rear Low Kick",
                "Manage distance first, then finish low."
            ),

            ComboTemplate(
                "Jab - Lead Hook - Rear Body Kick",
                "Turn the hook cleanly before rotating into the kick."
            ),

            ComboTemplate(
                "Check - Cross - Rear Low Kick",
                "Set the checking leg down under control before countering."
            ),

            ComboTemplate(
                "Jab - Cross - Roll Left - Lead Hook",
                "Roll clearly to the LEFT before returning with the hook."
            ),

            ComboTemplate(
                "Rear Low Kick - Jab - Cross",
                "Recover your stance after the kick before punching."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Hook - Switch Kick",
                "Stay balanced through the transition from hands to kick."
            ),

            ComboTemplate(
                "Lead Front Kick - Jab - Rear Body Kick",
                "Control range and keep both kicks technically clean."
            ),

            ComboTemplate(
                "Jab - Slip Right - Cross - Rear Low Kick",
                "Slip clearly to the RIGHT before countering with hands and kick."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Roll Left - Rear Body Kick",
                "Roll clearly to the LEFT and rebuild your stance before kicking."
            ),

            ComboTemplate(
                "Double Jab - Cross - Rear High Kick",
                "Use the punches to disguise the high kick."
            ),

            ComboTemplate(
                "Jab - Rear Low Kick - Cross - Lead Hook",
                "Recover from the kick before restarting the hands."
            ),

            ComboTemplate(
                "Check - Jab - Cross - Rear Body Kick",
                "Defend first, then build your counter in order."
            ),

            ComboTemplate(
                "Lead Low Kick - Jab - Cross - Pivot Left",
                "Finish your offense and leave on the LEFT angle."
            ),

            ComboTemplate(
                "Jab - Cross - Slip Left - Lead Hook - Rear Low Kick",
                "Slip clearly to the LEFT before completing the counter."
            ),

            ComboTemplate(
                "Double Jab - Rear Body Kick - Cross",
                "Return fully to stance before the final cross."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Roll Right - Cross - Rear Low Kick",
                "Roll clearly to the RIGHT and finish balanced."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Hook - Rear Body Kick - Step Back",
                "Finish the last combination cleanly and exit under control."
            )
        )
    }

    // ---------------------------------------------------------
    // MUAY THAI FALLBACK
    // ---------------------------------------------------------

    private fun muayThaiFallbackBank():
            List<ComboTemplate> {

        return listOf(
            ComboTemplate(
                "Jab - Rear Low Kick",
                "Touch the guard and finish with the low kick."
            ),

            ComboTemplate(
                "Double Jab - Rear Teep",
                "Use the jabs before pushing the opponent back."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Hook - Rear Low Kick",
                "Finish the boxing before rotating into the kick."
            ),

            ComboTemplate(
                "Lead Teep - Cross - Rear Kick",
                "Recover your stance after the teep before continuing."
            ),

            ComboTemplate(
                "Jab - Rear Body Kick",
                "Use the jab to hide the body kick."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Rear Knee",
                "Punch your way into knee range."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Elbow",
                "Close distance before throwing the elbow."
            ),

            ComboTemplate(
                "Lead Teep - Jab - Cross",
                "Break rhythm with the teep and return to stance."
            ),

            ComboTemplate(
                "Jab - Cross - Switch Kick",
                "Hide the switch behind the hands."
            ),

            ComboTemplate(
                "Lead Check - Cross - Rear Kick",
                "Set the checking leg down before answering."
            ),

            ComboTemplate(
                "Jab - Lead Hook - Rear Low Kick",
                "Finish your hands before kicking."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Clinch Knee",
                "Enter the clinch with posture and control."
            ),

            ComboTemplate(
                "Rear Teep - Rear Kick - Cross",
                "Land balanced after each kick before continuing."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Elbow",
                "Shorten the range before the elbow."
            ),

            ComboTemplate(
                "Lead Hook - Rear Kick - Lead Hook",
                "Return to stance before punching again."
            ),

            ComboTemplate(
                "Double Jab - Rear Knee",
                "Use the jabs to hide the knee entry."
            ),

            ComboTemplate(
                "Jab - Rear Body Kick - Lead Hook",
                "Recover after the kick before throwing the hook."
            ),

            ComboTemplate(
                "Rear Teep - Cross - Lead Elbow",
                "Move from long range into close range under control."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Check - Rear Kick",
                "Be ready to defend immediately after attacking."
            ),

            ComboTemplate(
                "Lead Teep - Rear Low Kick - Cross",
                "Mix ranges while keeping your balance."
            ),

            ComboTemplate(
                "Jab - Cross - Lead Hook - Rear Kick",
                "Let the hands create the opening for the kick."
            ),

            ComboTemplate(
                "Switch Kick - Cross - Lead Hook",
                "Return to stance before beginning the boxing."
            ),

            ComboTemplate(
                "Rear Knee - Lead Hook - Rear Low Kick",
                "Exit close range with controlled offense."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Kick - Lead Hook",
                "Stay balanced when transitioning back to punches."
            ),

            ComboTemplate(
                "Lead Check - Rear Kick - Cross - Lead Hook",
                "Counter immediately after the check."
            ),

            ComboTemplate(
                "Lead Teep - Jab - Cross - Rear Elbow",
                "Move smoothly from long range into elbow range."
            ),

            ComboTemplate(
                "Jab - Cross - Clinch Knee - Rear Kick",
                "Maintain posture through each range change."
            ),

            ComboTemplate(
                "Lead Hook - Rear Low Kick - Rear Body Kick",
                "Recover between kicks instead of rushing."
            ),

            ComboTemplate(
                "Cross - Lead Elbow - Rear Knee",
                "Keep every close-range strike compact."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Kick - Rear Elbow",
                "Finish the session with balance through every range."
            )
        )
    }

    // ---------------------------------------------------------
    // MMA FALLBACK
    // ---------------------------------------------------------

    private fun mmaFallbackBank():
            List<ComboTemplate> {

        return listOf(
            ComboTemplate(
                "Jab - Cross - Level Change",
                "Show the wrestling threat after the hands."
            ),

            ComboTemplate(
                "Double Jab - Sprawl",
                "Strike first, then defend the imaginary takedown."
            ),

            ComboTemplate(
                "Jab - Rear Low Kick",
                "Attack the base and recover your stance."
            ),

            ComboTemplate(
                "Jab - Cross - Double Leg Entry",
                "Hide the level change behind straight punches."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Sprawl",
                "Finish your combination ready to defend."
            ),

            ComboTemplate(
                "Jab - Lead Teep - Cross",
                "Manage distance before re-entering."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Kick",
                "Mix boxing and kicking without losing your stance."
            ),

            ComboTemplate(
                "Level Change - Cross - Lead Hook",
                "Sell the wrestling threat before coming upstairs."
            ),

            ComboTemplate(
                "Jab - Cross - Clinch Knee",
                "Punch your way into the clinch."
            ),

            ComboTemplate(
                "Lead Hook - Rear Low Kick - Sprawl",
                "Attack and immediately prepare to defend."
            ),

            ComboTemplate(
                "Jab - Cross - Single Leg Entry",
                "Hide the single-leg entry behind your hands."
            ),

            ComboTemplate(
                "Lead Teep - Cross - Lead Hook",
                "Control range before entering with punches."
            ),

            ComboTemplate(
                "Jab - Slip Right - Cross - Level Change",
                "Slip clearly to the RIGHT before changing levels."
            ),

            ComboTemplate(
                "Rear Low Kick - Cross - Lead Hook",
                "Kick first and punch the reaction."
            ),

            ComboTemplate(
                "Jab - Cross - Underhook Entry",
                "Close distance while keeping a strong posture."
            ),

            ComboTemplate(
                "Sprawl - Cross - Lead Hook",
                "Defend the shot and counter immediately."
            ),

            ComboTemplate(
                "Jab - Rear Kick - Double Leg Entry",
                "Mix striking threats before changing levels."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Level Change - Cross",
                "Use the level change to break the opponent's reaction."
            ),

            ComboTemplate(
                "Jab - Cross - Rear Knee",
                "Enter knee range with your posture tall."
            ),

            ComboTemplate(
                "Jab - Pull - Cross - Sprawl",
                "Counter the strike and remain wrestling-aware."
            ),

            ComboTemplate(
                "Double Jab - Cross - Double Leg Entry",
                "Use the hands to disguise the entry."
            ),

            ComboTemplate(
                "Rear Low Kick - Jab - Cross - Sprawl",
                "Recover your base before defending the shot."
            ),

            ComboTemplate(
                "Level Change - Lead Hook - Cross",
                "Sell the takedown before attacking upstairs."
            ),

            ComboTemplate(
                "Jab - Cross - Clinch Knee - Exit",
                "Score inside and leave with your stance intact."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Rear Kick - Sprawl",
                "Chain offense into takedown defense."
            ),

            ComboTemplate(
                "Jab - Single Leg Entry - Cross",
                "Change levels and return to striking posture."
            ),

            ComboTemplate(
                "Lead Teep - Cross - Level Change",
                "Push, punch and then change levels."
            ),

            ComboTemplate(
                "Sprawl - Jab - Cross - Rear Low Kick",
                "Defend first and then rebuild offense."
            ),

            ComboTemplate(
                "Jab - Cross - Double Leg Entry - Sprawl",
                "Flow between attacking and defensive wrestling movement."
            ),

            ComboTemplate(
                "Cross - Lead Hook - Level Change - Cross",
                "Finish the session with controlled mixed-range movement."
            )
        )
    }

    // ---------------------------------------------------------
    // HELPERS
    // ---------------------------------------------------------

    private fun Int.floorMod(
        other: Int
    ): Int {

        return (
                (
                        this %
                                other
                        ) +
                        other
                ) %
                other
    }

    private data class ComboTemplate(
        val moves: String,
        val cue: String
    )
}