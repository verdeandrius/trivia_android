package com.avm.triviaapp.ui.commons

/**
 * UIState.kt
 *
 * This sealed class represents the different states of the UI. It is used to manage the UI based on
 * whether data is loading, has been successfully loaded, or if an error occurred.
 *
 * @param T The type of data expected in case of a successful load.
 */
sealed class UIState<out T> {
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
}