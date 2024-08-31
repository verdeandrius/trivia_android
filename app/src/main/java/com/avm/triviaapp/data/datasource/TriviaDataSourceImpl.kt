package com.avm.triviaapp.data.datasource

import com.avm.triviaapp.core.network.ApiResponse
import com.avm.triviaapp.core.network.NetworkHandler
import com.avm.triviaapp.data.api.TriviaApiService
import com.avm.triviaapp.domain.models.responses.TriviaCategoriesResponse
import com.avm.triviaapp.domain.models.responses.TriviaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * TriviaDataSourceImpl.kt
 *
 * Implementation of the TriviaDataSource interface that interacts with the Trivia API.
 */
class TriviaDataSourceImpl @Inject constructor(
    private val apiService: TriviaApiService,
    private val networkHandler: NetworkHandler
) : TriviaDataSource {
    override suspend fun fetchCategories(): ApiResponse<TriviaCategoriesResponse?> {
        val response = networkHandler.executeNetworkCall {
            apiService.getCategories()
        }
        return response
    }

    override suspend fun fetchTriviaQuestions(amount: Int, category: Int, difficulty: String): ApiResponse<TriviaResponse?> {
        val response = networkHandler.executeNetworkCall {
            apiService.getTriviaQuestions(amount, category, difficulty)
        }
        return response
    }
}