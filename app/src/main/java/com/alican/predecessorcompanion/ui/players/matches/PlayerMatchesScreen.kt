package com.alican.predecessorcompanion.ui.players.matches

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
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerMatchesUIModel

@Composable
fun PlayerMatchesScreen(
    modifier: Modifier = Modifier,
    playerId: String,
    viewModel: PlayerMatchesViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = playerId) {
        viewModel.getMatches(playerId = playerId)
    }

    val statistics by viewModel.matches.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        when (statistics) {
            is UIState.Empty -> {}
            is UIState.Error -> {}
            is UIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UIState.Success -> {
                val response = (statistics as UIState.Success<List<PlayerMatchesUIModel>>).response
                StatelessMatches(matches = response)
            }
        }
    }
}


@Composable
fun StatelessMatches(
    modifier: Modifier = Modifier,
    matches: List<PlayerMatchesUIModel>
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        matches.forEach { match ->
            Text(
                text = match.gameMode
            )
        }
    }
}

