package com.example.data.database.databasetable

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.Converters
import com.example.data.database.dao.ArticlesDao
import com.example.data.database.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ArticlesDataBase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
}