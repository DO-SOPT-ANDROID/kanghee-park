package org.sopt.dosopttemplate.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class SupportEntity(
    val url: String,
    val text: String
)
