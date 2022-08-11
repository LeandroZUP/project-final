package br.com.zup.hellozupper.ui.feed.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.databinding.ActivityFeedBinding
import br.com.zup.hellozupper.ui.feed.viewmodel.FeedViewModel

class FeedActivity : AppCompatActivity() {
    private val adapter: FeedAdapter by lazy {
        FeedAdapter(mutableListOf(), this::saveReadNews)
    }
    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this)[FeedViewModel::class.java]
    }
    private lateinit var binding: ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFeedBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotReadNews()
        showRecyclerView()
        observers()
    }

    private fun showRecyclerView(){
        binding.rvFeed.adapter = adapter
        binding.rvFeed.layoutManager = LinearLayoutManager(this)
    }

    private fun observers() {
        viewModel.status.observe(this){
            adapter.updateFeedList(it)
        }
    }

    private fun saveReadNews(news: Feed){
        viewModel.saveReadNews(news)
    }
}