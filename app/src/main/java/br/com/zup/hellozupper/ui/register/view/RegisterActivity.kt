package br.com.zup.hellozupper.ui.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.databinding.ActivityRegisterBinding
import br.com.zup.hellozupper.domain.model.User
import br.com.zup.hellozupper.ui.main.view.MainActivity
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