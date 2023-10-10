package org.sopt.dosopttemplate.presentation.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity.Companion.USER_INFO

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActivityLauncher()
        initBtnClickListener()
    }

    private fun initActivityLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != RESULT_OK) return@registerForActivityResult
                val data = result.data ?: return@registerForActivityResult
                val userInfo = getParcelable(data)
            }
    }

    private fun initBtnClickListener() {
        binding.btnLoginLogin.setOnClickListener {
//            TODO("회원가입 확인")
//            TODO("아이디, 비밀번호 맞는지 확인")
//            TODO("마이페이지로 넘어가기")
        }
        binding.btnLoginSignUp.setOnClickListener {
            if (::resultLauncher.isInitialized) resultLauncher.launch(
                Intent(this, SignUpActivity::class.java)
            )
        }
    }

    private fun getParcelable(data: Intent) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data.getParcelableExtra(USER_INFO, UserInfo::class.java)
        } else {
            data.getParcelableExtra(USER_INFO)
        }
}
