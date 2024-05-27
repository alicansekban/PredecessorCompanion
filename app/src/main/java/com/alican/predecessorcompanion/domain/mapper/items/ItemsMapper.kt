package com.alican.predecessorcompanion.domain.mapper.items

import com.alican.predecessorcompanion.data.remote.response.items.ItemsResponseItem
import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel
import com.alican.predecessorcompanion.utils.Constant

fun ItemsResponseItem.toUIModel(): ItemUIModel {
    return ItemUIModel(
        itemName = this.name ?: "",
        itemImage = Constant.BASE_URL + this.image,
        itemType = this.rarity ?: "default",
        itemPrice = this.price ?: 0.0
    )
}