package com.alican.predecessorcompanion.domain.mapper.builds

import com.alican.predecessorcompanion.data.remote.response.builds.BuildsResponseItem
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel

fun BuildsResponseItem.toUIModel(): BuildsUIModel {
    return BuildsUIModel(
        role = this.role ?: "",
        description = this.description ?: "",
        item1Id = item1_id,
        item2Id = item2_id,
        item3Id = item3_id,
        item4Id = item4_id,
        item5Id = item5_id,
        heroId = hero_id,
        title = title ?: ""
    )
}