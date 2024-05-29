package com.alican.predecessorcompanion.ui.more.item_detail

import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel

data class ItemDetailUIState(
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val item: ItemUIModel = ItemUIModel(),
    val errorMessage: String = "",
)
