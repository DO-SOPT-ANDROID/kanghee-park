package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.domain.model.InputStatus
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.toast

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initOnClickListener()
        initObserver()
    }

    private fun initOnClickListener() {
        with(binding) {
            btnLoginLogin.setOnClickListener {
                viewModel?.postSignIn()
            }
            btnLoginSignUp.setOnClickListener {
                moveToSignUp()
            }
            root.setOnClickListener { view ->
                hideKeyboard(view)
            }
        }
    }

    private fun moveToSignUp() {
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        startActivity(intentToSignUp)
    }

    private fun moveToMain() {
        val intentToMain = Intent(this, MainActivity::class.java).apply {
            putExtra(USER, viewModel.loginUser.value)
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intentToMain)
        finish()
    }

    private fun initObserver() {
        initLoginUserObserver()
        initLoginStateObserver()
        initFieldObserver()
    }

    private fun initLoginUserObserver() {
        viewModel.loginUser.observe(this) {
            if (viewModel.signInState.value == InputStatus.RIGHTINPUT) login()
            else toast(LOGIN_FAILED)
        }
    }

    private fun login() {
        toast(LOGIN_SUCCEED + viewModel.loginUser.value?.id.toString())
        moveToMain()
    }

    private fun initLoginStateObserver() {
        viewModel.signInState.observe(this) { success ->
            if (success == InputStatus.WRONGINPUT) toast(LOGIN_FAILED)
        }
    }

    private fun initFieldObserver() {
        viewModel.id.observe(this) {
            viewModel.changeSignInStateToEditing()
        }
        viewModel.password.observe(this) {
            viewModel.changeSignInStateToEditing()
        }
    }

    companion object {
        const val LOGIN_SUCCEED = "로그인 성공 회원님의 id는 "
        const val LOGIN_FAILED = "로그인 실패. 비밀번호와 아이디를 다시 확인해주세요."
    }
}
