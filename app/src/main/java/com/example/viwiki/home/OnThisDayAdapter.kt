package com.example.viwiki.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.databinding.HomeCardBinding
import com.example.viwiki.home.ArticlesOfTheDayResponse.OnThisDay

/**
 * Adapter for Articles in home
 */
class OnThisDayAdapter :
    ListAdapter<OnThisDay, OnThisDayAdapter.ArticleViewHolder>(ArticleDiffCallBack()) {

    /**
     * Used by onCreateViewHolder
     */
    class ArticleViewHolder private constructor(val binding: HomeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the Page data
         * @param onThisDay The Page
         */
        fun bind(onThisDay: OnThisDay) {
            binding.onThisDay = onThisDay
        }

        companion object {
            /**
             * Creates a new viewHolder that holds the provided ViewGroup
             * @param viewGroup The viewGroup to hold
             */
            fun from(viewGroup: ViewGroup): ArticleViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val view = HomeCardBinding.inflate(
                    inflater,
                    viewGroup,
                    false
                )
                return ArticleViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
    }

    class ArticleDiffCallBack : DiffUtil.ItemCallback<OnThisDay>() {
        override fun areItemsTheSame(oldItem: OnThisDay, newItem: OnThisDay): Boolean {
            for (i in 0..oldItem.pages.size) {
                if (oldItem.pages[i] == newItem.pages[i]) {
                    return true
                }
            }
            return false
        }

        override fun areContentsTheSame(oldItem: OnThisDay, newItem: OnThisDay): Boolean {
            return oldItem == newItem
        }

    }
}