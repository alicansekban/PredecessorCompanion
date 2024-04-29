package com.alican.predecessorcompanion.data.remote.response.leaderBoard

import com.squareup.moshi.Json

data class LeaderBoardResponse(

	@Json(name="is_active")
	val isActive: Boolean? = null,

	@Json(name="mmr")
	val mmr: Double? = null,

	@Json(name="flags")
	val flags: List<FlagsItem?>? = null,

	@Json(name="rank")
	val rank: Int? = null,

	@Json(name="rank_active")
	val rankActive: Int? = null,

	@Json(name="rank_title")
	val rankTitle: String? = null,

	@Json(name="id")
	val id: String? = null,

	@Json(name="is_ranked")
	val isRanked: Boolean? = null,

	@Json(name="display_name")
	val displayName: String? = null,

	@Json(name="region")
	val region: String? = null,

	@Json(name="rank_image")
	val rankImage: String? = null
)

data class FlagsItem(

	@Json(name="identifier")
	val identifier: String? = null,

	@Json(name="color")
	val color: String? = null,

	@Json(name="text")
	val text: String? = null
)
