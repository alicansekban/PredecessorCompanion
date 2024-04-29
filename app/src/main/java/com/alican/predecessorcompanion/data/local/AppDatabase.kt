package com.alican.predecessorcompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alican.predecessorcompanion.data.local.dao.ExampleDao
import com.alican.predecessorcompanion.data.local.entity.ExampleEntity

@Database(
    entities = [ExampleEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exampleDao(): ExampleDao
}