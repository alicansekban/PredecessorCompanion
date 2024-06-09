package com.alican.predecessorcompanion.ui.players.detail

data class PlayerDetailUIState (
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val isEmpty: Boolean = false
)