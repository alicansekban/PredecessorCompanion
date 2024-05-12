package com.alican.predecessorcompanion.data.remote.repository.players

import com.alican.predecessorcompanion.data.remote.dataSource.players.PlayersRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.utils.ResultWrapper
import javax.inject.Inject

class PlayersRepository @Inject constructor(
    private val remoteDataSource: PlayersRemoteDataSource
) {
    suspend fun searchPlayers(searchQuery : String): ResultWrapper<List<LeaderBoardResponse>> {
        return remoteDataSource.searchPlayers(searchQuery = searchQuery)
    }

}