package com.alican.predecessorcompanion.ui.home

sealed class HomeUIStateEvents {

    data class UpdateSearchQuery(val searchQuery: String) : HomeUIStateEvents()

    data object onContinue : HomeUIStateEvents()
}

data class HomeUIStateModel(
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)