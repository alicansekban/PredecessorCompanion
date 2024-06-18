package com.alican.predecessorcompanion.domain.mapper.heroes

import com.alican.predecessorcompanion.data.local.entity.HeroesEntity
import com.alican.predecessorcompanion.data.remote.response.heroes.HeroesResponse
import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesUIModel
import com.alican.predecessorcompanion.utils.Constant
import com.alican.predecessorcompanion.utils.HeroDetail


fun HeroesResponse.toUIModel() : HeroesUIModel {
    return HeroesUIModel(
        heroName = this.display_name ?: "",
        heroId = this.id.toString(),
        image = (Constant.BASE_URL + this.image),
        role = this.roles?.get(0) ?: ""
    )
}

fun HeroesResponse.toEntity(): HeroesEntity {
    return HeroesEntity(
        heroName = this.display_name ?: "",
        heroId = this.id.toString(),
        image = (Constant.BASE_URL + this.image),
        role = this.roles?.get(0) ?: ""
    )
}

fun HeroesUIModel.toDetailModel(): HeroDetail {
    return HeroDetail(
        heroName = this.heroName,
        heroId = this.heroId,
        image = this.image,
        role = this.role
    )
}

fun HeroesUIModel.toEntity(): HeroesEntity {
    return HeroesEntity(
        heroName = this.heroName,
        heroId = this.heroId,
        image = this.image,
        role = this.role
    )
}

fun HeroesEntity.toUIModel(): HeroesUIModel {
    return HeroesUIModel(
        heroName = this.heroName,
        heroId = this.heroId,
        image = this.image,
        role = this.role
    )
}

