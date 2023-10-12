package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extensions.getParcelable
import org.sopt.dosopttemplate.util.hideKeyboard
import org.sopt.dosopttemplate.util.snackBar
import org.sopt.dosopttemplate.util.toast

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initActivityLauncher()
        initonClickListener()
    }

    private fun initActivityLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != RESULT_OK) return@registerForActivityResult
                val data = result.data ?: return@registerForActivityResult
                val userInfo = data.getParcelable(USER_INFO, UserInfo::class.java)

                viewModel.updateUserInfo(userInfo!!)
            }
    }

    private fun initonClickListener() {
        binding.btnLoginLogin.setOnClickListener {
            login()
        }
        binding.btnLoginSignUp.setOnClickListener {
            moveToSignUp()
        }
        binding.root.setOnClickListener { view ->
            hideKeyboard(view)
        }
    }

    private fun moveToSignUp() {
        if (::resultLauncher.isInitialized) resultLauncher.launch(
            Intent(this, SignUpActivity::class.java)
        )
    }

    private fun login() {
        when (viewModel.checkSignUp()) {
            LOGIN_SUCCEED -> {
                toast(LOGIN_SUCCEED)
                moveToMain()
            }

            LOGIN_FAILED -> snackBar(binding.root, LOGIN_FAILED)
            NON_MEMBER -> snackBar(binding.root, NON_MEMBER)
        }
    }

    private fun moveToMain() {
        val intentToMain = Intent(this, MainActivity::class.java).apply {
            putExtra(USER_INFO, viewModel.userInfo)
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
