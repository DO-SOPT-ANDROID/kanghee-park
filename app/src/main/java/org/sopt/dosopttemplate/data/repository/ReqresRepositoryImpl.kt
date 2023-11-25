package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.ReqresDataSource
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Inject

class ReqresRepositoryImpl @Inject constructor(
    private val reqresDataSource: ReqresDataSource
) : ReqresRepository {
    override suspend fun getListUser(page: Int): Result<List<Profile>> =
        runCatching {
            reqresDataSource.getUserList(page)
        }.map { response -> requireNotNull(response.data).map { profileEntity -> profileEntity.toProfile() } }
}