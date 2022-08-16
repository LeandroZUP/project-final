package br.com.zup.hellozupper.data.model

import com.google.gson.annotations.SerializedName

data class Benefit(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)