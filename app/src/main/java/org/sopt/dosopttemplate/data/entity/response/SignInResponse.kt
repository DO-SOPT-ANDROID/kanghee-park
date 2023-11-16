package org.sopt.dosopttemplate.data.entity.response

import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.User

@Serializable
data class SignInResponse(
    val id: Int,
    val username: String,
    val nickname: String
) {
    fun toUser(): User = User(
        id = this.id.toString(),
        username = this.username,
        nickname = this.nickname
    )
}
