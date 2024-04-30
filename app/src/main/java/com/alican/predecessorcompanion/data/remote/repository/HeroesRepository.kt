package com.alican.predecessorcompanion.data.remote.repository

import com.alican.predecessorcompanion.data.remote.dataSource.HeroesRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.utils.ResultWrapper
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource,
) {
    suspend fun getHeroes(): ResultWrapper<List<HeroesResponse>> {
        return remoteDataSource.getHeroes()
    }

}