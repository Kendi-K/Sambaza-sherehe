package com.strathmore.sambaza_sherehe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.strathmore.sambaza_sherehe.ui.theme.EventAppTheme // <--- ADD THIS IMPORT


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventAppTheme { // Ensure EventAppTheme is defined
                val viewModel: EventViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
                                @Suppress("UNCHECKED_CAST")
                                return EventViewModel(AppDatabase.getDatabase(this@MainActivity).eventDao()) as T
                            }
                            throw IllegalArgumentException("Unknown ViewModel class")
                        }
                    }
                )
                AppNavigation(viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: EventViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") { // Start at home
        composable("home_screen") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("add_event") {
            AddEventScreen(
                onAddEvent = {
                    // Handle the event addition logic here
                },
                viewModel = viewModel,
                onEventAdded = {
                    navController.popBackStack() // Go back after adding
                    true // Return true to indicate success
                },
            )
        }
        composable(
            route = "event_detail/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId")
            eventId?.let {
                EventDetailScreen(
                    navController = navController,
                    viewModel = viewModel,
                    eventId = it
                )
            } ?: run {
                // Handle error: eventId not found, perhaps navigate back or show error
                navController.popBackStack()
            }
        }
        // You might still want EventListScreen for a different purpose,
        // or you can remove it if HomeScreen replaces its functionality.
        composable("event_list_debug") { // Example if you keep EventListScreen
            val onAddEventClick = {
                navController.navigate("add_event")
            }
            EventListScreen(
                navController = navController,
                onAddEventClick = onAddEventClick,
                viewModel = viewModel // Pass viewModel
            )
        }
    }
}


