package br.com.zup.hellozupper.ui.register.viewmodel

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.domain.repository.AuthenticationRepository
import br.com.zup.hellozupper.utils.*

class RegisterViewModel : ViewModel() {
    private val authenticationRepository = AuthenticationRepository()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User): Boolean {
        return when {
            validateNameField(user) -> {
                false
            }
            validateEmailField(user) -> {
                false
            }
            validatePasswordField(user) -> {
                false
            }
            else -> {
                registerUser(user)
                true
            }
        }
    }

    private fun validateNameField(user: User): Boolean {
        when {
            user.name.isEmpty() -> {
                _errorState.value = NAME_ERROR_MESSAGE
                return true
            }
            user.name.length < 2 -> {
                _errorState.value = NAME_INCOMPLETE
                return true
            }
        }
        return false
    }

    private fun validateEmailField(user: User): Boolean {
        return if (user.email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(user.email)
                .matches() && user.email.contains("@zup.com.br")
        ) {
            false
        } else {
            _errorState.value = EMAIL_INVALID
            true
        }
    }

    private fun validatePasswordField(user: User): Boolean {
        when {
            user.password.isEmpty() -> {
                _errorState.value = PASSWORD_ERROR_MESSAGE
                return true
            }
            user.password.length < 6 -> {
                _errorState.value = PASSWORD_INCOMPLETE
                return true
            }
        }
        return false
    }

    private fun registerUser(user: User) {
        try {
            authenticationRepository.registerUser(
                user.email,
                user.password
            ).addOnSuccessListener {
                authenticationRepository.updateUserProfile(user.name)?.addOnSuccessListener {
                    _registerState.value = user
                }

            }.addOnFailureListener {
                _errorState.value = CREATE_USER_ERROR_MESSAGE
            }
        } catch (ex: Exception) {
            _errorState.value = ex.message
        }
    }
}