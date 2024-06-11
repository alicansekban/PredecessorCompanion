package com.alican.predecessorcompanion.ui.players.teammates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerTeammateUIModel
import com.alican.predecessorcompanion.domain.use_case.players.PlayerTeammatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerTeammatesViewModel @Inject constructor(
    private val playerTeammatesUseCase: PlayerTeammatesUseCase
) : ViewModel() {
    private val _teammates = MutableStateFlow<UIState<List<PlayerTeammateUIModel>>>(UIState.Empty())

    val teammates: StateFlow<UIState<List<PlayerTeammateUIModel>>>
        get() = _teammates.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(), UIState.Empty()
        )

    fun getTeammates(playerId: String) {
        if (_teammates.value is UIState.Empty)
            viewModelScope.launch {
                playerTeammatesUseCase.invoke(playerId = playerId).collect { state ->
                    _teammates.emit(state)
                }
            }
    }
}