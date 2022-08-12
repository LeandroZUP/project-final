package br.com.zup.hellozupper.ui.feed.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.databinding.ActivityFeedBinding
import br.com.zup.hellozupper.ui.feed.viewmodel.FeedViewModel
import br.com.zup.hellozupper.ui.viewstate.ViewState
import com.google.android.material.snackbar.Snackbar

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

    private fun showRecyclerView() {
        binding.rvFeed.adapter = adapter
        binding.rvFeed.layoutManager = LinearLayoutManager(this)
    }

    private fun observers() {
        viewModel.status.observe(this) {
            when (it) {
                is ViewState.Success -> adapter.updateFeedList(it.data)
                is ViewState.Error -> Snackbar.make(
                    binding.root,
                    "${it.throwable.message}",
                    Snackbar.LENGTH_SHORT
                ).show()
                else -> {}
            }
        }

        viewModel.loading.observe(this) {
            when (it) {
                is ViewState.Loading -> {
                    binding.pbLoading.isVisible = it.loading == true
                }
                else -> {}
            }
        }

        viewModel.readNews.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun saveReadNews(news: Feed) {
        viewModel.saveReadNews(news)
    }
}