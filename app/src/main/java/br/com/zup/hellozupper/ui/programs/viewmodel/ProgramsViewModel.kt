package br.com.zup.hellozupper.ui.programs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.hellozupper.data.model.Programs
import br.com.zup.hellozupper.domain.usecase.ProgramsUseCase
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_PROGRAMS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProgramsViewModel : ViewModel() {
    private val programsUseCase = ProgramsUseCase()

    private val _listState = MutableLiveData<ViewState<List<Programs>>>()
    var listState: LiveData<ViewState<List<Programs>>> = _listState

    private val _loading = MutableLiveData<ViewState<Boolean>>()
    var loading: LiveData<ViewState<Boolean>> = _loading

    fun getAllPrograms() {
        _loading.value = ViewState.Loading(true)
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    programsUseCase.getAllProgramsNetwork()
                }
                _listState.value = response
            } catch (ex: Exception) {
                _listState.value =
                    ViewState.Error(Throwable(ERRO_API_PROGRAMS))
            } finally {
                _loading.value = ViewState.Loading(false)
            }
        }
    }
}