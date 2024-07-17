package com.alican.predecessorcompanion.ui.home

import com.alican.predecessorcompanion.custom.drawer.HeroStatisticsFilterModel
import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesStatisticsUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.utils.getHeroStatisticsFiltersList

sealed class HomeUIStateEvents {

    data class UpdateSearchQuery(val searchQuery: String) : HomeUIStateEvents()

    data object OnContinue : HomeUIStateEvents()

    data class ChangeScreenType(val screenType: ScreenType) : HomeUIStateEvents()

    data class FilterSelected(val selectedFilter: HeroStatisticsFilterModel) : HomeUIStateEvents()
}

data class HomeUIStateModel(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val players: List<PlayersUIModel> = emptyList(),
    val heroes: List<HeroesStatisticsUIModel> = emptyList(),
    val screenType: ScreenType = ScreenType.HEROES,
    val isSearchResultEmpty: Boolean = false,
    val filters: List<HeroStatisticsFilterModel> = getHeroStatisticsFiltersList()
)

enum class ScreenType {
    SEARCH, HEROES
}