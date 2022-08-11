package com.example.viwiki.home

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentHomeBinding
import timber.log.Timber

/**
 * Home screen of the App. Shows the article of the day.
 */
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Fragment onCreate Called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("Fragment onCreateView Called")
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        // TODO error message for blank response

        // Wire buttons:
        // - refresh button
        /*binding.btnRefresh.setOnClickListener {
            viewModel.fetchTodayFeaturedArticle()
        }*/

        // - gotoFullArticle button
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
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //menuInflater.inflate(R.menu.action_bar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Refresh action
                if (R.id.action_refresh == menuItem.itemId) {
                    viewModel.fetchTodayFeaturedArticle() // Refetch the data
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

}



