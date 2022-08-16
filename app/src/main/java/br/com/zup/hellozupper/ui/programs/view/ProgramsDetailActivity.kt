package br.com.zup.hellozupper.ui.programs.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.hellozupper.data.model.Programs
import br.com.zup.hellozupper.databinding.ActivityProgramsDetailBinding
import br.com.zup.hellozupper.utils.PROGRAMS_KEY
import com.squareup.picasso.Picasso

class ProgramsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgramsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPassedData()
    }

    private fun getPassedData() {
        val programs = intent.getParcelableExtra<Programs>(PROGRAMS_KEY)

        programs?.let {
            showInformationOnScreen(it.image, it.description)
            supportActionBar(it.title)
        }
    }

    fun showInformationOnScreen(image: String, description: String) {
        Picasso.get().load(image)
            .into(binding.ivPrograms)
        binding.tvDetailPrograms.text = description
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