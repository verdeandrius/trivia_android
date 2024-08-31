package com.avm.triviaapp.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avm.triviaapp.core.network.ApiResponse
import com.avm.triviaapp.data.repository.TriviaRepository
import com.avm.triviaapp.domain.enums.Difficulty
import com.avm.triviaapp.domain.models.responses.TriviaQuestion
import com.avm.triviaapp.ui.commons.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * TriviaDetailViewModel.kt
 *
 * This ViewModel is responsible for managing the logic related to the detail screen, where trivia questions
 * for a selected category are displayed. It interacts with the TriviaRepository to fetch the questions and
 * manages the UI state using UIState.
 */
@HiltViewModel
class TriviaDetailViewModel @Inject constructor(
    private val repository: TriviaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<TriviaQuestion>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<TriviaQuestion>>> = _uiState

    /**
     * Fetches the trivia questions for a given category and difficulty from the repository,
     * and updates the UI state based on the result.
     *
     * @param category The category ID for filtering questions.
     * @param difficulty The difficulty level for filtering questions.
     */
    fun fetchQuestions(category: Int, difficulty: Difficulty) {
        viewModelScope.launch {
            when (val response = repository.fetchTriviaQuestions(
                category = category,
                difficulty = difficulty)) {
                is ApiResponse.Success -> {
                    val data = response.data?.results
                    _uiState.value = if (data.isNullOrEmpty()) {
                        UIState.Error("No questions found")
                    } else {
                        UIState.Success(data)
                    }
                }
                is ApiResponse.Error -> {
                    _uiState.value = UIState.Error(response.message)
                }
            }
        }
    }
}