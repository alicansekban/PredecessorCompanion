package com.alican.predecessorcompanion.domain.mapper.heroes

import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesUIModel
import com.alican.predecessorcompanion.utils.Constant


fun HeroesResponse.toUIModel() : HeroesUIModel {
    return HeroesUIModel(
        heroName = this.display_name ?: "",
        heroId = this.id.toString(),
        image = (Constant.BASE_URL + this.image),
        role = this.roles?.get(0) ?: ""
    )
}