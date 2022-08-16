package br.com.zup.hellozupper.domain.usecase

import br.com.zup.hellozupper.data.model.Pillar
import br.com.zup.hellozupper.domain.repository.PillarsRepository
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_PILLARS

class PillarsUseCase {
    private val pillarsRepository = PillarsRepository()

    suspend fun getAllPillarsNetwork(): ViewState<List<Pillar>> {
        return try {
            val pillarsZup = pillarsRepository.getAllNetwork()
            ViewState.Success(pillarsZup.pillars)
        } catch (ex: Exception) {
            ViewState.Error(Exception(ERRO_API_PILLARS))
        }
    }
}