package org.sopt.dosopttemplate.presentation.signUp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo

class SignUpViewModel : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val nickname: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()

    fun checkSignUpTerms(): Boolean {
        if (id.value.isNullOrBlank() || password.value.isNullOrBlank() || nickname.value.isNullOrBlank() || mbti.value.isNullOrBlank()) return false
        return (isIdLengthSuitable() && isPassWordLengthSuitable() && isNickNameRegexMatch() && isMBTIRegexMatch())
    }

    fun showText() {
        Log.d(
            "kang",
            "id: ${id.value}, password: ${password.value}, nickname: ${nickname.value}, mbti: ${mbti.value}"
        )
    }

    fun createUser(): UserInfo =
        UserInfo(id.value!!, password.value!!, nickname.value!!, mbti.value!!)

    private fun isIdLengthSuitable() = id.value!!.length in 6..10
    private fun isPassWordLengthSuitable() = password.value!!.length in 8..12
    private fun isNickNameRegexMatch(): Boolean {
        val nicknameRegex = Regex("""^\S+$""")
        return nicknameRegex.matches(nickname.value!!)
    }

    private fun isMBTIRegexMatch(): Boolean {
        val mbtiRegex = Regex("""^\S+$""")
        return mbtiRegex.matches(mbti.value!!)
    }
}