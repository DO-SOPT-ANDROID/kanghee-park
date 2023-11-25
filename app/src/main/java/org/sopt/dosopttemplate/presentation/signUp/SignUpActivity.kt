package org.sopt.dosopttemplate.presentation.signUp

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.snackBar
import org.sopt.dosopttemplate.util.toast

@AndroidEntryPoint
class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initOnClickListener()
        initSignUpStateObserver()
    }

    private fun initOnClickListener() {
        binding.btnSignUp.setOnClickListener {
            if (viewModel.isSignUpAvailable()) {
                viewModel.postSignUp()
            } else snackBar(binding.root, SIGN_UP_FAILED)
        }
        binding.root.setOnClickListener { view ->
            hideKeyboard(view)
        }
    }

    private fun initSignUpStateObserver() {
        viewModel.signUpState.observe(this) { success ->
            if (success) signUp()
            else toast(SIGN_UP_FAILED)
        }
    }

    private fun signUp() {
        toast(SIGN_UP_SUCCEED)
        finish()
    }

    companion object {
        private const val SIGN_UP_SUCCEED = "회원가입 성공"
        private const val SIGN_UP_FAILED = "회원가입 실패. 조건을 확인해주세요."
        const val USER = "user"
    }
}
