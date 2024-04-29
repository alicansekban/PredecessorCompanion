package com.alican.predecessorcompanion.data.remote.response.heroes

import com.squareup.moshi.Json

data class HeroesResponse(

	@Json(name="abilities")
	val abilities: List<AbilitiesItem?>? = null,

	@Json(name="image")
	val image: String? = null,

	@Json(name="stats")
	val stats: List<Int?>? = null,

	@Json(name="classes")
	val classes: List<String?>? = null,

	@Json(name="roles")
	val roles: List<String?>? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="display_name")
	val displayName: String? = null,

	@Json(name="base_stats")
	val baseStats: BaseStats? = null,

	@Json(name="game_id")
	val gameId: Int? = null
)

data class BaseStats(

	@Json(name="max_health")
	val maxHealth: List<Double> = emptyList(),

	@Json(name="basic_attack_time")
	val basicAttackTime: List<Double> = emptyList(),

	@Json(name="attack_range")
	val attackRange: List<Double> = emptyList(),

	@Json(name="base_movement_speed")
	val baseMovementSpeed: List<Double> = emptyList(),

	@Json(name="cleave")
	val cleave: List<Double> = emptyList(),

	@Json(name="magical_armor")
	val magicalArmor: List<Double> = emptyList(),

	@Json(name="physical_power")
	val physicalPower: List<Double> = emptyList(),

	@Json(name="base_mana_regeneration")
	val baseManaRegeneration: List<Double> = emptyList(),

	@Json(name="attack_speed")
	val attackSpeed: List<Double> = emptyList(),

	@Json(name="max_mana")
	val maxMana: List<Double> = emptyList(),

	@Json(name="base_health_regeneration")
	val baseHealthRegeneration: List<Double> = emptyList(),

	@Json(name="physical_armor")
	val physicalArmor: List<Double> = emptyList()
)

data class AbilitiesItem(

	@Json(name="menu_description")
	val menuDescription: String? = null,

	@Json(name="image")
	val image: String? = null,

	@Json(name="cost")
	val cost: List<Double> = emptyList(),

	@Json(name="cooldown")
	val cooldown: List<Double> = emptyList(),

	@Json(name="game_description")
	val gameDescription: String? = null,

	@Json(name="display_name")
	val displayName: String? = null
)
