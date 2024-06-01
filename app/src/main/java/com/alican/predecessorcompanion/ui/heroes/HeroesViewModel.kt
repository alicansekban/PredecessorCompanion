package com.alican.predecessorcompanion.ui.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alican.predecessorcompanion.domain.UIState
import com.alican.predecessorcompanion.domain.use_case.heroes.GetHeroesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val getHeroesUseCase: GetHeroesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HeroesUIStateModel())
    val uiState: StateFlow<HeroesUIStateModel>
        get() = _uiState.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            HeroesUIStateModel()
        )

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            getHeroesUseCase.invoke().collect { uiState ->
                when (uiState) {
                    is UIState.Empty -> {}
                    is UIState.Error -> {}
                    is UIState.Loading -> {}
                    is UIState.Success -> {
                        _uiState.value = _uiState.value.copy(
                            heroes = uiState.response,
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}