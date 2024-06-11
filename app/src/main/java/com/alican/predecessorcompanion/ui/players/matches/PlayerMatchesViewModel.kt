package com.alican.predecessorcompanion.ui.players.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerMatchesUIModel
import com.alican.predecessorcompanion.domain.use_case.players.PlayerMatchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerMatchesViewModel @Inject constructor(
    private val playerMatchesUseCase: PlayerMatchesUseCase
) : ViewModel() {

    private val _matches = MutableStateFlow<UIState<List<PlayerMatchesUIModel>>>(UIState.Empty())

    val matches: StateFlow<UIState<List<PlayerMatchesUIModel>>>
        get() = _matches.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(), UIState.Empty()
        )

    fun getMatches(playerId: String) {
        if (_matches.value is UIState.Empty)
            viewModelScope.launch {
                playerMatchesUseCase.invoke(playerId = playerId).collect { state ->
                    _matches.emit(state)
                }
            }
    }
}