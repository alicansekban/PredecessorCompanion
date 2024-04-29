package com.alican.predecessorcompanion.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "deneme")
data class ExampleEntity(
    @PrimaryKey
    val id : Int = 0
)
