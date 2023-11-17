package org.sopt.dosopttemplate.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.model.Profile

@Serializable
data class ProfileEntity(
    val id: Int,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val avatar: String
) {
    fun toProfile(): Profile = Profile(
        id = this.id,
        email = this.email,
        firstName = this.firstName,
        lastName = this.lastName,
        avatar = this.avatar
    )
}
