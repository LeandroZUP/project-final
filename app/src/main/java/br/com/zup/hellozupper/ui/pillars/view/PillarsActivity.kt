package br.com.zup.hellozupper.ui.pillars.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.hellozupper.databinding.ActivityPillarsBinding

class PillarsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPillarsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPillarsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}