package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.entity.BaseResponse
import org.sopt.dosopttemplate.data.entity.ProfileEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ReqresService {
    @GET("users")
    suspend fun getUserList(
        @Query("page") page: Int
    ): BaseResponse<List<ProfileEntity>>
}