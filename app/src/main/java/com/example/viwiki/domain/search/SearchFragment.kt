package com.example.viwiki.domain.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.viwiki.MainActivity
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentSearchBinding
import com.example.viwiki.domain.BaseListAdapter
import com.example.viwiki.domain.saved.ListItemClickListener

/**
 * Fragment for the search results screen
 */
class SearchFragment : Fragment() {

    /**
     * Search query
     */
    private lateinit var searchQuery: String

    /**
     * Adapter for the recycler view
     */
    private lateinit var listAdapter: BaseListAdapter<SearchResponse.SearchQuery.Search>

    /**
     * Search ViewModel
     */
    private val viewModel: SearchViewModel by viewModels()

    /**
     * Load instance state and trigger search
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // instantiate adapter
        listAdapter = BaseListAdapter(
            R.layout.result_list_item, ListItemClickListener { pageTitle ->
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra(MainActivity.PAGE_TITLE_EXTRA_KEY, pageTitle)
                context?.startActivity(intent)
            }
        )

        doSearch() // Verify intents and search
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

        // Observe the SearchResponse
        viewModel.searchResponse.observe(viewLifecycleOwner) { response ->
            val query = response.query

            // Generic message for action bar
            setActionBarTitle(
                getString(
                    R.string.action_bar_results_label_generic,
                    searchQuery
                )
            )

            // Show hits in action bar
            query.searchInfo.totalHits.let {
                if (it != 0) {
                    setActionBarTitle(
                        getString(
                            R.string.action_bar_results_label_number,
                            it,
                            searchQuery
                        )
                    )
                }
            }

            // Update adapter data
            listAdapter.submitList(query.search)
        }

        // data binding
        val binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
            it.listAdapter = listAdapter
            // Refresh button listener
            it.statusScreen.btnRefresh.setOnClickListener {
                doSearch() // search again
            }

        }


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