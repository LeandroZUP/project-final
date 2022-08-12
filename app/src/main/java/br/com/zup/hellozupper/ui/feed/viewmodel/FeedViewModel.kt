package br.com.zup.hellozupper.ui.feed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.domain.usecase.FeedUseCase
import br.com.zup.hellozupper.ui.MESSAGE_FAIL_LOAD_NEWS_LIST
import br.com.zup.hellozupper.ui.MESSAGE_SUCCESS_NEWS_READ_ADDED
import br.com.zup.hellozupper.ui.viewstate.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(application: Application) : AndroidViewModel(application) {
    private val feedUseCase = FeedUseCase(application)
    private val _status = MutableLiveData<ViewState<List<Feed>>>()
    var status: LiveData<ViewState<List<Feed>>> = _status

    private val _readNews = MutableLiveData<String>()
    var readNews: LiveData<String> = _readNews

    private val _loading = MutableLiveData<ViewState<Boolean>>()
    var loading: LiveData<ViewState<Boolean>> = _loading

    fun getSearchListNews(): ViewState<List<Feed>>? {
        return _status.value
    }

    fun getNotReadNews() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedUseCase.getNewsNotRead()
                }
                _status.value = response
            } catch (e: Exception) {
                _status.value = ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
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
            }catch (e: Exception) {
                _readNews.value = "Fail"
            }
        }
    }

    fun getAllReadNews() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    feedUseCase.getAllNews()
                }
                _status.value = response
            } catch (e: Exception) {
                _status.value = ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
            } finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }
}