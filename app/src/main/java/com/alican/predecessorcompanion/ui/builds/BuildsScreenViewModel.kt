package com.alican.predecessorcompanion.ui.builds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel
import com.alican.predecessorcompanion.domain.use_case.builds.GetBuildsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildsScreenViewModel @Inject constructor(
    private val getBuildsUseCase: GetBuildsUseCase
) : ViewModel() {
    private val _builds = MutableStateFlow<PagingData<BuildsUIModel>>(PagingData.empty())
    val builds: StateFlow<PagingData<BuildsUIModel>>
        get() = _builds

    private val _uiState = MutableStateFlow(BuildsUIStateModel())
    val uiState: StateFlow<BuildsUIStateModel>
        get() = _uiState


    init {
        getBuildsNextPage(1)
    }

    fun updateUIEvents(event: BuildsUIStateEvents) {
        when (event) {
            is BuildsUIStateEvents.GetNextPage -> {
                getBuildsNextPage(event.page)
            }

            is BuildsUIStateEvents.FavoriteButtonClicked -> {
//                if (event.player.isFavorite) {
//                    removePlayerFromFavorite(player = event.player)
//                } else {
//                    addToFavorite(player = event.player)
//                }
            }
        }
    }

    private fun getBuildsNextPage(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getBuildsUseCase.invoke(page = page).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }

                    is UIState.Success -> {
                        val currentBuilds = _uiState.value.builds
                        val updatedBuilds = currentBuilds + state.response
                        _uiState.value = _uiState.value.copy(
                            builds = updatedBuilds,
                            page = page,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}