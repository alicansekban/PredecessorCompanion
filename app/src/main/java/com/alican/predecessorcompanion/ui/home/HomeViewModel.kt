package com.alican.predecessorcompanion.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.use_case.heroes.GetHeroesStatisticsUseCase
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
    private val getHeroesStatisticsUseCase: GetHeroesStatisticsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIStateModel())
    val uiState: StateFlow<HomeUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, HomeUIStateModel()
        )

    init {
        getHeroesStatistics("1W")
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

            is HomeUIStateEvents.FilterSelected -> {
                val updatedList = _uiState.value.filters.map {
                    if (it.id == event.selectedFilter.id) {
                        it.copy(isSelected = true)
                    } else {
                        it.copy(isSelected = false)
                    }
                }
                _uiState.value = _uiState.value.copy(
                    filters = updatedList
                )
                getHeroesStatistics(timeFrame = event.selectedFilter.id)
                /**
                 * burada servis isteği atılacak seçili filtre ile
                 * örnek kod getHeroesStatistics(filter = event.selectedFilter)
                 */
            }
        }
    }

    private fun searchPlayer() {
        viewModelScope.launch {
            searchPlayersUseCase.invoke(_uiState.value.searchQuery).collect { state ->
                when (state) {
                    is UIState.Empty -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }

                    is UIState.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }

                    is UIState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is UIState.Success -> {
                        val result = state.response
                        _uiState.value = _uiState.value.copy(
                            players = result,
                            isSearchResultEmpty = result.isEmpty(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getHeroesStatistics(timeFrame: String) {
        viewModelScope.launch {
            getHeroesStatisticsUseCase.invoke(timeFrame = timeFrame).collect { state ->
                when (state) {
                    is UIState.Empty -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }

                    is UIState.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }

                    is UIState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is UIState.Success -> {
                        val result = state.response
                        _uiState.value = _uiState.value.copy(
                            heroes = result,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}