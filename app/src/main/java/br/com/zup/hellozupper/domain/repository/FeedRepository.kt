package br.com.zup.hellozupper.domain.repository

import android.app.Application
import br.com.zup.hellozupper.data.datasourse.local.HelloZupperDatabase
import br.com.zup.hellozupper.data.datasourse.remote.RetrofitService
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.data.model.FeedEntity

class FeedRepository(application: Application) {
    private val feedDao = HelloZupperDatabase.getDatabase(application).helloZupperDao()
    private val helloZupperAPI = RetrofitService.apiService

    fun saveReadNewsIndex(news: FeedEntity){
        feedDao.saveReadNewsIndex(news)
    }

    suspend fun getAllNewsNetwork(): List<Feed> {
        return helloZupperAPI.getFeed().feed
    }

    fun getAllReadNews(): List<FeedEntity> {
        return feedDao.getAllReadNews()
    }

    fun modifyReadNewsToNotRead(news: FeedEntity){
        feedDao.deleteNewsDB(news.id)
    }
}