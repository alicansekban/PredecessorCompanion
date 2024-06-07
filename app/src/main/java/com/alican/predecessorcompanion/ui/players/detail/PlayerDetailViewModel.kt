package com.alican.predecessorcompanion.ui.players.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.domain.use_case.players.PlayerDetailUseCase
import com.alican.predecessorcompanion.domain.use_case.players.PlayerStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val getPlayerDetails: PlayerDetailUseCase,
    private val getPlayerStatistics: PlayerStatisticsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val playerId = checkNotNull(savedStateHandle.get<String>("player_id"))

    private val _detailState = MutableStateFlow<UIState<PlayersUIModel>>(UIState.Empty())

    private val _uiState = MutableStateFlow(PlayerDetailUIState())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        PlayerDetailUIState()
    )

    val detailState: StateFlow<UIState<PlayersUIModel>>
        get() = _detailState.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(), UIState.Empty()
        )

    init {
        getPlayerDetail()
        getPlayerStatistics()
    }

    private fun getPlayerDetail() {
        viewModelScope.launch {
            getPlayerDetails.invoke(playerId).collect {
                _detailState.emit(it)
            }
        }
    }

    private fun getPlayerStatistics() {
        viewModelScope.launch {
            getPlayerStatistics.invoke(playerId).collect {
                when (it) {
                    is UIState.Empty -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                statistics = PlayerStatisticsUIModel(),
                                errorMessage = "",
                                isEmpty = true
                            )
                        }
                    }

                    is UIState.Error -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                statistics = PlayerStatisticsUIModel(),
                                errorMessage = it.errorMessage,
                                isEmpty = false
                            )
                        }
                    }

                    is UIState.Loading -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = true,
                                statistics = PlayerStatisticsUIModel(),
                                errorMessage = "",
                                isEmpty = false
                            )
                        }
                    }

                    is UIState.Success -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                statistics = it.response,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }
        }
    }
}