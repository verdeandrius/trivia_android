package com.avm.triviaapp.domain.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Difficulty.kt
 *
 * This enum class represents the difficulty levels of trivia questions in the Open Trivia Database API.
 *
 * @param value The string value representing the difficulty level.
 */
@Serializable
enum class Difficulty(val value: String) {
    @SerialName("easy") Easy("easy"),
    @SerialName("medium") Medium("medium"),
    @SerialName("hard") Hard("hard");
}
