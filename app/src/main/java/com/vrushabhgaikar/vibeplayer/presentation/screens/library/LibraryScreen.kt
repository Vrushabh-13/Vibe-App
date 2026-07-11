package com.vrushabhgaikar.vibeplayer.presentation.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vrushabhgaikar.vibeplayer.R
import com.vrushabhgaikar.vibeplayer.navigation.Routes
import com.vrushabhgaikar.vibeplayer.presentation.components.AppIcon
import com.vrushabhgaikar.vibeplayer.presentation.components.AppImage
import com.vrushabhgaikar.vibeplayer.presentation.components.AppSectionTitle
import com.vrushabhgaikar.vibeplayer.presentation.components.AppTopBar
import com.vrushabhgaikar.vibeplayer.presentation.components.VerticalSpacer
import com.vrushabhgaikar.vibeplayer.presentation.screens.home.HomeViewModel
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.components.PlaylistCard
import com.vrushabhgaikar.vibeplayer.presentation.screens.library.components.QuickActionCard
import com.vrushabhgaikar.vibeplayer.ui.theme.BlackBg
import com.vrushabhgaikar.vibeplayer.ui.theme.CardBg
import com.vrushabhgaikar.vibeplayer.ui.theme.White

@Composable
fun LibraryScreen(
    viewModel: HomeViewModel,
    navController: NavHostController,
    onOpenFavorites: () -> Unit,
    onOpenHistory: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    var playlistName by remember { mutableStateOf("") }
    val playlistList =
        viewModel.playlists.collectAsState().value
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBg)
    ) {
        AppTopBar("Library")
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {


            item { VerticalSpacer(12.dp) }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(id = R.drawable.ic_like),
                        title = stringResource(R.string.favourites),
                        onClick = onOpenFavorites
                    )

                    QuickActionCard(
                        modifier = Modifier.weight(1f),
                        icon = painterResource(id = R.drawable.ic_recent),
                        title = stringResource(R.string.history),
                        onClick = onOpenHistory
                    )
                }
            }

            item { VerticalSpacer(20.dp) }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppSectionTitle(
                        stringResource(R.string.my_playlists),
                        modifier = Modifier.weight(1f)
                    )

                    AppIcon(
                        painter = painterResource(id = R.drawable.ic_add_button),
                        contentDescription = null,
                        tint = White,
                        modifier = Modifier.clickable {
                            showDialog = true
                        }
                    )
                }
            }

            item {
                if (playlistList.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppImage(
                            painter = painterResource(id = R.drawable.img_notfound),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp)
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Text(
                            text = "No playlists created",
                            color = White
                        )
                    }
                }
            }

            // ✅ SAFE GRID (MANUAL 2 COLUMN ROW)
            items(playlistList.chunked(2)) { rowItems ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    rowItems.forEach { playlist ->
                        PlaylistCard(
                            name = playlist.name,
                            songCount = playlist.mediaIds.size,
                            onClick = {
                                navController.navigate(

                                    "${Routes.PLAYLIST_DETAILS}/${playlist.name}"
                                )
                            },
                            onEditClick = {
                                navController.navigate(
                                    "playlist_edit/${playlist.name}"
                                )
                            }
                        )

                    }

                    // balance row if only 1 item
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }


        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                containerColor = CardBg,
                title = { Text("Create Playlist", color = White) },
                text = {
                    Column {
                        Text("Enter playlist name", color = White)

                        Spacer(modifier = Modifier.padding(8.dp))

                        OutlinedTextField(
                            value = playlistName,
                            onValueChange = { playlistName = it
                                           isError = false },
                            placeholder = { Text("Playlist name") },
                            isError = isError

                        )
                        if(isError){
                            Text(
                                text = "Playlist name cannot be empty",
                                color = Color.Red
                            )
                        }
                    }
                },
                confirmButton = {
                    Text(
                        text = "Create",
                        color = White,
                        modifier = Modifier.clickable {
                            if (playlistName.isBlank()) {
                                isError = true
                                return@clickable
                            }
                            viewModel.createPlaylist(playlistName)
                            playlistName = ""
                            isError = false
                            showDialog = false
                        }
                    )
                },
                dismissButton = {
                    Text(
                        text = "Cancel",
                        color = White,
                        modifier = Modifier.clickable {
                            playlistName = ""
                            isError = false
                            showDialog = false
                        }
                    )
                }
            )
        }
    }
}


