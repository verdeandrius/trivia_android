package com.avm.triviaapp.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avm.triviaapp.domain.models.responses.TriviaCategory
import com.avm.triviaapp.data.repository.TriviaRepository
import com.avm.triviaapp.core.network.ApiResponse
import com.avm.triviaapp.ui.commons.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * HomeViewModel.kt
 *
 * This ViewModel is responsible for managing the logic related to the home screen, where a list of trivia categories is displayed.
 * It interacts with the TriviaRepository to fetch the categories and manages the UI state using UIState.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TriviaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<TriviaCategory>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<TriviaCategory>>> = _uiState.asStateFlow()

    /**
     * Fetches the trivia categories from the repository and updates the UI state based on the result.
     */
    fun fetchCategories() {
        viewModelScope.launch {
            when (val response = repository.fetchCategories()) {
                is ApiResponse.Success -> {
                    val data = response.data?.categories
                    _uiState.value = if (data.isNullOrEmpty()) {
                        UIState.Error("No categories found")
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