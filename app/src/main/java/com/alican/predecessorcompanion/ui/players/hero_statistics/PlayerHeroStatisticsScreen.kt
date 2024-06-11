package com.alican.predecessorcompanion.ui.players.hero_statistics

import com.alican.predecessorcompanion.domain.ui_model.players.PlayerHeroStatisticsUIModel

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

@Composable
fun PlayerHeroStatisticsScreen(
    modifier: Modifier = Modifier,
    playerId: String,
    viewModel: PlayerHeroStatisticsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = playerId) {
        viewModel.getMatches(playerId = playerId)
    }

    val heroStats by viewModel.heroStats.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        when (heroStats) {
            is UIState.Empty -> {}
            is UIState.Error -> {}
            is UIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is UIState.Success -> {
                val response =
                    (heroStats as UIState.Success<List<PlayerHeroStatisticsUIModel>>).response
                StatelessMatches(heroStats = response)
            }
        }
    }
}


@Composable
fun StatelessMatches(
    modifier: Modifier = Modifier,
    heroStats: List<PlayerHeroStatisticsUIModel>
) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        heroStats.forEach { heroStats ->
            Text(
                text = heroStats.heroName
            )
        }
    }
}

