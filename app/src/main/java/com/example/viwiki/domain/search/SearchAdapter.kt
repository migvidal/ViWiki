package com.example.viwiki.domain.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.databinding.ResultListItemBinding
import com.example.viwiki.domain.search.SearchResponse.SearchQuery.Search

/**
 * Adapter for the search RecyclerView
 */
class SearchAdapter :
    ListAdapter<Search, SearchAdapter.SearchViewHolder>(SearchDiffCallBack()) {

    /**
     * Used by onCreateViewHolder
     */
    class SearchViewHolder private constructor(val binding: ResultListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the searchResult data
         * @param searchResult The SearchResult
         */
        fun bind(searchResult: Search) {
            binding.searchResult = searchResult
        }

        companion object {
            /**
             * Creates a new viewHolder that holds the provided ViewGroup
             * @param viewGroup The viewGroup to hold
             */
            fun from(viewGroup: ViewGroup): SearchViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val view = ResultListItemBinding.inflate(
                    inflater,
                    viewGroup,
                    false
                )
                return SearchViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchResult = getItem(position)
        holder.bind(searchResult)
    }

    class SearchDiffCallBack : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.pageId == newItem.pageId
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

    }
}