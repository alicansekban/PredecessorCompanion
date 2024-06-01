package com.alican.predecessorcompanion.domain.mapper.players

import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel

fun LeaderBoardResponse.toUIModel() : PlayersUIModel {
    return PlayersUIModel(
        name = display_name ?: "",
        id = id ?: "",
        rankIcon = rank_image ?: "",
        rank = rank_title ?: ""
    )
}