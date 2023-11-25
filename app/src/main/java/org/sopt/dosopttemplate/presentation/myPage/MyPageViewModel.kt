package org.sopt.dosopttemplate.presentation.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.domain.model.User

class MyPageViewModel : ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    fun setUser(user: User) {
        _user.value = user
    }
}