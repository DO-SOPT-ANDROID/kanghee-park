package org.sopt.dosopttemplate.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val username: String,
    val nickname: String
) : Parcelable
