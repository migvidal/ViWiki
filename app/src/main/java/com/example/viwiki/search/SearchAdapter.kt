package com.example.viwiki.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.viwiki.R
import com.example.viwiki.article_detail.ArticleFragment

class SearchAdapter(
    private val context: SearchActivity,
    private var dataSet: List<SearchResponse.SearchQuery.Search>
) : Adapter<SearchAdapter.SearchViewHolder>() {

    /**
     * Used by onCreateViewHolder
     */
    inner class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvResult = view.findViewById<TextView>(R.id.tv_result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.result_list_item,
            parent,
            false
        )

        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val resultTitle = dataSet[position].title
        holder.tvResult.text = resultTitle

        // onclick listener
        if (resultTitle !== null) {

            holder.view.setOnClickListener {
                val sharedPreferences = context.getPreferences(Context.MODE_PRIVATE)
                sharedPreferences.edit()
                    .putString(context.getString(R.string.saved_article_title_key), resultTitle)
                    .apply()
                val articleFragment = ArticleFragment.newInstance(resultTitle)
                val fragmentTransaction = context.supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.search_container, articleFragment)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateResults(dataSet: List<SearchResponse.SearchQuery.Search>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}