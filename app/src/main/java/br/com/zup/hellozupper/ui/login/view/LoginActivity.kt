package br.com.zup.hellozupper.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.databinding.ActivityLoginBinding
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.ui.home.view.HomeActivity
import br.com.zup.hellozupper.ui.login.viewmodel.LoginViewModel
import br.com.zup.hellozupper.ui.register.view.RegisterActivity
import br.com.zup.hellozupper.utils.LOGIN_ERROR_MESSAGE
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        clickOnTextRegister()
        clickOnEnterButton()
    }

    private fun clickOnTextRegister() {
        binding.tvRegister.setOnClickListener {
            goToRegister()
        }
    }

    private fun clickOnEnterButton() {
        binding.bEnter.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
    }

    private fun getDataUser(): User {
        return User(
            email = binding.etEmail.text.toString(),
            password = binding.etLock.text.toString()
        )
    }

    private fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
        this.finish()
    }

    private fun initObservers() {
        viewModel.loginState.observe(this) {
            if (it) {
                goToHome()
            } else {
                Snackbar.make(binding.root, LOGIN_ERROR_MESSAGE, Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}