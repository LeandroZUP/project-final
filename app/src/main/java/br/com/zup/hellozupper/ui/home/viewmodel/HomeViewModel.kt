package br.com.zup.hellozupper.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import br.com.zup.hellozupper.domain.repository.AuthenticationRepository

class HomeViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    fun getUserName() = authenticationRepository.getNameUser()

    fun getUserEmail() = authenticationRepository.getEmailUser()

    fun logoutUser() = authenticationRepository.logoutUser()
}