package com.alican.predecessorcompanion.data.remote.response.items

import com.squareup.moshi.Json

data class EffectsItem(

	@Json(name="menu_description")
	val menuDescription: String? = null,

	@Json(name="condition")
	val condition: Any? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="cooldown")
	val cooldown: String? = null,

	@Json(name="active")
	val active: Boolean? = null
)

data class ItemsResponseItem(

	@Json(name="image")
	val image: String? = null,

	@Json(name="hero_class")
	val hero_class: Any? = null,

	@Json(name="requirements")
	val requirements: List<Any?>? = null,

	@Json(name="total_price")
	val total_price: Int? = null,

	@Json(name="required_level")
	val required_level: Any? = null,

	@Json(name="slot_type")
	val slot_type: String? = null,

	@Json(name="display_name")
	val display_name: String? = null,

	@Json(name="build_paths")
	val build_paths: List<Any?>? = null,

	@Json(name="effects")
	val effects: List<EffectsItem?>? = null,

	@Json(name="stats")
	val stats: Stats? = null,

	@Json(name="price")
	val price: Double? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="aggression_type")
	val aggression_type: String? = null,

	@Json(name="game_id")
	val game_id: Int? = null,

	@Json(name="rarity")
	val rarity: String? = null
)

data class Stats(

	@Json(name="magical_power")
	val magicalPower: Any? = null,

	@Json(name="critical_chance")
	val criticalChance: Any? = null,

	@Json(name="physical_power")
	val physicalPower: Any? = null,

	@Json(name="attack_speed")
	val attackSpeed: Any? = null,

	@Json(name="omnivamp")
	val omnivamp: Any? = null,

	@Json(name="ability_haste")
	val abilityHaste: Any? = null,

	@Json(name="max_health")
	val maxHealth: Any? = null,

	@Json(name="tenacity")
	val tenacity: Any? = null,

	@Json(name="magical_armor")
	val magicalArmor: Any? = null,

	@Json(name="magical_lifesteal")
	val magicalLifesteal: Any? = null,

	@Json(name="physical_penetration")
	val physicalPenetration: Any? = null,

	@Json(name="lifesteal")
	val lifesteal: Any? = null,

	@Json(name="max_mana")
	val maxMana: Any? = null,

	@Json(name="physical_armor")
	val physicalArmor: Any? = null,

	@Json(name="movement_speed")
	val movementSpeed: Any? = null,

	@Json(name="magical_penetration")
	val magicalPenetration: Any? = null,

	@Json(name="health_regeneration")
	val healthRegeneration: Any? = null,

	@Json(name="mana_regeneration")
	val manaRegeneration: Any? = null,

	@Json(name="heal_shield_increase")
	val healShieldIncrease: Any? = null,

	@Json(name="gold_per_second")
	val goldPerSecond: Any? = null
)
