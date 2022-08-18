package br.com.zup.hellozupper.data.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(
    @SerializedName("feed")
    val feed: List<Feed>
)