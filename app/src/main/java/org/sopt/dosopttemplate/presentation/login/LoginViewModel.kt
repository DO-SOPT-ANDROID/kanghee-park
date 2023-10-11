package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.presentation.login.LoginActivity.Companion.LOGIN_FAILED
import org.sopt.dosopttemplate.presentation.login.LoginActivity.Companion.LOGIN_SUCCEED
import org.sopt.dosopttemplate.presentation.login.LoginActivity.Companion.NON_MEMBER

class LoginViewModel : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    lateinit var userInfo: UserInfo

    fun checkSignUp(): String {
        return if (isSignUp()) {
            checkLogin()
        } else NON_MEMBER
    }

    private fun isSignUp() = ::userInfo.isInitialized

    private fun checkLogin() = if (isUserInfoCorrect()) LOGIN_SUCCEED
    else LOGIN_FAILED

    private fun isUserInfoCorrect() = id.value == userInfo.id && password.value == userInfo.password

    fun updateUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }
}