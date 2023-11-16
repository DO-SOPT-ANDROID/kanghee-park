package org.sopt.dosopttemplate.presentation.myPage

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.model.DefaultData
import org.sopt.dosopttemplate.domain.model.User
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
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
        val username = createDefaultData(ARGS_USERNAME)
        val nickname = createDefaultData(ARGS_NICKNAME)
        viewModel.setUser(User(id.value.toInt(), username.value, nickname.value))
    }

    private fun createDefaultData(key: String): DefaultData {
        return DefaultData(arguments?.getString(key))
    }

    companion object {
        private const val ARGS_ID = "ID"
        private const val ARGS_USERNAME = "USERNAME"
        private const val ARGS_NICKNAME = "NICKNAME"

        @JvmStatic
        fun newInstance(id: Int, username: String, nickname: String) =
            MyPageFragment().apply {
                val args = bundleOf(
                    ARGS_ID to id,
                    ARGS_USERNAME to username,
                    ARGS_NICKNAME to nickname
                )
                arguments = args
            }
    }
}