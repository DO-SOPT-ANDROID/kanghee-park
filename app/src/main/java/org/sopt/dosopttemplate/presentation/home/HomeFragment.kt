package org.sopt.dosopttemplate.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.model.ProfileModel
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.binding.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private var homeProfileAdapter: HomeProfileAdapter? = null
    private val mocList: List<ProfileModel> = listOf(
        ProfileModel.MyProfile(1, "박강희", "안녕하세요, 강희입니다.", R.drawable.profile, "윤하-별의 조각"),
        ProfileModel.FriendProfile(2, "이삭", "toast", R.drawable.ic_android, "이삭-맛있다", false),
        ProfileModel.FriendProfile(3, "경지현", "제 꿈은 안드짱입니다", R.drawable.ic_android, "윤하-사건의 지평선", true),
        ProfileModel.FriendProfile(4, "조세연", "응애 나 막내", R.drawable.ic_android, "송이-말하는 감자 탈출", true),
        ProfileModel.FriendProfile(5, "박동민", "컴포즈 맡겨달라고.", R.drawable.ic_android, "compose-easy", false),
        ProfileModel.FriendProfile(6, "이태희", "웃으세요", R.drawable.ic_android, "부장님-그만해요", false),
        ProfileModel.FriendProfile(7, "전채연", "나랑 탁구 칠 사람", R.drawable.ic_android, "하현상-파도", true),
        ProfileModel.FriendProfile(8, "최준서", "타로 봐드립니다", R.drawable.ic_android, "저스디스-indigo", false),
        ProfileModel.FriendProfile(9, "서재원", "지구에서 한아뿐", R.drawable.ic_android, "정세랑-최고", false),
        ProfileModel.FriendProfile(10, "박소현", "도시락 줄 서세요", R.drawable.ic_android, "최유리-숲", true)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        initHomeProfileAdapter()
        submitHomeProfileAdapterList()
    }

    override fun onDestroy() {
        super.onDestroy()
        homeProfileAdapter = null
    }

    private fun initHomeProfileAdapter() {
        homeProfileAdapter = HomeProfileAdapter()
        binding.rvProfileList.adapter = homeProfileAdapter
    }

    private fun submitHomeProfileAdapterList() {
        homeProfileAdapter?.submitList(mocList)
    }
}