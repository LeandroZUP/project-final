package br.com.zup.hellozupper.data.model


import com.google.gson.annotations.SerializedName

data class PillarsResponse(
    @SerializedName("pillars")
    val pillars: List<Pillar>
)