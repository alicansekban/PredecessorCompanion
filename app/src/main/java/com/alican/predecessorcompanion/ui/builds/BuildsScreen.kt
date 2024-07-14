package com.alican.predecessorcompanion.ui.builds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.ui.saved.builds.BuildItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BuildsScreen(viewModel: BuildsScreenViewModel = hiltViewModel()) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {
            items(uiState.builds) {
                BuildItem(build = it) {
                    viewModel.updateUIEvents(event = BuildsUIStateEvents.FavoriteButtonClicked(it))
                }
            }
        }
    }
    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (index != null && index >= uiState.builds.size - 1)
                    viewModel.updateUIEvents(
                        BuildsUIStateEvents.GetNextPage(
                            page = uiState.page.plus(
                                1
                            )
                        )
                    )

            }
    }
}