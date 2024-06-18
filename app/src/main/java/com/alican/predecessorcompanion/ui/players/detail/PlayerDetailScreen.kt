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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.ui.players.statistics.PlayerStatisticsScreen

@Composable
fun PlayerDetailScreen(
    player: PlayersUIModel,
    onBackClick: () -> Unit
) {
    StatelessDetailScreen(
        model = player,
        onBackClick = onBackClick,
        playerId = player.id
    )
}

@Composable
fun StatelessDetailScreen(
    model: PlayersUIModel,
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
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                    text = model.name,
                    fontSize = 20.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                PlayerStatisticsScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 20.dp),
                    playerId = playerId
                )

                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = model.mmr,
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
