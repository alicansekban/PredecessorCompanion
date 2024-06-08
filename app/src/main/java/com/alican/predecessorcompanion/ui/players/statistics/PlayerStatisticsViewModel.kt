package com.alican.predecessorcompanion.ui.players.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel
import com.alican.predecessorcompanion.domain.use_case.players.PlayerStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerStatisticsViewModel @Inject constructor(
    private val playerStatisticsUseCase: PlayerStatisticsUseCase
) : ViewModel() {

    private val _statistics = MutableStateFlow<UIState<PlayerStatisticsUIModel>>(UIState.Empty())

    val statistics: StateFlow<UIState<PlayerStatisticsUIModel>>
        get() = _statistics.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(), UIState.Empty()
        )

    fun getStatistics(playerId: String) {
        if (_statistics.value is UIState.Empty)
            viewModelScope.launch {
                playerStatisticsUseCase.invoke(playerId = playerId).collect { state ->
                    _statistics.emit(state)
                }
            }
    }
}