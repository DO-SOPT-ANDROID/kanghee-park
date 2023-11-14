package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.entity.ProfileModel
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding

class MyProfileViewHolder (private val binding: ItemMyProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(myProfile: ProfileModel.MyProfile) {
        binding.data = myProfile
    }
}