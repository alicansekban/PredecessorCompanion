package com.alican.predecessorcompanion.ui.players.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.domain.use_case.players.PlayerDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val getPlayerDetails: PlayerDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val playerId = checkNotNull(savedStateHandle.get<String>("player_id"))

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
    }

    private fun getPlayerDetail() {
        viewModelScope.launch {
            getPlayerDetails.invoke(playerId).collect {
                _detailState.emit(it)
            }
        }
    }
}