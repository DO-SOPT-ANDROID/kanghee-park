package org.sopt.dosopttemplate.presentation.home

import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.data.ProfileModel
import org.sopt.dosopttemplate.databinding.ItemFriendProfileBinding

class FriendProfileViewHolder (private val binding: ItemFriendProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(myProfile: ProfileModel.FriendProfile) {
        binding.data = myProfile
    }
}