package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.entity.request.SignInRequest
import org.sopt.dosopttemplate.data.entity.request.SignUpRequest
import org.sopt.dosopttemplate.domain.model.User
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
                SignUpRequest(
                    userName,
                    password,
                    nickname
                )
            )
        }

    override suspend fun postSignIn(userName: String, password: String): Result<User> =
        kotlin.runCatching {
            authDataSource.postSignIn(
                SignInRequest(
                    userName, password
                )
            )
        }.map { signInResponse -> signInResponse.toUser() }

}