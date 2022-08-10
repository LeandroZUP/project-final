package br.com.zup.hellozupper.domain.usecase

import android.app.Application
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.domain.repository.FeedRepository

class FeedUsecase(application: Application) {
    private val feedRepository = FeedRepository(application)

    suspend fun getNewsNotRead(): List<Feed> {
        val listFeedAPI = feedRepository.getAllNewsNetwork()
        val listFeedDB = feedRepository.getAllReadNews().map { it.id }

        return listFeedAPI.filterNot {
            listFeedDB.contains(it.id)
        }
    }
}