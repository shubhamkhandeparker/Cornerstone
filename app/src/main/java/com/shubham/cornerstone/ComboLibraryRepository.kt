package com.shubham.cornerstone

import kotlinx.coroutines.flow.Flow

class ComboLibraryRepository(
    private val dao: ComboLibraryDao
) {

    fun observePlaylists(): Flow<List<ComboPlaylistEntity>> {
        return dao.observePlaylists()
    }

    fun observePlaylistById(playlistId: Long): Flow<ComboPlaylistEntity?> {
        return dao.observePlaylistById(playlistId)
    }

    fun observeCombosForPlaylist(
        playlistId: Long
    ): Flow<List<PlaylistComboEntity>> {
        return dao.observeCombosForPlaylist(playlistId)
    }

    suspend fun createPlaylist(name: String) {
        val cleanName = name.trim()

        if (cleanName.isBlank()) return

        dao.insertPlaylist(
            ComboPlaylistEntity(
                name = cleanName
            )
        )
    }

    suspend fun deletePlaylist(playlistId: Long) {
        dao.deletePlaylistWithCombos(playlistId)
    }

    suspend fun addCustomComboToPlaylist(
        playlistId: Long,
        moves: String,
        cue: String
    ) {
        val cleanMoves = moves.trim()
        val cleanCue = cue.trim()

        if (cleanMoves.isBlank()) return

        dao.addCustomComboToPlaylist(
            playlistId = playlistId,
            moves = cleanMoves,
            cue = cleanCue
        )
    }

    suspend fun deleteComboFromPlaylist(
        playlistId: Long,
        comboId: Long
    ) {
        dao.deleteComboFromPlaylist(
            playlistId = playlistId,
            comboId = comboId
        )
    }
}