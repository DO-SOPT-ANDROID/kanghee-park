package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.User

interface AuthRepository {
    suspend fun postSignUp(userName: String, password: String, nickname: String): Result<Unit>

    suspend fun postSignIn(userName: String, password: String): Result<User>
}