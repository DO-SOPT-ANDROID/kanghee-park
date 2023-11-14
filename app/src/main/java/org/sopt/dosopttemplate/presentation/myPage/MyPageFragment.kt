package org.sopt.dosopttemplate.presentation.myPage

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.model.DefaultData
import org.sopt.dosopttemplate.domain.model.UserInfo
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        getBundleArgs()
    }

    private fun getBundleArgs() {
        arguments ?: return
        val id = createDefaultData(ARGS_ID)
        val password = createDefaultData(ARGS_PASSWORD)
        val nickname = createDefaultData(ARGS_NICKNAME)
        val mbti = createDefaultData(ARGS_MBTI)
        viewModel.setUserInfo(UserInfo(id.value, password.value, nickname.value, mbti.value))
    }

    private fun createDefaultData(key: String): DefaultData {
        return DefaultData(arguments?.getString(key))
    }

    companion object {
        private const val ARGS_ID = "ID"
        private const val ARGS_PASSWORD = "PASSWORD"
        private const val ARGS_NICKNAME = "NICKNAME"
        private const val ARGS_MBTI = "MBTI"

        @JvmStatic
        fun newInstance(id: String, password: String, nickname: String, mbti: String) =
            MyPageFragment().apply {
                val args = bundleOf(
                    ARGS_ID to id,
                    ARGS_PASSWORD to password,
                    ARGS_NICKNAME to nickname,
                    ARGS_MBTI to mbti,
                )
                arguments = args
            }
    }
}