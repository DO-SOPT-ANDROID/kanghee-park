package org.sopt.dosopttemplate.presentation.home

import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.domain.model.ProfileModel
import org.sopt.dosopttemplate.databinding.ItemFriendProfileBinding

class FriendProfileViewHolder(private val binding: ItemFriendProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(myProfile: ProfileModel.FriendProfile) {
        binding.data = myProfile
        binding.ivContextMenu.setOnClickListener { view -> showContextMenu(view) }
    }

    private fun showContextMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val inflater: MenuInflater = popupMenu.menuInflater
        inflater.inflate(R.menu.profile_menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_add_bookmark -> changeBookmarkStateTo(true)
                R.id.menu_remove_bookmark -> changeBookmarkStateTo(false)
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun changeBookmarkStateTo(bookmarkState: Boolean): Boolean {
        val user = binding.data?.copy(heart = bookmarkState)
        binding.data = user
        return true
    }
}