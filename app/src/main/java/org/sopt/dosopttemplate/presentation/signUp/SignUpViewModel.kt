package org.sopt.dosopttemplate.presentation.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.data.Mbti
import org.sopt.dosopttemplate.data.UserInfo
import java.util.regex.Pattern

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
        (isIdLengthSuitable() && isPassWordLengthSuitable() && isNickNameRegexMatch() && isMBTIRegexMatch() && isMbtiInEnum())

    private fun isMbtiInEnum(): Boolean {
        return Mbti.values().any { it.name == mbti.value }
    }

    private fun isSignUpFormsBlank() =
        listOf(id.value, password.value, nickname.value, mbti.value).any { it.isNullOrBlank() }

    fun createUser(): UserInfo =
        UserInfo(id.value!!, password.value!!, nickname.value!!, mbti.value!!)

    private fun isIdLengthSuitable() = id.value!!.length in 6..10
    private fun isPassWordLengthSuitable() = password.value!!.length in 8..12
    private fun isNickNameRegexMatch(): Boolean {
        return SIGNUP_REGEX.matcher(nickname.value!!).find()
    }

    private fun isMBTIRegexMatch(): Boolean {
        return SIGNUP_REGEX.matcher(mbti.value!!).find()
    }

    companion object {
        private const val SIGNUP_PATTERN = """^\S+$"""
        val SIGNUP_REGEX: Pattern = Pattern.compile(SIGNUP_PATTERN)
    }
}