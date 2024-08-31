
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.avm.triviaapp.ui.home.view.HomeScreen

/**
 * NavGraph.kt
 *
 * This file defines the navigation graph for the application using Jetpack Compose Navigation.
 * It connects the different screens in the app, such as the Home, Detail, and Result screens.
 *
 * @param navController The NavHostController used to manage app navigation.
 */
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen()
        }
        /*composable("detail") {
            TriviaDetailScreen()
        }*/

        /*
        composable("result") {
            TriviaResultScreen(navController)
        }*/
    }
}