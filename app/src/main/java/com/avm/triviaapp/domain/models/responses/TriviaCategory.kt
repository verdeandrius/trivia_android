package com.avm.triviaapp.domain.models.responses

import kotlinx.serialization.Serializable

/**
 * TriviaCategory.kt
 *
 * This data class represents a single trivia category, including its ID and name.
 *
 * @param id The ID of the trivia category.
 * @param name The name of the trivia category.
 */
@Serializable
data class TriviaCategory(
    val id: Int,
    val name: String
)
