package com.alican.predecessorcompanion.data.remote.dataSource.players

import com.alican.predecessorcompanion.data.local.AppDatabase
import com.alican.predecessorcompanion.data.local.entity.PlayersEntity
import javax.inject.Inject

class PlayersLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {
    suspend fun insertPLayer(players: List<PlayersEntity>) {
        appDatabase.playersDao().insertPlayers(players)
    }

    fun getPlayers(): List<PlayersEntity> = appDatabase.playersDao().getPlayers()


    fun getPlayerDetail(playerId: String): PlayersEntity =
        appDatabase.playersDao().getPlayerDetails(playerId = playerId)

    fun removePlayer(playerId: String) =
        appDatabase.playersDao().removePlayer(playerId = playerId)
}
