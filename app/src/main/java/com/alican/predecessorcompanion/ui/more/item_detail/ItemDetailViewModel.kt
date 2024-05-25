package com.alican.predecessorcompanion.ui.more.item_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.alican.predecessorcompanion.utils.ItemDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel(){

    var args: ItemDetail? = null

}