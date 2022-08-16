package br.com.zup.hellozupper.ui.register.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.databinding.ActivityRegisterBinding
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.ui.home.view.HomeActivity
import br.com.zup.hellozupper.ui.login.view.LoginActivity
import br.com.zup.hellozupper.ui.register.viewmodel.RegisterViewModel
import br.com.zup.hellozupper.utils.USER_KEY
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObservers()
        clickOnSaveButton()
        clickOntextLogin()
    }

    private fun clickOntextLogin() {
        binding.tvLogin.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun clickOnSaveButton() {
        binding.bSave.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
    }

    private fun getDataUser(): User {
        return User(
            name = binding.etName.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etLock.text.toString()
        )
    }

    private fun initObservers() {
        viewModel.registerState.observe(this) {
            goToHome(it)
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToHome(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
        this.finish()
    }
}