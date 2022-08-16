package br.com.zup.hellozupper.domain.repository

import br.com.zup.hellozupper.data.datasourse.remote.RetrofitService
import br.com.zup.hellozupper.data.model.BenefitsResponse

class BenefitsRepository {
    suspend fun getAllNetwork(): BenefitsResponse {
        return RetrofitService.apiService.getBenefits()
    }
}