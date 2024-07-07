package com.example.data.di.repositorymodule

import com.example.data.api.MyApiService
import com.example.data.datasource.getNewsSourcesDataSource
import com.example.data.repositoryimpl.getNewsSourcesRepoImpl
import com.example.domain.repository.getNewSourcesRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    abstract fun provideNewsRepository(newsSourcesRepoImpl: getNewsSourcesRepoImpl): getNewSourcesRepo


}