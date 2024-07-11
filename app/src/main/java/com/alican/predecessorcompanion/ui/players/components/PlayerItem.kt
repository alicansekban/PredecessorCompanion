package com.alican.predecessorcompanion.ui.players.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

@Composable
fun PlayerItem(
    player: PlayersUIModel, openPlayerDetail: (PlayersUIModel) -> Unit,
    onFavoriteClicked: (PlayersUIModel) -> Unit
) {
    var isFavorite by remember {
        mutableStateOf(player.isFavorite)
    }
    val icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                openPlayerDetail(player)
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
            Image(imageVector = icon, contentDescription = "", modifier = Modifier.clickable {
                onFavoriteClicked(player)
                isFavorite = isFavorite.not()
            })
            ImageView(imageUrl = player.rankIcon, modifier = Modifier.size(30.dp))
            Text(text = player.mmr)
        }
    }
}