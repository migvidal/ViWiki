package com.example.viwiki.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.viwiki.R
import com.example.viwiki.article_detail.ArticleFragment
import com.example.viwiki.utils.Logger

class SearchAdapter(val context: SearchActivity) : Adapter<SearchAdapter.SearchViewHolder>() {
    private val TAG = "SearchAdapter"

    var dataSet = listOf(SearchResponse.SearchQuery.Search())
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        Logger.logInfo(TAG, "getItemCount: " + dataSet.size)
        return dataSet.size
    }

    /**
     * Used by onCreateViewHolder
     */
    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvResult = view.findViewById<TextView>(R.id.tv_result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        Logger.logInfo(TAG, "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.result_list_item,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Logger.logInfo(TAG, "onBindViewHolder")
        val articleTitle = dataSet[position].title
        holder.tvResult.text = articleTitle

        // onclick listener
        holder.tvResult.setOnClickListener {
            if (articleTitle !== null) {
                // Create ArticleFragment and pass the article title
                val fragment = ArticleFragment.newInstance(articleTitle)
                // Show the fragment
                context.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_search, fragment)
                    .addToBackStack(null)
                    .commit()
            }

        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }




}