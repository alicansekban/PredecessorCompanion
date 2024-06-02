package com.alican.predecessorcompanion.domain.mapper.players

import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
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