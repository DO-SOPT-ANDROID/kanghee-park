package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.LOGIN_FAILED
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.LOGIN_SUCCEED
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.NON_MEMBER
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.util.ShowSnackBar.showSnackBar
import org.sopt.dosopttemplate.util.ToastMessageUtil.showToast
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extensions.getParcelable

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initActivityLauncher()
        initBtnClickListener()
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

    private fun initBtnClickListener() {
        binding.btnLoginLogin.setOnClickListener {
            login()
        }
        binding.btnLoginSignUp.setOnClickListener {
            goToSignUp()
        }
    }

    private fun goToSignUp() {
        if (::resultLauncher.isInitialized) resultLauncher.launch(
            Intent(this, SignUpActivity::class.java)
        )
    }

    private fun login() {
        when (viewModel.checkSignUp()) {
            LOGIN_SUCCEED -> {
                showToast(applicationContext, LOGIN_SUCCEED)
                moveToMain()
            }

            LOGIN_FAILED -> showSnackBar(binding.root, LOGIN_FAILED)
            NON_MEMBER -> showSnackBar(binding.root, NON_MEMBER)
        }
    }

    private fun moveToMain() {
        val intentToMain = Intent(this, MainActivity::class.java).apply {
            putExtra(USER_INFO, viewModel.userInfo)
        }
        startActivity(intentToMain)
        finish()
    }
}
