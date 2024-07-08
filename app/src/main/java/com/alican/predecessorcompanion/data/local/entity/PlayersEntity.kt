package com.alican.predecessorcompanion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayersEntity(
    @PrimaryKey
    val playerId: String = "",
    val name: String = "",
    val rankIcon: String = "",
    val rankActive: String = "",
    val rankTitle: String = "",
    val mmr: String = ""
)

