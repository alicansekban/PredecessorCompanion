package com.alican.predecessorcompanion.domain.use_case.builds

import com.alican.predecessorcompanion.data.remote.repository.builds.BuildsRepository
import com.alican.predecessorcompanion.domain.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoveBuildFromSavedUseCase @Inject constructor(
    private val repository: BuildsRepository
) {
    operator fun invoke(buildId: String): Flow<UIState<Boolean>> {
        return flow {
            emit(UIState.Loading())
            emit(
                try {
                    repository.removeBuildFromSaved(buildId)
                    UIState.Success(true)
                } catch (e: Exception) {
                    UIState.Error("")
                }
            )
        }
    }
}