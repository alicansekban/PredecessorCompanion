package com.alican.predecessorcompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alican.predecessorcompanion.data.local.entity.PlayersEntity

@Dao
interface PlayersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayers(players: List<PlayersEntity>)

    @Query("SELECT * FROM players WHERE playerId = :playerId")
    fun getPlayerDetails(playerId: String): PlayersEntity

    @Query("SELECT * FROM players")
    fun getPlayers(): List<PlayersEntity>

    @Query("DELETE FROM players Where playerId = :playerId")
    fun removePlayer(playerId: String)
}