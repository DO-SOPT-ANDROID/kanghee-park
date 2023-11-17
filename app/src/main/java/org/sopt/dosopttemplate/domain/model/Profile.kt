package org.sopt.dosopttemplate.domain.model

data class Profile(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)
