package com.alican.predecessorcompanion.ui.more.item_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.data.remote.repository.items.ItemsRepository
import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel
import com.alican.predecessorcompanion.utils.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(
    private val repository: ItemsRepository
) : ViewModel() {
    var args: ItemUIModel? = null

    private val _uiState = MutableStateFlow(ItemDetailUIState())
    val uiState = _uiState.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(),
        ItemDetailUIState()
    )

    fun getDetail() {
        viewModelScope.launch {
            args?.let { it ->
                when (val response = repository.getItemDetails(it.itemName)) {
                    is ResultWrapper.GenericError -> {
                        _uiState.update {
                            it.copy(errorMessage = response.error.toString(), isLoading = false)
                        }
                    }

                    ResultWrapper.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true)
                        }
                    }

                    ResultWrapper.NetworkError -> {
                        _uiState.update {
                            it.copy(
                                errorMessage = "İnternet bağlantınızı kontrol edin.",
                                isLoading = false
                            )
                        }
                    }

                    is ResultWrapper.Success -> {
                        _uiState.update {
                            it.copy(
                                item = response.value,
                                isLoading = false,
                                isSuccess = true
                            )
                        }
                    }
                }
            }
        }
    }
}