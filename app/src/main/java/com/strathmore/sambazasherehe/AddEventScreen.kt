package com.strathmore.sambaza_sherehe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import android.media.metrics.Event
import com.strathmore.sambaza_sherehe.Event

@Composable
fun AddEventScreen(
    onAddEvent: () -> Unit,
    viewModel: EventViewModel,
    onEventAdded: () -> Boolean,

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
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") } 
    var description by remember { mutableStateOf("") }
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
            value = date,
            onValueChange = { date = it },
            label = { Text("Date (e.g., YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Time (e.g., HH:MM AM/PM)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().heightIn(min = 80.dp), // Allow multi-line for description
            singleLine = false
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
                    viewModel.addEvent(Event(
                        name = name, location = location, discount = discount.toInt(), date = date, time = time, description = description
                    ))
                    name = ""
                    location = ""
                    date = ""
                    time = ""
                    description = ""
                    discount = ""
                    onAddEvent()
                }
            },
            enabled = name.isNotBlank() && location.isNotBlank() && discount.toIntOrNull() != null
        ) {
            Text("Add Event")
        }
    }
}
