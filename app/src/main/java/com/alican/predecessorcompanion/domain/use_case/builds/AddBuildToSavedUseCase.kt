package com.alican.predecessorcompanion.domain.use_case.builds

import com.alican.predecessorcompanion.data.remote.repository.builds.BuildsRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.builds.toEntity
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddBuildToSavedUseCase @Inject constructor(private val repository: BuildsRepository) {

    operator fun invoke(build: BuildsUIModel): Flow<UIState<Boolean>> {
        return flow {
            emit(UIState.Loading())
            emit(
                try {
                    repository.addBuildToSaved(build = build.toEntity())
                    UIState.Success(true)
                } catch (e: Exception) {
                    UIState.Error("")
                }
            )
        }
    }
}