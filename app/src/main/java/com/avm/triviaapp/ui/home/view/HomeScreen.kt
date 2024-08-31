package com.avm.triviaapp.ui.home.view

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.avm.triviaapp.domain.models.responses.TriviaCategory
import com.avm.triviaapp.ui.commons.UIState
import com.avm.triviaapp.ui.home.viewmodel.HomeViewModel

/**
 * HomeScreen.kt (Temporary)
 *
 * This is a temporary version of the HomeScreen to verify that everything works correctly.
 * It consumes the HomeViewModel and displays the trivia categories fetched from the API.
 */

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    //onCategorySelected: (Int) -> Unit // Function to navigate to detail screen
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(uiState = uiState)
    viewModel.fetchCategories()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: UIState<List<TriviaCategory>>,
    //onCategorySelected: (Int) -> Unit // Function to navigate to detail screen
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trivia Categories") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                is UIState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UIState.Success -> {
                    val categories = (uiState).data
                    LazyColumn(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(categories) { category ->
                            Button(
                                onClick = { println("Category selected: ${category.id}") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(text = category.name)
                            }
                        }
                    }
                }
                is UIState.Error -> {
                    Text(
                        text = (uiState as UIState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
