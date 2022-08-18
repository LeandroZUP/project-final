package br.com.zup.hellozupper.ui.benefits.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.hellozupper.data.model.Benefits
import br.com.zup.hellozupper.databinding.ActivityBenefitsDetailsBinding
import br.com.zup.hellozupper.utils.KEY_BENEFITS

class BenefitsDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenefitsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPassedData()
    }

    private fun getPassedData() {
        val benefits = intent.getParcelableExtra<Benefits>(KEY_BENEFITS)

        benefits?.let {
            showInformationOnScreen(
                it.descriptionOne,
                it.descriptionTwo,
                it.descriptionThree,
                it.descriptionFour,
                it.descriptionFive,
                it.descriptionSix
            )
            supportActionBar(it.title)
        }
    }

    private fun showInformationOnScreen(
        descriptionOne: String,
        descriptionTwo: String,
        descriptionThree: String,
        descriptionFour: String,
        descriptionFive: String,
        descriptionSix: String,
    ) {
        binding.tvDescriptionOne.text = descriptionOne
        binding.tvDescriptionTwo.text = descriptionTwo
        binding.tvDescriptionThree.text = descriptionThree
        binding.tvDescriptionFour.text = descriptionFour
        binding.tvDescriptionFive.text = descriptionFive
        binding.tvDescriptionSix.text = descriptionSix
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