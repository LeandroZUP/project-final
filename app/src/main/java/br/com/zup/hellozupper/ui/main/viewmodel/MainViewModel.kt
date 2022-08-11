package br.com.zup.hellozupper.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.domain.usecase.FeedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val feedUsecase = FeedUseCase(application)
    var status = MutableLiveData<List<Feed>>()
    fun getNotReadNews(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                feedUsecase.getNewsNotRead()
            }
            status.value = response
        }
    }
}