package org.sopt.dosopttemplate.data.entity

import androidx.annotation.DrawableRes

sealed class ProfileModel {
    abstract val id: Int

    data class MyProfile(
        override val id: Int,
        val name: String,
        val description: String,
        @DrawableRes
        val profileResId: Int,
        val music: String
    ) : ProfileModel()

    data class FriendProfile(
        override val id: Int,
        val name: String,
        val description: String,
        @DrawableRes
        val profileResId: Int,
        val music: String,
        val heart: Boolean
    ) : ProfileModel()
}