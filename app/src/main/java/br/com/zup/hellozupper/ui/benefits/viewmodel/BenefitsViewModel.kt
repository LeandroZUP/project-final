package br.com.zup.hellozupper.ui.benefits.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Benefits
import br.com.zup.hellozupper.domain.usecase.BenefitsUseCase
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_BENEFITS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BenefitsViewModel: ViewModel() {
    private val benefitsUseCase = BenefitsUseCase()

    private val _listState = MutableLiveData<ViewState<List<Benefits>>>()
    var listState: LiveData<ViewState<List<Benefits>>> = _listState

    private val _loading = MutableLiveData<ViewState<Boolean>>()
    var loading: LiveData<ViewState<Boolean>> = _loading

    fun getAllBenefits() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    benefitsUseCase.getAllBenefitsNetwork()
                }
                _listState.value = response
            } catch (ex: Exception) {
                _listState.value =
                    ViewState.Error(Throwable(ERRO_API_BENEFITS))
            } finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }
}