package org.sopt.dosopttemplate.data.entity.request

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val username: String,
    val password: String
)
