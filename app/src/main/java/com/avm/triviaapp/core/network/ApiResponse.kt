package com.avm.triviaapp.core.network

/**
 * ApiResponse.kt
 *
 * This sealed class represents the result of a network request. It can be one of the following:
 * - Success: Contains the data retrieved from the network.
 * - Error: Contains the error message or exception if the request failed.
 *
 * @param T The type of data expected in case of success.
 */
sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T?) : ApiResponse<T>()
    data class Error(val message: String, val cause: Throwable? = null) : ApiResponse<Nothing>()
}