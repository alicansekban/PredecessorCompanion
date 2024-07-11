package com.alican.predecessorcompanion.ui.saved.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.domain.use_case.players.GetSavedPlayersUseCase
import com.alican.predecessorcompanion.domain.use_case.players.RemovePlayerFromSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPlayersViewModel @Inject constructor(
    private val getSavedPlayersUseCase: GetSavedPlayersUseCase,
    private val removeFromSavedPlayersUseCase: RemovePlayerFromSavedUseCase
) :
    ViewModel() {
    private val _uiState = MutableStateFlow(SavedPlayersUIStateModel())
    val uiState: StateFlow<SavedPlayersUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, SavedPlayersUIStateModel()
        )

    init {
        getSavedPlayers()
    }


    private fun getSavedPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedPlayersUseCase.invoke().collect { uiState ->
                when (uiState) {
                    is UIState.Empty -> _uiState.emit(SavedPlayersUIStateModel())
                    is UIState.Success -> {
//                        _uiState.emit(_uiState.value.copy(savedPlayers = uiState.response))

                        _uiState.value = _uiState.value.copy(
                            savedPlayers = uiState.response,
                        )
                    }

                    is UIState.Error -> TODO()
                    is UIState.Loading -> TODO()
                }
            }
        }
    }

    fun removeFromSavedPlayers(model: PlayersUIModel) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }


    fun toastMessageShowed() {
        _uiState.value = _uiState.value.copy(isRemoved = false)
    }
}