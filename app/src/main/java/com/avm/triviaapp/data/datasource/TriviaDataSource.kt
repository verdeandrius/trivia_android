package com.avm.triviaapp.data.datasource

import com.avm.triviaapp.core.network.ApiResponse
import com.avm.triviaapp.domain.models.responses.TriviaCategoriesResponse
import com.avm.triviaapp.domain.models.responses.TriviaResponse

/**
 * TriviaDataSource.kt
 *
 * Interface that defines methods for fetching data from the Trivia API.
 */
interface TriviaDataSource {
    suspend fun fetchCategories(): ApiResponse<TriviaCategoriesResponse?>
    suspend fun fetchTriviaQuestions(amount: Int, category: Int, difficulty: String): ApiResponse<TriviaResponse?>
}