package br.com.zup.hellozupper.domain.usecase

import br.com.zup.hellozupper.data.model.Benefits
import br.com.zup.hellozupper.domain.repository.BenefitsRepository
import br.com.zup.hellozupper.ui.viewstate.ViewState
import br.com.zup.hellozupper.utils.ERRO_API_BENEFITS

class BenefitsUseCase {
    private val benefitRepository = BenefitsRepository()

    suspend fun getAllProgramsNetwork(): ViewState<List<Benefits>> {
        return try {
            val benefitsZup = benefitRepository.getAllNetwork()
            ViewState.Success(benefitsZup.benefits)
        } catch (ex: Exception) {
            ViewState.Error(Exception(ERRO_API_BENEFITS))
        }
    }
}