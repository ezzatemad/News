package com.example.news.di.channelmodule

import com.example.news.news.NewsIntent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.channels.Channel


@Module
@InstallIn(ViewModelComponent::class)
class ChannelModule {

    @Provides
    fun provideNewsIntentChannel(): Channel<NewsIntent> {
        return Channel(Channel.UNLIMITED)
    }
}