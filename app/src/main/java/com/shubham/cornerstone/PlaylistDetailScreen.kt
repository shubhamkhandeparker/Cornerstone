package com.shubham.cornerstone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
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
fun PlaylistDetailScreen(
    repository: ComboLibraryRepository,
    playlistId: Long,
    onBack: () -> Unit,
    onRunPlaylist: (List<PlaylistComboEntity>) -> Unit
) {
    val scope = rememberCoroutineScope()

    val playlist by repository
        .observePlaylistById(playlistId)
        .collectAsState(initial = null)

    val combos by repository
        .observeCombosForPlaylist(playlistId)
        .collectAsState(initial = emptyList())

    var movesText by rememberSaveable { mutableStateOf("") }
    var cueText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CornerstoneBlack)
            .systemBarsPadding()
            .imePadding()
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onBack
            ) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "Playlist",
                color = CornerstoneMutedText,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val currentPlaylist = playlist

        if (currentPlaylist == null) {
            Text(
                text = "Playlist not found.",
                color = CornerstoneText,
                fontWeight = FontWeight.Bold
            )
            return@Column
        }

        Text(
            text = currentPlaylist.name,
            color = CornerstoneText,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "${combos.size} combo${if (combos.size == 1) "" else "s"} saved",
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
                    text = "Add custom combo",
                    color = CornerstoneText,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = movesText,
                    onValueChange = { movesText = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Moves")
                    },
                    placeholder = {
                        Text("Example: Jab, cross, left hook")
                    },
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = cueText,
                    onValueChange = { cueText = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text("Cue / coaching note")
                    },
                    placeholder = {
                        Text("Example: Exit on angle")
                    },
                    singleLine = false,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        scope.launch {
                            repository.addCustomComboToPlaylist(
                                playlistId = playlistId,
                                moves = movesText,
                                cue = cueText
                            )

                            movesText = ""
                            cueText = ""
                        }
                    },
                    enabled = movesText.trim().isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CornerstoneRed
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add combo")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Combos",
            color = CornerstoneText,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (combos.isEmpty()) {
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
                                text = "No combos yet.",
                                color = CornerstoneText,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "Add your first combo above.",
                                color = CornerstoneMutedText
                            )
                        }
                    }
                }
            } else {
                items(
                    items = combos,
                    key = { combo -> combo.id }
                ) { combo ->
                    PlaylistComboCard(
                        combo = combo,
                        onDelete = {
                            scope.launch {
                                repository.deleteComboFromPlaylist(
                                    playlistId = playlistId,
                                    comboId = combo.id
                                )
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        Button(
            onClick = {
                onRunPlaylist(combos)
            },
            enabled = combos.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(
                containerColor = CornerstoneRed
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Run playlist")
        }
    }
}

@Composable
private fun PlaylistComboCard(
    combo: PlaylistComboEntity,
    onDelete: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = CornerstoneCard,
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = combo.moves,
                color = CornerstoneText,
                fontWeight = FontWeight.Bold
            )

            if (combo.cue.isNotBlank()) {
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = combo.cue,
                    color = CornerstoneMutedText
                )
            }

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