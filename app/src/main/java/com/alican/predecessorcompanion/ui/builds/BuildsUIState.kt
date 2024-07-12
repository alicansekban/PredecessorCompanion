package com.alican.predecessorcompanion.ui.builds

import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel

sealed class BuildsUIStateEvents {
    data class GetNextPage(val page: Int) : BuildsUIStateEvents()

    data class FavoriteButtonClicked(val build: BuildsUIModel) : BuildsUIStateEvents()
}

data class BuildsUIStateModel(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val builds: List<BuildsUIModel> = emptyList(),
    val page: Int = 1,
)