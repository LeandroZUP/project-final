package br.com.zup.hellozupper.data.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("id")
    val id: Int,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String,
)