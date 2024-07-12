package com.alican.predecessorcompanion.data.remote.repository.builds

import com.alican.predecessorcompanion.data.remote.dataSource.builds.BuildsRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.builds.BuildsResponseItem
import com.alican.predecessorcompanion.utils.ResultWrapper
import javax.inject.Inject

class BuildsRepository @Inject constructor(private val remoteDataSource: BuildsRemoteDataSource) {

    suspend fun getBuilds(page: Int): ResultWrapper<List<BuildsResponseItem>> {
        return remoteDataSource.getBuilds(page = page)
    }
}