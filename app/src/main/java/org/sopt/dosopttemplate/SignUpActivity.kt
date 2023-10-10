package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
