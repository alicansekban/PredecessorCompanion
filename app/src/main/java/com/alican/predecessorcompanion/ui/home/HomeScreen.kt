package com.alican.predecessorcompanion.ui.home

import PlayerSearchItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.ui.home.components.HeroesScreen

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState.screenType) {
        if (uiState.screenType == ScreenType.SEARCH) {
            focusRequester.requestFocus()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchIcon(uiState, viewModel, focusManager)
        HeroesSection(uiState)
        SearchSection(uiState, focusRequester, viewModel)
    }
}

@Composable
fun SearchIcon(uiState: HomeUIStateModel, viewModel: HomeViewModel, focusManager: FocusManager) {
    AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "",
            modifier = Modifier.clickable {
                viewModel.updateEvent(HomeUIStateEvents.ChangeScreenType(screenType = ScreenType.SEARCH))
            }
        )
    }
    AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            modifier = Modifier
                .clickable {
                    viewModel.updateEvent(HomeUIStateEvents.ChangeScreenType(ScreenType.HEROES))
                    focusManager.clearFocus()
                }
                .padding(12.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeroesSection(uiState: HomeUIStateModel) {
    AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            uiState.heroes.groupBy { it.role }.forEach { (role, heroes) ->
                Text(text = role)
                FlowRow(modifier = Modifier.wrapContentSize()) {
                    heroes.forEach { hero ->
                        HeroesScreen(hero = hero)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchSection(
    uiState: HomeUIStateModel,
    focusRequester: FocusRequester,
    viewModel: HomeViewModel
) {
    AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = uiState.searchQuery,
                onValueChange = {
                    viewModel.updateEvent(HomeUIStateEvents.UpdateSearchQuery(it))
                },
                keyboardActions = KeyboardActions(onSearch = {
                    viewModel.updateEvent(HomeUIStateEvents.OnContinue)
                }),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                shape = RoundedCornerShape(20.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .clickable {
                                viewModel.updateEvent(HomeUIStateEvents.UpdateSearchQuery(""))
                            }
                            .padding(12.dp)
                            .align(Alignment.End)
                    )
                },
                label = { Text(text = "Search") }
            )

            FlowRow(modifier = Modifier.wrapContentSize()) {
                uiState.players.forEach { player ->
                    PlayerSearchItem(player = player) { }
                }
            }
        }
    }
}
