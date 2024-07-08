package com.alican.predecessorcompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alican.predecessorcompanion.data.local.entity.PlayersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayersEntity)

    @Query("SELECT * FROM players WHERE playerId = :playerId")
    fun isPlayerFavorite(playerId: String): PlayersEntity?

    @Query("SELECT * FROM players")
    fun getPlayers(): Flow<List<PlayersEntity>>

    @Query("DELETE FROM players Where playerId = :playerId")
    fun removePlayer(playerId: String)
}