package br.com.zup.hellozupper.ui.benefits.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.data.model.Benefits
import br.com.zup.hellozupper.databinding.ActivityBenefitsBinding
import br.com.zup.hellozupper.ui.benefits.viewmodel.BenefitsViewModel
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.BENEFITS

class BenefitsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitsBinding
    private val viewModel: BenefitsViewModel by lazy {
        ViewModelProvider(this)[BenefitsViewModel::class.java]
    }

    private val adapter: BenefitsAdapter by lazy {
        BenefitsAdapter(arrayListOf()) //, this::goToBenefitsDetail
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBenefitsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRvPBenefitsList()
        supportActionBar()
        viewModel.getAllPrograms()
        initObserver()

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

        viewModel.loading.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pbLoadingBenefits.isVisible = it.loading == true
                }
                else -> {}
            }
        }
    }

    private fun setUpRvPBenefitsList() {
        binding.rvBenefitsButtons.adapter = adapter
        binding.rvBenefitsButtons.layoutManager = LinearLayoutManager(this)
    }

//    private fun goToBenefitsDetail(benefits: Benefits) {
//        Log.d("TODO", "Acertar o Benefícios Details")
//        val intent = Intent(this, BenefitDetailsActivity::class.java).apply {
//            putExtra(KEY_BENEFIT, benefit)
//        }
//        startActivity(intent)
//        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
//    }

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