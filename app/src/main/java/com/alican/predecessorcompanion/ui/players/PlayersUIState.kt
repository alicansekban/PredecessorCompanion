package com.alican.predecessorcompanion.ui.players

import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

sealed class PlayersUIStateEvents {
    data class GetNextPage(val page: Int) : PlayersUIStateEvents()

    data class FavoriteButtonClicked(val player: PlayersUIModel) : PlayersUIStateEvents()
}

data class PlayersUIStateModel(
    val players: List<PlayersUIModel> = emptyList(),
    val page: Int = 1,
    val isLoading: Boolean = false
)