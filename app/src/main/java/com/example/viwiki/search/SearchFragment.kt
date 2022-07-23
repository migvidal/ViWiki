package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentSearchBinding
import com.example.viwiki.utils.Logger
import com.example.viwiki.utils.dummySearchQuery

/**
 *
 */
class SearchFragment : Fragment() {
    /**
     * Tag for logging
     */
    private val TAG = "SearchFragment"

    /**
     * Search View model instance for the data
     */
    private val viewModel: SearchViewModel by viewModels()

    /**
     * View binding
     */
    private val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    /**
     * Adapter for the recycler view
     */
    private lateinit var mSearchAdapter: SearchAdapter


    /**
     * Create and return the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Init adapter
        mSearchAdapter =
            SearchAdapter(activity as SearchActivity, listOf(SearchResponse.SearchQuery.Search()))
        binding.rvResultsList.adapter = mSearchAdapter

        val divider =
            DividerItemDecoration(binding.rvResultsList.context, DividerItemDecoration.HORIZONTAL)
        binding.rvResultsList.addItemDecoration(divider)

        // Observe Search live data
        viewModel.searchResponse.observe(activity as SearchActivity) { response ->
            Logger.logInfo(TAG, "Enter_Observer")
            if (response.query !== null) {
                //mSearchAdapter.updateResults(response.query.search)
                mSearchAdapter.updateResults(dummySearchQuery.search)
            }
        }

        // Verify intent
        val intent = (activity as SearchActivity).intent
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewModel.searchArticles(query)
            }
        }

        // Inflate the layout for this fragment
        // TODO using binding
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

}