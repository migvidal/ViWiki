package com.example.viwiki.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.MainActivity
import com.example.viwiki.MainActivity.Companion.ARTICLE_TITLE_EXTRA_KEY
import com.example.viwiki.R
import com.example.viwiki.search.SearchResponse.SearchQuery.Search

/**
 * Adapter for the search RecyclerView
 */
class SearchAdapter(private val context: SearchActivity) :
    ListAdapter<Search, SearchAdapter.SearchViewHolder>(SearchDiffCallBack()) {

    /**
     * Used by onCreateViewHolder
     */
    class SearchViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {
        val tvResult: TextView = view.findViewById(R.id.tv_result)
        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(
                    R.layout.result_list_item,
                    parent,
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
        val articleTitle = getItem(position).title
        holder.tvResult.text = articleTitle

        // onclick listener
        holder.tvResult.setOnClickListener {
            if (articleTitle !== null) {
                // Create intent
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(ARTICLE_TITLE_EXTRA_KEY, articleTitle)
                context.startActivity(intent)
            }
        }
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