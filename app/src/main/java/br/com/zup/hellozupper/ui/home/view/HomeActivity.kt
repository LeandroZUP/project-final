package br.com.zup.hellozupper.ui.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.databinding.ActivityHomeBinding
import br.com.zup.hellozupper.ui.benefit.view.BenefitActivity
import br.com.zup.hellozupper.ui.feed.view.FeedActivity
import br.com.zup.hellozupper.ui.home.viewmodel.HomeViewModel
import br.com.zup.hellozupper.ui.pillars.view.PillarsActivity
import br.com.zup.hellozupper.ui.program.view.ProgramActivity
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
        startActivity(Intent(this, BenefitActivity::class.java))
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
        startActivity(Intent(this, ProgramActivity::class.java))
    }

    private fun supportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = HELLO + viewModel.getUserName()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}