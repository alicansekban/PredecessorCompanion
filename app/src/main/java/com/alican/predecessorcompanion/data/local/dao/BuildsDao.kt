package com.alican.predecessorcompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alican.predecessorcompanion.data.local.entity.BuildsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BuildsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuild(builds: BuildsEntity)

    @Query("SELECT * FROM builds WHERE buildId = :buildId")
    fun isBuildSaved(buildId: String): BuildsEntity?

    @Query("SELECT * FROM builds")
    fun getBuilds(): Flow<List<BuildsEntity>>

    @Query("DELETE FROM builds Where buildId = :buildId")
    fun removeBuild(buildId: String)

}