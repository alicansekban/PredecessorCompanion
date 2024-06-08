package com.alican.predecessorcompanion.data.remote.response.player

import com.squareup.moshi.Json

data class PlayerHeroStatisticsResponse(

	@Json(name="hero_statistics")
	val heroStatistics: List<HeroStatisticsItem>? = null
)

data class HeroStatisticsItem(

	@Json(name="largest_critical_strike")
	val largest_critical_strike: Int? = null,

	@Json(name="match_count")
	val match_count: Int? = null,

	@Json(name="wards_placed")
	val wards_placed: Int? = null,

	@Json(name="max_minions_killed")
	val max_minions_killed: Int? = null,

	@Json(name="max_gold_earned")
	val maxG_gold_earned: Int? = null,

	@Json(name="minions_killed")
	val minions_killed: Int? = null,

	@Json(name="avg_damage_dealt_to_objectives")
	val avg_damage_dealtT_to_objectives: Int? = null,

	@Json(name="max_kdar")
	val max_kdar: Double? = null,

	@Json(name="avg_damage_taken_from_heroes")
	val avg_damage_taken_from_heroes: Int? = null,

	@Json(name="avg_damage_mitigated")
	val avg_damage_mitigated: Int? = null,

	@Json(name="max_damage_taken_from_heroes")
	val max_damage_taken_from_heroes: Int? = null,

	@Json(name="avg_wards_destroyed")
	val avg_wards_destroyed: Int? = null,

	@Json(name="total_performance_score")
	val total_performance_score: Double? = null,

	@Json(name="avg_damage_dealt_to_heroes")
	val avg_damage_dealt_to_heroes: Int? = null,

	@Json(name="gold_min")
	val gold_min: Double? = null,

	@Json(name="avg_wards_placed")
	val avg_wards_placed: Int? = null,

	@Json(name="max_assists")
	val max_assists: Int? = null,

	@Json(name="max_damage_dealt_to_heroes")
	val max_damage_dealt_to_heroes: Int? = null,

	@Json(name="deaths")
	val deaths: Int? = null,

	@Json(name="total_damage_dealt_to_objectives")
	val total_damage_dealt_to_objectives: Int? = null,

	@Json(name="avg_kills")
	val avg_kills: Int? = null,

	@Json(name="avg_assists")
	val avg_assists: Int? = null,

	@Json(name="avg_healing_done")
	val avg_healing_done: Int? = null,

	@Json(name="max_kills")
	val max_kills: Int? = null,

	@Json(name="avg_performance_score")
	val avg_performance_score: Double? = null,

	@Json(name="avg_minions_killed")
	val avg_minions_killed: Int? = null,

	@Json(name="max_deaths")
	val max_deaths: Int? = null,

	@Json(name="display_name")
	val display_name: String? = null,

	@Json(name="gold_earned")
	val gold_earned: Int? = null,

	@Json(name="largest_multi_kill")
	val largest_multi_kill: Int? = null,

	@Json(name="total_damage_mitigated")
	val total_damage_mitigated: Int? = null,

	@Json(name="avg_kdar")
	val avg_kdar: Double? = null,

	@Json(name="total_damage_dealt_to_heroes")
	val total_damage_dealt_to_heroes: Int? = null,

	@Json(name="max_wards_destroyed")
	val max_wards_destroyed: Int? = null,

	@Json(name="avg_damage_dealt_to_structures")
	val avg_damage_dealt_to_structures: Int? = null,

	@Json(name="kills")
	val kills: Int? = null,

	@Json(name="max_healing_done")
	val max_healing_done: Int? = null,

	@Json(name="avg_gold_earned")
	val avg_gold_earned: Int? = null,

	@Json(name="total_damage_dealt_to_structures")
	val total_damage_dealt_to_structures: Int? = null,

	@Json(name="avg_deaths")
	val avg_deaths: Int? = null,

	@Json(name="winrate")
	val winrate: Double? = null,

	@Json(name="max_damage_dealt_to_objectives")
	val max_damage_dealt_to_objectives: Int? = null,

	@Json(name="max_damage_mitigated")
	val max_damage_mitigated: Int? = null,

	@Json(name="largest_killing_spree")
	val largest_killing_spree: Int? = null,

	@Json(name="assists")
	val assists: Int? = null,

	@Json(name="max_wards_placed")
	val max_wards_placed: Int? = null,

	@Json(name="max_damage_dealt_to_structures")
	val max_damage_dealt_to_structures: Int? = null,

	@Json(name="total_damage_taken_from_heroes")
	val total_damage_taken_from_heroes: Int? = null,

	@Json(name="hero_id")
	val hero_id: Int? = null,

	@Json(name="max_performance_score")
	val max_performance_score: Double? = null,

	@Json(name="cs_min")
	val cs_min: Double? = null,

	@Json(name="total_healing_done")
	val total_healing_done: Int? = null,

	@Json(name="wards_destroyed")
	val wards_destroyed: Int? = null
)
