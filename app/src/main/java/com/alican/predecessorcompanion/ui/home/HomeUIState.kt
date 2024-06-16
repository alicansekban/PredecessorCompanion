package com.alican.predecessorcompanion.ui.home

import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

sealed class HomeUIStateEvents {

    data class UpdateSearchQuery(val searchQuery: String) : HomeUIStateEvents()

    data object OnContinue : HomeUIStateEvents()

    data class ChangeScreenType(val screenType: ScreenType) : HomeUIStateEvents()
}

data class HomeUIStateModel(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val players: List<PlayersUIModel> = emptyList(),
    val heroes: List<HeroesUIModel> = emptyList(),
    val screenType: ScreenType = ScreenType.HEROES,
    val isSearchResultEmpty: Boolean = false
)

enum class ScreenType {
    SEARCH, HEROES
}