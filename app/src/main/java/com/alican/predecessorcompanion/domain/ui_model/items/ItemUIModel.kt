package com.alican.predecessorcompanion.domain.ui_model.items

import kotlinx.serialization.Serializable

@Serializable
data class ItemUIModel(
    val itemName: String = "",
    val displayName: String = "",
    val itemImage: String = "",
    val itemType: String? = null,
    val itemPrice: String? = "",
    val slotType: String? = null,
    val aggressionType: String? = null
)
