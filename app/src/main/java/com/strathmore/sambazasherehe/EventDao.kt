package com.strathmore.sambazasherehe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM Event")
    suspend fun getAll(): List<Event>

    @Insert
    suspend fun insert(event: Event)
}
