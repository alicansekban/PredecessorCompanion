package com.alican.predecessorcompanion.ui.players

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.alican.predecessorcompanion.data.remote.repository.players.PlayerUIModel

@Composable
fun PlayersScreen(viewModel: PlayersViewModel = hiltViewModel()) {

    val players: LazyPagingItems<PlayerUIModel> = viewModel.players.collectAsLazyPagingItems()
    val listState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = listState) {
            items(players.itemCount) {
                PlayerItem(player = players[it]!!)
            }
        }
        if (players.loadState.append is LoadState.Error) {
            Text(text = "error")
        }
    }
}

@Composable
fun PlayerItem(player: PlayerUIModel) {
    Text(
        text = player.name,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(16.dp)
    )
}