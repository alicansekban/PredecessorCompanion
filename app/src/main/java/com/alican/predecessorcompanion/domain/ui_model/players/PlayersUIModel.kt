package com.alican.predecessorcompanion.domain.ui_model.players

data class PlayersUIModel(
    val name: String = "",
    val id: String = "",
    val rankIcon: String = "",
    val rankActive: String = "",
    val rankTitle: String = "",
    val mmr: Double = 0.0
)
