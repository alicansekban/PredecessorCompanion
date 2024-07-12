package com.alican.predecessorcompanion.ui.saved.players

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.ui.players.components.PlayerItem

@Composable
fun SavedPlayersScreen(viewModel: SavedPlayersViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            uiState.savedPlayers,
            key = { it.id }
        ) {
                PlayerItem(
                    player = it,
                    openPlayerDetail = {},
                    onFavoriteClicked = { player ->
                        viewModel.removeFromSavedPlayers(player)
                    }
                )
            }
    }
}
