package br.com.zup.hellozupper.ui.feed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.data.model.FeedEntity
import br.com.zup.hellozupper.domain.usecase.FeedUseCase
import br.com.zup.hellozupper.ui.*
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(application: Application) : AndroidViewModel(application) {
    private val feedUseCase = FeedUseCase(application)
    private val _state = MutableLiveData<ViewState<List<Feed>>>()
    var state: LiveData<ViewState<List<Feed>>> = _state

    private val _readNews = MutableLiveData<String>()
    var readNews: LiveData<String> = _readNews

    private val _loading = MutableLiveData<ViewState<Boolean>>()
    var loading: LiveData<ViewState<Boolean>> = _loading


    fun getNotReadNews() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedUseCase.getNewsNotRead()
                }
                _state.value = response
            } catch (e: Exception) {
                _state.value = ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
            } finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }

    fun saveReadNews(news: Feed) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    feedUseCase.saveReadNewsIndex(news)
                }
                _readNews.value = MESSAGE_SUCCESS_NEWS_READ_ADDED
            } catch (e: Exception) {
                _readNews.value = MESSAGE_FAIL_NEWS_READ_ADDED
            }
        }
    }

    fun modifyReadNewsToNotRead(news: FeedEntity) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    feedUseCase.modifyReadNewsToNotRead(news)
                }
                _readNews.value = MESSAGE_SUCCESS_NEWS_READ_DELETED
            } catch (e: Exception) {
                _readNews.value = MESSAGE_FAIL_NEWS_READ_DELETED
            }
        }
    }

    fun getSearchNews(query: String) {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedUseCase.getSearchNews(query)
                }
                _state.value = response
            } catch (e: Exception) {
                _state.value = ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
            } finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }
}