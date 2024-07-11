package com.alican.predecessorcompanion.domain.use_case.players

import com.alican.predecessorcompanion.data.remote.repository.players.PlayersRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.players.toUIModel
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetSavedPlayersUseCase @Inject constructor(private val repository: PlayersRepository) {

    operator fun invoke(): Flow<UIState<List<PlayersUIModel>>> {
        return flow {
            emit(UIState.Loading())
            repository.getPlayers().map { list ->
                list.map {
                    it.toUIModel()
                }
            }.collect { players ->
                emit(UIState.Success(players))
            }
        }.catch { e ->
            emit(UIState.Error(e.message ?: "Unknown error"))
        }
    }
}
