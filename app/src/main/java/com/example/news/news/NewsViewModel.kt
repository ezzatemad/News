package com.example.news.news

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.getNewsSourcesUseCase
import com.example.news.utils.NetworkMonitor
import com.example.news.utils.NetworkUtils
import com.example.news.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val context: Context,
    var channel: Channel<NewsIntent>,
    private val getNewsSourcesUseCase: getNewsSourcesUseCase,
    private val networkMonitor: NetworkMonitor
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
                    is NewsIntent.LoadSearchNews -> loadSearchNews(intent.search)
                }
            }
        }
    }


    private fun loadSearchNews(search: String) {
        state.value = NewsState.Loading
        viewModelScope.launch {
            try {
                if (networkMonitor.checkForInternet()) {
                    val searchNews = withContext(Dispatchers.IO) {
                        getNewsSourcesUseCase.getSearchNews(search)
                    }
                    state.postValue(NewsState.LoadSearchNews(searchNews))
                } else {
                    val localSearchNews = withContext(Dispatchers.IO) {
                        getNewsSourcesUseCase.getLocalSearchNews(search)
                    }
                    state.postValue(NewsState.LoadSearchNews(localSearchNews))
                }
            } catch (ex: Exception) {
                Log.d("TAG", "loadNews: ${ex.localizedMessage}")
            }
        }

    }

    private fun loadNews(source: String) {
        state.postValue(NewsState.Loading)
        viewModelScope.launch {
            try {
                if (networkMonitor.checkForInternet()) {

                    val news = withContext(Dispatchers.IO) {
                        getNewsSourcesUseCase.getNews(source)
                    }
                    state.postValue(NewsState.LoadNews(news))
                } else {
                    val localNews = withContext(Dispatchers.IO) {
                        getNewsSourcesUseCase.getLocalNews(source)
                    }
                    state.postValue(NewsState.LoadNews(localNews))
                }
            } catch (ex: Exception) {
                Log.d("TAG", "loadNews: ${ex.localizedMessage}")
            }
        }

    }

    private fun loadSources(source: String) {
        state.postValue(NewsState.Loading)
        viewModelScope.launch {
            try {
                if (networkMonitor.checkForInternet()) {
                    val sources = withContext(Dispatchers.IO) {
                        getNewsSourcesUseCase.getSources(source)
                    }
                    state.postValue(NewsState.Success(sources))
                } else {
                    //load sources from local database
                    val localSources = withContext(Dispatchers.IO) {
                        getNewsSourcesUseCase.getLocalSources(source)
                    }
                    state.postValue(NewsState.Success(localSources))
                }
            } catch (e: Exception) {
                state.postValue(NewsState.Error(e.localizedMessage ?: "Unknown Error"))
            }
        }
    }
}

