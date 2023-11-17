package org.sopt.dosopttemplate.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val page: Int,
    @SerialName("per_page")
    val perPage: Int,
    val total: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val data: T? = null
)
