package br.com.zup.hellozupper.data.model


import com.google.gson.annotations.SerializedName

data class Pillar(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Image")
    val image: String,
    @SerializedName("title")
    val title: String
)