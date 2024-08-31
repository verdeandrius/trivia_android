package com.avm.triviaapp.domain.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TriviaQuestion.kt
 *
 * This data class represents a single trivia question, including its category, type, difficulty,
 * question text, and possible answers.
 *
 * @param category The category of the trivia question.
 * @param type The type of the trivia question (e.g., multiple choice, true/false).
 * @param difficulty The difficulty level of the trivia question.
 * @param question The text of the trivia question.
 * @param correctAnswer The correct answer to the trivia question.
 * @param incorrectAnswers The list of incorrect answers to the trivia question.
 */
@Serializable
data class TriviaQuestion(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,

    @SerialName("correct_answer")
    val correctAnswer: String,

    @SerialName("incorrect_answers")
    val incorrectAnswers: List<String>
)
