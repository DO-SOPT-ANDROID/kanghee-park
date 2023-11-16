package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.entity.MemberRequest
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {
    override suspend fun postSignUp(
        userName: String,
        password: String,
        nickname: String
    ): Result<Unit> =
        kotlin.runCatching {
            authDataSource.postSignUp(
                MemberRequest(
                    userName,
                    password,
                    nickname
                )
            )
        }

}