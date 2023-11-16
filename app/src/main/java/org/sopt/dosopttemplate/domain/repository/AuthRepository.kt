package org.sopt.dosopttemplate.domain.repository

interface AuthRepository {
    suspend fun postSignUp(userName: String, password: String, nickname: String): Result<Unit>
}