package com.shubham.cornerstone

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "training_repetitions",
    indices = [
        Index(
            value = ["localDate"]
        ),
        Index(
            value = ["movementType"]
        ),
        Index(
            value = ["trainingSessionId"]
        ),
        Index(
            value = ["recordedAtEpochMs"]
        )
    ]
)
data class TrainingRepetitionEntity(

    @PrimaryKey(
        autoGenerate = true
    )
    val id: Long = 0L,

    /*
     * Optional link to the completed training session
     * that produced these repetitions.
     *
     * It remains nullable so repetition-only challenge
     * sessions can also be recorded.
     */
    val trainingSessionId: Long? = null,

    /*
     * Stored using LocalDate.toString():
     * YYYY-MM-DD
     */
    val localDate: String,

    val recordedAtEpochMs: Long =
        System.currentTimeMillis(),

    /*
     * Examples:
     * ai
     * playlist
     * challenge
     */
    val sessionType: String,

    val sport: String,

    /*
     * Current supported value:
     * KICK
     *
     * Later this can support:
     * PUNCH
     * KNEE
     * ELBOW
     * CORE_REP
     */
    val movementType: String,

    val repetitionCount: Int,

    /*
     * Genuine active time spent performing the
     * repetitions. Paused and background time
     * must not be included.
     */
    val activeSeconds: Int,

    /*
     * Uses ChallengeIntegrityStatus names:
     * VALID
     * SUSPICIOUS
     * INVALID
     */
    val integrityStatus: String =
        ChallengeIntegrityStatus.VALID.name
)