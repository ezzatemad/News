package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.model.ArticleEntity


@Dao
interface ArticlesDao {

    @Insert
    fun insertAll(articleEntity: List<ArticleEntity>)

    @Delete
    fun delete(articleEntity: ArticleEntity)

    @Query("SELECT * FROM Articles")
    fun getAll(): List<ArticleEntity>
}