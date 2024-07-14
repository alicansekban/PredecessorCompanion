package com.alican.predecessorcompanion.data.remote.repository.builds

import com.alican.predecessorcompanion.data.local.entity.BuildsEntity
import com.alican.predecessorcompanion.data.remote.dataSource.builds.BuildsLocalDataSource
import com.alican.predecessorcompanion.data.remote.dataSource.builds.BuildsRemoteDataSource
import com.alican.predecessorcompanion.data.remote.response.builds.BuildsResponseItem
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildsRepository @Inject constructor(
    private val remoteDataSource: BuildsRemoteDataSource,
    private val localDataSource: BuildsLocalDataSource
) {

    suspend fun getBuilds(page: Int): ResultWrapper<List<BuildsResponseItem>> {
        return remoteDataSource.getBuilds(page = page)
    }

    fun getBuilds(): Flow<List<BuildsEntity>> {
        return localDataSource.getBuilds()

    }

    suspend fun addBuildToSaved(build: BuildsEntity) {
        try {
            localDataSource.insertBuild(build)
        } catch (e: Exception) {
            throw e
        }
    }

    fun removeBuildFromSaved(buildId: String) {
        try {
            localDataSource.removeBuild(buildId)
        } catch (e: Exception) {
            throw e
        }
    }

    fun isBuildSaved(buildId: String): BuildsEntity? =
        localDataSource.isBuildSaved(buildId)

}