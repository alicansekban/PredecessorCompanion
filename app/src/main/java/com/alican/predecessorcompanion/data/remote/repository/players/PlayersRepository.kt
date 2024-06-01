package com.alican.predecessorcompanion.data.remote.repository.players

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.alican.predecessorcompanion.custom.paging.PlayerPagingSource
import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.dataSource.players.PlayersRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
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

    fun playersPaging(searchQuery: String): Flow<PagingData<PlayerUIModel>> {
        return Pager(PagingConfig(pageSize = 20)) {
            PlayerPagingSource(webService, searchQuery)
        }.flow.map { pagingData ->
            pagingData.map { response ->
                response.toUIModel()
            }
        }
    }
}

data class PlayerUIModel(val name: String = "")

fun LeaderBoardResponse.toUIModel(): PlayerUIModel {
    return PlayerUIModel(
        name = this.display_name ?: ""
    )
}
