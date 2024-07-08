package com.alican.predecessorcompanion.domain.use_case.players

import com.alican.predecessorcompanion.data.remote.repository.players.PlayersRepository
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.mapper.players.toEntity
import com.alican.predecessorcompanion.domain.ui_model.players.PlayersUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddPlayerToFavoriteUseCase @Inject constructor(
    private val repository: PlayersRepository
) {

    operator fun invoke(player: PlayersUIModel): Flow<UIState<Boolean>> {
        return flow {
            emit(UIState.Loading())
            emit(
                try {
                    repository.addPlayerToFavorite(player = player.toEntity())
                    UIState.Success(true)
                } catch (e: Exception) {
                    UIState.Error("")
                }

            )
        }
    }
}