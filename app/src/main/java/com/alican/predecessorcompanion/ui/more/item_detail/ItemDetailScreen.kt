package com.alican.predecessorcompanion.ui.more.item_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.custom.image.ImageView
import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel
import com.alican.predecessorcompanion.utils.ItemDetail

@Composable
fun ItemDetailScreen(
    viewModel: ItemDetailViewModel = hiltViewModel(),
    itemDetail: ItemDetail,
    onBackClick: () -> Unit
) {
    LaunchedEffect(itemDetail) {
        viewModel.getDetail()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        uiState.isSuccess -> {
            ItemDetailUI(item = uiState.item, onBackClick = onBackClick)
        }
    }
}

@Composable
fun ItemDetailUI(
    item: ItemUIModel, onBackClick: () -> Unit,
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
                imageUrl = item.itemImage,
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
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = item.displayName,
                    fontSize = 18.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Rarity: ", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    item.itemType?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Aggression Type: ",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    item.aggressionType?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Slot Type: ", fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    item.slotType?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
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