package com.alican.predecessorcompanion.data.remote.repository.heroes

import com.alican.predecessorcompanion.data.local.entity.HeroesEntity
import com.alican.predecessorcompanion.data.remote.dataSource.heroes.HeroesLocalDataSource
import com.alican.predecessorcompanion.data.remote.dataSource.heroes.HeroesRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.heroes_statistics.HeroesStatisticsResponse
import com.alican.predecessorcompanion.domain.mapper.heroes.toEntity
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val remoteDataSource: HeroesRemoteDataSource,
    private val localDataSource: HeroesLocalDataSource
) {
    suspend fun getHeroes(): Flow<ResultWrapper<List<HeroesEntity>>> {
        return flow {
            when (val apiData = remoteDataSource.getHeroes()) {
                is ResultWrapper.GenericError -> {
                    emit(ResultWrapper.GenericError())
                }

                ResultWrapper.Loading -> {
                    emit(ResultWrapper.Loading)
                }

                ResultWrapper.NetworkError -> {
                    emit(ResultWrapper.NetworkError)
                }

                is ResultWrapper.Success -> {
                    val response = apiData.value
                    localDataSource.insertHeroes(response.map { it.toEntity() })
                    emit(ResultWrapper.Success(localDataSource.getHeroes()))
                }
            }

        }
    }

    suspend fun getHeroesStatistics(timeFrame: String): ResultWrapper<HeroesStatisticsResponse> {
        return remoteDataSource.getHeroesStatistics(timeFrame = timeFrame)
    }

    fun getHeroDetail(heroId: String): HeroesEntity {
        return localDataSource.getHeroDetail(heroId = heroId)
    }

}