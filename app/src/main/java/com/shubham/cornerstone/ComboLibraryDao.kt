package com.shubham.cornerstone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ComboLibraryDao {

    // -------------------------
    // Playlists
    // -------------------------

    @Query("SELECT * FROM combo_playlist ORDER BY createdAtEpochMs DESC")
    abstract fun observePlaylists(): Flow<List<ComboPlaylistEntity>>

    @Query("SELECT * FROM combo_playlist WHERE id = :playlistId LIMIT 1")
    abstract fun observePlaylistById(playlistId: Long): Flow<ComboPlaylistEntity?>

    @Insert
    abstract suspend fun insertPlaylist(playlist: ComboPlaylistEntity): Long

    @Query("DELETE FROM combo_playlist WHERE id = :playlistId")
    abstract suspend fun deletePlaylistById(playlistId: Long)

    // -------------------------
    // Playlist combos
    // -------------------------

    @Query(
        """
        SELECT * FROM playlist_combo
        WHERE playlistId = :playlistId
        ORDER BY position ASC, createdAtEpochMs ASC
        """
    )
    abstract fun observeCombosForPlaylist(
        playlistId: Long
    ): Flow<List<PlaylistComboEntity>>

    @Query(
        """
        SELECT COALESCE(MAX(position), -1) + 1
        FROM playlist_combo
        WHERE playlistId = :playlistId
        """
    )
    abstract suspend fun getNextPositionForPlaylist(playlistId: Long): Int

    @Insert
    abstract suspend fun insertPlaylistCombo(
        playlistCombo: PlaylistComboEntity
    ): Long

    @Query(
        """
        DELETE FROM playlist_combo
        WHERE id = :comboId
        AND playlistId = :playlistId
        """
    )
    abstract suspend fun deleteComboFromPlaylist(
        playlistId: Long,
        comboId: Long
    )

    @Query(
        """
        DELETE FROM playlist_combo
        WHERE playlistId = :playlistId
        """
    )
    abstract suspend fun deleteAllCombosForPlaylist(playlistId: Long)

    // -------------------------
    // Safe combined actions
    // -------------------------

    @Transaction
    open suspend fun addCustomComboToPlaylist(
        playlistId: Long,
        moves: String,
        cue: String
    ): Long {
        val nextPosition = getNextPositionForPlaylist(playlistId)

        return insertPlaylistCombo(
            PlaylistComboEntity(
                playlistId = playlistId,
                position = nextPosition,
                phase = "Playlist",
                moves = moves,
                cue = cue
            )
        )
    }

    @Transaction
    open suspend fun deletePlaylistWithCombos(playlistId: Long) {
        deleteAllCombosForPlaylist(playlistId)
        deletePlaylistById(playlistId)
    }
}