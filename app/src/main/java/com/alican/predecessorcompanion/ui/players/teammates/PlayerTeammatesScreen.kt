package com.alican.predecessorcompanion.ui.players.teammates

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
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerTeammateUIModel

@Composable
fun PlayerTeammatesScreen(
    modifier: Modifier = Modifier,
    playerId: String,
    viewModel: PlayerTeammatesViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = playerId) {
        viewModel.getTeammates(playerId = playerId)
    }

    val teammates by viewModel.teammates.collectAsStateWithLifecycle()

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
                val response = (teammates as UIState.Success<List<PlayerTeammateUIModel>>).response
                StatelessTeammates(teammates = response)
            }
        }
    }
}


@Composable
fun StatelessTeammates(
    modifier: Modifier = Modifier,
    teammates: List<PlayerTeammateUIModel>
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        teammates.forEach { teammate ->
            Text(text = teammate.name)
        }
    }
}