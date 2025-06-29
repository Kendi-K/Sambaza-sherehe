// EventViewModelFactory.kt
package com.strathmore.sambazasherehe
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class EventViewModelFactory(
    private val dao: EventDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventViewModel::class.java)) {
            return EventViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
