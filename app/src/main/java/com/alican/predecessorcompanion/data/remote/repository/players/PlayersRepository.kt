package com.alican.predecessorcompanion.data.remote.repository.players

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.alican.predecessorcompanion.custom.paging.PlayerPagingSource
import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.dataSource.players.PlayersRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerHeroStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerStatisticsResponse
import com.alican.predecessorcompanion.domain.mapper.players.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayersRepository @Inject constructor(
    private val remoteDataSource: PlayersRemoteDataSource,
    private val webService: WebService
) {
    suspend fun searchPlayers(searchQuery: String): ResultWrapper<List<LeaderBoardResponse>> {
        return remoteDataSource.searchPlayers(searchQuery = searchQuery)
    }

    fun playersPaging(searchQuery: String): Flow<PagingData<PlayersUIModel>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PlayerPagingSource(webService, searchQuery)
        }.flow.map { pagingData ->
            pagingData.map { response ->
                response.toUIModel()
            }
        }
    }

    suspend fun getPlayerDetails(playerId: String): ResultWrapper<LeaderBoardResponse> {
        return remoteDataSource.getPlayerDetail(playerId)
    }

    suspend fun getPlayerStatistics(playerId: String): ResultWrapper<PlayerStatisticsResponse> {
        return remoteDataSource.getPlayerStatistics(playerId)
    }

    suspend fun getPlayerHeroStatistics(playerId: String): ResultWrapper<PlayerHeroStatisticsResponse>{
        return remoteDataSource.getPlayerHeroStatistics(playerId)
    }
}
