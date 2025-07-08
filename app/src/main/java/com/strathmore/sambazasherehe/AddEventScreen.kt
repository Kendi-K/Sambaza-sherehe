package com.strathmore.sambazasherehe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

@Composable
fun AddEventScreen(
    onAddEvent: () -> Unit,
    viewModel: EventViewModel
) {
   /* val viewModel: EventViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                EventViewModel(AppDatabase.getDatabase(get()).eventDao())
            }
        }
    )*/
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Add New Event",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Event Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Location") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = discount,
            onValueChange = { discount = it },
            label = { Text("Discount (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (name.isNotBlank() && location.isNotBlank() && discount.toIntOrNull() != null) {
                    val newEvent = Event(name = name, location = location, discount = discount.toInt())
                    viewModel.addEvent(newEvent)
                    onAddEvent()
                }
            },
            enabled = name.isNotBlank() && location.isNotBlank() && discount.toIntOrNull() != null
        ) {
            Text("Add Event")
        }
    }
}


