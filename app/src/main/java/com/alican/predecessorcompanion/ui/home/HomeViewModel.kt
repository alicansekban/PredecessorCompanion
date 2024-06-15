package com.alican.predecessorcompanion.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.use_case.heroes.GetHeroesUseCase
import com.alican.predecessorcompanion.domain.use_case.players.SearchPlayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchPlayersUseCase: SearchPlayersUseCase,
    private val getHeroesUseCase: GetHeroesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIStateModel())
    val uiState: StateFlow<HomeUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, HomeUIStateModel()
        )

    init {
        getHeroes()
    }

    fun updateEvent(event: HomeUIStateEvents) {
        when (event) {
            is HomeUIStateEvents.UpdateSearchQuery -> {
                _uiState.value = _uiState.value.copy(searchQuery = event.searchQuery)
            }

            HomeUIStateEvents.OnContinue -> {
                searchPlayer()
            }

            is HomeUIStateEvents.ChangeScreenType -> {
                _uiState.value = _uiState.value.copy(screenType = event.screenType)
                if (event.screenType == ScreenType.HEROES) {
                    _uiState.value = _uiState.value.copy(searchQuery = "", players = emptyList())
                }
            }
        }
    }

    private fun searchPlayer() {
        viewModelScope.launch {
            searchPlayersUseCase.invoke(_uiState.value.searchQuery).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        val result = state.response
                        _uiState.value = _uiState.value.copy(players = result)

                    }
                }
            }
        }
    }

    private fun getHeroes() {
        viewModelScope.launch {
            getHeroesUseCase.invoke().collect { uiState ->
                when (uiState) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }

                    is UIState.Success -> {
                        _uiState.value = _uiState.value.copy(
                            heroes = uiState.response,
                        )
                    }
                }
            }
        }
    }
}