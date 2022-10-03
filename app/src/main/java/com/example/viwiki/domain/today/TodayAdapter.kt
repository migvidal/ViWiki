package com.example.viwiki.domain.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.databinding.TodayCardBinding
import com.example.viwiki.domain.today.TodayResponse.OnThisDay

/**
 * Adapter for Articles in home
 */
class TodayAdapter :
    ListAdapter<OnThisDay, TodayAdapter.ArticleViewHolder>(ArticleDiffCallBack()) {

    /**
     * Used by onCreateViewHolder
     */
    class ArticleViewHolder private constructor(val binding: TodayCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the DatabasePage data
         * @param onThisDay The DatabasePage
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
                val view = TodayCardBinding.inflate(
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