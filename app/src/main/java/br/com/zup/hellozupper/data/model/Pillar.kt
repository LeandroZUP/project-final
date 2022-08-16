package br.com.zup.hellozupper.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pillar(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Image")
    val image: String,
    @SerializedName("title")
    val title: String
) : Parcelable