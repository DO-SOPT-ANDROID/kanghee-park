package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.entity.BaseResponse
import org.sopt.dosopttemplate.data.entity.ProfileEntity
import org.sopt.dosopttemplate.data.service.ReqresService
import javax.inject.Inject

class ReqresDataSource @Inject constructor(
    private val reqresService: ReqresService
) {
    suspend fun getUserList(page: Int): BaseResponse<List<ProfileEntity>> =
        reqresService.getUserList(page)
}