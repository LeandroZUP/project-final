package br.com.zup.hellozupper.data.model

import com.google.gson.annotations.SerializedName

data class BenefitResponse(
@SerializedName("benefits")
val benefits: List<Benefit>
)
