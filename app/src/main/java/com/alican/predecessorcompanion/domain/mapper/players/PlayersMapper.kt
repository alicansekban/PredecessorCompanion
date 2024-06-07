package com.alican.predecessorcompanion.domain.mapper.players

import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerStatisticsResponse
import com.alican.predecessorcompanion.domain.ui_model.players.FavHeroUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.utils.Constant

fun LeaderBoardResponse.toUIModel(): PlayersUIModel {
    return PlayersUIModel(
        name = display_name ?: "",
        id = id ?: "",
        rankIcon = (Constant.BASE_URL + this.rank_image),
        rankActive = this.rank_active ?: "",
        rankTitle = rank_title ?: "",
        mmr = mmr ?: 0.0
    )
}

fun PlayerStatisticsResponse.toUIModel(): PlayerStatisticsUIModel {
    val favHero = FavHeroUIModel(
        favHero = favorite_hero?.display_name ?: "",
        favHeroId = favorite_hero?.id ?: "",
        favHeroImage = (Constant.BASE_URL + (this.favorite_hero?.image ?: ""))
    )
    return PlayerStatisticsUIModel(
        favRole = favorite_role ?: "",
        winrate = winrate ?: 0.0,
        favHero = favHero
    )
}