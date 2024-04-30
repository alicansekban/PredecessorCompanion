package com.alican.predecessorcompanion.data.remote.api

import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.data.remote.response.heroes_statistics.HeroesStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("players.json")
    suspend fun getLeaderBoard(
        @Query("page") page : Int,
        @Query("filter[name]") name : String,
    ) : List<LeaderBoardResponse>

    @GET("heroes.json")
    suspend fun getHeroes() : List<HeroesResponse>

    @GET("dashboard/hero_statistics.json")
    suspend fun getHeroStatistics(
        @Query("time_frame") timeFrame : String,
        @Query("hero_ids") heroId : String,
    ) : HeroesStatisticsResponse
}