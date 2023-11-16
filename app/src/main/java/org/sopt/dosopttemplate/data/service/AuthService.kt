package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.entity.MemberRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members")
    suspend fun postSignUp(
        @Body body: MemberRequest
    )
}