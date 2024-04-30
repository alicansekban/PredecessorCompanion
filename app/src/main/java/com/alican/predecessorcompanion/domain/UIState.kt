package com.alican.predecessorcompanion.domain

sealed class UIState<T> {

    class Loading<T> : UIState<T>()

    data class Success<T>(val response : T) : UIState<T>()

    data class Error<T>(val errorMessage : String) : UIState<T>()

    class Empty<T> : UIState<T>()
}

