package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.R

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
     * Adapter for the recycler view
     */
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val resultsRecyclerView = activity?.findViewById<RecyclerView>(R.id.recycler_view_results)

        // Set the adapter


        //  FIXME Add divider
        /*val divider = DividerItemDecoration(
            rvResultsList?.context,
            DividerItemDecoration.HORIZONTAL
        )
        rvResultsList?.addItemDecoration(divider)*/

        // Observe Search live data
        viewModel.searchLiveData.observe(this) { response ->
            if (response.query !== null) {
                searchAdapter.dataSet = response.query.search
                //mSearchAdapter.updateResults(dummySearchQuery.search)
            }
        }

        // Verify intent
        val intent = (activity as SearchActivity).intent
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewModel.searchArticles(query)
            }
        }

    }


    /**
     * Inflate layout and return view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        val resultsRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_results)


        searchAdapter = SearchAdapter(activity as SearchActivity)
        resultsRecyclerView?.apply {
            adapter = searchAdapter
            setHasFixedSize(true)
        }

        resultsRecyclerView.adapter = searchAdapter
        // Inflate the layout for this fragment
        return view
    }


}