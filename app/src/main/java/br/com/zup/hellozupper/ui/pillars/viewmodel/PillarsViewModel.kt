package br.com.zup.hellozupper.ui.pillars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Pillar
import br.com.zup.hellozupper.domain.usecase.PillarsUseCase
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_PILLARS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PillarsViewModel: ViewModel() {
    private val pillarsUseCase = PillarsUseCase()

    private val _listState = MutableLiveData<ViewState<List<Pillar>>>()
    var listState: LiveData<ViewState<List<Pillar>>> = _listState

    private val _loading = MutableLiveData<ViewState<Boolean>>()
    var loading: LiveData<ViewState<Boolean>> = _loading

    fun getAllRickAndMorty() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    pillarsUseCase.getAllPillarsNetwork()
                }
                _listState.value = response
            } catch (ex: Exception) {
                _listState.value =
                    ViewState.Error(Throwable(ERRO_API_PILLARS))
            }finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }
}