package br.com.zup.hellozupper.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.utils.USER_KEY
import br.com.zup.hellozupper.databinding.ActivityLoginBinding
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.ui.login.viewmodel.LoginViewModel
import br.com.zup.hellozupper.ui.main.view.MainActivity
import br.com.zup.hellozupper.ui.register.view.RegisterActivity
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
        clickOntextRegister()
        clickOnEnterButton()
    }

    private fun clickOntextRegister() {
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
    }

    private fun initObservers() {
        viewModel.loginState.observe(this) {
            goToMain(it)
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToMain(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
    }
}