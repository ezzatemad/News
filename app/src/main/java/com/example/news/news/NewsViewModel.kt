package com.example.news.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.getNewsSourcesUseCase
import com.example.news.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var channel: Channel<NewsIntent>,
    private val getNewsSourcesUseCase: getNewsSourcesUseCase
) : ViewModel() {

    val state = SingleLiveEvent<NewsState>()


    init {
        processIntent()
    }

    private fun processIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is NewsIntent.LoadSource -> loadSources(intent.source)
                    is NewsIntent.LoadNews -> loadNews(intent.source)
                }
            }
        }
    }
    private fun loadNews(source: String) {
        state.value = NewsState.Loading
        viewModelScope.launch {
            try {
                val news = getNewsSourcesUseCase.getNews(source)
                state.value = NewsState.LoadNews(news)
            }catch (ex:Exception){
                Log.d("TAG", "loadNews: ${ex.localizedMessage}")
            }
        }

    }

    private fun loadSources(source: String) {
        state.value = NewsState.Loading
        viewModelScope.launch {
            try {
                val sources = getNewsSourcesUseCase.getSources(source)
                state.value = NewsState.Success(sources)
            } catch (e: Exception) {
                state.value = NewsState.Error(e.localizedMessage ?: "Unknown Error")
            }
        }
    }
}

