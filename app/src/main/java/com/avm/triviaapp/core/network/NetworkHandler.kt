package com.avm.triviaapp.core.network

import retrofit2.Response
import javax.inject.Inject

/**
 * NetworkHandler.kt
 *
 * This class is responsible for managing network requests, checking connectivity status, and handling
 * responses using Retrofit's Response object and a custom ApiResponse sealed class.
 */
class NetworkHandler @Inject constructor(
    private val connectivityObserver: ConnectivityObserver
) {

    private val clientErrors = 400..499
    private val serverErrors = 500..599
    private val tokenExpired = 403

    suspend fun <T> executeNetworkCall(
        apiCall: suspend () -> Response<T>
    ): ApiResponse<T?> =
        // Verify internet connection established.
        if (connectivityObserver.isConnected()) {
            try {
                // Make call using Retrofit, then handle the received response.
                handleNetworkResponse(apiCall())
            } catch (exception: Exception) {
                // Response models are not accurate, expected parsing errors.
                ApiResponse.Error(message = exception.message ?: "Unknown error", cause = exception)
            }
        } else {
            // Create a failure response to handle this scenario.
            ApiResponse.Error(message = "No internet connection", cause = null)
        }

    /**
     * Method responsible for creating an ApiResponse object with the HTTP Response received.
     * @param httpsResponse Response object from Retrofit.
     * @return ApiResponse Retrieve the response received wrapped by an ApiResponse object.
     */
    private fun <T> handleNetworkResponse(
        httpsResponse: Response<T>
    ): ApiResponse<T?> {
        with(httpsResponse) {
            return if (isSuccessful) {
                ApiResponse.Success(data = body())
            } else {
                ApiResponse.Error(
                    message = message(),
                    cause = null
                )
            }
        }
    }
}