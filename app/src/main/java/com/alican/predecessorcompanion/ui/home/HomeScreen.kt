package com.alican.predecessorcompanion.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
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
    modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    Column(Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "", modifier.clickable {
                viewModel.updateEvent(event = HomeUIStateEvents.ChangeScreenType(screenType = ScreenType.SEARCH))
            })
        }
        AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "", modifier.clickable {
                viewModel.updateEvent(event = HomeUIStateEvents.ChangeScreenType(screenType = ScreenType.HEROES))
            })

            OutlinedTextField(value = uiState.searchQuery, onValueChange = {
                viewModel.updateEvent(event = HomeUIStateEvents.UpdateSearchQuery(it))
            }, keyboardActions = KeyboardActions(onSearch = {
                viewModel.updateEvent(event = HomeUIStateEvents.OnContinue)
            }), keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
            )
            )
            uiState.players.forEach {
                Text(text = it.name)
            }
        }
    }
}