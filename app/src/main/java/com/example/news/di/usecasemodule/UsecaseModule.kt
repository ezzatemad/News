package com.example.news.di.usecasemodule

import com.example.domain.repository.getNewSourcesRepo
import com.example.domain.usecase.getNewsSourcesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UsecaseModule {


    @Provides
    fun provideComplaintsDriverUseCase(getNewSourcesRepo: getNewSourcesRepo): getNewsSourcesUseCase {
        return getNewsSourcesUseCase(getNewSourcesRepo)
    }
}