package com.avm.triviaapp.data.api

import com.avm.triviaapp.domain.models.responses.TriviaCategoriesResponse
import com.avm.triviaapp.domain.models.responses.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

/**
 * TriviaApiService.kt
 *
 * This interface defines the API endpoints for the Open Trivia Database.
 * It contains methods to fetch trivia questions based on different parameters.
 */
interface TriviaApiService {

    /**
     * Fetches trivia questions from the API.
     *
     * @param amount The number of questions to fetch.
     * @param category The category ID to filter questions by.
     * @param difficulty The difficulty level of the questions.
     * @return A list of trivia questions.
     */
    @GET("api.php")
    suspend fun getTriviaQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String
    ): Response<TriviaResponse>

    /**
     * Fetches trivia categories from the API.
     *
     * @return The response containing a list of trivia categories.
     */
    @GET("api_category.php")
    suspend fun getCategories(): Response<TriviaCategoriesResponse>
}