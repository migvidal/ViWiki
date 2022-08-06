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
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentSearchBinding

/**
 *
 */
class SearchFragment : Fragment() {

    /**
     * SearchViewModel instance
     */
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var searchQuery: String

    /**
     * Load instance state and trigger search
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verify intents
        doSearch()
    }


    /**
     * Create layout and start observers
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        viewModel.searchResponse.observe(viewLifecycleOwner, Observer { response ->
            val query = response.query
            if (query != null) {

                // Generic message for action bar
                setActionBarTitle(
                    getString(
                        R.string.action_bar_results_title_generic,
                        searchQuery
                    )
                )

                // Show hits in action bar
                query.searchInfo.totalHits.let {
                    if (it != 0) {
                        try {
                            setActionBarTitle(
                                getString(
                                    R.string.action_bar_results_title_number,
                                    it,
                                    searchQuery
                                )
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                // Update adapter data
                searchAdapter?.dataSet = query.search
            }
        })

        // Refresh button listener
        binding.btnRefresh.setOnClickListener {
            doSearch() // Do the search again
        }

        // Bind data
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

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

    /**
     * Sets the action bar title (aka label)
     */
    private fun setActionBarTitle(title: String) {
        getActivitySafely()?.supportActionBar?.title = title
    }

    /**
     * Searches the query from the SEARCH intent
     */
    private fun doSearch() {
        val intent = getActivitySafely()?.intent
        if (intent?.action == Intent.ACTION_SEARCH) {
            searchQuery = intent.getStringExtra(SearchManager.QUERY).toString()
            // Trigger the search
            viewModel.searchArticles(searchQuery)
        }
    }


}