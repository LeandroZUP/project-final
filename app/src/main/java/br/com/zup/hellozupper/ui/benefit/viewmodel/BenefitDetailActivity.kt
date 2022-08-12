package br.com.zup.hellozupper.ui.benefit.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.hellozupper.databinding.ActivityBenefitDetailBinding

class BenefitDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenefitDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}