package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.viwiki.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    val viewModel: SearchViewModel by viewModels()
    val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }
    lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Verify intent
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewModel.searchResponse.observe(this, Observer { response ->
                    if (response.query !== null) {
                        setupRecyclerView(response.query.search)
                    }
                })
                viewModel.searchArticles(query)
            }
        }
    }

    private fun setupRecyclerView(resultList: List<SearchResponse.SearchQuery.Search>) {
        adapter = SearchAdapter(this, resultList)
        binding.resultsList.adapter = adapter
    }

}