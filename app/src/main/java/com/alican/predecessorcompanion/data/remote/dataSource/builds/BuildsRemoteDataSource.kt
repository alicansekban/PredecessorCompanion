package com.alican.predecessorcompanion.data.remote.dataSource.builds

import com.alican.predecessorcompanion.data.remote.api.WebService
import com.alican.predecessorcompanion.data.remote.response.builds.BuildsResponseItem
import com.alican.predecessorcompanion.utils.ResultWrapper
import com.alican.predecessorcompanion.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class BuildsRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getBuilds(page: Int): ResultWrapper<List<BuildsResponseItem>> {
        return safeApiCall(Dispatchers.IO) { webService.getBuilds(page = page) }
    }
}