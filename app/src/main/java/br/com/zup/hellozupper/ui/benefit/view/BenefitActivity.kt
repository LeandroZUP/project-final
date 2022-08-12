package br.com.zup.hellozupper.ui.benefit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.hellozupper.databinding.ActivityBenefitBinding

class BenefitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBenefitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBenefitBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}