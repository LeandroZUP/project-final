package br.com.zup.hellozupper.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Benefits(
    @SerializedName("descriptionFive")
    val descriptionFive: String,
    @SerializedName("descriptionFour")
    val descriptionFour: String,
    @SerializedName("descriptionOne")
    val descriptionOne: String,
    @SerializedName("descriptionSix")
    val descriptionSix: String,
    @SerializedName("descriptionThree")
    val descriptionThree: String,
    @SerializedName("descriptionTwo")
    val descriptionTwo: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
) : Parcelable