package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.SourceEntity

@Dao
interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(sourceEntities: List<SourceEntity>)

    @Query("SELECT * FROM SourceEntity")
    suspend fun getAll(): List<SourceEntity>
}
