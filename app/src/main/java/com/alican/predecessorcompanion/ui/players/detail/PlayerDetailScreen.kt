package com.alican.predecessorcompanion.ui.players.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

@Composable
fun PlayerDetailScreen(viewModel: PlayerDetailViewModel = hiltViewModel()) {

    val detailState by viewModel.detailState.collectAsStateWithLifecycle()

    when (detailState) {
        is UIState.Empty -> {}
        is UIState.Error -> {}
        is UIState.Loading -> {}
        is UIState.Success -> {
            val response = (detailState as UIState.Success<PlayersUIModel>).response
            StatelessDetailScreen(response)
        }
    }

}

@Composable
fun StatelessDetailScreen(model: PlayersUIModel) {
    

}
