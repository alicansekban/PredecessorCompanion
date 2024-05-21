package com.alican.predecessorcompanion.data.remote.repository.items

import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.domain.mapper.items.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.items.ItemUIModel
import com.alican.predecessorcompanion.utils.ResultWrapper
import com.alican.predecessorcompanion.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ItemsRepository @Inject constructor(
    private val webService: WebService,
) {
    suspend fun getItems(): ResultWrapper<List<ItemUIModel>> {
        return when (val response = safeApiCall(Dispatchers.IO) {
            webService.getItems()
        }) {
            is ResultWrapper.GenericError -> response
            ResultWrapper.Loading -> {
                ResultWrapper.Loading
            }

            ResultWrapper.NetworkError -> {
                ResultWrapper.NetworkError
            }

            is ResultWrapper.Success -> {
                val data = response.value
                val uiModel = data.map {
                    it.toUIModel()
                }
                ResultWrapper.Success(uiModel)
            }
        }
    }
}