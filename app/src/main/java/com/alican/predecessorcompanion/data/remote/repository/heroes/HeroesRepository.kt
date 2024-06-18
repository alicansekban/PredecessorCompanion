package com.alican.predecessorcompanion.data.remote.repository.heroes

import com.alican.predecessorcompanion.data.local.entity.HeroesEntity
import com.alican.predecessorcompanion.data.remote.dataSource.heroes.HeroesLocalDataSource
import com.alican.predecessorcompanion.data.remote.dataSource.heroes.HeroesRemoteDataSource
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
            val localHeroes = localDataSource.getHeroes()
            if (localHeroes.isNotEmpty()) {
                emit(ResultWrapper.Success(localHeroes))
            } else {
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
                        ResultWrapper.Success(localDataSource.getHeroes())
                    }
                }
            }
        }
    }

}