package com.alican.predecessorcompanion.ui.saved.builds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.ui_model.builds.BuildsUIModel
import com.alican.predecessorcompanion.domain.use_case.builds.GetSavedBuildsUseCase
import com.alican.predecessorcompanion.domain.use_case.builds.RemoveBuildFromSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedBuildsViewModel @Inject constructor(
    private val getSavedBuildsUseCase: GetSavedBuildsUseCase,
    private val removeBuildFromSavedUseCase: RemoveBuildFromSavedUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedBuildsUIStateModel())
    val uiState: StateFlow<SavedBuildsUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly, SavedBuildsUIStateModel()
        )

    init {
        getSavedBuilds()
    }

    private fun getSavedBuilds() {
        viewModelScope.launch(Dispatchers.IO) {
            getSavedBuildsUseCase.invoke().collect { uiState ->
                when (uiState) {
                    is UIState.Empty -> _uiState.emit((SavedBuildsUIStateModel()))
                    is UIState.Success -> {
//                        _uiState.emit(_uiState.value.copy(savedPlayers = uiState.response))

                        _uiState.value = _uiState.value.copy(
                            savedBuilds = uiState.response,
                        )
                    }

                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                }
            }
        }
    }

    fun removeFromSavedBuilds(model: BuildsUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            removeBuildFromSavedUseCase.invoke(model.buildId).collect {
                if (it is UIState.Success) {

                }
            }
        }
    }
}