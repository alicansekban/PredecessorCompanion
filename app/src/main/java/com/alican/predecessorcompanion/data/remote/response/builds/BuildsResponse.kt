package com.alican.predecessorcompanion.data.remote.response.builds

import com.squareup.moshi.Json

data class FlagsItem(

    @Json(name = "identifier")
    val identifier: String? = null,

    @Json(name = "color")
    val color: String? = null,

    @Json(name = "text")
    val text: String? = null
)

data class ModulesItem(

    @Json(name = "item1_id")
    val item1_id: Int? = null,

    @Json(name = "item2_id")
    val item2_id: Int? = null,

    @Json(name = "item7_id")
    val item7_id: Int? = null,

    @Json(name = "item6_id")
    val item6_id: Int? = null,

    @Json(name = "item4_id")
    val item4_id: Int? = null,

    @Json(name = "item5_id")
    val item5Id: Int? = null,

    @Json(name = "item3_id")
    val item3_id: Int? = null,

    @Json(name = "title")
    val title: String? = null
)

data class BuildsResponseItem(

    @Json(name = "game_version")
    val gameV_version: GameVersion? = null,

    @Json(name = "role")
    val role: String? = null,

    @Json(name = "item1_id")
    val item1_id: Int? = null,

    @Json(name = "item2_id")
    val item2_id: Int? = null,

    @Json(name = "author")
    val author: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "created_at")
    val created_at: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "skill_order")
    val skill_order: List<Int?>? = null,

    @Json(name = "modules")
    val modules: List<ModulesItem?>? = null,

    @Json(name = "updated_at")
    val updated_at: String? = null,

    @Json(name = "item6_id")
    val item6_id: Int? = null,

    @Json(name = "upvotes_count")
    val upvotes_count: Int? = null,

    @Json(name = "author_player")
    val author_player: AuthorPlayer? = null,

    @Json(name = "item4_id")
    val item4_id: Int? = null,

    @Json(name = "item5_id")
    val item5_id: Int? = null,

    @Json(name = "item3_id")
    val item3_id: Int? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "hero_id")
    val hero_id: Int? = null,

    @Json(name = "crest_id")
    val crest_id: Int? = null,

    @Json(name = "downvotes_count")
    val downvotes_count: Int? = null
)

data class GameVersion(

    @Json(name = "updated_at")
    val updated_at: String? = null,

    @Json(name = "release")
    val release: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "created_at")
    val created_at: String? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "display_badge")
    val display_badge: Boolean? = null
)

data class AuthorPlayer(

    @Json(name = "is_active")
    val is_active: Boolean? = null,

    @Json(name = "mmr")
    val mmr: Double? = null,

    @Json(name = "top_percentage")
    val top_percentage: Double? = null,

    @Json(name = "flags")
    val flags: List<Any?>? = null,

    @Json(name = "rank")
    val rank: Int? = null,

    @Json(name = "rank_active")
    val rank_active: Int? = null,

    @Json(name = "rank_title")
    val rank_title: String? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "is_ranked")
    val is_ranked: Boolean? = null,

    @Json(name = "display_name")
    val display_name: String? = null,

    @Json(name = "region")
    val region: String? = null,

    @Json(name = "rank_image")
    val rank_image: String? = null
)
