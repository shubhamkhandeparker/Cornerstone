package com.shubham.cornerstone

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

private val CornerstoneBlack = Color(0xFF0B0B0D)
private val CornerstoneCard = Color(0xFF17171A)
private val CornerstoneRed = Color(0xFFE63946)
private val CornerstoneText = Color(0xFFF5F5F5)
private val CornerstoneMutedText = Color(0xFFB7B7B7)

@Composable
fun PlaylistsScreen(
    repository: ComboLibraryRepository,
    onBack: () -> Unit,
    onRunPlaylist: (List<PlaylistComboEntity>) -> Unit
) {
    var selectedPlaylistId by rememberSaveable {
        mutableStateOf<Long?>(null)
    }

    val openPlaylistId = selectedPlaylistId

    if (openPlaylistId != null) {
        PlaylistDetailScreen(
            repository = repository,
            playlistId = openPlaylistId,
            onBack = {
                selectedPlaylistId = null
            },
            onRunPlaylist = onRunPlaylist
        )
    } else {
        PlaylistListScreen(
            repository = repository,
            onBack = onBack,
            onOpenPlaylist = { playlistId ->
                selectedPlaylistId = playlistId
            }
        )
    }
}

@Composable
private fun PlaylistListScreen(
    repository: ComboLibraryRepository,
    onBack: () -> Unit,
    onOpenPlaylist: (Long) -> Unit
) {
    val scope = rememberCoroutineScope()

    val playlists by repository
        .observePlaylists()
        .collectAsState(initial = emptyList())

    var playlistName by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CornerstoneBlack)
            .systemBarsPadding()
            .imePadding()
            .padding(20.dp)
    ) {
        OutlinedButton(
            onClick = onBack
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Combo playlists",
            color = CornerstoneText,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Create your own boxing, Muay Thai, or MMA combo libraries.",
            color = CornerstoneMutedText
        )

        Spacer(modifier = Modifier.height(22.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = CornerstoneCard,
            shape = RoundedCornerShape(22.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "New playlist",
                    color = CornerstoneText,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = playlistName,
                    onValueChange = { playlistName = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Playlist name")
                    },
                    placeholder = {
                        Text("Example: Morning Session")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        scope.launch {
                            repository.createPlaylist(playlistName)
                            playlistName = ""
                        }
                    },
                    enabled = playlistName.trim().isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CornerstoneRed
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Create playlist")
                }
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (playlists.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = CornerstoneCard,
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "No playlists yet.",
                                color = CornerstoneText,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Create one above to start saving combos.",
                                color = CornerstoneMutedText
                            )
                        }
                    }
                }
            } else {
                items(
                    items = playlists,
                    key = { playlist -> playlist.id }
                ) { playlist ->
                    PlaylistCard(
                        playlist = playlist,
                        onOpen = {
                            onOpenPlaylist(playlist.id)
                        },
                        onDelete = {
                            scope.launch {
                                repository.deletePlaylist(playlist.id)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PlaylistCard(
    playlist: ComboPlaylistEntity,
    onOpen: () -> Unit,
    onDelete: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onOpen()
            },
        color = CornerstoneCard,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = playlist.name,
                color = CornerstoneText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Tap to open playlist",
                color = CornerstoneMutedText
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = onDelete
                ) {
                    Text(
                        text = "Delete",
                        color = CornerstoneRed
                    )
                }
            }
        }
    }
}