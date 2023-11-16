package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.InputStatus
import org.sopt.dosopttemplate.domain.model.User
import org.sopt.dosopttemplate.domain.usecase.PostSignInUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postSignInUseCase: PostSignInUseCase
) : ViewModel() {
    val id: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    private val _signInState: MutableLiveData<InputStatus> = MutableLiveData()
    val signInState: LiveData<InputStatus> = _signInState
    private val _loginUser: MutableLiveData<User> = MutableLiveData()
    val loginUser: LiveData<User> = _loginUser

    fun postSignIn() {
        viewModelScope.launch {
            postSignInUseCase(
                requireNotNull(id.value),
                requireNotNull(password.value)
            ).onSuccess { user ->
                _signInState.value = InputStatus.RIGHTINPUT
                _loginUser.value = user
            }
                .onFailure { throwable ->
                    _signInState.value = InputStatus.WRONGINPUT
                    Timber.e("$throwable")
                }
        }
    }

    fun changeSignInStateToEditing() {
        _signInState.value = InputStatus.EDITING
    }
}