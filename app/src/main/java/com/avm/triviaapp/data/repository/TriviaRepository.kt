package com.avm.triviaapp.data.repository

import com.avm.triviaapp.core.network.ApiResponse
import com.avm.triviaapp.domain.enums.Difficulty
import com.avm.triviaapp.domain.models.responses.TriviaCategoriesResponse
import com.avm.triviaapp.domain.models.responses.TriviaResponse

interface TriviaRepository {

    suspend fun fetchCategories(): ApiResponse<TriviaCategoriesResponse?>

    suspend fun fetchTriviaQuestions(
        category: Int,
        difficulty: Difficulty
    ): ApiResponse<TriviaResponse?>
}