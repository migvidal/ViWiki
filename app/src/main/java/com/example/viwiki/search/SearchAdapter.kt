package com.example.viwiki.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.viwiki.MainActivity
import com.example.viwiki.MainActivity.Companion.ARTICLE_TITLE_EXTRA_KEY
import com.example.viwiki.R

class SearchAdapter(val context: SearchActivity) : Adapter<SearchAdapter.SearchViewHolder>() {

    var dataSet = listOf(SearchResponse.SearchQuery.Search())
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    /**
     * Used by onCreateViewHolder
     */
    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvResult = view.findViewById<TextView>(R.id.tv_result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(
            R.layout.result_list_item,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val articleTitle = dataSet[position].title
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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


}