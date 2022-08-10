package br.com.zup.hellozupper.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.hellozupper.utils.EMAIL_ERROR_MESSAGE
import br.com.zup.hellozupper.utils.LOGIN_ERROR_MESSAGE
import br.com.zup.hellozupper.utils.PASSWORD_ERROR_MESSAGE
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.domain.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel(){
    private val authenticationRepository = AuthenticationRepository()
    val auth: FirebaseAuth = Firebase.auth

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun validateDataUser(user: User) : Boolean {
        when {
            user.email.isEmpty() -> {
                _errorState.value = EMAIL_ERROR_MESSAGE
                return false
            }
            user.password.isEmpty() -> {
                _errorState.value = PASSWORD_ERROR_MESSAGE
                return false
            }
            else -> {
                loginUser(auth, user)
                return true
            }
        }
    }

    private fun loginUser(auth: FirebaseAuth, user: User) {
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