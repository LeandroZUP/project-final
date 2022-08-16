package br.com.zup.hellozupper.ui.benefit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.hellozupper.data.model.benefit.Benefit
import br.com.zup.hellozupper.databinding.ActivityBenefitDetailsBinding
import br.com.zup.hellozupper.utils.KEY_BENEFIT

class BenefitDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getPassedData()
    }

    private fun getPassedData() {
        val benefit = intent.getParcelableExtra<Benefit>(KEY_BENEFIT)

        benefit?.let {
            showInformationOnScreen(it.description)
            supportActionBar(it.title)
        }
    }

    //TODO Text View Detalhes
    private fun showInformationOnScreen(description: String) {
        binding.tvDetailBenefit
    }

    private fun supportActionBar(title: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}