package com.shubham.cornerstone

data class TechniqueLesson(
    val id: String,
    val title: String,
    val sport: String,
    val level: String,
    val shortDescription: String,
    val purpose: String,
    val imagePanels: List<TechniqueImagePanel>,
    val steps: List<String>,
    val cues: List<String>,
    val commonMistakes: List<String>
)

data class TechniqueImagePanel(
    val title: String,
    val subtitle: String,
    val coachingLines: List<String>
)