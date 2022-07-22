package com.example.viwiki.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.viwiki.MainActivity
import com.example.viwiki.MainActivity.Companion.ARTICLE_NAME
import com.example.viwiki.R

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
        holder.view.setOnClickListener {
            val fragmentTransaction = context.supportFragmentManager
            /*fragmentTransaction.beginTransaction()
                .add(R.id.articleFragment, ArticleFragment())
                .commit()*/

            val intent = Intent(context, MainActivity::class.java)
                .putExtra(ARTICLE_NAME, resultTitle)
            context.startActivity(intent)
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