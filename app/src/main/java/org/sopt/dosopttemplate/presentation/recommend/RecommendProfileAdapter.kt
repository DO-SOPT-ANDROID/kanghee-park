package org.sopt.dosopttemplate.presentation.recommend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.sopt.dosopttemplate.databinding.ItemRecemmendProfileBinding
import org.sopt.dosopttemplate.domain.model.Profile
import org.sopt.dosopttemplate.util.ItemDiffCallback

class RecommendProfileAdapter :
    ListAdapter<Profile, RecommendProfileAdapter.RecommendProfileViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendProfileViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemRecommendProfileBinding =
            ItemRecemmendProfileBinding.inflate(layoutInflater, parent, false)
        return RecommendProfileViewHolder(itemRecommendProfileBinding)
    }

    override fun onBindViewHolder(holder: RecommendProfileViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RecommendProfileViewHolder(private val binging: ItemRecemmendProfileBinding) :
        RecyclerView.ViewHolder(binging.root) {
        fun onBind(data: Profile) {
            binging.profile = data
        }
    }

    companion object {
        private val DIFF_CALLBACK = ItemDiffCallback<Profile>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}