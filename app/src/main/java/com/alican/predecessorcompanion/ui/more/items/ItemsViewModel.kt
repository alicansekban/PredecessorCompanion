package com.alican.predecessorcompanion.ui.more.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.data.remote.repository.items.ItemsRepository
import com.alican.predecessorcompanion.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.adapters.ImmutableListAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(private val repository: ItemsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ItemsUIStateModel())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        ItemsUIStateModel()
    )
    init {
        getItems()
    }
    private fun getItems() {
        viewModelScope.launch {
            when (val response = repository.getItems()) {
                is ResultWrapper.GenericError -> {}
                ResultWrapper.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }

                ResultWrapper.NetworkError -> {}
                is ResultWrapper.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isSuccess = true,
                        isLoading = false,
                        items = ImmutableListAdapter(response.value)
                    )
                }
            }
        }
    }
}