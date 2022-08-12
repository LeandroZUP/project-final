package br.com.zup.hellozupper.ui.feed.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.hellozupper.R
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

    private lateinit var searchView: SearchView
    private var listFeed = mutableListOf<Feed>()

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
                is ViewState.Success -> {
                    listFeed = it.data as MutableList<Feed>
                    adapter.updateFeedList(it.data)
                }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView

        searchView.isIconified = false

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchString = searchView.query.toString()
                val listSearchNews = mutableListOf<Feed>()
                listFeed.forEach {
                    if (it.content.contains(searchString)){
                        listSearchNews.add(it)
                        adapter.updateFeedList(listSearchNews)
                    }
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val listSearchNews = mutableListOf<Feed>()
                    listFeed.forEach {
                        val title = it.title.lowercase()
                        val content = it.content.lowercase()
                        if (content.contains(newText.lowercase()) || title.contains(newText.lowercase())){
                            listSearchNews.add(it)
                            adapter.updateFeedList(listSearchNews)
                        }
                    }
                }
                return true
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.search -> {
                viewModel.getAllReadNews()
                true
            }
            else -> {super.onOptionsItemSelected(item)}
        }
    }
}