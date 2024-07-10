package com.example.data.di.repositorymodule.roomdatabase

import android.content.Context
import androidx.room.Room
import com.example.data.database.dao.ArticlesDao
import com.example.data.database.dao.SourcesDao
import com.example.data.database.databasetable.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDataBaseModule {



//    @Provides
//    @Singleton
//    fun provideSourceRoomDatabase(
//        context: Context
//    ): SourcesDataBase {
//        return Room.databaseBuilder(
//            context, SourcesDataBase::class.java, "SourcesDB"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }


    @Provides
    @Singleton
    fun provideAppRoomDatabase(
        context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context, AppDataBase::class.java, "AppDB"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(
        appDataBase: AppDataBase
    ): ArticlesDao {
        return appDataBase.articlesDao()
    }

    @Singleton
    @Provides
    fun provideSourcesDao(
        appDataBase: AppDataBase
    ): SourcesDao {
        return appDataBase.sourcesDao()
    }
}