package org.sopt.dosopttemplate.presentation.signUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.presentation.login.LoginActivity
import org.sopt.dosopttemplate.util.binding.BindingActivity

class SignUpActivity : BindingActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {
    private val viewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initSignUpBtnClickListener()
    }

    private fun initSignUpBtnClickListener() {
        binding.btnSignUp.setOnClickListener {
            viewModel.showText()
            if (viewModel.checkSignUpTerms()) {
                createIntent()
                makeToast(SIGN_UP_SUCCEED)
                finish()
            }
            snackBar(SIGN_UP_FAILED)
        }
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

    private fun makeToast(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    private fun snackBar(text: String) {
        Snackbar.make(
            binding.root,
            text,
            Snackbar.LENGTH_SHORT,
        ).show()
    }

    companion object {
        private const val SIGN_UP_SUCCEED = "회원가입 성공"
        private const val SIGN_UP_FAILED = "회원가입 실패. 조건을 확인해주세요."
        const val USER_INFO = "userInfo"
    }
}
