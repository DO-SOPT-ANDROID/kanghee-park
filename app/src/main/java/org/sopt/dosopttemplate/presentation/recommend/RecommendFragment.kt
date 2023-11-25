package org.sopt.dosopttemplate.presentation.recommend

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentRecommendBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

@AndroidEntryPoint
class RecommendFragment : BindingFragment<FragmentRecommendBinding>(R.layout.fragment_recommend) {
    private val viewModel by viewModels<RecommendViewModel>()
    private var recommendProfileAdapter: RecommendProfileAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        initRecommendProfileAdapter()
        initProfileListObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        recommendProfileAdapter = null
    }

    private fun initRecommendProfileAdapter() {
        recommendProfileAdapter = RecommendProfileAdapter()
        binding.rvRecommendList.adapter = recommendProfileAdapter
    }

    private fun initProfileListObserver() {
        viewModel.profileList.observe(viewLifecycleOwner) {
            submitHomeProfileAdapterList()
        }
    }

    private fun submitHomeProfileAdapterList() {
        recommendProfileAdapter?.submitList(viewModel.profileList.value)
    }
}