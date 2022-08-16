package br.com.zup.hellozupper.ui.detailpillars.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.hellozupper.databinding.ActivityDetailPillarsBinding

class DetailPillarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPillarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPillarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}