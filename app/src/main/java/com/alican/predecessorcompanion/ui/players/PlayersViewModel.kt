package com.alican.predecessorcompanion.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alican.predecessorcompanion.data.remote.repository.players.PlayersRepository
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(private val repository: PlayersRepository) :
    ViewModel() {

    private val _players = MutableStateFlow<PagingData<PlayersUIModel>>(PagingData.empty())
    val players: StateFlow<PagingData<PlayersUIModel>>
        get() = _players

    init {
        getPlayers()
    }

    private fun getPlayers() {
        viewModelScope.launch {
            repository.playersPaging("").cachedIn(viewModelScope).collect { pagingData ->
                _players.emit(pagingData)
            }
        }
    }
}