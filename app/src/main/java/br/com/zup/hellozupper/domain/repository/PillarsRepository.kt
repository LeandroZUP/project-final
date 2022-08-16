package br.com.zup.hellozupper.domain.repository

import br.com.zup.hellozupper.data.datasourse.remote.RetrofitService
import br.com.zup.hellozupper.data.model.PillarsResponse

class PillarsRepository {

    suspend fun getAllNetwork(): PillarsResponse {
        return RetrofitService.apiService.getPillars()
    }
}