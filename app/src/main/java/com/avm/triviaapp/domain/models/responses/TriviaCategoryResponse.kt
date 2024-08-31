package com.avm.triviaapp.domain.models.responses

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TriviaCategoriesResponse.kt
 *
 * This data class represents the response from the Open Trivia Database API that includes
 * a list of trivia categories.
 *
 * @param categories The list of trivia categories.
 */
data class TriviaCategoriesResponse(
    @SerializedName("trivia_categories")
    val categories: List<TriviaCategory>
)
