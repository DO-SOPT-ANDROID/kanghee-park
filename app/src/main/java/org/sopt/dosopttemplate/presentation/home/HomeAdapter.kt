package org.sopt.dosopttemplate.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.ProfileModel
import org.sopt.dosopttemplate.databinding.ItemFriendProfileBinding
import org.sopt.dosopttemplate.databinding.ItemMyProfileBinding
import org.sopt.dosopttemplate.util.ItemDiffCallback

class HomeAdapter: ListAdapter<ProfileModel, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            R.layout.item_my_profile -> {
                val itemMyProfileBinding =
                    ItemMyProfileBinding.inflate(layoutInflater, parent, false)

                MyProfileViewHolder(
                    itemMyProfileBinding
                )
            }

            else -> {
                val itemFriendProfileBinding =
                    ItemFriendProfileBinding.inflate(layoutInflater, parent, false)

                FriendProfileViewHolder(
                    itemFriendProfileBinding
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]

        when (holder) {
            is MyProfileViewHolder -> holder.onBind(item as ProfileModel.MyProfile)
            is FriendProfileViewHolder -> holder.onBind(
                item as ProfileModel.FriendProfile
            )
        }
    }

    override fun getItemCount(): Int = currentList.size

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is ProfileModel.MyProfile -> R.layout.item_my_profile
        is ProfileModel.FriendProfile -> R.layout.item_friend_profile
    }

    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<ProfileModel>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}