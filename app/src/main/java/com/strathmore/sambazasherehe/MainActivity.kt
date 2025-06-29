package com.strathmore.sambazasherehe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.strathmore.sambazasherehe.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventAppTheme {
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

/*class ViewModelFactory(private val context: ComponentActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventViewModel(AppDatabase.getDatabase(context).eventDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/

@Composable
fun AppNavigation(viewModel: EventViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "event_list") {
        composable("event_list") {
            EventListScreen(
                onAddEventClick = { navController.navigate("add_event") },
                viewModel = viewModel
            )
        }
        composable("add_event") {
            AddEventScreen(
                onAddEvent = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}
setContent {
    EventAppTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "events") {
            composable("events") {
                EventListScreen(navController)
            }
            composable("addEvent") {
                AddEventScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}
