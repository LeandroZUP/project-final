package br.com.zup.hellozupper.domain.usecase

import android.app.Application
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.data.model.FeedEntity
import br.com.zup.hellozupper.domain.repository.FeedRepository
import br.com.zup.hellozupper.ui.MESSAGE_FAIL_LOAD_NEWS_LIST
import br.com.zup.hellozupper.ui.viewstate.ViewState
import java.lang.Exception

class FeedUseCase(application: Application) {
    private val feedRepository = FeedRepository(application)

    suspend fun getNewsNotRead(): ViewState<List<Feed>> {
        return try {
            val listFeedAPI = feedRepository.getAllNewsNetwork()
            val listFeedDB = feedRepository.getAllReadNews().map { it.id }

            val notReadNewsList =  listFeedAPI.filterNot {
                listFeedDB.contains(it.id)
            }

            ViewState.Success(notReadNewsList)
        }catch (e: Exception) {
            ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
        }
    }

    suspend fun getSearchNews(query: String): ViewState<List<Feed>> {
        return try {
            val listFeedAPI = feedRepository.getAllNewsNetwork()
            val listSearchNews = mutableListOf<Feed>()
            listFeedAPI.forEach {
                if (it.title.lowercase().contains(query.lowercase()) || it.sender.lowercase().contains(query.lowercase()) || it.content.lowercase().contains(query.lowercase())){
                    listSearchNews.add(it)
                }
            }
            ViewState.Success(listSearchNews)
        }catch (e: Exception) {
            ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
        }
    }

    fun saveReadNewsIndex(news: Feed) {
        val feedDB = FeedEntity(news.id)
        feedRepository.saveReadNewsIndex(feedDB)
    }
}