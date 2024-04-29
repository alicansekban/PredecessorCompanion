package com.alican.predecessorcompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alican.predecessorcompanion.data.local.dao.HeroesDao
import com.alican.predecessorcompanion.data.local.entity.HeroesEntity

@Database(
    entities = [HeroesEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exampleDao(): HeroesDao
}