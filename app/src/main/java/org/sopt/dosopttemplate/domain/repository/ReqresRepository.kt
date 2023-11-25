package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.Profile


interface ReqresRepository {
    suspend fun getListUser(page: Int): Result<List<Profile>>
}