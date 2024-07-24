package com.alican.predecessorcompanion.ui.home

import PlayerSearchItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alican.predecessorcompanion.custom.drawer.CustomModalDrawer
import com.alican.predecessorcompanion.domain.mapper.heroes.toDetailModel
import com.alican.predecessorcompanion.ui.home.components.HeroesScreen
import com.alican.predecessorcompanion.utils.HeroDetail
import com.alican.predecessorcompanion.utils.noRippleClick
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onHeroClick: (HeroDetail) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(uiState.screenType) {
        if (uiState.screenType == ScreenType.SEARCH) {
            focusRequester.requestFocus()
        }
    }
    CustomModalDrawer(
        drawerState = drawerState,
        title = "Filters",
        scope = scope,
        items = uiState.filters,
        onItemClicked = {
            viewModel.updateEvent(event = HomeUIStateEvents.FilterSelected(it))
            scope.launch {
                drawerState.close()
            }
        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
                        Icon(imageVector = Icons.Default.Search,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    viewModel.updateEvent(
                                        event = HomeUIStateEvents.ChangeScreenType(
                                            screenType = ScreenType.SEARCH
                                        )
                                    )
                                }
                                .padding(12.dp))
                    }
                    AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
                        Icon(imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            modifier = Modifier
                                .noRippleClick {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                    focusManager.clearFocus()
                                }
                                .padding(12.dp))
                    }
                }

                AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        uiState.heroes.sortedByDescending { it.pickRate }.forEach {
                            HeroesScreen(
                                hero = it,
                                onClick = {
                                    onHeroClick(it.toDetailModel())
                                }
                            )
                        }
                    }
                }
            }

            AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Close",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .noRippleClick {
                                    viewModel.updateEvent(
                                        HomeUIStateEvents.ChangeScreenType(
                                            ScreenType.HEROES
                                        )
                                    )
                                    focusManager.clearFocus()
                                }
                                .align(Alignment.CenterVertically)
                        )
                        OutlinedTextField(
                            value = uiState.searchQuery,
                            maxLines = 1,
                            onValueChange = {
                                viewModel.updateEvent(HomeUIStateEvents.UpdateSearchQuery(it))
                            },
                            keyboardActions = KeyboardActions(onSearch = {
                                viewModel.updateEvent(HomeUIStateEvents.OnContinue)
                            }),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text, imeAction = ImeAction.Search
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            shape = RoundedCornerShape(20.dp),
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.Close,
                                    contentDescription = "Close",
                                    modifier = Modifier
                                        .clickable {
                                            viewModel.updateEvent(
                                                HomeUIStateEvents.UpdateSearchQuery(
                                                    ""
                                                )
                                            )
                                        }
                                        .padding(12.dp))
                            },
                            label = { Text(text = "Search") }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
                        }

                        if (uiState.isSearchResultEmpty) {
                            Text(
                                text = "No results found",
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        } else {
                            FlowRow(modifier = Modifier.wrapContentSize()) {
                                uiState.players.forEach { player ->
                                    PlayerSearchItem(player = player) { }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}