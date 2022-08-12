package br.com.zup.hellozupper.ui.program.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.hellozupper.databinding.ActivityProgramBinding

class ProgramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProgramBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgramBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}