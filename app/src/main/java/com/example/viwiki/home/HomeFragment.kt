package com.example.viwiki.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentHomeBinding

/**
 * Home screen of the App. Shows the article of the day.
 */
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)


        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        // Click listeners:

        // - refresh button
        binding.statusScreen.btnRefresh.setOnClickListener {
            viewModel.fetchTodayFeaturedArticle()
        }

        // - article card
        binding.cardFeatured.setOnClickListener {
            // Get the tfa (featured article)
            viewModel.featuredArticleResponse.value?.tfa?.let { tfa ->
                val action = HomeFragmentDirections.actionHomeFragmentToArticleFragment()
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
                menuItem.itemId.let {
                    // Refresh action
                    if (R.id.action_refresh == it) {
                        viewModel.fetchTodayFeaturedArticle() // Refetch the data
                    }
                    // About action
                    if (R.id.action_about == it) {
                        // Navigate to About
                        findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)

                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}



