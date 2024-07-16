package com.alican.predecessorcompanion.data.remote.response.heroes_statistics

import com.squareup.moshi.Json

data class HeroesStatisticsResponse(

	@Json(name="hero_statistics")
	val hero_statistics: List<HeroStatisticsItem> = emptyList()
)

data class HeroStatisticsItem(

	@Json(name="kills")
	val kills: Int? = null,

	@Json(name="match_count")
	val match_count: Double? = null,

	@Json(name="avg_cs")
	val avg_cs: Double? = null,

	@Json(name="winrate")
	val winrate: Double? = null,

	@Json(name="avg_performance_score")
	val avg_performance_score: Double? = null,

	@Json(name="display_name")
	val display_name: String? = null,

	@Json(name="avg_game_duration")
	val avg_game_duration: Double? = null,

	@Json(name="avg_damage_taken_from_heroes")
	val avg_damage_taken_from_heroes: Double? = null,

	@Json(name="avg_gold")
	val avg_gold: Double? = null,

	@Json(name="avg_damage_dealt_to_heroes")
	val avg_damage_dealt_to_heroes: Double? = null,

	@Json(name="assists")
	val assists: Int? = null,

	@Json(name="avg_kdar")
	val avg_kdar: Double? = null,

	@Json(name="winrate_mirrorless")
	val winrateMirrorless: Double? = null,

	@Json(name="hero_id")
	val hero_id: Int? = null,

	@Json(name="pickrate")
	val pickrate: Double? = null,

	@Json(name="deaths")
	val deaths: Int? = null
)
