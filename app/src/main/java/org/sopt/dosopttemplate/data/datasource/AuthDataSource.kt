package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.request.SignUpRequest
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) {
    suspend fun postSignUp(memberRequest: SignUpRequest) = authService.postSignUp(memberRequest)
}