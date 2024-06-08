package com.alican.predecessorcompanion.data.remote.dataSource.players

import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerHeroStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerMatchesResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerStatisticsResponse
import com.alican.predecessorcompanion.utils.ResultWrapper
import com.alican.predecessorcompanion.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PlayersRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun searchPlayers(searchQuery : String) : ResultWrapper<List<LeaderBoardResponse>> {
        return safeApiCall(Dispatchers.IO) {webService.getLeaderBoard(page = 0, name =searchQuery )}
    }

    suspend fun getPlayerDetail(playerId: String) : ResultWrapper<LeaderBoardResponse> {
        return safeApiCall(Dispatchers.IO) {webService.getPlayerDetail(playerId)}
    }

    suspend fun getPlayerStatistics(playerId: String) : ResultWrapper<PlayerStatisticsResponse> {
        return safeApiCall(Dispatchers.IO) {webService.getPlayerStatistics(playerId)}
    }

    suspend fun getPlayerHeroStatistics(playerId: String) : ResultWrapper<PlayerHeroStatisticsResponse> {
        return safeApiCall(Dispatchers.IO) {webService.getPlayerHeroStatistics(playerId)}
    }

    suspend fun getPlayerMatches(playerId: String) : ResultWrapper<PlayerMatchesResponse> {
        return safeApiCall(Dispatchers.IO) {webService.getPlayerMatches(playerId)}
    }
}