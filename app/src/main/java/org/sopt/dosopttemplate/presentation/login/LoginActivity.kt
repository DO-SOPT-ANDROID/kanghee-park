package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.LOGIN_FAILED
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.LOGIN_SUCCEED
import org.sopt.dosopttemplate.presentation.login.LoginViewModel.Companion.NON_MEMBER
import org.sopt.dosopttemplate.presentation.main.MainActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER_INFO
import org.sopt.dosopttemplate.util.binding.BindingActivity

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
                val userInfo = getParcelable(data)
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
                makeToast(LOGIN_SUCCEED)
                goToMain()
            }

            LOGIN_FAILED -> snackBar(LOGIN_FAILED)
            NON_MEMBER -> snackBar(NON_MEMBER)
        }
    }

    private fun goToMain() {
        val intentToMain = Intent(this, MainActivity::class.java)
        intent.putExtra(USER_INFO, viewModel.userInfo)
        startActivity(intentToMain)
        finish()
    }

    private fun getParcelable(data: Intent) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data.getParcelableExtra(USER_INFO, UserInfo::class.java)
        } else {
            data.getParcelableExtra(USER_INFO)
        }

    private fun snackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT,
        ).show()
    }
    private fun makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }
}
