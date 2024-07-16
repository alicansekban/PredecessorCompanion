package com.alican.predecessorcompanion.data.remote.dataSource.heroes

import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.data.remote.response.heroes_statistics.HeroesStatisticsResponse
import com.alican.predecessorcompanion.utils.ResultWrapper
import com.alican.predecessorcompanion.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HeroesRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getHeroes() : ResultWrapper<List<HeroesResponse>> {
        return safeApiCall(Dispatchers.IO) {webService.getHeroes()}
    }

    suspend fun getHeroesStatistics(timeFrame: String): ResultWrapper<HeroesStatisticsResponse> {
        return safeApiCall(Dispatchers.IO) { webService.getHeroStatistics(timeFrame = timeFrame) }
    }
}