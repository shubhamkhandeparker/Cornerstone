package com.shubham.cornerstone

object TechniqueLibrary {

    val all: List<TechniqueLesson> = listOf(
        TechniqueLesson(
            id = "jab",
            title = "Jab",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A fast straight punch thrown with your lead hand.",
            purpose = "Use the jab to measure distance, interrupt your opponent, set up bigger shots, and stay active while staying safe.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Front view",
                    subtitle = "Lead hand travels straight from guard.",
                    coachingLines = listOf(
                        "Chin tucked",
                        "Rear hand stays home",
                        "Lead shoulder protects jaw"
                    )
                ),
                TechniqueImagePanel(
                    title = "Side view",
                    subtitle = "Punch snaps out and returns fast.",
                    coachingLines = listOf(
                        "Do not lean too far",
                        "Keep balance centered",
                        "Recover guard immediately"
                    )
                ),
                TechniqueImagePanel(
                    title = "Foot position",
                    subtitle = "Lead foot points toward target.",
                    coachingLines = listOf(
                        "Small step is optional",
                        "Do not cross feet",
                        "Stay ready to move"
                    )
                ),
                TechniqueImagePanel(
                    title = "End position",
                    subtitle = "Arm extended, shoulder high, rear hand up.",
                    coachingLines = listOf(
                        "Palm faces down or slightly inward",
                        "Elbow not flared wide",
                        "Snap back to guard"
                    )
                )
            ),
            steps = listOf(
                "Start in your stance with both hands high.",
                "Keep your rear hand protecting your face.",
                "Extend your lead hand straight toward the target.",
                "Raise your lead shoulder slightly to protect your chin.",
                "Snap the hand back to guard immediately."
            ),
            cues = listOf(
                "Fast out, faster back",
                "Shoulder to chin",
                "Rear hand glued to face",
                "Do not admire the punch"
            ),
            commonMistakes = listOf(
                "Dropping the rear hand while jabbing",
                "Leaning forward and losing balance",
                "Pushing the jab instead of snapping it",
                "Bringing the hand back slowly"
            )
        ),

        TechniqueLesson(
            id = "cross",
            title = "Cross",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A powerful straight punch thrown with your rear hand.",
            purpose = "Use the cross after the jab, as a counter, or when the opponent is open on the center line.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Start position",
                    subtitle = "Rear hand loaded but guard stays tight.",
                    coachingLines = listOf(
                        "Chin tucked",
                        "Lead hand returns",
                        "Rear heel ready to rotate"
                    )
                ),
                TechniqueImagePanel(
                    title = "Hip rotation",
                    subtitle = "Power starts from the rear foot and hip.",
                    coachingLines = listOf(
                        "Turn rear hip",
                        "Pivot rear foot",
                        "Do not arm-punch"
                    )
                ),
                TechniqueImagePanel(
                    title = "Punch line",
                    subtitle = "Rear hand travels straight down the middle.",
                    coachingLines = listOf(
                        "Elbow stays in",
                        "Shoulder protects chin",
                        "Eyes on target"
                    )
                ),
                TechniqueImagePanel(
                    title = "Recovery",
                    subtitle = "Return to stance after the punch.",
                    coachingLines = listOf(
                        "Do not fall in",
                        "Bring hand back",
                        "Stay balanced"
                    )
                )
            ),
            steps = listOf(
                "Start in stance with both hands up.",
                "Rotate your rear hip and rear shoulder forward.",
                "Pivot slightly on the rear foot.",
                "Send the rear hand straight to the target.",
                "Return the hand to guard and reset your stance."
            ),
            cues = listOf(
                "Turn the hip",
                "Straight down the pipe",
                "Shoulder protects chin",
                "Reset after impact"
            ),
            commonMistakes = listOf(
                "Throwing only with the arm",
                "Dropping the lead hand",
                "Over-rotating and losing balance",
                "Leaving the punch extended"
            )
        ),

        TechniqueLesson(
            id = "lead_hook",
            title = "Lead Hook",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A circular punch thrown with the lead hand.",
            purpose = "Use the lead hook when the opponent is close, moving into you, or open on the side of the guard.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Guard position",
                    subtitle = "Lead elbow prepares to lift.",
                    coachingLines = listOf(
                        "Hands high",
                        "Chin tucked",
                        "Feet planted"
                    )
                ),
                TechniqueImagePanel(
                    title = "Elbow line",
                    subtitle = "Elbow and fist travel together.",
                    coachingLines = listOf(
                        "Elbow not too low",
                        "Wrist straight",
                        "Forearm strong"
                    )
                ),
                TechniqueImagePanel(
                    title = "Hip turn",
                    subtitle = "Power comes from rotation.",
                    coachingLines = listOf(
                        "Turn lead hip",
                        "Pivot lead foot",
                        "Do not slap"
                    )
                ),
                TechniqueImagePanel(
                    title = "Finish",
                    subtitle = "Hook lands and returns to guard.",
                    coachingLines = listOf(
                        "Stay compact",
                        "Do not swing wide",
                        "Reset fast"
                    )
                )
            ),
            steps = listOf(
                "Start with your guard high.",
                "Lift the lead elbow slightly.",
                "Rotate your lead hip and shoulder.",
                "Keep your elbow bent as the punch travels across.",
                "Return the hand to guard immediately."
            ),
            cues = listOf(
                "Compact, not wide",
                "Turn the hip",
                "Elbow behind fist",
                "Back to guard"
            ),
            commonMistakes = listOf(
                "Swinging too wide",
                "Dropping the opposite hand",
                "Slapping instead of punching",
                "Standing tall with chin exposed"
            )
        ),

        TechniqueLesson(
            id = "sprawl",
            title = "Sprawl",
            sport = "MMA / Wrestling",
            level = "Beginner",
            shortDescription = "A defensive movement used to stop a takedown attempt.",
            purpose = "Use the sprawl when an opponent shoots toward your legs. Your goal is to push your hips back and down while controlling their upper body.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Opponent shoots",
                    subtitle = "Recognize level change early.",
                    coachingLines = listOf(
                        "See the entry",
                        "Hands ready",
                        "Move hips back"
                    )
                ),
                TechniqueImagePanel(
                    title = "Hips back",
                    subtitle = "Throw your legs backward.",
                    coachingLines = listOf(
                        "Heavy hips",
                        "Chest pressure",
                        "Legs away"
                    )
                ),
                TechniqueImagePanel(
                    title = "Frame",
                    subtitle = "Use hands or forearms to block forward drive.",
                    coachingLines = listOf(
                        "Control head and shoulders",
                        "Do not give hips",
                        "Stay heavy"
                    )
                ),
                TechniqueImagePanel(
                    title = "Control & follow-up",
                    subtitle = "Stay heavy after stopping the shot.",
                    coachingLines = listOf(
                        "Control head and shoulders",
                        "Keep hips heavy",
                        "Circle out or attack safely"
                    )
                )
            ),
            steps = listOf(
                "Notice the opponent lowering level toward your legs.",
                "Kick your legs backward quickly.",
                "Drop your hips heavy toward the opponent’s shoulders or upper back.",
                "Use your hands or forearms to block and control their head and shoulders.",
                "Stay heavy, then circle out or follow up safely."
            ),
            cues = listOf(
                "Hips heavy",
                "Legs back",
                "Chest pressure",
                "Control first"
            ),
            commonMistakes = listOf(
                "Reacting too late",
                "Leaving hips too high",
                "Falling flat with no control",
                "Standing up before controlling the opponent"
            )
        )
    )

    fun find(id: String): TechniqueLesson? {
        return all.firstOrNull { it.id == id }
    }
}