package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.MEET_CRITERIA
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.NOT_MEET_CRITERIA
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.NOT_YET_SIGN_UP
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.snackBar
import org.sopt.dosopttemplate.util.toast

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initOnClickListener()
    }

    private fun initOnClickListener() {
        with(binding) {
            btnLoginLogin.setOnClickListener {
                login()
            }
            btnLoginSignUp.setOnClickListener {
                moveToSignUp()
            }
            root.setOnClickListener { view ->
                hideKeyboard(view)
            }
        }
    }

    private fun login() {
        when (viewModel.checkUserSignUp()) {
            MEET_CRITERIA -> {
                toast(LOGIN_SUCCEED)
                moveToMain()
            }

            NOT_MEET_CRITERIA -> snackBar(binding.root, LOGIN_FAILED)
            NOT_YET_SIGN_UP -> snackBar(binding.root, NON_MEMBER)
        }
    }

    private fun moveToSignUp() {
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        startActivity(intentToSignUp)
    }

    private fun moveToMain() {
        val intentToMain = Intent(this, MainActivity::class.java).apply {
            putExtra(USER, viewModel.user)
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intentToMain)
        finish()
    }

    companion object {
        const val LOGIN_SUCCEED = "로그인 성공"
        const val LOGIN_FAILED = "로그인 실패. 비밀번호와 아이디를 다시 확인해주세요."
        const val NON_MEMBER = "회원이 아닙니다. 회원가입을 진행해주세요."
    }
}
