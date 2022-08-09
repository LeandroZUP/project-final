package br.com.zup.hellozupper

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.datasourse.remote.HelloZupperAPI
import br.com.zup.hellozupper.data.datasourse.remote.RetrofitService
import br.com.zup.hellozupper.data.model.PillarsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    var status = MutableLiveData<PillarsResponse>()
    fun getPillars(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){
                RetrofitService.apiService.getPillars()
            }
            status.value = response
        }
    }
}