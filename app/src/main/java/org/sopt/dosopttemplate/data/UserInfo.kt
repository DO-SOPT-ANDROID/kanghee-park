package org.sopt.dosopttemplate.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    var id: String = "",
    val password: String = "",
    val nickname: String = "",
    val mbti: String = "",
) : Parcelable
