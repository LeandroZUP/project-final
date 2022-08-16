package br.com.zup.hellozupper.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.hellozupper.utils.EMAIL_ERROR_MESSAGE
import br.com.zup.hellozupper.utils.LOGIN_ERROR_MESSAGE
import br.com.zup.hellozupper.utils.PASSWORD_ERROR_MESSAGE
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.domain.repository.AuthenticationRepository

class LoginViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User): Boolean {
        return when {
            validateEmailField(user) -> {
                false
            }
            validatePasswordField(user) -> {

                false
            }
            else -> {
                loginUser(user)
                true
            }
        }
    }

    private fun validateEmailField(user: User): Boolean {

        if (user.email.isEmpty()) {
            _errorState.value = EMAIL_ERROR_MESSAGE
            return true
        }
        return false
    }

    private fun validatePasswordField(user: User): Boolean {

        if (user.password.isEmpty()) {
            _errorState.value = PASSWORD_ERROR_MESSAGE
            return true
        }
        return false
    }

    private fun loginUser(user: User) {
        try {
            authenticationRepository.loginUser(
                user.email,
                user.password
            ).addOnSuccessListener {
                _loginState.value = user
            }.addOnFailureListener {
                _errorState.value = LOGIN_ERROR_MESSAGE
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}