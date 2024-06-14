package com.alican.predecessorcompanion.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(value = uiState.searchQuery, onValueChange = {
            viewModel.updateEvent(event = HomeUIStateEvents.UpdateSearchQuery(it))
        }, keyboardActions = KeyboardActions(
            onSearch = {
                viewModel.updateEvent(event = HomeUIStateEvents.onContinue)
            }
        ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )
        uiState.players.forEach {
            Text(text = it.name)
        }
    }
}