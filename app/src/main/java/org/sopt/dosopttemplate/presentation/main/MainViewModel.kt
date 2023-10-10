package org.sopt.dosopttemplate.presentation.main

import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo

class MainViewModel : ViewModel() {
    var user: UserInfo = UserInfo()

    fun setUserInfo(userInfo: UserInfo) {
        user = userInfo
    }
}