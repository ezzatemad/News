package com.example.news.di.viewmodelmodule

import android.content.Context
import com.example.news.utils.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {


    @Provides
    fun provideNetworkMonitor(
        context: Context
    ): NetworkMonitor {
        return NetworkMonitor(context)
    }
}