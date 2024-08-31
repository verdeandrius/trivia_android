package com.avm.triviaapp.ui.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

/**
 * TriviaDetailScreen.kt
 *
 * This file defines the Detail screen for trivia questions.
 *
 * @param navController The NavHostController used to manage navigation from this screen.
 */
@Composable
fun TriviaDetailScreen(modifier: Modifier = Modifier) {
//fun TriviaDetailScreen(navController: NavHostController) {
    Row {
        Text("Some text")
        Text("Some more text")
        Text("Last text")
    }
}

@Preview(showBackground = true)
@Composable
fun TriviaDetailScreenPreview() {
    Surface (color = MaterialTheme.colorScheme.background){
        TriviaDetailScreen()
    }

}