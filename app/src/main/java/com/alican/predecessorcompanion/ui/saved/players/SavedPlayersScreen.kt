package com.alican.predecessorcompanion.ui.saved.players

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.custom.loading.LoadingDialog
import com.alican.predecessorcompanion.ui.players.components.PlayerItem

@Composable
fun SavedPlayersScreen(viewModel: SavedPlayersViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        LoadingDialog()
    } else {
        Column {
            uiState.savedPlayers.forEach { player ->
                PlayerItem(
                    player = player,
                    openPlayerDetail = {},
                    onFavoriteClicked = {}
                )
            }
        }
    }
}
