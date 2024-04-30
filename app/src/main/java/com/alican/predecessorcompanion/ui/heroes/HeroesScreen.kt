package com.alican.predecessorcompanion.ui.heroes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HeroesScreen(
    viewModel: HeroesViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.isLoading) {

    }
    AnimatedVisibility(visible = uiState.isSuccess) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {

            uiState.heroes.forEach {
                Text(text = it.heroName)
            }
        }
    }

}