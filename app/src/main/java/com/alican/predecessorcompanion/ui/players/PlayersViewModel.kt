package com.alican.predecessorcompanion.ui.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.domain.use_case.players.AddPlayerToFavoriteUseCase
import com.alican.predecessorcompanion.domain.use_case.players.GetSavedPlayersUseCase
import com.alican.predecessorcompanion.domain.use_case.players.RemovePlayerFromSavedUseCase
import com.alican.predecessorcompanion.domain.use_case.players.SearchPlayersPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val playersPagingUseCase: SearchPlayersPagingUseCase,
    private val addPlayerToFavoriteUseCase: AddPlayerToFavoriteUseCase,
    private val removePlayerFromSavedUseCase: RemovePlayerFromSavedUseCase,
    private val getSavedPlayersUseCase: GetSavedPlayersUseCase
) :
    ViewModel() {

    private val _players = MutableStateFlow<PagingData<PlayersUIModel>>(PagingData.empty())
    val players: StateFlow<PagingData<PlayersUIModel>>
        get() = _players

    private val _uiState = MutableStateFlow(PlayersUIStateModel())
    val uiState: StateFlow<PlayersUIStateModel>
        get() = _uiState


    init {
        getPlayersNextPage(1)
        getSavedPlayers()
    }

    fun updateUIEvents(event: PlayersUIStateEvents) {
        when (event) {
            is PlayersUIStateEvents.GetNextPage -> {
                getPlayersNextPage(event.page)
            }

            is PlayersUIStateEvents.FavoriteButtonClicked -> {
                if (event.player.isFavorite) {
                    removePlayerFromFavorite(player = event.player)
                } else {
                    addToFavorite(player = event.player)
                }
            }
        }
    }
    private fun getSavedPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedPlayersUseCase.invoke().collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        val localPlayers = state.response
                        val remotePlayers = _uiState.value.players
                        val updatedRemotePlayers = remotePlayers.map { remotePlayer ->
                            if (localPlayers.any { localPlayer -> localPlayer.id == remotePlayer.id }) {
                                remotePlayer.copy(isFavorite = true)
                            } else {
                                remotePlayer.copy(isFavorite = false)
                            }
                        }
                        _uiState.update {
                            it.copy(
                                players = updatedRemotePlayers
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getPlayersNextPage(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            playersPagingUseCase.invoke(searchQuery = "", page = page).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }

                    is UIState.Success -> {
                        val currentPlayers = _uiState.value.players
                        val updatedPlayers = currentPlayers + state.response
                        _uiState.value = _uiState.value.copy(
                            players = updatedPlayers,
                            page = page,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun addToFavorite(player: PlayersUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addPlayerToFavoriteUseCase.invoke(player = player).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        player.isFavorite = true
                    }
                }
            }
        }
    }

    private fun removePlayerFromFavorite(player: PlayersUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removePlayerFromSavedUseCase.invoke(playerId = player.id).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        player.isFavorite = false
                    }
                }
            }
        }
    }
}