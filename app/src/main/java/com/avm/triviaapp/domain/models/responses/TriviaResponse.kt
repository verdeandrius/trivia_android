package com.avm.triviaapp.domain.models.responses

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * TriviaResponse.kt
 *
 * This file defines the data model for the response received from the Open Trivia Database API.
 * It includes the questions, their types, and their correct and incorrect answers.
 */
@Serializable
data class TriviaResponse(
    @SerialName("response_code")
    val responseCode: Int,
    val results: List<TriviaQuestion>
)