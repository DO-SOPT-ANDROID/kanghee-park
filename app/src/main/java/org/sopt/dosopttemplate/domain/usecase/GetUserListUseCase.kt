package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Inject

class GetUserListUseCase @Inject constructor(
    private val reqresRepository: ReqresRepository
) {
    suspend operator fun invoke(page: Int) = reqresRepository.getListUser(page)
}