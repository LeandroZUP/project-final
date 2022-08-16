package br.com.zup.hellozupper.ui.feed.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.R
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.data.model.FeedEntity
import br.com.zup.hellozupper.databinding.ActivityFeedBinding
import br.com.zup.hellozupper.ui.feed.viewmodel.FeedViewModel
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.MESSAGE_EMPTY_NEWS_LIST
import com.google.android.material.snackbar.Snackbar

class FeedActivity : AppCompatActivity() {
    private val adapter: FeedAdapter by lazy {
        FeedAdapter(saveReadNews = this::saveReadNews, modifyReadNewsToNotRead = this::modifyReadNewsToNotRead)
    }
    private val viewModel: FeedViewModel by lazy {
        ViewModelProvider(this)[FeedViewModel::class.java]
    }

    private lateinit var searchView: SearchView

    private lateinit var binding: ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFeedBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getNotReadNews()
        showRecyclerView()
        observers()
    }

    private fun showRecyclerView() {
        binding.rvFeed.adapter = adapter
        binding.rvFeed.layoutManager = LinearLayoutManager(this)
    }

    private fun observers() {
        viewModel.state.observe(this) {
            when (it) {
                is ViewState.Success -> {
                    if (it.data.isEmpty()){
                        binding.tvEmptyFeed.text = MESSAGE_EMPTY_NEWS_LIST
                        binding.tvEmptyFeed.isVisible = true
                    }else{
                        binding.tvEmptyFeed.isVisible = false
                    }
                    adapter.updateFeedList(it.data)
                }
                is ViewState.Error -> {
                    binding.tvEmptyFeed.text = it.throwable.message
                    binding.tvEmptyFeed.isVisible = true
                }
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

    private fun modifyReadNewsToNotRead(news: FeedEntity){
        viewModel.modifyReadNewsToNotRead(news)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView

        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchString = searchView.query.toString()
                viewModel.getSearchNews(searchString)
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.getSearchNews(newText)
                }
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.search -> {
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }
}