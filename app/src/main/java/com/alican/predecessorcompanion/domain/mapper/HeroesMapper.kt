package com.alican.predecessorcompanion.domain.mapper

import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.domain.ui_model.HeroesUIModel


fun HeroesResponse.toUIModel() : HeroesUIModel {
    return HeroesUIModel(
        heroName = this.name ?:"",
        heroId = this.id.toString()
    )
}