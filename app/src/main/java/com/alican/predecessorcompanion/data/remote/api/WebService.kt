package com.alican.predecessorcompanion.data.remote.api

import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.data.remote.response.heroes_statistics.HeroesStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.items.ItemsResponseItem
import com.alican.predecessorcompanion.data.remote.response.leaderBoard.LeaderBoardResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerHeroStatisticsResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerMatchesResponse
import com.alican.predecessorcompanion.data.remote.response.player.PlayerStatisticsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("players.json?filter[include_inactive]=[0,1]&filter[include_unranked]=[0,1]")
    suspend fun getLeaderBoard(
        @Query("page") page: Int,
        @Query("filter[name]") name: String,
    ): List<LeaderBoardResponse>

    @GET("players/{player_id}.json")
    suspend fun getPlayerDetail(
        @Path("player_id") player_id: String,
    ): LeaderBoardResponse

    @GET("players/{player_id}/statistics.json")
    suspend fun getPlayerStatistics(
        @Path("player_id") player_id: String,
    ): PlayerStatisticsResponse

    @GET("players/{player_id}/hero_statistics.json")
    suspend fun getPlayerHeroStatistics(
        @Path("player_id") player_id: String,
    ): PlayerHeroStatisticsResponse

    @GET("players/{player_id}/matches.json")
    suspend fun getPlayerMatches(
        @Path("player_id") player_id: String,
    ): PlayerMatchesResponse

    @GET("heroes.json")
    suspend fun getHeroes(): List<HeroesResponse>

    @GET("dashboard/hero_statistics.json")
    suspend fun getHeroStatistics(
        @Query("time_frame") timeFrame: String,
        @Query("hero_ids") heroId: String,
    ): HeroesStatisticsResponse

    @GET("items.json")
    suspend fun getItems(): List<ItemsResponseItem>

    @GET("items/{item_name}.json")
    suspend fun getItemDetails(
        @Path("item_name") name: String
    ): ItemsResponseItem


}