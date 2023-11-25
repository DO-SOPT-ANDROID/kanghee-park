package org.sopt.dosopttemplate.data.entity.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.User

@Serializable
data class SignInResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("username")
    val username: String,
    @SerialName("nickname")
    val nickname: String
) {
    fun toUser(): User = User(
        id = this.id.toString(),
        username = this.username,
        nickname = this.nickname
    )
}
