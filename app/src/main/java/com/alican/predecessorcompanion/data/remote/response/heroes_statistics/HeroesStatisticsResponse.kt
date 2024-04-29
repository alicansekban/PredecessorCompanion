package com.alican.predecessorcompanion.data.remote.response.heroes_statistics

import com.squareup.moshi.Json

data class HeroesStatisticsResponse(

	@Json(name="hero_statistics")
	val heroStatistics: List<HeroStatisticsItem> = emptyList()
)

data class HeroStatisticsItem(

	@Json(name="kills")
	val kills: Int? = null,

	@Json(name="match_count")
	val matchCount: Double? = null,

	@Json(name="avg_cs")
	val avgCs: Double? = null,

	@Json(name="winrate")
	val winrate: Double? = null,

	@Json(name="avg_performance_score")
	val avgPerformanceScore: Double? = null,

	@Json(name="display_name")
	val displayName: String? = null,

	@Json(name="avg_game_duration")
	val avgGameDuration: Double? = null,

	@Json(name="avg_damage_taken_from_heroes")
	val avgDamageTakenFromHeroes: Double? = null,

	@Json(name="avg_gold")
	val avgGold: Double? = null,

	@Json(name="avg_damage_dealt_to_heroes")
	val avgDamageDealtToHeroes: Double? = null,

	@Json(name="assists")
	val assists: Int? = null,

	@Json(name="avg_kdar")
	val avgKdar: Double? = null,

	@Json(name="winrate_mirrorless")
	val winrateMirrorless: Double? = null,

	@Json(name="hero_id")
	val heroId: Int? = null,

	@Json(name="pickrate")
	val pickrate: Double? = null,

	@Json(name="deaths")
	val deaths: Int? = null
)
