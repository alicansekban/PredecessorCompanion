package com.alican.predecessorcompanion.domain.use_case.builds

import com.alican.predecessorcompanion.data.remote.repository.builds.BuildsRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.builds.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBuildsUseCase @Inject constructor(private val buildsRepository: BuildsRepository) {
    operator fun invoke(page: Int): Flow<UIState<List<BuildsUIModel>>> {
        return flow {
            emit(UIState.Loading())
            emit(
                when (val result = buildsRepository.getBuilds(page = page)) {
                    is ResultWrapper.GenericError -> UIState.Error(
                        errorMessage = result.error ?: "Error"
                    )

                    ResultWrapper.Loading -> UIState.Loading()
                    ResultWrapper.NetworkError -> UIState.Error(errorMessage = "Network Error")
                    is ResultWrapper.Success -> {
                        val builds = result.value.map { it.toUIModel() }
                        UIState.Success(builds)
                    }
                }
            )
        }
    }
}