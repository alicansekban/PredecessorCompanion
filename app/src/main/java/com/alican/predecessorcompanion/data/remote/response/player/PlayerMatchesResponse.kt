package com.alican.predecessorcompanion.data.remote.response.player

import com.squareup.moshi.Json

data class PlayerMatchesResponse(

	@Json(name="matches")
	val matches: List<MatchesItem>? = null
)

data class FlagsItem(

	@Json(name="identifier")
	val identifier: String? = null,

	@Json(name="color")
	val color: String? = null,

	@Json(name="text")
	val text: String? = null
)

data class MatchesItem(

	@Json(name="start_time")
	val startTime: String? = null,

	@Json(name="players")
	val players: List<PlayersItem?>? = null,

	@Json(name="end_time")
	val endTime: String? = null,

	@Json(name="game_region")
	val gameRegion: String? = null,

	@Json(name="winning_team")
	val winningTeam: String? = null,

	@Json(name="game_mode")
	val gameMode: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="game_duration")
	val gameDuration: Int? = null,

	@Json(name="region")
	val region: String? = null
)

data class PlayersItem(

	@Json(name="largest_critical_strike")
	val largestCriticalStrike: Int? = null,

	@Json(name="physical_damage_taken_from_heroes")
	val physicalDamageTakenFromHeroes: Int? = null,

	@Json(name="gold_spent")
	val goldSpent: Int? = null,

	@Json(name="wards_placed")
	val wardsPlaced: Int? = null,

	@Json(name="true_damage_dealt")
	val trueDamageDealt: Int? = null,

	@Json(name="minions_killed")
	val minionsKilled: Int? = null,

	@Json(name="rank")
	val rank: Double? = null,

	@Json(name="neutral_minions_team_jungle")
	val neutralMinionsTeamJungle: Int? = null,

	@Json(name="total_damage_taken")
	val totalDamageTaken: Int? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="performance_title")
	val performanceTitle: String? = null,

	@Json(name="deaths")
	val deaths: Int? = null,

	@Json(name="physical_damage_taken")
	val physicalDamageTaken: Int? = null,

	@Json(name="item_healing_done")
	val itemHealingDone: Int? = null,

	@Json(name="magical_damage_dealt_to_heroes")
	val magicalDamageDealtToHeroes: Int? = null,

	@Json(name="total_damage_dealt_to_objectives")
	val totalDamageDealtToObjectives: Int? = null,

	@Json(name="level")
	val level: Int? = null,

	@Json(name="lane_minions_killed")
	val laneMinionsKilled: Int? = null,

	@Json(name="true_damage_taken")
	val trueDamageTaken: Int? = null,

	@Json(name="display_name")
	val displayName: String? = null,

	@Json(name="gold_earned")
	val goldEarned: Int? = null,

	@Json(name="largest_multi_kill")
	val largestMultiKill: Int? = null,

	@Json(name="total_damage_mitigated")
	val totalDamageMitigated: Int? = null,

	@Json(name="total_shielding_received")
	val totalShieldingReceived: Int? = null,

	@Json(name="total_damage_dealt_to_heroes")
	val totalDamageDealtToHeroes: Int? = null,

	@Json(name="is_ranked")
	val isRanked: Boolean? = null,

	@Json(name="rank_image")
	val rankImage: Double? = null,

	@Json(name="physical_damage_dealt")
	val physicalDamageDealt: Int? = null,

	@Json(name="kills")
	val kills: Int? = null,

	@Json(name="total_damage_dealt_to_structures")
	val totalDamageDealtToStructures: Int? = null,

	@Json(name="performance_score")
	val performanceScore: Double? = null,

	@Json(name="role")
	val role: String? = null,

	@Json(name="mmr_change")
	val mmrChange: Double? = null,

	@Json(name="flags")
	val flags: List<Double?>? = null,

	@Json(name="neutral_minions_killed")
	val neutralMinionsKilled: Int? = null,

	@Json(name="magical_damage_taken")
	val magicalDamageTaken: Int? = null,

	@Json(name="inventory_data")
	val inventoryData: List<Int?>? = null,

	@Json(name="mmr")
	val mmr: Double? = null,

	@Json(name="assists")
	val assists: Int? = null,

	@Json(name="largest_killing_spree")
	val largestKillingSpree: Int? = null,

	@Json(name="true_damage_taken_from_heroes")
	val trueDamageTakenFromHeroes: Int? = null,

	@Json(name="total_damage_taken_from_heroes")
	val totalDamageTakenFromHeroes: Int? = null,

	@Json(name="hero_id")
	val heroId: Int? = null,

	@Json(name="physical_damage_dealt_to_heroes")
	val physicalDamageDealtToHeroes: Int? = null,

	@Json(name="true_damage_dealt_to_heroes")
	val trueDamageDealtToHeroes: Int? = null,

	@Json(name="team")
	val team: String? = null,

	@Json(name="magical_damage_dealt")
	val magicalDamageDealt: Int? = null,

	@Json(name="crest_healing_done")
	val crestHealingDone: Int? = null,

	@Json(name="utility_healing_done")
	val utilityHealingDone: Int? = null,

	@Json(name="neutral_minions_enemy_jungle")
	val neutralMinionsEnemyJungle: Int? = null,

	@Json(name="magical_damage_taken_from_heroes")
	val magicalDamageTakenFromHeroes: Int? = null,

	@Json(name="total_damage_dealt")
	val totalDamageDealt: Int? = null,

	@Json(name="total_healing_done")
	val totalHealingDone: Int? = null,

	@Json(name="wards_destroyed")
	val wardsDestroyed: Int? = null
)
