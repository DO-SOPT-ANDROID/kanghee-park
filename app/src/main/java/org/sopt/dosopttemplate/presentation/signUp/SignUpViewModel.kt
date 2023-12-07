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
    private val _isPasswordMeetCriteria: MutableLiveData<Boolean> = MutableLiveData()
    val isPasswordMeetCriteria: LiveData<Boolean> = _isPasswordMeetCriteria
    private val _isMeetCriteria: MutableLiveData<Boolean> = MutableLiveData(false)
    val isMeetCriteria: LiveData<Boolean> = _isMeetCriteria
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

    fun updateIsMeetCriteria() {
        _isMeetCriteria.value = (isIdRegexMatch() && isPasswordRegexMatch())
    }

    fun isSignUpAvailable() =
        (isIdRegexMatch() && isPasswordRegexMatch() && isSignUpFormsBlank() && isMbtiInEnum())

    private fun isMbtiInEnum(): Boolean {
        return Mbti.values().any { it.name == mbti.value }
    }

    private fun isSignUpFormsBlank() = !(listOf(id.value, password.value, nickname.value, mbti.value).any { it.isNullOrBlank() })

    private fun isIdRegexMatch(): Boolean {
        return id.value?.let { ID_REGEX.matcher(it).find() } ?: false
    }

    fun isPasswordRegexMatch(): Boolean {
        val pwRegexMatch = password.value?.let { PASSWORD_REGEX.matcher(it).find() } ?: false
        _isPasswordMeetCriteria.value = pwRegexMatch
        return pwRegexMatch
    }

    companion object {
        private const val ID_PATTERN = """^[a-zA-Z0-9]{6,10}${'$'}"""
        private const val PASSWORD_PATTERN =
            """^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#${'$'}%^&*(),.?":{}|<>]).{6,12}${'$'}"""
        val ID_REGEX: Pattern = Pattern.compile(ID_PATTERN)
        val PASSWORD_REGEX: Pattern = Pattern.compile(PASSWORD_PATTERN)
    }
}