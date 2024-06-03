package com.alican.predecessorcompanion.data.remote.dataSource.players

import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
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
}