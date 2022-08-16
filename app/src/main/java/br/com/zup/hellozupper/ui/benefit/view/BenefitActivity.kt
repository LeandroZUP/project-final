package br.com.zup.hellozupper.ui.benefit.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.data.model.benefit.Benefit
import br.com.zup.hellozupper.databinding.ActivityBenefitBinding
import br.com.zup.hellozupper.ui.benefit.viewmodel.BenefitViewModel
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.BENEFITS
import br.com.zup.hellozupper.utils.KEY_BENEFIT

class BenefitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitBinding
    private val viewModel: BenefitViewModel by lazy {
        ViewModelProvider(this)[BenefitViewModel::class.java]
    }

    private val adapter: BenefitAdapter by lazy {
        BenefitAdapter(arrayListOf(), this::goToBenefitDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenefitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar()
        viewModel.getAllPrograms()
        initObserver()
        setUpRvPBenefitsList()
    }

    private fun initObserver() {
        viewModel.listState.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    adapter.updateBenefitsList(it.data.toMutableList())
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

        //TODO Consert Loading
        viewModel.loading.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pbLoadingBenefit.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }

    //TODO Consert RecyclerView
    private fun setUpRvPBenefitsList() {
        binding.rvBenefitsList.adapter = adapter
        binding.rvBenefitsList.layoutManager = LinearLayoutManager(this)
    }

    private fun goToBenefitDetail(benefit: Benefit) {
        val intent = Intent(this, BenefitDetailsActivity::class.java).apply {
            putExtra(KEY_BENEFIT, benefit)
        }
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
    }

    private fun supportActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = BENEFITS
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}