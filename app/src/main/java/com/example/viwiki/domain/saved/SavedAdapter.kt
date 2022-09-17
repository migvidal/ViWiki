package com.example.viwiki.domain.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.databinding.FragmentSavedListItemBinding
import com.example.viwiki.domain.page.Page

class SavedAdapter(val onClickListener: ListItemClickListener) :
    ListAdapter<Page, SavedAdapter.SavedViewHolder>(SavedDiffCallback()) {

    class SavedViewHolder private constructor(val binding: FragmentSavedListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(page: Page) {
            binding.page = page
        }

        companion object {
            fun from(viewGroup: ViewGroup): SavedViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val binding = FragmentSavedListItemBinding.inflate(
                    inflater,
                    viewGroup,
                    false
                )
                return SavedViewHolder(binding)
            }
        }
    }

    class SavedDiffCallback : DiffUtil.ItemCallback<Page>() {
        override fun areItemsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem.pageId == newItem.pageId
        }

        override fun areContentsTheSame(oldItem: Page, newItem: Page): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedViewHolder {
        return SavedViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SavedViewHolder, position: Int) {
        val page = getItem(position)
        holder.bind(page)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(page.title)
        }
    }
}