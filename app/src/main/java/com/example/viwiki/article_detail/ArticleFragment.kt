package com.example.viwiki.article_detail

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import com.example.viwiki.MainActivity
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    /**
     * Navigation safeargs
     */
    private val navArgs: ArticleFragmentArgs by navArgs()

    /**
     * The title of the article to show
     */
    private var articleTitle: String? = null

    /**
     * View model for the data
     */
    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up icon as an X
        getActivitySafely()?.supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        // Get the article title passed by `navArgs`
        navArgs.apply {
            if (argArticleTitle.isNotBlank()) {
                articleTitle = argArticleTitle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentArticleBinding>(
            inflater, R.layout.fragment_article, container, false
        )

        // Trigger the API request if there's an article name
        if (articleTitle !== null) {
            viewModel.fetchArticleByTitle(articleTitle!!)
        }

        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Override the options menu in this fragment. Used to hide / show the options menu.
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
        getActivitySafely()?.supportActionBar?.title = articleTitle
    }

    private fun getActivitySafely(): MainActivity? {
        if (activity is MainActivity) {
            return activity as MainActivity
        }
        return null
    }
}