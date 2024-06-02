package com.alican.predecessorcompanion.data.remote.api

import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.data.remote.response.heroes_statistics.HeroesStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.items.ItemsResponseItem
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("players.json?filter[include_inactive]=[0,1]&filter[include_unranked]=[0,1]")
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
    @GET("items.json")
    suspend fun getItems(): List<ItemsResponseItem>

    @GET("items/{item_name}.json")
    suspend fun getItemDetails(
        @Path("item_name") name: String
    ): ItemsResponseItem
}