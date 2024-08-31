package com.avm.triviaapp.domain.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ResponseCode.kt
 *
 * This enum class represents the various response codes that can be returned by the
 * Open Trivia Database API, indicating the status of the API request.
 *
 * @param value The integer value representing the response code.
 */
@Serializable
enum class ResponseCode(private val value: Long) {
    @SerialName("0") Success(0),
    @SerialName("1") NoResults(1),
    @SerialName("2") InvalidParameter(2),
    @SerialName("3") TokenNotFound(3),
    @SerialName("4") TokenEmpty(4),
    @SerialName("5") RateLimit(5);
}
