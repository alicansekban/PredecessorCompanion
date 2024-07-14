package com.alican.predecessorcompanion.domain.ui_model.builds

import kotlinx.serialization.Serializable

@Serializable
data class BuildsUIModel(
    val role: String = "",
    val description: String = "",
    val item1Id: Int? = null,
    val item2Id: Int? = null,
    val item3Id: Int? = null,
    val item4Id: Int? = null,
    val item5Id: Int? = null,
    val heroId: Int? = null,
    val title: String = "",
    val buildId: String = "",
    var isFavorite: Boolean = false
)
