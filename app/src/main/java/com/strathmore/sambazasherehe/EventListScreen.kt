package com.strathmore.sambaza_sherehe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// ... other imports
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

@Composable
fun EventListScreen(
    navController: androidx.navigation.NavController,
    onAddEventClick: () -> Unit,
    //onDeleteClick: () -> Unit,
    viewModel: EventViewModel // Pass the viewModel
) {
    // val context = LocalContext.current 
    // val viewModel: EventViewModel = viewModel(...)
    val events by viewModel.events.collectAsState() // Observe events

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "All Events (List Screen)", 
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(onClick = onAddEventClick) {
            Text("Add New Event")
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (events.isEmpty()) {
            Text("No events available.")
        } else {
            LazyColumn {
                items(events, key = { it.id }) { event -> // Use event.id as key
                    EventListItem(
                        event = event,
                        onEventClick = { eventId ->
                            navController.navigate("event_detail/${eventId}")
                        },
                    )
                    Divider()
                }
            }
        }
    }
}
