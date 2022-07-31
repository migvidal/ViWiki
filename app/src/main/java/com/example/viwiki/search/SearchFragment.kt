package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentSearchBinding
import com.example.viwiki.utils.Logger

/**
 *
 */
class SearchFragment : Fragment() {
    /**
     * Tag for logging
     */
    private val TAG = "SearchFragment"

    /**
     * SearchViewModel instance
     */
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchQuery: String

    /**
     * Load instance state and trigger search
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.logInfo(TAG, "onCreate Called")
        super.onCreate(savedInstanceState)

        // Verify intents
        val intent = getActivitySafely()?.intent
        if (intent?.action == Intent.ACTION_SEARCH) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY).toString()
            // Trigger the search
            viewModel.searchArticles(searchQuery)
        }
    }


    /**
     * Create layout and start observers
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.logInfo(TAG, "onCreateView Called")

        /**
         * Data binding
         */
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater, R.layout.fragment_search, container, false
        )
        /**
         * Adapter for the recycler view
         */
        val searchAdapter = getActivitySafely()?.let { SearchAdapter(it) }

        // Setup recycler view
        binding.recyclerViewResults.apply {
            adapter = searchAdapter
            setHasFixedSize(true)
        }

        // Observe the SearchResponse
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer { response ->
            val query = response.query
            if (query != null) {
                val searchInfo = query.searchInfo
                // Update binding. UI logic is in the layout file
                binding.searchInfo = searchInfo
                if (searchInfo.totalHits != 0) {
                    // Show hits in action bar
                    // TODO using string resources
                    val totalHitsMessage = "${searchInfo.totalHits} results for '$searchQuery'"
                    getActivitySafely()?.supportActionBar?.title = totalHitsMessage
                    // Update adapter data
                    searchAdapter?.dataSet = query.search
                } else {
                    getActivitySafely()?.supportActionBar?.title = "Results for '$searchQuery'"
                }
            }
        })

        // Return the view
        return binding.root
    }

    /**
     * Returns the activity, after a type check
     */
    private fun getActivitySafely(): SearchActivity? {
        if (activity is SearchActivity) {
            return activity as SearchActivity
        }
        return null
    }


}