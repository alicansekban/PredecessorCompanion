package com.alican.predecessorcompanion.data.remote.dataSource.builds

import com.alican.predecessorcompanion.data.local.AppDatabase
import com.alican.predecessorcompanion.data.local.entity.BuildsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BuildsLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun insertBuild(build: BuildsEntity) {
        appDatabase.buildsDao().insertBuild(build)
    }

    fun getBuilds(): Flow<List<BuildsEntity>> = appDatabase.buildsDao().getBuilds()


    fun isBuildSaved(buildId: String): BuildsEntity? =
        appDatabase.buildsDao().isBuildSaved(buildId)

    fun removeBuild(buildId: String) =
        appDatabase.buildsDao().removeBuild(buildId)
}