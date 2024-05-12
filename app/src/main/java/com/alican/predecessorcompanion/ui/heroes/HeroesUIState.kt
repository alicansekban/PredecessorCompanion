package com.alican.predecessorcompanion.ui.heroes

import com.alican.predecessorcompanion.domain.ui_model.heroes.HeroesUIModel


sealed class HeroesUIEvents {

}

data class HeroesUIStateModel(
    val heroes : List<HeroesUIModel> = emptyList(),
    val isLoading : Boolean = true,
    val isSuccess : Boolean = false,
    val isError : Boolean = false,
    val errorMessage : String = ""
)