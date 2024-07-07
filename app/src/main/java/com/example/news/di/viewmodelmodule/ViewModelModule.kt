package com.example.news.di.viewmodelmodule

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {


    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context {
        return context
    }
}