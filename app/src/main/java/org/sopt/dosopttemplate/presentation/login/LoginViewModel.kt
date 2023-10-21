package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo

class LoginViewModel : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    lateinit var userInfo: UserInfo

    fun checkUserSignUp(): Int {
        return if (isUserSignUp()) {
            checkLoginCriteria()
        } else NOT_YET_SIGN_UP
    }

    private fun isUserSignUp() = ::userInfo.isInitialized

    private fun checkLoginCriteria() = if (isUserInfoCorrect()) MEET_CRITERIA
    else NOT_MEET_CRITERIA

    private fun isUserInfoCorrect() = id.value == userInfo.id && password.value == userInfo.password

    fun updateUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    companion object {
        const val MEET_CRITERIA = 0
        const val NOT_MEET_CRITERIA = 1
        const val NOT_YET_SIGN_UP = 2
    }
}