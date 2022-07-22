package com.example.viwiki.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.viwiki.R
import com.example.viwiki.databinding.ResultListItemBinding
import com.example.viwiki.utils.Logger

class SearchAdapter(
    private val context: SearchActivity,
    private var dataSet: List<SearchResponse.SearchQuery.Search>
) : Adapter<SearchAdapter.SearchViewHolder>() {

    /**
     * Used by onCreateViewHolder
     */
    inner class SearchViewHolder(val binding: ResultListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var searchResult = binding.searchResult
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        // TODO with data binding
        val binding = DataBindingUtil.inflate<ResultListItemBinding>(
            LayoutInflater.from(context), R.layout.result_list_item, parent, false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            searchResult = dataSet[position]
            holder.binding.executePendingBindings()
        }
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