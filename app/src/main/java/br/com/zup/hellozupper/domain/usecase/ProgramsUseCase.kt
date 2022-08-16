package br.com.zup.hellozupper.domain.usecase

import br.com.zup.hellozupper.data.model.Programs
import br.com.zup.hellozupper.domain.repository.ProgramsRepository
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_PROGRAMS

class ProgramsUseCase {
    private val programsRepository = ProgramsRepository()

    suspend fun getAllProgramsNetwork(): ViewState<List<Programs>> {
        return try {
            val programsZup = programsRepository.getAllNetwork()
            ViewState.Success(programsZup.programs)
        } catch (ex: Exception) {
            ViewState.Error(Exception(ERRO_API_PROGRAMS))
        }
    }
}