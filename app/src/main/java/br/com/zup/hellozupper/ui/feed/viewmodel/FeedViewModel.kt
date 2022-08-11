package br.com.zup.hellozupper.ui.feed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.data.model.FeedEntity
import br.com.zup.hellozupper.domain.usecase.FeedUseCase
import br.com.zup.hellozupper.ui.MESSAGE_SUCCESS_NEW_READ_ADDED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FeedViewModel(application: Application): AndroidViewModel(application) {
    private val feedUseCase = FeedUseCase(application)
    private val _status = MutableLiveData<List<Feed>>()
    var status: LiveData<List<Feed>> = _status

    private val _readNews = MutableLiveData<String>()
    var readNews: LiveData<String> = _readNews

    fun getNotReadNews(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                feedUseCase.getNewsNotRead()
            }
            _status.value = response
        }
    }

    fun saveReadNews(news: Feed){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                feedUseCase.saveReadNewsIndex(news)
            }
        }
        _readNews.value = MESSAGE_SUCCESS_NEW_READ_ADDED
    }
}