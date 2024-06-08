package com.alican.predecessorcompanion.ui.players.statistics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel

@Composable
fun PlayerStatisticsScreen(
    modifier: Modifier = Modifier,
    playerId: String,
    viewModel: PlayerStatisticsViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = playerId) {
        viewModel.getStatistics(playerId = playerId)
    }

    val teammates by viewModel.statistics.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        when (teammates) {
            is UIState.Empty -> {}
            is UIState.Error -> {}
            is UIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UIState.Success -> {
                val response = (teammates as UIState.Success<PlayerStatisticsUIModel>).response
                StatelessStatistics(statistics = response)
            }
        }
    }

}


@Composable
fun StatelessStatistics(
    modifier: Modifier = Modifier,
    statistics: PlayerStatisticsUIModel
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = statistics.favRole)
    }
}