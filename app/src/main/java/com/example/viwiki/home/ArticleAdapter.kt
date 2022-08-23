package com.example.viwiki.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.databinding.HomeCardBinding
import com.example.viwiki.home.ArticlesOfTheDayResponse.Article

/**
 * Adapter for Articles in home
 */
class ArticleAdapter :
    ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(ArticleDiffCallBack()) {

    /**
     * Used by onCreateViewHolder
     */
    class ArticleViewHolder private constructor(val binding: HomeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the Article data
         * @param article The Article
         */
        fun bind(article: Article) {
            binding.article = article
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

    class ArticleDiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.pageId == newItem.pageId
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
}