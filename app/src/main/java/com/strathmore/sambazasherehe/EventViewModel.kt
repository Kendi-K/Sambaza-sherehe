// EventViewModel.kt
package com.strathmore.sambazasherehe


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EventViewModel(private val dao: EventDao) : ViewModel() {

    private val _events = MutableStateFlow<List<Event>>(emptyList())
    val events: StateFlow<List<Event>> = _events.asStateFlow()

    init {
        loadEvents()
    }

    fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.getAllEventsFlow()
                    .collect { eventList -> 
                        _events.value = eventList
                    }
            } catch (e: Exception) {
                println("Failed to load events: ${e.message}")
                
            }
        }
    }

    fun addEvent(event: com.strathmore.sambaza_sherehe.Event) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                dao.insert(event)
                
            } catch (e: Exception) {
                println("Failed to add event: ${e.message}")
            }
        }
    }

    // Function to get a single event by ID
    fun getEventById(eventId: Int): Flow<Event?> {
        return dao.getEventByIdFlow(eventId) 
            .flowOn(Dispatchers.IO)
    }

    /*fun deleteEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) { // Also good practice to run DB operations on IO dispatcher
            try {
                dao.deleteEvent(event) // Corrected line
            } catch (e: Exception) {
                println("Failed to delete event: ${e.message}")
            }
        }*/
}

