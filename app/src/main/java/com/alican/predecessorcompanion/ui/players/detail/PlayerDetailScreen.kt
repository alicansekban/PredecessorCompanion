package com.alican.predecessorcompanion.ui.players.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateBefore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.ui.players.statistics.PlayerStatisticsScreen
import java.util.Locale

@Composable
fun PlayerDetailScreen(
    viewModel: PlayerDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val detailState by viewModel.detailState.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {
        when (detailState) {
            is UIState.Empty -> {
                Text(
                    text = "No player data available.",
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            is UIState.Error -> {
                val errorMessage = (detailState as UIState.Error).errorMessage
                Text(
                    text = "Error: $errorMessage",
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Red,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }

            is UIState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is UIState.Success -> {
                val response = (detailState as UIState.Success<PlayersUIModel>).response
                StatelessDetailScreen(
                    response,
                    uiState.statistics,
                    onBackClick = onBackClick,
                    playerId = viewModel.playerId
                )
            }
        }
    }
}

@Composable
fun StatelessDetailScreen(
    model: PlayersUIModel,
    statistic: PlayerStatisticsUIModel,
    onBackClick: () -> Unit,
    playerId: String
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                )
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            ImageView(
                imageUrl = model.rankIcon,
                modifier = Modifier.size(120.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .clip(RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                PlayerStatisticsScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 20.dp),
                    playerId = playerId
                )


                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    text = model.name,
                    fontSize = 20.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )

                val formattedMmr = String.format(Locale.ROOT, "%.4f", model.mmr).take(4)
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = formattedMmr,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = model.rankTitle)
            }

        }
        Icon(imageVector = Icons.AutoMirrored.Filled.NavigateBefore,
            "",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(42.dp)
                .padding(start = 16.dp, top = 16.dp)
                .clickable {
                    onBackClick()
                }
                .shadow(50.dp),
            tint = Color.White)
    }
}
