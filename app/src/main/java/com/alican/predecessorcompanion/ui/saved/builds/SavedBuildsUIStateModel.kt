package com.alican.predecessorcompanion.ui.saved.builds

import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel

data class SavedBuildsUIStateModel(
    val savedBuilds: List<BuildsUIModel> = emptyList(),
    val isRemoved: Boolean = false,
    val isLoading: Boolean = false,
)