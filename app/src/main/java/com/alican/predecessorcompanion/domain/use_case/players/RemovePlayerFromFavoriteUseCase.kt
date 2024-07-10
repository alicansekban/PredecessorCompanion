package com.alican.predecessorcompanion.domain.use_case.players

import com.alican.predecessorcompanion.data.remote.repository.players.PlayersRepository
import com.alican.predecessorcompanion.domain.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemovePlayerFromFavoriteUseCase @Inject constructor(
    private val repository: PlayersRepository
) {
    operator fun invoke(playerId: String): Flow<UIState<Boolean>> {
        return flow {
            emit(UIState.Loading())
            emit(
                try {
                    repository.removePlayerFromFavorite(playerId = playerId)
                    UIState.Success(true)
                } catch (e: Exception) {
                    UIState.Error("")
                }
            )
        }
    }
}