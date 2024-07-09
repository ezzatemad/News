package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.SourceEntity

@Dao
interface SourcesDao {

    @Insert
    fun insertAll(sourceEntity: List<SourceEntity>)

    @Delete
    fun delete(sourceEntity: SourceEntity)

    @Query("SELECT * FROM SourceEntity")
    fun getAll(): List<SourceEntity>


//        @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//        fun loadAllByIds(userIds: IntArray): List<User>
//
//        @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//                "last_name LIKE :last LIMIT 1")
//        fun findByName(first: String, last: String): User
//
//

}