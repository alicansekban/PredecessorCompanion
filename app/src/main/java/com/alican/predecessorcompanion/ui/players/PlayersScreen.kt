package com.alican.predecessorcompanion.ui.players

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

@Composable
fun PlayersScreen(
    viewModel: PlayersViewModel = hiltViewModel(), openPlayerDetail: (String) -> Unit
) {

    val players: LazyPagingItems<PlayersUIModel> = viewModel.players.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(players.itemCount) {
                PlayerItem(player = players[it]!!, openPlayerDetail = openPlayerDetail)
            }
            item {
                if (players.loadState.append is LoadState.Error) {
                    Text(text = "error")
                }
                if (players.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }
        if (players.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun PlayerItem(player: PlayersUIModel, openPlayerDetail: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                openPlayerDetail(player.id)
            },
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = "${player.rankActive}.", fontWeight = FontWeight.Bold)
            Text(
                text = player.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            ImageView(imageUrl = player.rankIcon, modifier = Modifier.size(30.dp))
            val formattedMmr = String.format("%.4f", player.mmr).take(4)
            Text(text = formattedMmr)
        }
    }
}