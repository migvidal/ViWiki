package com.example.viwiki.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.MainActivity
import com.example.viwiki.MainActivity.Companion.ARTICLE_TITLE_EXTRA_KEY
import com.example.viwiki.databinding.ResultListItemBinding
import com.example.viwiki.search.SearchResponse.SearchQuery.Search

/**
 * Adapter for the search RecyclerView
 */
class SearchAdapter(private val context: SearchActivity) :
    ListAdapter<Search, SearchAdapter.SearchViewHolder>(SearchDiffCallBack()) {

    /**
     * Used by onCreateViewHolder
     */
    class SearchViewHolder private constructor(val binding: ResultListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the article data and the click listener to the viewHolder
         * @param articleTitle The article title
         * @param context The context for the click listener's intent
         */
        // TODO REFACTOR: DIVIDE IN bind and setclicklistener
        fun bind(articleTitle: String?, context: SearchActivity) {
            binding.tvResult.text = articleTitle

            // onclick listener
            binding.tvResult.setOnClickListener {
                if (articleTitle !== null) {
                    // Create intent
                    val intent = Intent(context, MainActivity::class.java)
                    intent.putExtra(ARTICLE_TITLE_EXTRA_KEY, articleTitle)
                    context.startActivity(intent)
                }
            }
        }

        companion object {
            /**
             * Creates a new viewHolder that holds the provided ViewGroup
             * @param viewGroup The viewGroup to hold
             */
            fun from(viewGroup: ViewGroup): SearchViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val view = ResultListItemBinding.inflate(inflater,
                    viewGroup,
                    false)
                return SearchViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val articleTitle = getItem(position).title
        holder.bind(articleTitle, context)
    }



    class SearchDiffCallBack : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

    }



}