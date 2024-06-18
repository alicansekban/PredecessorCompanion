package com.alican.predecessorcompanion.data.remote.dataSource.heroes

import com.alican.predecessorcompanion.data.local.AppDatabase
import com.alican.predecessorcompanion.data.local.entity.HeroesEntity
import javax.inject.Inject

class HeroesLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) {

    suspend fun insertHeroes(heroes: List<HeroesEntity>) {
        appDatabase.heroesDao().insertHeroes(heroes = heroes)
    }

    fun getHeroes(): List<HeroesEntity> = appDatabase.heroesDao().getHeroes()


    fun getHeroDetail(heroId: String): HeroesEntity =
        appDatabase.heroesDao().getHeroDetail(heroId = heroId)

    fun deleteHeroesList() = appDatabase.heroesDao().removeList()
}