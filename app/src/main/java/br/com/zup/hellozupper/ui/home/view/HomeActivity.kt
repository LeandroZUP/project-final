package br.com.zup.hellozupper.ui.home.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.databinding.ActivityHomeBinding
import br.com.zup.hellozupper.ui.benefits.view.BenefitsActivity
import br.com.zup.hellozupper.ui.feed.view.FeedActivity
import br.com.zup.hellozupper.ui.home.viewmodel.HomeViewModel
import br.com.zup.hellozupper.ui.login.view.LoginActivity
import br.com.zup.hellozupper.ui.pillars.view.PillarsActivity
import br.com.zup.hellozupper.ui.programs.view.ProgramsActivity
import br.com.zup.hellozupper.utils.HELLO

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar()
        clickOnButtonOurDNA()
        clickOnButtonBenefit()
        clickOnButtonFeed()
        clickOnButtonProgram()
        clickOnImageWhatsapp()
    }

    private fun clickOnButtonOurDNA() {
        binding.btnDna.setOnClickListener {
            goToDNA()
        }
    }

    private fun goToDNA() {
        startActivity(Intent(this, PillarsActivity::class.java))
    }

    private fun clickOnButtonBenefit() {
        binding.btnBenefits.setOnClickListener {
            goToBenefit()
        }
    }

    private fun goToBenefit() {
        startActivity(Intent(this, BenefitsActivity::class.java))
    }

    private fun clickOnImageWhatsapp() {
        binding.ivWhatsapp.setOnClickListener {
            goToWhatsapp()
        }
    }

    private fun goToWhatsapp() {
        val urlWhatsapp =
            "https://api.whatsapp.com/send?phone=3498319866&text= Olá meu nome e ${viewModel.getUserName()}\n" +
                    "Email: ${viewModel.getUserEmail()}"
        intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(urlWhatsapp)
        startActivity(intent)
    }

    private fun clickOnButtonFeed() {
        binding.btnFeed.setOnClickListener {
            goToFeed()
        }
    }

    private fun goToFeed() {
        startActivity(Intent(this, FeedActivity::class.java))
    }

    private fun clickOnButtonProgram() {
        binding.btnPrograms.setOnClickListener {
            goToProgram()
        }
    }

    private fun goToProgram() {
        startActivity(Intent(this, ProgramsActivity::class.java))
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun supportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = HELLO + viewModel.getUserName()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            goToLogin()
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}