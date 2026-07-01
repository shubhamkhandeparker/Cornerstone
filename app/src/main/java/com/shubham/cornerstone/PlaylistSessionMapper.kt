package com.shubham.cornerstone

fun List<PlaylistComboEntity>.toSessionCombos(): List<Combo> {
    return mapIndexed { index, playlistCombo ->
        Combo(
            number = index + 1,
            phase = playlistCombo.phase,
            moves = playlistCombo.moves,
            cue = playlistCombo.cue
        )
    }
}