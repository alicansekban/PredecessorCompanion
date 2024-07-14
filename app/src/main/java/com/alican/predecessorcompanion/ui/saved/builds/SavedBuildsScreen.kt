package com.alican.predecessorcompanion.ui.saved.builds

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel

@Composable
fun SavedBuilsScreen(viewModel: SavedBuildsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            uiState.savedBuilds,
            key = { it.buildId }
        ) {
            BuildItem(
                build = it,
                onFavoriteClicked = { build ->
                    viewModel.removeFromSavedBuilds(build)
                }
            )
        }
    }
}

@Composable
fun BuildItem(build: BuildsUIModel, onFavoriteClicked: (BuildsUIModel) -> Unit) {

    var isFavorite by remember {
        mutableStateOf(build.isFavorite)
    }
    val icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {

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
            Text(
                text = build.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(imageVector = icon, contentDescription = "", modifier = Modifier.clickable {
                onFavoriteClicked(build)
                isFavorite = isFavorite.not()
            })
        }
    }
}