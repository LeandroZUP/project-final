package br.com.zup.hellozupper

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.domain.usecase.FeedUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val feedUsecase = FeedUsecase(application)
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