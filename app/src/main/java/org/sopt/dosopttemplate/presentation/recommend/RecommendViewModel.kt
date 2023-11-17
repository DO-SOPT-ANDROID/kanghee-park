package org.sopt.dosopttemplate.presentation.recommend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.domain.usecase.GetUserListUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecommendViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {
    private val _profileList: MutableLiveData<List<Profile>> = MutableLiveData()
    val profileList: LiveData<List<Profile>> = _profileList

    init {
        getUserList()
    }

    private fun getUserList() {
        viewModelScope.launch {
            getUserListUseCase(2).onSuccess { profileList ->
                _profileList.value = profileList
            }.onFailure { throwable ->
                Timber.e("$throwable")
            }
        }
    }
}