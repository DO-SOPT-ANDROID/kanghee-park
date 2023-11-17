package org.sopt.dosopttemplate.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.domain.model.User
import org.sopt.dosopttemplate.presentation.doAndroid.DoAndroidFragment
import org.sopt.dosopttemplate.presentation.home.HomeFragment
import org.sopt.dosopttemplate.presentation.myPage.MyPageFragment
import org.sopt.dosopttemplate.presentation.recommend.RecommendFragment
import org.sopt.dosopttemplate.presentation.signUp.SignUpActivity
import org.sopt.dosopttemplate.util.binding.BindingActivity
import org.sopt.dosopttemplate.util.extensions.getParcelable

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
        getUser()
        clickBottomNavigation()
    }

    private fun getUser() {
        user = intent.getParcelable(SignUpActivity.USER, User::class.java)
            ?: throw IllegalArgumentException("missing user")
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_do_android -> {
                    replaceFragment(DoAndroidFragment())
                    true
                }

                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(
                        MyPageFragment.newInstance(
                            user.id,
                            user.username,
                            user.nickname
                        )
                    )
                    true
                }

                R.id.menu_recommend -> {
                    replaceFragment(RecommendFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }
}