package br.com.zup.hellozupper.domain.repository

import br.com.zup.hellozupper.data.datasourse.remote.RetrofitService
import br.com.zup.hellozupper.data.model.ProgramsResponse

class ProgramsRepository {
    suspend fun getAllNetwork(): ProgramsResponse {
        return RetrofitService.apiService.getProgram()
    }
}