// EventDao.kt (Interface)
package com.strathmore.sambaza_sherehe

//import android.media.metrics.Event
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.strathmore.sambaza_sherehe.Event


@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)

    // Modify getAll to return Flow for reactive updates
    @Query("SELECT * FROM events ORDER BY name ASC") 
    fun getAllEventsFlow(): Flow<List<Event>>

    // Get a single event by ID
    @Query("SELECT * FROM events WHERE id = :eventId")
    fun getEventByIdFlow(eventId: Int): Flow<Event?>
    
    @Query("SELECT * FROM events ORDER BY name ASC")
    suspend fun getAll(): List<Event>

    @Query("SELECT * FROM events WHERE id = :eventId")
    suspend fun getEventById(eventId: Int): Event?

    //@Delete
    //suspend fun deleteEvent(event: Event)
}
