package br.com.zup.hellozupper.data.datasourse.remote

import br.com.zup.hellozupper.data.model.FeedResponse
import br.com.zup.hellozupper.data.model.PillarsResponse
import retrofit2.http.GET

interface HelloZupperAPI {
    @GET("/pillars")
    suspend fun getPillars(): PillarsResponse

    @GET("/feed")
    suspend fun getFeed(): FeedResponse
}