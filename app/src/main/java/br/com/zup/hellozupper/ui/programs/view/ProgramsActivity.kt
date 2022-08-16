package br.com.zup.hellozupper.ui.programs.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.data.model.Programs
import br.com.zup.hellozupper.databinding.ActivityProgramsBinding
import br.com.zup.hellozupper.ui.programs.viewmodel.ProgramsViewModel
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.PROGRAMAS
import br.com.zup.hellozupper.utils.PROGRAMS_KEY

class ProgramsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgramsBinding

    private val viewModel: ProgramsViewModel by lazy {
        ViewModelProvider(this)[ProgramsViewModel::class.java]
    }

    private val adapter: ProgramsAdapter by lazy {
        ProgramsAdapter(arrayListOf(), this::goToProgramsDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar()
        viewModel.getAllPrograms()
        initObserver()
        setUpRvProgramsList()
    }

    private fun initObserver() {
        viewModel.listState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateProgramsList(it.data.toMutableList())
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
                    binding.pbLoadingPrograms.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }

    private fun setUpRvProgramsList() {
        binding.rvPrograms.adapter = adapter
        binding.rvPrograms.layoutManager = LinearLayoutManager(this)
    }

    private fun goToProgramsDetail(programs: Programs) {
        val intent = Intent(this, ProgramsDetailActivity::class.java).apply {
            putExtra(PROGRAMS_KEY, programs)
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun supportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = PROGRAMAS
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}