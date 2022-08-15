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
            val listFeedAPI = modifyReadStatus()

            val notReadNewsList = listFeedAPI.filterNot {
                it.read
            }

            ViewState.Success(notReadNewsList)
        } catch (e: Exception) {
            ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
        }
    }

    private suspend fun modifyReadStatus(): List<Feed> {
        val listFeedAPI = feedRepository.getAllNewsNetwork()
        val listIndexFeedDB = feedRepository.getAllReadNews().map { it.id }

        listFeedAPI.forEach {
            if (it.id in listIndexFeedDB) {
                it.read = true
            }
        }
        return listFeedAPI
    }

    suspend fun getSearchNews(query: String): ViewState<List<Feed>> {
        return try {
            val listFeedAPI = modifyReadStatus()
            val listSearchNews = listFeedAPI.filter {
                it.title.lowercase().contains(query.lowercase())
                        || it.sender.lowercase().contains(query.lowercase())
                        || it.content.lowercase().contains(query.lowercase())
            }
            ViewState.Success(listSearchNews)
        } catch (e: Exception) {
            ViewState.Error(Throwable(MESSAGE_FAIL_LOAD_NEWS_LIST))
        }
    }

    fun saveReadNewsIndex(news: Feed) {
        val feedDB = FeedEntity(news.id)
        feedRepository.saveReadNewsIndex(feedDB)
    }
}