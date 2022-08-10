package br.com.zup.hellozupper.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.hellozupper.R
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import br.com.zup.hellozupper.MainViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotReadNews()

        viewModel.status.observe(this){
            val texto = findViewById<TextView>(R.id.tvTeste)
            texto.text = it.toString()
        }
    }
}