package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.entity.request.SignInRequest
import org.sopt.dosopttemplate.data.entity.request.SignUpRequest
import org.sopt.dosopttemplate.data.entity.response.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun postSignUp(
        @Body body: SignUpRequest
    )

    @POST("api/v1/members/sign-in")
    suspend fun postSignIn(
        @Body body: SignInRequest
    ): SignInResponse
}