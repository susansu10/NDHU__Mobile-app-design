package com.example.roomdatabaseex.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// mapping functions to the SQL

@Dao
interface ItemDao {
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: Item)

}