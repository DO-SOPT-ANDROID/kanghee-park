package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extensions.getParcelable

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        getUserInfo()
    }

    private fun getUserInfo() {
        val userInfo = intent.getParcelable(USER_INFO, UserInfo::class.java)
        if (userInfo != null) {
            viewModel.setUserInfo(userInfo)
        }
    }
}