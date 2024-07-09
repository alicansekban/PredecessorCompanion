package com.alican.predecessorcompanion.domain.ui_model.players

import kotlinx.serialization.Serializable

@Serializable
data class PlayersUIModel(
    val name: String = "",
    val id: String = "",
    val rankIcon: String = "",
    val rankActive: String = "",
    val rankTitle: String = "",
    val mmr: String = "",
    var isFavorite: Boolean = false
)
