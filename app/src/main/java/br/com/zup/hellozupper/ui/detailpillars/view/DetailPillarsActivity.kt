package br.com.zup.hellozupper.ui.detailpillars.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.hellozupper.data.model.Pillar
import br.com.zup.hellozupper.databinding.ActivityDetailPillarsBinding
import br.com.zup.hellozupper.utils.KEY_PILLAR
import com.squareup.picasso.Picasso

class DetailPillarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPillarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPillarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPassedData()
    }

    private fun getPassedData() {
        val pillar = intent.getParcelableExtra<Pillar>(KEY_PILLAR)

        pillar?.let {
            showInformationOnScreen(it.image, it.title, it.description)
            supportActionBar(it.title)
        }
    }

    fun showInformationOnScreen(image: String, title: String, description: String) {
        Picasso.get().load(image)
            .into(binding.ivPillars)
        binding.tvTitlePillars.text = title
        binding.tvDescriptionPillars.text = description
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