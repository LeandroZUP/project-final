package br.com.zup.hellozupper.data.model

import com.google.gson.annotations.SerializedName

data class BenefitsResponse(
    @SerializedName("benefits")
    val benefits: List<Benefits>
)