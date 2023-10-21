package org.sopt.dosopttemplate.presentation.myPage

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.UserInfo
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()
    private var _id: String? = null
    private val id
        get() = _id ?: ""

    private var _password: String? = null
    private val password
        get() = _password ?: ""

    private var _nickname: String? = null
    private val nickname
        get() = _nickname ?: ""

    private var _mbti: String? = null
    private val mbti
        get() = _mbti ?: ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        getBundleArgs()
    }

    private fun getBundleArgs() {
        arguments ?: return
        _id = arguments?.getString(ARGS_ID)
        _nickname = arguments?.getString(ARGS_NICKNAME)
        _mbti = arguments?.getString(ARGS_MBTI)
        viewModel.setUserInfo(UserInfo(id, password, nickname, mbti))
    }

    companion object {
        private const val ARGS_ID = "ID"
        private const val ARGS_PASSWORD = "PASSWORd"
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