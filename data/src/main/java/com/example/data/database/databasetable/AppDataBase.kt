package com.example.data.database.databasetable

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.Converters
import com.example.data.database.dao.ArticlesDao
import com.example.data.database.dao.SourcesDao
import com.example.data.database.model.ArticleEntity
import com.example.data.database.model.SourceEntity

@Database(entities = [ArticleEntity::class, SourceEntity::class], version = 100)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao

    abstract fun sourcesDao(): SourcesDao
}