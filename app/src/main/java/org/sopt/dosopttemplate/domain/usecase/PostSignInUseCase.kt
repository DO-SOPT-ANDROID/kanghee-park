package org.sopt.dosopttemplate.domain.usecase

import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class PostSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userName: String, password: String) =
        authRepository.postSignIn(userName, password)
}