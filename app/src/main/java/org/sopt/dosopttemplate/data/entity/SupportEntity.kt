package org.sopt.dosopttemplate.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportEntity(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String
)
