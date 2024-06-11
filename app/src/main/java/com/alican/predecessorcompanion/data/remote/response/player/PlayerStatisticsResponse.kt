package com.alican.predecessorcompanion.data.remote.response.player

import com.squareup.moshi.Json

data class PlayerStatisticsResponse(

	@Json(name="matches_played")
	val matches_played: Int? = null,

	@Json(name="favorite_role")
	val favorite_role: String? = null,

	@Json(name="avg_kdar")
	val avg_kdar: Double? = null,

	@Json(name="hours_played")
	val hours_played: Double? = null,

	@Json(name="avg_performance_score")
	val avg_performance_score: Double? = null,

	@Json(name="winrate")
	val winrate: Double? = null,

	@Json(name="avg_kda")
	val avg_kda: List<Any?>? = null,

	@Json(name="favorite_hero")
	val favorite_hero: FavoriteHero? = null
)

data class FavoriteHero(

	@Json(name="image")
	val image: String? = null,

	@Json(name="visible")
	val visible: Boolean? = null,

	@Json(name="updated_at")
	val updated_at: String? = null,

	@Json(name="stats")
	val stats: List<Int?>? = null,

	@Json(name="classes")
	val classes: List<String?>? = null,

	@Json(name="roles")
	val roles: List<String?>? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="created_at")
	val created_at: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="display_name")
	val display_name: String? = null,

	@Json(name="enabled")
	val enabled: Boolean? = null,

	@Json(name="game_id")
	val game_id: Int? = null
)
