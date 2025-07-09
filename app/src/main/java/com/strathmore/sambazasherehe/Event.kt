// Event.kt
package com.strathmore.sambaza_sherehe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, 
    val name: String,
    val location: String,
    val date: String, 
    val time: String, 
    val description: String, 
    val discount: Int
)
