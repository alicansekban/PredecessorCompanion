package com.alican.predecessorcompanion.domain.ui_model.items

data class ItemUIModel(
    val itemName: String = "",
    val displayName: String = "",
    val itemImage: String = "",
    val itemType: String? = null,
    val itemPrice: Double? = 0.0,
    val slotType: String? = null,
    val aggressionType: String? = null
)
