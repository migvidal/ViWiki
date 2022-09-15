package com.example.viwiki.domain.today

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentTodayBinding

/**
 * Home screen of the App. Shows the article of the day.
 */
class TodayFragment : Fragment() {

    val viewModel: TodayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentTodayBinding.inflate(inflater, container, false)

        /**
         * Adapter for the recycler view
         */
        val todayAdapter = TodayAdapter()

        // Observe the Response
        viewModel.todayResponse.observe(viewLifecycleOwner) { response ->
            val list = response.onThisDay
            todayAdapter.submitList(list)
        }

        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.articleAdapter = todayAdapter

        // Click listeners:

        // - refresh button
        // TODO in xml
        binding.statusScreen.btnRefresh.setOnClickListener {
            viewModel.fetchTodayFeaturedArticle()
        }

        // - article card
        binding.homeContainer.setOnClickListener {
            // Get the tfa (featured article)
            viewModel.todayResponse.value?.tfa?.let { tfa ->
                val action =
                    TodayFragmentDirections.actionTodayFragmentToArticleFragment()
                // Pass its title as an argument
                action.argArticleTitle = tfa.normalizedTitle
                // Navigate to fragment
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    // Refresh action
                    R.id.action_refresh -> viewModel.fetchTodayFeaturedArticle() // Refresh the data
                    // Saved articles action
                    R.id.action_saved -> findNavController()
                        .navigate(R.id.action_todayFragment_to_savedFragment) // Navigate to Saved
                    // About action
                    R.id.action_about -> findNavController()
                        .navigate(R.id.action_todayFragment_to_aboutFragment) // Navigate to About
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}



