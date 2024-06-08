package com.alican.predecessorcompanion.data.remote.response.player

import com.squareup.moshi.Json

data class PlayerCommonTeammatesResponse(

	@Json(name="teammates")
	val teammates: List<TeammatesItem>? = null
)

data class TeammatesItem(

	@Json(name="matches_played")
	val matches_played: Int? = null,

	@Json(name="matches_percentage")
	val matches_percentage: Double? = null,

	@Json(name="winrate")
	val winrate: Double? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="display_name")
	val display_name: String? = null
)
