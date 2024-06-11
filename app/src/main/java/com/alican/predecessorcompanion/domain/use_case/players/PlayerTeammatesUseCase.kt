package com.alican.predecessorcompanion.domain.use_case.players

import com.alican.predecessorcompanion.data.remote.repository.players.PlayersRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.players.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayerTeammateUIModel
import com.alican.predecessorcompanion.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayerTeammatesUseCase @Inject constructor(
    private val playersRepository: PlayersRepository
) {
    operator fun invoke(playerId: String): Flow<UIState<List<PlayerTeammateUIModel>>> {
        return flow {
            emit(UIState.Loading())
            emit(
                when (val result = playersRepository.getPlayerTeammates(playerId)) {
                    is ResultWrapper.GenericError -> UIState.Error(
                        errorMessage = result.error ?: "Error"
                    )

                    ResultWrapper.Loading -> UIState.Loading()
                    ResultWrapper.NetworkError -> UIState.Error(errorMessage = "Network Error")
                    is ResultWrapper.Success -> {
                        val playerHeroes = result.value.teammates?.map { it.toUIModel() }
                        playerHeroes?.let {
                            UIState.Success(playerHeroes)
                        } ?: run {
                            UIState.Empty()
                        }
                    }
                }
            )
        }
    }
}