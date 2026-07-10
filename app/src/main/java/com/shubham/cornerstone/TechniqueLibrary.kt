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
            id = "rear_hook",
            title = "Rear Hook",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A powerful circular punch thrown with your rear hand.",
            purpose = "Use the rear hook at close range, after a lead-hand attack, or when your opponent's side guard is open.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Guard position",
                    subtitle = "Start balanced with both hands protecting your face.",
                    coachingLines = listOf(
                        "Chin tucked",
                        "Lead hand stays high",
                        "Rear elbow stays compact"
                    )
                ),
                TechniqueImagePanel(
                    title = "Rear-side rotation",
                    subtitle = "Rotate the rear hip and shoulder together.",
                    coachingLines = listOf(
                        "Turn rear hip",
                        "Pivot rear foot",
                        "Keep your balance centered"
                    )
                ),
                TechniqueImagePanel(
                    title = "Hook line",
                    subtitle = "Rear fist travels around in a compact arc.",
                    coachingLines = listOf(
                        "Elbow follows the fist",
                        "Wrist stays straight",
                        "Do not swing too wide"
                    )
                ),
                TechniqueImagePanel(
                    title = "Return to guard",
                    subtitle = "Bring the rear hand back immediately.",
                    coachingLines = listOf(
                        "Reset your stance",
                        "Keep lead hand protecting",
                        "Stay ready to defend"
                    )
                )
            ),
            steps = listOf(
                "Begin in your normal fighting stance with both hands high.",
                "Rotate your rear hip and shoulder toward the target.",
                "Pivot on the ball of your rear foot.",
                "Keep the rear elbow bent as the fist travels in a compact arc.",
                "Return the rear hand to guard and reset your stance."
            ),
            cues = listOf(
                "Turn the rear hip",
                "Compact arc",
                "Elbow behind fist",
                "Return to guard"
            ),
            commonMistakes = listOf(
                "Swinging the punch too wide",
                "Dropping the lead hand",
                "Throwing only with the arm",
                "Over-rotating and losing balance"
            )
        ),

        TechniqueLesson(
            id = "lead_uppercut",
            title = "Lead Uppercut",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A rising punch thrown with the lead hand.",
            purpose = "Use the lead uppercut at close range, against a lowered guard, or to set up a hook or cross.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Guard position",
                    subtitle = "Start compact with your chin protected.",
                    coachingLines = listOf(
                        "Hands close to face",
                        "Elbows compact",
                        "Stay balanced"
                    )
                ),
                TechniqueImagePanel(
                    title = "Level change",
                    subtitle = "Bend slightly through the knees.",
                    coachingLines = listOf(
                        "Do not bend at the waist",
                        "Keep eyes on target",
                        "Load the lead side"
                    )
                ),
                TechniqueImagePanel(
                    title = "Punch path",
                    subtitle = "Lead fist rises through the center.",
                    coachingLines = listOf(
                        "Palm faces inward",
                        "Elbow stays underneath",
                        "Keep the punch compact"
                    )
                ),
                TechniqueImagePanel(
                    title = "Return to guard",
                    subtitle = "Recover the lead hand immediately.",
                    coachingLines = listOf(
                        "Do not leave hand low",
                        "Reset your stance",
                        "Stay ready to defend"
                    )
                )
            ),
            steps = listOf(
                "Begin in your fighting stance with both hands high.",
                "Bend your knees slightly and load your lead side.",
                "Rotate your lead hip and shoulder toward the target.",
                "Drive the lead fist upward through the center.",
                "Return your hand to guard immediately."
            ),
            cues = listOf(
                "Bend the knees",
                "Punch through the center",
                "Elbow under fist",
                "Return to guard"
            ),
            commonMistakes = listOf(
                "Dropping the hand before punching",
                "Bending too far at the waist",
                "Swinging the punch upward too wide",
                "Leaving the opposite hand down"
            )
        ),

        TechniqueLesson(
            id = "rear_uppercut",
            title = "Rear Uppercut",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A powerful rising punch thrown with your rear hand.",
            purpose = "Use the rear uppercut at close range, after a jab or hook, or when the opponent protects the sides but leaves the center open.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Guard position",
                    subtitle = "Rear hand begins tight to the chin.",
                    coachingLines = listOf(
                        "Lead hand stays high",
                        "Rear elbow compact",
                        "Chin tucked"
                    )
                ),
                TechniqueImagePanel(
                    title = "Rear-side load",
                    subtitle = "Lower slightly and load the rear hip.",
                    coachingLines = listOf(
                        "Bend knees slightly",
                        "Do not lean backward",
                        "Keep balance centered"
                    )
                ),
                TechniqueImagePanel(
                    title = "Upward drive",
                    subtitle = "Drive the rear fist upward with hip rotation.",
                    coachingLines = listOf(
                        "Rear hip turns forward",
                        "Palm faces inward",
                        "Elbow stays beneath fist"
                    )
                ),
                TechniqueImagePanel(
                    title = "Recovery",
                    subtitle = "Return the rear hand and reset.",
                    coachingLines = listOf(
                        "Bring hand back fast",
                        "Reset rear foot",
                        "Stay protected"
                    )
                )
            ),
            steps = listOf(
                "Start in stance with your guard high.",
                "Bend your knees slightly and load the rear side.",
                "Rotate the rear hip and shoulder forward.",
                "Drive the rear fist upward through the center.",
                "Return the rear hand to guard and reset."
            ),
            cues = listOf(
                "Load the rear side",
                "Drive from the floor",
                "Elbow under fist",
                "Recover quickly"
            ),
            commonMistakes = listOf(
                "Using only the arm",
                "Dropping the rear hand before punching",
                "Leaning too far forward",
                "Over-rotating after the punch"
            )
        ),

        TechniqueLesson(
            id = "slip",
            title = "Slip",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A small head movement used to avoid a straight punch.",
            purpose = "Use a slip to move your head just outside an incoming jab or cross while staying close enough to counter.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting guard",
                    subtitle = "Begin balanced with eyes on the opponent.",
                    coachingLines = listOf(
                        "Hands high",
                        "Chin tucked",
                        "Knees relaxed"
                    )
                ),
                TechniqueImagePanel(
                    title = "Outside slip",
                    subtitle = "Move your head just outside the punch line.",
                    coachingLines = listOf(
                        "Small movement",
                        "Keep eyes forward",
                        "Do not bend at waist"
                    )
                ),
                TechniqueImagePanel(
                    title = "Inside slip",
                    subtitle = "Move to the opposite side of the center line.",
                    coachingLines = listOf(
                        "Rotate shoulders slightly",
                        "Keep guard active",
                        "Stay under control"
                    )
                ),
                TechniqueImagePanel(
                    title = "Counter position",
                    subtitle = "Finish balanced and ready to respond.",
                    coachingLines = listOf(
                        "Feet stay planted",
                        "Return to center",
                        "Counter immediately"
                    )
                )
            ),
            steps = listOf(
                "Start in your fighting stance with your hands high.",
                "Watch the opponent's shoulders and incoming straight punch.",
                "Rotate your torso slightly and move your head off the center line.",
                "Keep the movement small enough to remain balanced.",
                "Return to stance or counter immediately."
            ),
            cues = listOf(
                "Move inches, not feet",
                "Eyes on opponent",
                "Rotate, do not lean",
                "Slip and counter"
            ),
            commonMistakes = listOf(
                "Moving the head too far",
                "Bending only at the waist",
                "Dropping both hands",
                "Closing the eyes during the slip"
            )
        ),

        TechniqueLesson(
            id = "roll",
            title = "Roll",
            sport = "Boxing / MMA / Muay Thai",
            level = "Beginner",
            shortDescription = "A curved defensive movement used to move underneath hooks.",
            purpose = "Use the roll to move underneath a hook while staying balanced and ready to counter from the opposite side.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting guard",
                    subtitle = "Stay compact before the hook arrives.",
                    coachingLines = listOf(
                        "Hands high",
                        "Chin tucked",
                        "Feet stable"
                    )
                ),
                TechniqueImagePanel(
                    title = "Level change",
                    subtitle = "Lower your body by bending the knees.",
                    coachingLines = listOf(
                        "Do not bend at waist",
                        "Keep back controlled",
                        "Eyes stay forward"
                    )
                ),
                TechniqueImagePanel(
                    title = "U-shaped movement",
                    subtitle = "Move under the hook in a smooth curve.",
                    coachingLines = listOf(
                        "Head travels under",
                        "Shift weight smoothly",
                        "Keep both hands ready"
                    )
                ),
                TechniqueImagePanel(
                    title = "Exit position",
                    subtitle = "Rise on the opposite side ready to counter.",
                    coachingLines = listOf(
                        "Return to stance",
                        "Stay balanced",
                        "Counter safely"
                    )
                )
            ),
            steps = listOf(
                "Begin in stance with your guard tight.",
                "Bend your knees as the hook approaches.",
                "Move your head and torso underneath the punch in a U-shaped path.",
                "Shift your weight smoothly toward the opposite side.",
                "Rise back into stance and prepare to counter."
            ),
            cues = listOf(
                "Bend the knees",
                "Draw a U",
                "Hands stay high",
                "Rise ready to counter"
            ),
            commonMistakes = listOf(
                "Bending only at the waist",
                "Dropping too low",
                "Taking the eyes off the opponent",
                "Standing up before clearing the punch"
            )
        ),

        TechniqueLesson(
            id = "teep",
            title = "Teep",
            sport = "Muay Thai / MMA",
            level = "Beginner",
            shortDescription = "A straight pushing kick used to manage distance.",
            purpose = "Use the teep to stop forward pressure, maintain range, disrupt balance, or create space for other strikes.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting stance",
                    subtitle = "Begin balanced with your guard high.",
                    coachingLines = listOf(
                        "Rear heel light",
                        "Hands protecting face",
                        "Eyes on target"
                    )
                ),
                TechniqueImagePanel(
                    title = "Knee chamber",
                    subtitle = "Lift the kicking knee toward your chest.",
                    coachingLines = listOf(
                        "Stay tall",
                        "Support leg stable",
                        "Do not drop guard"
                    )
                ),
                TechniqueImagePanel(
                    title = "Push extension",
                    subtitle = "Extend the foot straight into the target.",
                    coachingLines = listOf(
                        "Push through the ball of foot",
                        "Hips move forward",
                        "Keep balance"
                    )
                ),
                TechniqueImagePanel(
                    title = "Return to stance",
                    subtitle = "Retract the leg before placing it down.",
                    coachingLines = listOf(
                        "Bring knee back first",
                        "Do not fall forward",
                        "Reset your guard"
                    )
                )
            ),
            steps = listOf(
                "Start in your Muay Thai or MMA stance.",
                "Lift your knee toward your chest.",
                "Extend your lower leg and push the target with the ball of your foot.",
                "Lean back only slightly while keeping your guard active.",
                "Retract the knee and return to stance."
            ),
            cues = listOf(
                "Knee first",
                "Push, do not snap",
                "Stay balanced",
                "Retract before stepping down"
            ),
            commonMistakes = listOf(
                "Swinging the leg like a round kick",
                "Dropping both hands",
                "Leaning backward too far",
                "Placing the foot down before retracting"
            )
        ),

        TechniqueLesson(
            id = "round_kick",
            title = "Round Kick",
            sport = "Muay Thai / MMA",
            level = "Beginner",
            shortDescription = "A powerful circular kick delivered with the shin.",
            purpose = "Use the round kick to attack the legs, body, or head by rotating your hips and striking through the target.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting stance",
                    subtitle = "Begin balanced and ready to rotate.",
                    coachingLines = listOf(
                        "Hands high",
                        "Weight centered",
                        "Eyes on target"
                    )
                ),
                TechniqueImagePanel(
                    title = "Support-foot pivot",
                    subtitle = "Turn the support foot away from the target.",
                    coachingLines = listOf(
                        "Lift support heel",
                        "Open the hip",
                        "Do not keep foot planted"
                    )
                ),
                TechniqueImagePanel(
                    title = "Hip rotation",
                    subtitle = "Swing the hip and shin through the target.",
                    coachingLines = listOf(
                        "Turn the hip over",
                        "Strike with shin",
                        "Swing same-side arm"
                    )
                ),
                TechniqueImagePanel(
                    title = "Recovery",
                    subtitle = "Return the leg safely to stance.",
                    coachingLines = listOf(
                        "Control the return",
                        "Reset your guard",
                        "Stay balanced"
                    )
                )
            ),
            steps = listOf(
                "Begin in your fighting stance.",
                "Step slightly or shift your weight onto the support leg.",
                "Pivot the support foot away from the target.",
                "Rotate your hips and swing the shin through the target.",
                "Control the leg back to your stance."
            ),
            cues = listOf(
                "Pivot first",
                "Turn the hip over",
                "Kick through the target",
                "Recover with control"
            ),
            commonMistakes = listOf(
                "Kicking only with the lower leg",
                "Failing to pivot the support foot",
                "Leaning backward too far",
                "Dropping both hands during the kick"
            )
        ),

        TechniqueLesson(
            id = "knee",
            title = "Knee",
            sport = "Muay Thai / MMA",
            level = "Beginner",
            shortDescription = "A close-range strike delivered with the knee.",
            purpose = "Use the knee at close range, from the clinch, or when an opponent moves forward with an exposed body.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting stance",
                    subtitle = "Begin balanced with the guard active.",
                    coachingLines = listOf(
                        "Hands high",
                        "Hips relaxed",
                        "Eyes on target"
                    )
                ),
                TechniqueImagePanel(
                    title = "Hip drive",
                    subtitle = "Drive the hips forward toward the target.",
                    coachingLines = listOf(
                        "Push from support foot",
                        "Hips move forward",
                        "Stay tall"
                    )
                ),
                TechniqueImagePanel(
                    title = "Knee path",
                    subtitle = "Lift the knee through the center line.",
                    coachingLines = listOf(
                        "Point toes downward",
                        "Strike with knee point",
                        "Keep body controlled"
                    )
                ),
                TechniqueImagePanel(
                    title = "Return to stance",
                    subtitle = "Bring the leg back under control.",
                    coachingLines = listOf(
                        "Recover your base",
                        "Guard stays high",
                        "Do not fall forward"
                    )
                )
            ),
            steps = listOf(
                "Start in stance or a controlled clinch position.",
                "Push from the support foot and drive your hips forward.",
                "Lift the striking knee toward the target.",
                "Point the toes downward and strike with the front of the knee.",
                "Retract the leg and return to a balanced stance."
            ),
            cues = listOf(
                "Drive the hips",
                "Toes down",
                "Knee through the target",
                "Recover your base"
            ),
            commonMistakes = listOf(
                "Lifting the knee without hip drive",
                "Leaning backward too far",
                "Leaving the chin unprotected",
                "Falling forward after the strike"
            )
        ),

        TechniqueLesson(
            id = "elbow",
            title = "Elbow",
            sport = "Muay Thai / MMA",
            level = "Beginner",
            shortDescription = "A short-range strike delivered with the point of the elbow.",
            purpose = "Use the elbow at close range when punches are crowded or when the opponent's guard leaves a narrow opening.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting guard",
                    subtitle = "Begin close with both hands protecting.",
                    coachingLines = listOf(
                        "Chin tucked",
                        "Opposite hand high",
                        "Stay compact"
                    )
                ),
                TechniqueImagePanel(
                    title = "Elbow chamber",
                    subtitle = "Raise the elbow into striking position.",
                    coachingLines = listOf(
                        "Shoulder stays relaxed",
                        "Hand remains close",
                        "Do not wind up"
                    )
                ),
                TechniqueImagePanel(
                    title = "Striking path",
                    subtitle = "Rotate the body and bring the elbow across.",
                    coachingLines = listOf(
                        "Turn the hip and shoulder",
                        "Strike with elbow point",
                        "Keep movement compact"
                    )
                ),
                TechniqueImagePanel(
                    title = "Recovery",
                    subtitle = "Return immediately to a protected guard.",
                    coachingLines = listOf(
                        "Opposite hand stays high",
                        "Reset your stance",
                        "Stay close and balanced"
                    )
                )
            ),
            steps = listOf(
                "Begin at close range with your guard high.",
                "Keep the elbow bent and raise it into position.",
                "Rotate your hip and shoulder toward the target.",
                "Bring the elbow across in a short controlled path.",
                "Return immediately to your guard."
            ),
            cues = listOf(
                "Short and sharp",
                "Turn the body",
                "Opposite hand high",
                "Return to guard"
            ),
            commonMistakes = listOf(
                "Winding the elbow too far back",
                "Throwing only with the arm",
                "Dropping the opposite hand",
                "Using the forearm instead of the elbow point"
            )
        ),

        TechniqueLesson(
            id = "check_kick",
            title = "Check Kick",
            sport = "Muay Thai / MMA",
            level = "Beginner",
            shortDescription = "A defensive leg lift used to block an incoming low kick.",
            purpose = "Use the check to protect your thigh and body by meeting the incoming kick with the hard part of your shin.",
            imagePanels = listOf(
                TechniqueImagePanel(
                    title = "Starting stance",
                    subtitle = "Begin balanced and ready to lift the lead leg.",
                    coachingLines = listOf(
                        "Hands high",
                        "Weight ready to shift",
                        "Eyes on opponent"
                    )
                ),
                TechniqueImagePanel(
                    title = "Knee lift",
                    subtitle = "Raise the checking knee toward the incoming kick.",
                    coachingLines = listOf(
                        "Lift knee high",
                        "Stay upright",
                        "Support leg stable"
                    )
                ),
                TechniqueImagePanel(
                    title = "Shin position",
                    subtitle = "Turn the shin outward toward the kick.",
                    coachingLines = listOf(
                        "Toes point down",
                        "Shin faces impact",
                        "Protect ribs with elbow"
                    )
                ),
                TechniqueImagePanel(
                    title = "Return to stance",
                    subtitle = "Place the foot down under control.",
                    coachingLines = listOf(
                        "Do not fall backward",
                        "Reset your guard",
                        "Prepare to counter"
                    )
                )
            ),
            steps = listOf(
                "Start in your normal fighting stance.",
                "Shift your weight onto the support leg.",
                "Lift the checking knee toward the incoming kick.",
                "Turn the shin outward and point the toes downward.",
                "Return the foot to stance and prepare to counter."
            ),
            cues = listOf(
                "Knee high",
                "Toes down",
                "Shin to shin",
                "Check and counter"
            ),
            commonMistakes = listOf(
                "Lifting the leg too late",
                "Keeping the toes pointed forward",
                "Dropping both hands",
                "Leaning backward and losing balance"
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
                "Drop your hips heavy toward the opponent's shoulders or upper back.",
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