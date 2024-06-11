package com.alican.predecessorcompanion.ui.players.hero_statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerHeroStatisticsUIModel
import com.alican.predecessorcompanion.domain.use_case.players.PlayerHeroStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerHeroStatisticsViewModel @Inject constructor(
    private val playerHeroStatUseCase: PlayerHeroStatisticsUseCase
) : ViewModel() {
    private val _heroStats =
        MutableStateFlow<UIState<List<PlayerHeroStatisticsUIModel>>>(UIState.Empty())

    val heroStats: StateFlow<UIState<List<PlayerHeroStatisticsUIModel>>>
        get() = _heroStats.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(), UIState.Empty()
        )

    fun getMatches(playerId: String) {
        if (_heroStats.value is UIState.Empty)
            viewModelScope.launch {
                playerHeroStatUseCase.invoke(playerId = playerId).collect { state ->
                    _heroStats.emit(state)
                }
            }
    }
}