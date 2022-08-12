package br.com.zup.hellozupper.ui.benefit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.databinding.ActivityBenefitBinding
import br.com.zup.hellozupper.ui.benefit.viewmodel.BenefitViewModel

class BenefitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitBinding
    private val viewModel: BenefitViewModel by lazy {
        ViewModelProvider(this)[BenefitViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBenefitBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }
}