package com.strathmore.sambazasherehe

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun EventListScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: EventViewModel = viewModel(
        factory = EventViewModelFactory(
            AppDatabase.getDatabase(context).eventDao()
        )
    )
//fun EventListScreen(
   // onAddEventClick: () -> Unit,
   // viewModel: EventViewModel
//) 
   /* val viewModel: EventViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                EventViewModel(AppDatabase.getDatabase(get()).eventDao())
            }
        }
    )*/
    val events = viewModel.events
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Trending Events",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onAddEventClick) {
            Text("Add New Event")
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(events) { event ->
                EventItem(event = event)
            }
        }
    }
}

@Composable
fun EventItem(event: Event) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.name, style = MaterialTheme.typography.titleMedium)
            Text(text = event.location, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Discount: ${event.discount}%",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
