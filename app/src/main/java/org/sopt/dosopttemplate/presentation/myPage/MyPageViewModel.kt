package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo

class MyPageViewModel : ViewModel() {
    private val _user: MutableLiveData<UserInfo> = MutableLiveData()
    val user: LiveData<UserInfo> = _user

    fun setUserInfo(userInfo: UserInfo) {
        _user.value = userInfo
    }
}