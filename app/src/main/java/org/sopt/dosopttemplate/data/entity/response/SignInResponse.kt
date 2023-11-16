package org.sopt.dosopttemplate.data.entity.response

import org.sopt.dosopttemplate.domain.model.User

data class SignInResponse(
    val id: Int,
    val username: String,
    val nickname: String
) {
    fun toUser(): User = User(
        id = this.id,
        username = this.username,
        nickname = this.nickname
    )
}
