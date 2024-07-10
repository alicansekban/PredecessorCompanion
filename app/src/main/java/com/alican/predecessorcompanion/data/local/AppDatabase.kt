package com.alican.predecessorcompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alican.predecessorcompanion.data.local.dao.HeroesDao
import com.alican.predecessorcompanion.data.local.dao.PlayersDao
import com.alican.predecessorcompanion.data.local.entity.HeroesEntity
import com.alican.predecessorcompanion.data.local.entity.PlayersEntity

@Database(
    entities = [HeroesEntity::class, PlayersEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun heroesDao(): HeroesDao
    abstract fun playersDao(): PlayersDao
}