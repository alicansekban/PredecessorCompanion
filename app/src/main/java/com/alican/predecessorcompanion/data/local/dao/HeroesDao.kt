package com.alican.predecessorcompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alican.predecessorcompanion.data.local.entity.HeroesEntity


@Dao
interface HeroesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroesEntity>)

    @Query("SELECT * FROM heroes WHERE heroId = :heroId")
    fun getHeroDetail(heroId: String): HeroesEntity

    @Query("SELECT * FROM heroes")
    fun getHeroes(): List<HeroesEntity>

    @Query("DELETE FROM heroes")
    fun removeList()

}