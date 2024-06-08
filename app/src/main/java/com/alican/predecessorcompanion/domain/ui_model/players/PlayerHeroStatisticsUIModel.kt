package com.alican.predecessorcompanion.domain.ui_model.players

data class PlayerHeroStatisticsUIModel(
    val matchCount: Int = 0,
    val winrate: Double = 0.0,
    val avgKills: Int = 0,
    val maxKills: Int = 0,
    val largestMultiKill: Int = 0,
    val largestKillingSpree: Int = 0,
    val totalPerfScore: Double = 0.0,
    val avgPerfScore: Double = 0.0,
    val maxPerfScore: Double = 0.0
)
