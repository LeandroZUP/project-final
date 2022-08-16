package br.com.zup.hellozupper.domain.repository

import br.com.zup.hellozupper.data.datasourse.remote.RetrofitService
import br.com.zup.hellozupper.data.model.benefit.BenefitResponse

class BenefitRepository {
    suspend fun getAllNetwork(): BenefitResponse {
        return RetrofitService.apiService.getBenefit()
    }
}