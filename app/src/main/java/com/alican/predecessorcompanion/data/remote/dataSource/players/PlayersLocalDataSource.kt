package com.alican.predecessorcompanion.data.remote.dataSource.players

import com.alican.predecessorcompanion.data.local.AppDatabase
import com.alican.predecessorcompanion.data.local.entity.PlayersEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayersLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {
    suspend fun insertPlayer(player: PlayersEntity) {
        appDatabase.playersDao().insertPlayer(player)
    }

    fun getPlayers(): Flow<List<PlayersEntity>> = appDatabase.playersDao().getPlayers()


    fun isPlayerFavorite(playerId: String): PlayersEntity? =
        appDatabase.playersDao().isPlayerFavorite(playerId = playerId)

    fun removePlayer(playerId: String) =
        appDatabase.playersDao().removePlayer(playerId = playerId)
}
