package com.alican.predecessorcompanion.domain.mapper.players

import com.alican.predecessorcompanion.data.local.entity.PlayersEntity
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.data.remote.response.player.HeroStatisticsItem
import com.alican.predecessorcompanion.data.remote.response.player.MatchesItem
import com.alican.predecessorcompanion.data.remote.response.player.PlayerStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.player.TeammatesItem
import com.alican.predecessorcompanion.domain.ui_model.players.FavHeroUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerHeroStatisticsUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerMatchesUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerStatisticsUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerTeammateUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.utils.Constant
import java.util.Locale

fun LeaderBoardResponse.toUIModel(
    isFavorite: Boolean = false
): PlayersUIModel {
    return PlayersUIModel(
        name = display_name ?: "",
        id = id ?: "",
        rankIcon = (Constant.BASE_URL + this.rank_image),
        rankActive = this.rank_active ?: "",
        rankTitle = rank_title ?: "",
        mmr = String.format(Locale.ROOT, "%.4f", mmr).take(4),
        isFavorite = isFavorite
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

fun HeroStatisticsItem.toUIModel(): PlayerHeroStatisticsUIModel {
    return PlayerHeroStatisticsUIModel(
        matchCount = match_count ?: 0,
        winrate = winrate ?: 0.0,
        avgKills = avg_kills ?: 0,
        maxKills = max_kills ?: 0,
        largestMultiKill = largest_multi_kill ?: 0,
        largestKillingSpree = largest_killing_spree ?: 0,
        totalPerfScore = total_performance_score ?: 0.0,
        avgPerfScore = avg_performance_score ?: 0.0,
        maxPerfScore = max_performance_score ?: 0.0,
        heroName = display_name ?: ""
    )
}

fun MatchesItem.toUIModel() : PlayerMatchesUIModel{
    return PlayerMatchesUIModel(
        gameMode = game_mode ?: "",
        matchId = this.id ?: "",
        region = region ?: "",
        gameDuration = game_duration.toString()
    )
}

fun TeammatesItem.toUIModel(): PlayerTeammateUIModel{
    return PlayerTeammateUIModel(
        matchesPercentage = matches_percentage.toString(),
        matchesPlayed = matches_played.toString(),
        winRate = winrate.toString(),
        name = display_name ?: ""
    )
}

fun LeaderBoardResponse.toEntity(): PlayersEntity {
    return PlayersEntity(
        name = display_name ?: "",
        playerId = id ?: "",
        rankIcon = (Constant.BASE_URL + this.rank_image),
        rankActive = this.rank_active ?: "",
        rankTitle = rank_title ?: "",
        mmr = String.format(Locale.ROOT, "%.4f", mmr).take(4)
    )
}

fun PlayersUIModel.toEntity(): PlayersEntity {
    return PlayersEntity(
        name = name,
        playerId = id,
        rankIcon = rankIcon,
        rankActive = rankActive,
        rankTitle = rankTitle,
        mmr = mmr
    )
}

fun PlayersEntity.toUIModel(): PlayersUIModel {
    return PlayersUIModel(
        name = name,
        id = playerId,
        rankIcon = rankIcon,
        rankActive = rankActive,
        rankTitle = rankTitle,
        mmr = mmr,
        isFavorite = true
    )
}