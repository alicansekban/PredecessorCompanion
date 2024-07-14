package com.alican.predecessorcompanion.ui.builds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel
import com.alican.predecessorcompanion.domain.use_case.builds.AddBuildToSavedUseCase
import com.alican.predecessorcompanion.domain.use_case.builds.GetBuildsUseCase
import com.alican.predecessorcompanion.domain.use_case.builds.GetSavedBuildsUseCase
import com.alican.predecessorcompanion.domain.use_case.builds.RemoveBuildFromSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuildsScreenViewModel @Inject constructor(
    private val getBuildsUseCase: GetBuildsUseCase,
    private val removeBuildFromSavedUseCase: RemoveBuildFromSavedUseCase,
    private val addBuildToSavedUseCase: AddBuildToSavedUseCase,
    private val getSavedBuildsUseCase: GetSavedBuildsUseCase
) : ViewModel() {
    private val _builds = MutableStateFlow<PagingData<BuildsUIModel>>(PagingData.empty())
    val builds: StateFlow<PagingData<BuildsUIModel>>
        get() = _builds

    private val _uiState = MutableStateFlow(BuildsUIStateModel())
    val uiState: StateFlow<BuildsUIStateModel>
        get() = _uiState


    init {
        getBuildsNextPage(1)
        getSavedBuilds()
    }

    fun updateUIEvents(event: BuildsUIStateEvents) {
        when (event) {
            is BuildsUIStateEvents.GetNextPage -> {
                getBuildsNextPage(event.page)
            }

            is BuildsUIStateEvents.FavoriteButtonClicked -> {
                if (event.build.isFavorite) {
                    removeBuildFromSaved(build = event.build)
                } else {
                    addToSaved(build = event.build)
                }
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

    private fun getSavedBuilds() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedBuildsUseCase.invoke().collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        val localBuilds = state.response
                        val remoteBuilds = _uiState.value.builds
                        val updatedRemoteBuilds = remoteBuilds.map { remoteBuild ->
                            if (localBuilds.any { localBuilds -> localBuilds.buildId == remoteBuild.buildId }) {
                                remoteBuild.copy(isFavorite = true)
                            } else {
                                remoteBuild.copy(isFavorite = false)
                            }
                        }
                        _uiState.update {
                            it.copy(
                                builds = updatedRemoteBuilds
                            )
                        }
                    }
                }
            }
        }
    }

    private fun addToSaved(build: BuildsUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addBuildToSavedUseCase.invoke(build = build).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        build.isFavorite = true
                    }
                }
            }
        }
    }

    private fun removeBuildFromSaved(build: BuildsUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeBuildFromSavedUseCase.invoke(buildId = build.buildId).collect { state ->
                when (state) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        build.isFavorite = false
                    }
                }
            }
        }
    }
}