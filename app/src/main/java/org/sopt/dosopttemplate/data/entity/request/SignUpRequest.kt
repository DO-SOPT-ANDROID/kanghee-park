package org.sopt.dosopttemplate.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val username: String,
    val password: String,
    val nickname: String
)
