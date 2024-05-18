package com.alican.predecessorcompanion.domain.use_case.heroes

import com.alican.predecessorcompanion.data.remote.repository.heroes.HeroesRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.heroes.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesUIModel
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeroesUseCase @Inject constructor(
    private val heroesRepository: HeroesRepository
) {

    operator fun invoke() : Flow<UIState<List<HeroesUIModel>>> {
        return flow {
            emit(UIState.Loading())
            emit(
               when( val result = heroesRepository.getHeroes()) {
                   is ResultWrapper.GenericError -> UIState.Error(errorMessage = result.error ?: "Error")
                   ResultWrapper.Loading -> UIState.Loading()
                   ResultWrapper.NetworkError -> UIState.Error(errorMessage = "Network Error")
                   is ResultWrapper.Success -> {
                       val heroes = result.value.map { it.toUIModel() }
                       UIState.Success(heroes)
                   }
               }
            )
        }
    }
}