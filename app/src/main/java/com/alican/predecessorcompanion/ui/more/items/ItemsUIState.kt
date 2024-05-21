package com.alican.predecessorcompanion.ui.more.items

import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.adapters.ImmutableListAdapter

data class ItemsUIStateModel(
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val items: ImmutableList<ItemUIModel> = ImmutableListAdapter(emptyList())
)