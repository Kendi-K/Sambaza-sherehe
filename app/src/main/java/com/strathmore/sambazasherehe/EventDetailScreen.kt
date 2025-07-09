// EventDetailScreen.kt
package com.strathmore.sambazasherehe

//import android.media.metrics.Event
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.strathmore.sambazasherehe.Event


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    navController: NavController,
    viewModel: EventViewModel,
    eventId: Int 
) {
  
    val event by viewModel.getEventById(eventId).collectAsState(initial = null)


    LaunchedEffect(eventId) {
       
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(event?.name ?: "Event Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            event?.let { currentEvent ->
                Text("Event Name: ${currentEvent.name}", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Location: ${currentEvent.location}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Date: ${currentEvent.date}", style = MaterialTheme.typography.bodyLarge) 
                Spacer(modifier = Modifier.height(8.dp))
                Text("Time: ${currentEvent.time}", style = MaterialTheme.typography.bodyLarge) 
                Spacer(modifier = Modifier.height(8.dp))
                Text("Description: ${currentEvent.description}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Discount: ${currentEvent.discount}%", style = MaterialTheme.typography.bodyMedium)
                
            } ?: run {
                Text("Event not found or loading...")
                
            }
        }
    }
}
