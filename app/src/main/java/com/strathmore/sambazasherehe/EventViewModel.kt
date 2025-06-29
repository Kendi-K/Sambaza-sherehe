package com.strathmore.sambazasherehe

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineExceptionHandler

class EventViewModel(private val dao: EventDao) : ViewModel() {
    val events = mutableStateListOf<Event>()

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

    fun addEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                dao.insert(event)
                events.add(event)
            } catch (e: Exception) {
                println("Failed to add event: ${e.message}")
            }
        }
    }
}