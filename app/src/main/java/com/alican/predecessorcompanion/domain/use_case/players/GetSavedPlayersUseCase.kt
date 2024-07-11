package com.alican.predecessorcompanion.domain.use_case.players

import com.alican.predecessorcompanion.data.remote.repository.players.PlayersRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.players.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetSavedPlayersUseCase @Inject constructor(private val repository: PlayersRepository) {

    operator fun invoke(): Flow<UIState<List<PlayersUIModel>>> {
        return flow {
            try {
                repository.getPlayers()
                    .collect { players ->
                        if (players.isEmpty()) {
                            emit(UIState.Empty())
                        } else {
                            emit(UIState.Success(players.map { it.toUIModel() }))
                        }
                    }
            } catch (e: Exception) {
                emit(UIState.Error("error"))
            }
        }
    }
}
