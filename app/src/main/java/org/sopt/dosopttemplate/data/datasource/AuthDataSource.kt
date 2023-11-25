package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.request.SignInRequest
import org.sopt.dosopttemplate.data.entity.request.SignUpRequest
import org.sopt.dosopttemplate.data.entity.response.SignInResponse
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun postSignUp(signUpRequest: SignUpRequest) = authService.postSignUp(signUpRequest)

    suspend fun postSignIn(signInRequest: SignInRequest): SignInResponse =
        authService.postSignIn(signInRequest)
}