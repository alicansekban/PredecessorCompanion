package com.alican.predecessorcompanion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class HeroesEntity(
    @PrimaryKey
    val heroId: String = "",
    val heroName: String = "",
    val image: String = "",
    val role: String = "",
)
