package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo

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

    private fun isUserInfoCorrect() = id.value.equals(userInfo.id) && password.value.equals(userInfo.password)

    fun updateUserInfo(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    companion object {
        const val LOGIN_SUCCEED = "로그인 성공"
        const val LOGIN_FAILED = "로그인 실패. 비밀번호와 아이디를 다시 확인해주세요."
        const val NON_MEMBER = "회원이 아닙니다. 회원가입을 진행해주세요."
    }
}