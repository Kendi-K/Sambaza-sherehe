// HomeScreen.kt
package com.strathmore.sambazasherehe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items 

import com.strathmore.sambazasherehe.Event

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: EventViewModel 
) {
    val events by viewModel.events.collectAsState() // Observe events as State

    LaunchedEffect(Unit) {
        viewModel.loadEvents() 
    }

    androidx.compose.material3.Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(title = { androidx.compose.material3.Text("Sambaza Sherehe - Home") })
        },
        floatingActionButton = {
            androidx.compose.material3.FloatingActionButton(onClick = { navController.navigate("add_event") }) {
                androidx.compose.material3.Icon(Icons.Filled.Add, contentDescription = "Add Event")
            }
        }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            if (events.isEmpty()) {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.Text("No events yet. Tap the '+' button to add one!")
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(events, key = { it.id }) { event -> // Use event.id as key

                        EventListItem(
                            event = event,
                            onEventClick = { eventId ->
                                navController.navigate("event_detail/${eventId}")
                            }
                        )
                        androidx.compose.material3.Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun EventListItem(event: Event, onEventClick: (Int) -> Unit) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onEventClick(event.id) }, // Use event.id
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        androidx.compose.foundation.layout.Column(modifier = Modifier.padding(16.dp)) {
            androidx.compose.material3.Text(text = event.name, style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(4.dp))
            androidx.compose.material3.Text(text = "Location: ${event.location}", style = androidx.compose.material3.MaterialTheme.typography.bodyMedium)
            if (event.discount > 0) {
                androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(4.dp))
                androidx.compose.material3.Text(
                    text = "Discount: ${event.discount}%",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
