package br.com.zup.hellozupper.domain.usecase

import br.com.zup.hellozupper.data.model.benefit.Benefit
import br.com.zup.hellozupper.domain.repository.BenefitRepository
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_BENEFIT

class BenefitUseCase {
    private val benefitRepository = BenefitRepository()

    suspend fun getAllProgramsNetwork(): ViewState<List<Benefit>> {
        return try {
            val benefitZup = benefitRepository.getAllNetwork()
            ViewState.Success(benefitZup.benefits)
        } catch (ex: Exception) {
            ViewState.Error(Exception(ERRO_API_BENEFIT))
        }
    }
}