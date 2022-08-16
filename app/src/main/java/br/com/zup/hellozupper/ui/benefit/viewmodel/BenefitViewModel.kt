package br.com.zup.hellozupper.ui.benefit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.benefit.Benefit
import br.com.zup.hellozupper.domain.usecase.BenefitUseCase
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_BENEFIT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BenefitViewModel: ViewModel() {
    private val benefitUseCase = BenefitUseCase()

    private val _listState = MutableLiveData<ViewState<List<Benefit>>>()
    var listState: LiveData<ViewState<List<Benefit>>> = _listState

    private val _loading = MutableLiveData<ViewState<Boolean>>()
    var loading: LiveData<ViewState<Boolean>> = _loading

    fun getAllPrograms() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    benefitUseCase.getAllProgramsNetwork()
                }
                _listState.value = response
            } catch (ex: Exception) {
                _listState.value =
                    ViewState.Error(Throwable(ERRO_API_BENEFIT))
            } finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }
}