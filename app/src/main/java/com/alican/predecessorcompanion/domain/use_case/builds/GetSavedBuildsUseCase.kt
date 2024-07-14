package com.alican.predecessorcompanion.domain.use_case.builds

import com.alican.predecessorcompanion.data.remote.repository.builds.BuildsRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.builds.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedBuildsUseCase @Inject constructor(private val repository: BuildsRepository) {

    operator fun invoke(): Flow<UIState<List<BuildsUIModel>>> {
        return flow {
            emit(UIState.Loading())
            repository.getBuilds().map { list ->
                list.map {
                    it.toUIModel()
                }
            }.collect { builds ->
                emit(UIState.Success(builds))
            }
        }.catch { e ->
            emit(UIState.Error(e.message ?: "Unknown error"))
        }
    }
}