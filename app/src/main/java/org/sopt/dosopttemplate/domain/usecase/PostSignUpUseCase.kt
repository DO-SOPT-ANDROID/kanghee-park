package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class PostSignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userName: String, password: String, nickname: String) = authRepository.postSignUp(userName, password, nickname)
}