package com.alican.predecessorcompanion.ui.home

import PlayerSearchItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier
            .fillMaxWidth(0.60f)
            .fillMaxHeight()
            .background(Color.White),
        drawerContent = {
            Column {
                uiState.filters.forEach {
                    Text(text = it.title, modifier = Modifier.clickable {
                        viewModel.updateEvent(event = HomeUIStateEvents.FilterSelected(it))
                        scope.launch {
                            drawerState.close()
                        }
                    })
                }
            }

        },
        content = {
            Column(modifier = Modifier.fillMaxSize()) {
                AnimatedVisibility(visible = uiState.screenType == ScreenType.HEROES) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            viewModel.updateEvent(
                                event = HomeUIStateEvents.ChangeScreenType(
                                    screenType = ScreenType.SEARCH
                                )
                            )
                        })
                }
                AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .noRippleClick {
                                viewModel.updateEvent(HomeUIStateEvents.ChangeScreenType(ScreenType.HEROES))
                                focusManager.clearFocus()
                            }
                            .padding(12.dp)
                            .align(Alignment.End))
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
                                    scope.launch {
                                        drawerState.open()
                                    }
                                    //  onHeroClick(it.toDetailModel())
                                }
                            )
                        }

                    }
                }
            }

            AnimatedVisibility(visible = uiState.screenType == ScreenType.SEARCH) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(value = uiState.searchQuery,
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
                                        viewModel.updateEvent(HomeUIStateEvents.UpdateSearchQuery(""))
                                    }
                                    .padding(12.dp)
                                    .align(Alignment.End))
                        },
                        label = { Text(text = "Search") })
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


