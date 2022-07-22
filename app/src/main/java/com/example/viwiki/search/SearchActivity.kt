package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.article_detail.ArticleFragment
import com.example.viwiki.databinding.ActivitySearchBinding
import com.example.viwiki.home.HomeFragment
import com.example.viwiki.utils.Logger
import com.example.viwiki.utils.dummySearchQuery

class SearchActivity : AppCompatActivity() {
    private val TAG = "SearchActivity"

    private val viewModel: SearchViewModel by viewModels()
    private val binding: ActivitySearchBinding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }
    private lateinit var mSearchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Init adapter
        mSearchAdapter = SearchAdapter(this, listOf(SearchResponse.SearchQuery.Search()))
        binding.rvResultsList.adapter = mSearchAdapter
        val divider = DividerItemDecoration(binding.rvResultsList.context, DividerItemDecoration.HORIZONTAL)
        binding.rvResultsList.addItemDecoration(divider)

        // Observe Search live data
        viewModel.searchResponse.observe(this, Observer { response ->
            Logger.logInfo(TAG, "Enter_Observer")
            if (response.query !== null) {
                mSearchAdapter.updateResults(response.query.search)
                //mSearchAdapter.updateResults(dummySearchQuery.search)
            }
        })

        // Verify intent
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewModel.searchArticles(query)
            }
        }
    }

}
