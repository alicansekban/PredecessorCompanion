package com.alican.predecessorcompanion.data.remote.repository.players

import com.alican.predecessorcompanion.data.local.entity.PlayersEntity
import com.alican.predecessorcompanion.data.remote.dataSource.players.PlayersLocalDataSource
import com.alican.predecessorcompanion.data.remote.dataSource.players.PlayersRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerCommonTeammatesResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerHeroStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerMatchesResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerStatisticsResponse
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayersRepository @Inject constructor(
    private val remoteDataSource: PlayersRemoteDataSource,
    private val localDataSource: PlayersLocalDataSource,
) {
    suspend fun searchPlayers(searchQuery: String): ResultWrapper<List<LeaderBoardResponse>> {
        return remoteDataSource.searchPlayers(searchQuery = searchQuery)
    }

    suspend fun getPlayersPaging(
        searchQuery: String,
        page: Int
    ): ResultWrapper<List<LeaderBoardResponse>> {
        return remoteDataSource.getPlayersPaging(page = page, searchQuery = searchQuery)
    }

    fun getPlayers(): Flow<List<PlayersEntity>> {
        return flow {
            localDataSource.getPlayers()
        }
    }

    suspend fun addPlayerToFavorite(player: PlayersEntity) {
        try {
            localDataSource.insertPlayer(player)
        } catch (e: Exception) {
            throw e
        }
    }

    fun removePlayerFromSaved(playerId: String) {
        try {
            localDataSource.removePlayer(playerId)
        } catch (e: Exception) {
            throw e
        }
    }

    fun isPlayerFavorite(playerId: String): PlayersEntity? =
        localDataSource.isPlayerFavorite(playerId = playerId)



    suspend fun getPlayerDetails(playerId: String): ResultWrapper<LeaderBoardResponse> {
        return remoteDataSource.getPlayerDetail(playerId)
    }

    suspend fun getPlayerStatistics(playerId: String): ResultWrapper<PlayerStatisticsResponse> {
        return remoteDataSource.getPlayerStatistics(playerId)
    }

    suspend fun getPlayerHeroStatistics(playerId: String): ResultWrapper<PlayerHeroStatisticsResponse> {
        return remoteDataSource.getPlayerHeroStatistics(playerId)
    }

    suspend fun getPlayerMatches(playerId: String): ResultWrapper<PlayerMatchesResponse> {
        return remoteDataSource.getPlayerMatches(playerId)
    }

    suspend fun getPlayerTeammates(playerId: String): ResultWrapper<PlayerCommonTeammatesResponse> {
        return remoteDataSource.getPlayerTeammates(playerId)
    }
}
