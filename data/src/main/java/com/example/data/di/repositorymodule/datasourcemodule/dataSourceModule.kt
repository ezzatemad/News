package com.example.data.di.repositorymodule.datasourcemodule

import com.example.data.datasource.getNewsSourcesDataSource
import com.example.data.datasourceimpl.getNewsSourcesDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class dataSourceModule {


    @Binds
    abstract fun provideNewsSourcesDataSource(
        getNewsSourcesDataSourceImpl: getNewsSourcesDataSourceImpl
    ): getNewsSourcesDataSource
}