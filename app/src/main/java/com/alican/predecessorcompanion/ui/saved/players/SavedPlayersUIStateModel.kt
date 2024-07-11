package com.alican.predecessorcompanion.ui.saved.players

import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

data class SavedPlayersUIStateModel(
    val savedPlayers: List<PlayersUIModel> = emptyList(),
    val isRemoved: Boolean = false,
    val isLoading: Boolean = false,
)
