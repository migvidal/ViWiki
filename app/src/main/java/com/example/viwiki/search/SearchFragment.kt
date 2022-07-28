package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
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

    /**
     * Load instance state and trigger search
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.logInfo(TAG, "onCreate Called")
        super.onCreate(savedInstanceState)

        // Verify intents
        val intent = (activity as SearchActivity).intent
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                // Trigger search
                viewModel.searchArticles(query)
            }
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
        val searchAdapter = SearchAdapter(activity as SearchActivity)

        // Setup recycler view
        binding.recyclerViewResults.apply {
            adapter = searchAdapter
            setHasFixedSize(true)
        }

        // Observe the SearchResponse
        viewModel.searchLiveData.observe(viewLifecycleOwner, Observer { response ->
            val query = response.query
            if (query != null) {
                // Update binding. UI logic is in the layout file
                binding.searchInfo = query.searchInfo
                if (query.searchInfo.totalHits != 0) {
                    // Update adapter data
                    searchAdapter.dataSet = query.search
                }
            }
        })

        // Return the view
        return binding.root
    }


}