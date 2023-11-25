package org.sopt.dosopttemplate.presentation.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.Mbti
import org.sopt.dosopttemplate.domain.usecase.PostSignUpUseCase
import timber.log.Timber
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignUpUseCase: PostSignUpUseCase
) : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val nickname: MutableLiveData<String> = MutableLiveData()
    val mbti: MutableLiveData<String> = MutableLiveData()
    private val _signUpState = MutableLiveData<Boolean>()
    val signUpState: LiveData<Boolean> = _signUpState

    fun postSignUp() {
        viewModelScope.launch {
            postSignUpUseCase(
                requireNotNull(id.value),
                requireNotNull(password.value),
                requireNotNull(nickname.value)
            )
                .onSuccess {
                    _signUpState.value = true
                }
                .onFailure { throwable ->
                    _signUpState.value = false
                    Timber.e("$throwable")
                }
        }
    }

    fun isSignUpAvailable(): Boolean {
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

    private fun isIdLengthSuitable() = id.value?.length in 6..10
    private fun isPassWordLengthSuitable() = password.value?.length in 8..12
    private fun isNickNameRegexMatch(): Boolean {
        return nickname.value?.let { SIGNUP_REGEX.matcher(it).find() } ?: false
    }

    private fun isMBTIRegexMatch(): Boolean {
        return mbti.value?.let { SIGNUP_REGEX.matcher(it).find() } ?: false
    }

    companion object {
        private const val SIGNUP_PATTERN = """^\S+$"""
        val SIGNUP_REGEX: Pattern = Pattern.compile(SIGNUP_PATTERN)
    }
}