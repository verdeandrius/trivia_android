package com.avm.triviaapp.data.repository

import com.avm.triviaapp.core.network.ApiResponse
import com.avm.triviaapp.data.datasource.TriviaDataSource
import com.avm.triviaapp.domain.enums.Difficulty
import com.avm.triviaapp.domain.models.responses.TriviaCategoriesResponse
import com.avm.triviaapp.domain.models.responses.TriviaResponse

import javax.inject.Inject

/**
 * TriviaRepositoryImpl.kt
 *
 * This class implements the TriviaRepository interface, providing the logic to fetch trivia
 * questions and categories from the Open Trivia Database API. It uses NetworkHandler to execute
 * network calls and process responses.
 */
class TriviaRepositoryImpl @Inject constructor(
    private val dataSource: TriviaDataSource,
) : TriviaRepository {

    /**
     * Fetches the list of trivia categories from the API.
     *
     * @return ApiResponse containing TriviaCategoriesResponse that contains a list of TriviaCategory or an error message.
     */
    override suspend fun fetchCategories(): ApiResponse<TriviaCategoriesResponse?> {
        return dataSource.fetchCategories()
    }

    /**
     * Fetches the trivia questions based on the provided category and difficulty.
     *
     * @param category The category ID for filtering questions.
     * @param difficulty The difficulty level for filtering questions.
     * @return ApiResponse containing a TriviaResponse that contains a list of TriviaQuestion or an error message.
     */
    override suspend fun fetchTriviaQuestions(
        category: Int,
        difficulty: Difficulty
    ): ApiResponse<TriviaResponse?> {
        return dataSource.fetchTriviaQuestions(
            amount = 10,
            category = category,
            difficulty = difficulty.name)
    }
}