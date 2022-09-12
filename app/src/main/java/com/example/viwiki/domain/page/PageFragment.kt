package com.example.viwiki.domain.page

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.viwiki.MainActivity
import com.example.viwiki.R
import com.example.viwiki.ViWikiApplication

import com.example.viwiki.databinding.FragmentPageBinding

class PageFragment : Fragment() {

    /**
     * Navigation safeargs
     */
    private val navArgs: PageFragmentArgs by navArgs()

    /**
     * The title of the page to show
     */
    private lateinit var pageTitle: String

    /**
     * View model for the data
     */
    private val viewModel: PageViewModel by viewModels {
        PageViewModelFactory(
            (getMainActivity()?.application as ViWikiApplication).pageRepositoryImpl,
        pageTitle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set up icon as an X
        getMainActivity()?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        // Get the article title passed by `navArgs`
        navArgs.apply {
            pageTitle = argArticleTitle
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val binding = FragmentPageBinding.inflate(inflater, container, false)

        // Refresh button listener
        binding.statusScreen.btnRefresh.setOnClickListener {
            updatePage() // fetch the article again
        }

        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    private fun updatePage() {
        viewModel.refreshPage()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Override control of the options menu in this fragment.
        // Used to hide / show the menu.
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.action_bar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean =
                false // Let the parent activity handle the selections (e.g. Search or the Up button)

            override fun onPrepareMenu(menu: Menu) {
                menu.clear() // Don't show the menu
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // Set action bar title
        getMainActivity()?.supportActionBar?.title = pageTitle
    }

    /**
     * Gets the parent `MainActivity`
     * @return the main activity, `null` if type is not `MainActivity`
     */
    private fun getMainActivity(): MainActivity? {
        if (activity is MainActivity) {
            return activity as MainActivity
        }
        return null
    }


}