package com.alican.predecessorcompanion.domain.ui_model.players

data class PlayerStatisticsUIModel(
    val favRole: String = "",
    val winrate: Double = 0.0,
    val favHero : FavHeroUIModel = FavHeroUIModel()
)


data class FavHeroUIModel(
    val favHeroId : String = "",
    val favHero : String = "",
    val favHeroImage : String = "",
)
