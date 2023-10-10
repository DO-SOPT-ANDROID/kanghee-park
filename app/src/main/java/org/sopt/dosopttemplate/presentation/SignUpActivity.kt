package org.sopt.dosopttemplate.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()
    }

    private fun signUp() {
        binding.btnSignUp.setOnClickListener {
            if (isIdLengthSuitable() && isPassWordLengthSuitable() && isNickNameRegexMatch() && isMBTIRegexMatch()) {
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
                createUser(),
            )
        }
        setResult(RESULT_OK, intent)
    }

    private fun createUser() = UserInfo(
        with(binding) {
            etSignUpId.text.toString()
            etSignUpPassword.text.toString()
            etSignUpNickname.text.toString()
            etSignUpMbti.text.toString()
        }
    )

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

    private fun isIdLengthSuitable() = binding.etSignUpId.text.length in 6..10
    private fun isPassWordLengthSuitable() = binding.etSignUpPassword.text.length in 8..12
    private fun isNickNameRegexMatch(): Boolean {
        val nicknameRegex = Regex("""^\S+$""")
        return nicknameRegex.matches(binding.etSignUpNickname.text)
    }

    private fun isMBTIRegexMatch(): Boolean {
        val mbtiRegex = Regex("""^\S+$""")
        return mbtiRegex.matches(binding.etSignUpMbti.text)
    }

    companion object {
        private const val SIGN_UP_SUCCEED = "회원가입 성공"
        private const val SIGN_UP_FAILED = "회원가입 실패. 조건을 확인해주세요."
        private const val USER_INFO = "userInfo"
    }
}
