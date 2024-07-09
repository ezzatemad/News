package com.example.data.database.databasetable

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.data.Converters
import com.example.data.database.dao.ArticlesDao
import com.example.data.database.dao.SourcesDao
import com.example.data.database.model.SourceEntity

@Database(entities = [SourceEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SourcesDataBase : RoomDatabase() {

    abstract fun sourcesDao(): SourcesDao

}