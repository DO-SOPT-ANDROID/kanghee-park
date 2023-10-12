package org.sopt.dosopttemplate.presentation.signUp

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.presentation.login.LoginActivity
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.snackBar
import org.sopt.dosopttemplate.util.toast

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initSignUpBtnClickListener()
    }

    private fun initSignUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            if (viewModel.checkSignUpTerms()) {
                signUp()
            } else snackBar(binding.root, SIGN_UP_FAILED)
        }
    }

    private fun signUp() {
        createIntent()
        toast(SIGN_UP_SUCCEED)
        finish()
    }

    private fun createIntent() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra(
                USER_INFO,
                viewModel.createUser(),
            )
        }
        setResult(RESULT_OK, intent)
    }

    companion object {
        private const val SIGN_UP_SUCCEED = "회원가입 성공"
        private const val SIGN_UP_FAILED = "회원가입 실패. 조건을 확인해주세요."
        const val USER_INFO = "userInfo"
    }
}
