package br.com.zup.hellozupper.ui.pillars.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.data.model.Pillar
import br.com.zup.hellozupper.databinding.ActivityPillarsBinding
import br.com.zup.hellozupper.ui.detailpillars.view.DetailPillarsActivity
import br.com.zup.hellozupper.ui.pillars.viewmodel.PillarsViewModel
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.KEY_PILLAR
import br.com.zup.hellozupper.utils.NOSSO_DNA

class PillarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPillarsBinding

    private val viewModel: PillarsViewModel by lazy {
        ViewModelProvider(this)[PillarsViewModel::class.java]
    }

    private val adapter: PillarsAdapter by lazy {
        PillarsAdapter(arrayListOf(), this::goToPillarDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPillarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar()
        viewModel.getAllPillars()
        initObserver()
        setUpRvPillarsList()
    }

    private fun initObserver() {
        viewModel.listState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updatePillarsList(it.data.toMutableList())
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        this,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }

        viewModel.loading.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pbLoadingPillars.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }

    private fun setUpRvPillarsList() {
        binding.rvPillarsList.adapter = adapter
        binding.rvPillarsList.layoutManager = LinearLayoutManager(this)
    }

    private fun goToPillarDetail(pillar: Pillar) {
        val intent = Intent(this, DetailPillarsActivity::class.java).apply {
            putExtra(KEY_PILLAR, pillar)
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }
    private fun supportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = NOSSO_DNA
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}