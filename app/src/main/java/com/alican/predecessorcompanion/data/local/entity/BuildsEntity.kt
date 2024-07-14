package com.alican.predecessorcompanion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "builds")
data class BuildsEntity(
    @PrimaryKey
    val buildId: String = "",
    val title: String = ""
)