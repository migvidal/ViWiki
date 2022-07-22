package com.example.viwiki.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.viwiki.R
import com.example.viwiki.utils.Logger

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
        // TODO with data binding
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.result_list_item,
            parent,
            false
        )
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvResult.text = dataSet[position].title
        // onclick listener...
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun updateResults(dataSet: List<SearchResponse.SearchQuery.Search>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}