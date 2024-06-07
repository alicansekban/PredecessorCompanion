package com.alican.predecessorcompanion.ui.players.detail

import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel

data class PlayerDetailUIState (
    val statistics: PlayerStatisticsUIModel = PlayerStatisticsUIModel(),
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val isEmpty: Boolean = false
)