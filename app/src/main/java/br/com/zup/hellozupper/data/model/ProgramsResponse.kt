package br.com.zup.hellozupper.data.model

import com.google.gson.annotations.SerializedName

data class ProgramsResponse(
    @SerializedName("program")
    val programs: List<Programs>
)