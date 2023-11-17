package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.entity.BaseResponse
import org.sopt.dosopttemplate.data.entity.ProfileEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ReqresService {
    @GET("users/{page}")
    suspend fun getUserList(
        @Path("page") page: Int
    ): BaseResponse<List<ProfileEntity>>
}