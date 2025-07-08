package com.strathmore.sambazasherehe

import androidx.compose.animation.core.copy
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.withContext
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EventViewModel(private val dao: EventDao) : ViewModel() {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("events")
    val events = mutableStateListOf<Event>()

    init {
        fetchEvents()
    }
    private fun fetchEvents() {
        database.get().addOnSuccessListener { snapshot ->
            events.clear()
            for (eventSnapshot in snapshot.children) {
                val event = eventSnapshot.getValue(Event::class.java)
                event?.let { events.add(it) }
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // Log or handle the exception (e.g., print to Logcat for now)
        println("Coroutine error: ${throwable.message}")
    }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                events.addAll(dao.getAll())
            } catch (e: Exception) {
                println("Failed to load events: ${e.message}")
            }
        }
    }

    fun addEvent(event: Event) { // Pass the original event, likely without any ID set yet
        val firebaseKey: String = database.push().key ?: return

        // Create a new Event object to save to Firebase, including the Firebase key
        val eventForFirebase = event.copy(firebaseId = firebaseKey) // Assuming you want to store it

        database.child(firebaseKey).setValue(eventForFirebase) // Store the object that has firebaseId
            .addOnSuccessListener {
                viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
                    try {
                        // For Room, you might want to insert the original 'event'
                        // and let Room auto-generate its 'id' (Int).
                        // Then, potentially update this Room entry with the firebaseKey if needed.
                        // Or, save 'eventForFirebase' if your Room 'id' is not auto-generating
                        // and you manually set it (which is less common with Firebase keys).

                        // This part depends heavily on your Room setup for the 'id' field.
                        // If 'id' in Room is autoGenerate = true:
                        val newEventForRoom = event.copy(firebaseId = firebaseKey)
                        val generatedRoomId = dao.insert(newEventForRoom) // Assuming insert returns the new rowId

                        // You might need to fetch the event again or construct it with the generatedRoomId
                        // if you want the full object with both IDs in your local 'events' list.
                        // For simplicity, let's assume 'newEventForRoom' with its potentially 0 'id'
                        // (if not auto-generated and set) plus 'firebaseKey' is okay for the local list for now.
                        withContext(Dispatchers.Main) {
                            // You'll need to decide what object to add to the local list.
                            // Potentially, fetch it from DB to get the auto-generated Room ID.
                            events.add(newEventForRoom.copy(id = generatedRoomId.toInt())) // Example if insert returns Long
                        }
                    } catch (e: Exception) {
                        println("Failed to add event to Room or local list: ${e.message}")
                    }
                }
            }
            .addOnFailureListener {
                println("Failed to add event to Firebase: ${it.message}")
            }
    }


    /*database.child(eventId).setValue(event.copy(id = eventId))
    viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
        try {
            dao.insert(event)
            events.add(event)
        } catch (e: Exception) {
            println("Failed to add event: ${e.message}")
        }
    }
}*/

    init {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val eventsFromDb = dao.getAll()
            withContext(Dispatchers.Main) {
                events.addAll(eventsFromDb)
            }
        } catch (e: Exception) {
            println("DB Error: ${e.message}")
        }
    }
}
}

