package org.sopt.dosopttemplate.presentation.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.UserInfo

class SignUpViewModel : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val nickname: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()

    fun checkSignUpTerms(): Boolean {
        if (isSignUpFormsBlank()) return false
        return isSignUpFormsMatch()
    }

    private fun isSignUpFormsMatch() =
        (isIdLengthSuitable() && isPassWordLengthSuitable() && isNickNameRegexMatch() && isMBTIRegexMatch())

    private fun isSignUpFormsBlank() =
        (id.value.isNullOrBlank() || password.value.isNullOrBlank() || nickname.value.isNullOrBlank() || mbti.value.isNullOrBlank())

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