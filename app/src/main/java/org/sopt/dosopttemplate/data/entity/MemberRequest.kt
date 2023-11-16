package org.sopt.dosopttemplate.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class MemberRequest(
    val username: String,
    val password: String,
    val nickname: String
)
